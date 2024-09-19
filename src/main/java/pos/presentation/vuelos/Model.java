package pos.presentation.vuelos;

import pos.Application;
import pos.logic.Vuelo;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {

    Vuelo filter;
    List<Vuelo> list;
    Vuelo current;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
    }

    public Model() {
    }

    public void init(List<Vuelo> list) {
        this.list = list;
        this.current = new Vuelo(); //creacion de constructor default Vuelo
        this.filter = new Vuelo();
        this.mode = Application.MODE_CREATE;
    }

    public List<Vuelo> getList() {
        return list;
    }

    public void setList(List<Vuelo> list) {
        this.list = list;
        firePropertyChange(LIST);
    }

    public Vuelo getCurrent() {
        return current;
    }

    public void setCurrent(Vuelo current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public Vuelo getFilter() {
        return filter;
    }

    public void setFilter(Vuelo filter) {
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    public static final String LIST = "list";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
}
