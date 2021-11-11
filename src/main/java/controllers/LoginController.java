package controllers;

import dominio.repositorio.RepositorioUsuarios;
import dominio.tareas.ValidadorPassword;
import dominio.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

public class LoginController {

  public static ModelAndView view(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    return new ModelAndView(model, "login.hbs");
  }

  public static ModelAndView login(Request req, Response res) {

    if(req.session().attribute("session") != null){
      res.redirect("/");
    }


    final String username = req.queryParams("username");
    final String password = req.queryParams("password");
    Usuario usuario;
    HashMap<String, Object> model = new HashMap<>();

    try{
      usuario = RepositorioUsuarios.getInstance().buscarPorNombreDeUsuario(username);
    } catch (NoResultException e){
      res.status(401);
      model.put("error", true);
      model.put("logged",false);
      return new ModelAndView(model, "login.hbs");
    }

    System.out.println(usuario.getUsername());

    ValidadorPassword validator = new ValidadorPassword();

    if (!validator.login(password, usuario.getPassword())){
      res.status(401);
      model.put("error",true);
      return new ModelAndView(model,"login.hbs");
    }

    model.put("error",false);
    model.put("logged",true);

    req.session().attribute("session", usuario.getId());

    res.redirect("/");

    return new ModelAndView(model, "home.hbs");

  }

  public static ModelAndView logout(Request req, Response res){
    req.session().removeAttribute("session");
    res.redirect("/");
    return new ModelAndView(null, "");
  }
}
