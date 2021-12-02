package controllers;

import java.util.HashMap;
import java.util.Map;

import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicasController {

  public static ModelAndView view(Request request, Response response){
    if(!authorize(request)){
      response.status(401);
      response.redirect("/");
      return new ModelAndView(null, "");
    }
    Map<String, Object> model = new HashMap<>();
    model.put("logged", true);
    model.put("admin", true);
    model.put("caracteristicas", RepositorioCaracteristicas.getINSTANCE().todos());
    return new ModelAndView(model, "characteristics.hbs");
  }

  public static ModelAndView delete(Request request, Response response){
    if(!authorize(request)){
      response.status(401);
      response.redirect("/login");
      return new ModelAndView(null, "");
    }
    final String value = request.queryParams("name");
    System.out.println("Borrando: " + value);
    RepositorioCaracteristicas.getINSTANCE().borrarPorNombre(value);
    response.redirect("/caracteristicas");
    return new ModelAndView(null, "");
  }

  public static ModelAndView create(Request request, Response response){
    if(!authorize(request)){
      response.status(401);
      response.redirect("/login");
      return new ModelAndView(null, "");
    }
    final String value = request.queryParams("name");
    RepositorioCaracteristicas.getINSTANCE().registrar(new Caracteristica(value));
    System.out.println("Borrando: " + value);
    response.redirect("/caracteristicas");
    return new ModelAndView(null, "");
  }

  private static boolean authorize(Request req){
    Long id = req.session().attribute("session");
    return id != null && Auth.authorize(req).esAdmin();
  }
}
