package controllers;

import dominio.repositorio.RepositorioUsuarios;
import dominio.usuarios.Usuario;
import spark.Request;

public class Auth {
  public static Usuario authorize(Request req){
    Long id = req.session().attribute("session");
    return id == null? null : RepositorioUsuarios.getInstance().buscarPorId(id);
  }
}
