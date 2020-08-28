package gui;

import SQL.ControlBd;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static gui.HomeController.mostrarConfirmacion;

public class FinalizarRetractoController implements Initializable {
    @FXML private TextField txtNombre;
    @FXML private TextField txtCedula;
    @FXML private TextField txtFechaInicio;
    @FXML private TextField txtTiempo;
    @FXML private TextField txtValorInicial;
    @FXML private TextField txtPorcentaje;
    @FXML private TextField txtValorCobrar;
    @FXML private TextField txtValorCobrado;
    @FXML private Text txtContrato;
    private String numeroContrato;
    private String user;
    private String password;
    private double utilidad;
    private int cobro;
    long meses;
    long dias;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumeroContrato(String contrato){
        this.numeroContrato = contrato;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtCedula() {
        return txtCedula;
    }

    public TextField getTxtFechaInicio() {
        return txtFechaInicio;
    }

    public TextField getTxtTiempo() {
        return txtTiempo;
    }

    public TextField getTxtValorInicial() {
        return txtValorInicial;
    }

    public TextField getTxtPorcentaje() {
        return txtPorcentaje;
    }

    public TextField getTxtValorCobrado() {
        return txtValorCobrado;
    }

    public Text getTxtContrato() {
        return txtContrato;
    }

    private HomeController homeController;

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
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

    public void calcularTiempo(){
        ControlBd controlBd = new ControlBd(user, password);
        Object[][] informacionContrato = controlBd.GetContrato(numeroContrato);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
        String fecha = informacionContrato[0][5].toString().substring(0,19).replace(' ','T');
        LocalDateTime fechaInicioContrato = LocalDateTime.parse(fecha,dtf);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fechaInicioContrato);
        //Calcula la cantidad de días entre la fecha de vencimiento del contrato y el día actual
        this.meses = tempDateTime.until(now, ChronoUnit.MONTHS);
        this.dias = fechaInicioContrato.plusMonths(meses).until(now,ChronoUnit.DAYS);
        long[] tiempo = {meses,dias};
        if(meses==1){
            if(dias==1){
                txtTiempo.setText("1 mes y 1 día");
            }else{
                txtTiempo.setText("1 mes y "+String.valueOf(dias)+" días");
            }
        }else{
            if(dias==1){
                txtTiempo.setText(String.valueOf(meses)+" meses y 1 día");
            }else{
                txtTiempo.setText(String.valueOf(meses)+" meses y "+String.valueOf(dias)+" días");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFormater(txtValorCobrado);
        TextFormater(txtValorCobrar);


    }


    public void cobrar() {
        ControlBd controlBd = new ControlBd(user, password);
        Object[][] informacionContrato = controlBd.GetContrato(numeroContrato);
        if(meses==0){meses=1;}
        if(dias>5){meses = meses+1;}
        int valor = Integer.parseInt(informacionContrato[0][8].toString());
        double porcentaje = Double.parseDouble(informacionContrato[0][9].toString());
        double cobroMes = valor*porcentaje/100;
        this.utilidad = cobroMes*meses;
        double cobroTotal = valor+utilidad;
        this.cobro = (int)cobroTotal;
        txtValorCobrar.setText(String.valueOf(cobro));

    }

    @FXML public void retractar() {
        if(txtValorCobrado.getText().length()==0){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el valor cobrado");
            return;
        }
        String valorCobrar = txtValorCobrado.getText().replace(".","");
        if(Integer.parseInt(valorCobrar)%10!=0){
            homeController.mostrarAlerta(" Información erronea","Escribiste un valor cobrado que no es válido");
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        String confirmacion = mostrarConfirmacion("Confirmación", "El contrato se retractará con la fecha de hoy ¿Estás seguro de retractar el contrato?");

        if (confirmacion.equals("OK")) {
            ControlBd control = new ControlBd(user, password);
            String fechaHoy = now.toString();
            control.updateEstado_Retractado(numeroContrato, fechaHoy);
            control.updateSobreprecio(numeroContrato,String.valueOf(cobro),txtValorCobrado.getText());

            homeController.mostrarInformacion("Contrato retractado", "El contrato se retractó de forma correcta");
            homeController.getTxtEstado_DetalleContrato().setText("Retractado");
            homeController.mostrarTablaInicial();//
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
        }
    }

    @FXML public void cancelar(){
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}

