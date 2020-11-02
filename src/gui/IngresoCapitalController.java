package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import logic.Caja;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class IngresoCapitalController implements Initializable {
    @FXML private TextField txtDinero = new TextField();
    private HomeController homeController;
    private CajaController cajaController;
    private String usuario;
    private String password;

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
        System.out.println("dsadasdsadas");
    }

    @FXML public void ingresarCapitalCaja(){
        if(txtDinero.getText().length()==0){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el valor que quieres ingresar a la caja");
            return;
        }
        String dinero = txtDinero.getText().replace(".","");
        if(Math.round(Float.valueOf(dinero))%50!=0){
            homeController.mostrarAlerta("Valor incorrecto","Por favor escribe una cantidad de dinero válida");
            return;
        }

        ControlBd controlBd = new ControlBd(usuario,password);
        float totalCaja=controlBd.ConsultarTotalCaja();

        Caja caja= new Caja();
        caja.setDescripcion("Ingreso Capital");
        caja.setIngreso(dinero);
        caja.setTotal(String.valueOf(totalCaja+Float.valueOf(dinero)));
        caja.setUsuario(usuario);

        SQL_Sentencias sentencias2= new SQL_Sentencias("root","");
        try {
            sentencias2.InsertarIngresoCaja(caja);
            homeController.mostrarInformacion("Capital ingresado", "Se completó ingreso de "+txtDinero.getText()+" a la caja");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LocalDate now = LocalDate.now();
        String fechaHoy = String.valueOf(now);
        cajaController.llenarTabla(fechaHoy);
        Stage stage = (Stage) txtDinero.getScene().getWindow();
        stage.close();
    }

    @FXML public void cerrar(){
        Stage stage = (Stage) txtDinero.getScene().getWindow();
        stage.close();
    }










}
