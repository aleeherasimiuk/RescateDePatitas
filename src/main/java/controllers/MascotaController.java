package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotaController {

  public static ModelAndView view(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    model.put("characteristicsEven", caracteristicas.subList(0, (caracteristicas.size() + 1) / 2));
    model.put("characteristicsOdd",
        caracteristicas.subList((caracteristicas.size() + 1) / 2, (caracteristicas.size())));
    return new ModelAndView(model, "add_pet.hbs");
  }

}
