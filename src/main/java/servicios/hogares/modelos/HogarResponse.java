package servicios.hogares.modelos;

import java.util.List;

public class HogarResponse {
  public String nombre;
  public Ubicacion ubicacion;
  public String telefono;
  public Admision admisiones;
  public int lugares_disponibles;
  public boolean patio;
  public List<String> caracteristicas;
}
