package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;

import dominio.mascota.Caracteristica;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.mascota.Tamanio;
import dominio.personas.DatosPersona;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioRescatesConChapita;
import dominio.repositorio.RepositorioRescatesSinChapita;
import dominio.rescate.DatosRescate;
import dominio.rescate.RescateConChapita;
import dominio.rescate.RescateSinChapita;
import dominio.rescate.Rescatista;
import dominio.ubicacion.Coordenadas;
import dominio.usuarios.Usuario;
import servicios.mail.JavaMail;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RescateController {

  public static ModelAndView viewWithoutBadge(Request request, Response response) {
    Map<String, Object> model = buildModel();
    model.put("error", false);
    Usuario usuario = Auth.authorize(request);
    if(usuario != null){
      model.put("logged", true);
      model.put("admin", usuario.esAdmin());
    } else{
      model.put("logged", false);
      model.put("admin", false);
    }

    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    model.put("hayCaracteristicas", !caracteristicas.isEmpty());
    model.put("characteristicsEven", caracteristicas.subList(0, (caracteristicas.size() + 1) / 2));
    model.put("characteristicsOdd",
        caracteristicas.subList((caracteristicas.size() + 1) / 2, (caracteristicas.size())));

    return new ModelAndView(model, "without_badge.hbs");
  }

  public static ModelAndView viewWithBadge(Request request, Response response) {
    Map<String, Object> model = buildModel();
    String id = request.params("id");
    model.put("action", "/rescates/crear/" + id);
    model.put("error", false);
    Usuario usuario = Auth.authorize(request);
    if(usuario != null){
      model.put("logged", true);
      model.put("admin", usuario.esAdmin());
    } else{
      model.put("logged", false);
      model.put("admin", false);
    }
    

    Mascota mascota = RepositorioMascotas.getINSTANCE().buscarPorId(Long.valueOf(id));
    if(mascota == null) {
      response.status(404);
      response.redirect("/404");
      return new ModelAndView(null, "");
    }

    return new ModelAndView(model, "with_badge.hbs");
  }

  public static ModelAndView withoutBadge(Request request, Response response) {

    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

    final DatosPersona datosPersona = PersonFetcher.getPersona(request);

    final ClaseMascota clase = ClaseMascota.valueOf(request.queryParams("class"));
    final Tamanio tamanio = Tamanio.valueOf(request.queryParams("size"));
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final Sexo sexo = Sexo.valueOf(request.queryParams("gender"));
    final LocalDate fecha = LocalDate.parse(request.queryParams("res_date"), formatter);
    //final String loc = request.queryParams("res_loc");
    final String desc = request.queryParams("desc");
    final String address = request.queryParams("address");
    final List<String> images = ImageFetcher.fetchImageURL(request);

    final Rescatista rescatista = new Rescatista(datosPersona, address);

    DatosRescate datosRescate = new DatosRescate(rescatista, images, fecha, desc, new Coordenadas(0.0, 0.0));

    RescateSinChapita rescateSinChapita = new RescateSinChapita(datosRescate, tamanio, clase, sexo);

    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    caracteristicas.forEach(caracteristica -> {
      if (request.queryParams(caracteristica.getNombre()) != null) {
        rescateSinChapita.agregarUnaCaracteristica(caracteristica.getNombre());
      }
    });

    RepositorioRescatesSinChapita.getINSTANCE().registrar(rescateSinChapita);

    response.redirect("/");
    return new ModelAndView(null, "/");
  }

  public static ModelAndView withBadge(Request request, Response response) {

    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

    final DatosPersona datosPersona = PersonFetcher.getPersona(request);
    final Long id = Long.valueOf(request.params("id"));
    final Mascota mascota = RepositorioMascotas.getINSTANCE().buscarPorId(id);
    if(mascota == null) {
      //response.status(404);
      response.redirect("/404", 404);
      return new ModelAndView(null, "");
    }

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final LocalDate fecha = LocalDate.parse(request.queryParams("res_date"), formatter);
    final String loc = request.queryParams("res_loc");
    final String desc = request.queryParams("desc");
    final String address = request.queryParams("address");
    final List<String> images = ImageFetcher.fetchImageURL(request);

    final Rescatista rescatista = new Rescatista(datosPersona, address);

    final DatosRescate datosRescate = new DatosRescate(rescatista, images, fecha, desc, new Coordenadas(0.0, 0.0));
    final RescateConChapita rescateConChapita = new RescateConChapita(datosRescate, mascota);

    RepositorioRescatesConChapita.getINSTANCE().registrar(rescateConChapita);
    
    rescateConChapita.avisarAlDuenio(new JavaMail());
  
    response.redirect("/");
    return new ModelAndView(null, "");
  }

  private static Map<String, Object> buildModel() {
    Map<String, Object> model = new HashMap<>();
    model.put("sexos", Sexo.values());
    model.put("tamaños", Tamanio.values());
    model.put("clases", ClaseMascota.values());
    model.put("tiposDocumento", TipoDeDocumento.values());
    return model;
  }
}
