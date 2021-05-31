package services.hogares;

import dominio.util.Lista;

public class HogarResponse {
  public String nombre;
  public Ubicacion ubicacion;
  public String telefono;
  public Admision admisiones;
  public int lugares_disponibles;
  public boolean patio;
  public Lista<String> caracteristicas;
}
