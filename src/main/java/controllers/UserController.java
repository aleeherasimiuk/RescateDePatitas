package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioValidaciones;
import dominio.usuarios.Duenio;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;


public class UserController implements TransactionalOps, WithGlobalEntityManager {

  public static ModelAndView viewStep1(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    return new ModelAndView(model, "signup.hbs");
  }

  public static ModelAndView viewStep2(Request request, Response response) {
    System.out.println(request.cookie("stepOne"));
    if(request.session().attribute("user") == null){
      response.redirect("/signup/step1");
    }
    Map<String, Object> model = new HashMap<>();
    model.put("error", false);
    model.put("tiposDocumento", TipoDeDocumento.values());
    return new ModelAndView(model, "owner.hbs");
  }

  public static ModelAndView signUpStep1(Request request, Response response) {

    final Map<String, Object> model = new HashMap<>();
    final String usuario = request.queryParams("username");
    final String password = request.queryParams("password");
    final String passwordConfirmation = request.queryParams("password_confirmation");

    try{
      RepositorioValidaciones.getInstance().validatePassword(password);
    } catch (RuntimeException e){
      model.put("contrasenia_vulnerable", true);
      return new ModelAndView(model, "signup.hbs");
    }

    if(RepositorioDuenios.getInstance().getDuenioByUsername(usuario)!=null){
      model.put("usuario_existente", true);
      return new ModelAndView(model, "signup.hbs");
    }

    if(!password.equals(passwordConfirmation)){
      model.put("contrasenias_no_coinciden", true);
      return new ModelAndView(model, "signup.hbs");
    }

    request.session().attribute("user", usuario);
    request.session().attribute("password", password);

    response.redirect("/signup/step2");
    return new ModelAndView(null, "");
  }

  public static ModelAndView signUpStep2(Request request, Response response) {
    final String user = request.session().attribute("user");
    final String password = request.session().attribute("password");
    
    final DatosPersona datosPersona = PersonFetcher.getPersona(request);

    final Duenio duenio = new Duenio(user, password, datosPersona);

    RepositorioDuenios.getInstance().registrar(duenio);
    
    request.session().removeAttribute("user");
    request.session().removeAttribute("password");

    response.redirect("/login");
    return new ModelAndView(null, "");
  }
}
