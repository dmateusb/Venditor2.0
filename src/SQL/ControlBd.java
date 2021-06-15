/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import gui.HomeController;
import logic.Caja;
import logic.Home;
import logic.Procedimientos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ingeniería
 */
public class ControlBd {
    private SQL_Sentencias sen;
    private String user = "";
    private String pass = "";
    private SQL_Conexion con;
    //Encapsulando User y Pass, de esta forma siempre estará en RAM.

    public ControlBd(String user, String pass) {
        this.user = user;
        this.pass = pass;

    }

    //Manejo Usuarios

    public boolean crearNuevoUsuarioBd(String usuario, String password, String rol) {
        boolean flag = false;
        boolean flag2 = false;
        try {
            flag = sen.crearUsuarioBd(usuario, password);
            flag2 = insertUsuario(usuario, password, rol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag && flag2;
    }

    public boolean eliminarUsuarioBd(String username) {
        boolean flag = false;
        boolean flag2 = false;
        try {
            flag = sen.eliminarUsuarioBd(username);
            flag2 = eliminarUsuario(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag && flag2;
    }


    //INSERTS

    public boolean insertUsuario(String usuario, String password, String rol) {
        String campos[] = {usuario, password, rol};
        return sen.insertar(campos, "INSERT into venditor.usuario(username,password,rol)" +
                " VALUES (?,?,?);");
    }

    public boolean insertEstadoCaja(String estado, String usuario) {
        String campos[] = {estado, usuario};
        return sen.insertarEgresoRetroventa(campos, "INSERT into venditor.estado_caja (Estado,Usuario)" +
                " VALUES (?,?);");
    }

    public boolean insertCaja(Caja caja) {
        if (Double.valueOf(caja.getTotal()) < 0) {
            HomeController.mostrarAlerta("Error", "No se puede completar la transacción porque " +
                    "no hay dinero suficiente en la caja");
            return false;
        }
        String campos[] = {
                caja.getDescripcion(), String.valueOf(caja.getIngreso()),
                String.valueOf(caja.getEgreso()), String.valueOf(caja.getUtilidad()),
                String.valueOf(caja.getTotal()), caja.getTipo()};
        return sen.insertarEgresoRetroventa(campos, "insert into Caja " +
                "(descripcion,ingreso,egreso,utilidad,total,usuario,tipo)" +
                " VALUES (?,?,?,?,?,?,?);");
    }

    //Existencias
    //Para comprobar si algún valor específico existe en la tabla buscada. Todas retornan un Boolean
    public boolean ExisteCliente(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        return sen.existencias(cedula, " from clientes where Cedula=" + cedula + "';");
    }

    public boolean ExisteContrato(String numeroContrato) {
        return sen.existencias(numeroContrato, " from contratos where Numero_contrato='" + numeroContrato + "';");
    }

    public boolean ExisteMovimiento(String id) {
        return sen.existencias(id, " from caja where Id='" + id + "';");
    }

    //Consultas

    public Object[][] consultarUsuario() {
        String[] columnas = {"username", "password", "rol"};
        Object[][] resultado = sen.GetTabla(columnas, "usuario",
                "select * FROM usuario;");
        return resultado;
    }

    public Object[][] consultarUsuarioPorUsername(String username) {
        String[] columnas = {"username", "password", "rol"};
        Object[][] resultado = sen.GetTabla(columnas, "usuario",
                "select * FROM usuario WHERE username like '%" + username + "%';");
        return resultado;
    }

    public Object[][] consultarCaja() {
        SQL_Sentencias sentencias = new SQL_Sentencias(this.user, this.pass);
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sentencias.GetTabla(columnas, "caja",
                "select Id,Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario FROM caja;");
        return resultado;
    }

    public Object[][] consultarCajaAdmin() {
        SQL_Sentencias sentencias = new SQL_Sentencias(this.user, this.pass);
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sentencias.GetTabla(columnas, "caja",
        "select Id, Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario FROM caja" +
        " WHERE Tipo != 'Retracto' AND Tipo != 'Retroventa';");
        return resultado;
    }

    public Object[][] consultarCajaFecha(String fecha) {
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "caja",
                "select Id,Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario FROM caja WHERE DATE(Fecha) = '" + fecha + "' ;");
        return resultado;
    }

    public Object[][] consultarCajaAdminFecha(String fecha) {
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "caja",
                "select Id,Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario " +
                        "FROM caja " +
                        "WHERE DATE(Fecha) = '" + fecha + "' AND Tipo != 'Retracto' AND Tipo != 'Retroventa' ;");
        return resultado;
    }

    public Object[][] consultarCajaAntesDeFecha(String fecha) {
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "caja",
                "select Id,Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario FROM caja WHERE DATE(Fecha) < '" + fecha + "' ;");
        return resultado;
    }

    public Object[][] consultarCajaAdminAntesDeFecha(String fecha) {
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "caja",
                "select Id,Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario " +
                        "FROM caja " +
                        "WHERE DATE(Fecha) < '" + fecha + "' " +
                        "AND Tipo!= 'Rectracto' AND Tipo!= 'Retroventa';");
        return resultado;
    }

    public Object[][] consultarCajaId(String id) {
        String[] columnas = {"Id", "Fecha", "Descripcion", "Ingreso", "Egreso", "Utilidad", "Total", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "caja",
                "select Id,Fecha,Descripcion,Ingreso,Egreso,Utilidad,Total,Usuario FROM caja WHERE Id= " + id + " ;");
        return resultado;
    }

    public float ConsultarTotalCaja() {
        SQL_Sentencias sentencias = new SQL_Sentencias(this.user, this.pass);
        String[] columnas = {"Total"};
        Object[][] resultado = sentencias.GetTabla(columnas, "caja",
                "select Total FROM caja ORDER BY id DESC LIMIT 1;");
        if (resultado.length == 0) return 0;
        String aux = (String) resultado[0][0];
        return Float.parseFloat(aux);
    }

    public Object[][] consultarEstadoCaja(String fecha) {
        String[] columnas = {"Id", "Fecha", "Estado", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "estado_caja",
                "select Id,Fecha,Estado,Usuario FROM estado_caja WHERE DATE(Fecha) = '" + fecha + "' ;");
        return resultado;
    }

    public Object[][] GetClienteNuevoContrato(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Cedula", "Nombre", "Apellidos", "Direccion", "Barrio", "Telefono1", "Telefono2", "Correo", "Foto", "Perfil", "Fecha_registro"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select Cedula, Nombre, Apellidos, Direccion, Barrio, Telefono1, Telefono2, Correo, Foto, Perfil, Fecha_registro FROM clientes Where Cedula='" + cedula + "';");
        return resultado;
    }

    public Object[][] GetContrato(String numeroContrato) {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Sobreprecio_real", "Sobreprecio_cobrado", "Fecha_inicio", "Fecha_final", "Tiempo", "Valor", "Porcentaje", "Renovaciones", "Estado", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select * FROM contratos Where Numero_contrato='" + numeroContrato + "';");
        return resultado;
    }

    public Object[][] GetArticulo(String numeroArticulo) {
        String[] columnas = {"Id", "Fecha", "Categoria", "Subcategoria", "Descripcion", "Peso", "Valor", "Estado", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "articulos", "select * FROM articulos Where Id='" + numeroArticulo + "';");
        return resultado;
    }

    public Object getHuella(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Huella"};
        Object[][] resultado = sen.GetHuella(columnas, "clientes", "select * FROM clientes Where Cedula='" + cedula + "';");
        return resultado[0][0];
    }


    public Object[][] ConsultarContrato() {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos");
        return resultado;
    }

    public Object[][] ConsultarContratosVencidos() {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos where Estado = 'Vencido'");
        System.out.println("Entra a la sentencia");
        return resultado;
    }

    public Object[][] ConsultarContratosVigentes() {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos where Estado = 'Vigente'");
        return resultado;
    }

    public Object[][] ConsultarContratosRetractados() {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Numero_contrato, Cedula, Articulo, Fecha_inicio, Estado FROM contratos where Estado = 'Retractado'");
        System.out.println("Entra a la sentencia");
        return resultado;
    }


    public Object[][] ConsultarContratosVencidosLikeCedula(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vencido' and Cedula like '%" + cedula + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosVigentesLikeCedula(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vigente' and Cedula like '%" + cedula + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosRetractadosLikeCedula(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Retractado' and Cedula like '%" + cedula + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosLikeCedula(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Valor", "Porcentaje", "Renovaciones", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Cedula like '%" + cedula + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosWithCedula(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Valor", "Porcentaje", "Renovaciones", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Cedula = " + cedula + ";");

        return resultado;
    }


    public Object[][] ConsultarContratoLikeContrato(String entrada) {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Numero_contrato like '%" + entrada + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosVencidosLikeContrato(String entrada) {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vencido' and  Numero_contrato like '%" + entrada + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosVigentesLikeContrato(String entrada) {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Vigente' and  Numero_contrato like '%" + entrada + "%';");
        return resultado;
    }

    public Object[][] ConsultarContratosRetractadosLikeContrato(String entrada) {
        String[] columnas = {"Numero_contrato", "Cedula", "Articulo", "Fecha_inicio", "Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select * from contratos where Estado = 'Retractado' and  Numero_contrato like '%" + entrada + "%';");
        return resultado;
    }

    public Object[][] ConsultarDescripcionArticulo(String Id) {
        String[] columnas = {"Descripcion"};
        Object[][] resultado = sen.GetTabla(columnas, "articulos", "select Descripcion from articulos where Id='" + Id + "';");
        return resultado;
    }

    public byte[] ConsultarFotoCliente(String documento) {
        byte[] resultado = sen.GetFoto("Foto", "clientes", "select foto FROM venditor.clientes where Cedula='" + documento + "';");
        return resultado;
    }

    public Object[][] ConsultarNombresCliente(String cedulaString) {
        String cedulaInt = cedulaString.replace(".", "");
        int cedula = Integer.valueOf(cedulaInt);
        String[] columnas = {"Nombre", "Apellidos"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select Nombre, Apellidos from clientes where Cedula='" + cedula + "';");
        return resultado;
    }

    public Object[][] ConsultarDescuentos() {
        String[] columnas = {"Id", "Numero_contrato", "Precio_real", "Precio_cobrado", "Razon", "Usuario"};
        Object[][] resultado = sen.GetTabla(columnas, "descuentos", "select * FROM descuentos");
        return resultado;
    }

    public boolean insertarFotografia(byte[] foto, String cedula) throws SQLException {

        return sen.InsertarFotoCliente(foto, cedula);
    }

    public Object[][] ConsultarCliente() {
        String[] columnas = {"Cedula", "Nombre", "Apellidos", "Direccion", "Telefono1", "Telefono2",
                "Correo", "Perfil", "Fecha_registro", "Usuario", "Huella", "Foto"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select * FROM clientes");
        return resultado;
    }

    public boolean insertarEgresocaja(Caja caja) throws SQLException {
        return sen.InsertarIngresoCaja(caja);
    }

    public String consultarIdArticulo(String contrato) {
        System.out.println(contrato);
        String[] columnas = {"Articulo"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Articulo from contratos where Numero_contrato = '" + contrato + "';");
        return resultado[0][0].toString();
    }

    public Object[][] consultarEstado(String contrato) {
        System.out.println(contrato);
        String[] columnas = {"Estado"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Estado from contratos where Numero_contrato = '" + contrato + "';");
        return resultado;
    }

    public String consultarSubcategoria(String idarticulo) {
        String[] columnas = {"Subcategoria"};
        Object[][] resultado = sen.GetTabla(columnas, "articulos", "select Subcategoria from articulos" +
                " where id= '" + idarticulo + "';");
        return resultado[0][0].toString();
    }

    public Object[][] consultarProcentaje(String contrato) {
        System.out.println(contrato);
        String[] columnas = {"Porcentaje"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Porcentaje from contratos where Numero_contrato = '" + contrato + "';");
        return resultado;
    }

    public Object[][] consultarRenovaciones(String contrato) {
        String[] columnas = {"Renovaciones"};
        Object[][] resultado = sen.GetTabla(columnas, "contratos", "select Renovaciones from contratos where Numero_contrato = '" + contrato + "';");
        return resultado;
    }


    public Object[][] ConsultarClientesLikeCedula(String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String[] columnas = {"Cedula", "Nombre", "Apellidos", "Direccion", "Telefono1", "Telefono2",
                "Correo", "Perfil", "Fecha_registro", "Usuario", "Huella", "Foto"};
        Object[][] resultado = sen.GetTabla(columnas, "clientes", "select * from clientes where cedula like '%" + cedula + "%';");
        return resultado;
    }


//---------------------------UPDATE----------------------///-----------------------///--------

    public boolean UpdateCliente(String nombre, String apellidos, String direccion,
                                 String telefono1, String telefono2, String correo, String cedulaString) {
        String cedula = cedulaString.replace(".", "");
        String campos[] = {nombre, apellidos, direccion, telefono1, telefono2, correo, cedula};
        return sen.insertar(campos, "update clientes set Nombre = ?, Apellidos = ? ," +
                " Direccion = ?, Telefono1 = ?, Telefono2 = ? , Correo = ? where Cedula = ?;");
    }

    public boolean updateEstado_Vencido(String numeroContrato) {
        String campos[] = {"Vencido", numeroContrato};
        return sen.insertar(campos, "update contratos set Estado = ? where Numero_contrato = ?;");
    }

    public boolean updateEstado_Retractado(String numeroContrato, String vencimiento) {
        String campos[] = {"Retractado", vencimiento, numeroContrato};
        return sen.insertar(campos, "update contratos set Estado = ?, Fecha_final = ? where Numero_contrato = ?;");
    }

    public boolean updateSobreprecio(String numeroContrato, String SobrePrecioRealPuntos, String SobrePrecioCobradoPuntos) {
        String SobrePrecioReal = SobrePrecioRealPuntos.replace(".", "");
        String SobrePrecioCobrado = SobrePrecioCobradoPuntos.replace(".", "");

        String campos[] = {SobrePrecioReal, SobrePrecioCobrado, numeroContrato};
        return sen.insertar(campos, "update contratos set Sobreprecio_real = ?, Sobreprecio_cobrado = ? where Numero_contrato = ?;");
    }

    //----------DELETES--------------
    private boolean eliminarUsuario(String username) throws SQLException {
        return sen.drop("usuario", "username", username);
    }


    public SQL_Sentencias getSen() {
        return sen;
    }

    public void setSen(SQL_Sentencias sen) {
        this.sen = sen;
    }

    public SQL_Conexion getCon() {
        return con;
    }

    public void setCon(SQL_Conexion con) {
        this.con = con;
    }
}
