package pos.presentation.vuelos;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;

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

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    
//    public Vuelo take() throws Exception{
//        Vuelo e = new Vuelo();
//        e.setDestino(descripcion.getText());
//        e.setCodigo(codigo.getText());
//        if(Double.parseDouble(precio.getText()) <= 0){
//            throw new Exception("Se ha digitado una cantidad invalida para el precio");
//        }
//        e.setPrecio(Double.parseDouble(precio.getText()));
//        e.setCategoria(Objects.requireNonNull(categorias.getSelectedItem()).toString());
//        e.setUnidad(unidad.getText());
//        if(Integer.parseInt(existencias.getText()) <= 0){
//            throw new Exception("Se ha digitado una cantidad invalida para existencia");
//        }
//        e.setExistencia(Integer.parseInt(existencias.getText()));
//        return e;
//    }

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
