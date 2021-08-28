package dominio.personas;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Documento {
  @Enumerated(EnumType.ORDINAL)
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
