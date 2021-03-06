package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import animatefx.animation.FadeIn;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import logic.*;
import org.apache.log4j.component.spi.Component;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;

/**
 * @author david
 */

public class HomeController implements Initializable {

    private ControlBd controlBd;
    private SQL_Sentencias sen;
    private static Usuario usuario;
    private static Stage stage;
    private String cedulaSeleccionada;
    @FXML private ImageView imageViewBusquedaCliente;
    @FXML private ImageView imageViewDetalleContrato;
    @FXML private ImageView imgViewFotoNuevaRetroveta;
    @FXML private LineChart graficaHome;
    @FXML private MenuItem btnnuevocliente;
    @FXML private MenuItem btnbusquedacliente;
    @FXML private AnchorPane pnCrearUsuario;
    @FXML private AnchorPane anchorTablaClientes;
    @FXML private VBox VBoxAnchorTablaClientes;
    @FXML private VBox VBoxanchorDetallesCliente;
    @FXML private AnchorPane anchorDetallesCliente;
    @FXML private AnchorPane anchorDetallesContrato;
    @FXML private AnchorPane anchorTablaContratos;
    @FXML private AnchorPane aPimgClienteNuevaRetroventa;
    @FXML private AnchorPane anchorCaja= new AnchorPane();
    @FXML private AnchorPane anchorCajaAdmin= new AnchorPane();
    @FXML private AnchorPane anchorUsuarios= new AnchorPane();
    @FXML private AnchorPane anchorImpresion=new AnchorPane();
    @FXML private AnchorPane anchorDescuentos= new AnchorPane();
    @FXML private AnchorPane anchorPrincipal;
    @FXML private AnchorPane paneNuevoCliente;
    @FXML private Button btnFotoNuevoCliente;
    @FXML private Button btnCrearRetroventa;
    @FXML private Button btnBorrarDatosCliente;
    @FXML private Button btnHuellaNuevoCliente;
    @FXML private Button btnInsertarDatosNuevoCliente;
    @FXML private Button btnCambiarPorcentaje;
    @FXML private Button btnVerDetalleTablaClientes;
    @FXML private Button btnVerDetallesContratos;
    @FXML private Button btnVerDetalleTablaDetallesClientes;
    @FXML private Button btnVerFoto;
    @FXML private Button btnModificarDetalleCliente;
    @FXML private Button btnGuardarDetalleCliente;
    @FXML private Button btnTomarNuevaHuellaDetalleCliente;
    @FXML private Button btnTomarNuevaFotoDetalleCliente;
    @FXML private Button btnCerrarSesion;
    @FXML private HBox HBoxPrincipal;

    //Ventana Detalle Contrato
    @FXML private TextField txtNumeroContrato_DetalleContrato;
    @FXML private TextField txtFechaInicio_DetalleContrato;
    @FXML private TextField txtFechaFinal_DetalleContrato;
    @FXML private TextField txtEstado_DetalleContrato;
    @FXML private TextField txtRenovaciones_DetalleContrato;
    @FXML private TextField txtCreadoPor_DetalleContrato;
    @FXML private TextField txtValor_DetalleContrato;
    @FXML private TextField txtTiempo_DetalleContrato;
    @FXML private TextField txtPorcentaje_DetalleContrato;
    @FXML private TextField txtPeso_DetalleContrato;
    @FXML private TextArea txtDescripcion_DetalleContrato;
    @FXML private TextField txtCategoria_DetalleContrato;
    @FXML private TextField txtSubcategoria_DetalleContrato;
    @FXML private TextField txtCedula_DetalleContrato;
    @FXML private TextField txtPerfil_DetalleContrato;
    @FXML private TextField txtNombre_DetalleContrato;
    @FXML private TextField txtApellidos_DetalleContrato;
    @FXML private TextField txtCorreo_DetalleContrato;
    @FXML private TextField txtDireccion_DetalleContrato;
    @FXML private TextField txtTelefono1_DetalleContrato;
    @FXML private TextField txtTelefono2_DetalleContrato;
    @FXML private TextField txtNombreBusquedaCliente;
    @FXML private TextField txtApellidoBusquedaCliente;
    @FXML private TextField txtDireccionBusquedaCliente;
    @FXML private TextField txtBarrioBusquedaCliente;
    @FXML private TextField txtTelefono1BusquedaCliente;
    @FXML private TextField txtTelefono2BusquedaCliente;
    @FXML private TextField txtCorreoBusquedaCliente;
    @FXML private TextField txtBusquedaCliente;
    @FXML private TextField txtFechaRegistroBusquedaCliente;
    @FXML private TextField txtPerfilBusquedaCliente;
    @FXML private TextField txtCedulaBusquedaCliente;
    @FXML private TextField txtLugarExpedicion;
    @FXML private TextField txtnombre;
    @FXML private TextField txttelefono1;
    @FXML private TextField txtcorreo;
    @FXML private TextField txtdireccion;
    @FXML private TextField txtbarrio;
    @FXML private TextField txtcedula;
    @FXML private TextField txtapellido;
    @FXML private TextField txttelefono2;
    @FXML private TextField txtcedulaNuevaRetroventa;
    @FXML private TextField txtnombreNuevaRetroventa;
    @FXML private TextField txtapellidoNuevaRetroventa;
    @FXML private TextField txtdireccionNuevaRetroventa;
    @FXML private TextField txtcorreoNuevaRetroventa;
    @FXML private TextField txttelefono1NuevaRetroventa;
    @FXML private TextField txttelefono2NuevaRetroventa;
    @FXML private TextArea txtObservacionesArticulo;
    @FXML private TextArea txtDescripcionArticulo;
    @FXML private TextField txtPesoArticulo;
    @FXML private TextField txtValorArticulo;
    @FXML private TextField txtCedulaContratos;
    @FXML private TextField txtNumeroContrato;
    @FXML private DatePicker dateexpedicion;
    private int pantallaActiva = 1;
    @FXML private ComboBox<String> cboxusuario;
    @FXML private ComboBox<String> cboxperfil;
    @FXML private TableView<Contrato> TablaContratos;
    @FXML private TableView<Cliente> Tabla_BusquedaClientes;
    @FXML private TableView<Contrato> TablaContratos_BusquedaClientes;
    @FXML private TableColumn<Contrato,Integer> cedula;
    @FXML private TableColumn<Contrato,String> nombre;
    @FXML private TableColumn<Contrato,String> apellidos;
    @FXML private TableColumn<Contrato,String> numeroContrato;
    @FXML private TableColumn<Contrato,String> estado;
    @FXML private TableColumn<Contrato,String> descripcion;
    @FXML private TableColumn<Contrato,String> vencimiento;
    @FXML private TableColumn<Contrato,String> columnaContratoBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaArticuloBusquedaClientes ;
    @FXML private TableColumn<Contrato,String> columnaPorcentajeBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaValorBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaRenovacionesBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaEstadoBusquedaClientes;
    @FXML private TableColumn<Cliente, Integer> ColumnaCedulaCliente;
    @FXML private TableColumn<Cliente, String> ColumnaNombreCliente;
    @FXML private Label lblNumeroContrato;

    private boolean flagPaneCaja=false;
    private boolean flagPaneUsuario=false;
    private boolean flagPaneDescuentos=false;


    private int meses;

    public TextField getTxtEstado_DetalleContrato() {
        return txtEstado_DetalleContrato;
    }
    @FXML private TableColumn<Cliente, String> ColumnaApellidosCliente;
    @FXML private TableColumn<Cliente, String> ColumnaDireccionCliente;
    @FXML private TableColumn<Cliente, String> ColumnaTelefono1Cliente;
    @FXML private TableColumn<Cliente, String> ColumnaTelefono2Cliente;
    @FXML private TableColumn<Cliente, String> ColumnaCorreoCliente;
    @FXML private TableColumn<Cliente, String> ColumnaHuellaCliente;
    @FXML private TableColumn<Cliente, String> ColumnaFotoCliente;
    @FXML private Cliente clienteEscogidoTabla;
    @FXML private Contrato contratoEscogidoTabla;
    @FXML private Contrato contratoEscogidoTablaDetalles;

     ;



    //VBoxes - Son los que se van pasando al frente cada vez que el usuario
    //selecciona alguna opción del menú
    @FXML private ComboBox<String> comboCategoria;
    @FXML private ComboBox<String> comboSubcategoria;
    @FXML private ComboBox<String> comboEstados;
    ObservableList<String> categorias = FXCollections.observableArrayList("Oro","Electrodomésticos");
    ObservableList<String> estados = FXCollections.observableArrayList("Todos","Vigentes","Retractados","Vencidos"); //Estados de los contratos
    ObservableList<String> subElectrodomesticos = FXCollections.observableArrayList(
            "Cámaras","Deportes","Herramientas","Hogar","Industria","Instrumentos",
            "Portatil","Reloj","Sonido","Televisores","Videojuegos", "Varios Artículos","Otros");
    ObservableList<String> subOro = FXCollections.observableArrayList("Oro");
    @FXML private Spinner SpinnerPorcentaje;
    @FXML private AnchorPane panenuevocliente;
    @FXML private AnchorPane paneHome;
    @FXML private AnchorPane paneNuevaRetroventa;
    @FXML private AnchorPane paneBusquedaCliente;
    @FXML private AnchorPane paneContratos;
    @FXML private MenuButton mbtntransacciones;
    @FXML private MenuItem btnnuevaretro;
    @FXML private MenuItem btnventa;
    @FXML private MenuItem btnrenovacion;
    @FXML private MenuItem btnretracto;
    @FXML private MenuButton mbtnclientes;
    @FXML private MenuButton mbtnbusqueda;
    @FXML private MenuItem btncontrato;
    @FXML private MenuItem btnclientehistorial;
    @FXML private MenuButton mbtngerencia;
    @FXML private MenuItem btnreportes;
    @FXML private MenuItem btnusuarios;
    @FXML private MenuItem btnbalance;
    @FXML private MenuItem btnaportesoretiro;
    @FXML private MenuItem btnestadistica;
    @FXML private MenuItem btnhistorialcorreciones;
    @FXML private MenuItem btnImpresion;
    @FXML private MenuItem btnDescuentos;
    @FXML private MenuItem btnbackup;
    @FXML private MenuItem btncaja;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btnCambiarCliente;
    @FXML private Button btnBorrarTodoNuevaRetro;
    @FXML public SplitMenuButton btnRenovarContrato;
    @FXML public Button btnRetractarContrato;

    private CajaController cajaController;
    private CajaAdminController cajaAdminController;
    private UsuarioController usuarioController;
    private ImpresionController impresionController;
    private DescuentosController descuentosController;
    private Integer cedulaContrato;
    ArrayList<Contrato> arrayContratos = new ArrayList();
    ArrayList<Cliente> arrayClientes = new ArrayList();

    public ObservableList<Contrato> lista;
    public ObservableList<Cliente> listaClientes;

    @FXML
    private ObservableList<String> listaAccionesUsuarios = FXCollections.observableArrayList(
            "Crear Usuario", "Editar Usuario", "Eliminar Usuario"
    );

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



    //Consulta a la BD los contratos vigentes y si la diferencia entre el día de vencimiento del contrato
    //y la fecha actual es mayor a 0 días, cambia el estado del contrato a "Vencido"
    public void contratosVencidos(){
        //Consulta los contratos vigentes
        Object[][] Contratos = controlBd.ConsultarContratosVigentes();
        for(int i=0;i<Contratos.length;i++){
            if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                String numeroContrato = Contratos[i][0].toString();
                Object[][] informacionContrato = controlBd.GetContrato(numeroContrato);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
                String fecha = informacionContrato[0][6].toString().substring(0,19).replace(' ','T');
                LocalDateTime fechaFinContrato = LocalDateTime.parse(fecha,dtf);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime tempDateTime = LocalDateTime.from(fechaFinContrato);
                //Calcula la cantidad de días entre la fecha de vencimiento del contrato y el día actual
                long days = tempDateTime.until(now,ChronoUnit.DAYS );
                //Si ya pasó al menos un día, el contrato se cambia a "Vencido"
                if(days>0){
                    controlBd.updateEstado_Vencido(numeroContrato);
                }
            }
        }
    }

    //Método para crear páneles con una información
    public static void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //Método para crear páneles con una alerta
    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //Método para mostrar páneles de confirmación
    public static final String OK = "OK";
    public static final String CANCEL = "Cancelar";

    public static String mostrarConfirmacion(String titulo,String mensaje, String... options) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        //To make enter key press the actual focused button, not the first one. Just like pressing "space".
        alert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                event.consume();
                try {
                    Robot r = new Robot();
                    r.keyPress(java.awt.event.KeyEvent.VK_SPACE);
                    r.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (options == null || options.length == 0) {
            options = new String[]{OK, CANCEL};
        }

        List<ButtonType> buttons = new ArrayList<>();
        for (String option : options) {
            buttons.add(new ButtonType(option));
        }

        alert.getButtonTypes().setAll(buttons);

        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent()) {
            return CANCEL;
        } else {
            return result.get().getText();
        }
    }

    public void retractarContrato() throws IOException{
        //Confirma que el contrato no esté retractado
        Object[][] contrato = controlBd.consultarEstado(txtNumeroContrato_DetalleContrato.getText());
        String estadoContrato = contrato[0][0].toString();

        if (estadoContrato.equals("Retractado")){
            mostrarAlerta("Contrato retractado","El contrato que intentas retractar ya está retractado.");
            return;
        }
        abrirDetalleContrato(false, false);
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FinalizarRetracto.fxml"));
        Parent root = loader.load();
        Stage stage= new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/im/favicon.png"));
        stage.setTitle("Finalizar Retracto");
        stage.resizableProperty().setValue(Boolean.TRUE);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        FinalizarRetractoController finalizarController = loader.getController();
        finalizarController.setControlBd(controlBd);
        finalizarController.setSen(sen);
        finalizarController.setNumeroContrato(txtNumeroContrato_DetalleContrato.getText());
        finalizarController.getTxtCedula().setText(txtCedula_DetalleContrato.getText());
        finalizarController.getTxtNombre().setText(txtNombre_DetalleContrato.getText());
        finalizarController.getTxtNombre().setText(txtNombre_DetalleContrato.getText());
        finalizarController.getTxtFechaInicio().setText(txtFechaInicio_DetalleContrato.getText());
        finalizarController.getTxtPorcentaje().setText(txtPorcentaje_DetalleContrato.getText());
        finalizarController.getTxtValorInicial().setText(txtValor_DetalleContrato.getText());
        finalizarController.cobrar();
        finalizarController.setHomeController(this);
*/
        //finalizarController.inicializar();








//        if(diasAdicionales>5){meses = meses+1;}
//        int valor = Integer.parseInt(informacionContrato[0][8].toString());
//        double porcentaje = Double.parseDouble(informacionContrato[0][9].toString());
//        System.out.println(valor);
//        System.out.println(porcentaje);
//        double cobroMes = valor*porcentaje/100;
//        double utilidad = cobroMes*meses;
//        double cobroTotal = valor+utilidad;
//

    }

    public void renovarContrato() throws SQLException, IOException {
        Object[][] contrato = controlBd.consultarEstado(txtNumeroContrato_DetalleContrato.getText());
        String estadoContrato = contrato[0][0].toString();

        if (estadoContrato.equals("Retractado")){
            mostrarAlerta("Contrato retractado","El contrato que intentas renovar ya está retractado. Es necesario crear un nuevo contrato.");
            return;
        }
        
        abrirDetalleContrato(true, false);

    }

    private void abrirDetalleContrato(boolean isRenovacion, boolean isEditar) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FinalizarRetracto.fxml"));
        Parent root = loader.load();
        FinalizarRetractoController controller = loader.getController();
        Stage stage= new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/im/favicon.png"));
        stage.setTitle("Renovar Retracto");
        stage.resizableProperty().setValue(Boolean.TRUE);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setRenovacion(isRenovacion);
        controller.setIsEditar(isEditar);
        controller.setControlBd(controlBd);
        controller.setSen(sen);
        controller.setNumeroContrato(txtNumeroContrato_DetalleContrato.getText());
        controller.getTxtCedula().setText(txtCedula_DetalleContrato.getText());
        controller.getTxtNombre().setText(txtNombre_DetalleContrato.getText());
        controller.getTxtFechaInicio().setText(txtFechaInicio_DetalleContrato.getText());
        controller.getTxtPorcentaje().setText(txtPorcentaje_DetalleContrato.getText());
        controller.getTxtValorInicial().setText(txtValor_DetalleContrato.getText());
        controller.cobrar();
        controller.setHomeController(this);
        stage.showAndWait();

    }

    public void renovar(String articulo) throws SQLException {
        if(getMeses()==-1) return;
        String confirmacion2;
        if(getMeses()==1){
            confirmacion2 = mostrarConfirmacion("Confirmación","El contrato se renovará por 1 mes a partir de la fecha de hoy. " +
                    "¿Estás seguro de renovar el contrato?");
        }else{
            confirmacion2 = mostrarConfirmacion("Confirmación","El contrato se renovará por "+ getMeses()+" meses a partir de la fecha de hoy. " +
                    "¿Estás seguro de renovar el contrato?");
        }
        if(confirmacion2.equals("OK")){
            Object[][] renovacion = controlBd.consultarRenovaciones(txtNumeroContrato_DetalleContrato.getText());

            int renovaciones = Integer.parseInt(renovacion[0][0].toString())+1;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime fechaFinal = LocalDateTime.now().plusMonths(getMeses());
            LocalDateTime hoy = LocalDateTime.now();
            String vencimiento = fechaFinal.toString();
            String fechaHoy = hoy.toString();



            String valor = (String)controlBd.getValorArticulo(articulo)[0][0];
            String precio = valor.replace(".", "");


            String contratoNuevo = new SQL_Sentencias(this.usuario.getUsername(),this.usuario.getPassword()).
                    InsertarContratoRenovado(txtCedula_DetalleContrato.getText(),articulo,Integer.parseInt(precio),
                            Double.parseDouble(txtPorcentaje_DetalleContrato.getText()),renovaciones,vencimiento,this.usuario.getUsername());
            if(contratoNuevo.length()!=0){
                controlBd.updateEstado_Retractado(txtNumeroContrato_DetalleContrato.getText(),fechaHoy);
                mostrarTablaInicial();
                llenarDatos_DetalleContrato(contratoNuevo,txtCedula_DetalleContrato.getText());
            }else{
                mostrarAlerta("No se renovó","Algo salió mal y no se pudo renovar el contrato.");
            }
        }
    }

    private String confirmarCobroRenovacion(String text) {
        String valorACobrar=cobrarInteresRenovacion(txtNumeroContrato_DetalleContrato.getText());
        String confirmacion=mostrarConfirmacion("Cobro utilidad",
                "La utilidad a pagar del actual contrato es" +
                        ": "+Procedimientos.setPuntosDecimales(valorACobrar));
        if(confirmacion.equals("OK")){
            return valorACobrar;
        }else{
            return null;
        }
    }

    public String cobrarInteresRenovacion(String numeroContrato) {
        long[] tiempo=calcularTiempo(numeroContrato);
        long meses=tiempo[0];
        long dias=tiempo[1];
        Object[][] informacionContrato = controlBd.GetContrato(numeroContrato);
        if(meses==0){meses=1;}
        if(dias>5){meses = meses+1;}
        int valor = Integer.parseInt(informacionContrato[0][8].toString());
        double porcentaje = Double.parseDouble(informacionContrato[0][9].toString());
        double cobroMes = valor*porcentaje/100;
        double utilidad = cobroMes * meses;
        float cobro = (float) utilidad;
        if(cobro%50!=0){
            float residuo=cobro%50;
            if(residuo<25){
                cobro-=residuo;
            }else {
                cobro+=(50-residuo);
            }
        }
        String valorCobro=String.format("%.0f",cobro);
        return valorCobro;
    }

    public  long[] calcularTiempo(String numeroContrato){
        Object[][] informacionContrato = this.controlBd.GetContrato(numeroContrato);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
        String fecha = informacionContrato[0][5].toString().substring(0,19).replace(' ','T');
        LocalDateTime fechaInicioContrato = LocalDateTime.parse(fecha,dtf);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fechaInicioContrato);
        //Calcula la cantidad de días entre la fecha de vencimiento del contrato y el día actual
        long meses = tempDateTime.until(now, ChronoUnit.MONTHS);
        long dias = fechaInicioContrato.plusMonths(meses).until(now, ChronoUnit.DAYS);
        long[] tiempo = {meses,dias};
        return tiempo;
    }



    public void setMeses(int meses) {
        this.meses=meses;
    }
    public int getMeses(){
        return this.meses;
    }

    public void btnEditaryRenovar() throws SQLException {
        String idArticulo = null;
        Object[][] contrato = controlBd.consultarEstado(txtNumeroContrato_DetalleContrato.getText());
        String estadoContrato = contrato[0][0].toString();

        if (estadoContrato.equals("Retractado")){
            mostrarAlerta("Contrato retractado","El contrato que intentas retractar ya está retractado.");
            return;
        }
        try {
             abrirDetalleContrato(true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String popUpEditarArticulo() throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/EditarArticulo.fxml"));
        Parent root=loader.load();
        EditarArticuloController controller=loader.getController();
        controller.getComboCategoria().setValue(txtCategoria_DetalleContrato.getText());
        controller.getComboSubcategoria().setValue(txtSubcategoria_DetalleContrato.getText());
        controller.getTxtPesoArticulo().setText(txtPeso_DetalleContrato.getText());
        controller.getTxtValorArticulo().setText(txtValor_DetalleContrato.getText());
        controller.getTxtDescripcionArticulo().setText(txtDescripcion_DetalleContrato.getText());
        SpinnerValueFactory<Double> valor= new SpinnerValueFactory.DoubleSpinnerValueFactory(
                3,mesesPlazo(txtSubcategoria_DetalleContrato),Double.parseDouble(txtPorcentaje_DetalleContrato.getText()),0.5);
        controller.getSpinnerPorcentaje().setValueFactory(valor);
        controller.setHomeController(this);
        controller.inicializar();
        Scene scene= new Scene(root);
        Stage stage= new Stage();
        stage.setTitle("Editar artículo");
        stage.resizableProperty().setValue(Boolean.TRUE);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        return controller.getIdArticulo();
    }
    public void formatoDeTextos(){
        TextFormater(txtValorArticulo);
        TextFormater(txtValor_DetalleContrato);
        TextFormater(txtCedula_DetalleContrato);
        TextFormater(txtCedulaBusquedaCliente);
        TextFormater(txtcedula);
        TextFormater(txtcedulaNuevaRetroventa);
        TextFormater(txtBusquedaCliente);
        TextFormater(txtCedulaContratos);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public void inicializar(){
        Procedimientos.setHomeController(this);
        if(Procedimientos.estadoCaja(this).equals("Cerrada")){
            btnRenovarContrato.setDisable(true);
            btnRetractarContrato.setDisable(true);
        }
        contratosVencidos();
        formatoDeTextos();
        mostrarTablaInicial();
        SpinnerPorcentaje.setEditable(false);
        SpinnerPorcentaje.setDisable(true);
        comboCategoria.setItems(categorias);
        comboEstados.setItems(estados);
        comboEstados.getSelectionModel().select(0);
        if (!usuario.getRol().equals("admin")) mbtngerencia.setDisable(true);
//        Boolean desactivar= usuario.getRol().equals("admin")? false:true;
//        mbtngerencia.setDisable(desactivar);
        graficar();
        InteresOro();
        //mostrarTablaInicial();
        paneHome.toFront();


        anchorPrincipal.getChildren().add(anchorCajaAdmin);
        anchorPrincipal.getChildren().add(anchorImpresion);
        anchorPrincipal.getChildren().add(anchorDescuentos);
        anchorPrincipal.getChildren().add(anchorUsuarios);
        anchorPrincipal.getChildren().add(anchorCaja);

    }
    private void cargarCaja(){
        if(cajaController==null){
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Caja.fxml"));
                anchorCaja= loader.load();
                cajaController= loader.getController();
                cajaController.setHomeController(this);
                if (Procedimientos.estadoCaja(this).equals("Cerrada")) {
                    cajaController.btnRetiroCapital.setDisable(true);
                    cajaController.btnIngresoCapital.setDisable(true);
                    cajaController.btnCerrarCaja.setDisable(true);
                } else {
                    cajaController.btnAbrirCaja.setVisible(false);
                }
                cajaController.llenarTabla(String.valueOf(LocalDate.now()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String fechaNueva= String.valueOf(cajaController.getSelectorFecha().getValue());
            cajaController.llenarTabla(fechaNueva);
        }
        limpiarYCargar(HBoxPrincipal,anchorCaja);
    }

    private void cargarCajaAdmin(){
        if(cajaAdminController==null){
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/CajaAdmin.fxml"));
                anchorCajaAdmin= loader.load();
                cajaAdminController= loader.getController();
                cajaAdminController.setHomeController(this);
                if (Procedimientos.estadoCaja(this).equals("Cerrada")) {
                    cajaAdminController.btnRetiroCapital.setDisable(true);
                    cajaAdminController.btnIngresoCapital.setDisable(true);
                    cajaAdminController.btnCerrarCaja.setDisable(true);
                } else {
                    cajaAdminController.btnAbrirCaja.setVisible(false);
                }
                LocalDate now = LocalDate.now();
                String fechaHoy = String.valueOf(now);
                cajaAdminController.llenarTabla(fechaHoy);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String fechaNueva= String.valueOf(cajaAdminController.getSelectorFecha().getValue());
            cajaAdminController.llenarTabla(fechaNueva);
        }
        limpiarYCargar(HBoxPrincipal,anchorCajaAdmin);
    }

    private void limpiarYCargar(HBox hbox,AnchorPane anchor){
        hbox.getChildren().clear();
        hbox.getChildren().add(anchor);
        anchorPrincipal.toFront();
        anchor.toFront();
    }

    private void cargarImpresion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Impresion.fxml"));
            anchorImpresion = loader.load();
            impresionController = loader.getController();
            impresionController.setHomeController(this);
            impresionController.setControlBd(this.controlBd);
            impresionController.iniciarElementosInterfaz();
            limpiarYCargar(HBoxPrincipal,anchorImpresion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDescuentos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Descuentos.fxml"));
            anchorDescuentos = loader.load();
            descuentosController = loader.getController();
            descuentosController.setHomeController(this);
            limpiarYCargar(HBoxPrincipal,anchorDescuentos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para actualizar la gráfica con los datos requeridos
    @FXML
    private void graficar() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new LineChart.Data("17/01", 20000000));
        series.getData().add(new LineChart.Data("18/01", 19500000));
        series.getData().add(new LineChart.Data("19/01", 11000000));
        series.getData().add(new LineChart.Data("20/01", 16000000));
        series.getData().add(new LineChart.Data("21/01", 15000000));
        series.getData().add(new LineChart.Data("22/01", 13000000));
        series.getData().add(new LineChart.Data("23/01", 18000000));
        graficaHome.getData().addAll(series);
    }

    @FXML
    public boolean buscarClienteNuevaRetro() {
        Object[][] Responsables = controlBd.GetClienteNuevoContrato(txtcedulaNuevaRetroventa.getText());
        if (Responsables[0][0] != null) {
            txtnombreNuevaRetroventa.setText(Responsables[0][1].toString());
            txtapellidoNuevaRetroventa.setText(Responsables[0][2].toString());
            txtdireccionNuevaRetroventa.setText(Responsables[0][3].toString());
            txttelefono1NuevaRetroventa.setText(Responsables[0][4].toString());
            txttelefono2NuevaRetroventa.setText(Responsables[0][5].toString());
            txtcorreoNuevaRetroventa.setText(Responsables[0][6].toString());
            txtcedulaNuevaRetroventa.setEditable(false);
            btnVerFoto.setDisable(false);
            btnCambiarCliente.setDisable(false);
            revisarHuellayFoto(1);
            return true;
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("No hay ningún cliente creado con ese número de cédula, "
                    + "por favor intenta de nuevo.");
            alert1.setTitle("No existe usuario");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            txtcedulaNuevaRetroventa.setText("");
            txtcedulaNuevaRetroventa.requestFocus();
            txtcedulaNuevaRetroventa.selectAll();
            return false;
        }
    }


    public void revisarHuellayFoto(int opcion) {
        if(opcion==1){
            Object huella = controlBd.getHuella(txtcedulaNuevaRetroventa.getText());
            Object foto = controlBd.ConsultarFotoCliente(txtcedulaNuevaRetroventa.getText());
            if (huella == null && foto == null) {
                alertaHuellayFoto("El usuario ingresado no " +
                        "cuenta con foto ni huella en la base de datos, ¿Desea editar este usuario?");
            } else if(huella==null){
                alertaHuellayFoto("El usuario ingresado no " +
                        "cuenta con huella en la base de datos, ¿Desea editar este usuario?");
            } else if(foto==null){
                alertaHuellayFoto("El usuario ingresado no " +
                        "cuenta con foto en la base de datos, ¿Desea editar este usuario?");
            }
        } else if(opcion==2){
            String cedula = Tabla_BusquedaClientes.getSelectionModel().getSelectedItem().getColumnaCedulaCliente().toString();
            Object huella = controlBd.getHuella(cedula);
            Object foto = controlBd.ConsultarFotoCliente(cedula);
            if (huella == null && foto == null) {
                mensajeHuellayFoto("El usuario ingresado no " +
                        "cuenta con foto ni huella en la base de datos");
            } else if(huella==null){
                mensajeHuellayFoto("El usuario ingresado no " +
                        "cuenta con huella en la base de datos");
            } else if(foto==null){
                mensajeHuellayFoto("El usuario ingresado no " +
                        "cuenta con foto en la base de datos");
            }
        }


    }

    public void mensajeHuellayFoto(String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public void alertaHuellayFoto(String mensaje){
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType si = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, no, si);
        alert.setHeaderText(null);
        alert.setTitle("Favor Responder");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == no) {

        } else {
            paneBusquedaCliente.toFront();
            llenarDatosDetalleCliente(txtcedulaNuevaRetroventa.getText());
        }
    }

    @FXML
    public void onClicBorrarArticuloNuevaRetroventa() {
        //Parte información articulo
        comboCategoria.setValue("Seleccione una");
        comboSubcategoria.setValue("Seleccione una");
        txtPesoArticulo.setText("");
        txtValorArticulo.setText("");
        txtDescripcionArticulo.setText("");
        btnCambiarCliente.setDisable(true);
        btnVerFoto.setDisable(true);
    }
    @FXML
    private void InteresOro(){
        SpinnerValueFactory<Double> ValoresSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(3,5,5,0.5);
        this.SpinnerPorcentaje.setValueFactory(ValoresSpinner);
    }

    @FXML
    private void InteresElectrodomesticos(){
        SpinnerValueFactory<Double> ValoresSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(8,10,10,0.5);
        this.SpinnerPorcentaje.setValueFactory(ValoresSpinner);
    }

    @FXML
    private void CambiarCliente() {
        //Parte información cliente
        txtcedulaNuevaRetroventa.setText("");
        txtnombreNuevaRetroventa.setText("");
        txtapellidoNuevaRetroventa.setText("");
        txtdireccionNuevaRetroventa.setText("");
        txtcorreoNuevaRetroventa.setText("");
        txttelefono1NuevaRetroventa.setText("");
        txttelefono2NuevaRetroventa.setText("");
        txtcedulaNuevaRetroventa.setEditable(true);


    }

    @FXML
    private void home() {
        paneHome.toFront();
    }

    @FXML
    public void onEnterCedulaNuevaRetroventa(ActionEvent ae) throws Exception {
        buscarClienteNuevaRetro();
    }

//    @FXML
//    private void enviarMensaje() {
//        String ACCOUNT_SID = "ACb97798b04b2e86fa5cadabae8600fa7e";
//        String AUTH_TOKEN = "b70b68a8ffa00dabe2d2429fb27b1832";
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        IncomingPhoneNumber incomingPhoneNumber = IncomingPhoneNumber.creator(
//                new com.twilio.type.PhoneNumber("+15005550006"))
//                .setVoiceUrl(URI.create("http://demo.twilio.com/docs/voice.xml"))
//                .create();
//
//        System.out.println(incomingPhoneNumber.getSid());


//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+573124038290"),
//                new com.twilio.type.PhoneNumber("+15005550006"),
//                "Hi there!")
//                .create();
//
//        System.out.println(message.getSid());
//    }

    @FXML
    public void handleButtonAction() {

    }

    public void calcularNumeroContrato(){
        int Id = 0;
        String[] columnas={"Numero_contrato"};
        Object[][] resultado = new SQL_Sentencias(this.usuario.getUsername(),this.usuario.getPassword()).GetTabla(columnas, "contratos", "SELECT Numero_contrato FROM contratos ORDER BY Numero_contrato ASC;");
        if(resultado.length==0){
            Id = 1;
        }else {
            Id = Integer.parseInt(resultado[resultado.length-1][0].toString().substring(1))+1;
        }
        String IdContrato = String.valueOf(Id);
        while (IdContrato.length()<7){
            IdContrato = "0"+IdContrato;
        }
        IdContrato = "C"+IdContrato;
        lblNumeroContrato.setText(IdContrato);
    }

    @FXML
    public void botonesHandle(ActionEvent event) {
        if (event.getSource() == btnnuevaretro) {
            calcularNumeroContrato();
            paneNuevaRetroventa.toFront();
            if(pantallaActiva != 1){
                new FadeIn(paneNuevaRetroventa).play();
            }
            aPimgClienteNuevaRetroventa.setOpacity(0);
            aPimgClienteNuevaRetroventa.toBack();
            pantallaActiva = 1;

        } else if (event.getSource() == btnventa) {

        } else if (event.getSource() == btnnuevaretro && pantallaActiva != 3) {
            pantallaActiva = 3;
            paneNuevaRetroventa.toFront();
            new FadeIn(paneNuevaRetroventa).play();
        } else if (event.getSource() == btncontrato) {
            mostrarTablaInicial();
            anchorTablaContratos.toFront();
            paneContratos.toFront();
            if(pantallaActiva != 4){
                new FadeIn(paneContratos).play();
            }
            pantallaActiva = 4;

        } else if (event.getSource() == btncontrato) {
            anchorDetallesContrato.toBack();
        } else if (event.getSource() == btnclientehistorial) {

        } else if (event.getSource() == btnreportes) {

        } else if (event.getSource() == btnusuarios && pantallaActiva!=8) {
            pantallaActiva=8;
            anchorPrincipal.toFront();
            if(!flagPaneUsuario){
                flagPaneUsuario=true;
                cargarFXMLUsuario();
            }
            anchorUsuarios.toFront();
        } else if (event.getSource() == btnbalance) {

        } else if (event.getSource() == btnaportesoretiro) {
            pantallaActiva=10;
            //anchorPrincipal.toFront();
            cargarCajaAdmin();


        } else if (event.getSource() == btnestadistica) {

        } else if (event.getSource() == btnhistorialcorreciones) {

        } else if (event.getSource() == btnImpresion && pantallaActiva!=13) {
            pantallaActiva = 13;
            cargarImpresion();
        } else if (event.getSource() == btnDescuentos && pantallaActiva!=14) {
            pantallaActiva = 14;
            //anchorPrincipal.toFront();
            if(flagPaneDescuentos==false){
                cargarDescuentos();
                flagPaneDescuentos=true;
            }
            anchorDescuentos.toFront();
        } else if (event.getSource() == btnbackup) {

        } else if (event.getSource() == btncaja) {
            pantallaActiva=16;
            cargarCaja();
            anchorCaja.toFront();
        } else if (event.getSource() == btnnuevocliente && pantallaActiva != 17) {
            pantallaActiva = 17;
            paneNuevoCliente.toFront();
            new FadeIn(paneNuevoCliente).play();
        } else if (event.getSource() == btnbusquedacliente) {
            mostrarTablaInicial_Clientes();
            paneBusquedaCliente.toFront();
            VBoxAnchorTablaClientes.toFront();
            anchorTablaClientes.toFront();
            //new FadeIn(paneBusquedaCliente).play();

            if(pantallaActiva != 18){
                new FadeIn(paneContratos).play();
            }
            pantallaActiva = 18;
        }
    }

    public void cargarFXMLUsuario() {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Usuarios.fxml"));
        try {
            anchorUsuarios=loader.load();
            usuarioController=loader.getController();
            usuarioController.setHomeController(this);
            usuarioController.inicializar();
            limpiarYCargar(HBoxPrincipal,anchorUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void crearUsuario(ActionEvent event) throws SQLException, IOException {
            byte[] data = null;

            Boolean isCreado = new SQL_Sentencias(this.usuario.getUsername(),this.usuario.getPassword()).InsertarNuevoCliente((txtcedula.getText()),
                    txtnombre.getText(), txtapellido.getText(), txtLugarExpedicion.getText(),txtdireccion.getText(),txtbarrio.getText(),
                    txttelefono1.getText(), txttelefono2.getText(), txtcorreo.getText(), sen.getUser());
            if (isCreado) {
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"Usuario creado exitosamente",ButtonType.OK);
                alert.setHeaderText("");
                alert.showAndWait();
                btnHuellaNuevoCliente.setDisable(false);
                btnFotoNuevoCliente.setDisable(false);
                btnInsertarDatosNuevoCliente.setDisable(true);
                txtcedula.setEditable(false);
                txtnombre.setEditable(false);
                txtLugarExpedicion.setEditable(false);
                txtapellido.setEditable(false);
                txtdireccion.setEditable(false);
                txtbarrio.setEditable(false);
                txttelefono1.setEditable(false);
                txttelefono2.setEditable(false);
                txtcorreo.setEditable(false);
            }



    }

    @FXML protected void cambioEstadoContratos() {
        txtCedulaContratos.setText("");
        txtNumeroContrato.setText("");
        String output = (String) comboEstados.getValue();
        if(output == "Vencidos"){
            Object[][] Contratos = controlBd.ConsultarContratosVencidos();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }else if(output == "Vigentes"){
            Object[][] Contratos = controlBd.ConsultarContratosVigentes();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);

        }else if(output == "Retractados"){
            Object[][] Contratos = controlBd.ConsultarContratosRetractados();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);

        }else{
            Object[][] Contratos = controlBd.ConsultarContrato();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }
    }

    @FXML protected void cambioSubcategorias() {
        String output = (String) comboCategoria.getValue();
        if(output == "Oro"){
            comboSubcategoria.getItems().removeAll();
            comboSubcategoria.setItems(subOro);
            InteresOro();
            txtPesoArticulo.setDisable(false);
            comboSubcategoria.getSelectionModel().select(0);
        }else{
            comboSubcategoria.getItems().removeAll();
            comboSubcategoria.setItems(subElectrodomesticos);
            comboSubcategoria.setValue("Cámaras");
            InteresElectrodomesticos();
            txtPesoArticulo.setText("");
            txtPesoArticulo.setDisable(true);
        }
    }


    public String InsertarNuevoArticulo(ComboBox<String> categoria,ComboBox<String> subCategoria,
                                                 TextArea descripccion, TextField peso,TextField valorArticulo) throws SQLException{
        String IdArticulo;
        String precio = valorArticulo.getText().replace(".", "");
        if(categoria.getValue().toString() =="Oro"){
            IdArticulo = new SQL_Sentencias(this.usuario.getUsername(),this.usuario.getPassword()).InsertarNuevoArticulo(categoria.getValue().toString(),subCategoria.getValue().toString(),
                    descripccion.getText(),
                    Double.parseDouble(peso.getText()),Integer.parseInt(precio),sen.getUser());
        }else{
            IdArticulo = new SQL_Sentencias(this.usuario.getUsername(),this.usuario.getPassword()).InsertarNuevoArticulo(categoria.getValue().toString(),subCategoria.getValue().toString(),
                    descripccion.getText(),
                   Integer.parseInt(precio),sen.getUser());
        }
        return IdArticulo;
    }

    public int  preguntarMesesNuevoContrato(int min, int max, int init) {
        setMeses(-1);
        Stage popupwindow= new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.initStyle(StageStyle.UTILITY);
        popupwindow.setTitle("Tiempo para el contrato");
        Button button1= new Button("Confirmar");
        Label label1= new Label("Meses ");

        label1.setWrapText(true);
        Spinner<Integer> spinner= new Spinner<>(1,mesesPlazo(comboSubcategoria),3);
        button1.setOnAction(e -> {
            setMeses(spinner.getValue());
            popupwindow.close();
        });
        VBox layout= new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label1,spinner, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        return meses;
    }

    public int mesesPlazo(ComboBox combo){
        if(combo.getValue()=="Oro"){
            return 6;
        }else{
            return 3;
        }
    }

    public int mesesPlazo(TextField textField){
        if(textField.getText().equals("Oro")){
            return 6;
        }else{
            return 3;
        }
    }

    @FXML
    public void onActionCerrarSesion() {
        Stage stage = (Stage) anchorPrincipal.getScene().getWindow();
        this.controlBd=null;
        this.sen=null;
        // do what you have to do
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
            //stage.initStyle(StageStyle.UNDECORATED);
            //stage.getIcons().add(new Image("/im/favicon.png"));
            Stage stage2 = new Stage();
            stage2.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML protected void InsertarNuevoContrato() throws SQLException {
        if(txtcedulaNuevaRetroventa.getText().length()==0){
            mostrarAlerta(" Información incompleta","No has seleccionado el cliente responsable del nuevo contrato.");
            return;
        }

        if(txtValorArticulo.getText().length()==0){
            mostrarAlerta(" Información incompleta","No has escrito el valor por el que se hará el contrato.");
            return;
        }

        if(txtDescripcionArticulo.getText().length()==0){
            mostrarAlerta(" Información incompleta","No has escrito la descripción del artículo con el que se hará el contrato.");
            return;
        }



        int meses=preguntarMesesNuevoContrato(1,mesesPlazo(comboSubcategoria),3);
        String confirmacion;
        if(meses==-1) return;
        if(meses==1){
            confirmacion = mostrarConfirmacion("Confirmación","El contrato se creará con las datos que se llenaron y a un término de 1 mes a partir de la fecha de hoy. ¿Estás seguro que quieres crear el contrato?");
        }else{
            confirmacion = mostrarConfirmacion("Confirmación","El contrato se creará con las datos que se llenaron y a un término de "+String.valueOf(meses)+" meses. ¿Estás seguro que quieres crear el contrato?");
        }


        if(confirmacion.equals("OK")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now().plusMonths(meses);
            String vencimiento = now.toString();
            //System.out.println(dtf.format(now));
            String ArticuloId = InsertarNuevoArticulo(comboCategoria,comboSubcategoria,txtDescripcionArticulo,
                    txtPesoArticulo,txtValorArticulo);
            String precio = txtValorArticulo.getText().replace(".", "");
            float egreso=redondearA50(Float.parseFloat(precio));
            System.out.println("Contrato: "+lblNumeroContrato.getText());
            /*descripcion
              ingreso,egreso,utilidad,total
            * */
            Caja caja = new Caja("Retroventa",
                    "Retroventa "+lblNumeroContrato.getText(),
                    "0",String.valueOf(egreso),"0",
                    String.valueOf(controlBd.ConsultarTotalCaja()-egreso),this.getUsuario().getUsername());
            if (!controlBd.insertCaja(caja)) return;
            boolean success = new SQL_Sentencias(this.usuario.getUsername(),this.usuario.getPassword()).InsertarNuevoContrato(txtcedulaNuevaRetroventa.getText(), ArticuloId, Integer.parseInt(precio),
                    Double.parseDouble(SpinnerPorcentaje.getValue().toString()), vencimiento, sen.getUser());
            if (success && ArticuloId!=null) {

                CambiarCliente();
                onClicBorrarArticuloNuevaRetroventa();
            }
        }
    }

    @FXML
    public void seleccionUsuario(ActionEvent event) {
        if (cboxusuario.getValue().toString() == "Crear Usuario") {
            pnCrearUsuario.toFront();
        }
    }

    @FXML
    public void tomarHuella() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Huella_Guardar huella = new Huella_Guardar();
        huella.setCedula(txtcedula.getText());
        huella.setVisible(true);
    }
    @FXML
    public void onClicTomarFotografia(){
        if(pantallaActiva==17) {
            tomarFotografia(txtcedula.getText(),0);
        }else if(pantallaActiva==18){
            tomarFotografia(txtCedulaBusquedaCliente.getText(),1);
        }
    }

    public float redondearA50(float cobro){
        if(cobro%50!=0){
            float residuo=cobro%50;
            if(residuo<25){
                cobro-=residuo;
            }else {
                cobro+=(50-residuo);
            }
        }
        return cobro;
    }

    public void tomarFotografia(String cedula,int refresh) {
        Stage stage = new Stage();

        WebCamAppLauncher webCam = new WebCamAppLauncher(this,cedula,refresh);
        webCam.start(stage);
        stage.show();

        //        Webcam cam = Webcam.getDefault();
//        Stack<String> nombrescamaras = new Stack<>();
//        List<Webcam> camaras = Webcam.getWebcams();
//        final Object lock = new Object();
//        for (Webcam webcam : camaras) {
//            nombrescamaras.add(webcam.getName());
//        }
//        cam.getDiscoveryService().setEnabled(false);
//        cam.getDiscoveryService().stop();
//        cam.close();
//        Combo combo = new Combo(lock, nombrescamaras);
//        Detener detener = new Detener(lock,sen,this);
//        detener.setCedula(cedula);
//        Thread t2 = new Thread(detener);
//        Thread t1 = new Thread(combo);
//
//        t2.start();
//        t1.start();
    }
    @FXML
    public void onClicTomarHuellaDetalleCliente(){
        Huella_Guardar huella = null;
        try {
            huella = new Huella_Guardar();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        huella.setCedula(txtCedulaBusquedaCliente.getText());
        huella.setVisible(true);
    }
    @FXML
    public void onClicRefreshFotogradiaDetalleCliente(){
        byte[] imagen = controlBd.ConsultarFotoCliente(txtCedulaBusquedaCliente.getText());
        mostrarFoto(imagen,imageViewBusquedaCliente);
    }
    public void onClicBorrarDatosNuevoCliente(){
        txtcedula.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        txtLugarExpedicion.setText("");
        txtdireccion.setText("");
        txtbarrio.setText("");
        txttelefono1.setText("");
        txttelefono2.setText("");
        txtcorreo.setText("");
        txtcedula.setEditable(true);
        txtnombre.setEditable(true);
        txtLugarExpedicion.setEditable(true);
        txtapellido.setEditable(true);
        txtdireccion.setEditable(true);
        txtbarrio.setEditable(true);
        txttelefono1.setEditable(true);
        txttelefono2.setEditable(true);
        txtcorreo.setEditable(true);
        btnInsertarDatosNuevoCliente.setDisable(false);
        btnFotoNuevoCliente.setDisable(true);
        btnHuellaNuevoCliente.setDisable(true);
    }

    @FXML
    public void buscarConHuellaBusquedaCliente(ActionEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        txtBusquedaCliente.setText("");
        Huella_Identificar huella= new Huella_Identificar(this);
        huella.setVisible(true);
    }
    @FXML
    public void buscarClienteBusquedaCliente() {
        llenarDatosDetalleCliente(txtBusquedaCliente.getText());
    }

    @FXML
    public void habilitarCambioPorcentaje(){
        if(btnCambiarPorcentaje.getText().equals("Cambiar")){
            SpinnerPorcentaje.setDisable(false);
            btnCambiarPorcentaje.setText("Confirmar");
        }else if(btnCambiarPorcentaje.getText().equals("Confirmar")){
            SpinnerPorcentaje.setDisable(true);
            btnCambiarPorcentaje.setText("Cambiar");
        }
    }

    public void llenarDatosDetalleCliente(String cedulaString){
        String cedula = cedulaString.replace(".","");
        anchorDetallesCliente.toFront();
        VBoxanchorDetallesCliente.toFront();
        Object[][] informacionCliente = controlBd.GetDetallesNuevoContrato(cedula);
        txtCedulaBusquedaCliente.setText(cedula);
        txtNombreBusquedaCliente.setText(informacionCliente[0][1].toString());
        txtApellidoBusquedaCliente.setText(informacionCliente[0][2].toString());
        txtLugarExpedicion.setText(informacionCliente[0][3].toString());
        txtDireccionBusquedaCliente.setText(informacionCliente[0][4].toString());
        txtBarrioBusquedaCliente.setText(informacionCliente[0][5].toString());
        txtTelefono1BusquedaCliente.setText(informacionCliente[0][6].toString());
        txtTelefono2BusquedaCliente.setText(informacionCliente[0][7].toString());
        txtCorreoBusquedaCliente.setText(informacionCliente[0][8].toString());
        txtPerfilBusquedaCliente.setText(informacionCliente[0][10].toString());
        txtFechaRegistroBusquedaCliente.setText(informacionCliente[0][11].toString().substring(0,10));
        byte[] imagen = controlBd.ConsultarFotoCliente(cedula);
        mostrarFoto(imagen,imageViewBusquedaCliente);
    }

    public void llenarDatos_DetalleContrato(String numeroContrato, String cedulaString){
        String cedula = cedulaString.replace(".","");
        Object[][] informacionCliente = controlBd.GetClienteNuevoContrato(cedula);
        txtCedula_DetalleContrato.setText(cedula);
        txtNombre_DetalleContrato.setText(informacionCliente[0][1].toString());
        txtApellidos_DetalleContrato.setText(informacionCliente[0][2].toString());
        txtDireccion_DetalleContrato.setText(informacionCliente[0][3].toString());
        txtTelefono1_DetalleContrato.setText(informacionCliente[0][5].toString());
        txtTelefono2_DetalleContrato.setText(informacionCliente[0][6].toString());
        txtCorreo_DetalleContrato.setText(informacionCliente[0][7].toString());
        txtPerfil_DetalleContrato.setText(informacionCliente[0][9].toString());
        Object[][] informacionContrato = controlBd.GetContrato(numeroContrato);
        txtNumeroContrato_DetalleContrato.setText(numeroContrato);
        txtFechaInicio_DetalleContrato.setText(informacionContrato[0][5].toString().substring(0,10));
        txtFechaFinal_DetalleContrato.setText(informacionContrato[0][6].toString().substring(0,10));
        txtEstado_DetalleContrato.setText(informacionContrato[0][11].toString());
        txtRenovaciones_DetalleContrato.setText(informacionContrato[0][10].toString());
        txtCreadoPor_DetalleContrato.setText(informacionContrato[0][12].toString());
        txtValor_DetalleContrato.setText(informacionContrato[0][8].toString());
        //Calculo de días desde la creación del contrato hasta hoy
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
        String fecha = informacionContrato[0][5].toString().substring(0,19).replace(' ','T');
        LocalDateTime fechaInicioContrato = LocalDateTime.parse(fecha,dtf);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fechaInicioContrato);
        long days = tempDateTime.until(now,ChronoUnit.DAYS );
        txtTiempo_DetalleContrato.setText(Long.toString(days));
        txtPorcentaje_DetalleContrato.setText(informacionContrato[0][9].toString());
        Object[][] informacionArticulo = controlBd.GetArticulo(informacionContrato[0][2].toString());

        if(informacionArticulo[0][5]!=null){
            txtPeso_DetalleContrato.setText(informacionArticulo[0][5].toString());
        }
        txtDescripcion_DetalleContrato.setText(informacionArticulo[0][4].toString());
        txtCategoria_DetalleContrato.setText(informacionArticulo[0][2].toString());
        txtSubcategoria_DetalleContrato.setText(informacionArticulo[0][3].toString());

        byte[] imagen = controlBd.ConsultarFotoCliente(cedula);
        mostrarFoto(imagen,imageViewDetalleContrato);
    }




    public void llenarDatos_DetalleContrato(){
        Object[][] informacionCliente = controlBd.GetClienteNuevoContrato(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
        txtCedula_DetalleContrato.setText(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
        txtNombre_DetalleContrato.setText(informacionCliente[0][1].toString());
        txtApellidos_DetalleContrato.setText(informacionCliente[0][2].toString());
        txtDireccion_DetalleContrato.setText(informacionCliente[0][3].toString());
        txtTelefono1_DetalleContrato.setText(informacionCliente[0][5].toString());
        txtTelefono2_DetalleContrato.setText(informacionCliente[0][6].toString());
        txtCorreo_DetalleContrato.setText(informacionCliente[0][7].toString());
        txtPerfil_DetalleContrato.setText(informacionCliente[0][9].toString());
        Object[][] informacionContrato = controlBd.GetContrato(TablaContratos.getSelectionModel().getSelectedItem().getNumeroContrato());
        txtNumeroContrato_DetalleContrato.setText(TablaContratos.getSelectionModel().getSelectedItem().getNumeroContrato());
        txtFechaInicio_DetalleContrato.setText(informacionContrato[0][5].toString().substring(0,10));
        txtFechaFinal_DetalleContrato.setText(informacionContrato[0][6].toString().substring(0,10));
        txtEstado_DetalleContrato.setText(informacionContrato[0][11].toString());
        txtRenovaciones_DetalleContrato.setText(informacionContrato[0][10].toString());
        txtCreadoPor_DetalleContrato.setText(informacionContrato[0][12].toString());
        txtValor_DetalleContrato.setText(informacionContrato[0][8].toString());
        //Calculo de días desde la creación del contrato hasta hoy
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
        String fecha = informacionContrato[0][5].toString().substring(0,19).replace(' ','T');
        LocalDateTime fechaInicioContrato = LocalDateTime.parse(fecha,dtf);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fechaInicioContrato);
        long days = tempDateTime.until(now,ChronoUnit.DAYS );
        txtTiempo_DetalleContrato.setText(Long.toString(days));
        txtPorcentaje_DetalleContrato.setText(informacionContrato[0][9].toString());
        Object[][] informacionArticulo = controlBd.GetArticulo(informacionContrato[0][2].toString());

        if(informacionArticulo[0][5]!=null){
            txtPeso_DetalleContrato.setText(informacionArticulo[0][5].toString());
        }
        txtDescripcion_DetalleContrato.setText(informacionArticulo[0][4].toString());
        txtCategoria_DetalleContrato.setText(informacionArticulo[0][2].toString());
        txtSubcategoria_DetalleContrato.setText(informacionArticulo[0][3].toString());
        //getClass.getResource("ruta")

        byte[] imagen = controlBd.ConsultarFotoCliente(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
        mostrarFoto(imagen,imageViewDetalleContrato);
    }

    public void llenarDatosDetalleCliente(){
        Object[][] informacionCliente = controlBd.GetClienteNuevoContrato(Procedimientos.getCedula());
        txtNombreBusquedaCliente.setText(informacionCliente[0][1].toString());
        txtApellidoBusquedaCliente.setText(informacionCliente[0][2].toString());
        txtDireccionBusquedaCliente.setText(informacionCliente[0][3].toString());
        txtTelefono1BusquedaCliente.setText(informacionCliente[0][4].toString());
        txtTelefono2BusquedaCliente.setText(informacionCliente[0][5].toString());
        txtCorreoBusquedaCliente.setText(informacionCliente[0][6].toString());
        byte[] imagen = controlBd.ConsultarFotoCliente(txtBusquedaCliente.getText());
        mostrarFoto(imagen,imageViewDetalleContrato);

    }

    public void setTxtBusquedaCliente(String txtBusquedaCliente) {
        this.txtBusquedaCliente.setText(txtBusquedaCliente);
    }

    public void mostrarTablaInicial(){
        Object[][] Contratos = controlBd.ConsultarContrato();
        for(int i=0;i<Contratos.length;i++){
            if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                contrato.setEstado(Contratos[i][4].toString());
                Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                contrato.setDescripcion(articulo[0][0].toString());
                Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                contrato.setNombre(clientes[0][0].toString());
                contrato.setApellidos(clientes[0][1].toString());
                arrayContratos.add(contrato);
            }
        }

        lista = FXCollections.observableArrayList(arrayContratos);
        numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
        estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
        cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
        vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
        TablaContratos.setItems(lista);
        lista.removeAll();
        estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
        {
            @Override
            public TableCell<Contrato, String> call(
                    TableColumn<Contrato, String> param)
            {
                return new TableCell<Contrato, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        if (!empty)
                        {
                            // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                            boolean isDeposit = true;
                            char val = '█';
                            setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                            if(item.toString().equals("Vigente")) // should be if type is deposit
                            {
                                setTextFill(Color.GREEN);
                                //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                            }
                            else if(item.toString().equals("Retractado"))
                            {
                                setTextFill(Color.BLUE);
                            }else{
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
            }
        });

        arrayContratos.removeAll(lista);
    }


    //Mostrar la tabla filtrada por cédula
    //Terminados los colores
    public void mostrarTablaCedula(){
        txtNumeroContrato.setText("");
        String output = (String) comboEstados.getValue();
        if(output == "Vencidos"){
            Object[][] Contratos = controlBd.ConsultarContratosVencidosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }else if(output == "Vigentes"){
            Object[][] Contratos = controlBd.ConsultarContratosVigentesLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);

        }else if(output == "Retractados"){
            Object[][] Contratos = controlBd.ConsultarContratosRetractadosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }else{
            Object[][] Contratos = controlBd.ConsultarContratosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    contrato.setEstado(Contratos[i][7].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }

            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }
    }



    //Mostrar la tabla Contratos filtrada por número de contrato
    public void mostrarTablaContrato(){
        txtCedulaContratos.setText("");
        String output = (String) comboEstados.getValue();
        if(output == "Vencidos"){
            Object[][] ContratosLikeContrato = controlBd.ConsultarContratosVencidosLikeContrato(txtNumeroContrato.getText().toString());
            for (int i = 0; i < ContratosLikeContrato.length; i++) {
                if (ContratosLikeContrato[i][0] != null && ContratosLikeContrato[i][1] != null && ContratosLikeContrato[i][2] != null) {
                    Contrato contrato = new Contrato(ContratosLikeContrato[i][0].toString(), Integer.parseInt(ContratosLikeContrato[i][1].toString()), ContratosLikeContrato[i][3].toString().substring(0, 10));
                    contrato.setEstado(ContratosLikeContrato[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(ContratosLikeContrato[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(ContratosLikeContrato[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente"))
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }else if(output == "Vigentes") {
            Object[][] ContratosLikeContrato = controlBd.ConsultarContratosVigentesLikeContrato(txtNumeroContrato.getText().toString());
            for (int i = 0; i < ContratosLikeContrato.length; i++) {
                if (ContratosLikeContrato[i][0] != null && ContratosLikeContrato[i][1] != null && ContratosLikeContrato[i][2] != null) {
                    Contrato contrato = new Contrato(ContratosLikeContrato[i][0].toString(), Integer.parseInt(ContratosLikeContrato[i][1].toString()), ContratosLikeContrato[i][3].toString().substring(0, 10));
                    contrato.setEstado(ContratosLikeContrato[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(ContratosLikeContrato[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(ContratosLikeContrato[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);

        }else if(output == "Retractados") {
            Object[][] ContratosLikeContrato = controlBd.ConsultarContratosRetractadosLikeContrato(txtNumeroContrato.getText().toString());
            for (int i = 0; i < ContratosLikeContrato.length; i++) {
                if (ContratosLikeContrato[i][0] != null && ContratosLikeContrato[i][1] != null && ContratosLikeContrato[i][2] != null) {
                    Contrato contrato = new Contrato(ContratosLikeContrato[i][0].toString(), Integer.parseInt(ContratosLikeContrato[i][1].toString()), ContratosLikeContrato[i][3].toString().substring(0, 10));
                    contrato.setEstado(ContratosLikeContrato[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(ContratosLikeContrato[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(ContratosLikeContrato[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>()
            {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param)
                {
                    return new TableCell<Contrato, String>()
                    {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            if (!empty)
                            {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                                if(item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                }
                                else if(item.toString().equals("Retractado"))
                                {
                                    setTextFill(Color.BLUE);
                                }else{
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }else{
            Object[][] ContratosLikeContrato = controlBd.ConsultarContratoLikeContrato(txtNumeroContrato.getText().toString());
            for (int i = 0; i < ContratosLikeContrato.length; i++) {
                if (ContratosLikeContrato[i][0] != null && ContratosLikeContrato[i][1] != null && ContratosLikeContrato[i][2] != null) {
                    Contrato contrato = new Contrato(ContratosLikeContrato[i][0].toString(), Integer.parseInt(ContratosLikeContrato[i][1].toString()), ContratosLikeContrato[i][3].toString().substring(0, 10));
                    contrato.setEstado(ContratosLikeContrato[i][4].toString());
                    Object[][] articulo = controlBd.ConsultarDescripcionArticulo(ContratosLikeContrato[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = controlBd.ConsultarNombresCliente(ContratosLikeContrato[i][1].toString());
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato, String>("numeroContrato"));
            estado.setCellValueFactory(new PropertyValueFactory<Contrato, String>("estado"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato, Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato, String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato, String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato, String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato, String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            estado.setCellFactory(new Callback<TableColumn<Contrato, String>, TableCell<Contrato, String>>() {
                @Override
                public TableCell<Contrato, String> call(
                        TableColumn<Contrato, String> param) {
                    return new TableCell<Contrato, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            if (!empty) {
                                // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                                boolean isDeposit = true;
                                char val = '█';
                                setText(String.valueOf(val) + String.valueOf(val) + String.valueOf(val) + String.valueOf(val));
                                if (item.toString().equals("Vigente")) // should be if type is deposit
                                {
                                    setTextFill(Color.GREEN);
                                    //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                                } else if (item.toString().equals("Retractado")) {
                                    setTextFill(Color.BLUE);
                                } else {
                                    setTextFill(Color.RED);
                                }
                            }
                        }
                    };
                }
            });
            arrayContratos.removeAll(lista);
        }
    }

    public void mostrarTablaInicial_Clientes(){

        Object[][] Clientes = controlBd.ConsultarCliente();
        String Foto;
        String Huella;
        for(int i=0;i<Clientes.length;i++){
            Foto = "true";
            Huella = "true";
            if (Clientes[i][0] != null && Clientes[i][1] != null && Clientes[i][2] != null&& Clientes[i][3] != null
                    && Clientes[i][4] != null&& Clientes[i][5] != null&& Clientes[i][6] != null
                    && Clientes[i][7] != null&& Clientes[i][8] != null&& Clientes[i][9] != null) {
                if(Clientes[i][10]==null){
                    Huella = "false";
                }
                if(Clientes[i][11]==null){
                        Foto = "false";
                    }
                Cliente cliente= new Cliente(Integer.parseInt(Clientes[i][0].toString()),Clientes[i][1].toString(),
                        Clientes[i][2].toString(),Clientes[i][3].toString(),Clientes[i][4].toString(),
                        Clientes[i][5].toString(),Clientes[i][6].toString(),Clientes[i][7].toString(),Clientes[i][8].toString()
                        ,Clientes[i][9].toString(),Huella,Foto);
                arrayClientes.add(cliente);
            }
        }

        listaClientes = FXCollections.observableArrayList(arrayClientes);
        ColumnaCedulaCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("ColumnaCedulaCliente"));
        ColumnaNombreCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaNombreCliente"));
        ColumnaApellidosCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaApellidosCliente"));
        ColumnaDireccionCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaDireccionCliente"));
        ColumnaTelefono1Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaTelefono1Cliente"));
        ColumnaTelefono2Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaTelefono2Cliente"));
        ColumnaCorreoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaCorreoCliente"));
        ColumnaHuellaCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaHuellaCliente"));
        ColumnaFotoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaFotoCliente"));
        Tabla_BusquedaClientes.setItems(listaClientes);
        listaClientes.removeAll();
        ColumnaHuellaCliente.setCellFactory(new Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(
                    TableColumn<Cliente, String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        if (!empty)
                        {
                            // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                            boolean isDeposit = true;
                            char val = '█';
                            setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                            if(item.toString().equals("true")) // should be if type is deposit
                            {
                                setTextFill(Color.GREEN);
                                //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                            }else{
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
            }
        });
        ColumnaFotoCliente.setCellFactory(new Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(
                    TableColumn<Cliente, String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        if (!empty)
                        {
                            // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                            boolean isDeposit = true;
                            char val = '█';
                            setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                            if(item.toString().equals("true")) // should be if type is deposit
                            {
                                setTextFill(Color.GREEN);
                                //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                            }else{
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
            }
        });

        arrayClientes.removeAll(listaClientes);
    }



    public void mostrarTablaClientesPorCedula(){
        Object[][] ClientesLikeCedula = controlBd.ConsultarClientesLikeCedula(txtBusquedaCliente.getText());
        String Foto;
        String Huella;
        for (int i = 0; i < ClientesLikeCedula.length; i++) {
            Foto = "true";
            Huella = "true";
            if (ClientesLikeCedula[i][0] != null && ClientesLikeCedula[i][1] != null && ClientesLikeCedula[i][2] != null&& ClientesLikeCedula[i][3] != null
                    && ClientesLikeCedula[i][4] != null&& ClientesLikeCedula[i][5] != null&& ClientesLikeCedula[i][6] != null
                    && ClientesLikeCedula[i][7] != null&& ClientesLikeCedula[i][8] != null&& ClientesLikeCedula[i][9] != null) {
                if(ClientesLikeCedula[i][10]==null){
                    Huella = "false";
                }
                if(ClientesLikeCedula[i][11]==null){
                    Foto = "false";
                }
                Cliente cliente= new Cliente(Integer.parseInt(ClientesLikeCedula[i][0].toString()),ClientesLikeCedula[i][1].toString(),
                        ClientesLikeCedula[i][2].toString(),ClientesLikeCedula[i][3].toString(),ClientesLikeCedula[i][4].toString(),
                        ClientesLikeCedula[i][5].toString(),ClientesLikeCedula[i][6].toString(),ClientesLikeCedula[i][7].toString(),ClientesLikeCedula[i][8].toString()
                        ,ClientesLikeCedula[i][9].toString(),Huella,Foto);
                arrayClientes.add(cliente);
            }
        }
        listaClientes = FXCollections.observableArrayList(arrayClientes);
        ColumnaCedulaCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("ColumnaCedulaCliente"));
        ColumnaNombreCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaNombreCliente"));
        ColumnaApellidosCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaApellidosCliente"));
        ColumnaDireccionCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaDireccionCliente"));
        ColumnaTelefono1Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaTelefono1Cliente"));
        ColumnaTelefono2Cliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaTelefono2Cliente"));
        ColumnaCorreoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaCorreoCliente"));
        ColumnaHuellaCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaHuellaCliente"));
        ColumnaFotoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("ColumnaFotoCliente"));
        Tabla_BusquedaClientes.setItems(listaClientes);
        listaClientes.removeAll();
        ColumnaHuellaCliente.setCellFactory(new Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(
                    TableColumn<Cliente, String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        if (!empty)
                        {
                            // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                            boolean isDeposit = true;
                            char val = '█';
                            setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                            if(item.toString().equals("true")) // should be if type is deposit
                            {
                                setTextFill(Color.GREEN);
                                //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                            }else{
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
            }
        });
        ColumnaFotoCliente.setCellFactory(new Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>>()
        {
            @Override
            public TableCell<Cliente, String> call(
                    TableColumn<Cliente, String> param)
            {
                return new TableCell<Cliente, String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        if (!empty)
                        {
                            // should be something like (transaction.getType().equals(TransactionTypes.DEPOSIT) ? true : false;)
                            boolean isDeposit = true;
                            char val = '█';
                            setText(String.valueOf(val)+String.valueOf(val)+String.valueOf(val)+String.valueOf(val));
                            if(item.toString().equals("true")) // should be if type is deposit
                            {
                                setTextFill(Color.GREEN);
                                //setFont(Font.font ("Verdana", FontWeight.BOLD,14));
                            }else{
                                setTextFill(Color.RED);
                            }
                        }
                    }
                };
            }
        });

        arrayClientes.removeAll(listaClientes);
    }
    //Mostrar la tabla de contratos de un determinado Cliete
    public void mostrarTablaInicialContratos_BusquedaClientes(String cedula){
        Object[][] Contratos = controlBd.ConsultarContratosWithCedula(cedula);
        for(int i=0;i<Contratos.length-1;i++){
            if (Contratos[i][0] != null && Contratos[i][2] != null) {
                Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                contrato.setValor(Contratos[i][4].toString());
                contrato.setPorcentaje(Contratos[i][5].toString());
                contrato.setRenovaciones(Contratos[i][6].toString());
                contrato.setEstado(Contratos[i][7].toString());
                Object[][] articulo = controlBd.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                contrato.setDescripcion(articulo[0][0].toString());
                Object[][] clientes = controlBd.ConsultarNombresCliente(Contratos[i][1].toString());
                contrato.setNombre(clientes[0][0].toString());
                contrato.setApellidos(clientes[0][1].toString());
                arrayContratos.add(contrato);
            }
        }

        lista = FXCollections.observableArrayList(arrayContratos);
        columnaContratoBusquedaClientes.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
        columnaArticuloBusquedaClientes.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
        columnaPorcentajeBusquedaClientes.setCellValueFactory(new PropertyValueFactory<Contrato,String>("porcentaje"));
        columnaValorBusquedaClientes.setCellValueFactory(new PropertyValueFactory<Contrato,String>("valor"));
        columnaRenovacionesBusquedaClientes.setCellValueFactory(new PropertyValueFactory<Contrato,String>("renovaciones"));
        columnaEstadoBusquedaClientes.setCellValueFactory(new PropertyValueFactory<Contrato,String>("estado"));

        TablaContratos_BusquedaClientes.setItems(lista);
        lista.removeAll();
        arrayContratos.removeAll(lista);
    }

    Contrato tempo;
    Date ultimoMomentoClic;
    public void onClicTablaContratos_BusquedaClientes(){
        contratoEscogidoTablaDetalles=TablaContratos_BusquedaClientes.getSelectionModel().getSelectedItem();
        if(contratoEscogidoTablaDetalles!=null){
            btnVerDetalleTablaDetallesClientes.setDisable(false);
        }
        Contrato fila = TablaContratos_BusquedaClientes.getSelectionModel().getSelectedItem();
        if (fila == null) return;
        if(fila != tempo){
            tempo = fila;
            ultimoMomentoClic = new Date();
        } else if(fila == tempo) {
            Date now = new Date();
            long diff = now.getTime() - ultimoMomentoClic.getTime();
            if (diff < 300){ //another click registered in 300 millis
                verDetalleContratoFromCliente();
            } else {
                ultimoMomentoClic = new Date();
            }
        }
    }


    Cliente temporal;
    Date ultimoClick;
    @FXML
    public void onClicTablaClientes(){
        clienteEscogidoTabla=Tabla_BusquedaClientes.getSelectionModel().getSelectedItem();
        if(clienteEscogidoTabla!=null){
            btnVerDetalleTablaClientes.setDisable(false);
        }
        Cliente fila = Tabla_BusquedaClientes.getSelectionModel().getSelectedItem();
        if (fila == null) return;
        if(fila != temporal){
            temporal = fila;
            ultimoClick = new Date();
        } else if(fila == temporal) {
            Date now = new Date();
            long diff = now.getTime() - ultimoClick.getTime();
            if (diff < 300){ //another click registered in 300 millis
                verDetalleTablaClientes();
            } else {
                ultimoClick = new Date();
            }
        }
    }



    Contrato temp;
    Date lastClickTime;
    @FXML
    public void onClicTablaContratos(){
        contratoEscogidoTabla=TablaContratos.getSelectionModel().getSelectedItem();
        if(contratoEscogidoTabla!=null){
            btnVerDetallesContratos.setDisable(false);
        }
        Contrato row = TablaContratos.getSelectionModel().getSelectedItem();
        if (row == null) return;
        if(row != temp){
            temp = row;
            lastClickTime = new Date();
        } else if(row == temp) {
            Date now = new Date();
            long diff = now.getTime() - lastClickTime.getTime();
            if (diff < 300){ //another click registered in 300 millis
                verDetalleContratos();
            } else {
                lastClickTime = new Date();
            }
        }
    }


    @FXML
    public void duplicarDocumento() throws IOException, DocumentException {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = new PdfReader("/im/CONTRATO DUPLICADO OFICIO.pdf"); // input PDF
        File f = new File("program.txt");

        // Get the absolute path of file f
        String absolute = f.getAbsolutePath();
        String address = absolute.substring(0,absolute.length()-11);
        String direccion = address.replace("\\", "/");
        System.out.println("address: "+address);
        System.out.println("direccion:"+direccion);
        String cedulaImprimir = txtCedula_DetalleContrato.getText();
        String contratoImprimir = txtNumeroContrato_DetalleContrato.getText();
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(direccion+"CONTRATO DUPLICADO "+contratoImprimir+".pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font



        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(539, 970);   // set x,y position (0,0 is at the bottom left)
            over.showText(contratoImprimir);  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(338, 907);   // set x,y position (0,0 is at the bottom left)
            over.showText(cedulaImprimir);  // set text
            over.endText();


            // draw a red circle
//            over.setRGBColorStroke(0xFF, 0x00, 0x00);
//            over.setLineWidth(5f);
//            over.ellipse(250, 450, 350, 550);
//            over.stroke();
        }

        stamper.close();

        try {

            if ((new File(direccion+"CONTRATO DUPLICADO "+contratoImprimir+".pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler "+direccion+"CONTRATO DUPLICADO "+contratoImprimir+".pdf");
                p.waitFor();

            } else {

                System.out.println("File doesn't exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void generarDocumento() throws IOException, DocumentException {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = new PdfReader("/im/CONTRATO OFICIO.pdf"); // input PDF
        File f = new File("program.txt");

        // Get the absolute path of file f
        String absolute = f.getAbsolutePath();
        String address = absolute.substring(0,absolute.length()-11);
        String direccion = address.replace("\\", "/");
//        System.out.println("address: "+address);
//        System.out.println("direccion:"+direccion);
        String cedulaImprimir = txtCedula_DetalleContrato.getText();
        String contratoImprimir = txtNumeroContrato_DetalleContrato.getText();
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(direccion+"CONTRATO "+contratoImprimir+".pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font



        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(539, 970);   // set x,y position (0,0 is at the bottom left)
            over.showText(contratoImprimir);  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(338, 907);   // set x,y position (0,0 is at the bottom left)
            over.showText(cedulaImprimir);  // set text
            over.endText();


            // draw a red circle
//            over.setRGBColorStroke(0xFF, 0x00, 0x00);
//            over.setLineWidth(5f);
//            over.ellipse(250, 450, 350, 550);
//            over.stroke();
        }

        stamper.close();

        try {

            if ((new File(direccion+"CONTRATO "+contratoImprimir+".pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler "+direccion+"CONTRATO "+contratoImprimir+".pdf");
                p.waitFor();

            } else {

                System.out.println("File doesn't exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void verDetalleTablaClientes(){
        cedulaSeleccionada = Tabla_BusquedaClientes.getSelectionModel().getSelectedItem().getColumnaCedulaCliente().toString();
        revisarHuellayFoto(2);
        llenarDatosDetalleCliente(cedulaSeleccionada);
        mostrarTablaInicialContratos_BusquedaClientes(cedulaSeleccionada);
        anchorDetallesCliente.toFront();
        VBoxanchorDetallesCliente.toFront();
    }
    @FXML
    public void verDetalleCliente(){
        //llenarDatosDetalleCliente(txtCedula_DetalleContrato.getText());
        llenarDatosDetalleCliente(txtCedula_DetalleContrato.getText());
        mostrarTablaInicialContratos_BusquedaClientes(txtCedula_DetalleContrato.getText());
        btnModificarDetalleCliente.toFront();
        //mostrarTablaInicialContratos_BusquedaClientes();

        anchorDetallesCliente.toFront();
        VBoxanchorDetallesCliente.toFront();
        paneBusquedaCliente.toFront();
    }
    @FXML
    public void verDetalleContratos(){
        llenarDatos_DetalleContrato();
        anchorDetallesContrato.toFront();
        btnVerDetallesContratos.setDisable(true);
    }
    @FXML
    public void modificarDetalleCLienteOnClic(){
        txtNombreBusquedaCliente.setEditable(true);
        txtApellidoBusquedaCliente.setEditable(true);
        txtDireccionBusquedaCliente.setEditable(true);
        txtTelefono1BusquedaCliente.setEditable(true);
        txtTelefono2BusquedaCliente.setEditable(true);
        txtCorreoBusquedaCliente.setEditable(true);
        btnGuardarDetalleCliente.toFront();
        btnTomarNuevaFotoDetalleCliente.setDisable(false);
        btnTomarNuevaHuellaDetalleCliente.setDisable(false);
    }
    @FXML
    public void guardarDetalleCLienteOnClic(){
        controlBd.UpdateCliente(
                txtNombreBusquedaCliente.getText(),
                txtApellidoBusquedaCliente.getText(),
                txtDireccionBusquedaCliente.getText(),
                txtTelefono1BusquedaCliente.getText(),
                txtTelefono2BusquedaCliente.getText(),
                txtCorreoBusquedaCliente.getText(),
                cedulaSeleccionada
        );
        txtNombreBusquedaCliente.setEditable(false);
        txtApellidoBusquedaCliente.setEditable(false);
        txtDireccionBusquedaCliente.setEditable(false);
        txtTelefono1BusquedaCliente.setEditable(false);
        txtTelefono2BusquedaCliente.setEditable(false);
        txtCorreoBusquedaCliente.setEditable(false);
        btnTomarNuevaFotoDetalleCliente.setDisable(true);
        btnModificarDetalleCliente.toFront();
        btnTomarNuevaHuellaDetalleCliente.setDisable(true);
    }
    public void onClicTomarNuevaFotoDetallesCliente(){

    }
    @FXML
    public void regresarBusquedaClientes(){
        mostrarTablaInicial_Clientes();
        paneBusquedaCliente.toFront();
        VBoxAnchorTablaClientes.toFront();
        anchorTablaClientes.toFront();
    }

    @FXML
    public void verDetalleContrato(){
        cedulaSeleccionada=txtCedula_DetalleContrato.getText();
        llenarDatos_DetalleContrato(
                TablaContratos_BusquedaClientes.getSelectionModel().getSelectedItem().getNumeroContrato().toString(),
                cedulaSeleccionada);
        anchorDetallesContrato.toFront();
        paneContratos.toFront();
    }

    @FXML
    public void verDetalleContratoFromCliente(){

        cedulaSeleccionada=txtCedulaBusquedaCliente.getText();
        llenarDatos_DetalleContrato(
                TablaContratos_BusquedaClientes.getSelectionModel().getSelectedItem().getNumeroContrato().toString(),
                cedulaSeleccionada);
        anchorDetallesContrato.toFront();
        paneContratos.toFront();
        pantallaActiva = 4;
        btnVerDetalleTablaDetallesClientes.setDisable(true);
    }

    @FXML
    public void OcultarDetallesCliente(){
        anchorDetallesCliente.toBack();
        VBoxanchorDetallesCliente.toBack();
    }

    @FXML
    public void OcultarDetallesContrato(){
        anchorDetallesContrato.toBack();
    }
    public void confirmarNuevoCLiente(){
        txtcedula.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        txtLugarExpedicion.setText("");
        txtdireccion.setText("");
        txtcorreo.setText("");
        txttelefono1.setText("");
        txttelefono2.setText("");
        btnInsertarDatosNuevoCliente.setDisable(false);
        btnFotoNuevoCliente.setDisable(true);
        btnHuellaNuevoCliente.setDisable(true);
        btnBorrarDatosCliente.setDisable(true);
    }


    public void setTxtcedulaNuevaRetroventa(String cedula) {
        this.txtcedulaNuevaRetroventa.setText(cedula);
    }
    //Busqueda de cliente desde la pantalla de nueva retroventa
    @FXML
    private void buscarClientePorHuella_NuevaRetroventa(){
        txtcedulaNuevaRetroventa.setText("");
        Huella_Identificar huella= null;
        try {
            huella = new Huella_Identificar(this);
            huella.setOperacion(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        huella.setVisible(true);
    }
    @FXML
    public void verFoto_NuevaRetroventa(){
        byte[] imagen = controlBd.ConsultarFotoCliente(txtcedulaNuevaRetroventa.getText());
        if(imagen!=null) {
            mostrarFoto(imagen, imgViewFotoNuevaRetroveta);
            aPimgClienteNuevaRetroventa.setOpacity(1);
            aPimgClienteNuevaRetroventa.toFront();
        }else{
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Foto no disponible");
            alert1.setTitle("Advertencia");
            alert1.setHeaderText(null);
            alert1.showAndWait();
        }
    }

    @FXML
    public void closeFotoNuevaRetroventa(){
        aPimgClienteNuevaRetroventa.setOpacity(0);
        aPimgClienteNuevaRetroventa.toBack();
    }

    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    public void mostrarFoto(byte[] imagen, ImageView panelImagen) {
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = SwingFXUtils.toFXImage(bImage, null);
            panelImagen.setImage(image);
        } catch (NullPointerException e) {
            System.out.print(e.toString());
            Image image = new Image(getClass().getClassLoader().getResourceAsStream("img/Imagen_no_disponible.png"));
            panelImagen.setImage(image);

        }
    }

    public ObservableList<String> getSubElectrodomesticos() {
        return subElectrodomesticos;
    }

    public void setSubElectrodomesticos(ObservableList<String> subElectrodomesticos) {
        this.subElectrodomesticos = subElectrodomesticos;
    }

    public ObservableList<String> getSubOro() {
        return subOro;
    }

    public void setSubOro(ObservableList<String> subOro) {
        this.subOro = subOro;
    }

    public ObservableList<String> getCategorias() {
        return categorias;
    }

    public ControlBd getControlBd() {
        return controlBd;
    }

    public SQL_Sentencias getSentencias() {
        return sen;
    }


    public void setControlBd(ControlBd controlBd) {
        this.controlBd = controlBd;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SQL_Sentencias getSen() {
        return sen;
    }

    public void setSen(SQL_Sentencias sen) {
        this.sen = sen;
    }

    public TextField getTxtSubcategoria_DetalleContrato() {
        return txtSubcategoria_DetalleContrato;
    }

    public void setTxtSubcategoria_DetalleContrato(TextField txtSubcategoria_DetalleContrato) {
        this.txtSubcategoria_DetalleContrato = txtSubcategoria_DetalleContrato;
    }
}
