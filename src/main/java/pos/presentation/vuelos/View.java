package pos.presentation.vuelos;

import pos.Application;
import pos.logic.Ciudad;
import pos.logic.Vuelo;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;

import static pos.presentation.vuelos.Model.CIUDADES;
import static pos.presentation.vuelos.Model.LIST;

public class View implements PropertyChangeListener {
    private JPanel panelVuelo;
    private JTextField numero;
    private JComboBox origenCB;
    private JComboBox salidaCB;
    private JComboBox destinoCB;
    private JComboBox llegadaCB;
    private JTextField ciudad;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton buscarButton;
    private JLabel origenLbl;
    private JLabel destinoLbl;
    private JLabel numeroLbl;
    private JLabel ciudadLbl;
    private JPanel panelBusqueda;
    private JPanel panelListado;
    private JPanel panel1;
    private JTable listado;
    private JLabel salidaLbl;
    private JLabel llegadaLbl;

    public View() {
        guardarButton.addActionListener(new ActionListener() { //actualizar lista al guaradar
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //if (validate()){}
                    controller.save(take());
                    origenCB.setSelectedIndex(0);
                    destinoCB.setSelectedIndex(0);
                    salidaCB.setSelectedIndex(0);
                    llegadaCB.setSelectedIndex(0);
                    numero.setEnabled(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                origenCB.setSelectedIndex(0);
                destinoCB.setSelectedIndex(0);
                salidaCB.setSelectedIndex(0);
                llegadaCB.setSelectedIndex(0);
                numero.setEnabled(true);
                controller.clear();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vuelo vuelo = new Vuelo();

                    Ciudad origen = new Ciudad();
                    origen.setNombre(ciudad.getText());
                    vuelo.setOrigen(origen);

                    Ciudad destino = new Ciudad();
                    destino.setNombre(ciudad.getText());
                    vuelo.setDestino(destino);

                    controller.search(vuelo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        listado.addMouseListener(new MouseAdapter() { //actualizar salida y llegada con edit selected
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = listado.getSelectedRow();
                controller.edit(row);
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENT:
                numero.setText("" + model.getCurrent().getNumero());
                origenCB.setSelectedItem(model.getCurrent().getOrigen());
                destinoCB.setSelectedItem(model.getCurrent().getDestino());
                salidaCB.setSelectedIndex(model.getCurrent().getSalida()-1);
                llegadaCB.setSelectedIndex(model.getCurrent().getLlegada()-1);
                if(model.getMode()== Application.MODE_EDIT){
                    numero.setEnabled(false);
                } break;

            case Model.FILTER:
                numero.setText("" + model.getFilter().getNumero());
                numero.setEnabled(true);
                break;

            case Model.CIUDADES:
                origenCB.setModel(model.getCiudadesOrigen());
                destinoCB.setModel(model.getCiudadesDestino());
                break;

            case LIST:
                int[] cols = {TableModel.NUMERO,TableModel.ORIGEN, TableModel.SALIDA, TableModel.DESTINO, TableModel.LLEGADA, TableModel.DURACION};
                listado.setModel(new TableModel(cols, model.getList()));
                listado.setRowHeight(30);
                TableColumnModel columnModel = listado.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                break;

        }
    }
    
    public Vuelo take() throws Exception{
        try{
            Vuelo e = new Vuelo();
            e.setNumero(numero.getText());
            e.setOrigen((Ciudad) origenCB.getSelectedItem());
            e.setDestino((Ciudad) destinoCB.getSelectedItem());
            e.setSalida(Integer.parseInt((String) salidaCB.getSelectedItem()));
            e.setLlegada(Integer.parseInt((String) llegadaCB.getSelectedItem()));
            return e;
        }catch (Exception ex){
            throw new Exception("Valores invalidos");
        }
    }
    private boolean validate() {
        boolean valid = true;
        if (numero.getText().isEmpty()) {
            valid = false;
            numeroLbl.setBorder(Application.BORDER_ERROR);
            numeroLbl.setToolTipText("numero requerido");
        } else {
            numeroLbl.setBorder(null);
            numeroLbl.setToolTipText(null);
        }

        if (origenCB.getSelectedItem() == null) {
            valid = false;
            origenLbl.setBorder(Application.BORDER_ERROR);
            origenLbl.setToolTipText("origen requerido");
        } else {
            origenLbl.setBorder(null);
            origenLbl.setToolTipText(null);
        }

        if (destinoCB.getSelectedItem() == null) {
            valid = false;
            destinoLbl.setBorder(Application.BORDER_ERROR);
            destinoLbl.setToolTipText("destino requerido");
        } else {
            destinoLbl.setBorder(null);
            destinoLbl.setToolTipText(null);
        }

        if (salidaCB.getSelectedItem() == null) {
            valid = false;
            salidaLbl.setBorder(Application.BORDER_ERROR);
            salidaLbl.setToolTipText("salida requerida");
        } else {
            salidaLbl.setBorder(null);
            salidaLbl.setToolTipText(null);
        }

        if (llegadaCB.getSelectedItem() == null) {
            valid = false;
            llegadaLbl.setBorder(Application.BORDER_ERROR);
            llegadaLbl.setToolTipText("llegada requerida");
        } else {
            llegadaLbl.setBorder(null);
            llegadaLbl.setToolTipText(null);
        }

        return valid;
    }
    // MVC
    private pos.presentation.vuelos.Model model;
    private pos.presentation.vuelos.Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
