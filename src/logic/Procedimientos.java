package logic;

import gui.HomeController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class Procedimientos {
    BufferedImage imagen;
    private static String nombreCamara;
    private static String cedula;
    private static String usuario="root";
    private static String password="";
    private static HomeController homeController;



     public static void main(String[] args) {
         Procedimientos procedimientos= new Procedimientos();
     }
    public static String setPuntosDecimales(String text){
        if(text.length()>3){
            int puntosAgregados=0;
            for(int i=1;i <= Integer.valueOf((text.length()-puntosAgregados)/3);i++){
                //1.223.232
                String left=text.substring(0,text.length()-puntosAgregados-3*i);
                if(left.equals("") || left.equals("-")) break;
                String right=text.substring(text.length()-puntosAgregados-3*i,text.length());
                text=left+"."+right;
                puntosAgregados++;
                }
        }
        return text;
    }

    //Evalúa el estado de la caja y devuelve su resultado
    public static String estadoCaja(HomeController HC){
        LocalDate hoy = LocalDate.now();
        String fechaHoy = hoy.toString();
        Object[][] Cajas=HC.getControlBd().consultarEstadoCaja(fechaHoy);
        //Si la caja nunca ha sido abierta, la abre por primera vez
        if(Cajas.length==0){
            HC.getSentencias().InsertarEstadoCaja("Abierta",HC.getUsuario().getUsername());
            return "Abierta";
        }
        //Si la caja no se ha abierto con la fecha de hoy, la abre y retorna "Abierta" como estado
        if(Cajas[0][0]==null && Cajas[0][1]==null){
            HC.getSentencias().InsertarEstadoCaja("Abierta",usuario);
            return "Abierta";
        }
        //Si la caja ya ha sido abierta hoy, muestra su último estado
        else{
            int i=0;
            while(Cajas[i][0]!=null ){
                i=i+1;
                if (i<=Cajas.length) break;
            }
            if(Cajas[i-1][2].toString().equals("Cerrada")){
                return "Cerrada";
            }else
                return "Abierta";
        }
    }


     public static HomeController getHomeController() {
         return homeController;
     }

     public static void setHomeController(HomeController homeController) {
         homeController = homeController;
     }

    public static byte[] convertToBytes(BufferedImage originalImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( originalImage, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    public static String getNombreCamara() {
        return nombreCamara;
    }

    public static void setNombreCamara(String nombreCamara) {
        Procedimientos.nombreCamara = nombreCamara;
    }

     public static String getCedula() {
         return cedula;
     }

     public static void setCedula(String cedula) {
         Procedimientos.cedula = cedula;
     }
 }
