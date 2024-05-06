package practicaJUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/* Se aconseja “limpiar” las listas de entradas y salas antes de cada test (utilizando una anotación vista en
clase), utilizando el método “vaciarCine()”.
Después, en cada método de test aislado, preparar las listas
con la información necesaria para cada prueba.*/
public class SistemaVentaEntradaTest {
    static SistemaVentaEntradas sistemaVenta;

    @BeforeAll
    static void crearInstanciaSistemaVenta() {
        System.out.println("ANTES DE TODOS LOS TESTS");
        sistemaVenta = new SistemaVentaEntradas();
    }

    //“limpiar” las listas de entradas y salas antes de cada test
    @BeforeEach
    void limpiarCine() {
        sistemaVenta.vaciarCine();
        System.out.println("SISTEMA DE VENTA DE ENTRADAS LIMPIO");
    }
    //esto es más del primer método
    @Test
    @DisplayName("comprobar error si la sala no existe al comprar entrada")
    void comprarEntradaErrorSala() {
        sistemaVenta.anyadirSala(1, "Una rubia muy legal");
        //Compruebo que nos da false si la sala que le indicamos no existe
        assertFalse(sistemaVenta.comprarEntrada(30, 5));
    }

    @ParameterizedTest
    @DisplayName("Comprobar si las butacas son erróneas en varias opciones")
    @CsvSource({"1,-1",
            "1,35"})
    void comprarEntradaErrorButaca(int numero1, int numero2) {
        sistemaVenta.anyadirSala(1, "Una rubia muy legal");
        assertFalse(sistemaVenta.comprarEntrada(numero1, numero2));

    }

    @Test
    @DisplayName("la entrada se compra y se añade a la lista de entradas")
    void comprarEntradasSinErrores() {
        sistemaVenta.anyadirSala(1, "Una rubia muy legal");
        //Comprobar que se crea la entrada
        assertTrue(sistemaVenta.comprarEntrada(1, 5));

    }

    @Test
    @DisplayName("Comprobar que la entrada se añadido a la lista correctamente")
    void comprarEntradasAddListaEntradas() {
        Entrada miEntrada = new Entrada(1, 5, 10.0);
        sistemaVenta.anyadirSala(1, "Una rubia muy legal");
        sistemaVenta.comprarEntrada(1, 5);
        for (Entrada entrada : sistemaVenta.getEntradas()) {
            assertEquals(entrada.getNumButaca(), miEntrada.getNumButaca());
            assertEquals(entrada.getNumSala(), miEntrada.getNumSala());
            assertEquals(entrada.getPrecio(), miEntrada.getPrecio());
        }

    }
    @Test
    @DisplayName("Comprobar método entradas vendidas")
    void comprobarEntradasVendidasTest(){
        sistemaVenta.anyadirSala(1, "Una rubia muy legal");
        sistemaVenta.comprarEntrada(1, 5);
        sistemaVenta.comprarEntrada(1,12);
        assertEquals(sistemaVenta.getEntradasVendidasPorSala(1),2);
    }
    @Test
    @DisplayName("Comprobar ArrayList salas vaciado")
    void vaciarCineTest(){
       int totalSalas= sistemaVenta.getSalas().size();
       assertEquals(0,totalSalas);
    }
    @Test
    @DisplayName("Comprobar ArrayList entradas vaciado")
    void vaciarCineTestEntradas(){
        int totalEntradas=sistemaVenta.getEntradas().size();
        assertEquals(0,totalEntradas);
    }
}



