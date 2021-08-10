package dominio.rescate;

import dominio.asociacion.Asociacion;
import dominio.exceptions.HogarNoAceptaMascota;
import dominio.exceptions.NoHayAsociacionAsignadaAlRescate;
import dominio.exceptions.YaHayUnaAsociacionAsignada;
import dominio.hogares.Hogar;
import dominio.mascota.ClaseMascota;
import dominio.mascota.Tamanio;
import dominio.personas.Contacto;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioRescatesSinChapita;
import dominio.tareas.ValidadorCaracteristica;
import dominio.util.Lista;
import servicios.mail.EmailException;
import servicios.mail.JavaMail;
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

  public void confirmarMascotaEncontrada(JavaMail javaMail){
    MailRescateSinChapita mailer = new MailRescateSinChapita(this);
    try{
      javaMail.enviarMail(mailer);
    }catch(EmailException e){
      System.out.println("Error al enviar el mail" + e.getMessage());
      // Encolar para la próxima vez
    }
    datosRescate.confirmarEncuentro();
  }

  public void asignarAsociacion(){
    if(asociacionAsignada != null) throw new YaHayUnaAsociacionAsignada();
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
    RepositorioRescatesSinChapita.getINSTANCE().registrar(this);
  }

  public void asignarHogar(Hogar hogar){
    if(!hogar.aceptaMascota(claseMascota, tamanio))
      throw new HogarNoAceptaMascota();
    this.datosRescate.setHogar(hogar);
  }

  public void agregarUnaCaracteristica(String caracteristica) {
    new ValidadorCaracteristica().validarCaracteristica(caracteristica);
    this.caracteristicas.add(caracteristica.toUpperCase());
  }

  public Asociacion getAsociacionAsignada() {
    if(asociacionAsignada == null) throw new NoHayAsociacionAsignadaAlRescate();
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
