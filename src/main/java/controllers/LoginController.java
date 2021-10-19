package controllers;

import dominio.repositorio.RepositorioDuenios;
import dominio.usuarios.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import javax.persistence.NoResultException;

public class LoginController {
  

  public static ModelAndView view(Request req, Response res) {
    return new ModelAndView(null, "login.hbs");
  }

  public static ModelAndView login(Request req, Response res) {
    final String username = req.queryParams("username");
    final String password = req.queryParams("password");
    Duenio duenio;

    try{
      duenio = RepositorioDuenios.getInstance().getDuenioByUsername(username);
    } catch (NoResultException e){
      duenio = null;
    }

    HashMap<String, Object> model = new HashMap<>();

    if (duenio == null || !duenio.login(password)){
      res.status(401);

      model.put("error",true);
      return new ModelAndView(model,"login.hbs");
    }

    model.put("error",false);
    return  new ModelAndView(model, "login.hbs");
  }

}
