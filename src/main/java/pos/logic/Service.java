package pos.logic;
//
import pos.data.Data;
import pos.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service() {
        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
    }

    public void stop() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // ----------------------Ciudades-------------------------

    public void create(Ciudad e) throws Exception{
        Ciudad result = data.getCiudades().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result == null) data.getCiudades().add(e);
    }

    public List<Ciudad> search(Ciudad e){
        return data.getCiudades();
    }

    public Ciudad read(Ciudad e){
        return data.getCiudades().stream().filter(i->i.getNombre().equals(e.getNombre())).findFirst().orElse(null);
    }
//    public Ciudad read(String id){
//        return data.getCiudades().stream().filter(i-> i.getId().equals(id)).findFirst().orElse(null);
//    }


    // ----------------------Vuelos-------------------------

    public void create(Vuelo e) throws Exception {
        Vuelo result = data.getVuelos().stream().filter(i -> i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result == null) {
            create(e.getDestino());
            create(e.getOrigen());
            data.getVuelos().add(e);
        }
        else throw new Exception("Error create vuelo :(");
    }

    public Vuelo readNumero(Vuelo e) throws Exception{ //vuelos que coincidan con su destino
        Vuelo result = data.getVuelos().stream().filter(i->i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result!=null) {
            result.setOrigen(read(result.getOrigen()));
            result.setDestino(read(result.getDestino()));
            //result.setDestino(new Ciudad(result.getDestino().getId(), result.getDestino().getNombre(), result.getDestino().getGmt()));
            //result.setDestino(new Ciudad(result.getOrigen().getId(), result.getOrigen().getNombre(), result.getOrigen().getGmt()));
            return result;
        }
        else throw new Exception("Error read vuelo :(");
    }

    public Vuelo read(Vuelo e) throws Exception{ //vuelos que coincidan con su destino
        Vuelo result = data.getVuelos().stream().filter(i->i.getDestino().getNombre().equals(e.getDestino().getNombre())).findFirst().orElse(null);
        if (result!=null) {
            result.setOrigen(read(result.getOrigen()));
            result.setDestino(read(result.getDestino()));
            //result.setDestino(new Ciudad(result.getDestino().getId(), result.getDestino().getNombre(), result.getDestino().getGmt()));
            //result.setDestino(new Ciudad(result.getOrigen().getId(), result.getOrigen().getNombre(), result.getOrigen().getGmt()));
            return result;
        }
        else throw new Exception("Error read vuelo :(");
    }

    public void update(Vuelo e) throws Exception{
        Vuelo result;
        try{
            result = this.read(e);
            data.getVuelos().remove(result);
            data.getVuelos().add(e);
        }catch (Exception ex) {
            throw new Exception("Producto no existe");
        }
    }

    public void delete(Vuelo e) throws Exception{
        data.getVuelos().remove(e);
    }

    public List<Vuelo> search(Vuelo e){
        return  data.getVuelos().stream()
                .filter(i -> i.getDestino().getNombre().equals(e.getDestino().getNombre()) || i.getOrigen().getNombre().equals(e.getOrigen().getNombre()))
                .sorted(Comparator.comparing(Vuelo::getNumero).thenComparing(Vuelo::getNumero))
                .collect(Collectors.toList());
    }

}