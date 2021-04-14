package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Caja;
import logic.Descuentos;
import logic.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static gui.HomeController.mostrarConfirmacion;

public class FinalizarRetractoController implements Initializable {
    private String valorCobro;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCedula;
    @FXML private TextField txtFechaInicio;
    @FXML private TextField txtTiempo;
    @FXML private TextField txtValorInicial;
    @FXML private TextField txtPorcentaje;
    @FXML private TextField txtValorCobrar;
    @FXML private TextField txtValorCobrado;
    @FXML private Text txtContrato;
    private SQL_Sentencias sen;
    private ControlBd controlBd;
    private String numeroContrato;
    private double utilidad;
    private float cobro;
    long meses;
    long dias;
    private boolean renovacion = false;




    public void setNumeroContrato(String contrato){
        this.numeroContrato = contrato;
        txtContrato.setText(contrato);
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

    @FXML
    public void closeButtonAction() {
        Stage stage1 = (Stage) txtValorCobrado.getScene().getWindow();
        stage1.close();
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


    public String cobrar() {
        calcularTiempo();
        Object[][] informacionContrato = controlBd.GetContrato(numeroContrato);
        if(meses==0){meses=1;}
        if(dias>5){meses = meses+1;}
        int valor = Integer.parseInt(informacionContrato[0][8].toString());
        double porcentaje = Double.parseDouble(informacionContrato[0][9].toString());
        double cobroMes = valor*porcentaje/100;
        this.utilidad = cobroMes*meses;
        double cobroTotal;
        if(renovacion){
            cobroTotal = utilidad;
        }else{
            cobroTotal = valor+utilidad;
        }
        this.cobro = (float)cobroTotal;
        if(cobro%50!=0){
            float residuo=cobro%50;
            if(residuo<25){
                cobro-=residuo;
            }else {
                cobro+=(50-residuo);
            }
        }
        valorCobro=String.format("%.0f",cobro);
        txtValorCobrar.setText(valorCobro);
        return valorCobro;
    }

    @FXML public void retractar() throws SQLException {
        SQL_Sentencias sentencias= new SQL_Sentencias("root","");
        String valorCobrado=txtValorCobrado.getText().replace(".","");
        String valorCobrar=txtValorCobrar.getText().replace(".","");
        if(txtValorCobrado.getText().length()==0){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el valor cobrado");
            return;
        }
        if(Double.parseDouble(valorCobrado)-Double.parseDouble(txtValorInicial.getText().replace(".",""))<0&&!renovacion){
            homeController.mostrarAlerta(" Información erronea","No se puede cobrar un valor menor al costo inicial");
            return;
        }
        if(Double.parseDouble(valorCobrado)-Double.parseDouble(valorCobrar)>0){
            homeController.mostrarAlerta(" Información erronea","No se puede cobrar un valor mayor al cobro esperado");
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        String confirmacion = mostrarConfirmacion("Confirmación", "El contrato se retractará con la fecha de hoy ¿Estás seguro de retractar el contrato?");

        if (confirmacion.equals("OK")) {
            if(Double.parseDouble(valorCobrado)-Double .parseDouble(valorCobrar)<0){
                Descuentos descuento= new Descuentos(numeroContrato,String.valueOf(valorCobro),valorCobrado,textInput());
                try {
                    sentencias.insertarDescuento(descuento);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            String fechaHoy = now.toString();
            controlBd.updateEstado_Retractado(numeroContrato, fechaHoy);
            controlBd.updateSobreprecio(numeroContrato,String.valueOf(valorCobro),txtValorCobrado.getText());
            float totalCaja=controlBd.ConsultarTotalCaja();
            String idArticulo=controlBd.consultarIdArticulo(numeroContrato);
            String subCategoria = controlBd.consultarSubcategoria(idArticulo);
            Caja caja= new Caja();
            if(renovacion){
                caja.setDescripcion("Renovación " + numeroContrato);
            }else{
                caja.setDescripcion("Retracto " + numeroContrato);
            }
            float ingreso=Float.parseFloat(txtValorCobrado.getText().replace(".",""));
            float utilidad=Float.parseFloat(valorCobrado)-Float.parseFloat(txtValorInicial.getText().replace(".",""));
            ingreso-=utilidad;
            if(renovacion){
                caja.setIngreso(String.valueOf(0));
                utilidad=Float.parseFloat(valorCobrado);
            }else{
                caja.setIngreso(String.valueOf(ingreso));
            }
            caja.setUtilidad(String.valueOf(utilidad));
            caja.setTotal(String.valueOf(totalCaja+ingreso+utilidad));
            Usuario usuario = homeController.getUsuario();
            SQL_Sentencias sentencias2= new SQL_Sentencias(usuario.getUsername(),usuario.getPassword()  );
            try {
                sentencias2.InsertarRetractoCaja(caja);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            homeController.mostrarInformacion("Contrato retractado", "El contrato se retractó de forma correcta");
            homeController.getTxtEstado_DetalleContrato().setText("Retractado");
            homeController.mostrarTablaInicial();//
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();
            if(renovacion){
                preguntarMesesRenovacion();
                homeController.renovar();
                renovacion=false;
            }
        }
    }
    public int  preguntarMesesRenovacion() {
        homeController.setMeses(-1);
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.initStyle(StageStyle.UTILITY);
        popupwindow.setTitle("Tiempo renovacion");
        Button button1= new Button("Confirmar");
        Label label1= new Label("Meses a renovar");

        label1.setWrapText(true);
        Spinner<Integer> spinner= new Spinner<>(1,homeController.mesesPlazo(homeController.getTxtEstado_DetalleContrato()),3);
        button1.setOnAction(e -> {
            homeController.setMeses(spinner.getValue());
            popupwindow.close();
        });
        VBox layout= new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label1,spinner, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        return homeController.getMeses();
    }


    public String textInput(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Favor completar");
        dialog.setHeaderText("Motivo del descuento");
// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        String motivo = "";
        if (result.isPresent()){
            motivo=result.get();
        }
        return motivo;
    }
    @FXML public void cancelar(){
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    public ControlBd getControlBd() {
        return controlBd;
    }

    public void setControlBd(ControlBd controlBd) {
        this.controlBd = controlBd;
    }

    public SQL_Sentencias getSen() {
        return sen;
    }

    public void setSen(SQL_Sentencias sen) {
        this.sen = sen;
    }

    public boolean isRenovacion() {
        return renovacion;
    }

    public void setRenovacion(boolean renovacion) {
        this.renovacion = renovacion;
    }
}

