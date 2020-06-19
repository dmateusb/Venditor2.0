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
    
    
    
    

    public byte[] ConsultarFotoVisitante(String documento){
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













    //-----------------------------DE AQUÍ PARA ABAJO NO USAMOS NINGÚN MÉTODO---------------------------











    public Object[][] ConsultarVehiculo(String placa){
        String[] columnas={"Placa","Marca","Color","Tipo_vehiculo","Serie","Cedula_visitante"};
        Object[][] resultado = sen.GetTabla(columnas, "vehiculo", "select * from vehiculo where Placa='"+placa+"';");
        return resultado;
    }
    public Object[][] ConsultarVehiculoVisitante(String cedula){
        String[] columnas={"Placa","Marca","Color","Tipo_vehiculo","Serie","Cedula_visitante"};
        Object[][] resultado = sen.GetTabla(columnas, "vehiculo", "select * from vehiculo where Cedula_visitante='"+cedula+"';");
        return resultado;
    }
    public Object[][] ConsultarResponsable(String documento){
        String[] columnas={"Cedula_responsable","Nombres","Apellidos","Correo","Direccion"};
        Object[][] resultado = sen.GetTabla(columnas, "responsable", "select * from responsable where Cedula_responsable='"+documento+"';");
        return resultado;
    }
    public Object[][] ConsultarLugar(String referencia){
        String[] columnas={"Referencia","Descripcion","Cedula_responsable"};
        Object[][] resultado = sen.GetTabla(columnas, "lugar", "select * from lugar where Referencia='"+referencia+"';");
        return resultado;
    }
    //Consulta tabla completa
    public Object[][] ConsultarVisitante(){
        String[] columnas={"Cedula_visitante","Nombres","Apellidos",  "Fecha_registro","Equipos","Huella","Tipo_visita", "Fecha_Nacimiento","foto"};
        Object[][] resultado = sen.GetTabla(columnas, "visitante", "select * from visitante;");
        return resultado;
    }
    
    public Object[][] ConsultarVehiculo(){
        String[] columnas={"Placa","Marca","Color","Tipo_vehiculo","Serie","Cedula_visitante"};
        Object[][] resultado = sen.GetTabla(columnas, "vehiculo", "select * from vehiculo;");
        return resultado;
    }
    public Object[][] ConsultarResponsable(){
        String[] columnas={"Cedula_responsable","Nombres","Apellidos","Correo","Direccion"};
        Object[][] resultado = sen.GetTabla(columnas, "responsable", "select * from responsable ;");
        return resultado;
    }
    public Object[][] ConsultarLugar(){
        String[] columnas={"Referencia","Descripcion","Cedula_responsable1","Cedula_responsable2","Cedula_responsable3"};
        Object[][] resultado = sen.GetTabla(columnas, "lugar", "select * from lugar ;");
        return resultado;
    }
    //Consulta tabla por similitud de caracteres-
    public Object[][] ConsultarLikeVisitante(String entrada){
        String[] columnas={"Cedula_visitante","Nombres","Apellidos", "Fecha_registro","Equipos","Huella","Tipo_visita", "Fecha_Nacimiento","foto"};
        Object[][] resultado = sen.GetTabla(columnas, "visitante", "select * from visitante where Cedula_visitante like '%"+entrada+"%';");
        return resultado;
    }
    public Object[][] ConsultarLikeVehiculo(String entrada){
        String[] columnas={"Placa","Marca","Color","Tipo_vehiculo","Serie","Cedula_visitante"};
        Object[][] resultado = sen.GetTabla(columnas, "vehiculo", "select * from vehiculo where Placa like '%"+entrada+"%';");
        return resultado;
    }

    public Object[][] ConsultarLikeLugar(String entrada){
        String[] columnas={"Referencia","Descripcion","Cedula_responsable"};
        Object[][] resultado = sen.GetTabla(columnas, "lugar", "select * from lugar where Referencia like '%"+entrada+"%';");
        return resultado;
    }
    //Conslutas Históricos por empresa
    public Object[][] ConsultarHistoricoPaquetes(String empresa){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Empresa='"+empresa+"';");
        return resultado;
    }
    //Consulta por empresa y fecha(llegada o Entrega)
    public Object[][] ConsultarHistoricoPaquetes(String empresa, String fecha){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Empresa"
                + "='"+empresa+"' and Fecha_llegada like '%"+fecha+"%' or Fecha_entrega like '%"+fecha+"%';");
        return resultado;
    }
    //Consulta Histórico por referencia
    public Object[][] ConsultarHistoricoPaquetesReferencia(String referencia){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Referencia" + "='"+referencia+"';");
        return resultado;
    }
    //Consulta Histórica por referencia y fecha (llegada o entrega)
    public Object[][] ConsultarHistoricoPaquetesReferencia(String referencia, String fecha){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Referencia"
                + "='"+referencia+"' and Fecha_llegada like '%"+fecha+"%' or Fecha_entrega like '%"+fecha+"%';");
        return resultado;
    }
    //Consulta Histórico por tipo
    public Object[][] ConsultarHistoricoPaquetesTipo(String tipo){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Referencia like '%"+tipo+"%';");
        return resultado;
    }
    //Consulta Histórica por referencia y fecha (llegada o entrega)
    public Object[][] ConsultarHistoricoPaquetesTipo(String referencia, String fecha){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Referencia"
                + "='"+referencia+"' and Fecha_llegada like '%"+fecha+"%' or Fecha_entrega like '%"+fecha+"%';");
        return resultado;
    }
    //Conslutas Históricos por empresa
    public Object[][] ConsultarHistoricoVisitas(String empresa){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Empresa='"+empresa+"';");
        return resultado;
    }
    //Consulta por empresa y fecha(llegada o Entrega)
    public Object[][] ConsultarHistoricoVisitas(String empresa, String fecha){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Empresa"
                + "='"+empresa+"' and Fecha like '%"+fecha+"%' or Fecha_salida like '%"+fecha+"%';");
        return resultado;
    }
    //Consulta Histórico por referencia
    public Object[][] ConsultarHistoricoVisitasReferencia(String referencia){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Referencia" + "='"+referencia+"';");
        return resultado;
    }
    //Consulta Histórica por referencia y fecha (llegada o entrega)
    public Object[][] ConsultarHistoricoVisitasReferencia(String referencia, String fecha){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Referencia"
                + "='"+referencia+"' and Fecha like '%"+fecha+"%' or Fecha_salida like '%"+fecha+"%';");
        return resultado;
    }
    //Consulta Histórico por Visitante
    public Object[][] ConsultarHistoricoVisitasVisitante(String documento){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Cedula_visitante='"+documento+"';");
        return resultado;
    }
    //Consulta Histórica por visitante y fecha (llegada o entrega)
    public Object[][] ConsultarHistoricoVisitasVisitante(String referencia, String fecha){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Cedula_visitante"
                + "='"+referencia+"' and Fecha like '%"+fecha+"%' or Fecha_salida like '%"+fecha+"%';");
        return resultado;
    }
    //Consulta Histórico por Vehiculo
    public Object[][] ConsultarHistoricoVisitasVehiculo(String placa){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Placa='"+placa+"';");
        return resultado;
    }
    //Consulta Histórica por Vehiculo y fecha (llegada o entrega)
    public Object[][] ConsultarHistoricoVisitasVehiculo(String placa, String fecha){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Placa"
                + "='"+placa+"' and Fecha like '%"+fecha+"%' or Fecha_salida like '%"+fecha+"%';");
        return resultado;
    }
    
    //Consultas LIKE para históricos paquetes
    
    public Object[][] ConsultarHistoricoLikePaquetes(String empresa){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Empresa like '%"+empresa+"%';");
        return resultado;
    }
    
    public Object[][] ConsultarHistoricoLikePaquetesReferencia(String referencia){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Referencia" + " like '%"+referencia+"%';");
        return resultado;
    }
    
    public Object[][] ConsultarHistoricoLikePaquetesTipo(String tipo){
        String[] columnas={"Empresa","Fecha_llegada","Fecha_entrega", "Destinatario","Tipo","Detalle","Responsable", "Referencia"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_paquetes", "select * from historico_paquetes where Referencia like '%"+tipo+"%';");
        return resultado;
    }
      
    //Consultas LIKE para históricos Visitas
    public Object[][] ConsultarHistoricoVisitas() {
        String[] columnas = {"Fecha", "Fecha_salida", "Cedula_visitante", "Referencia", "Cedula_responsable", "Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "historico_visitas", "select * from historico_visitas where not Fecha_salida = '0000-00-00 00:00:00';");

        return resultado;
    }
    public Object[][] ConsultarHistoricoLikeVisitas(String empresa){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Empresa like '%"+empresa+"%';");
        return resultado;
    }
    public Object[][] ConsultarHistoricoLikeVisitasReferencia(String referencia){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Referencia" + " like '%"+referencia+"%';");
        return resultado;
    }
    public Object[][] ConsultarHistoricoLikeVisitasVisitante(String documento){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Cedula_visitante like '%"+documento+"%';");
        return resultado;
    }
    public Object[][] ConsultarHistoricoLikeVisitasVehiculo(String placa){
        String[] columnas={"Empresa","Fecha","Fecha_salida", "Cedula_visitante","Referencia","Cedula_responsable","Placa"};
        Object[][] resultado = sen.GetTabla(columnas, "hitorico_visitas", "select * from historico_visitas where Placa='"+placa+"';");
        return resultado;
    }
    
    //Registros
    public boolean UpdateEstadoActual(String fecha, String fechaSalida){
        String campos[]={ fechaSalida , fecha};
        System.out.println("Fecha ="+fecha);
        return sen.insertar(campos, "update historico_visitas set Fecha_salida = ? where Fecha = ?;");
    }
    public boolean UpdatePaquetesActual(String fecha, String fechaEntrega){
        String campos[]={fechaEntrega,fecha};
        return sen.insertar(campos, "update historico_paquetes set Fecha_entrega = ? where Fecha_Llegada = ?;");
    }
    public boolean UpdateVehiculo(String marca, String color, String tipo_vehiculo, String serie, String placa){
        String campos[]={marca,color,tipo_vehiculo,serie,placa};
        System.out.println("PLaca ="+placa);
        return sen.insertar(campos, "update vehiculo set Marca = ?, Color =? , Tipo_vehiculo=?, Serie = ? where Placa = ?;");
        
        
    }

    public Object[][] EliminarVehiculo(String entrada){
        String[] columnas={"Placa","Marca","Color","Tipo_vehiculo","Serie","Cedula_visitante"};
        Object[][] resultado = sen.GetTabla(columnas, "vehiculo", "delete * from vehiculo where Placa = '"+entrada+"';");
        return resultado;
    }
    
    public boolean registrarCompras(String proveedor, String descripcion, String monto, String fecha){
        String[] datos = {proveedor, descripcion, monto, fecha};
        return sen.insertar(datos, "insert into compras(proveedor, descripcion, monto, fecha) values(?,?,?,?);");
    }
    
    public boolean RegistrarVisitante(String Cedula_visitante, String Nombres, String Apellidos, String Equipos, String Huella,String Tipo_visita,String Fecha_Nacimiento, String foto){
        String[] datos={Cedula_visitante,Nombres,Apellidos,Equipos,Huella,Tipo_visita, Fecha_Nacimiento,foto};
        return sen.insertar(datos, "insert into visitante(Cedula_visitante,Nombres,Apellidos,Equipos,Huella,Tipo_visita, Fecha_Nacimiento,foto) values(?,?,?,?,?,?,?,?);");
    }
    
    public boolean RegistrarVehiculo(String Placa, String Marca, String Color, String Tipo_vehiculo, String Serie, String Cedula_visitante){
        String[] datos={Placa,Marca,Color,Tipo_vehiculo,Serie,Cedula_visitante};
        return sen.insertar(datos, "insert into vehiculo(Placa,Marca,Color,Tipo_vehiculo,Serie,Cedula_visitante) values(?,?,?,?,?,?);");
    } 
    
    public boolean RegistrarResponsable(String Cedula_responsable, String Nombres, String Apellidos, String Correo, String Direccion){
        String[] datos={Cedula_responsable,Nombres,Apellidos,Correo,Direccion};
        return sen.insertar(datos, "insert into responsable(Cedula_responsable,Nombres,Apellidos,Correo,Direccion) values(?,?,?,?,?);");
    }
    
    public boolean RegistrarLugar(String Referencia, String Descripcion, String Cedula_responsable){
        String[] datos={Referencia,Descripcion,Cedula_responsable};
        return sen.insertar(datos, "insert into lugar(Referencia,Descripcion,Cedula_responsable) values(?,?,?);");
    }
            
    public boolean RegistrarHistoricoVisitas(String Fecha,String Fecha_salida, String Cedula_visitante, String Referencia, String Cedula_responsable, String Placa){
        String[] datos={Fecha,Fecha_salida, Cedula_visitante,Referencia,Cedula_responsable,Placa};
        return sen.insertar(datos, "insert into historico_visitas(Fecha,Fecha_salida, Cedula_visitante,Referencia,Cedula_responsable,Placa) values(?,?,?,?,?,?);");
    }
    
    public boolean RegistrarHistoricoPaquetes(String Empresa, String Fecha_llegada, String Fecha_entrega, String Destinatario, String Tipo, String Detalle, String Responsable, String Referencia){
        String[] datos={Empresa,Fecha_llegada,Fecha_entrega, Destinatario,Tipo,Detalle,Responsable, Referencia};
        return sen.insertar(datos, "insert into historico_paquetes(Empresa,Fecha_llegada,Fecha_entrega, Destinatario,Tipo,Detalle,Responsable, Referencia) values(?,?,?,?,?,?,?,?);");
    }
    public boolean NuevoCliente(String Cedula, String Expedicion, String Nombre, String Apellidos, String Direccion, String Telefono1, String Telefono2,
                                String Correo,String Perfil, String Huella, String Foto, String Fecha_Registro, String Usuario){
        String[] datos={ Cedula,  Expedicion,  Nombre,  Apellidos,  Direccion,  Telefono1,  Telefono2,
                 Correo, Perfil,  Huella,  Foto,  Fecha_Registro,  Usuario};
        return sen.insertar(datos, "insert into clientes( Cedula,  Expedicion,  Nombre,  Apellidos,  Direccion,  Telefono1,  Telefono2, Correo, Perfil,  Huella,  Foto,  Fecha_registro,  Usuario) values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
    }
    
    
    //Arqueos
    
}
