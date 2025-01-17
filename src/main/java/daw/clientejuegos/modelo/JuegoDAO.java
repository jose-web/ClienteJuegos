/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rosa
 */
public class JuegoDAO {

    private static final Connection CONEXION = Conexion.getInstance();

    public static void insertar_juego(
            int id_usuario,
            String nombre_juego,
            String sistema_operativo,
            String tipo,
            String descripcion,
            int pegi,
            double precio
    ) {

        Statement st;
        ResultSet res;
        String sql;

        PreparedStatement prest;

        try {

            st = CONEXION.createStatement();

            // Iniciamos la transacción
            sql = "start transaction";
            st.executeQuery(sql);

            // Insertamos el juego
            sql = "insert into juego(nombre_juego, sistema_operativo, tipo, descripcion, pegi, precio) values (?,?,?,?,?,?)";

            prest = CONEXION.prepareStatement(sql);

            prest.setString(1, nombre_juego);
            prest.setString(2, sistema_operativo);
            prest.setString(3, tipo);
            prest.setString(4, descripcion);
            prest.setInt(5, pegi);
            prest.setDouble(6, precio);

            prest.executeUpdate();

            // Recogemos el último id  ya que este es el id de nuestro juego 
            sql = "select max(id_juego) as ultima_id from juego";

            res = st.executeQuery(sql);
            res.next();

            int id_juego = res.getInt("ultima_id");

            // Creamos la asociación de el juego con su creador
            sql = "insert into creado_por(id_usuario,id_juego) values (?,?)";
            prest = CONEXION.prepareStatement(sql);

            prest.setInt(1, id_usuario);
            prest.setInt(2, id_juego);

            prest.executeUpdate();

            // Hacemos commit para que los cambios sean guardados
            sql = "commit";
            st.executeQuery(sql);

            prest.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla usuario");
            System.out.println(e);
        }
    }

    public static void editar_juego(
            int id_juego,
            String nombre_juego,
            String sistema_operativo,
            String tipo,
            String descripcion,
            int pegi,
            double precio
    ) {
        String sql = "update juego set nombre_juego = ?, sistema_operativo = ?, tipo = ?, descripcion = ?, pegi = ?, precio = ? where id_juego = ?";

        PreparedStatement prest;

        try {
            prest = CONEXION.prepareStatement(sql);

            prest.setString(1, nombre_juego);
            prest.setString(2, sistema_operativo);
            prest.setString(3, tipo);
            prest.setString(4, descripcion);
            prest.setInt(5, pegi);
            prest.setDouble(6, precio);
            prest.setInt(7, id_juego);

            prest.executeUpdate();

            prest.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla usuario");
            System.out.println(e);
        }
    }

    public static ArrayList<JuegoVO> consultarJuegos() {
        Statement st;
        ResultSet res;
        ArrayList<JuegoVO> lista = new ArrayList();

        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from juego";

        try {

            // Preparamos Statement
            st = CONEXION.createStatement();
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()) {
                JuegoVO j = new JuegoVO();
                // Recogemos los datos del turismo, guardamos en un objeto
                j.setId_juego(res.getInt("id_juego"));
                j.setNombre_juego(res.getString("nombre_juego"));
                j.setSistema_operativo(res.getString("sistema_operativo"));
                j.setTipo(res.getString("tipo"));
                j.setDescripcion(res.getString("descripcion"));
                j.setPegi(res.getInt("pegi"));
                j.setPrecio(res.getDouble("precio"));

                //Añadimos el objeto al array
                lista.add(j);
            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla Jugadores");
            System.out.println(e);
        }

        return lista;
    }

    public static ArrayList<JuegoVO> consultarJuegosFiltro(String columna, String valor) {
        Statement st;
        ResultSet res;
        ArrayList<JuegoVO> lista = new ArrayList();

        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from juego where " + columna + "='" + valor + "'";

        try {

            // Preparamos Statement
            st = CONEXION.createStatement();
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()) {
                JuegoVO j = new JuegoVO();
                // Recogemos los datos del turismo, guardamos en un objeto
                j.setNombre_juego(res.getString("nombre_juego"));
                j.setSistema_operativo(res.getString("sistema_operativo"));
                j.setPegi(res.getInt("pegi"));

                //Añadimos el objeto al array
                lista.add(j);
            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla Jugadores");
            System.out.println(e);
        }

        return lista;
    }

    public static JuegoVO buscar_juego(int id_juego) {

        Statement st;
        ResultSet res;
        JuegoVO juego = new JuegoVO();

        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from juego where id_juego=" + id_juego;
        try {

            // Preparamos Statement
            st = CONEXION.createStatement();
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()) {

                // Recogemos los datos del turismo, guardamos en un objeto
                juego.setId_juego(res.getInt("id_juego"));
                juego.setNombre_juego(res.getString("nombre_juego"));
                juego.setSistema_operativo(res.getString("sistema_operativo"));
                juego.setTipo(res.getString("tipo"));
                juego.setDescripcion(res.getString("descripcion"));
                juego.setPegi(res.getInt("pegi"));
                juego.setPrecio(res.getDouble("precio"));
                //Añadimos el objeto al array

            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla Juegos");
            System.out.println(e);
        }

        return juego;

    }

    public static int borrar_juego(int idUsuario, int idJuego) {
        Statement st;

        int propietario = consultarPertenenciaJuegoCreado(idUsuario, idJuego);
        if (propietario == 1) {

            String sql = "";

            try {

                st = CONEXION.createStatement();

                // Iniciamos transacción
                sql = "start transaction";
                st.executeQuery(sql);

                //Hacemos borrado del juego en la tabla biblioteca
                sql = "delete from biblioteca where id_juego =" + idJuego;
                st.executeUpdate(sql);

                //Hacemos borrado del juego en la tabla creado_por
                sql = "delete from creado_por where id_juego =" + idJuego;
                st.executeUpdate(sql);

                //Hacemos borrado del juego en la tabla juego
                sql = "delete from juego where id_juego =" + idJuego;
                st.executeUpdate(sql);

                sql = "commit";
                st.executeQuery(sql);

                st.close();
            } catch (SQLException e) {
                return -1;
            }
        }
        return 1;
    }

    public static ArrayList<JuegoVO> consultarJuegosCreados(int id_usuario) {
        Statement st;
        ResultSet res;
        ArrayList<JuegoVO> lista = new ArrayList();

        // Guardo la consulta SQL realizar en una cadena
        String sql = "select juego.* from juego join creado_por on juego.id_juego = creado_por.id_juego where id_usuario =" + id_usuario;

        try {

            // Preparamos Statement
            st = CONEXION.createStatement();
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()) {
                JuegoVO j = new JuegoVO();
                // Recogemos los datos del turismo, guardamos en un objeto
                j.setId_juego(res.getInt("id_juego"));
                j.setNombre_juego(res.getString("nombre_juego"));
                j.setSistema_operativo(res.getString("sistema_operativo"));
                j.setTipo(res.getString("tipo"));
                j.setDescripcion(res.getString("descripcion"));
                j.setPegi(res.getInt("pegi"));
                j.setPrecio(res.getDouble("precio"));

                //Añadimos el objeto al array
                lista.add(j);
            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta");
            System.out.println(e);
        }

        return lista;
    }

    public static int consultarPertenenciaJuegoCreado(int id_usuario, int id_juego) {
        Statement st;
        ResultSet res;
        ArrayList<JuegoVO> lista = new ArrayList();

        // Guardo la consulta SQL realizar en una cadena
        String sql = "select count(*) as contador from creado_por where id_usuario =" + id_usuario + " and id_juego = " + id_juego;

        try {

            // Preparamos Statement
            st = CONEXION.createStatement();
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            if (res.next()) {
                return res.getInt("contador");
            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta");
            System.out.println(e);
        }

        return -1;
    }

}
