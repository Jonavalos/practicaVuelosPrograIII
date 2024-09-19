package pos.presentation.vuelos;

import pos.Application;
import pos.logic.Vuelo;
import pos.logic.Service;


public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Vuelo()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    public void search(Vuelo filter) throws  Exception{
        if(Service.instance().read(filter) != null){
            model.setMode(Application.MODE_EDIT);
        }else{
            model.setMode(Application.MODE_CREATE);
        }
        model.setFilter(filter);
        model.setCurrent(Service.instance().read(filter));
        model.setList(Service.instance().search(model.getFilter()));
    }

    public void save(Vuelo e) throws  Exception{
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Vuelo());
        search(model.getFilter());
    }
    public void edit(int row){
        Vuelo e = model.getList().get(row);
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {}
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear() {
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Vuelo());
    }

}
