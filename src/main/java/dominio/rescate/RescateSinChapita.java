package dominio.rescate;

import dominio.asociacion.Asociacion;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.personas.Contacto;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioPublicaciones;
import dominio.tareas.ValidadorCaracteristica;
import dominio.util.Lista;
import servicios.mail.MailRescateSinChapita;

public class RescateSinChapita {

  private final DatosRescate datosRescate;
  private final Tamanio tamanio;
  private final ClaseMascota claseMascota;
  private final Lista<String> caracteristicas;

  private Asociacion asociacionAsignada;
  private EstadoPublicacion estado;

  public RescateSinChapita(DatosRescate datosRescate, Tamanio tamanio, ClaseMascota claseMascota) {
    this.datosRescate = datosRescate;
    this.estado = EstadoPublicacion.PENDIENTE;
    this.claseMascota = claseMascota;
    this.tamanio = tamanio;
    caracteristicas = new Lista<String>();
  }

  public void confirmarMascotaEncontrada(){
    MailRescateSinChapita mail = new MailRescateSinChapita();
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

  public void agregarUnaCaracteristica(String caracteristica) {
    new ValidadorCaracteristica().validarCaracteristica(caracteristica);
    this.caracteristicas.add(caracteristica.toUpperCase());
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

  public Lista<String> getCaracteristicas() {
    return caracteristicas;
  }

  
}
