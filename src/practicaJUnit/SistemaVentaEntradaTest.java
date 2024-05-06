package practicaJUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/* Se aconseja “limpiar” las listas de entradas y salas antes de cada test (utilizando una anotación vista en
clase), utilizando el método “vaciarCine()”.
Después, en cada método de test aislado, preparar las listas
con la información necesaria para cada prueba.*/
public class SistemaVentaEntradaTest {
//TODO: “limpiar” las listas de entradas y salas antes de cada test
    //@BeforeEach
    //void limpiarCine(){
    //System.out.println("(Limpiando salas y listas de entradas)");


    @Test
    @DisplayName("comprobar error si al comprar entrada, nos da false si la sala no existe")
    void comprarEntradaErrorSala(){

        SistemaVentaEntradas miSistema=new SistemaVentaEntradas();
        miSistema.anyadirSala(1,"Una rubia muy legal");
        //Compruebo que nos da false si la sala que le indicamos no existe
            assertFalse(miSistema.comprarEntrada(30,5));


    }
    //TODO: crear el metodo como un ParameterizedTest
    @Test
    @DisplayName("compruebo error si las butacas son erróneas")
    void comprarEntradaErrorButaca(){
        SistemaVentaEntradas miSistema=new SistemaVentaEntradas();
        miSistema.anyadirSala(1,"Una rubia muy legal");
        //compruebo que me da error si la entrada es 0 o menos
        assertFalse(miSistema.comprarEntrada(1,-1));
        // comrpuebo que da error si la butaca es mayor a 30
        assertFalse(miSistema.comprarEntrada(1,35));
    }
    @Test
    @DisplayName("la entrada se compra y se añade a la lista de entradas")
    void comprarEntradasSinErrores(){
        SistemaVentaEntradas miSistema=new SistemaVentaEntradas();
        miSistema.anyadirSala(1,"Una rubia muy legal");
        //Comprobar que se crea la entrada
        assertTrue(miSistema.comprarEntrada(1,5));

    }
    @Test
    @DisplayName("Comprobar que la entrada se añadido a la lista")
    void comprarEntradasAddListaEntradas(){
        SistemaVentaEntradas miSistema=new SistemaVentaEntradas();
        Entrada miEntrada=new Entrada(1,5,10.0);
        ArrayList<Entrada> miLista=new ArrayList<>();
        miLista.add(miEntrada);
        //Comprobar entrada se añade a lista
        /*miSistema.getEntradas();
        assertArrayEquals(miSistema.getEntradas(),miLista);
        assertInstanceOf(miSistema.getEntradas(),miEntrada);*/
    }
}



