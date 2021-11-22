package controllers;

import java.io.IOException;
import java.util.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import dominio.mascota.*;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.usuarios.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotaController {

  public static ModelAndView view(Request request, Response response) {
    if(Auth.authorize(request) == null){
      response.status(401);
      response.redirect("/login");
      return new ModelAndView(null, "");
    }
    Map<String, Object> model = new HashMap<>();
    model.put("logged", true);
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    model.put("characteristicsEven", caracteristicas.subList(0, (caracteristicas.size() + 1) / 2));
    model.put("characteristicsOdd", caracteristicas.subList((caracteristicas.size() + 1) / 2, (caracteristicas.size())));
    model.put("sexos", Sexo.values());
    model.put("tamaños", Tamanio.values());
    model.put("clases", ClaseMascota.values());
    model.put("hayCaracteristicas", !caracteristicas.isEmpty());
    return new ModelAndView(model, "add_pet.hbs");
  }

  public static ModelAndView create(Request request, Response response) throws IOException, ServletException {
    final Duenio duenio = (Duenio) Auth.authorize(request);
    if(duenio == null){
      response.redirect("/login");
      return new ModelAndView(null, "");
    }
    final Map<String, Object> model = new HashMap<>();
    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
    System.out.println(request.queryParams());

    final ClaseMascota clase = ClaseMascota.valueOf(request.queryParams("type"));
    final String nombre = request.queryParams("name");
    final String apodo = request.queryParams("nick");
    final Integer edad = Integer.valueOf(request.queryParams("age"));
    final Sexo sexo = Sexo.valueOf(request.queryParams("gender"));
    final Tamanio tamanio = Tamanio.valueOf(request.queryParams("size"));
    final List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    final String descripcion = request.queryParams("desc");
    final List<String> images = ImageFetcher.fetchImageURL(request);

    final Mascota mascota = new Mascota(clase, nombre, apodo, edad, sexo, tamanio);
    mascota.setDescripcionFisica(descripcion);
    caracteristicas.forEach(caracteristica -> {
      if(request.queryParams(caracteristica.getNombre()) != null){
        mascota.agregarUnaCaracteristica(caracteristica.getNombre());
      }
    });

    images.forEach(url -> {
      mascota.agregarUnaFoto(url);
    });

    duenio.registrarUnaMascota(mascota);

    response.redirect("/");
    return new ModelAndView(model, "");
  }

}
