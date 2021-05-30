package services.hogares;

import java.util.List;

public class Hogar {
  public String id;
  public String nombre;
  public Ubicacion ubicacion;
  public String telefono;
  public Admision admision;
  public int capacidad;
  public int lugares_disponibles;
  public boolean patio;
  public List<String> caracteristicas;
}
