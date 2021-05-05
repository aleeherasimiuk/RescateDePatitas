package dominio.mascota;

import java.util.HashMap;
import java.util.Map;

import dominio.util.Lista;

public class Mascota {
  private int idDuenio;
  private int idMascota;
  private Clase clase;
  private String nombre;
  private String apodo;
  private int edad;
  private Sexo sexo;
  private String descripcionFisica;
  private Lista<String> fotos;
  private Map<String, String> caracteristicas;
  private static int idSiguiente = 0;

  public Mascota(int idMascota, int idDuenio, Clase clase, String nombre, String apodo, int edad, Sexo sexo) {
    this.idDuenio = idDuenio;
    this.idMascota = idMascota;
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    caracteristicas = new HashMap<String, String>();
    this.fotos = new Lista<String>();
  }

  public Mascota(int idDuenio, Clase clase, String nombre, String apodo, int edad, Sexo sexo) {
    this(idSiguiente++, idDuenio, clase, nombre, apodo, edad, sexo);
  }

  public void setDescripcionFisica(String descripcionFisica) {
    this.descripcionFisica = descripcionFisica;
  }

  public void agregarUnaFoto(String url) {
    this.fotos.add(url);
  }

  public void agregarUnaCaracteristica(String caracteristica, String valor) {
    this.caracteristicas.put(caracteristica, valor);
  }

  public String obtenerCaracteristica(String caracteristica){
    if(!caracteristicas.containsKey(caracteristica)){
      return null;
    }

    return this.caracteristicas.get(caracteristica);
  }

  public Clase getClase() {
    return clase;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApodo() {
    return apodo;
  }

  public int getEdad() {
    return edad;
  }

  public Sexo getSexo() {
    return sexo;
  }

  public String getDescripcionFisica() {
    return descripcionFisica;
  }

  public int getId() {
    return idMascota;
  }

  public int getIdDuenio() {
    return idDuenio;
  }
}
