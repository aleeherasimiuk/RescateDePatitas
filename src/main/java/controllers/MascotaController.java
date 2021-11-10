package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import dominio.mascota.*;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.repositorio.RepositorioDuenios;
import dominio.usuarios.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.utils.IOUtils;

public class MascotaController {

  public static ModelAndView view(Request request, Response response) {
    if(authorize(request) == null){
      response.status(401);
      response.redirect("/");
      return new ModelAndView(null, "");
    }
    Map<String, Object> model = new HashMap<>();
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
    Duenio duenio = authorize(request);
    if(duenio == null){
      response.redirect("/login");
      return new ModelAndView(null, "");
    }
    Map<String, Object> model = new HashMap<>();
    System.out.println("LO SIGUIENTE ES EL TYPE");
    String location = "imagenes";
    long maxFileSize = 100000000;
    long maxRequestSize = 100000000;
    int fileSizeThreshold = 1024;
    request.attribute("org.eclipse.jetty.multipartConfig",
        new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    System.out.println(request.queryParams("type"));
    ClaseMascota clase = ClaseMascota.valueOf(request.queryParams("type"));
    String nombre = request.queryParams("name");
    String apodo = request.queryParams("nick");
    Integer edad = Integer.valueOf(request.queryParams("age"));
    Sexo sexo = Sexo.valueOf(request.queryParams("gender"));
    Tamanio tamanio = Tamanio.valueOf(request.queryParams("size"));
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    String descripcion = request.queryParams("desc");
    InputStream fotos = getFotos(request);

    try {
      // TODO: hay que revisar como guardar las imaganes correctamente en un directorio existente, revisar si es relative o absolute
      final OutputStream fos = new FileOutputStream("images/" + nombre + ".jpg");
      IOUtils.copy(fotos, fos);
      fos.flush();
      fos.close();
    } catch (IOException e1) {
      e1.printStackTrace();
      response.redirect("/error");
    }

    Mascota mascota = new Mascota(clase, nombre, apodo, edad, sexo, tamanio);
    mascota.setDescripcionFisica(descripcion);
    caracteristicas.forEach(caracteristica -> {
      if(request.queryParams(caracteristica.getNombre()) != null){
        mascota.agregarUnaCaracteristica(caracteristica.getNombre());
      }
    });

    duenio.registrarUnaMascota(mascota);

    response.redirect("/");
    return new ModelAndView(model, "");
  }

  private static Duenio authorize(Request req){
    Long id = req.session().attribute("session");
    if(id == null){
      return null;
    }
    return RepositorioDuenios.getInstance().buscarPorId(id);
  }

  private static InputStream getFotos(Request req) {
    String location = "imagenes";
    long maxFileSize = 100000000;
    long maxRequestSize = 100000000;
    int fileSizeThreshold = 1024;
    req.attribute("org.eclipse.jetty.multipartConfig",
        new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    try {
      Part part = req.raw().getPart("photos");
      System.out.println("leyendo archivo => " + req.raw().getPart("photos").getSubmittedFileName());
      return part.getInputStream();
    } catch (IOException | ServletException e) {
      // Todo excepciones personalizadas
      e.printStackTrace();
    }

    return null;
  }

}
