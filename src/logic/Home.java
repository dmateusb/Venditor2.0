package logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.BasicConfigurator;

import java.net.URL;

/**
 *
 * @author david
 */
public class Home extends Application {
    /*
    @Override
    public void start(Stage stage) throws Exception {

        stage.initStyle(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.exit();
            }
        });
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        //stage.getIcons().add(new Image(Home.getResourceAsStream("/imagenes/Iconos/FAVICON.png")));
        stage.show();
        new FadeIn(root).play();
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        /*Class clazz=Home.class;
        URL url = clazz.getResource("../img/tempphoto.jpg");
        */

        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        BasicConfigurator.configure();

        launch(args);
    }
    
}
