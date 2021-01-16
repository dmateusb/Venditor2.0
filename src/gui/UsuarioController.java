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
import javafx.scene.control.cell.PropertyValueFactory;
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

import java.util.ArrayList;

public class UsuarioController {

    private HomeController homeController;
    ArrayList<Usuario> arrayUsuario = new ArrayList();
    public ObservableList<Usuario> listaUsuarios;


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
}
