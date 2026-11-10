package co.edu.uniquindio.poo.parcial3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PanaderiaTest {

    Panaderia panaderia;

    @BeforeEach
    void setUp() {
        panaderia = new Panaderia("El Trigal", new LinkedList<>(), new LinkedList<>());
    }

    //Prueba 1: Registrar P01 por 25 panes tradicionales
    @Test
    public void registrarPedidoQuedaPendiente() {

        Empleado ana = new Empleado("Ana", "1001", "E01", 100, TipoEmpleado.PANADERO_ARTESANAL);
        panaderia.getListaEmpleados().add(ana);

        Pedido pedido = new Pedido("P01", "Cafe Central", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        panaderia.agregarPedido(pedido);

        //El pedido queda pendiente, conserva la cantidad y los minutos no cambian
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstadoPedido());
        assertEquals(25, pedido.getCantidadUnidades());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    //Prueba 2: Registrar P01, cancelarlo e intentar registrar otro con el mismo codigo
    @Test
    public void registrarCodigoRepetidoDePedidoCancelado() {


        Pedido pedidoOriginal = new Pedido("P01", "Cafe Central", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        panaderia.agregarPedido(pedidoOriginal);
        panaderia.cancelarPedido("P01");

        Pedido pedidoRepetido = new Pedido("P01", "Restaurante Sol", EstadoPedido.PENDIENTE, TipoPan.INTEGRAL, 10);

        boolean lanzoExcepcion = false;
        try {
            panaderia.agregarPedido(pedidoRepetido);
        } catch (IllegalArgumentException e) {
            lanzoExcepcion = true;
        }

        //Se lanza la excepcion y solo queda el pedido original cancelado
        assertTrue(lanzoExcepcion);
        assertEquals(1, panaderia.getListaPedidos().size());
        assertEquals(EstadoPedido.CANCELADO, pedidoOriginal.getEstadoPedido());
    }

    //Prueba 3: Intentar registrar un pedido de cero panes con el historial vacio
    @Test
    public void registrarPedidoDeCeroPanes() {
        
        Pedido pedido = new Pedido("P01", "Cafe Central", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 0);

        boolean lanzoExcepcion = false;
        try {
            panaderia.agregarPedido(pedido);
        } catch (IllegalArgumentException e) {
            lanzoExcepcion = true;
        }

        //Se lanza la excepcion y el historial sigue vacio
        assertTrue(lanzoExcepcion);
        assertEquals(0, panaderia.getListaPedidos().size());
    }
    //Prueba 4: Asignar un pedido de 25 panes tradicionales entre Ana y Carlos
    @Test
    public void asignarAlPanaderoQueTardaMenos() {

        Empleado ana = new Empleado("Ana", "1001", "E01", 100, TipoEmpleado.PANADERO_ARTESANAL);
        Empleado carlos = new Empleado("Carlos", "1002", "E02", 45, TipoEmpleado.PANADERO_INDUSTRIAL);
        panaderia.getListaEmpleados().add(ana);
        panaderia.getListaEmpleados().add(carlos);

        Pedido pedido = new Pedido("P01", "Cafe Central", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        panaderia.agregarPedido(pedido);

        //Ana necesitaria 90 minutos y Carlos 45
        assertEquals(90, ana.minutosGastados(pedido));
        assertEquals(45, carlos.minutosGastados(pedido));

        panaderia.asignarPedido("P01");

        //Se asigna a Carlos, se reservan 45 minutos y queda en 0(aanaconservasus100)
        assertEquals(EstadoPedido.ASIGNADO, pedido.getEstadoPedido());
        assertEquals("E02", pedido.getCodigoEmpleado());
        assertEquals(45, pedido.getMinutosReservados());
        assertEquals(0, carlos.getMinutosDisponibles());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    @Test
    void registrarEmpleadoYAsignarPedido() {
        Empleado ana = new Empleado("Ana", "1001", "E01", 80, TipoEmpleado.PANADERO_ARTESANAL);
        Empleado diana = new Empleado("Diana", "1002", "E02", 80, TipoEmpleado.PANADERO_ARTESANAL);
        Empleado carlos = new Empleado("Carlos", "1003", "E03", 54, TipoEmpleado.PANADERO_INDUSTRIAL);

        panaderia.getListaEmpleados().add(ana);
        panaderia.getListaEmpleados().add(diana);
        panaderia.getListaEmpleados().add(carlos);


        Pedido pedido2= new Pedido("P02", "Cafe Inferior", EstadoPedido.PENDIENTE, TipoPan.INTEGRAL, 13);

        panaderia.getListaPedidos().add(pedido2);

        panaderia.asignarPedido("P02");

        assertEquals(EstadoPedido.ASIGNADO, pedido2.getEstadoPedido());
        assertEquals(0, ana.getMinutosDisponibles());
        assertEquals(80, diana.getMinutosDisponibles());
        assertEquals(54, carlos.getMinutosDisponibles());
    }

    @Test
    void dejarPendienteUnPedido() {
        Empleado carlos= new Empleado ("Carlos", "1001", "E01", 44, TipoEmpleado.PANADERO_INDUSTRIAL);
        Empleado lucia = new Empleado("Lucia", "1002", "E02", 120, TipoEmpleado.CAJERO);

        panaderia.getListaEmpleados().add(carlos);
        panaderia.getListaEmpleados().add(lucia);

        Pedido pedido3= new Pedido("P03", "Cafe Lateral", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        panaderia.getListaPedidos().add(pedido3);

        boolean lanzoExcepcion = false;
        try {
            panaderia.agregarPedido(pedido3);
        } catch (IllegalArgumentException e) {
            lanzoExcepcion = true;
        }

        assertTrue(lanzoExcepcion);
        assertEquals(EstadoPedido.PENDIENTE, pedido3.getEstadoPedido());
        assertEquals(44, carlos.getMinutosDisponibles());
        assertEquals(120, lucia.getMinutosDisponibles());
    }

    @Test
    void cancelarleACarlos1Pedido() {
        Empleado carlos= new Empleado ("Carlos", "1001", "E01", 120, TipoEmpleado.PANADERO_INDUSTRIAL);
        panaderia.getListaEmpleados().add(carlos);
        Pedido pedido3= new Pedido("P03", "Cafe Superior", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        Pedido pedido4= new Pedido("P04", "Cafe Diagonal", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        panaderia.getListaPedidos().add(pedido3);
        panaderia.getListaPedidos().add(pedido4);

        panaderia.asignarPedido("P03");
        panaderia.asignarPedido("P04");
        panaderia.cancelarPedido("P03");

        assertEquals(EstadoPedido.CANCELADO, pedido3.getEstadoPedido());
        assertEquals(EstadoPedido.ASIGNADO, pedido4.getEstadoPedido());
        assertEquals(75, carlos.getMinutosDisponibles());

    }

    @Test
    void cancelarPedidoPendiente() {
        Pedido pedido5 = new Pedido("P05", "Cafe Vectorial", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        Empleado luis = new Empleado ("Luis", "1005", "E05", 100, TipoEmpleado.PANADERO_INDUSTRIAL);
        panaderia.getListaPedidos().add(pedido5);
        panaderia.getListaEmpleados().add(luis);
        panaderia.cancelarPedido("P05");

        assertEquals(EstadoPedido.CANCELADO, pedido5.getEstadoPedido());
        assertEquals(100, luis.getMinutosDisponibles());
    }

    @Test
    void cancelarLaCancelacion() {
        Pedido pedido6= new Pedido ("P06", "Cafe Vectorial Unitario", EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 25);
        Empleado david = new Empleado("David","1006", "E06", 100, TipoEmpleado.PANADERO_INDUSTRIAL);

        panaderia.getListaEmpleados().add(david);
        panaderia.getListaPedidos().add(pedido6);
        panaderia.asignarPedido("P06");
        panaderia.cancelarPedido("P06");


        assertThrows(IllegalStateException.class,
                () -> panaderia.cancelarPedido("P06"));
        assertEquals(100, david.getMinutosDisponibles());
    }
}