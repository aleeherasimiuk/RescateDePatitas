import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dominio.rescate.Coordenadas;

public class CoordenadasTest {

  @DisplayName("La distancia entre (0,0) y (1,1) es √2")
  @Test
  void testDistancia1(){
 
    Coordenadas coordenadas      = new Coordenadas(0.0, 0.0);
    Coordenadas otrasCoordenadas = new Coordenadas(1.0, 1.0);

    assertEquals(Math.sqrt(2.0), coordenadas.distanciaA(otrasCoordenadas));
    
  }


  @DisplayName("La distancia entre (0,0) y (-1,1) es √2")
  @Test
  void testDistancia2(){
 
    Coordenadas coordenadas      = new Coordenadas( 0.0, 0.0);
    Coordenadas otrasCoordenadas = new Coordenadas(-1.0, 1.0);

    assertEquals(Math.sqrt(2.0), coordenadas.distanciaA(otrasCoordenadas));
    
  }

  @DisplayName("La distancia entre (0,0) y (0,1) es 1")
  @Test
  void testDistancia3(){
 
    Coordenadas coordenadas      = new Coordenadas(0.0, 0.0);
    Coordenadas otrasCoordenadas = new Coordenadas(0.0, 1.0);

    assertEquals(1, coordenadas.distanciaA(otrasCoordenadas));
    
  }


  @DisplayName("(10,10) es más cerca que (25, -50) con respecto a (3,4)")
  @Test
  void testDistancia4(){
 
    Coordenadas coordenadas    = new Coordenadas( 3.0,   4.0);
    Coordenadas coordsCercanas = new Coordenadas(10.0,  10.0);
    Coordenadas coordsLejanas  = new Coordenadas(25.0, -50.0);

    Coordenadas masCercana = coordenadas.elMasCercano(coordsCercanas, coordsLejanas);

    assertEquals(coordsCercanas, masCercana);
    
  }

  @DisplayName("El parque chacabuco queda más lejos que el parque avellaneda del campus")
  @Test
  void testDistancia5(){

    Coordenadas UTN               = new Coordenadas(-34.65858825852768, -58.46736257475716);
    Coordenadas parqueChacabuco   = new Coordenadas(-34.63481134002147, -58.442202384019055);
    Coordenadas parqueAvellaneda  = new Coordenadas(-34.64388667313111, -58.47976161190845);

    Coordenadas masCercano = UTN.elMasCercano(parqueChacabuco, parqueAvellaneda);
    assertEquals(parqueAvellaneda, masCercano);

  }

  
}
