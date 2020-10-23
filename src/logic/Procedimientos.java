package logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

 public  class Procedimientos {
    BufferedImage imagen;
    private static String nombreCamara;
    private static String cedula;

     public static void main(String[] args) {
         Procedimientos procedimientos= new Procedimientos();
         System.out.println(procedimientos.setPuntosDecimales("543"));
         System.out.println(procedimientos.setPuntosDecimales("134543"));

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
