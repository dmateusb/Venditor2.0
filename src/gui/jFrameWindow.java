/*package gui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import javafx.application.Platform;
import logic.CloseListener;
import logic.Procedimientos;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class jFrameWindow  extends JFrame implements  Runnable{
    private Executor executor = Executors.newSingleThreadExecutor();
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private Webcam webcam;
    private WebcamPanel panel;
    private JPanel main;
    private static JFrame jFrame= new jFrameWindow();
    private JButton btntomarFotografia;
    private JButton btnConfirmar;
    private BufferedImage image=null;
    private static String cedula;
    private Object lock = new Object();

    public jFrameWindow() {
        webcam=Webcam.getDefault();
        panel = new WebcamPanel(webcam, false);
        panel.setPreferredSize(webcam.getViewSize());
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 400, 300);
        main.add(panel);
        if (initialized.compareAndSet(false, true)) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    panel.start();
                }
            });
        }
        btntomarFotografia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                image = webcam.getImage();
                if(image!=null) {
                    btnConfirmar.setEnabled(true);
                    btntomarFotografia.setText("Tomar nuevamente");

                }
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write(image, "PNG", new File("./temp/fotocedula.PNG"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                webcam.close();
                jFrame.dispose();
            }
        });

        jFrame.setSize(720,480);
        jFrame.setContentPane(new   jFrameWindow().main);
        jFrame.setVisible(true);
    }


    public  void run() {
        try{
            synchronized (lock){
                lock.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String a=Procedimientos.getNombreCamara();
        webcam = Webcam.getWebcamByName(Procedimientos.getNombreCamara());
        panel = new WebcamPanel(webcam, false);
        panel.setPreferredSize(webcam.getViewSize());
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 400, 300);
        main.add(panel);
        if (initialized.compareAndSet(false, true)) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    panel.start();
                }
            });
        }
        btntomarFotografia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                image = webcam.getImage();
                if(image!=null) {
                    btnConfirmar.setEnabled(true);
                    btntomarFotografia.setText("Tomar nuevamente");
root
                }
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write(image, "PNG", new File("./temp/fotocedula.PNG"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                webcam.close();
                jFrame.dispose();
            }
        });

        jFrame.setSize(720,480);
        jFrame.setContentPane(new   jFrameWindow().main);
        jFrame.setVisible(true);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }
}
*/
package gui;

import SQL.ControlBd;
import SQL.SQL_Sentencias;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import logic.CloseListener;
import logic.Procedimientos;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class jFrameWindow extends JFrame  {
    private Executor executor = Executors.newSingleThreadExecutor();
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private Webcam webcam;
    private WebcamPanel panel;
    private JPanel main;
    private static JFrame jFrame= new jFrameWindow();
    private JButton btntomarFotografia;
    private JButton btnConfirmar;
    private JLabel imgPrevFoto;
    private BufferedImage image=null;
    private static String cedula;
    private Object lock;
    private SQL_Sentencias sen= new SQL_Sentencias("root","");
    public jFrameWindow() {

        webcam = getCameraByName(Procedimientos.getNombreCamara());
        panel = new WebcamPanel(webcam, false);
        panel.setPreferredSize(webcam.getViewSize());
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 400, 300);
        main.add(panel);
        if (initialized.compareAndSet(false, true)) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    panel.start();
                }
            });
        }
        btntomarFotografia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                image = webcam.getImage();
                if (image != null) {
                   /* ImageIcon icon = new ImageIcon(image);
                    imgPrevFoto= new JLabel();
                    imgPrevFoto.setIcon(icon);
                    imgPrevFoto.setVisible(true);
                    main.add(imgPrevFoto);
                    main.repaint();
                    */
                    handleButtons(btntomarFotografia);

                }
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                byte[] data = null;
                try {
                    BufferedImage bImage = image;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(bImage, "jpg", bos);
                    data = bos.toByteArray();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Error al obtener la foto");
                    ex.printStackTrace();
                }
                try {
                    sen.InsertarFotoCliente(data, cedula);
                    JOptionPane.showMessageDialog(null,"Foto insertada exitosamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error en la conexión con la base de datos");
                    ex.printStackTrace();
                }
                webcam.getDiscoveryService().setEnabled(false);
                webcam.getDiscoveryService().stop();
                webcam.close();
                jFrame.dispose();
            }
        });
    }

    private void handleButtons(JButton btn) {
        if(btn.getText()=="Tomar Fotografia"){
            panel.pause();
            btn.setText("Nueva Foto");
            btnConfirmar.setEnabled(true);
        }else if(btn.getText()=="Nueva Foto"){
            btnConfirmar.setEnabled(false);
            panel.resume();
            btn.setText("Tomar Fotografia");
        }
    }

    public Webcam getCameraByName(String name) {
        for (Webcam webcam : Webcam.getWebcams()) {
            if (webcam.getName().equalsIgnoreCase(name)) {
                return webcam;
            }
        }
        return null;
    }

    public static void run() {
        jFrame.setSize(720,480);
        jFrame.setContentPane(new   jFrameWindow().main);
        jFrame.setVisible(true);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public Object getLock() {
        return lock;
    }
}
