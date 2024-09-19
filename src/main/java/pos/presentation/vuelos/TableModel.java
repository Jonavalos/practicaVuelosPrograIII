package pos.presentation.vuelos;

import pos.logic.Vuelo;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Vuelo> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Vuelo> rows) {
        super(cols, rows);
    }

    public static final int NUMERO = 0;
    public static final int ORIGEN = 1;
    public static final int SALIDA = 2;
    public static final int DESTINO = 3;
    public static final int LLEGADA = 4;
    public static final int DURACION = 5;


    public int ajusteGMT(Vuelo vuelo) {         //origen salida         destino llegada

        int horaRealSalida = vuelo.getSalida() + vuelo.getOrigen().getGmt();
        int horaRealLlegada = vuelo.getLlegada() + vuelo.getDestino().getGmt();

        return horaRealLlegada - horaRealSalida;
    }

    @Override
    protected Object getPropertyAt(Vuelo e, int col) {
        return switch (cols[col]) {
            case NUMERO -> e.getNumero();
            case ORIGEN -> e.getOrigen().getNombre();
            case SALIDA -> e.getSalida();
            case DESTINO -> e.getDestino().getNombre();
            case LLEGADA -> e.getLlegada();
            case DURACION -> ajusteGMT(e);
            default -> "";
        };
    }


    @Override
    protected void initColNames(){
        colNames = new String[6];
        colNames[ORIGEN]= "Origen";
        colNames[NUMERO]= "Numero";
        colNames[LLEGADA]= "Llegada";
        colNames[SALIDA]= "Salida";
        colNames[DESTINO]= "Destino";
        colNames[DURACION]= "Duracion";

    }

}

