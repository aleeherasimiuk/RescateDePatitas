package dominio.mascota;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mascota {
  private int idDuenio;
  private int idMascota;
  private Clase clase;
  private String nombre;
  private String apodo;
  private int edad;
  private Sexo sexo;
  private String descripcionFisica;
  private List<String> fotos;
  private Map<String, String> caracteristicas;

  public Mascota(int idMascota, int idDuenio, Clase clase, String nombre, String apodo, int edad, Sexo sexo) {
    this.idDuenio = idDuenio;
    this.idMascota = idMascota;
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    caracteristicas = new HashMap<String, String>();
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
      throw new RuntimeException("La mascota no contiene la característica deseada");
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
