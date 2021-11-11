package controllers;

import java.util.HashMap;
import java.util.Map;

import dominio.usuarios.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public static ModelAndView view(Request req, Response res) {

		final Duenio duenio = Auth.authorize(req);
		final Map<String, Object> model = new HashMap<>();
		if(duenio == null) {
			model.put("logged", false);
			model.put("error", false);
		} else {
			model.put("logged", true);
			model.put("error", false);
		}

		
		return new ModelAndView(model, "home.hbs");
	}
}
