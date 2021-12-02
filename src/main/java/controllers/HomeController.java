package controllers;

import java.util.HashMap;
import java.util.Map;

import dominio.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public static ModelAndView view(Request req, Response res) {

		final Usuario duenio = Auth.authorize(req);
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
