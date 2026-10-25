
import java.sql.*;

public class Usuarios {

    static String todos = "SELECT * FROM usuarios ORDER BY nombreu";
    static String id = "SELECT idusuarios FROM usuarios ORDER BY idusuarios";

    String idusuarios, nombre, usuario, nip, rol, estado;

    public Usuarios() {
    }

    public Usuarios(String id, String nombre, String usuario,
            String nip, String rol, String estado) {
        this.idusuarios = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.nip = nip;
        this.rol = rol;
        this.estado = estado;
    }

    // BUSCAR USUARIO PARA INICIAR SESION
    public boolean iniciarSesion(Connection con, String usuario, String nip) {

        try {
            String sql = "SELECT * FROM usuarios WHERE nom_usu = ? AND nip = ? AND estadou = 'Activo'";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, nip);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.idusuarios = rs.getString("idusuarios");
                this.nombre = rs.getString("nombreu");
                this.usuario = rs.getString("nom_usu");
                this.nip = rs.getString("nip");
                this.rol = rs.getString("rol");
                this.estado = rs.getString("estadou");

                rs.close();
                ps.close();
                return true;
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    // BORRAR USUARIO
    public int borrar(Connection con) {
        int resultado = 0;

        try {
            String sql = "DELETE FROM usuarios WHERE idusuarios = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idusuarios);

            resultado = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return resultado;
    }

    // ACTUALIZAR USUARIO
    public int conectar(Connection con) {
        int resultado = 0;

        try {
            String sql = "UPDATE usuarios SET nombreu = ?, nom_usu = ?, nip = ?, rol = ?, estadou = ? WHERE idusuarios = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, usuario);
            ps.setString(3, nip);
            ps.setString(4, rol);
            ps.setString(5, estado);
            ps.setString(6, idusuarios);

            resultado = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    // GETTERS PARA OBTENER LOS DATOS
    public String getIdusuarios() {
        return idusuarios;
    }
    public String getNombre() {
        return nombre;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getNip() {
        return nip;
    }
    public String getRol() {
        return rol;
    }
    public String getEstado() {
        return estado;
    }
}

    

