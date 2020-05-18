package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import animatefx.animation.FadeIn;
import com.github.sarxos.webcam.Webcam;
import com.twilio.Twilio;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author david
 */

public class HomeController implements Initializable {

    private String usuario;
    private String pass;
    private ControlBd controlBd = new ControlBd("root", "");
    @FXML private ImageView imageViewBusquedaCliente;
    @FXML private ImageView imageViewDetalleContrato;
    @FXML private LineChart graficaHome;
    @FXML private MenuItem btnnuevocliente;
    @FXML private MenuItem btnbusquedacliente;
    @FXML private AnchorPane pnCrearUsuario;
    @FXML private AnchorPane anchorTablaClientes;
    @FXML private AnchorPane anchorDetallesCliente;
    @FXML private AnchorPane anchorDetallesContrato;
    @FXML private AnchorPane anchorTablaContratos;
    @FXML private Button btnFotoNuevoCliente;
    @FXML private Button btnCrearRetroventa;
    @FXML private Button btnConfirmarNuevoCliente;
    @FXML private Button btnHuellaNuevoCliente;
    @FXML private Button btnInsertarDatosNuevoCliente;
    @FXML private Button btnCambiarPorcentaje;
    @FXML private Button btnVerDetalleTablaClientes;
    @FXML private Button btnVerDetallesContratos;
    @FXML private Button btnVerFoto;

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
    @FXML private TextField txtTelefono1BusquedaCliente;
    @FXML private TextField txtTelefono2BusquedaCliente;
    @FXML private TextField txtCorreoBusquedaCliente;
    @FXML private TextField txtBusquedaCliente;
    @FXML TextField txtCedulaConfirmacionNuevoCLiente;
    @FXML private TextField txtnombre;
    @FXML private TextField txttelefono1;
    @FXML private TextField txtcorreo;
    @FXML private TextField txtdireccion;
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
    @FXML private TableColumn<Contrato,String> descripcion;
    @FXML private TableColumn<Contrato,String> vencimiento;
    @FXML private TableColumn<Contrato,String> columnaContratoBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaArticuloBusquedaClientes ;
    @FXML private TableColumn<Contrato,String> columnaPorcentajeBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaValorBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaRenovacionesBusquedaClientes;
    @FXML private TableColumn<Contrato,String> columnaEstadoBusquedaClientes;

    @FXML
    private TableColumn<Cliente, Integer> ColumnaCedulaCliente;

    @FXML
    private TableColumn<Cliente, String> ColumnaNombreCliente;

    @FXML
    private TableColumn<Cliente, String> ColumnaApellidosCliente;

    @FXML
    private TableColumn<Cliente, String> ColumnaDireccionCliente;

    @FXML
    private TableColumn<Cliente, String> ColumnaTelefono1Cliente;

    @FXML
    private TableColumn<Cliente, String> ColumnaTelefono2Cliente;

    @FXML
    private TableColumn<Cliente, String> ColumnaCorreoCliente;
    @FXML private Cliente clienteEscogidoTabla;
    @FXML private Contrato contratoEscogidoTabla;


    SQL_Sentencias bd = new SQL_Sentencias("root", "");
    ControlBd control = new ControlBd("root", "");



    //VBoxes - Son los que se van pasando al frente cada vez que el usuario
    //selecciona alguna opción del menú
    @FXML private ComboBox<String> comboCategoria;
    @FXML private ComboBox<String> comboSubcategoria;
    @FXML private ComboBox<String> comboEstados;
    ObservableList<String> categorias = FXCollections.observableArrayList("Oro","Electrodomésticos");
    ObservableList<String> estados = FXCollections.observableArrayList("Todos","Activos","Retractados","Vencidos"); //Estados de los contratos
    ObservableList<String> subElectrodomesticos = FXCollections.observableArrayList("Cámaras","Televisores","Portatil","Videojuegos","Sonido","Hogar","Instrumentos",
            "Deportes", "Herramientas","Industria","Otros");
    ObservableList<String> subOro = FXCollections.observableArrayList("Oro");
    @FXML private Spinner SpinnerPorcentaje;
    @FXML private VBox vboxnuevocliente;
    @FXML private VBox vboxHome;
    @FXML private VBox vboxNuevaRetroventa;
    @FXML private VBox vboxBusquedaCliente;
    @FXML private VBox vboxContratos;
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
    @FXML private MenuItem btnlibrocaja;
    @FXML private MenuItem btncorreciones;
    @FXML private MenuItem btnbackup;
    @FXML private MenuItem btncaja;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btnCambiarCliente;
    @FXML private Button btnBorrarTodoNuevaRetro;

    Contrato contrato;
    private Integer cedulaContrato;
    ArrayList<Contrato> arrayContratos = new ArrayList();
    ArrayList<Cliente> arrayClientes = new ArrayList();

    public ObservableList<Contrato> lista;
    public ObservableList<Cliente> listaClientes;

    @FXML
    private ObservableList<String> listaAccionesUsuarios = FXCollections.observableArrayList(
            "Crear Usuario", "Editar Usuario", "Eliminar Usuario"
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtcedula.textProperty().addListener((observable, oldValue, newValue) -> {
//            Aca deben ir los requisitos de todos los valores
            if (txtcedula.getText().length() != 0) {
//                btnFotoNuevoCliente.setDisable(false);
//                btnHuellaNuevoCliente.setDisable(false);
            } else {

//                btnFotoNuevoCliente.setDisable(true);
//                btnHuellaNuevoCliente.setDisable(true);
            }
        });
        SpinnerPorcentaje.setEditable(false);
        SpinnerPorcentaje.setDisable(true);
        comboCategoria.setItems(categorias);
        comboEstados.setItems(estados);
        comboEstados.getSelectionModel().select(0);
        mostrarTablaInicial_Clientes();
        graficar();
        InteresOro();
        //mostrarTablaInicial();
        vboxHome.toFront();
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
    public void buscarClienteNuevaRetro() {
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
        }
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
        txtcedulaNuevaRetroventa.setText("");
        txtnombreNuevaRetroventa.setText("");
        txtapellidoNuevaRetroventa.setText("");
        txtdireccionNuevaRetroventa.setText("");
        txtcorreoNuevaRetroventa.setText("");
        txttelefono1NuevaRetroventa.setText("");
        txttelefono2NuevaRetroventa.setText("");
        txtcedulaNuevaRetroventa.setEditable(true);


        btnCambiarCliente.setDisable(true);
        btnVerFoto.setDisable(true);
    }

    public void recibirParametros(String user, String password) throws IOException {
        this.usuario = usuario;
        this.pass = pass;
    }

    @FXML
    private void home() {
        vboxHome.toFront();
    }

    @FXML
    public void onEnterCedulaNuevaRetroventa(ActionEvent ae) throws Exception {
        buscarClienteNuevaRetro();
    }

    @FXML
    private void enviarMensaje() {
        String ACCOUNT_SID = "ACb97798b04b2e86fa5cadabae8600fa7e";
        String AUTH_TOKEN = "b70b68a8ffa00dabe2d2429fb27b1832";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        IncomingPhoneNumber incomingPhoneNumber = IncomingPhoneNumber.creator(
                new com.twilio.type.PhoneNumber("+15005550006"))
                .setVoiceUrl(URI.create("http://demo.twilio.com/docs/voice.xml"))
                .create();

        System.out.println(incomingPhoneNumber.getSid());

//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+573124038290"),
//                new com.twilio.type.PhoneNumber("+15005550006"),
//                "Hi there!")
//                .create();
//
//        System.out.println(message.getSid());
    }

    @FXML
    public void handleButtonAction() {

    }

    @FXML
    public void botonesHandle(ActionEvent event) {
        if (event.getSource() == btnnuevaretro && pantallaActiva != 1) {
            vboxNuevaRetroventa.toFront();
            pantallaActiva = 1;
            new FadeIn(vboxNuevaRetroventa).play();
        } else if (event.getSource() == btnventa) {

        } else if (event.getSource() == btnnuevaretro && pantallaActiva != 3) {
            pantallaActiva = 3;
            vboxNuevaRetroventa.toFront();
            new FadeIn(vboxNuevaRetroventa).play();
        } else if (event.getSource() == btncontrato ) {
            mostrarTablaInicial();
            anchorTablaContratos.toFront();
            vboxContratos.toFront();
            if(pantallaActiva != 4){
                new FadeIn(vboxContratos).play();
            }
            pantallaActiva = 4;

        } else if (event.getSource() == btncontrato) {
            anchorDetallesContrato.toBack();
        } else if (event.getSource() == btnclientehistorial) {

        } else if (event.getSource() == btnreportes) {

        } else if (event.getSource() == btnusuarios) {

        } else if (event.getSource() == btnbalance) {

        } else if (event.getSource() == btnaportesoretiro) {

        } else if (event.getSource() == btnestadistica) {

        } else if (event.getSource() == btnhistorialcorreciones) {

        } else if (event.getSource() == btnlibrocaja) {

        } else if (event.getSource() == btncorreciones) {

        } else if (event.getSource() == btnbackup) {

        } else if (event.getSource() == btncaja) {

        } else if (event.getSource() == btnnuevocliente && pantallaActiva != 17) {
            pantallaActiva = 17;
            vboxnuevocliente.toFront();
            new FadeIn(vboxnuevocliente).play();
        } else if (event.getSource() == btnbusquedacliente && pantallaActiva != 18) {
            pantallaActiva = 18;
            vboxBusquedaCliente.toFront();
            anchorTablaClientes.toFront();
            new FadeIn(vboxBusquedaCliente).play();
        }
    }

    @FXML
    public void crearUsuario(ActionEvent event) throws SQLException, IOException {
        if (txtcedula.getText().equals(txtCedulaConfirmacionNuevoCLiente.getText())) {
            byte[] data = null;
            SQL_Sentencias bd = new SQL_Sentencias("root", "");
            ControlBd control = new ControlBd("root", "");

            Boolean isCreado = bd.InsertarNuevoCliente((Integer.parseInt(txtcedula.getText())),
                    txtnombre.getText(), txtapellido.getText(), txtdireccion.getText(),
                    txttelefono1.getText(), txttelefono2.getText(), txtcorreo.getText(), bd.getUser());
            if (isCreado) {
                btnHuellaNuevoCliente.setDisable(false);
                btnFotoNuevoCliente.setDisable(false);
                btnInsertarDatosNuevoCliente.setDisable(true);
            }

        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Favor revise el número de cédula");
            alert1.setTitle("Confirmar número cédula");
            alert1.setHeaderText(null);
            alert1.showAndWait();
        }

    }

    @FXML protected void cambioEstadoContratos() {
        txtCedulaContratos.setText("");
        txtNumeroContrato.setText("");
        String output = (String) comboEstados.getValue();
        if(output == "Vencidos"){
            Object[][] Contratos = control.ConsultarContratosVencidos();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);
        }else if(output == "Activos"){
            Object[][] Contratos = control.ConsultarContratosActivos();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);

        }else if(output == "Retractados"){
            Object[][] Contratos = control.ConsultarContratosRetractados();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);

        }else{
            Object[][] Contratos = control.ConsultarContrato();
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }

            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
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
            InteresElectrodomesticos();
            txtPesoArticulo.setText("");
            txtPesoArticulo.setDisable(true);
        }
    }

    @FXML protected String InsertarNuevoArticulo() throws SQLException{
        String IdArticulo;
        SQL_Sentencias sen = new SQL_Sentencias("root", "");
        if(comboCategoria.getValue().toString() =="Oro"){
            IdArticulo = sen.InsertarNuevoArticulo(comboCategoria.getValue().toString(),comboSubcategoria.getValue().toString(),txtDescripcionArticulo.getText(),
                    Double.parseDouble(txtPesoArticulo.getText()),Integer.parseInt(txtValorArticulo.getText()),sen.getUser());
        }else{
            IdArticulo = sen.InsertarNuevoArticulo(comboCategoria.getValue().toString(),comboSubcategoria.getValue().toString(),txtDescripcionArticulo.getText(),
                    Integer.parseInt(txtValorArticulo.getText()),sen.getUser());
        }
        return IdArticulo;
    }

    @FXML protected void InsertarNuevoContrato() throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now().plusMonths(3);
        String vencimiento = now.toString();
        //System.out.println(dtf.format(now));
        String ArticuloId = InsertarNuevoArticulo();
        SQL_Sentencias sen = new SQL_Sentencias("root", "");
        sen.InsertarNuevoContrato(Integer.parseInt(txtcedulaNuevaRetroventa.getText().toString()),ArticuloId,Integer.parseInt(txtValorArticulo.getText().toString()),
                                Double.parseDouble(SpinnerPorcentaje.getValue().toString()),vencimiento,sen.getUser());
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
    public void tomarFotografia(ActionEvent event) {
        Webcam cam = Webcam.getDefault();
        Stack<String> nombrescamaras = new Stack<>();
        List<Webcam> camaras = Webcam.getWebcams();
        final Object lock = new Object();
        for (Webcam webcam : camaras) {
            nombrescamaras.add(webcam.getName());
        }
        Combo combo = new Combo(lock, nombrescamaras);
        Detener detener = new Detener(lock);
        detener.setCedula(txtcedula.getText());

        Thread t2 = new Thread(detener);
        Thread t1 = new Thread(combo);

        t2.start();
        t1.start();

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
        SpinnerPorcentaje.setDisable(false);
        btnCambiarPorcentaje.setDisable(true);
    }

    public void llenarDatosDetalleCliente(String cedula){
        Object[][] informacionCliente = controlBd.GetClienteNuevoContrato(cedula);
        txtNombreBusquedaCliente.setText(informacionCliente[0][1].toString());
        txtApellidoBusquedaCliente.setText(informacionCliente[0][2].toString());
        txtDireccionBusquedaCliente.setText(informacionCliente[0][3].toString());
        txtTelefono1BusquedaCliente.setText(informacionCliente[0][4].toString());
        txtTelefono2BusquedaCliente.setText(informacionCliente[0][5].toString());
        txtCorreoBusquedaCliente.setText(informacionCliente[0][6].toString());
        byte[] imagen = controlBd.ConsultarFotoVisitante(cedula);
        mostrarFoto(imagen,imageViewBusquedaCliente);
    }

    public void llenarDatos_DetalleContrato(String numeroContrato, String cedula){
        Object[][] informacionCliente = controlBd.GetClienteNuevoContrato(cedula);
        txtCedula_DetalleContrato.setText(cedula);
        txtNombre_DetalleContrato.setText(informacionCliente[0][1].toString());
        txtApellidos_DetalleContrato.setText(informacionCliente[0][2].toString());
        txtDireccion_DetalleContrato.setText(informacionCliente[0][3].toString());
        txtTelefono1_DetalleContrato.setText(informacionCliente[0][4].toString());
        txtTelefono2_DetalleContrato.setText(informacionCliente[0][5].toString());
        txtCorreo_DetalleContrato.setText(informacionCliente[0][6].toString());
        txtPerfil_DetalleContrato.setText(informacionCliente[0][8].toString());
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

        byte[] imagen = controlBd.ConsultarFotoVisitante(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
        mostrarFoto(imagen,imageViewDetalleContrato);
    }




    public void llenarDatos_DetalleContrato(){
        Object[][] informacionCliente = controlBd.GetClienteNuevoContrato(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
        txtCedula_DetalleContrato.setText(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
        txtNombre_DetalleContrato.setText(informacionCliente[0][1].toString());
        txtApellidos_DetalleContrato.setText(informacionCliente[0][2].toString());
        txtDireccion_DetalleContrato.setText(informacionCliente[0][3].toString());
        txtTelefono1_DetalleContrato.setText(informacionCliente[0][4].toString());
        txtTelefono2_DetalleContrato.setText(informacionCliente[0][5].toString());
        txtCorreo_DetalleContrato.setText(informacionCliente[0][6].toString());
        txtPerfil_DetalleContrato.setText(informacionCliente[0][8].toString());
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

        byte[] imagen = controlBd.ConsultarFotoVisitante(Integer.toString(TablaContratos.getSelectionModel().getSelectedItem().getCedula()));
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
        byte[] imagen = controlBd.ConsultarFotoVisitante(txtBusquedaCliente.getText());
        mostrarFoto(imagen,imageViewDetalleContrato);

    }

    public void setTxtBusquedaCliente(String txtBusquedaCliente) {
        this.txtBusquedaCliente.setText(txtBusquedaCliente);
    }

    public void mostrarTablaInicial(){

        Object[][] Contratos = control.ConsultarContrato();
        for(int i=0;i<Contratos.length;i++){
            if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                contrato.setDescripcion(articulo[0][0].toString());
                Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                contrato.setNombre(clientes[0][0].toString());
                contrato.setApellidos(clientes[0][1].toString());
                arrayContratos.add(contrato);
            }
        }

        lista = FXCollections.observableArrayList(arrayContratos);
        numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
        cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
        vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
        TablaContratos.setItems(lista);
        lista.removeAll();
        arrayContratos.removeAll(lista);
    }


    //Mostrar la tabla filtrada por cédula
    public void mostrarTablaCedula(){
        txtNumeroContrato.setText("");
        String output = (String) comboEstados.getValue();
        if(output == "Vencidos"){
            Object[][] Contratos = control.ConsultarContratosVencidosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);
        }else if(output == "Activos"){
            Object[][] Contratos = control.ConsultarContratosActivosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);

        }else if(output == "Retractados"){
            Object[][] Contratos = control.ConsultarContratosRetractadosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }
            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);

        }else{
            Object[][] Contratos = control.ConsultarContratosLikeCedula(txtCedulaContratos.getText());
            for(int i=0;i<Contratos.length;i++){
                if (Contratos[i][0] != null && Contratos[i][1] != null && Contratos[i][2] != null) {
                    Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                    Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                    contrato.setDescripcion(articulo[0][0].toString());
                    Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
                    contrato.setNombre(clientes[0][0].toString());
                    contrato.setApellidos(clientes[0][1].toString());
                    arrayContratos.add(contrato);
                }
            }

            lista = FXCollections.observableArrayList(arrayContratos);
            numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
            cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
            nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
            apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
            descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
            vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
            TablaContratos.setItems(lista);
            lista.removeAll();
            arrayContratos.removeAll(lista);
        }
    }



    //Mostrar la tabla Contratos filtrada por número de contrato
    public void mostrarTablaContrato(){
 Object[][] ContratosLikeContrato = control.ConsultarContratoLikeContrato(txtNumeroContrato.getText().toString());
        for (int i = 0; i < ContratosLikeContrato.length; i++) {
            if (ContratosLikeContrato[i][0] != null && ContratosLikeContrato[i][1] != null && ContratosLikeContrato[i][2] != null) {
                Contrato contrato = new Contrato(ContratosLikeContrato[i][0].toString(), Integer.parseInt(ContratosLikeContrato[i][1].toString()), ContratosLikeContrato[i][3].toString().substring(0, 10));
                Object[][] articulo = control.ConsultarDescripcionArticulo(ContratosLikeContrato[i][2].toString());
                contrato.setDescripcion(articulo[0][0].toString());
                Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(ContratosLikeContrato[i][1].toString()));
                contrato.setNombre(clientes[0][0].toString());
                contrato.setApellidos(clientes[0][1].toString());
                arrayContratos.add(contrato);
            }
        }
        lista = FXCollections.observableArrayList(arrayContratos);
        numeroContrato.setCellValueFactory(new PropertyValueFactory<Contrato,String>("numeroContrato"));
        cedula.setCellValueFactory(new PropertyValueFactory<Contrato,Integer>("cedula"));
        nombre.setCellValueFactory(new PropertyValueFactory<Contrato,String>("nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<Contrato,String>("apellidos"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Contrato,String>("descripcion"));
        vencimiento.setCellValueFactory(new PropertyValueFactory<Contrato,String>("vencimiento"));
        TablaContratos.setItems(lista);
        lista.removeAll();
        arrayContratos.removeAll(lista);
    }

    public void mostrarTablaInicial_Clientes(){

        Object[][] Clientes = control.ConsultarCliente();
        for(int i=0;i<Clientes.length;i++){
            if (Clientes[i][0] != null && Clientes[i][1] != null && Clientes[i][2] != null&& Clientes[i][3] != null
                    && Clientes[i][4] != null&& Clientes[i][5] != null&& Clientes[i][6] != null
                    && Clientes[i][7] != null&& Clientes[i][8] != null&& Clientes[i][9] != null) {
                Cliente cliente= new Cliente(Integer.parseInt(Clientes[i][0].toString()),Clientes[i][1].toString(),
                        Clientes[i][2].toString(),Clientes[i][3].toString(),Clientes[i][4].toString(),
                        Clientes[i][5].toString(),Clientes[i][6].toString(),Clientes[i][7].toString(),Clientes[i][8].toString()
                        ,Clientes[i][9].toString());
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
        Tabla_BusquedaClientes.setItems(listaClientes);
        listaClientes.removeAll();
        arrayClientes.removeAll(listaClientes);
    }

    public void mostrarTablaClientesPorCedula(){
        Object[][] ClientesLikeCedula = control.ConsultarClientesLikeCedula(txtBusquedaCliente.getText());
        for (int i = 0; i < ClientesLikeCedula.length; i++) {
            if (ClientesLikeCedula[i][0] != null && ClientesLikeCedula[i][1] != null && ClientesLikeCedula[i][2] != null&& ClientesLikeCedula[i][3] != null
                    && ClientesLikeCedula[i][4] != null&& ClientesLikeCedula[i][5] != null&& ClientesLikeCedula[i][6] != null
                    && ClientesLikeCedula[i][7] != null&& ClientesLikeCedula[i][8] != null&& ClientesLikeCedula[i][9] != null) {
                Cliente cliente= new Cliente(Integer.parseInt(ClientesLikeCedula[i][0].toString()),ClientesLikeCedula[i][1].toString(),
                        ClientesLikeCedula[i][2].toString(),ClientesLikeCedula[i][3].toString(),ClientesLikeCedula[i][4].toString(),
                        ClientesLikeCedula[i][5].toString(),ClientesLikeCedula[i][6].toString(),ClientesLikeCedula[i][7].toString(),ClientesLikeCedula[i][8].toString()
                        ,ClientesLikeCedula[i][9].toString());
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
        Tabla_BusquedaClientes.setItems(listaClientes);
        listaClientes.removeAll();
        arrayClientes.removeAll(listaClientes);
    }
    //Mostrar la tabla de contratos de un determinado Cliete
    public void mostrarTablaInicialContratos_BusquedaClientes(String cedula){
        Object[][] Contratos = control.ConsultarContratosLikeCedula(cedula);
        for(int i=0;i<Contratos.length-1;i++){
            if (Contratos[i][0] != null && Contratos[i][2] != null) {
                Contrato contrato = new Contrato(Contratos[i][0].toString(), Integer.parseInt(Contratos[i][1].toString()), Contratos[i][3].toString().substring(0, 10));
                contrato.setValor(Contratos[i][4].toString());
                contrato.setPorcentaje(Contratos[i][5].toString());
                contrato.setRenovaciones(Contratos[i][6].toString());
                contrato.setEstado(Contratos[i][7].toString());
                Object[][] articulo = control.ConsultarDescripcionArticulo(Contratos[i][2].toString());
                contrato.setDescripcion(articulo[0][0].toString());
                Object[][] clientes = control.ConsultarNombresCliente(Integer.parseInt(Contratos[i][1].toString()));
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
    @FXML
    public void onClicTablaClientes(){
        clienteEscogidoTabla=Tabla_BusquedaClientes.getSelectionModel().getSelectedItem();
        if(clienteEscogidoTabla!=null){
            btnVerDetalleTablaClientes.setDisable(false);
        }
    }

    @FXML
    public void onClicTablaContratos(){
        contratoEscogidoTabla=TablaContratos.getSelectionModel().getSelectedItem();
        if(contratoEscogidoTabla!=null){
            btnVerDetallesContratos.setDisable(false);
        }
    }

    @FXML
    public void verDetalleTablaClientes(){
        llenarDatosDetalleCliente(Tabla_BusquedaClientes.getSelectionModel().getSelectedItem().getColumnaCedulaCliente().toString());
        mostrarTablaInicialContratos_BusquedaClientes(Tabla_BusquedaClientes.getSelectionModel().getSelectedItem().getColumnaCedulaCliente().toString());
        anchorDetallesCliente.toFront();
    }
    @FXML
    public void verDetalleCliente(){
        llenarDatosDetalleCliente(txtCedula_DetalleContrato.getText());
        //mostrarTablaInicialContratos_BusquedaClientes();

        anchorDetallesCliente.toFront();
        vboxBusquedaCliente.toFront();
    }
    @FXML
    public void verDetalleContratos(){
        llenarDatos_DetalleContrato();
        anchorDetallesContrato.toFront();
    }
    @FXML
    public void regresarBusquedaClientes(){
        vboxBusquedaCliente.toFront();
        anchorTablaClientes.toFront();
    }

    @FXML
    public void verDetalleContrato(){
        llenarDatos_DetalleContrato(TablaContratos_BusquedaClientes.getSelectionModel().getSelectedItem().getNumeroContrato().toString(),
                Tabla_BusquedaClientes.getSelectionModel().getSelectedItem().getColumnaCedulaCliente().toString());
        anchorDetallesContrato.toFront();
        vboxContratos.toFront();
    }

    @FXML
    public void OcultarDetallesCliente(){
        anchorDetallesCliente.toBack();
    }

    @FXML
    public void OcultarDetallesContrato(){
        anchorDetallesContrato.toBack();
    }
    public void confirmarNuevoCLiente(){
        txtcedula.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        txtCedulaConfirmacionNuevoCLiente.setText("");
        txtdireccion.setText("");
        txtcorreo.setText("");
        txttelefono1.setText("");
        txttelefono2.setText("");
        btnInsertarDatosNuevoCliente.setDisable(false);
        btnFotoNuevoCliente.setDisable(true);
        btnHuellaNuevoCliente.setDisable(true);
        btnConfirmarNuevoCliente.setDisable(true);
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
        byte[] imagen = controlBd.ConsultarFotoVisitante(txtcedulaNuevaRetroventa.getText());
       // mostrarfoto(imagen);

    }

    public void mostrarFoto(byte[] imagen, ImageView panelImagen) {
        ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bImage, null);
        panelImagen.setImage(image);
    }
}
