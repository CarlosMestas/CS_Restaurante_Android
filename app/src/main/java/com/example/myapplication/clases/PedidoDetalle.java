package com.example.myapplication.clases;

public class PedidoDetalle {
    private int codigo;
    private String codigoComida;
    private String cantidad;
    private String subtotal;
    private String codigoPedido;
    private int numeroMesa;
    private String estado;

    public PedidoDetalle(int codigo, String codigoComida, String cantidad, String subtotal, String codigoPedido, int numeroMesa, String estado) {
        this.codigo = codigo;
        this.codigoComida = codigoComida;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.codigoPedido = codigoPedido;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoComida() {
        return codigoComida;
    }

    public void setCodigoComida(String codigoComida) {
        this.codigoComida = codigoComida;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PedidoDetalle{" +
                "codigo='" + codigo + '\'' +
                ", codigoComida='" + codigoComida + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", codigoPedido='" + codigoPedido + '\'' +
                ", numeroMesa=" + numeroMesa +
                ", estado='" + estado + '\'' +
                '}';
    }
}
