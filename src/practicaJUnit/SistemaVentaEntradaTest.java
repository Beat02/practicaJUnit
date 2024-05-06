package practicaJUnit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaVentaEntradaTest {
    static SistemaVentaEntradas sistemaVenta;
    String pelicula = "Una rubia muy legal";

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

    @AfterAll
    static void mensajeFinal() {
        System.out.println("TEST FINALIZADO");
    }

    //Test anyadirSala
    @Test
    @DisplayName("Comprobar sala añadida TRUE")
    void anyadirSalaCorrecto() {
        assertTrue(sistemaVenta.anyadirSala(1, "La novia cadaver"));
    }

    @Test
    @DisplayName("Comprobar sala repetida")
    void anyadirSalaNumeroIgual() {
        sistemaVenta.anyadirSala(1, "Alicia a través del espejo");
        assertFalse(sistemaVenta.anyadirSala(1, "Bonnie and Clyde"));
    }

    @Test
    @DisplayName("Comprobar numero de salas máximo alcanzado")
    void anyadirSalaMaxNumAlcanzado() {
        for (int i = 1; i <= SistemaVentaEntradas.MAX_SALAS; i++) {
            assertTrue(sistemaVenta.anyadirSala(i, "El padre de la novia" + i));
        }
        assertFalse(sistemaVenta.anyadirSala(SistemaVentaEntradas.MAX_SALAS + 1, "La cumbre escarlata"));
    }

    //TEST comprarEntrada
    @ParameterizedTest
    @DisplayName("Comprobar error si al comprar la entrada la sala no existe")
    @CsvSource({"30,5",
            "4,10"})
    void comprarEntradaErrorSala(int numero1, int numero2){
        sistemaVenta.anyadirSala(1, pelicula);
        assertFalse(sistemaVenta.comprarEntrada(numero1,numero2));
    }

    @ParameterizedTest
    @DisplayName("Comprobar si las butacas son erróneas en varias opciones")
    @CsvSource({"1,-1",
            "1,35"})
    void comprarEntradaErrorButaca(int numero1, int numero2) {
        sistemaVenta.anyadirSala(1, pelicula);
        assertFalse(sistemaVenta.comprarEntrada(numero1, numero2));

    }

    @Test
    @DisplayName("Comprobar compra entrada")
    void comprarEntradasSinErrores() {
        sistemaVenta.anyadirSala(1, pelicula);
        //Comprobar que se crea la entrada
        assertTrue(sistemaVenta.comprarEntrada(1, 5));

    }

    @Test
    @DisplayName("Comprobar que la entrada se añadido a la lista correctamente")
    void comprarEntradasAddListaEntradas() {
        Entrada miEntrada = new Entrada(1, 5, 10.0);
        sistemaVenta.anyadirSala(1, pelicula);
        sistemaVenta.comprarEntrada(1, 5);
        for (Entrada entrada : sistemaVenta.getEntradas()) {
            assertEquals(entrada.getNumButaca(), miEntrada.getNumButaca());
            assertEquals(entrada.getNumSala(), miEntrada.getNumSala());
            assertEquals(entrada.getPrecio(), miEntrada.getPrecio());
        }

    }

    //TEST TotalRecaudacion
    @Test
    @DisplayName("Comprobar total recaudación")
    void totalRecaudacion() {
        sistemaVenta = new SistemaVentaEntradas();
        sistemaVenta.anyadirSala(1, "El señor de los anillos");
        sistemaVenta.comprarEntrada(1, 5);
        sistemaVenta.comprarEntrada(1, 15);
        double totalEsperado = 18.0;
        assertEquals(totalEsperado, sistemaVenta.totalRecaudacion(), 0.0);
    }

    //TEST getEntradasVendidasPorSala
    @Test
    @DisplayName("Comprobar cantidad entradas vendidas")
    void comprobarEntradasVendidasTest() {
        sistemaVenta.anyadirSala(1, pelicula);
        sistemaVenta.comprarEntrada(1, 5);
        sistemaVenta.comprarEntrada(1, 12);
        int totalEntradas=2;
        assertEquals(totalEntradas,sistemaVenta.getEntradasVendidasPorSala(1));
    }

    //TEST calcularPrecioEntrada
    @Test
    @DisplayName("Comprobar total recaudación correcto")
    void testCalcularPrecioEntrada() {
        sistemaVenta = new SistemaVentaEntradas();
        assertEquals(10.0, sistemaVenta.calcularPrecioEntrada(5), 0.0);
        assertEquals(8.0, sistemaVenta.calcularPrecioEntrada(15), 0.0);
        assertEquals(5.0, sistemaVenta.calcularPrecioEntrada(25), 0.0);
    }

    //TEST vaciarCine
    @Test
    @DisplayName("Comprobar ArrayList salas vaciado")
    void vaciarCineTest() {
        sistemaVenta.getSalas().clear();
        int totalSalas = sistemaVenta.getSalas().size();
        assertEquals(0, totalSalas);
    }

    @Test
    @DisplayName("Comprobar ArrayList entradas vaciado")
    void vaciarCineTestEntradas() {
        sistemaVenta.getEntradas().clear();
        int totalEntradas = sistemaVenta.getEntradas().size();
        assertEquals(0, totalEntradas);
    }
}



