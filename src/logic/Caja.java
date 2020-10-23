package logic;

public class Caja {
    private int id;
    private String fecha;
    private String usuario;
    private String descripcion;
    private String ingreso;
    private String egreso;
    private String utilidad;
    private String total;

    public Caja() {
        this.ingreso="0";
        this.egreso="0";
        this.utilidad="0";
        this.total="0";
    }

    public Caja(int id,String fecha, String descripcion, String ingreso, String egreso, String utilidad, String total,
                String usuario) {
        this.id=id;
        this.fecha=fecha;
        this.usuario=usuario;
        this.descripcion = descripcion;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.utilidad = utilidad;
        this.total = total;
    }


    public Caja(String descripcion, String ingreso, String egreso, String utilidad, String total) {
        this.descripcion = descripcion;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.utilidad = utilidad;
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
