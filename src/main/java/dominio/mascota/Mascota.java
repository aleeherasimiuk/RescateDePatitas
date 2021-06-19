package dominio.mascota;

import java.util.HashMap;
import java.util.Map;
import dominio.tareas.ValidadorCaracteristica;
import dominio.util.Lista;


public class Mascota {
  private final ClaseMascota clase;
  private final String nombre;
  private final String apodo;
  private final int edad;
  private final Sexo sexo;
  private final Lista<String> fotos;
  private final Tamanio tamanio;
  private final Lista<String> caracteristicas;

  private String descripcionFisica;

  public Mascota(ClaseMascota clase, String nombre, String apodo, int edad, Sexo sexo, Tamanio tamanio) {
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.caracteristicas = new Lista<String>();
    this.fotos = new Lista<String>();
    this.tamanio = tamanio;
  }

  public Tamanio getTamanio() {
    return tamanio;
  }

  public void setDescripcionFisica(String descripcionFisica) {
    this.descripcionFisica = descripcionFisica;
  }

  public void agregarUnaFoto(String url) {
    this.fotos.add(url);
  }

  public void agregarUnaCaracteristica(String caracteristica) {
    new ValidadorCaracteristica().validarCaracteristica(caracteristica);
    this.caracteristicas.add(caracteristica.toUpperCase());
  }

   public ClaseMascota getClase() {
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

  public Lista<String> getCaracteristicas() {
    return caracteristicas;
  }
}
