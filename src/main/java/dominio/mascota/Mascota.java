package dominio.mascota;

import java.util.HashMap;
import java.util.Map;

import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.OpcionInvalida;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.util.Lista;


public class Mascota {
  private final ClaseMascota clase;
  private final String nombre;
  private final String apodo;
  private final int edad;
  private final Sexo sexo;
  private final Lista<String> fotos;
  private final Tamanio tamanio;
  private final Map<String, String> caracteristicas;

  private String descripcionFisica;

  public Mascota(ClaseMascota clase, String nombre, String apodo, int edad, Sexo sexo, Tamanio tamanio) {
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.caracteristicas = new HashMap<String, String>();
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

  public void agregarUnaCaracteristica(String caracteristica, String valor) {
    this.validarCaracteristica(caracteristica,valor);
    this.caracteristicas.put(caracteristica.toUpperCase(), valor.toUpperCase());
  }

  private void validarCaracteristica(String caracteristica, String valor) {
    RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getINSTANCE();
    Caracteristica caracteristica_obtenida = repositorioCaracteristicas.obtenerCaracteristica(caracteristica.toUpperCase());
    if(caracteristica_obtenida==null) {
      throw new CaracteristicaInvalida();
    }
    if (!caracteristica_obtenida.tieneEstaOpcion(valor)){
      throw new OpcionInvalida(caracteristica.toUpperCase());
    }
  }

  public String obtenerCaracteristica(String caracteristica) {
    if (!caracteristicas.containsKey(caracteristica.toUpperCase())) {
      return null;
    }
    return this.caracteristicas.get(caracteristica.toUpperCase());
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
}
