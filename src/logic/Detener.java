package logic;

import gui.jFrameWindow;

import javax.swing.*;

public class Detener implements Runnable {
    private  Object lock;
    private String cedula;

    public Detener(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                lock.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jFrameWindow window = new jFrameWindow();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setCedula(cedula);
        window.run();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
