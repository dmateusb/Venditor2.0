package logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import SQL.ControlBd;
import SQL.SQL_Conexion;
import SQL.SQL_Sentencias;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import gui.HomeController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * This example demonstrates how to use Webcam Capture API in a JavaFX application.
 *
 * @author Rakesh Bhatt (rakeshbhatt10)
 */
public class WebCamAppLauncher extends Application {



    private class WebCamInfo {

        private String webCamName;
        private int webCamIndex;

        public String getWebCamName() {
            return webCamName;
        }

        public void setWebCamName(String webCamName) {
            this.webCamName = webCamName;
        }

        public int getWebCamIndex() {
            return webCamIndex;
        }

        public void setWebCamIndex(int webCamIndex) {
            this.webCamIndex = webCamIndex;
        }

        @Override
        public String toString() {
            return webCamName;
        }
    }
    private int refresh;
    private int indexWeb;
    private FlowPane bottomCameraControlPane;
    private FlowPane topPane;
    private BorderPane root;
    private String cameraListPromptText = "Choose Camera";
    private ImageView imgWebCamCapturedImage;
    private Webcam webCam = null;
    private boolean stopCamera = false;
    private BufferedImage grabbedImage;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private BorderPane webCamPane;
    private Button btnTomarFoto;
    private Button btnTomarNuevamente;
    private Button btnGuardarFoto;
    private HomeController homeController;
    private String cedula;



    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Connecting Camera Device Using Webcam Capture API");

        root = new BorderPane();
        topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(20);
        topPane.setOrientation(Orientation.HORIZONTAL);
        topPane.setPrefHeight(40);
        root.setTop(topPane);
        webCamPane = new BorderPane();
        webCamPane.setStyle("-fx-background-color: #ccc;");
        imgWebCamCapturedImage = new ImageView();
        webCamPane.setCenter(imgWebCamCapturedImage);
        root.setCenter(webCamPane);
        createTopPanel();
        bottomCameraControlPane = new FlowPane();
        bottomCameraControlPane.setOrientation(Orientation.HORIZONTAL);
        bottomCameraControlPane.setAlignment(Pos.CENTER);
        bottomCameraControlPane.setHgap(20);
        bottomCameraControlPane.setVgap(10);
        bottomCameraControlPane.setPrefHeight(40);
        bottomCameraControlPane.setDisable(true);
        createCameraControls();
        root.setBottom(bottomCameraControlPane);

        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(700);
        primaryStage.setWidth(600);
        primaryStage.centerOnScreen();
        primaryStage.show();

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                setImageViewSize();
            }
        });

    }

    protected void setImageViewSize() {

        double height = webCamPane.getHeight();
        double width = webCamPane.getWidth();

        imgWebCamCapturedImage.setFitHeight(height);
        imgWebCamCapturedImage.setFitWidth(width);
        imgWebCamCapturedImage.prefHeight(height);
        imgWebCamCapturedImage.prefWidth(width);
        imgWebCamCapturedImage.setPreserveRatio(true);

    }

    private void createTopPanel() {

        int webCamCounter = 0;
        Label lbInfoLabel = new Label("Select Your WebCam Camera");
        ObservableList<WebCamInfo> options = FXCollections.observableArrayList();

        topPane.getChildren().add(lbInfoLabel);

        for (Webcam webcam : Webcam.getWebcams()) {
            WebCamInfo webCamInfo = new WebCamInfo();
            webCamInfo.setWebCamIndex(webCamCounter);
            webCamInfo.setWebCamName(webcam.getName());
            options.add(webCamInfo);
            webCamCounter++;
        }

        ComboBox<WebCamInfo> cameraOptions = new ComboBox<WebCamInfo>();
        cameraOptions.setItems(options);
        cameraOptions.setPromptText(cameraListPromptText);
        cameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {

            @Override
            public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
                if (arg2 != null) {
                    System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
                    initializeWebCam(arg2.getWebCamIndex());
                    indexWeb=arg2.getWebCamIndex();
                }
            }
        });
        topPane.getChildren().add(cameraOptions);
    }

    protected void initializeWebCam(final int webCamIndex) {

        Task<Void> webCamTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                if (webCam != null) {
                    disposeWebCamCamera();
                }

                webCam = Webcam.getWebcams().get(webCamIndex);
                webCam.open();

                startWebCamStream();

                return null;
            }
        };

        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();

        bottomCameraControlPane.setDisable(false);
        btnTomarNuevamente.setDisable(true);
    }

    protected void startWebCamStream() {

        stopCamera = false;

        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                final AtomicReference<WritableImage> ref = new AtomicReference<>();
                BufferedImage img = null;

                while (!stopCamera) {
                    try {
                        if ((img = webCam.getImage()) != null) {

                            ref.set(SwingFXUtils.toFXImage(img, ref.get()));
                            img.flush();

                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    imageProperty.set(ref.get());
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);

    }

    private void createCameraControls() {

        btnTomarFoto = new Button();
        btnTomarFoto.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                btnGuardarFoto.setDisable(false);

                stopWebCamCamera();
            }
        });
        btnTomarFoto.setText("Tomar fotografía");
        btnTomarNuevamente = new Button();
        btnTomarNuevamente.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                startWebCamCamera();
                btnGuardarFoto.setDisable(true);

            }
        });
        btnTomarNuevamente.setText("Nueva Fotografía");
        btnGuardarFoto = new Button();
        btnGuardarFoto.setDisable(true);
        btnGuardarFoto.setText("Guardar fotografia");
        btnGuardarFoto.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Stage stage = (Stage) btnGuardarFoto.getScene().getWindow();
                try {
                    guardarFoto();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                disposeWebCamCamera();
                stage.close();
            }
        });
        bottomCameraControlPane.getChildren().add(btnTomarNuevamente);
        bottomCameraControlPane.getChildren().add(btnTomarFoto);
        bottomCameraControlPane.getChildren().add(btnGuardarFoto);
    }

    private void guardarFoto() throws IOException {
        byte[] bytes = WebcamUtils.getImageBytes(Webcam.getWebcams().get(indexWeb), "jpg");
        Usuario usuario = homeController.getUsuario();
        SQL_Sentencias sen= new SQL_Sentencias(usuario.getUsername(),usuario.getPassword());
        sen.setCon(new SQL_Conexion(usuario.getUsername(),usuario.getPassword()));
        try {
            sen.InsertarFotoCliente(bytes,this.cedula);
            JOptionPane.showMessageDialog(null,"Foto insertada exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en la conexión con la base de datos");
            e.printStackTrace();
        }
        if(refresh==1)this.homeController.onClicRefreshFotogradiaDetalleCliente();
//        try {
//            BufferedImage bImage = image;
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos);
//            data = bos.toByteArray();
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null,"Error al obtener la foto");
//            ex.printStackTrace();
//        }
//        try {
//            sen.setCon(new SQL_Conexion());
//            sen.InsertarFotoCliente(bytes, cedula);
//            JOptionPane.showMessageDialog(null,"Foto insertada exitosamente");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Error en la conexión con la base de datos");
//            ex.printStackTrace();
//        }
    }


    protected void disposeWebCamCamera() {
        stopCamera = true;
        webCam.close();
        btnTomarNuevamente.setDisable(true);
        btnTomarFoto.setDisable(true);
    }

    protected void startWebCamCamera() {
        stopCamera = false;
        startWebCamStream();
        btnTomarFoto.setDisable(false);
        btnTomarNuevamente.setDisable(true);
    }

    protected void stopWebCamCamera() {
        stopCamera = true;
        btnTomarNuevamente.setDisable(false);
        btnTomarFoto.setDisable(true);
    }

    public WebCamAppLauncher() {
    }


    public WebCamAppLauncher(HomeController homeController, String cedula, int refresh) {
        this.refresh=refresh;
        this.cedula=cedula;
        this.homeController = homeController;
    }

    public static void main(String[] args) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Stage stage = new Stage();
                WebCamAppLauncher webCam = new WebCamAppLauncher();
                webCam.start(stage);
                stage.show();
            }
// ...
        });

    }

}
