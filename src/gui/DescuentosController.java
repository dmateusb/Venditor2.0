package gui;
import SQL.ControlBd;
import SQL.SQL_Sentencias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DescuentosController {
    String usuario = "root";
    String password = "";
    ArrayList<Descuentos> arrayDescuentos = new ArrayList();
    public ObservableList<Descuentos> lista;

//    public void mostrarTablaInicial() {
//        ControlBd control = new ControlBd(usuario, password);
//        Object[][] Descuentos = control.ConsultarDescuentos();
//        for (int i = 0; i < Descuentos.length; i++) {
//            if (Descuentos[i][0] != null && Descuentos[i][1] != null && Descuentos[i][2] != null) {
//                Descuentos descuento = new Descuentos();
//
//
//                arrayDescuentos.add(descuento);
//            }
//        }
//    }

    private HomeController homeController;



    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }





}
