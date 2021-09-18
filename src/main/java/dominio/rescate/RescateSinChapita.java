package dominio.rescate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
import persistencia.PersistentEntity;
import servicios.mail.EmailException;
import servicios.mail.JavaMail;
import servicios.mail.MailRescateSinChapita;

@Entity
public class RescateSinChapita extends PersistentEntity{

  @OneToOne
  private DatosRescate datosRescate;

  @Enumerated(EnumType.STRING)
  private Tamanio tamanio;
  @Enumerated(EnumType.STRING)
  private ClaseMascota claseMascota;
  @ElementCollection
  @CollectionTable(name = "caracteristicas_rescate", joinColumns=@JoinColumn(name="rescate_id"))
  private List<String> caracteristicas_rescate;

  @ManyToOne
  private Asociacion asociacionAsignada;
  @Enumerated(EnumType.STRING)
  private EstadoPublicacion estado;

  protected RescateSinChapita() {}

  public RescateSinChapita(DatosRescate datosRescate, Tamanio tamanio, ClaseMascota claseMascota) {
    this.datosRescate = datosRescate;
    this.estado = EstadoPublicacion.PENDIENTE;
    this.claseMascota = claseMascota;
    this.tamanio = tamanio;
    caracteristicas_rescate = new ArrayList<String>();
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
    this.caracteristicas_rescate.add(caracteristica.toUpperCase());
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

  public List<String> getCaracteristicas() {
    return caracteristicas_rescate;
  }


}
