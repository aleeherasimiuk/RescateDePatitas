package adopcion;

import dominio.mascota.Tamanio;
import dominio.util.Lista;

public class SolicitudAdopcion {
  private final boolean aceptaPerros;
  private final boolean aceptaGatos;
  private final boolean tienePatio;
  private final Lista<Tamanio> tamaniosSoportados;
  private final Lista<String> condiciones; // necesitaPatioGrande, 
  
  public SolicitudAdopcion(boolean aceptaPerros, boolean aceptaGatos, boolean tienePatio) {
    super();
    this.aceptaPerros = aceptaPerros;
    this.aceptaGatos = aceptaGatos;
    this.tienePatio = tienePatio;
    this.tamaniosSoportados = new Lista<>();
    this.condiciones = new Lista<>();    
  }
}
