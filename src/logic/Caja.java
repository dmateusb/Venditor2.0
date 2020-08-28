package logic;

public class Caja {
    private String descripcion;
    private float ingreso;
    private float egreso;
    private float utilidad;
    private float total;

    public Caja() {
    }

    public Caja(String descripcion, float ingreso, float egreso, float utilidad, float total) {
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

    public float getIngreso() {
        return ingreso;
    }

    public void setIngreso(float ingreso) {
        this.ingreso = ingreso;
    }

    public float getEgreso() {
        return egreso;
    }

    public void setEgreso(float egreso) {
        this.egreso = egreso;
    }

    public float getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(float utilidad) {
        this.utilidad = utilidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
