package gui;

import SQL.ControlBd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Cliente;
import logic.Contrato;
import logic.Home;
import logic.Usuario;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioController {

    private HomeController homeController;
    ArrayList<Usuario> arrayUsuario = new ArrayList();
    public ObservableList<Usuario> listaUsuarios;
    public static final String OK = "OK";
    public static final String CANCEL = "Cancelar";

    @FXML
    private TextField txtBusquedaUsuario;

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TableColumn<Usuario, String> columnaUsername;

    @FXML
    private TableColumn<Usuario, String> columnaRol;

    @FXML
    private Button btnNuevoCliente;

    @FXML
    private Button btnEliminar;
    
    public void inicializar(){
        btnEliminar.setDisable(true);
        inicializarTabla();
    }

    public void inicializarTabla() {
        ControlBd controlBd= homeController.getControlBd();
        Object[][] usuarios = controlBd.consultarUsuario();
        for(int i=0;i<usuarios.length;i++){
                Usuario usuario= new Usuario(usuarios[i][0].toString(),usuarios[i][1].toString(),usuarios[i][2].toString());
                arrayUsuario.add(usuario);

        }

        listaUsuarios = FXCollections.observableArrayList(arrayUsuario);

        columnaUsername.setCellValueFactory(new PropertyValueFactory<Usuario,String>("username"));
        columnaRol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("rol"));
        tablaUsuarios.setItems(listaUsuarios);
        listaUsuarios.removeAll();
        arrayUsuario.removeAll(listaUsuarios);
    }

    public void buscarUsuario() {
        ControlBd controlBd= homeController.getControlBd();
        Object[][] usuarios = controlBd.consultarUsuarioPorUsername(txtBusquedaUsuario.getText());
        for(int i=0;i<usuarios.length;i++){
            if  (usuarios[i][0]!=null && usuarios[i][1]!=null && usuarios[i][2]!=null){
                Usuario usuario = new Usuario(usuarios[i][0].toString(), usuarios[i][1].toString(), usuarios[i][2].toString());
                arrayUsuario.add(usuario);
            }
        }

        listaUsuarios = FXCollections.observableArrayList(arrayUsuario);

        columnaUsername.setCellValueFactory(new PropertyValueFactory<Usuario,String>("username"));
        columnaRol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("rol"));
        tablaUsuarios.setItems(listaUsuarios);
        listaUsuarios.removeAll();
        arrayUsuario.removeAll(listaUsuarios);
    }

    @FXML
    public void onActionEliminarUsuario(ActionEvent event) {
        if(mostrarConfirmacion("Confirmación","Esta seguro que desea eliminar el usuario " +
                "seleccionado").equals("OK")){
            String username= tablaUsuarios.getSelectionModel().getSelectedItem().getUsername();
            if(homeController.getControlBd().eliminarUsuarioBd(username)){
                mostrarAlerta("Exito","Usuario eliminado");
                inicializarTabla();
            }else{
                mostrarAlerta("Error","Error al eliminar el usuario de la base de datos");
            }
        }

    }

    @FXML
    public void onActionNuevoUsuario(ActionEvent event) {
        abrirPopUpNuevoUsuario();
    }

    private void abrirPopUpNuevoUsuario() {
        Stage popUpWindow= new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.initStyle(StageStyle.UTILITY);
        popUpWindow.setTitle("Nuevo Usuario");
        VBox vBox= new VBox(10);
        vBox.setPadding(new Insets(10,10,10,10));
        TextField txtNombreUsuario= new TextField();
        txtNombreUsuario.setPromptText("Nombre de usuario");
        txtNombreUsuario.setText("Nombre de usuario");
        PasswordField txtContraseña1= new PasswordField();
        txtContraseña1.setPromptText("Contraseña");
        PasswordField txtContraseña2= new PasswordField();
        txtContraseña2.setPromptText("Confirmar contraseña");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Vendedor","Admin"
                );
        ComboBox<String> cBoxRol= new ComboBox<>(options);
        cBoxRol.setValue("Vendedor");
        HBox hBox= new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        Label label= new Label("Rol: ");
        hBox.getChildren().addAll(label,cBoxRol);
        Button btnConfirmar= new Button("Confirmar");
        vBox.requestFocus();
        btnConfirmar.setOnAction(event -> {
            if(txtContraseña1.getText().equals(txtContraseña2.getText()) && !txtContraseña1.getText().equals("")){
                if(homeController.getControlBd().crearNuevoUsuarioBd(txtNombreUsuario.getText(),
                        txtContraseña1.getText(),cBoxRol.getValue().toString().toLowerCase())){
                    mostrarAlerta("Ëxito","Usuario ingresado correctamente");
                    popUpWindow.close();
                    inicializarTabla();
                }else{
                    mostrarAlerta("Error al crear nuevo usuario","Error en la conexión con la base de datos");
                }
            }else{
                mostrarAlerta("Error al crear nuevo usuario","Las contraseñas deben coincidir");
            }
        });
        vBox.getChildren().addAll(txtNombreUsuario,txtContraseña1,txtContraseña2,hBox,btnConfirmar);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void onClicTablaUsuarios(MouseEvent event) {
        btnEliminar.setDisable(false);
    }

    @FXML
    public void onKeyReleasedNombreUsuario(KeyEvent event) {
        buscarUsuario();
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
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
}
