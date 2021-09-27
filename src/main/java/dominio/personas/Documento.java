package dominio.personas;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Documento {
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_documento")
  private TipoDeDocumento tipo;

  protected Documento() {}

  public TipoDeDocumento getTipo() {
    return tipo;
  }

  @Column(name = "numero_documento")
  private String numero;

  public String numero() {
    return numero;
  }

  public Documento(TipoDeDocumento tipo, String numero) {
    this.tipo = tipo;
    this.numero = numero;
  }
}
