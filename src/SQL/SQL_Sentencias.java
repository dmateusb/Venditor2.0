/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import logic.Caja;
import logic.Descuentos;
import logic.Usuario;

/**
 *
 * @author Tacho
 */
public class SQL_Sentencias {
    //En esta clase estarán las sentencias de SQL que se utilizarán para controlar la BD
    //
    private SQL_Conexion con= new SQL_Conexion(this.user,this.pass);
    //private SQL_Conexion con;
    PreparedStatement ps;
    ResultSet res;
    private String user="root";
    private String pass="";


    public SQL_Sentencias(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public SQL_Sentencias() {

    }

    public  boolean crearUsuarioBd(String user, String password) throws SQLException {
        boolean flag=false;
        try {
            String insert="CREATE USER '"+user+"'@localhost IDENTIFIED BY '"+password+"';";
            ps = con.conectado().prepareStatement(insert);
            ps.execute();
            insert="GRANT ALL PRIVILEGES ON *.* TO '"+user+"'@localhost IDENTIFIED BY '"+password+"';";
            ps=con.conectado().prepareStatement(insert);
            ps.execute();
            ps.close();
            flag=true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  flag;
    }

    public  boolean eliminarUsuarioBd(String user) throws SQLException {
        boolean flag=false;
        try {
            String insert="DROP USER '"+user+"'@localhost;";
            ps = con.conectado().prepareStatement(insert);
            ps.execute();
            flag=true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  flag;
    }

    public  boolean drop(String table, String column, String value) throws SQLException {
        boolean flag=false;
        try {
            String insert="DELETE from "+table+" WHERE "+column+" = '"+value+"';";
            ps = con.conectado().prepareStatement(insert);
            ps.execute();
            flag=true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  flag;
    }

    public boolean insertar(String datos[], String insert) {
        boolean estado = false;
        try {
            ps = con.conectado().prepareStatement(insert);
            for (int i = 0; i <= datos.length - 1; i++) {
                ps.setString(i + 1, datos[i]);
            }
            ps.execute();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }
    public boolean insertarEgresoRetroventa(String[] datos, String insert) {
        String descripcion=datos[0];
        float ingreso=Float.parseFloat(datos[1]);
        float egreso=Float.parseFloat(datos[2]);
        float utilidad=Float.parseFloat(datos[3]);
        float total=Float.parseFloat(datos[4]);
        boolean estado = false;
        try {
            ps = con.conectado().prepareStatement(insert);
            ps.setString(1,descripcion);
            ps.setFloat(2,ingreso);
            ps.setFloat(3,egreso);
            ps.setFloat(4,utilidad);
            ps.setFloat(5,total);
            ps.setString(6,user);
            ps.execute();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }
    //Método de inserción para los artículos de Oro
    //Este método inserta la variable peso
    public String InsertarNuevoArticulo(String categoria, String subcategoria, String descripcion, Double peso,
        int valor, String usuario){
        int Id=0;
        String[] columnas={"Id"};
        Object[][] resultado = GetTabla(columnas, "articulos", "select Id FROM articulos;");
        if(resultado.length==0){
            Id = 1;
        }else {
            Id = Integer.parseInt(resultado[resultado.length-1][0].toString().substring(1))+1;
        }
        String IdArticulo = String.valueOf(Id);
        while (IdArticulo.length()<7){
            IdArticulo = "0"+IdArticulo;
        }
        IdArticulo = "A"+IdArticulo;
        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.articulos(Id,Categoria,Subcategoria,Descripcion,Peso, Valor, Estado,Usuario) values (?,?,?,?,?,?,?,?)");
            ps.setString(1, IdArticulo);
            ps.setString(2, categoria);
            ps.setString(3, subcategoria);
            ps.setString(4, descripcion);
            ps.setDouble(5, peso);
            ps.setInt(6, valor);
            ps.setString(7,"Vigente");
            ps.setString(8, usuario);
            ps.execute();
            con.desconectar();
            return IdArticulo;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo contrato."
                    + " Por favor verifica la información ingresada.");
            alert1.setTitle("Error al crear el contrato");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el contrato");

        } finally {
            con.desconectar();

        }
         return IdArticulo;
    }

    //Método de inserción para los artículos de Oro
    //Este método no inserta la variable peso
    public String InsertarNuevoArticulo(String categoria, String subcategoria, String descripcion, int valor, String usuario){
        int Id=0;
        String[] columnas={"Id"};
        Object[][] resultado = GetTabla(columnas, "articulos", "select Id FROM articulos;");
        if(resultado.length==0){
            Id = 1;
        }else {
            Id = Integer.parseInt(resultado[resultado.length-1][0].toString().substring(1))+1;
        }
        String IdArticulo = String.valueOf(Id);
        while (IdArticulo.length()<7){
            IdArticulo = "0"+IdArticulo;
        }
        IdArticulo = "A"+IdArticulo;

        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.articulos(Id,Categoria,Subcategoria,Descripcion,Valor, Estado,Usuario) values (?,?,?,?,?,?,?)");
            ps.setString(1, IdArticulo);
            ps.setString(2, categoria);
            ps.setString(3, subcategoria);
            ps.setString(4, descripcion);
            ps.setInt(5, valor);
            ps.setString(6,"Vigente");
            ps.setString(7, usuario);
            ps.execute();
            con.desconectar();
            return IdArticulo;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo contrato."
                    + " Por favor verifica la información ingresada.");
            alert1.setTitle("Error al crear el contrato");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el contrato");

        } finally {
            con.desconectar();

        }
        return IdArticulo;
    }

    public boolean InsertarNuevoContrato(String cedulaString, String articulo, int valor,Double porcentaje, String vencimiento, String usuario) throws SQLException{
        int Id = 0;
        String cedulaInt = cedulaString.replace(".", "");
        int cedula = Integer.valueOf(cedulaInt);
        String[] columnas={"Numero_contrato"};
        Object[][] resultado = GetTabla(columnas, "contratos", "SELECT Numero_contrato FROM contratos ORDER BY Numero_contrato ASC;");
        if(resultado.length==0){
            Id = 1;
        }else {
            Id = Integer.parseInt(resultado[resultado.length-1][0].toString().substring(1))+1;
        }
        String IdContrato = String.valueOf(Id);
        while (IdContrato.length()<7){
            IdContrato = "0"+IdContrato;
        }
        IdContrato = "C"+IdContrato;
        System.out.println(IdContrato);

        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.contratos (Numero_contrato,Cedula,Articulo,Valor,Porcentaje,Fecha_final,Usuario) values (?,?,?,?,?,?,?)");
            ps.setString(1, IdContrato);
            ps.setInt(2, cedula);
            ps.setString(3, articulo);
            ps.setInt(4, valor);
            ps.setDouble(5, porcentaje);
            ps.setString(6, vencimiento);
            ps.setString(7, usuario);
            ps.execute();
            con.desconectar();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Se creo el nuevo contrato correctamente");
            alert1.setTitle("Contrato nuevo");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            return true;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo contrato."
                    + " Por favor verifica la información ingresada.");
            alert1.setTitle("Error al crear el contrato");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el contrato");
            return false;
        } finally {
            con.desconectar();
        }
    }

    public boolean InsertarEstadoCaja(String Estado, String usuario){
        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.estado_caja (Estado,Usuario) " +
                    "values (?,?)");
            ps.setString(1, Estado);
            ps.setString(2, usuario);
            ps.execute();
            con.desconectar();
            System.out.println("Inserta el nuevo estado de la caja");
//            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//            alert1.setContentText("Se renovó el nuevo contrato correctamente");
//            alert1.setTitle("Contrato nuevo");
//            alert1.setHeaderText(null);
//            alert1.showAndWait();
            return true;
        } catch (SQLException ex) {
            System.out.println("algo salió mal con el nuevo estado de la caja");
            return false;
        } finally {
            con.desconectar();
            return true;
        }





    }




    public String InsertarContratoRenovado(String cedulaString, String articulo, int valor,Double porcentaje,int renovaciones, String vencimiento, String usuario) throws SQLException{
        int Id = 0;
        String cedulaInt = cedulaString.replace(".", "");
        int cedula = Integer.valueOf(cedulaInt);

        String[] columnas={"Numero_contrato"};
        Object[][] resultado = GetTabla(columnas, "contratos", "SELECT Numero_contrato FROM contratos ORDER BY Numero_contrato ASC;");
        if(resultado.length==0){
            Id = 1;
        }else {
            Id = Integer.parseInt(resultado[resultado.length-1][0].toString().substring(1))+1;
        }
        String IdContrato = String.valueOf(Id);
        while (IdContrato.length()<7){
            IdContrato = "0"+IdContrato;
        }
        IdContrato = "C"+IdContrato;
        //System.out.println(IdContrato);

        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.contratos (Numero_contrato,Cedula,Articulo,Valor,Porcentaje,Renovaciones,Fecha_final,Usuario) " +
                    "values (?,?,?,?,?,?,?,?)");
            ps.setString(1, IdContrato);
            ps.setInt(2, cedula);
            ps.setString(3, articulo);
            ps.setInt(4, valor);
            ps.setDouble(5, porcentaje);
            ps.setInt(6,renovaciones);
            ps.setString(7, vencimiento);
            ps.setString(8, usuario);
            ps.execute();
            con.desconectar();
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Se renovó el nuevo contrato correctamente");
            alert1.setTitle("Contrato nuevo");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            return IdContrato;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo contrato."
                    + " Por favor verifica la información ingresada.");
            alert1.setTitle("Error al crear el contrato");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el contrato");
            return "";
        } finally {
            con.desconectar();
        }
    }
    
    public boolean InsertarNuevoCliente(String cedulaString, String nombre, String apellidos, String direccion, String barrio, String telefono1, String telefono2, String correo, String usuario) throws SQLException{
        try {

            String cedulaInt = cedulaString.replace(".", "");
            int cedula = Integer.valueOf(cedulaInt);



            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.clientes (Cedula,Nombre,Apellidos,Direccion,Barrio,Telefono1,Telefono2,Correo,Usuario) values (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, apellidos);
            ps.setString(4, direccion);
            ps.setString(5, barrio);
            ps.setString(6, telefono1);
            ps.setString(7, telefono2);
            ps.setString(8, correo);
            ps.setString(9, usuario);
            ps.execute();
            con.desconectar();
            return true;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo usuario."
                    + " Por favor verifica que no sea un usuario existente.");
            alert1.setTitle("Error al crear el usuario");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el visitante");
            return false;
        } finally {
            con.desconectar();
        }
    }

    public boolean insertarDescuento(Descuentos descuentos) throws SQLException{
        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.descuentos (Numero_contrato,Precio_real,Precio_cobrado,Razon,Usuario) values (?,?,?,?,?)");
            ps.setString(1, descuentos.getNumero_contrato());
            ps.setFloat(2,Float.parseFloat(descuentos.getPrecio_real()));
            ps.setFloat(3,Float.parseFloat(descuentos.getPrecio_cobrado()));
            ps.setString(4,descuentos.getRazon());
            ps.setString(5,user);
            ps.execute();
            con.desconectar();
            return true;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal en la conexion a la base de datos");
            alert1.setTitle("Error al crear el usuario");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println(ex.getMessage());
            return false;
        } finally {
            con.desconectar();
        }
    }

    public boolean InsertarRetractoCaja(Caja caja) throws SQLException{
        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.caja (Descripcion, Ingreso, Egreso, Utilidad, Total, Usuario) values (?,?,?,?,?,?) ");
            ps.setString(1, caja.getDescripcion());
            ps.setString(2, caja.getIngreso());
            ps.setString(3,"0");
            ps.setString(4, caja.getUtilidad());
            ps.setString(5, caja.getTotal());
            ps.setString(6, user);
            ps.execute();
            con.desconectar();
            return true;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo movimiento de caja."
                    + " Por favor verifica tus datos.");
            alert1.setTitle("Error al crear el movimiento de caja");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el movimiento de caja");
            return false;
        } finally {
            con.desconectar();
        }
    }

    public boolean InsertarIngresoCaja(Caja caja) throws SQLException{
        try {
            Connection c = con.conectado();
            ps = c.prepareStatement("INSERT into venditor.caja (Descripcion, Ingreso, Egreso, Utilidad, Total, Usuario) values (?,?,?,?,?,?) ");
            ps.setString(1, caja.getDescripcion());
            ps.setString(2, caja.getIngreso());
            ps.setString(3,caja.getEgreso());
            ps.setString(4, caja.getUtilidad());
            ps.setString(5, caja.getTotal());
            ps.setString(6, user);
            ps.execute();
            con.desconectar();
            return true;
        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Algo salió mal y no se pudo crear el nuevo movimiento de caja."
                    + " Por favor verifica tus datos.");
            alert1.setTitle("Error al crear el movimiento de caja");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            System.err.println("Error al insertar el movimiento de caja");
            return false;
        } finally {
            con.desconectar();
        }
    }
    
    public boolean InsertarFotoCliente(byte[] foto, String cedulaString) throws SQLException{

        String cedulaInt = cedulaString.replace(".", "");
        int cedula = Integer.valueOf(cedulaInt);

        try {
            Connection c = con.conectado();
            PreparedStatement ps = c.prepareStatement("UPDATE venditor.clientes SET Foto=? WHERE Cedula = ?;");
            ps.setBytes(1, foto);
            ps.setInt(2, cedula);

            ps.execute();
            con.desconectar();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al guardar la foto");
            return false;
        } finally {
            con.desconectar();
        }
    }

    public boolean InsertarHuellaCliente(String cedulaString, ByteArrayInputStream huella, Integer tamañoHuella) throws SQLException {
        String cedula = cedulaString.replace(".","");
        try {
            Connection c = con.conectado();
            //ingresando huella
            PreparedStatement ps = c.prepareStatement("UPDATE venditor.clientes SET huella=? WHERE Cedula = ?;");
            ps.setBinaryStream(1, huella, tamañoHuella);
            ps.setString(2, cedula);
            ps.execute();
            con.desconectar();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al grabar huella");
            return false;
        } finally {
            con.desconectar();
        }
    }

    public boolean update(String sql) {
        boolean estado = false;
        try {
            ps = con.conectado().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            estado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return estado;
    }

    public Object[][] GetTabla(String colName[], String tabla, String sql) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("select count(*) as total from " + tabla);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][colName.length];
        String col[] = new String[colName.length];

        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    col[j] = res.getString(colName[j]);
                    data[i][j] = col[j];
                }
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public Object[][] GetHuella(String colName[], String tabla, String sql) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("select count(*) as total from " + tabla);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][colName.length];
        String col[] = new String[colName.length];

        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    col[j] = res.getString(colName[j]);
                    data[i][j] = col[j];
                }
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public byte[] GetFoto(String colName, String tabla, String sql) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("select count(*) as total from " + tabla);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[] data = new String[0][0];
        byte[] bytes = null;
        String col = colName;

        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                Blob blob = res.getBlob(colName);
                if (blob != null) {
                    bytes = blob.getBytes(1, (int) blob.length());
                }
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return bytes;
    }

    public boolean existencias(String campo, String from_where) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("SELECT count(" + campo + ") as total  " + from_where);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }




    public Object[] poblar_combox(String tabla, String nombrecol, String sql) {
        int registros = 0;
        try {
            ps = con.conectado().prepareStatement("SELECT count(*) as total FROM " + tabla);
            res = ps.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[] datos = new Object[registros];
        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            int i = 0;
            while (res.next()) {
                datos[i] = res.getObject(nombrecol);
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }

    public Double datos_totalfactura(String campo, String sql) {
        double data = 0;
        try {
            ps = con.conectado().prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                data = res.getDouble(campo);
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public boolean existeFecha(String fecha, String fuente) {

        try {
            ps = con.conectado().prepareStatement("SELECT fecha FROM " + fuente + " WHERE fecha = '" + fecha + "'");
            res = ps.executeQuery();
            if (res.next()) {
                return true;
            } else {
                res.close();
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SQL_Sentencias.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

//    public ArrayList GetBdTables() throws SQLException {
//        //Object[][] datos;
//        String datos = "";
//        ArrayList<String> arrayTablas = new ArrayList<>();
//        int nRegistros = 0;
//        String nombreColumna = "TABLE_NAME";
//        ps = con.conectado().prepareStatement("SELECT * from Information_Schema.Tables where TABLE_SCHEMA = 'asambleas' and TABLE_NAME like '%bd%'; ");
//        res = ps.executeQuery();
//        while (res.next()) {
//            datos = res.getString(nombreColumna);
//            arrayTablas.add(datos);
//
//        }
//        res.close();
//
//        //res.close();
//        return arrayTablas;
//    }

//    public ArrayList GetNTables() throws SQLException {
//        //Object[][] datos;
//        String datos = "";
//        ArrayList<String> arrayTablas = new ArrayList<>();
//        int nRegistros = 0;
//        String nombreColumna = "TABLE_NAME";
//        ps = con.conectado().prepareStatement("SELECT * from Information_Schema.Tables where TABLE_SCHEMA = 'proyecto_facturacion';");
//        res = ps.executeQuery();
//        while (res.next()) {
//            datos = res.getString(nombreColumna);
//            arrayTablas.add(datos);
//
//        }
//        res.close();
//
//        //res.close();
//        return arrayTablas;
//    }
//
//    public ArrayList GetVotTablesBd(String baseDatos) throws SQLException {
//        //Object[][] datos;
//        String datos = "";
//        ArrayList<String> arrayTablas = new ArrayList<>();
//        int nRegistros = 0;
//        String nombreColumna = "TABLE_NAME";
//        ps = con.conectado().prepareStatement("SELECT * from Information_Schema.Tables where TABLE_SCHEMA = 'asambleas' and TABLE_NAME like '%vot" + baseDatos.substring(2) + "%' ;");
//        res = ps.executeQuery();
//        while (res.next()) {
//            datos = res.getString(nombreColumna);
//            arrayTablas.add(datos);
//
//        }
//        res.close();
//
//        //res.close();
//        return arrayTablas;
//    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public SQL_Conexion getCon() {
        return con;
    }

    public void setCon(SQL_Conexion con) {
        this.con = con;
    }
}
