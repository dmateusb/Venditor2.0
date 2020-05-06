package logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

 public  class Procedimientos {
    BufferedImage imagen;
    private static String nombreCamara;
    private static String cedula;
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
