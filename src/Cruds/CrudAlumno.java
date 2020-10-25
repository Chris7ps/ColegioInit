/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Clases.Alumno;
import General.ConexionBaseDeDatos;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class CrudAlumno {

    public static List<Alumno> universo() {
        List<Alumno> listAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno WHERE activo = true";

        try (Statement stmt = ConexionBaseDeDatos.CONNECTION.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombres = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cui = rs.getString("cui");
                String genero = rs.getString("genero");
                String grado = rs.getString("grado");
                Date fechanacimiento = rs.getDate("fechanacimiento");

                Alumno alumno = new Alumno();
                alumno.setId(id);
                alumno.setNombres(nombres);
                alumno.setApellidos(apellido);
                alumno.setCui(cui);
                alumno.setGenero(genero);
                alumno.setGradoAlumno(grado);
                alumno.setFechaDeNacimiento(fechanacimiento);
                
                listAlumnos.add(alumno);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return listAlumnos;

    }

}
