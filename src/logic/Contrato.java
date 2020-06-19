package logic;

import java.util.Stack;

public class Contrato {
    int cedula;
    String numeroContrato;
    private String articulo;
    private String sobreprecio_real;
    private String sobreprecio_cobrado;
    private String fecha_inicio;
    private String fecha_final;
    private String fecha_vencimiento;
    private String tiempo;
    private String valor;
    private String porcentaje;
    private String renovaciones;
    private String estado;
    private String usuario;
    private String nombre;
    private String apellidos;
    private String descripcion;
    private String vencimiento;

    public Contrato(String numeroContrato, String articulo, String valor, String porcentaje, String renovaciones, String estado) {
        String valorFormateado="";
        Stack<String> stack1 = new Stack<String>();
        Stack<String> stack2 = new Stack<String>();
        for(int i=0;i<valor.length();i++){
            stack1.push(valor.substring(i,i+1));
        }
        int contador=0;
        while(!stack1.isEmpty()){
            valorFormateado = stack1.pop()+valorFormateado;
            contador++;
            if(contador%3==0){
                valorFormateado = "."+valorFormateado;
            }
        }

        this.numeroContrato = numeroContrato;
        this.articulo = articulo;
        this.valor = valorFormateado;
        this.porcentaje = porcentaje;
        this.renovaciones = renovaciones;
        this.estado = estado;
    }

    public Contrato(int cedula, String nombre, String apellidos, String numeroContrato, String descripcion, String vencimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroContrato = numeroContrato;
        this.descripcion = descripcion;
        this.vencimiento = vencimiento;
    }


    String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_final"};

    public Contrato(String numeroContrato, int cedula, String vencimiento) {
        this.cedula = cedula;
        this.numeroContrato = numeroContrato;
        this.vencimiento = vencimiento;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getSobreprecio_real() {
        return sobreprecio_real;
    }

    public void setSobreprecio_real(String sobreprecio_real) {
        this.sobreprecio_real = sobreprecio_real;
    }

    public String getSobreprecio_cobrado() {
        return sobreprecio_cobrado;
    }

    public void setSobreprecio_cobrado(String sobreprecio_cobrado) {
        this.sobreprecio_cobrado = sobreprecio_cobrado;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        //Este método hace que el valor tenga el formato de dinero, agregando un punto cada 3 cifras
        String valorFormateado="";
        Stack<String> stack1 = new Stack<String>();
        Stack<String> stack2 = new Stack<String>();
        for(int i=0;i<valor.length();i++){
            stack1.push(valor.substring(i,i+1));
        }
        int contador=0;
        while(!stack1.isEmpty()){
            valorFormateado = stack1.pop()+valorFormateado;
            contador++;
            if(contador%3==0 && !stack1.isEmpty()){
                valorFormateado = "."+valorFormateado;
            }
        }

        this.valor = valorFormateado;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getRenovaciones() {
        return renovaciones;
    }

    public void setRenovaciones(String renovaciones) {
        this.renovaciones = renovaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
}
