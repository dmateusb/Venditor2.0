/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

/**
 *
 * @author Ingeniería
 */
public class ControlBd {
    private SQL_Sentencias sen;
    private String user = "";
    private String pass = "";
    private SQL_Conexion con;
    //Encapsulando User y Pass, de esta forma siempre estará en RAM.
    
    public ControlBd(String user, String pass){
        this.user = user;
        this.pass = pass;
        con = new SQL_Conexion(user, pass);
        sen = new SQL_Sentencias (user, pass);
        
    }
    //Existencias
    //Para comprobar si algún valor específico existe en la tabla buscada. Todas retornan un Boolean
    public boolean ExisteCliente(String cedula){
        return sen.existencias(cedula, " from clientes where Cedula="+cedula+"';");
    }
    public boolean ExisteContrato(String numeroContrato){
        return sen.existencias(numeroContrato, " from contratos where Numero_contrato='"+numeroContrato+"';");
    }
    public boolean ExisteMovimiento(String id){
        return sen.existencias(id, " from caja where Id='"+id+"';");
    }

     //Consultas
    public Object[][] GetClienteNuevoContrato(String cedula){
        String[] columnas={"Cedula","Nombre", "Apellidos","Direccion","Barrio","Telefono1","Telefono2", "Correo","Foto","Perfil","Fecha_registro"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select Cedula, Nombre, Apellidos, Direccion, Barrio, Telefono1, Telefono2, Correo, Foto, Perfil, Fecha_registro FROM clientes Where Cedula='"+cedula+"';");
        return resultado;
    }
    public Object[][] GetContrato(String numeroContrato){
        String[] columnas={"Numero_contrato","Cedula","Articulo", "Sobreprecio_real","Sobreprecio_cobrado","Fecha_inicio","Fecha_final","Tiempo","Valor","Porcentaje","Renovaciones","Estado","Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select * FROM contratos Where Numero_contrato='"+numeroContrato+"';");
        return resultado;
    }
    public Object[][] GetArticulo(String numeroArticulo){
        String[] columnas={"Id","Fecha","Categoria","Subcategoria","Descripcion","Peso","Valor","Estado","Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "articulos", "select * FROM articulos Where Id='"+numeroArticulo+"';");
        return resultado;
    }

    public Object getHuella(String cedula){
        String[] columnas={"Huella"};
        Object[][] resultado = sen.GetHuella(columnas, "clientes", "select * FROM clientes Where Cedula='"+cedula+"';");
        return resultado[0][0];
    }




    public Object[][] ConsultarContrato(){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos");
        return resultado;
    }

    public Object[][] ConsultarContratosVencidos(){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos where Estado = 'Vencido'");
        System.out.println("Entra a la sentencia");
        return resultado;
    }
    public Object[][] ConsultarContratosVigentes(){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos where Estado = 'Vigente'");
        System.out.println("Entra a la sentencia");
        return resultado;
    }

    public Object[][] ConsultarContratosRetractados(){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos where Estado = 'Retractado'");
        System.out.println("Entra a la sentencia");
        return resultado;
    }



    public Object[][] ConsultarContratosVencidosLikeCedula(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vencido' and Cedula like '%"+entrada+"%';");
        return resultado;
    }
    public Object[][] ConsultarContratosVigentesLikeCedula(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vigente' and Cedula like '%"+entrada+"%';");
        return resultado;
    }
    public Object[][] ConsultarContratosRetractadosLikeCedula(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Retractado' and Cedula like '%"+entrada+"%';");
        return resultado;
    }
    public Object[][] ConsultarContratosLikeCedula(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Valor","Porcentaje","Renovaciones","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Cedula like '%"+entrada+"%';");
        return resultado;
    }

    public Object[][] ConsultarContratosWithCedula(String entrada){
        System.out.println(entrada);
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Valor","Porcentaje","Renovaciones","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Cedula = "+entrada+";");

        return resultado;
    }




    public Object[][] ConsultarContratoLikeContrato(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Numero_contrato like '%"+entrada+"%';");
        return resultado;
    }

    public Object[][] ConsultarContratosVencidosLikeContrato(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vencido' and  Numero_contrato like '%"+entrada+"%';");
        return resultado;
    }

    public Object[][] ConsultarContratosVigentesLikeContrato(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vigente' and  Numero_contrato like '%"+entrada+"%';");
        return resultado;
    }

    public Object[][] ConsultarContratosRetractadosLikeContrato(String entrada){
        String[] columnas={"Numero_contrato","Cedula","Articulo","Fecha_inicio","Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Retractado' and  Numero_contrato like '%"+entrada+"%';");
        return resultado;
    }

    public Object[][] ConsultarDescripcionArticulo(String Id){
        String[] columnas={"Descripcion"};
        Object[][] resultado = sen.GetTabla(columnas, "articulos", "select Descripcion from articulos where Id='"+Id+"';");
        return resultado;
    }

    public Object[][] ConsultarNombresCliente(int cedula){
        String[] columnas={"Nombre","Apellidos"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select Nombre, Apellidos from clientes where Cedula='"+cedula+"';");
        return resultado;
    }

    public Object[][] ConsultarCliente(){
        String[] columnas={"Cedula","Nombre","Apellidos","Direccion","Telefono1","Telefono2",
                "Correo","Perfil","Fecha_registro","Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select * FROM clientes");
        return resultado;
    }

    public Object[][] ConsultarClientesLikeCedula(String cedula){
        String[] columnas={"Cedula","Nombre","Apellidos","Direccion","Telefono1","Telefono2",
                "Correo","Perfil","Fecha_registro","Usuario"};
        Object[][] resultado= sen.GetTabla(columnas,"clientes","select * from clientes where cedula like '%"+cedula+"%';");
        return  resultado;
    }

    public Object[][] consultarIdArticulo(String contrato){
        System.out.println(contrato);
        String[] columnas={"Articulo"};
        Object[][] resultado= sen.GetTabla(columnas,"contratos","select Articulo from contratos where Numero_contrato = '"+contrato+"';");
        return  resultado;
    }

    public Object[][] consultarEstado(String contrato){
        System.out.println(contrato);
        String[] columnas={"Estado"};
        Object[][] resultado= sen.GetTabla(columnas,"contratos","select Estado from contratos where Numero_contrato = '"+contrato+"';");
        return  resultado;
    }

    public Object[][] consultarProcentaje(String contrato){
        System.out.println(contrato);
        String[] columnas={"Porcentaje"};
        Object[][] resultado= sen.GetTabla(columnas,"contratos","select Porcentaje from contratos where Numero_contrato = '"+contrato+"';");
        return  resultado;
    }

    public Object[][] consultarRenovaciones(String contrato){
        String[] columnas={"Renovaciones"};
        Object[][] resultado= sen.GetTabla(columnas,"contratos","select Renovaciones from contratos where Numero_contrato = '"+contrato+"';");
        return  resultado;
    }
    
    
    
    

    public byte[] ConsultarFotoCliente(String documento){
        byte[] resultado = sen.GetFoto("Foto", "clientes", "select foto FROM venditor.clientes where Cedula='"+documento+"';");
        return resultado;
    }


//---------------------------UPDATE----------------------///-----------------------///--------

    public boolean UpdateCliente(String nombre, String apellidos, String direccion,
                                 String telefono1, String telefono2, String correo,String cedula){
        String campos[]={nombre,apellidos,direccion,telefono1,telefono2,correo,cedula};
        return sen.insertar(campos, "update clientes set Nombre = ?, Apellidos = ? ," +
                " Direccion = ?, Telefono1 = ?, Telefono2 = ? , Correo = ? where Cedula = ?;");
    }

    public boolean updateEstado_Vencido(String numeroContrato){
        String campos[]={"Vencido",numeroContrato};
        return sen.insertar(campos, "update contratos set Estado = ? where Numero_contrato = ?;");
    }

    public boolean updateEstado_Retractado(String numeroContrato, String vencimiento){
        String campos[]={"Retractado",vencimiento,numeroContrato};
        return sen.insertar(campos, "update contratos set Estado = ?, Fecha_final = ? where Numero_contrato = ?;");
    }


    
}
