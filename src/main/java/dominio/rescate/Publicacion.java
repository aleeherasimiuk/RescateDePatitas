package dominio.rescate;

import dominio.mascota.Mascota;
import dominio.repositorio.RepositorioPublicaciones;
import dominio.util.Lista;
public class Publicacion {
  
  private final Rescate rescate;
  private EstadoPublicacion estado;
  //private String asociacionAsignada; // TODO

  public Publicacion(Rescate rescate) {
    this.rescate = rescate;
    estado = EstadoPublicacion.PENDIENTE;
  }

  public void aprobar(){
    estado = EstadoPublicacion.APROBADA;
  }

  public void rechazar(){
    estado = EstadoPublicacion.RECHAZADA;
  }

  public Lista<String> fotos(){
    return rescate.getFotos();
  }

  public String datosNoSensibles(){
    return ""; //TODO
  }

  public boolean estaAprobada(){
    return estado == EstadoPublicacion.APROBADA;
  }

  public boolean estaPendiente(){
    return estado == EstadoPublicacion.PENDIENTE;
  }

  public void registrarse(){
    RepositorioPublicaciones.getINSTANCE().registrar(this);
  }

  public void identificarMascota(Mascota mascota){
    rescate.setMascota(mascota);
  }
}
