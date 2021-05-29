package dominio.rescate;

import java.time.LocalDate;

import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioPublicaciones;
import dominio.rescate.Ubicacion.Coordenadas;
import dominio.util.Lista;
import servicios.JavaMail;
public class Publicacion {
  
  private final Rescate rescate;
  private EstadoPublicacion estado;
  private Asociacion asociacionAsignada;

  public Publicacion(Rescate rescate) {
    this.rescate = rescate;
    this.estado = EstadoPublicacion.PENDIENTE;
  }

  public void identificarMascota(Mascota mascota){
    rescate.setMascota(mascota);
    JavaMail mail = new JavaMail();
    mail.enviarMail("Esto es una prueba", "aleeherasimiuk@gmail.com");
  }

  public void asignarAsociacion(){
    if(asociacionAsignada != null) throw new RuntimeException("La publicación ya tiene una asociación asignada");
    asociacionAsignada = RepositorioAsociaciones.getInstance().obtenerLaMasCercana(rescate);
  }

  public void aprobar(){
    estado = EstadoPublicacion.APROBADA;
  }

  public void rechazar(){
    estado = EstadoPublicacion.RECHAZADA;
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


  public Asociacion getAsociacionAsignada() {
    if(asociacionAsignada == null) throw new RuntimeException("No se ha asignado ninguna asociación");
    return asociacionAsignada;
  }

  /* DATOS NO SENSIBLES */

  public Lista<String> fotos(){
    return rescate.getFotos();
  }

  public int telefonoDeContacto(){
    return rescate.telefonoDeContacto();
  }

  public Coordenadas lugar(){
    return rescate.getLugar();
  }

  public LocalDate fecha(){
    return rescate.getFecha();
  }

  public String descripcion(){
    return rescate.getDescripcion();
  }


 

}
