package dominio.rescate;


import java.util.HashMap;
import java.util.Map;

import dominio.asociacion.Asociacion;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.personas.Contacto;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioPublicaciones;
import dominio.tareas.ValidadorCaracteristica;
import servicios.mail.MailerRescatista;

public class Publicacion {

  private final DatosRescate datosRescate;
  private final Tamanio tamanio;
  private final ClaseMascota claseMascota;
  private final Map<String, String> caracteristicas;

  private Asociacion asociacionAsignada;
  private EstadoPublicacion estado;

  public Publicacion(DatosRescate datosRescate, Tamanio tamanio, ClaseMascota claseMascota) {
    this.datosRescate = datosRescate;
    this.estado = EstadoPublicacion.PENDIENTE;
    this.claseMascota = claseMascota;
    this.tamanio = tamanio;
    caracteristicas = new HashMap<>();
  }

  public void confirmarMascotaEncontrada(){
    MailerRescatista mail = new MailerRescatista();
    mail.enviarMail(this);
    datosRescate.confirmarEncuentro();
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

  public void agregarUnaCaracteristica(String caracteristica, String valor) {
    ValidadorCaracteristica.validarCaracteristica(caracteristica,valor);
    this.caracteristicas.put(caracteristica.toUpperCase(), valor.toUpperCase());
  }

  public String obtenerCaracteristica(String caracteristica) {
    if (!caracteristicas.containsKey(caracteristica.toUpperCase())) {
      return null;
    }
    return this.caracteristicas.get(caracteristica.toUpperCase());
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

  public DatosRescate getDatosRescate() {
    return datosRescate;
  }

  public Tamanio getTamanio() {
    return tamanio;
  }

  public ClaseMascota getClaseMascota() {
    return claseMascota;
  }

  public Map<String, String> getCaracteristicas() {
    return caracteristicas;
  }

  
}
