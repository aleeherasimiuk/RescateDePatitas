package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RescateController {


  public static ModelAndView viewWithoutBadge(Request request, Response response){
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    model.put("characteristicsEven", caracteristicas.subList(0, (caracteristicas.size() + 1) / 2));
    model.put("characteristicsOdd",
        caracteristicas.subList((caracteristicas.size() + 1) / 2, (caracteristicas.size())));
    return new ModelAndView(model, "without_badge.hbs");
  }

  public static ModelAndView viewWithBadge(Request request, Response response){
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    return new ModelAndView(model, "with_badge.hbs");
  }
  
}
