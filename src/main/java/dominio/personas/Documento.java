package dominio.personas;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Documento {
  @Enumerated(EnumType.STRING)
  private TipoDeDocumento tipo;

  public Documento() {

  }

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
