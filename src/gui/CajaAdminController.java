package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Caja;
import logic.Procedimientos;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CajaAdminController {
    @FXML private TableView<Caja> TablaCajaAdmin;
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
    

    public ObservableList<Caja> listaCajas;

    public void llenarTabla(){
        ControlBd control=homeController.getControlBd();
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
        TablaCajaAdmin.setItems(listaCajas);
        listaCajas.removeAll();
        arrayCajas.removeAll(listaCajas);
        LocalDate now = LocalDate.now();
        if (selectorFecha.getValue()==null){
            selectorFecha.setValue(now);
        }

        System.out.println(selectorFecha.getValue());
    }

    public void llenarTabla(String fechaCaja){
        //Hago que los textFields de los totales de la caja normal no sean visibles en la caja admin
        txtIngresos.setVisible(false);
        txtEgresos.setVisible(false);
        txtUtilidades.setVisible(false);
        txtEfectivo.setVisible(false);
        txtInicioCaja.setVisible(false);
        LocalDate now = LocalDate.parse(fechaCaja);
        if (selectorFecha.getValue()==null){
            selectorFecha.setValue(now);
        }
        long inicioCaja=0;

        ControlBd control=homeController.getControlBd();
        Object[][] Cajas=control.consultarCajaAdminFecha(fechaCaja);
        Object[][] CajasParaId=control.consultarCajaFecha(fechaCaja);
        Object[][] CajasTotal=control.consultarCajaAdmin();

        if(Cajas==null){
            txtEgresos.setText("0");
            txtIngresos.setText("0");
            txtUtilidades.setText("0");
            if(CajasTotal[0][0]==null){
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
            return;
        }
        if(Cajas[0][0]==null){
            txtEgresos.setText("0");
            txtIngresos.setText("0");
            txtUtilidades.setText("0");
            if(CajasTotal[0][0]==null){
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
            int lastId = Integer.parseInt(CajasParaId[0][0].toString())-1;
            if(Integer.valueOf(CajasParaId[0][0].toString())==1){
                lastId=lastId+1;
            }
            Object[][] cajaParaTotal = control.consultarCajaId(String.valueOf(lastId));
            inicioCaja = Long.valueOf(cajaParaTotal[0][6].toString());
            txtInicioCaja.setText(String.valueOf(inicioCaja));
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

        Object[][] cajaTotal=control.consultarCajaFecha(fechaCaja);
        long[] totalesCaja = calcularTotales(Cajas);
        long[] totalesCajaTotal = calcularTotales(cajaTotal);
        long ingresos = totalesCaja[0];
        long egresos = totalesCaja[1];
        long utilidades = totalesCaja[2];
        long ingresosTotales = totalesCajaTotal[0];
        long egresosTotales = totalesCajaTotal[1];
        long utilidadesTotales = totalesCajaTotal[2];
        /*for(int i=0;i<Cajas.length;i++){
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
*/
        txtInicioCaja.setText(Procedimientos.setPuntosDecimales(String.valueOf(inicioCaja)));
        txtEgresos.setText(Procedimientos.setPuntosDecimales(String.valueOf(egresos)));
        txtIngresos.setText(Procedimientos.setPuntosDecimales(String.valueOf(ingresos+inicioCaja)));
        txtUtilidades.setText(Procedimientos.setPuntosDecimales(String.valueOf(utilidades)));
        txtEfectivo.setText(Procedimientos.setPuntosDecimales(
                String.valueOf(ingresosTotales+utilidadesTotales-egresosTotales+inicioCaja)));

        listaCajas = FXCollections.observableArrayList(arrayCajas);
        id.setCellValueFactory(new PropertyValueFactory<Caja, Integer>("id"));
        fecha.setCellValueFactory(new PropertyValueFactory<Caja, String>("fecha"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Caja, String>("descripcion"));
        ingreso.setCellValueFactory(new PropertyValueFactory<Caja, Float>("ingreso"));
        egreso.setCellValueFactory(new PropertyValueFactory<Caja, Float>("egreso"));
        utilidad.setCellValueFactory(new PropertyValueFactory<Caja, Float>("utilidad"));
        TablaCajaAdmin.setItems(listaCajas);
        listaCajas.removeAll();
        arrayCajas.removeAll(listaCajas);
    }

    private long[] calcularTotales(Object[][] Cajas){
        long ingresos=0;
        long egresos=0;
        long utilidades=0;
        long[] resultados = new long[3];
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
        resultados[0]=ingresos;
        resultados[1]=egresos;
        resultados[2]=utilidades;
        return resultados;
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
homeController.getUsuario().getPassword();
        ingresarCapital.setCajaAdminController(this);
        ingresarCapital.setUsuario(homeController.getUsuario().getUsername());
        ingresarCapital.setPassword(homeController.getUsuario().getPassword());

    }
    @FXML public void CerrarCaja()throws IOException {
        String confirmacion = homeController.mostrarConfirmacion("Confirmación", "La caja se cerrará y" +
                " no se podrán hacer más transacciones. ¿Estás seguro que quieres cerrar la caja?");
        if (confirmacion.equals("OK")) {
            SQL_Sentencias sen = new SQL_Sentencias(homeController.getUsuario().getUsername(),    homeController.getUsuario().getPassword());
            if(sen.InsertarEstadoCaja("Cerrada",homeController.getUsuario().getUsername())){
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
        SQL_Sentencias sen = new SQL_Sentencias(homeController.getUsuario().getUsername(),homeController.getUsuario().getPassword());
        if(sen.InsertarEstadoCaja("Abierta",homeController.getUsuario().getUsername())){

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
        agregarGasto.setCajaAdminController(this);
        agregarGasto.setUsuario(homeController.getUsuario().getUsername());
        agregarGasto.setPassword(homeController.getUsuario().getPassword());
    }


    public DatePicker getSelectorFecha() {
        return selectorFecha;
    }

    public void setSelectorFecha(DatePicker selectorFecha) {
        this.selectorFecha = selectorFecha;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
