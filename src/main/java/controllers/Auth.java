package controllers;

import dominio.repositorio.RepositorioDuenios;
import dominio.usuarios.Duenio;
import spark.Request;

public class Auth {
  public static Duenio authorize(Request req){
    Long id = req.session().attribute("session");
    return id == null? null : RepositorioDuenios.getInstance().buscarPorId(id);
  }
}
