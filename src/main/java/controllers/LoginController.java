package controllers;

import dominio.repositorio.RepositorioDuenios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController {
  public static ModelAndView view(Request req, Response res) {
    return new ModelAndView(null, "login.hbs");
  }

  public static ModelAndView login(Request req, Response res) {
    String id = req.queryParams("email");
    System.out.println(id);

    // res.redirect("/");
    String user = req.queryParams("user");
    String password = req.queryParams("password");

    try {
      RepositorioDuenios.getInstance().login(user,password);
      res.status(200);
    }catch (RuntimeException e){
      res.status(401);
    }

    return null;// new ModelAndView(null, null);
  }

}
