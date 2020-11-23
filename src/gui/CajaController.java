package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gui.HomeController.mostrarConfirmacion;

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
    @FXML private TextField txtIngresos;
    @FXML private TextField txtEgresos;
    @FXML private TextField txtUtilidades;
    @FXML private TextField txtEfectivo;
    @FXML private TextField txtInicioCaja;
    @FXML public Button btnIngresoCapital;
    @FXML public Button btnRetiroCapital;
    @FXML public Button btnCerrarCaja;
    @FXML public Button btnAbrirCaja;
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
        LocalDate now = LocalDate.now();
        if (selectorFecha.getValue()==null){
            selectorFecha.setValue(now);
        }

        System.out.println(selectorFecha.getValue());
    }

    public void llenarTabla(String fechaCaja){
        LocalDate now = LocalDate.parse(fechaCaja);
        if (selectorFecha.getValue()==null){
            selectorFecha.setValue(now);
        }
        long inicioCaja=0;

        ControlBd control=homeController.getControl();
        Object[][] Cajas=control.consultarCajaFecha(fechaCaja);
        Object[][] CajasTotal=control.consultarCaja();


        if(Cajas[0][0] == null && Cajas[0][1] == null){
            txtEgresos.setText("0");
            txtIngresos.setText("0");
            txtUtilidades.setText("0");
            if(CajasTotal[0][0] == null && CajasTotal[0][1] == null){
                txtInicioCaja.setText("0");
                txtEfectivo.setText("0");
            }else{
                Object[][] cajaAntesDeFecha = control.consultarCajaAntesDeFecha(fechaCaja);
                int cota = cajaAntesDeFecha.length-1;
                while(cajaAntesDeFecha[cota][6]==null){
                    cota=cota-1;
                }
                inicioCaja = Long.valueOf(cajaAntesDeFecha[cota][6].toString());
                txtInicioCaja.setText(Procedimientos.setPuntosDecimales(String.valueOf(inicioCaja)));
                txtEfectivo.setText(Procedimientos.setPuntosDecimales(String.valueOf(inicioCaja)));
            }

        }else{
            int lastId = Integer.parseInt(Cajas[0][0].toString())-1;
            Object[][] cajaParaTotal = control.consultarCajaId(String.valueOf(lastId));
            inicioCaja = Long.valueOf(cajaParaTotal[0][6].toString());
            txtInicioCaja.setText(String.valueOf(inicioCaja));
        }

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

        long ingresos=0;
        long egresos=0;
        long utilidades=0;
        for(int i=0;i<Cajas.length;i++){
            if(Cajas[i][3]!=null){
                ingresos=ingresos+Long.valueOf(Cajas[i][3].toString());
            }
            if(Cajas[i][4]!=null){
                egresos=egresos+Long.valueOf(Cajas[i][4].toString());
            }
            if(Cajas[i][4]!=null){
                utilidades=utilidades+Long.valueOf(Cajas[i][5].toString());
            }


        }

        txtInicioCaja.setText(Procedimientos.setPuntosDecimales(String.valueOf(inicioCaja)));
        txtEgresos.setText(Procedimientos.setPuntosDecimales(String.valueOf(egresos)));
        txtIngresos.setText(Procedimientos.setPuntosDecimales(String.valueOf(ingresos+inicioCaja)));
        txtUtilidades.setText(Procedimientos.setPuntosDecimales(String.valueOf(utilidades)));
        txtEfectivo.setText(Procedimientos.setPuntosDecimales(String.valueOf(ingresos+utilidades-egresos+inicioCaja)));

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

    @FXML public void CambioFecha(){
        String fechaNueva= String.valueOf(selectorFecha.getValue());
        System.out.println(fechaNueva+" Fecha nueva");
        llenarTabla(fechaNueva);

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
    @FXML public void CerrarCaja()throws IOException {
        String confirmacion = homeController.mostrarConfirmacion("Confirmación", "La caja se cerrará y" +
                " no se podrán hacer más transacciones. ¿Estás seguro que quieres cerrar la caja?");
        if (confirmacion.equals("OK")) {
            SQL_Sentencias sen = new SQL_Sentencias(usuarioBD,passwordBD);
            if(sen.InsertarEstadoCaja("Cerrada",usuarioBD)){
                btnCerrarCaja.setDisable(true);
                btnIngresoCapital.setDisable(true);
                btnRetiroCapital.setDisable(true);
                btnAbrirCaja.setVisible(true);
                homeController.btnRenovarContrato.setDisable(true);
                homeController.btnRetractarContrato.setDisable(true);
                homeController.mostrarInformacion("Caja cerrada", "La caja se cerró correctamente. Para abrirla de nuevo es necesario la aprobación del administrador.");
            }
            sen=null;
        }

    }

    @FXML public void AbrirCaja(){
        SQL_Sentencias sen = new SQL_Sentencias(usuarioBD,passwordBD);
        if(sen.InsertarEstadoCaja("Abierta",usuarioBD)){

            System.out.println("Se abre la caja");
            btnCerrarCaja.setDisable(false);
            btnIngresoCapital.setDisable(false);
            btnRetiroCapital.setDisable(false);
            btnAbrirCaja.setVisible(false);
            homeController.btnRetractarContrato.setDisable(false);
            homeController.btnRenovarContrato.setDisable(false);
        }
        sen=null;
    }

    @FXML public void AgregarGasto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AgregarGasto.fxml"));
        Parent root = loader.load();
        Stage stage= new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/im/favicon.png"));
        stage.setTitle("Retirar Capital");
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
