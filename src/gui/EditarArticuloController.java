package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditarArticuloController  {
    private String idArticulo="-1";
    private HomeController homeController;
    @FXML
    private Button btnCambiarPorcentaje;

    @FXML
    private TextArea txtDescripcionArticulo;

    @FXML
    private Spinner<Double> SpinnerPorcentaje;
    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private ComboBox<String> comboSubcategoria;

    @FXML
    private TextField txtPesoArticulo;

    @FXML
    private TextField txtValorArticulo;



    @FXML
    protected void cambioSubcategorias() {
        String output = (String) comboCategoria.getValue();
        if(output == "Oro"){
            comboSubcategoria.getItems().removeAll();
            comboSubcategoria.setItems(homeController.getSubOro());
            InteresOro();
            txtPesoArticulo.setDisable(false);
            comboSubcategoria.getSelectionModel().select(0);


        }else{
            comboSubcategoria.getItems().removeAll();
            comboSubcategoria.setItems(homeController.getSubElectrodomesticos());
            comboSubcategoria.getSelectionModel().select(0);
            InteresElectrodomesticos();
            txtPesoArticulo.setText("");
            txtPesoArticulo.setDisable(true);
        }


    }
    @FXML
    public void InteresOro(){
        SpinnerValueFactory<Double> ValoresSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(3,5,5,0.5);
        this.SpinnerPorcentaje.setValueFactory(ValoresSpinner);
    }
    @FXML
    private void InteresElectrodomesticos(){
        SpinnerValueFactory<Double> ValoresSpinner = new SpinnerValueFactory.DoubleSpinnerValueFactory(8,10,10,0.5);
        this.SpinnerPorcentaje.setValueFactory(ValoresSpinner);
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
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

    public void inicializar() {
//        cambioSubcategorias();
        SpinnerPorcentaje.setEditable(false);
        SpinnerPorcentaje.setDisable(true);
        comboCategoria.setItems(homeController.getCategorias());
        homeController.TextFormater(txtValorArticulo);
    }

    @FXML
    public void onClicConfirmar(){
        if(txtValorArticulo.getText().length()==0){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el valor por el que se hará el contrato.");
            return;
        }

        if(txtDescripcionArticulo.getText().length()==0){
            homeController.mostrarAlerta(" Información incompleta","No has escrito la descripción del artículo con el que se hará el contrato.");
            return;
        }
        if(comboCategoria.getValue().equals("Oro") && txtPesoArticulo.getText().length()==0){
            homeController.mostrarAlerta(" Información incompleta","No has escrito el peso del artículo con el que se hará el contrato.");
            return;
        }
        try {
            idArticulo= homeController.InsertarNuevoArticulo(comboCategoria,comboSubcategoria,txtDescripcionArticulo,
                    txtPesoArticulo,txtValorArticulo);
            Stage stage = (Stage) btnCambiarPorcentaje.getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Button getBtnCambiarPorcentaje() {
        return btnCambiarPorcentaje;
    }

    public void setBtnCambiarPorcentaje(Button btnCambiarPorcentaje) {
        this.btnCambiarPorcentaje = btnCambiarPorcentaje;
    }

    public TextArea getTxtDescripcionArticulo() {
        return txtDescripcionArticulo;
    }

    public void setTxtDescripcionArticulo(TextArea txtDescripcionArticulo) {
        this.txtDescripcionArticulo = txtDescripcionArticulo;
    }

    public Spinner<Double> getSpinnerPorcentaje() {
        return SpinnerPorcentaje;
    }

    public void setSpinnerPorcentaje(Spinner<Double> spinnerPorcentaje) {
        SpinnerPorcentaje = spinnerPorcentaje;
    }

    public ComboBox<String> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(ComboBox<String> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public ComboBox<String> getComboSubcategoria() {
        return comboSubcategoria;
    }

    public void setComboSubcategoria(ComboBox<String> comboSubcategoria) {
        this.comboSubcategoria = comboSubcategoria;
    }

    public TextField getTxtPesoArticulo() {
        return txtPesoArticulo;
    }

    public void setTxtPesoArticulo(TextField txtPesoArticulo) {
        this.txtPesoArticulo = txtPesoArticulo;
    }

    public TextField getTxtValorArticulo() {
        return txtValorArticulo;
    }

    public void setTxtValorArticulo(TextField txtValorArticulo) {
        this.txtValorArticulo = txtValorArticulo;
    }
}
