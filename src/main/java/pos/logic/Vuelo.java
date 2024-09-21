package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;

//
@XmlAccessorType(XmlAccessType.FIELD)
public class Vuelo {
    @XmlID
    String numero;
    Ciudad origen;
    Ciudad destino;
    Integer salida;
    Integer llegada;

    public Vuelo(){
        this("", new Ciudad(),new Ciudad(),0,0);
    }

    public Vuelo(String numero,Ciudad origen, Ciudad destino, Integer salida, Integer llegada) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.salida = salida;
        this.llegada = llegada;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Integer getSalida() {
        return salida;
    }

    public void setSalida(Integer salida) {
        this.salida = salida;
    }

    public Integer getLlegada() {
        return llegada;
    }

    public void setLlegada(Integer llegada) {
        this.llegada = llegada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return numero == vuelo.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }
}
