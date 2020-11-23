package logic;

public class Descuentos {

    private String Numero_contrato;
    private String Precio_real;
    private String Precio_cobrado;
    private String Razon;

    public String getNumero_contrato() {
        return Numero_contrato;
    }

    public void setNumero_contrato(String numero_contrato) {
        Numero_contrato = numero_contrato;
    }

    public String getPrecio_real() {
        return Precio_real;
    }

    public void setPrecio_real(String precio_real) {
        Precio_real = precio_real;
    }

    public String getPrecio_cobrado() {
        return Precio_cobrado;
    }

    public void setPrecio_cobrado(String precio_cobrado) {
        Precio_cobrado = precio_cobrado;
    }

    public String getRazon() {
        return Razon;
    }

    public void setRazon(String razon) {
        Razon = razon;
    }

    public Descuentos(String numero_contrato, String precio_real, String precio_cobrado, String razon) {
        Numero_contrato = numero_contrato;
        Precio_real = precio_real;
        Precio_cobrado = precio_cobrado;
        Razon = razon;
    }
}
