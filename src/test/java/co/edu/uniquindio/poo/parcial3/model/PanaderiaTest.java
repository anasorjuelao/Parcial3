package co.edu.uniquindio.poo.parcial3.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PanaderiaTest { 

    //Prueba 1: Registrar P01 por 25 panes tradicionales
    @Test
    public void registrarPedidoQuedaPendiente() {
        Panaderia panaderia = new Panaderia("El Trigal", new LinkedList<>(), new LinkedList<>());
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
        Panaderia panaderia = new Panaderia("El Trigal", new LinkedList<>(), new LinkedList<>());

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
        Panaderia panaderia = new Panaderia("El Trigal", new LinkedList<>(), new LinkedList<>());

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
}