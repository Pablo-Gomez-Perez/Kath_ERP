package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;
import java.sql.Date;

public class VentaFiltro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoBusqueda;
    private String textoBusqueda;
    private String ordenarPor;
    private Date fechaInicial;
    private Date fechaFinal;

    public VentaFiltro() {
        super();
    }

    public VentaFiltro(String tipoBusqueda, String textoBusqueda, String ordenarPor, Date fechaInicial, Date fechaFinal) {
        this.tipoBusqueda = tipoBusqueda;
        this.textoBusqueda = textoBusqueda;
        this.ordenarPor = ordenarPor;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public String getTipoBusqueda() { return tipoBusqueda; }
    public void setTipoBusqueda(String tipoBusqueda) { this.tipoBusqueda = tipoBusqueda; }
    public String getTextoBusqueda() { return textoBusqueda; }
    public void setTextoBusqueda(String textoBusqueda) { this.textoBusqueda = textoBusqueda; }
    public String getOrdenarPor() { return ordenarPor; }
    public void setOrdenarPor(String ordenarPor) { this.ordenarPor = ordenarPor; }
    public Date getFechaInicial() { return fechaInicial; }
    public void setFechaInicial(Date fechaInicial) { this.fechaInicial = fechaInicial; }
    public Date getFechaFinal() { return fechaFinal; }
    public void setFechaFinal(Date fechaFinal) { this.fechaFinal = fechaFinal; }

    @Override
    public String toString() {
        return "VentaFiltro [tipoBusqueda=" + tipoBusqueda + ", textoBusqueda=" + textoBusqueda + ", ordenarPor="
                + ordenarPor + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + "]";
    }

    public static class VentaFiltroBuilder {

        private final VentaFiltro filtro;

        public VentaFiltroBuilder() {
            this.filtro = new VentaFiltro();
        }

        public VentaFiltroBuilder tipoBusqueda(String value) { filtro.tipoBusqueda = value; return this; }
        public VentaFiltroBuilder textoBusqueda(String value) { filtro.textoBusqueda = value; return this; }
        public VentaFiltroBuilder ordenarPor(String value) { filtro.ordenarPor = value; return this; }
        public VentaFiltroBuilder fechaInicial(Date value) { filtro.fechaInicial = value; return this; }
        public VentaFiltroBuilder fechaFinal(Date value) { filtro.fechaFinal = value; return this; }

        public VentaFiltro build() { return filtro; }
    }
}
