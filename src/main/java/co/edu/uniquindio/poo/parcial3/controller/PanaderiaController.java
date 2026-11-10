package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.EstadoPedido;
import co.edu.uniquindio.poo.parcial3.model.Panaderia;
import co.edu.uniquindio.poo.parcial3.model.Pedido;
import co.edu.uniquindio.poo.parcial3.model.TipoPan;

public class PanaderiaController {
    private final Panaderia panaderia;

    Pedido pedido = new Pedido("P01", "Pedro Picapiedras",
            EstadoPedido.PENDIENTE, TipoPan.TRADICIONAL, 23);

    public PanaderiaController(Panaderia panaderia) {
        this.panaderia = panaderia;
    }
    public String registrarPedido(){
        return panaderia.agregarPedido(pedido);
    }
    public void asignarPedido(Pedido pedido){
        panaderia.agregarPedido(pedido);
    }
    public void cancelarPedido(Pedido pedido){
        panaderia.cancelarPedido(pedido.getCodigoPedido());
    }

}
