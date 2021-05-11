package dominio.mascota;

import java.util.HashMap;
import java.util.Map;

import dominio.repositorio.RepositorioCaracteristicas;
import dominio.util.Lista;


public class Mascota {
  private Clase clase;
  private String nombre;
  private String apodo;
  private int edad;
  private Sexo sexo;
  private String descripcionFisica;
  private Lista<String> fotos;
  private Map<String, String> caracteristicas;

  public Mascota(Clase clase, String nombre, String apodo, int edad, Sexo sexo) {
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.caracteristicas = new HashMap<String, String>();
    this.fotos = new Lista<String>();
  }

  public void setDescripcionFisica(String descripcionFisica) {
    this.descripcionFisica = descripcionFisica;
  }

  public void agregarUnaFoto(String url) {
    this.fotos.add(url);
  }

  public void agregarUnaCaracteristica(String caracteristica, String valor) {
    this.validarCaracteristica(caracteristica,valor);
    this.caracteristicas.put(caracteristica.toUpperCase(), valor.toUpperCase());
  }

  private void validarCaracteristica(String caracteristica, String valor) {
    RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getINSTANCE();
    Caracteristica caracteristica_obtenida = repositorioCaracteristicas.obtenerCaracteristica(caracteristica.toUpperCase());
    if(caracteristica_obtenida==null) {
      throw new CaracteristicaInvalida();
    }else if (!caracteristica_obtenida.tieneEstaOpcion(valor)){
      throw new OpcionInvalida(caracteristica);
    }
  }

  public String obtenerCaracteristica(String caracteristica) {
    if (!caracteristicas.containsKey(caracteristica.toUpperCase())) {
      return null;
    }
    return this.caracteristicas.get(caracteristica.toUpperCase());
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
}
