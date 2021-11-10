package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.personas.Documento;
import dominio.personas.TipoDeDocumento;
import spark.Request;

public class PersonFetcher {

  public static DatosPersona getPersona(Request request) {

    final String fName = request.queryParams("fname");
    final String lName = request.queryParams("lname");
    final String document = request.queryParams("document");
    final String contactFName = request.queryParams("contact_fname");
    final String contactLName = request.queryParams("contact_lname");
    final String contactEmail = request.queryParams("contact_email");
    final String contactPhone = request.queryParams("contact_phone");
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final LocalDate birthday = LocalDate.parse(request.queryParams("birthday"), formatter);
    final TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(request.queryParams("document_type"));

    return new DatosPersona(lName, fName, new Documento(tipoDeDocumento, document),
        new Contacto(contactFName, contactLName, contactPhone, contactEmail), birthday);
  }

}
