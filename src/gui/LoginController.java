package gui;

import SQL.ControlBd;
import SQL.SQL_Conexion;
import SQL.SQL_Sentencias;
import animatefx.animation.FadeIn;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.Usuario;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private ControlBd controlBd;
    private Usuario currentUser;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField password;

    @FXML
    private Button ingreso;

    @FXML
    private Button closeButton;

    SQL_Conexion con;
    SQL_Sentencias sen;
    //Método para obtener el usuario para loggear en la base de datos
    public String getUser() {
        return this.usuario.getText();
    }

    //Método para obtener el password para loggear en la base de datos
    public String getPassword() {
        return this.password.getText();
    }

    //Método para que de loggear cuando se oprime enter y se está sobre el campo "Password"
    @FXML
    public void onEnter(ActionEvent ae) throws Exception {
        logear();
    }

    //Método para cerrar la ventana "Login" pero que continúe la ejecución del programa
    @FXML
    public void closeButtonAction() {
        Stage stage1 = (Stage) closeButton.getScene().getWindow();
        stage1.close();
    }

    //Método para detener la ejecución del programa completamente
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    //Método para abrir el Home y que tenga el tamaño máximo de display
    public void abrir(Stage stage) throws Exception {
        stage.initStyle(StageStyle.DECORATED);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
        Parent root = loader.load();
        HomeController homeController= loader.getController();
        homeController.setControlBd(controlBd);
        homeController.setUsuario(currentUser);
        homeController.setSen(sen);
        homeController.inicalizar();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/im/favicon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        //Se mide la pantalla
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
            }
        });
        //Se le da el tamaño de los límites encontrados por el objeto "bounds"
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        //stage.getIcons().add(new Image(Home.getResourceAsStream("/imagenes/Iconos/FAVICON.png")));
        stage.show();
        new FadeIn(root).play();
    }

    //Método para logear al usuario en la Base de Datos.
    public void logear() throws Exception {
        con = new SQL_Conexion(usuario.getText(), password.getText());
        //Verificación de que los campos de usuario y contraseña no estén vacíos
        if (usuario.getText().length() > 0 && password.getText().length() > -1) {


            //Si el usuario se puede conectar a la BD entonces puede también logear en Venditor
            if (con.SQL_Conexion(usuario.getText(), password.getText()) == true ) {
                sen= new SQL_Sentencias(this.usuario.getText(),this.password.getText());
                sen.setCon(con);
                controlBd= new ControlBd(this.usuario.getText(),this.password.getText());
                controlBd.setSen(sen);
                controlBd.setCon(con);
                Object[][] resultado = controlBd.consultarUsuario(this.usuario.getText());
                if(this.usuario.getText().equals(resultado[0][0].toString())
                        && this.password.getText().equals(resultado[0][1].toString()))
                    currentUser = new Usuario(this.usuario.getText(),this.password.getText(), (String) resultado[0][2]);
                Stage stage = new Stage();
                abrir(stage);
                closeButtonAction();
            } else {
                //Si el usuario no tiene permiso para ingresar a la base de datos tampoco
                //podrá ingresa a Venditor, por lo que se envía una alerta avisando que
                //las credenciales de acceso no son corrrectas.
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("EL usuario y/o contraseña no concuerdan, "
                        + "por favor ingrese los datos nuevamente.");
                alert1.setTitle("Error al ingresar");
                alert1.setHeaderText(null);
                alert1.showAndWait();
                //System.out.println("El usuario contraseña no concuerdan");
                usuario.requestFocus();
                usuario.selectAll();
                password.setText("");
            }
        } else {
            //Si los campos de usuario y password están vacíos se envía una alerta
            //notificándolo
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Por favor escribe tu usuario y contraseña.");
            alert2.setTitle("Información incompleta");
            alert2.setHeaderText(null);
            alert2.showAndWait();

            usuario.requestFocus();
            usuario.selectAll();
            password.setText("");
        }
    }

    //Método para que el campo "usuario" obtenga el foco y se pueda escribir en él
    public void focus() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usuario.requestFocus();
            }
        });
        usuario.selectAll();
        password.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Se inicializa la ventana con el cursor sobre el campo "usuario"
        focus();
    }


    public ControlBd getControlBd() {
        return controlBd;
    }

    public void setControlBd(ControlBd controlBd) {
        this.controlBd = controlBd;
    }
}
