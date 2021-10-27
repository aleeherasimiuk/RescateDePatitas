package controllers;

import java.util.HashMap;
import java.util.Map;

import dominio.repositorio.RepositorioValidaciones;
import router.Router;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {

  public static ModelAndView viewStep1(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    return new ModelAndView(model, "signup.hbs");
  }

  public static ModelAndView viewStep2(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    return new ModelAndView(model, "owner.hbs");
  }

  public static ModelAndView signUpStep1(Request request, Response response) {
  
    final Map<String, Object> model = new HashMap<>();
    final String usuario = request.queryParams("username");
    final String password = request.queryParams("password");
    final String passwordConfirmation = request.queryParams("password_confirmation");

    try{
      RepositorioValidaciones.getInstance().validatePassword(password);
    } catch (RuntimeException e){
      model.put("error", true);
      return new ModelAndView(model, "signup.hbs");
    }

    if(!password.equals(passwordConfirmation)){
      model.put("error", true);
      System.out.println("IF");
      return new ModelAndView(model, "signup.hbs");

    }

    request.session().attribute("user", usuario);
    request.session().attribute("password", password);

    response.redirect("/signup/step2");
    return new ModelAndView(null, "");
  }

  public static ModelAndView signUpStep2(Request request, Response response) {

    System.out.println((String) request.session().attribute("user"));
    System.out.println((String) request.session().attribute("password"));

    request.session().removeAttribute("user");
    request.session().removeAttribute("password");

    response.redirect("/login");
    return new ModelAndView(null, "");
  }
}
