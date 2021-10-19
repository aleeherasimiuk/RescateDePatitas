package controllers;

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
    return new ModelAndView(null, "login.hbs");
  }

}
