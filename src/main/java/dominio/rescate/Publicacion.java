package dominio.rescate;


import dominio.asociacion.Asociacion;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.personas.Contacto;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioPublicaciones;
import servicios.MailerRescatista;

public class Publicacion {

  private final DatosRescate datosRescate;
  private final Tamanio tamanio;
  private final ClaseMascota claseMascota;

  private Asociacion asociacionAsignada;
  private EstadoPublicacion estado;

  public Publicacion(DatosRescate datosRescate, Tamanio tamanio, ClaseMascota claseMascota) {
    this.datosRescate = datosRescate;
    this.estado = EstadoPublicacion.PENDIENTE;
    this.claseMascota = claseMascota;
    this.tamanio = tamanio;
  }

  public void confirmarMascotaEncontrada(){
    MailerRescatista mail = new MailerRescatista();
    mail.enviarMail(this);
  }

  public void asignarAsociacion(){
    if(asociacionAsignada != null) throw new RuntimeException("La publicación ya tiene una asociación asignada");
    asociacionAsignada = RepositorioAsociaciones.getInstance().obtenerLaMasCercana(datosRescate.getLugar());
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

  public void asignarHogar(Hogar hogar){
    if(!hogar.aceptaMascota(claseMascota, tamanio))
      throw new RuntimeException("El hogar solicitado no acepta esta mascota");
    this.datosRescate.setHogar(hogar);
  }

  public Asociacion getAsociacionAsignada() {
    if(asociacionAsignada == null) throw new RuntimeException("No se ha asignado ninguna asociación");
    return asociacionAsignada;
  }

  public String emailDeContacto(){
    return getContacto().getEmail();
  }

  public Contacto getContacto() {
    return datosRescate.getRescatista().getDatosPersona().getContacto();
  }

  
}
