/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jose
 */
public class BibliotecaDAO {

    private static final Connection CONEXION = Conexion.getInstance();

    public static int comprar_juego(int idUsuario, int idJuego) {
        Statement st;
        ResultSet res;

        String sql = "select saldo from usuario where id_usuario=" + idUsuario;

        double saldo = 0;
        double precio = 0;

        try {
            st = CONEXION.createStatement();
            res = st.executeQuery(sql);

            if (res.next()) {
                saldo = res.getDouble("saldo");
            }

            sql = "select precio from juego where id_juego =" + idJuego;

            res = st.executeQuery(sql);

            if (res.next()) {
                precio = res.getDouble("precio");
            }

            if (saldo >= precio) {
                // Compra
                sql = "start transaction";
                st.executeQuery(sql);

                double resta = saldo - precio;
                sql = "update usuario set saldo=" + resta + " where id_usuario=" + idUsuario;
                st.executeUpdate(sql);

                sql = "insert into biblioteca (id_usuario,id_juego) values(" + idUsuario + "," + idJuego + ")";
                st.executeUpdate(sql);

                sql = "commit";
                st.executeQuery(sql);

            } else {
                st.close();
                return 0;
            }
            st.close();
        } catch (SQLException e) {
            return -1;
        }
        return 1;
    }
}
