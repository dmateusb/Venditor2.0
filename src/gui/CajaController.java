package gui;

import SQL.ControlBd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Caja;
import logic.Cliente;
import logic.Contrato;
import logic.Procedimientos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CajaController {
    @FXML private TableView<Caja> TablaCaja;
    @FXML private TableColumn<Caja, String> fecha;
    @FXML private TableColumn<Caja, String> descripcion;
    @FXML private TableColumn<Caja, Float> ingreso;
    @FXML private TableColumn<Caja, Float> egreso;
    @FXML private TableColumn<Caja, Float> utilidad;
    @FXML private TableColumn<Caja, Integer> id;
    @FXML private DatePicker selectorFecha;
    private HomeController homeController;
    ArrayList<Caja> arrayCajas= new ArrayList<>();

    String usuarioBD ="root";
    String passwordBD ="";

    public ObservableList<Caja> listaCajas;

    public void llenarTabla(){
        ControlBd control=homeController.getControl();
        Object[][] Cajas=control.consultarCaja();
        for(int i=0;i<Cajas.length;i++){
            if (Cajas[i][0] != null && Cajas[i][1] != null && Cajas[i][2] != null&& Cajas[i][3] != null
                    && Cajas[i][4] != null&& Cajas[i][5] != null&& Cajas[i][6] != null
                    && Cajas[i][7] !=null) {
                String ingreso= Procedimientos.setPuntosDecimales(Cajas[i][3].toString());
                String egreso=Procedimientos.setPuntosDecimales(Cajas[i][4].toString());
                String utilidad=Procedimientos.setPuntosDecimales(Cajas[i][5].toString());
                String total=Procedimientos.setPuntosDecimales(Cajas[i][6].toString());
                Caja caja= new Caja(Integer.parseInt(Cajas[i][0].toString()),Cajas[i][1].toString(),
                        Cajas[i][2].toString(),ingreso,egreso,utilidad,
                        total,Cajas[i][7].toString());
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
        TablaCaja.setItems(listaCajas);
        listaCajas.removeAll();
        arrayCajas.removeAll(listaCajas);
    }

    public void llenarTabla(String fechaCaja){


        ControlBd control=homeController.getControl();
        Object[][] Cajas=control.consultarCaja();
        for(int i=0;i<Cajas.length;i++){
            if (Cajas[i][0] != null && Cajas[i][1] != null && Cajas[i][2] != null&& Cajas[i][3] != null
                    && Cajas[i][4] != null&& Cajas[i][5] != null&& Cajas[i][6] != null
                    && Cajas[i][7] !=null) {
                String ingreso= Procedimientos.setPuntosDecimales(Cajas[i][3].toString());
                String egreso=Procedimientos.setPuntosDecimales(Cajas[i][4].toString());
                String utilidad=Procedimientos.setPuntosDecimales(Cajas[i][5].toString());
                String total=Procedimientos.setPuntosDecimales(Cajas[i][6].toString());
                Caja caja= new Caja(Integer.parseInt(Cajas[i][0].toString()),Cajas[i][1].toString(),
                        Cajas[i][2].toString(),ingreso,egreso,utilidad,
                        total,Cajas[i][7].toString());
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
        TablaCaja.setItems(listaCajas);
        listaCajas.removeAll();
        arrayCajas.removeAll(listaCajas);
    }



    @FXML public void IngresoCapital() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/IngresoCapital.fxml"));
        Parent root = loader.load();
        Stage stage= new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/im/favicon.png"));
        stage.setTitle("Ingresar Capital");
        stage.resizableProperty().setValue(Boolean.TRUE);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        IngresoCapitalController ingresarCapital  = loader.getController();
        ingresarCapital.setHomeController(homeController);
        ingresarCapital.setCajaController(this);
        ingresarCapital.setUsuario(usuarioBD);
        ingresarCapital.setPassword(passwordBD);
    }

    @FXML public void AgregarGasto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AgregarGasto.fxml"));
        Parent root = loader.load();
        Stage stage= new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/im/favicon.png"));
        stage.setTitle("Ingresar Capital");
        stage.resizableProperty().setValue(Boolean.TRUE);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        AgregarGastoController agregarGasto  = loader.getController();
        agregarGasto.setHomeController(homeController);
        agregarGasto.setCajaController(this);
        agregarGasto.setUsuario(usuarioBD);
        agregarGasto.setPassword(passwordBD);
    }





    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
