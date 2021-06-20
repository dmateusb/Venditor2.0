package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Caja;
import logic.Cliente;
import logic.Contrato;
import logic.Procedimientos;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class ImpresionController {
    private HomeController homeController;
    @FXML private Spinner tg1;
    @FXML private Spinner tg2;
    @FXML private Spinner tg3;
    @FXML private Spinner tg4;
    @FXML private Spinner tg5;
    @FXML private Spinner tg6;
    @FXML private Spinner tg7;
    @FXML private Spinner tg8;
    @FXML private Spinner tg9;
    @FXML private Spinner tg10;
    @FXML private Spinner tg11;
    @FXML private Spinner tg12;
    @FXML private Spinner tg13;
    @FXML private Spinner tg14;
    @FXML private Spinner tg15;
    @FXML private Spinner tg16;
    @FXML private Spinner tg17;
    @FXML private Spinner tg18;
    @FXML private Spinner tg19;
    @FXML private Spinner tg20;
    @FXML private Spinner tg21;
    @FXML private Spinner tg22;
    @FXML private Spinner tg23;
    @FXML private Spinner tg24;
    @FXML private Spinner tg25;
    @FXML private Spinner tg26;
    @FXML private Spinner tg27;
    @FXML private Spinner tg28;
    @FXML private Spinner tg29;
    @FXML private Spinner tg30;
    @FXML private Spinner tg31;
    @FXML private Spinner tg32;
    @FXML private ToggleButton tgbtn1;
    @FXML private ToggleButton tgbtn2;
    @FXML private ToggleButton tgbtn3;
    @FXML private ToggleButton tgbtn4;
    @FXML private ToggleButton tgbtn5;
    @FXML private ToggleButton tgbtn6;
    @FXML private ToggleButton tgbtn7;
    @FXML private ToggleButton tgbtn8;
    @FXML private ToggleButton tgbtn9;
    @FXML private ToggleButton tgbtn10;
    @FXML private ToggleButton tgbtn11;
    @FXML private ToggleButton tgbtn12;
    @FXML private ToggleButton tgbtn13;
    @FXML private ToggleButton tgbtn14;
    @FXML private ToggleButton tgbtn15;
    @FXML private ToggleButton tgbtn16;
    @FXML private Button btnEditar;
    @FXML private Button btnFormato;
    @FXML private Button btnDocumento;
    private boolean editando = false;
    Map<String, ToggleButton> toggleButtons = new HashMap<>();
    Map<String, Spinner> spinners = new HashMap<>();


    public void initialize(){

        iniciarElementosInterfaz();

    }
    @FXML private void editar(){
        if(editando){
            tgbtn1.setDisable(true);
            tgbtn2.setDisable(true);
            tgbtn3.setDisable(true);
            tgbtn4.setDisable(true);
            tgbtn5.setDisable(true);
            tgbtn6.setDisable(true);
            tgbtn7.setDisable(true);
            tgbtn8.setDisable(true);
            tgbtn9.setDisable(true);
            tgbtn10.setDisable(true);
            tgbtn11.setDisable(true);
            tgbtn12.setDisable(true);
            tgbtn13.setDisable(true);
            tgbtn14.setDisable(true);
            tgbtn15.setDisable(true);
            tgbtn16.setDisable(true);
            tg1.setDisable(true);
            tg2.setDisable(true);
            tg3.setDisable(true);
            tg4.setDisable(true);
            tg5.setDisable(true);
            tg6.setDisable(true);
            tg7.setDisable(true);
            tg8.setDisable(true);
            tg9.setDisable(true);
            tg10.setDisable(true);
            tg11.setDisable(true);
            tg12.setDisable(true);
            tg13.setDisable(true);
            tg14.setDisable(true);
            tg15.setDisable(true);
            tg16.setDisable(true);
            tg17.setDisable(true);
            tg18.setDisable(true);
            tg19.setDisable(true);
            tg20.setDisable(true);
            tg21.setDisable(true);
            tg22.setDisable(true);
            tg23.setDisable(true);
            tg24.setDisable(true);
            tg25.setDisable(true);
            tg26.setDisable(true);
            tg27.setDisable(true);
            tg28.setDisable(true);
            tg29.setDisable(true);
            tg30.setDisable(true);
            tg31.setDisable(true);
            tg32.setDisable(true);
            btnEditar.setText("Editar");
            btnFormato.setDisable(false);
            btnDocumento.setDisable(false);
            editando=false;
        }else{
            tgbtn1.setDisable(false);
            tgbtn2.setDisable(false);
            tgbtn3.setDisable(false);
            tgbtn4.setDisable(false);
            tgbtn5.setDisable(false);
            tgbtn6.setDisable(false);
            tgbtn7.setDisable(false);
            tgbtn8.setDisable(false);
            tgbtn9.setDisable(false);
            tgbtn10.setDisable(false);
            tgbtn11.setDisable(false);
            tgbtn12.setDisable(false);
            tgbtn13.setDisable(false);
            tgbtn14.setDisable(false);
            tgbtn15.setDisable(false);
            tgbtn16.setDisable(false);
            tg1.setDisable(false);
            tg2.setDisable(false);
            tg3.setDisable(false);
            tg4.setDisable(false);
            tg5.setDisable(false);
            tg6.setDisable(false);
            tg7.setDisable(false);
            tg8.setDisable(false);
            tg9.setDisable(false);
            tg10.setDisable(false);
            tg11.setDisable(false);
            tg12.setDisable(false);
            tg13.setDisable(false);
            tg14.setDisable(false);
            tg15.setDisable(false);
            tg16.setDisable(false);
            tg17.setDisable(false);
            tg18.setDisable(false);
            tg19.setDisable(false);
            tg20.setDisable(false);
            tg21.setDisable(false);
            tg22.setDisable(false);
            tg23.setDisable(false);
            tg24.setDisable(false);
            tg25.setDisable(false);
            tg26.setDisable(false);
            tg27.setDisable(false);
            tg28.setDisable(false);
            tg29.setDisable(false);
            tg30.setDisable(false);
            tg31.setDisable(false);
            tg32.setDisable(false);
            btnEditar.setText("Guardar");
            btnFormato.setDisable(true);
            btnDocumento.setDisable(true);
            editando=true;
        }
    }

    @FXML private void botonesActivos(ActionEvent event) throws DocumentException, IOException {
        String text= event.toString();
        String estado = text.substring(text.length()-10,text.length()-2);
        String id = text.substring(48,55);
        System.out.println();
        if(id.charAt(id.length()-1)==','){
            id=id.substring(0,id.length()-1);
        }
        if(estado.equals("Inactivo")){
            toggleButtons.get(id).setText("Activo");



        }else{
            toggleButtons.get(id).setText("Inactivo");




        }
    }
    @FXML private void abrirTest() throws DocumentException, IOException {
        Procedimientos.abrirFormatoPdf("carta");
    }
    @FXML private void abrirDocumentoTest() throws DocumentException, IOException {
        Procedimientos.abrirDocumentoPdf("carta");
    }


    private void iniciarElementosInterfaz(){
        SpinnerValueFactory<Integer> ValoresSpinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,416,1);
        SpinnerValueFactory<Integer> ValoresSpinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,358,1);
        SpinnerValueFactory<Integer> ValoresSpinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,477,1);
        SpinnerValueFactory<Integer> ValoresSpinner4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,358,1);
        SpinnerValueFactory<Integer> ValoresSpinner5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,537,1);
        SpinnerValueFactory<Integer> ValoresSpinner6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,358,1);
        SpinnerValueFactory<Integer> ValoresSpinner7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,110,1);
        SpinnerValueFactory<Integer> ValoresSpinner8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,196,1);
        SpinnerValueFactory<Integer> ValoresSpinner9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,335,1);
        SpinnerValueFactory<Integer> ValoresSpinner10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,296,1);
        SpinnerValueFactory<Integer> ValoresSpinner11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,445,1);
        SpinnerValueFactory<Integer> ValoresSpinner12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,296,1);
        SpinnerValueFactory<Integer> ValoresSpinner13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner15 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner16 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner17 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner18 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner19 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner20 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner21 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner22 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner23 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner24 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner25 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner26 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner27 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner28 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner29 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner30 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner31 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        SpinnerValueFactory<Integer> ValoresSpinner32 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,1);
        tg1.setValueFactory(ValoresSpinner1);
        tg2.setValueFactory(ValoresSpinner2);
        tg3.setValueFactory(ValoresSpinner3);
        tg4.setValueFactory(ValoresSpinner4);
        tg5.setValueFactory(ValoresSpinner5);
        tg6.setValueFactory(ValoresSpinner6);
        tg7.setValueFactory(ValoresSpinner7);
        tg8.setValueFactory(ValoresSpinner8);
        tg9.setValueFactory(ValoresSpinner9);
        tg10.setValueFactory(ValoresSpinner10);
        tg11.setValueFactory(ValoresSpinner11);
        tg12.setValueFactory(ValoresSpinner12);
        tg13.setValueFactory(ValoresSpinner13);
        tg14.setValueFactory(ValoresSpinner14);
        tg15.setValueFactory(ValoresSpinner15);
        tg16.setValueFactory(ValoresSpinner16);
        tg17.setValueFactory(ValoresSpinner17);
        tg18.setValueFactory(ValoresSpinner18);
        tg19.setValueFactory(ValoresSpinner19);
        tg20.setValueFactory(ValoresSpinner20);
        tg21.setValueFactory(ValoresSpinner21);
        tg22.setValueFactory(ValoresSpinner22);
        tg23.setValueFactory(ValoresSpinner23);
        tg24.setValueFactory(ValoresSpinner24);
        tg25.setValueFactory(ValoresSpinner25);
        tg26.setValueFactory(ValoresSpinner26);
        tg27.setValueFactory(ValoresSpinner27);
        tg28.setValueFactory(ValoresSpinner28);
        tg29.setValueFactory(ValoresSpinner29);
        tg30.setValueFactory(ValoresSpinner30);
        tg31.setValueFactory(ValoresSpinner31);
        tg32.setValueFactory(ValoresSpinner32);
        toggleButtons.put("tgbtn1",tgbtn1);
        toggleButtons.put("tgbtn2",tgbtn2);
        toggleButtons.put("tgbtn3",tgbtn3);
        toggleButtons.put("tgbtn4",tgbtn4);
        toggleButtons.put("tgbtn5",tgbtn5);
        toggleButtons.put("tgbtn6",tgbtn6);
        toggleButtons.put("tgbtn7",tgbtn7);
        toggleButtons.put("tgbtn8",tgbtn8);
        toggleButtons.put("tgbtn9",tgbtn9);
        toggleButtons.put("tgbtn10",tgbtn10);
        toggleButtons.put("tgbtn11",tgbtn11);
        toggleButtons.put("tgbtn12",tgbtn12);
        toggleButtons.put("tgbtn13",tgbtn13);
        toggleButtons.put("tgbtn14",tgbtn14);
        toggleButtons.put("tgbtn15",tgbtn15);
        toggleButtons.put("tgbtn16",tgbtn16);
        spinners.put("tg1",tg1);
        spinners.put("tg2",tg2);
        spinners.put("tg3",tg3);
        spinners.put("tg4",tg4);
        spinners.put("tg5",tg5);
        spinners.put("tg6",tg6);
        spinners.put("tg7",tg7);
        spinners.put("tg8",tg8);
        spinners.put("tg9",tg9);
        spinners.put("tg10",tg10);
        spinners.put("tg11",tg11);
        spinners.put("tg12",tg12);
        spinners.put("tg13",tg13);
        spinners.put("tg14",tg14);
        spinners.put("tg15",tg15);
        spinners.put("tg16",tg16);
        spinners.put("tg17",tg17);
        spinners.put("tg18",tg18);
        spinners.put("tg19",tg19);
        spinners.put("tg20",tg20);
        spinners.put("tg21",tg21);
        spinners.put("tg22",tg22);
        spinners.put("tg23",tg23);
        spinners.put("tg24",tg24);
        spinners.put("tg25",tg25);
        spinners.put("tg26",tg26);
        spinners.put("tg27",tg27);
        spinners.put("tg28",tg28);
        spinners.put("tg29",tg29);
        spinners.put("tg30",tg30);
        spinners.put("tg31",tg31);
        spinners.put("tg32",tg32);
    }


    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }






}
