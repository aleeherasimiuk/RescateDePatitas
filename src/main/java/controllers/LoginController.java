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
		// validation and store session
		Boolean error = false;


		res.redirect("/");
		return null;
	}
}
