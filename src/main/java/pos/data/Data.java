package pos.data;

import pos.logic.*;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
//
    @XmlElementWrapper(name = "vuelos")
    @XmlElement(name = "vuelo")
    private List<Vuelo> vuelos;

    @XmlElementWrapper(name = "ciudades")
    @XmlElement(name = "ciudad")
    private List<Ciudad> ciudades;

    public Data() {
        vuelos = new ArrayList<>();
        ciudades = new ArrayList<>();
    }

    public List<Vuelo> getVuelos() { return vuelos; }

    public List<Ciudad> getCiudades() { return ciudades; }


}