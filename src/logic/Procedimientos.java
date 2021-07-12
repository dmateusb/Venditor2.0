package logic;

import gui.HomeController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import SQL.ControlBd;

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

    public static void abrirFormatoPdf(String tamañoPapel, ControlBd controlBd)throws IOException, DocumentException {
        PdfReader reader;
        Object[][] informacionImpresion = controlBd.consultarImpresion();
         if(tamañoPapel.equals("carta")){
             reader = new PdfReader("/im/CONTRATO-CARTA.pdf");
         }else{
             reader = new PdfReader("/im/CONTRATO OFICIO.pdf");
         }
        //Creo un archivo temporal para obtener la dirección local en la que se creó.
        //Esta dirección la uso para obtener la ruta en la que se guardará el archivo generado
        File f = new File("program.txt");

        // Obtengo la direccion absoluta del directorio
        String absolute = f.getAbsolutePath();
        String address = absolute.substring(0,absolute.length()-11);
        String direccion = address.replace("\\", "/");


        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(direccion+"CONTRATO TEST.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
        //loop on pages (1-based)

        // get object for writing over the existing content;
        // you can also use getUnderContent for writing in the bottom layer
        PdfContentByte over = stamper.getOverContent(1);

        if(informacionImpresion[0][3].equals("1")){
            String fechaInicio = "00-00-0000";
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(416, 755);   // set x,y position (0,0 is at the bottom left)
            over.showText(fechaInicio);  // set text
            over.endText();
        }



        String fechaFinal = "00-00-0000";
        String contratoImprimir = "C0000000";
        String nombreCliente = "José María Figueroa Martinez";
        String cedulaCliente = "123456789";
        String lugarExpCliente = "Bogotá";
        String direccionCliente = "Calle 00 # 00 - 00";
        String barrio = "Bario nuevo";
        String telefono = "3003003030";
        String nombreVendedor = "María José Martinez Figueroa";
        String cedulaVendedor = "987654321";
        String lugarExpVendedor = "Bogotá";
        String descripcion = "Un equipo de sonido con 4 parlantes";
        String peso ="N/A";
        String precio = "100.000";
        String sobreprecio = "130.000";




            // write text


            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(477, 358);   // set x,y position (0,0 is at the bottom left)
            over.showText(fechaFinal);  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 12);    // set font and size
            over.setTextMatrix(536, 358);   // set x,y position (0,0 is at the bottom left)
            over.showText(contratoImprimir);  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 8);    // set font and size
            over.setTextMatrix(110, 296);   // set x,y position (0,0 is at the bottom left)
            over.showText(nombreCliente);  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 8);    // set font and size
            over.setTextMatrix(335, 296);   // set x,y position (0,0 is at the bottom left)
            over.showText(cedulaCliente);  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 8);    // set font and size
            over.setTextMatrix(445, 296);   // set x,y position (0,0 is at the bottom left)
            over.showText(lugarExpCliente);  // set text
            over.endText();

        stamper.close();

        try {

            if ((new File(direccion+"CONTRATO TEST.pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler "+direccion+"CONTRATO TEST.pdf");
                p.waitFor();

            } else {

                System.out.println("File doesn't exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void abrirDocumentoPdf(String tamañoPapel, ControlBd controlBd)throws IOException, DocumentException {
        PdfReader reader;
        Object[][] informacionImpresion = controlBd.consultarImpresion();
        if(tamañoPapel.equals("carta")){
            reader = new PdfReader("/im/FORMATO BLANCO.pdf");
        }else{
            reader = new PdfReader("/im/FORMATO BLANCO.pdf");
        }
        //Creo un archivo temporal para obtener la dirección local en la que se creó.
        //Esta dirección la uso para obtener la ruta en la que se guardará el archivo generado
        File f = new File("program.txt");

        // Obtengo la direccion absoluta del directorio
        String absolute = f.getAbsolutePath();
        String address = absolute.substring(0,absolute.length()-11);
        String direccion = address.replace("\\", "/");

        System.out.println(informacionImpresion[0][3]);



        String fechaInicio = "00-00-0000";
        String fechaFinal = "00-00-0000";
        String contratoImprimir = "C0000000";
        String nombreCliente = "José María Figueroa Martinez";
        String cedulaCliente = "123456789";
        String lugarExpCliente = "Bogotá";
        String direccionCliente = "Calle 00 # 00 - 00";
        String barrio = "Bario nuevo";
        String telefono = "3003003030";
        String nombreVendedor = "María José Martinez Figueroa";
        String cedulaVendedor = "987654321";
        String lugarExpVendedor = "Bogotá";
        String descripcion = "Un equipo de sonido con 4 parlantes";
        String peso ="N/A";
        String precio = "100.000";
        String sobreprecio = "130.000";


        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(direccion+"BLANCO TEST.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font



        //loop on pages (1-based)

        // get object for writing over the existing content;
        // you can also use getUnderContent for writing in the bottom layer
        PdfContentByte over = stamper.getOverContent(1);

        // write text
        over.beginText();
        over.setFontAndSize(bf, 10);    // set font and size
        over.setTextMatrix(416, 358);   // set x,y position (0,0 is at the bottom left)
        over.showText(fechaInicio);  // set text
        over.endText();

        over.beginText();
        over.setFontAndSize(bf, 10);    // set font and size
        over.setTextMatrix(477, 358);   // set x,y position (0,0 is at the bottom left)
        over.showText(fechaFinal);  // set text
        over.endText();

        over.beginText();
        over.setFontAndSize(bf, 12);    // set font and size
        over.setTextMatrix(537, 358);   // set x,y position (0,0 is at the bottom left)
        over.showText(contratoImprimir);  // set text
        over.endText();

        over.beginText();
        over.setFontAndSize(bf, 12);    // set font and size
        over.setTextMatrix(537, 358);   // set x,y position (0,0 is at the bottom left)
        over.showText(contratoImprimir);  // set text
        over.endText();

        over.beginText();
        over.setFontAndSize(bf, 10);    // set font and size
        over.setTextMatrix(110, 296);   // set x,y position (0,0 is at the bottom left)
        over.showText(nombreCliente);  // set text
        over.endText();

        over.beginText();
        over.setFontAndSize(bf, 10);    // set font and size
        over.setTextMatrix(335, 296);   // set x,y position (0,0 is at the bottom left)
        over.showText(cedulaCliente);  // set text
        over.endText();

        over.beginText();
        over.setFontAndSize(bf, 10);    // set font and size
        over.setTextMatrix(445, 296);   // set x,y position (0,0 is at the bottom left)
        over.showText(lugarExpCliente);  // set text
        over.endText();

        stamper.close();

        try {

            if ((new File(direccion+"BLANCO TEST.pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler "+direccion+"BLANCO TEST.pdf");
                p.waitFor();

            } else {

                System.out.println("File doesn't exists");

            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
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
