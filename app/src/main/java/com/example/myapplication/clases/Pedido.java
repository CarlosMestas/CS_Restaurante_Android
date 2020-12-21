package com.example.myapplication.clases;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private Cliente cliente;
    private int numeroMesa;
    private double total;
    private String codigoEstado;
    private String codigoAdmin;
    private String hora;
    private String fecha;
    private List<PedidoDetalle> pedidos;

    public Pedido(int numeroMesa, String codigoEstado, String hora, String fecha) {
        this.codigo = "";
        this.cliente = null;
        this.numeroMesa = numeroMesa;
        this.total = 0;
        this.codigoEstado = codigoEstado;
        this.codigoAdmin = "";
        this.hora = hora;
        this.fecha = fecha;
        this.pedidos = new ArrayList<PedidoDetalle>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getCodigoAdmin() {
        return codigoAdmin;
    }

    public void setCodigoAdmin(String codigoAdmin) {
        this.codigoAdmin = codigoAdmin;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<PedidoDetalle> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDetalle> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {

        return "Pedido{" +
                "codigo='" + codigo + '\'' +
                ", cliente='" + cliente + '\'' +
                ", numeroMesa=" + numeroMesa +
                ", total=" + total +
                ", codigoEstado='" + codigoEstado + '\'' +
                ", codigoAdmin='" + codigoAdmin + '\'' +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }

    public ArrayList<String> finalExecution(){
        ArrayList<String> query = new ArrayList<>();

        query.add("INSERT INTO `pedido`(`PedCod`, `PedCliCod`, `PedNumMes`, `PedTot`, `PedEstCod`, `PedHor`, `PedFec`) " +
                "VALUES (" + codigo + ", "+ cliente.getCodigo() + "," + numeroMesa +","+ total+","+codigoEstado+",'" + hora + "','"+ fecha +"');");

        for(int i = 0; i < pedidos.size(); i++){
            query.add("INSERT INTO `pedidodetalle`(`PedDetCod`, `PedDetComCod`, `PedDetCan`, `PedDetSub`, `PedDetPedCod`, `PedDetNumMes`,`PedDetEst`) " +
                    "VALUES (" + pedidos.get(i).getCodigo() + "," + pedidos.get(i).getCodigoComida() + ", " + pedidos.get(i).getCantidad() +"," + pedidos.get(i).getSubtotal() +
                    "," + getCodigo() + "," + pedidos.get(i).getNumeroMesa() + ",'" + pedidos.get(i).getEstado()+"');");
        }

        return query;
    }

}
