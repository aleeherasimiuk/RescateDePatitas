package dominio.mascota;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import dominio.repositorio.RepositorioDuenios;
import dominio.tareas.ValidadorCaracteristica;
import dominio.usuarios.Duenio;
import persistencia.PersistentEntity;

@Entity
@Table(name = "mascotas")
public class Mascota extends PersistentEntity {

  @Enumerated(EnumType.STRING)
  private ClaseMascota clase;

  private String nombre;
  private String apodo;
  private int edad;

  @Enumerated(EnumType.STRING)
  private Sexo sexo;

  @ElementCollection
  @CollectionTable(name = "fotos_mascota", joinColumns=@JoinColumn(name="mascota_id"))
  @Column(name="url")
  private List<String> fotos;

  @Enumerated(EnumType.STRING)
  private Tamanio tamanio;

  @ElementCollection
  @CollectionTable(name = "caracteristicas_mascota", joinColumns=@JoinColumn(name="mascota_id"))
  @Column(name="descripcion")
  private List<String> caracteristicas;

  @Column(name="descripcion")
  private String descripcionFisica;

  protected Mascota(){}

  public Mascota(ClaseMascota clase, String nombre, String apodo, int edad, Sexo sexo, Tamanio tamanio) {
    this.clase = clase;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.caracteristicas = new ArrayList<String>();
    this.fotos = new ArrayList<String>();
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
    new ValidadorCaracteristica().validarCaracteristica(caracteristica.toUpperCase());
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

  public void setApodo(String apodo) {
    this.apodo = apodo;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getDescripcionFisica() {
    return descripcionFisica;
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }

  public boolean tieneEstaCaracteristica(String caracteristica){
    return caracteristicas.contains(caracteristica);
  }

  public Duenio obtenerDuenio() {
    return RepositorioDuenios.getInstance().duenioDe(this);
  }

}
