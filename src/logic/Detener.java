package logic;

import SQL.SQL_Sentencias;
import gui.HomeController;
import gui.jFrameWindow;

import javax.swing.*;

public class Detener implements Runnable {
    private  Object lock;
    private String cedula;
    private SQL_Sentencias sen;
    private HomeController homeController;
    public Detener(Object lock, SQL_Sentencias sen, HomeController homeController) {
        this.sen=sen;
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
        window.setHomeController(this.homeController);
        window.setSen(sen);
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

    public SQL_Sentencias getSen() {
        return sen;
    }

    public void setSen(SQL_Sentencias sen) {
        this.sen = sen;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
