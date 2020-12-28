package logic;

public final class Usuario {

    private static String username;
    private static String password;
    private static String rol;

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        username = username;
    }

    public static  String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        password = password;
    }

    public static String getRol() {
        return rol;
    }

    public static  void setRol(String rol) {
        rol = rol;
    }
}
