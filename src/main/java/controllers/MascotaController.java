package controllers;

import java.util.*;

import dominio.mascota.*;
import dominio.repositorio.RepositorioCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotaController {

  public static ModelAndView view(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    model.put("characteristicsEven", caracteristicas.subList(0, (caracteristicas.size() + 1) / 2));
    model.put("characteristicsOdd", caracteristicas.subList((caracteristicas.size() + 1) / 2, (caracteristicas.size())));
    model.put("sexos", Sexo.values());
    model.put("tamaños", Tamanio.values());
    model.put("clases", ClaseMascota.values());
    model.put("hayCaracteristicas", !caracteristicas.isEmpty());
    return new ModelAndView(model, "add_pet.hbs");
  }

  public static ModelAndView create(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    ClaseMascota clase = ClaseMascota.valueOf(request.queryParams("type"));
    String nombre = request.queryParams("name");
    String apodo = request.queryParams("nick");
    Integer edad = Integer.valueOf(request.queryParams("age"));
    Sexo sexo = Sexo.valueOf(request.queryParams("gender"));
    Tamanio tamanio = Tamanio.valueOf(request.queryParams("size"));
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    System.out.println(request.queryParams(caracteristicas.get(0).getNombre()));

    caracteristicas.forEach(caracteristica -> {
      System.out.println(caracteristica.getNombre());
      System.out.println(request.queryParams(caracteristica.getNombre()));
      /*
      if(request.queryParams(caracteristica.getNombre()) == "False"){
        caracteristicas.remove(caracteristica);
      }*/
    });

    Mascota mascota = new Mascota(clase, nombre, apodo, edad, sexo, tamanio);
    caracteristicas.forEach(caracteristica -> {
      mascota.agregarUnaCaracteristica(caracteristica.getNombre());
    });



    return new ModelAndView(model, "");
  }

}
