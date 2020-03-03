/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rosa
 */
public class UsuarioDAO {

    private static final Connection CONEXION = Conexion.getInstance();

    public static void insertar_usuario(
            String nombre,
            String password,
            LocalDate fecha_nacimiento,
            double saldo,
            String nickname
    ) {
        String sql = "insert into usuario(nombre, password, fecha_nacimiento, saldo, nickname) values (?,?,?,?,?)";

        PreparedStatement prest;

        try {
            prest = CONEXION.prepareStatement(sql);

            prest.setString(1, nombre);
            prest.setString(2, toMd5(password));
            prest.setObject(3, fecha_nacimiento);
            prest.setDouble(4, saldo);
            prest.setString(5, nickname);

            prest.executeUpdate();

            prest.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla usuario");
            System.out.println(e);
        }
    }

    public static String toMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static int login_Usuario(String nombre, String password) {
        ResultSet res;
        int resultado = -1;

        String sql = "select count(*) as cuentaUsuario from usuario where nickname = ? and password = ?";
        PreparedStatement prest;

        try {
            prest = CONEXION.prepareStatement(sql);

            prest.setString(1, nombre);
            prest.setString(2, toMd5(password));

            res = prest.executeQuery();

            if (res.next()) {
                resultado = res.getInt("cuentaUsuario");
            }

            prest.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla usuario");
            System.out.println(e);
        }

        return resultado;
    }

    public static UsuarioVO busca_usuario_nickcame(String nickname) {
        Statement st;
        ResultSet res;

        String sql = "select * from usuario where nickname='" + nickname + "'";

        UsuarioVO usuario = new UsuarioVO();
        try {

            st = CONEXION.createStatement();

            res = st.executeQuery(sql);
            if (res.next()) {
                usuario.setId_usuario(res.getInt("id_usuario"));
                usuario.setNombre(res.getString("nombre"));
                usuario.setSaldo(res.getDouble("saldo"));
                usuario.setNickname(res.getString("nickname"));
                usuario.setPass(res.getString("password"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNueva = LocalDate.parse(res.getString("fecha_nacimiento"), formatter);

                usuario.setFecha_nacimiento(fechaNueva);
            }
            // Cerramos el recurso PreparedStatement 
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla Usuario");
            System.out.println(e);
        }

        return usuario;
    }

    public static int updateUsuario(String nombre, String password, LocalDate Fecha, String nick, int id) {

        String existe_pass = "".equals(password) ? "" : ", password=?";
        String sql = "update usuario set nombre = ?,fecha_nacimiento = ?, nickname=?" + existe_pass + "  where id_usuario=" + id;

        try {
            PreparedStatement prest = CONEXION.prepareStatement(sql);
            // Establecemos los parámetros de la sentencia
            prest.setString(1, nombre);
            prest.setObject(2, Fecha);
            prest.setString(3, nick);
            if (!"".equals(password)) {
                prest.setString(4, toMd5(password));
            }

            //return prest.toString();
            prest.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return -1;
        }

    }

    public static int existe_nick(String nick, int id_usuario) {
        Statement st;
        ResultSet res;

        String sql = "select count(*) as contador from usuario where nickname='" + nick + "' and id_usuario!=" + id_usuario;

        try {

            //return prest.toString();
            st = CONEXION.createStatement();

            res = st.executeQuery(sql);
            res.next();
            // return sql;
            return res.getInt("contador");

        } catch (SQLException e) {
            return -1;
        }
    }

    public static int addSaldo(double saldo, int id_usuario) {

        //  double total = saldo + saldoActual;
        String sql = "update usuario set saldo=saldo+" + saldo + " where id_usuario=" + id_usuario;

        try {
            PreparedStatement prest = CONEXION.prepareStatement(sql);
            // Establecemos los parámetros de la sentencia

            //return prest.toString();
            prest.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

}
