/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Clases.Alumno;
import General.ConexionBaseDeDatos;
import java.sql.Date;
import java.sql.PreparedStatement;
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
        String sql = "SELECT * FROM alumno WHERE activo = true ORDER BY id DESC";

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

    public static Alumno buscarAlumno(Long codigo) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE activo = true and id =" + codigo;

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

                alumno = new Alumno();
                alumno.setId(id);
                alumno.setNombres(nombres);
                alumno.setApellidos(apellido);
                alumno.setCui(cui);
                alumno.setGenero(genero);
                alumno.setGradoAlumno(grado);
                alumno.setFechaDeNacimiento(fechanacimiento);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return alumno;
    }

    public static Alumno buscarAlumnoPorCui(String cuiSearch) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE activo = true and cui = ?";

        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setString(1, cuiSearch);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombres = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cui = rs.getString("cui");
                String genero = rs.getString("genero");
                String grado = rs.getString("grado");
                Date fechanacimiento = rs.getDate("fechanacimiento");

                alumno = new Alumno();
                alumno.setId(id);
                alumno.setNombres(nombres);
                alumno.setApellidos(apellido);
                alumno.setCui(cui);
                alumno.setGenero(genero);
                alumno.setGradoAlumno(grado);
                alumno.setFechaDeNacimiento(fechanacimiento);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return alumno;
    }

    public static Boolean insertar(Alumno alumnoInsertar) {
        String sql = " INSERT INTO alumno (activo, nombre, apellido, "
                + " fechaNacimiento, cui, grado, genero) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, alumnoInsertar.getNombres());
            stmt.setString(3, alumnoInsertar.getApellidos());
            stmt.setDate(4, alumnoInsertar.getFechaDeNacimiento());
            stmt.setString(5, alumnoInsertar.getCui());
            stmt.setString(6, alumnoInsertar.getGradoAlumno());
            stmt.setString(7, alumnoInsertar.getGenero());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }

    }

    public static Boolean actualizar(Alumno alumnoActualizar) {
        String sql = " UPDATE alumno "
                + " SET nombre = ?, apellido = ?, "
                + " fechaNacimiento = ?, cui = ?, grado = ?, genero = ? "
                + " WHERE id = ? ";
        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setString(1, alumnoActualizar.getNombres());
            stmt.setString(2, alumnoActualizar.getApellidos());
            stmt.setDate(3, alumnoActualizar.getFechaDeNacimiento());
            stmt.setString(4, alumnoActualizar.getCui());
            stmt.setString(5, alumnoActualizar.getGradoAlumno());
            stmt.setString(6, alumnoActualizar.getGenero());
            stmt.setLong(7, alumnoActualizar.getId());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }
    }

    public static Boolean eliminar(Long codigo) {
        String sql = " UPDATE alumno "
                + " SET activo = false "
                + " WHERE id = ? ";
        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }
    }
    
}
