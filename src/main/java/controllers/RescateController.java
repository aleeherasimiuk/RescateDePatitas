package controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RescateController {


  public static ModelAndView view(Request request, Response response){
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    return new ModelAndView(model, "rescuer.hbs");
  }
  
}
