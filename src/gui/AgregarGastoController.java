package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import logic.Caja;
import logic.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AgregarGastoController implements Initializable{
    @FXML private TextField txtDinero = new TextField();
    @FXML private ComboBox<String> comboGastos;
    @FXML private ComboBox<String> comboGastos2;
    @FXML private TextField txtDescripcionOtro;
    private HomeController homeController;
    private CajaController cajaController;
    private CajaAdminController cajaAdminController;
    private String usuario;
    private String password;
    private ObservableList<String> gastos = FXCollections.observableArrayList(
            "Nómina", "Parafiscales", "Servicios", "Otro"
    );
    private ObservableList<String> gastosNomina = FXCollections.observableArrayList(
            "Cesantías", "Comisiones", "Honorarios",
            "Intereses de cesantías", "Primas" ,"Sueldos", "Vacaciones", "Otro"
    );
    private ObservableList<String> gastosServicios = FXCollections.observableArrayList(
            "Agua", "Aseo","Energía",  "Gas", "Internet", "Seguridad" ,"Teléfono", "Otro"
    );
    private ObservableList<String> gastosParafiscales = FXCollections.observableArrayList(
            "ARP","CCF" ,"EPS","ICBF",  "Pensiones",  "Sena",  "Otro"
    );
    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public CajaController getCajaController() {
        return cajaController;
    }

    public void setCajaController(CajaController cajaController) {
        this.cajaController = cajaController;
    }

    public CajaAdminController getCajaAdminController() {
        return cajaAdminController;
    }

    public void setCajaAdminController(CajaAdminController cajaAdminController) {
        this.cajaAdminController = cajaAdminController;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void TextFormater(TextField textField){
        final char seperatorChar = '.';
        final Pattern p = Pattern.compile("[0-9" + seperatorChar + "]*");
        textField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.isContentChange()) {
                return c; // no need for modification, if only the selection changes
            }
            String newText = c.getControlNewText();
            if (newText.isEmpty()) {
                return c;
            }
            if (!p.matcher(newText).matches()) {
                return null; // invalid change
            }

            // invert everything before the range
            int suffixCount = c.getControlText().length() - c.getRangeEnd();
            int digits = suffixCount - suffixCount / 4;
            StringBuilder sb = new StringBuilder();

            // insert seperator just before caret, if necessary
            if (digits % 3 == 0 && digits > 0 && suffixCount % 4 != 0) {
                sb.append(seperatorChar);
            }

            // add the rest of the digits in reversed order
            for (int i = c.getRangeStart() + c.getText().length() - 1; i >= 0; i--) {
                char letter = newText.charAt(i);
                if (Character.isDigit(letter)) {
                    sb.append(letter);
                    digits++;
                    if (digits % 3 == 0) {
                        sb.append(seperatorChar);
                    }
                }
            }

            // remove seperator char, if added as last char
            if (digits % 3 == 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.reverse();
            int length = sb.length();

            // replace with modified text
            c.setRange(0, c.getRangeEnd());
            c.setText(sb.toString());
            c.setCaretPosition(length);
            c.setAnchor(length);

            return c;
        }));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFormater(txtDinero);
        comboGastos.setItems(gastos);
        comboGastos.setValue("Nómina");
        comboGastos2.setItems(gastosNomina);
        comboGastos2.setValue("Cesantías");
    }

    @FXML public void onClicComboGastos(){
        String gasto = comboGastos.getValue();
        comboGastos2.setVisible(true);
        txtDescripcionOtro.setVisible(false);
        txtDescripcionOtro.setText("");
        switch(gasto){
            case "Nómina":
                comboGastos2.setItems(gastosNomina);
                comboGastos2.setValue("Césantias");
                break;
            case "Parafiscales":
                comboGastos2.setItems(gastosParafiscales);
                comboGastos2.setValue("ARP");
                break;
            case "Servicios":
                comboGastos2.setItems(gastosServicios);
                comboGastos2.setValue("Agua");
                break;
            case "Otro":
                comboGastos2.setVisible(false);
                txtDescripcionOtro.setVisible(true);
                break;
        }
    }

    @FXML public void agregarGasto(){
        if(txtDinero.getText().length()==0 && comboGastos.getValue().equals("")){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el valor que quieres ingresar a la caja");
            return;
        }
        String dinero = txtDinero.getText().replace(".","");
        if(Math.round(Float.valueOf(dinero))%50!=0){
            homeController.mostrarAlerta("Valor incorrecto","Por favor escribe una cantidad de dinero válida");
            return;
        }
        if(comboGastos.getValue()==""){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el tipo de gasto que quieres ingresar a la caja");
            return;
        }

        float totalCaja=this.homeController.getControlBd().ConsultarTotalCaja();

        Caja caja= new Caja();
        caja.setTipo("Egreso");
        String descripcion = ((txtDescripcionOtro.getText().equals("")) ?
                comboGastos.getValue()+" "+ comboGastos2.getValue() : txtDescripcionOtro.getText());
        caja.setDescripcion(descripcion);
        caja.setEgreso(dinero);
        caja.setTotal(String.valueOf(totalCaja-Float.valueOf(dinero)));
        caja.setUsuario(usuario);
        if(homeController.getControlBd().insertCaja(caja)){
            homeController.mostrarInformacion("Gasto agregado", "Se completó un retiro de caja de "+txtDinero.getText());
        }else{
            return;
        }

//        try {
//            sentencias2.InsertarIngresoCaja(caja);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        LocalDate now = LocalDate.now();
        String fechaHoy = String.valueOf(now);
        try{
            cajaAdminController.llenarTabla(fechaHoy);
        }catch(Exception e){

        }
        try{
            cajaController.llenarTabla(fechaHoy);
        }catch(Exception e){

        }

        Stage stage = (Stage) txtDinero.getScene().getWindow();
        stage.close();
    }




}
