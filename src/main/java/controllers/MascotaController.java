package controllers;

import java.util.*;

import dominio.mascota.*;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioUsuarios;
import dominio.usuarios.Duenio;
import dominio.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotaController {

  public static ModelAndView view(Request request, Response response) {
    if(authorize(request) == null){
      response.status(401);
      response.redirect("/");
      return new ModelAndView(null, "");
    }
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
    Duenio duenio = authorize(request);
    if(duenio == null){
      response.redirect("/login");
      return new ModelAndView(null, "");
    }
    Map<String, Object> model = new HashMap<>();
    ClaseMascota clase = ClaseMascota.valueOf(request.queryParams("type"));
    String nombre = request.queryParams("name");
    String apodo = request.queryParams("nick");
    Integer edad = Integer.valueOf(request.queryParams("age"));
    Sexo sexo = Sexo.valueOf(request.queryParams("gender"));
    Tamanio tamanio = Tamanio.valueOf(request.queryParams("size"));
    List<Caracteristica> caracteristicas = RepositorioCaracteristicas.getINSTANCE().todos();
    String descripcion = request.queryParams("desc");

    Mascota mascota = new Mascota(clase, nombre, apodo, edad, sexo, tamanio);
    mascota.setDescripcionFisica(descripcion);
    caracteristicas.forEach(caracteristica -> {
      if(request.queryParams(caracteristica.getNombre()) != null){
        mascota.agregarUnaCaracteristica(caracteristica.getNombre());
      }
    });

    duenio.registrarUnaMascota(mascota);

    return new ModelAndView(model, "");
  }

  private static Duenio authorize(Request req){
    Long id = req.session().attribute("session");
    if(id == null){
      return null;
    }
    return RepositorioDuenios.getInstance().buscarPorId(id);
  }

}
