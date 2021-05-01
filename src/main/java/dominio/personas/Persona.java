package dominio.personas;

public class Persona {
  private String apellido;
  private String nombre;
  private TipoDeDocumento tipoDocumento;
  private int numeroDocumento;
  private Contacto contacto;

  public Persona(String apellido, String nombre, TipoDeDocumento tipoDocumento, int numeroDocumento, Contacto contacto) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
  }

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public TipoDeDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public int getNumeroDocumento() {
    return numeroDocumento;
  }

  public Contacto getContacto() {
    return contacto;
  }
}
