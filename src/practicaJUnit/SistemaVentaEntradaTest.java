package practicaJUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/* Se aconseja “limpiar” las listas de entradas y salas antes de cada test (utilizando una anotación vista en
clase), utilizando el método “vaciarCine()”.
Después, en cada método de test aislado, preparar las listas
con la información necesaria para cada prueba.*/
public class SistemaVentaEntradaTest {
    static SistemaVentaEntradas miSistema;

    @BeforeAll
    static void crearInstanciaSistemaVenta() {
        System.out.println("ANTES DE TODOS LOS TESTS");
        miSistema = new SistemaVentaEntradas();
    }

    //“limpiar” las listas de entradas y salas antes de cada test
    @BeforeEach
    void limpiarCine() {
        miSistema.vaciarCine();
        System.out.println("SISTEMA DE VENTA DE ENTRADAS LIMPIO");
    }
    //esto es más del primer método
    @Test
    @DisplayName("comprobar error si la sala no existe al comprar entrada")
    void comprarEntradaErrorSala() {
        miSistema.anyadirSala(1, "Una rubia muy legal");
        //Compruebo que nos da false si la sala que le indicamos no existe
        assertFalse(miSistema.comprarEntrada(30, 5));
    }

    @ParameterizedTest
    @DisplayName("Comprobar si las butacas son erróneas en varias opciones")
    @CsvSource({"1,-1",
            "1,35"})
    void comprarEntradaErrorButaca(int numero1, int numero2) {
        miSistema.anyadirSala(1, "Una rubia muy legal");
        assertFalse(miSistema.comprarEntrada(numero1, numero2));

    }

    @Test
    @DisplayName("la entrada se compra y se añade a la lista de entradas")
    void comprarEntradasSinErrores() {
        miSistema.anyadirSala(1, "Una rubia muy legal");
        //Comprobar que se crea la entrada
        assertTrue(miSistema.comprarEntrada(1, 5));

    }

    @Test
    @DisplayName("Comprobar que la entrada se añadido a la lista correctamente")
    void comprarEntradasAddListaEntradas() {
        Entrada miEntrada = new Entrada(1, 5, 10.0);
        miSistema.anyadirSala(1, "Una rubia muy legal");
        miSistema.comprarEntrada(1, 5);
        for (Entrada entrada : miSistema.getEntradas()) {
            assertEquals(entrada.getNumButaca(), miEntrada.getNumButaca());
            assertEquals(entrada.getNumSala(), miEntrada.getNumSala());
            assertEquals(entrada.getPrecio(), miEntrada.getPrecio());
        }

    }
}



