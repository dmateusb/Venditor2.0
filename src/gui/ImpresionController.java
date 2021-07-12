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
import SQL.ControlBd;



public class ImpresionController {
    private ControlBd controlBd;
    private HomeController homeController;
    private Object[][] informacionContrato;
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
    @FXML private Spinner tgo1;
    @FXML private Spinner tgo2;
    @FXML private Spinner tgo3;
    @FXML private Spinner tgo4;
    @FXML private Spinner tgo5;
    @FXML private Spinner tgo6;
    @FXML private Spinner tgo7;
    @FXML private Spinner tgo8;
    @FXML private Spinner tgo9;
    @FXML private Spinner tgo10;
    @FXML private Spinner tgo11;
    @FXML private Spinner tgo12;
    @FXML private Spinner tgo13;
    @FXML private Spinner tgo14;
    @FXML private Spinner tgo15;
    @FXML private Spinner tgo16;
    @FXML private Spinner tgo17;
    @FXML private Spinner tgo18;
    @FXML private Spinner tgo19;
    @FXML private Spinner tgo20;
    @FXML private Spinner tgo21;
    @FXML private Spinner tgo22;
    @FXML private Spinner tgo23;
    @FXML private Spinner tgo24;
    @FXML private Spinner tgo25;
    @FXML private Spinner tgo26;
    @FXML private Spinner tgo27;
    @FXML private Spinner tgo28;
    @FXML private Spinner tgo29;
    @FXML private Spinner tgo30;
    @FXML private Spinner tgo31;
    @FXML private Spinner tgo32;
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
    @FXML private ToggleButton tgbtno1;
    @FXML private ToggleButton tgbtno2;
    @FXML private ToggleButton tgbtno3;
    @FXML private ToggleButton tgbtno4;
    @FXML private ToggleButton tgbtno5;
    @FXML private ToggleButton tgbtno6;
    @FXML private ToggleButton tgbtno7;
    @FXML private ToggleButton tgbtno8;
    @FXML private ToggleButton tgbtno9;
    @FXML private ToggleButton tgbtno10;
    @FXML private ToggleButton tgbtno11;
    @FXML private ToggleButton tgbtno12;
    @FXML private ToggleButton tgbtno13;
    @FXML private ToggleButton tgbtno14;
    @FXML private ToggleButton tgbtno15;
    @FXML private ToggleButton tgbtno16;
    @FXML private Button btnEditar;
    @FXML private Button btnFormato;
    @FXML private Button btnDocumento;
    @FXML private Button btnEditarO;
    @FXML private Button btnFormatoO;
    @FXML private Button btnDocumentoO;
    private Object[][] datosImpresionInicial;
    private boolean editando = false;
    private boolean editandoO = false;
    Map<String, ToggleButton> toggleButtons = new HashMap<>();
    Map<String, Spinner> spinners = new HashMap<>();

    public void initialize(){

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
            String[][] informacionImpresion = new String[16][4];
            Object[][] impresionBD = controlBd.consultarImpresion();
            for(int i=0;i<informacionImpresion.length;i++){
                    informacionImpresion[i][0] = impresionBD[i*2][0].toString();
            }
            //Barrio Cliente
            informacionImpresion[0][1] = tg15.getValue().toString();
            informacionImpresion[0][2] = tg16.getValue().toString();
            if(tgbtn8.isSelected()){
                informacionImpresion[0][3]="1";
            }else{
                informacionImpresion[0][3]="0";
            }
            //Cedula Cliente
            informacionImpresion[1][1] = tg9.getValue().toString();
            informacionImpresion[1][2] = tg10.getValue().toString();
            if(tgbtn5.isSelected()){
                informacionImpresion[1][3]="1";
            }else{
                informacionImpresion[1][3]="0";
            }
            controlBd.updateImpresion(informacionImpresion);
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

    @FXML private void editarO(){
        if(editandoO){
            tgbtno1.setDisable(true);
            tgbtno2.setDisable(true);
            tgbtno3.setDisable(true);
            tgbtno4.setDisable(true);
            tgbtno5.setDisable(true);
            tgbtno6.setDisable(true);
            tgbtno7.setDisable(true);
            tgbtno8.setDisable(true);
            tgbtno9.setDisable(true);
            tgbtno10.setDisable(true);
            tgbtno11.setDisable(true);
            tgbtno12.setDisable(true);
            tgbtno13.setDisable(true);
            tgbtno14.setDisable(true);
            tgbtno15.setDisable(true);
            tgbtno16.setDisable(true);
            tgo1.setDisable(true);
            tgo2.setDisable(true);
            tgo3.setDisable(true);
            tgo4.setDisable(true);
            tgo5.setDisable(true);
            tgo6.setDisable(true);
            tgo7.setDisable(true);
            tgo8.setDisable(true);
            tgo9.setDisable(true);
            tgo10.setDisable(true);
            tgo11.setDisable(true);
            tgo12.setDisable(true);
            tgo13.setDisable(true);
            tgo14.setDisable(true);
            tgo15.setDisable(true);
            tgo16.setDisable(true);
            tgo17.setDisable(true);
            tgo18.setDisable(true);
            tgo19.setDisable(true);
            tgo20.setDisable(true);
            tgo21.setDisable(true);
            tgo22.setDisable(true);
            tgo23.setDisable(true);
            tgo24.setDisable(true);
            tgo25.setDisable(true);
            tgo26.setDisable(true);
            tgo27.setDisable(true);
            tgo28.setDisable(true);
            tgo29.setDisable(true);
            tgo30.setDisable(true);
            tgo31.setDisable(true);
            tgo32.setDisable(true);
            btnEditarO.setText("Editar");
            btnFormatoO.setDisable(false);
            btnDocumentoO.setDisable(false);
            editandoO=false;
        }else{
            tgbtno1.setDisable(false);
            tgbtno2.setDisable(false);
            tgbtno3.setDisable(false);
            tgbtno4.setDisable(false);
            tgbtno5.setDisable(false);
            tgbtno6.setDisable(false);
            tgbtno7.setDisable(false);
            tgbtno8.setDisable(false);
            tgbtno9.setDisable(false);
            tgbtno10.setDisable(false);
            tgbtno11.setDisable(false);
            tgbtno12.setDisable(false);
            tgbtno13.setDisable(false);
            tgbtno14.setDisable(false);
            tgbtno15.setDisable(false);
            tgbtno16.setDisable(false);
            tgo1.setDisable(false);
            tgo2.setDisable(false);
            tgo3.setDisable(false);
            tgo4.setDisable(false);
            tgo5.setDisable(false);
            tgo6.setDisable(false);
            tgo7.setDisable(false);
            tgo8.setDisable(false);
            tgo9.setDisable(false);
            tgo10.setDisable(false);
            tgo11.setDisable(false);
            tgo12.setDisable(false);
            tgo13.setDisable(false);
            tgo14.setDisable(false);
            tgo15.setDisable(false);
            tgo16.setDisable(false);
            tgo17.setDisable(false);
            tgo18.setDisable(false);
            tgo19.setDisable(false);
            tgo20.setDisable(false);
            tgo21.setDisable(false);
            tgo22.setDisable(false);
            tgo23.setDisable(false);
            tgo24.setDisable(false);
            tgo25.setDisable(false);
            tgo26.setDisable(false);
            tgo27.setDisable(false);
            tgo28.setDisable(false);
            tgo29.setDisable(false);
            tgo30.setDisable(false);
            tgo31.setDisable(false);
            tgo32.setDisable(false);
            btnEditarO.setText("Guardar");
            btnFormatoO.setDisable(true);
            btnDocumentoO.setDisable(true);
            editandoO=true;
        }
    }

    @FXML private void guardarCambios(){


    }


    @FXML private void botonesActivos(ActionEvent event) throws DocumentException, IOException {
        String text= event.toString();
        System.out.println(text);
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

    @FXML private void botonesActivosO(ActionEvent event) throws DocumentException, IOException {
        String text= event.toString();
        String estado = text.substring(text.length()-10,text.length()-2);
        String id = text.substring(48,56);
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
        Procedimientos.abrirFormatoPdf("carta", controlBd);
    }
    @FXML private void abrirDocumentoTest() throws DocumentException, IOException {
        Procedimientos.abrirDocumentoPdf("carta",controlBd);
    }

    public void iniciarElementosInterfaz(){
        informacionContrato= controlBd.consultarImpresion();
        SpinnerValueFactory<Integer> ValoresSpinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[12][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[12][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[10][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[10][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[22][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[22][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[18][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[18][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[2][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[2][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[14][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[14][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[8][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[8][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner15 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,300,Integer.valueOf(informacionContrato[0][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner16 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,300,Integer.valueOf(informacionContrato[0][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner17 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[30][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner18 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[30][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner19 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[20][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner20 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[20][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner21 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[4][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner22 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[4][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner23 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[16][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner24 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[16][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner25 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[6][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner26 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[6][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner27 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[24][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner28 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[24][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner29 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[26][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner30 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[26][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner31 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[28][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinner32 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[28][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[13][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[13][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[11][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[11][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[23][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[23][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[19][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[19][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[3][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[3][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[15][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,900,Integer.valueOf(informacionContrato[15][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[9][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[9][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero15 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[1][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero16 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[1][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero17 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[31][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero18 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[31][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero19 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[21][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero20 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[21][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero21 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[5][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero22 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[5][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero23 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[17][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero24 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[17][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero25 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[7][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero26 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[7][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero27 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[25][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero28 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[24][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero29 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[27][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero30 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[27][2].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero31 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[29][1].toString()),1);
        SpinnerValueFactory<Integer> ValoresSpinnero32 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.valueOf(informacionContrato[29][1].toString()),1);
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
        tgo1.setValueFactory(ValoresSpinnero1);
        tgo2.setValueFactory(ValoresSpinnero2);
        tgo3.setValueFactory(ValoresSpinnero3);
        tgo4.setValueFactory(ValoresSpinnero4);
        tgo5.setValueFactory(ValoresSpinnero5);
        tgo6.setValueFactory(ValoresSpinnero6);
        tgo7.setValueFactory(ValoresSpinnero7);
        tgo8.setValueFactory(ValoresSpinnero8);
        tgo9.setValueFactory(ValoresSpinnero9);
        tgo10.setValueFactory(ValoresSpinnero10);
        tgo11.setValueFactory(ValoresSpinnero11);
        tgo12.setValueFactory(ValoresSpinnero12);
        tgo13.setValueFactory(ValoresSpinnero13);
        tgo14.setValueFactory(ValoresSpinnero14);
        tgo15.setValueFactory(ValoresSpinnero15);
        tgo16.setValueFactory(ValoresSpinnero16);
        tgo17.setValueFactory(ValoresSpinnero17);
        tgo18.setValueFactory(ValoresSpinnero18);
        tgo19.setValueFactory(ValoresSpinnero19);
        tgo20.setValueFactory(ValoresSpinnero20);
        tgo21.setValueFactory(ValoresSpinnero21);
        tgo22.setValueFactory(ValoresSpinnero22);
        tgo23.setValueFactory(ValoresSpinnero23);
        tgo24.setValueFactory(ValoresSpinnero24);
        tgo25.setValueFactory(ValoresSpinnero25);
        tgo26.setValueFactory(ValoresSpinnero26);
        tgo27.setValueFactory(ValoresSpinnero27);
        tgo28.setValueFactory(ValoresSpinnero28);
        tgo29.setValueFactory(ValoresSpinnero29);
        tgo30.setValueFactory(ValoresSpinnero30);
        tgo31.setValueFactory(ValoresSpinnero31);
        tgo32.setValueFactory(ValoresSpinnero32);
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
        toggleButtons.put("tgbtno1",tgbtno1);
        toggleButtons.put("tgbtno2",tgbtno2);
        toggleButtons.put("tgbtno3",tgbtno3);
        toggleButtons.put("tgbtno4",tgbtno4);
        toggleButtons.put("tgbtno5",tgbtno5);
        toggleButtons.put("tgbtno6",tgbtno6);
        toggleButtons.put("tgbtno7",tgbtno7);
        toggleButtons.put("tgbtno8",tgbtno8);
        toggleButtons.put("tgbtno9",tgbtno9);
        toggleButtons.put("tgbtno10",tgbtno10);
        toggleButtons.put("tgbtno11",tgbtno11);
        toggleButtons.put("tgbtno12",tgbtno12);
        toggleButtons.put("tgbtno13",tgbtno13);
        toggleButtons.put("tgbtno14",tgbtno14);
        toggleButtons.put("tgbtno15",tgbtno15);
        toggleButtons.put("tgbtno16",tgbtno16);
        spinners.put("tgo1",tgo1);
        spinners.put("tgo2",tgo2);
        spinners.put("tgo3",tgo3);
        spinners.put("tgo4",tgo4);
        spinners.put("tgo5",tgo5);
        spinners.put("tgo6",tgo6);
        spinners.put("tgo7",tgo7);
        spinners.put("tgo8",tgo8);
        spinners.put("tgo9",tgo9);
        spinners.put("tgo10",tgo10);
        spinners.put("tgo11",tgo11);
        spinners.put("tgo12",tgo12);
        spinners.put("tgo13",tgo13);
        spinners.put("tgo14",tgo14);
        spinners.put("tgo15",tgo15);
        spinners.put("tgo16",tgo16);
        spinners.put("tgo17",tgo17);
        spinners.put("tgo18",tgo18);
        spinners.put("tgo19",tgo19);
        spinners.put("tgo20",tgo20);
        spinners.put("tgo21",tgo21);
        spinners.put("tgo22",tgo22);
        spinners.put("tgo23",tgo23);
        spinners.put("tgo24",tgo24);
        spinners.put("tgo25",tgo25);
        spinners.put("tgo26",tgo26);
        spinners.put("tgo27",tgo27);
        spinners.put("tgo28",tgo28);
        spinners.put("tgo29",tgo29);
        spinners.put("tgo30",tgo30);
        spinners.put("tgo31",tgo31);
        spinners.put("tgo32",tgo32);
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public ControlBd getControlBd() {
        return controlBd;
    }

    public void setControlBd(ControlBd controlBd) {
        this.controlBd = controlBd;
    }
}
