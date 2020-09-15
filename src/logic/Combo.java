package logic;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Stack;

public class Combo implements Runnable, ItemListener {
    private String nombre;
    private JComboBox combo;
    private JFrame v= new JFrame();
    private  Object lock = new Object();
    private Stack<String> nombrescamaras=new Stack<>();

    public Combo(Object lock, Stack<String>nombrescamaras) {
        this.nombrescamaras=nombrescamaras;
        this.lock = lock;
    }

    @Override
    public void run() {
//        nombre=(String)combo.getSelectedItem();
        try{
            JComboBox();
        }catch(Exception e){

        }
    }
    public void JComboBox() {
        combo = new JComboBox();
        combo.addItemListener(this);
        while (!nombrescamaras.isEmpty()) {
            combo.addItem(nombrescamaras.pop());
        }

        Button button = new Button();
        button.setLabel("Seleccionar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (lock){

                    Procedimientos.setNombreCamara(nombre);
                    lock.notify();
                    v.dispose();
                }

            }

        });

        // Creacion de la ventana con los componentes
        v = new JFrame();
        v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        v.getContentPane().setLayout(new FlowLayout());
        v.getContentPane().add(combo);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        v.setLocation(dim.width/2-v.getSize().width/2, dim.height/2-v.getSize().height/2);

        v.getContentPane().add(button);
        //v.getContentPane().add(tf);
        v.pack();
        v.setVisible(true);
    }

    public Stack<String> getNombrescamaras() {
        return nombrescamaras;
    }

    public void setNombrescamaras(Stack<String> nombrescamaras) {
        this.nombrescamaras = nombrescamaras;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == combo) {
            nombre = (String) combo.getSelectedItem();
        }
    }
}
