package dominio.mascota;

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


  public Mascota(int idDuenio, Clase clase, String nombre, String apodo, int edad, Sexo sexo) {
    this.idDuenio = idDuenio;
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
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

  public String getDescripcionFisica(){
    return descripcionFisica;
  }

  public int getId(){
    return idMascota;
  }

  public int getIdDuenio(){
    return idDuenio;
  }
}


