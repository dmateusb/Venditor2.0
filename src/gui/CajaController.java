package gui;

import SQL.ControlBd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Caja;
import logic.Cliente;
import logic.Contrato;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CajaController {
    @FXML
    private TableView<Caja> TablaCaja;

    @FXML
    private TableColumn<Caja, String> fecha;

    @FXML
    private TableColumn<Caja, String> descripcion;
    @FXML
    private TableColumn<Caja, String> usuario;
    @FXML
    private TableColumn<Caja, Float> ingreso;
    @FXML
    private TableColumn<Caja, Float> egreso;
    @FXML
    private TableColumn<Caja, Float> utilidad;
    @FXML
    private TableColumn<Caja, Float> total;
    @FXML
    private TableColumn<Caja, Integer> id;
    private HomeController homeController;
    ArrayList<Caja> arrayCajas= new ArrayList<>();

    public ObservableList<Caja> listaCajas;

    public void llenarTabla(){
        ControlBd control=homeController.getControl();
        Object[][] Cajas=control.consultarCaja();
        for(int i=0;i<Cajas.length;i++){
            if (Cajas[i][0] != null && Cajas[i][1] != null && Cajas[i][2] != null&& Cajas[i][3] != null
                    && Cajas[i][4] != null&& Cajas[i][5] != null&& Cajas[i][6] != null
                    && Cajas[i][7] !=null) {
                Caja caja= new Caja(Integer.parseInt(Cajas[i][0].toString()),Cajas[i][1].toString(),
                        Cajas[i][2].toString(),Float.parseFloat(Cajas[i][3].toString()),
                Float.parseFloat(Cajas[i][4].toString()),Float.parseFloat(Cajas[i][5].toString()),
                Float.parseFloat(Cajas[i][6].toString()),Cajas[i][7].toString());
                arrayCajas.add(caja);
            }
        }

        listaCajas = FXCollections.observableArrayList(arrayCajas);
        id.setCellValueFactory(new PropertyValueFactory<Caja, Integer>("id"));
        fecha.setCellValueFactory(new PropertyValueFactory<Caja, String>("fecha"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Caja, String>("descripcion"));
        ingreso.setCellValueFactory(new PropertyValueFactory<Caja, Float>("ingreso"));
        egreso.setCellValueFactory(new PropertyValueFactory<Caja, Float>("egreso"));
        utilidad.setCellValueFactory(new PropertyValueFactory<Caja, Float>("utilidad"));
        total.setCellValueFactory(new PropertyValueFactory<Caja, Float>("total"));
        usuario.setCellValueFactory(new PropertyValueFactory<Caja, String>("usuario"));
        TablaCaja.setItems(listaCajas);
        listaCajas.removeAll();
        arrayCajas.removeAll(listaCajas);
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
