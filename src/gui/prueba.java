package gui;

import javax.swing.*;

public class prueba extends JFrame {
    private JButton button1;
    private JPanel panel1;

    public prueba(){
        setContentPane(panel1);
    }

    public static void main(String[] args) {
        prueba prueba= new prueba();
        prueba.setVisible(true);
    }

}
