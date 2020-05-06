package logic;

import com.mysql.jdbc.Blob;

public class Cliente {
    private Integer ColumnaCedulaCliente;
    private String ColumnaNombreCliente;
    private String ColumnaApellidosCliente;
    private String ColumnaDireccionCliente;
    private String ColumnaTelefono1Cliente;
    private String ColumnaTelefono2Cliente;
    private String ColumnaCorreoCliente;
    private String ColumnaPerfilCliente;
    private String ColumnaFechaRegistroCliente;
    private String ColumnaUsuarioCliente;

    public Cliente(Integer columnaCedulaCliente, String columnaNombreCliente, String columnaApellidosCliente, String columnaDireccionCliente, String columnaTelefono1Cliente, String columnaTelefono2Cliente, String columnaCorreoCliente, String columnaPerfilCliente, String columnaFechaRegistroCliente, String columnaUsuarioCliente) {
        ColumnaCedulaCliente = columnaCedulaCliente;
        ColumnaNombreCliente = columnaNombreCliente;
        ColumnaApellidosCliente = columnaApellidosCliente;
        ColumnaDireccionCliente = columnaDireccionCliente;
        ColumnaTelefono1Cliente = columnaTelefono1Cliente;
        ColumnaTelefono2Cliente = columnaTelefono2Cliente;
        ColumnaCorreoCliente = columnaCorreoCliente;
        ColumnaPerfilCliente = columnaPerfilCliente;
        ColumnaFechaRegistroCliente = columnaFechaRegistroCliente;
        ColumnaUsuarioCliente = columnaUsuarioCliente;
    }

    public Integer getColumnaCedulaCliente() {
        return ColumnaCedulaCliente;
    }

    public void setColumnaCedulaCliente(Integer columnaCedulaCliente) {
        ColumnaCedulaCliente = columnaCedulaCliente;
    }

    public String getColumnaNombreCliente() {
        return ColumnaNombreCliente;
    }

    public void setColumnaNombreCliente(String columnaNombreCliente) {
        ColumnaNombreCliente = columnaNombreCliente;
    }

    public String getColumnaApellidosCliente() {
        return ColumnaApellidosCliente;
    }

    public void setColumnaApellidosCliente(String columnaApellidosCliente) {
        ColumnaApellidosCliente = columnaApellidosCliente;
    }

    public String getColumnaDireccionCliente() {
        return ColumnaDireccionCliente;
    }

    public void setColumnaDireccionCliente(String columnaDireccionCliente) {
        ColumnaDireccionCliente = columnaDireccionCliente;
    }

    public String getColumnaTelefono1Cliente() {
        return ColumnaTelefono1Cliente;
    }

    public void setColumnaTelefono1Cliente(String columnaTelefono1Cliente) {
        ColumnaTelefono1Cliente = columnaTelefono1Cliente;
    }

    public String getColumnaTelefono2Cliente() {
        return ColumnaTelefono2Cliente;
    }

    public void setColumnaTelefono2Cliente(String columnaTelefono2Cliente) {
        ColumnaTelefono2Cliente = columnaTelefono2Cliente;
    }

    public String getColumnaCorreoCliente() {
        return ColumnaCorreoCliente;
    }

    public void setColumnaCorreoCliente(String columnaCorreoCliente) {
        ColumnaCorreoCliente = columnaCorreoCliente;
    }

    public String getColumnaPerfilCliente() {
        return ColumnaPerfilCliente;
    }

    public void setColumnaPerfilCliente(String columnaPerfilCliente) {
        ColumnaPerfilCliente = columnaPerfilCliente;
    }

    public String getColumnaFechaRegistroCliente() {
        return ColumnaFechaRegistroCliente;
    }

    public void setColumnaFechaRegistroCliente(String columnaFechaRegistroCliente) {
        ColumnaFechaRegistroCliente = columnaFechaRegistroCliente;
    }

    public String getColumnaUsuarioCliente() {
        return ColumnaUsuarioCliente;
    }

    public void setColumnaUsuarioCliente(String columnaUsuarioCliente) {
        ColumnaUsuarioCliente = columnaUsuarioCliente;
    }
}
