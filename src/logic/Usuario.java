
package logic;

public  class Usuario {

    private  String username;
    private  String password;
    private  String rol;

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        username = username;
    }

    public   String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        password = password;
    }

    public  String getRol() {
        return rol;
    }

    public   void setRol(String rol) {
        rol = rol;
    }
}