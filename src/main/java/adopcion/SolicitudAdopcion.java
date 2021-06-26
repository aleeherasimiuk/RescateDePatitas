package adopcion;

import dominio.mascota.Tamanio;
import dominio.util.Lista;

public class SolicitudAdopcion {
  private boolean aceptaPerros;
  private boolean aceptaGatos;
  private boolean tienePatio;
  private final Lista<Tamanio> tamaniosSoportados;
  private final Lista<String> condiciones; // necesitaPatioGrande, 
  
  public SolicitudAdopcion(boolean aceptaPerros, boolean aceptaGatos, boolean tienePatio) {
    super();
    this.aceptaPerros = aceptaPerros;
    this.aceptaGatos = aceptaGatos;
    this.tienePatio = tienePatio;
    
    tamaniosSoportados = new Lista<>();
    condiciones = new Lista<>();    
  }
  
  public void agregarCondicion(String condicion) {
    this.condiciones.add(condicion);
  }
  
  public void agregarTamanioSoportado(Tamanio tamanioSoportado) {
    this.tamaniosSoportados.add(tamanioSoportado);
  }

  public Lista<Tamanio> getTamaniosSoportados() {
    return tamaniosSoportados;
  }

  public Lista<String> getCondiciones() {
    return condiciones;
  }

  public void confirmarAceptaPerros() {
    this.aceptaPerros = true;
  }

  public void confirmarAceptaGatos() {
    this.aceptaGatos = true;
  }

  public void confirmarTienePatio() {
    this.tienePatio = true;
  }
  
}
