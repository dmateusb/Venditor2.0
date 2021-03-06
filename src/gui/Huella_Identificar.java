package gui;

import SQL.ControlBd;
import SQL.SQL_Conexion;
import SQL.SQL_Sentencias;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import logic.Procedimientos;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class Huella_Identificar extends JFrame implements Runnable{
    private JPanel mainPane;
    private JPanel panHuellas;
    private JPanel panBtns;
    private JLabel lblImagenHuella;
    private JTextArea txtArea;
    private HomeController home;
    private JButton btnIdentificar;
    private JButton btnSalir;
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;
    private String cedula;
    private SQL_Conexion con=new SQL_Conexion("root","");
    private int operacion=0;
    //    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        final Object lock=new Object();
//        Huella_Identificar huella = new Huella_Identificar();
//        huella.setVisible(true);
//    }
    public Huella_Identificar(HomeController home) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        this.home=home;
        run();
//        try {
//            identificarHuella();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        for(int i=0;i<20;i++){
            EnviarTexto("121\n");
        }
        setSize(600,400);
        setContentPane(mainPane);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", "Lookandfeel inválido.",
                    JOptionPane.ERROR_MESSAGE);
        }
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
                dispose();
            }
        });



    }





    protected void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("La Huella Digital ha sido Capturada");
                        btnIdentificar.setEnabled(true);
                        ProcesarCaptura(e.getSample());
                    }
                });
            }
        });
        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conecatado");
                    }
                });
            }
        });
        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El dedo ha sido quitado del Lector de Huella");
                    }
                });
            }
        });
        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Error: " + e.getError());
                    }
                });
            }
        });
        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Error: " + e.getError());
                    }
                });
            }
        });
    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public java.awt.Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }
    public void DibujarHuella(Image image) {
        lblImagenHuella.setIcon(new ImageIcon(
                image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
    }

    public void EstadoHuellas(){
        EnviarTexto("Muestra de Huellas Necesarias para Guardar Template "+ Reclutador.getFeaturesNeeded());
    }
    public void EnviarTexto(String string) {
        txtArea.append(string + "\n");
    }
    public void start(){
        Lector.startCapture();
        EnviarTexto("Utilizando el Lector de Huella Dactilar ");
    }
    public void stop(){
        System.out.println("Estoy cerrando 1");
        Lector.stopCapture();
        System.out.println("Estoy cerrando 2");
        EnviarTexto("No se está usando el Lector de Huella Dactilar ");
    }

    public void ProcesarCaptura(DPFPSample sample) {
// Procesar la muestra de la huella y crear un conjunto de caracterÃ­sticas con el propÃ³sito de inscripciÃ³n.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

// Procesar la muestra de la huella y crear un conjunto de caracterÃ­sticas con el propÃ³sito de verificacion.
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

// Comprobar la calidad de la muestra de la huella y lo aÃ±ade a su reclutador si es bueno
        if (featuresinscripcion != null)
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

// Dibuja la huella dactilar capturada.
                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

                btnIdentificar.setEnabled(true);

            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } finally {
                EstadoHuellas();
// Comprueba si la plantilla se ha creado.
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY: // informe de Ã©xito y detiene la captura de huellas
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla o Identificarla");
                        btnIdentificar.setEnabled(false);
                        break;

                    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                        Reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(Huella_Identificar.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
            }
    }

    public void run(){
        Iniciar();
        start();
        EstadoHuellas();
        btnIdentificar.setEnabled(false);
        btnSalir.grabFocus();
        btnIdentificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    identificarHuella();
                    Reclutador.clear();
                } catch (IOException ex) {

                }
            }
        });


    }






    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void identificarHuella() throws IOException {

        try {
            //Establece los valores para la sentencia SQL
            Connection c = con.conectado();

            //Obtiene todas las huellas de la bd
            PreparedStatement identificarStmt = c.prepareStatement("SELECT Cedula,Huella FROM clientes");
            ResultSet rs = identificarStmt.executeQuery();

            //Si se encuentra el nombre en la base de datos
            while(rs.next()){
                //Lee la plantilla de la base de datos
                byte templateBuffer[] = rs.getBytes("Huella");
                if(templateBuffer!=null){
                    String cedula=rs.getString("Cedula");
                    //Crea una nueva plantilla a partir de la guardada en la base de datos
                    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                    //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                    setTemplate(referenceTemplate);

                    // Compara las caracteriticas de la huella recientemente capturda con la
                    // alguna plantilla guardada en la base de datos que coincide con ese tipo
                    DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                    //compara las plantilas (actual vs bd)
                    //Si encuentra correspondencia dibuja el mapa
                    //e indica el nombre de la persona que coincidió.

                    if (result.isVerified()){
                        //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
                        JOptionPane.showMessageDialog(null, "La huella corresponde al numero" +
                                "de cédula: "+cedula,"Verificacion de Huella", JOptionPane.INFORMATION_MESSAGE);
                        try{
                            if(operacion==1) {
                                home.setTxtcedulaNuevaRetroventa(cedula);
                                home.buscarClienteNuevaRetro();
                            }else{
                                home.setTxtBusquedaCliente(cedula);
                                home.buscarClienteBusquedaCliente();
                            }
                        }catch(Exception e){
                            System.out.println("Hubo un problema en la interfaz");
                        }finally{
                            operacion=0;
                            stop();
                            this.dispose();
                        }
                        return ;
                    }
                }
            }
            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
            JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
        } catch (SQLException e) {
            //Si ocurre un error lo indica en la consola
            System.err.println("Error al identificar huella dactilar."+e.getMessage());
        }

      /*  home.setTxtBusquedaCliente("5");
        home.buscarClienteBusquedaCliente();*/
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }
}