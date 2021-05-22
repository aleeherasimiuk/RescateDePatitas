package dominio.personas;

public class Documento {
  private TipoDeDocumento tipo;

  public TipoDeDocumento getTipo() {
    return tipo;
  }

  private String numero;

  public String numero() {
    return numero;
  }

  public Documento(TipoDeDocumento tipo, String numero) {
    this.tipo = tipo;
    this.numero = numero;
  }
}
