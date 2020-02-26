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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Rosa
 */
public class JuegoDAO {
     private static final Connection CONEXION = Conexion.getInstance();
    
    public static void insertar_usuario(
            String nombre_juego,
            String sistema_operativo,
            String tipo,
            String descripcion,
            int pegi,
            double precio
    ) {
        String sql = "insert into juego(nombre_juego, sistema_operativo, tipo, descripcion, pegi, precio) values (?,?,?,?,?,?)";

        PreparedStatement prest;

        try {
            prest = CONEXION.prepareStatement(sql);

            prest.setString(1, nombre_juego);
            prest.setString(2, sistema_operativo);
            prest.setString(3, tipo);
            prest.setString(4, descripcion);
            prest.setInt(5, pegi);
            prest.setDouble(6, precio);

            prest.executeUpdate();

            prest.close();

        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla usuario");
            System.out.println(e);
        }
    }
    
    
     public static ArrayList<JuegoVO> consultarJuegos(){
        Statement st;
        ResultSet res;
        ArrayList<JuegoVO> lista = new ArrayList();
        
        // Guardo la consulta SQL realizar en una cadena
        String sql="select * from juego";

        try {
            
            // Preparamos Statement
            st = CONEXION.createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()){
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
}
