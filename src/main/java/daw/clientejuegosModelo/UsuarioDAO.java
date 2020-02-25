/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegosModelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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

    private static String toMd5(String input) {
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
}
