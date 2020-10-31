/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Clases.Profesor;
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
public class CrudProfesor {

    public static List<Profesor> universo() {
        List<Profesor> listProfesores = new ArrayList<>();
        String sql = "SELECT * FROM profesor WHERE activo = true ORDER BY id DESC";

        try (Statement stmt = ConexionBaseDeDatos.CONNECTION.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombres = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                String cui = rs.getString("cui");
                String genero = rs.getString("genero");
                Date fechanacimiento = rs.getDate("fechanacimiento");
                String profesion = rs.getString("profesion");

                Profesor profesor = new Profesor();
                profesor.setId(id);
                profesor.setNombres(nombres);
                profesor.setApellidos(apellido);
                profesor.setCui(cui);
                profesor.setGenero(genero);
                profesor.setFechaDeNacimiento(fechanacimiento);
                profesor.setProfesion(profesion);

                listProfesores.add(profesor);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listProfesores;

    }

    public static Profesor buscarProfesor(Long codigo) {
        Profesor profesor = null;
        String sql = "SELECT * FROM profesor WHERE activo = true and id =" + codigo;

        try (Statement stmt = ConexionBaseDeDatos.CONNECTION.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombres = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                String cui = rs.getString("cui");
                String genero = rs.getString("genero");
                Date fechanacimiento = rs.getDate("fechanacimiento");
                String profesion = rs.getString("profesion");
                

                profesor = new Profesor();
                profesor.setId(id);
                profesor.setNombres(nombres);
                profesor.setApellidos(apellido);
                profesor.setCui(cui);
                profesor.setGenero(genero);
                profesor.setFechaDeNacimiento(fechanacimiento);
                profesor.setProfesion(profesion);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return profesor;
    }

    public static Profesor buscarProfesorPorCui(String cuiSearch, Boolean update, Long profesorId) {
        Profesor profesor = null;
        String sql = "SELECT * FROM profesor WHERE activo = true and cui = ?";
        if (update) {
            sql += "id <> " + profesorId;
        }
        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setString(1, cuiSearch);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombres = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                String cui = rs.getString("cui");
                String genero = rs.getString("genero");

                Date fechanacimiento = rs.getDate("fechanacimiento");

                profesor = new Profesor();
                profesor.setId(id);
                profesor.setNombres(nombres);
                profesor.setApellidos(apellido);
                profesor.setCui(cui);
                profesor.setGenero(genero);
                profesor.setFechaDeNacimiento(fechanacimiento);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return profesor;
    }

    public static Boolean insertar(Profesor profesorInsertar) {
        String sql = " INSERT INTO profesor (activo, nombres, apellidos, "
                + " fechaNacimiento, cui, genero, profesion) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, profesorInsertar.getNombres());
            stmt.setString(3, profesorInsertar.getApellidos());
            stmt.setDate(4, profesorInsertar.getFechaDeNacimiento());
            stmt.setString(5, profesorInsertar.getCui());
            stmt.setString(6, profesorInsertar.getGenero());
            stmt.setString(7, profesorInsertar.getProfesion());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }

    }

    public static Boolean actualizar(Profesor profesorActualizar) {
        String sql = " UPDATE profesor "
                + " SET nombres = ?, apellidos = ?, "
                + " fechaNacimiento = ?, cui = ?, genero = ?, profesion = ? "
                + " WHERE id = ? ";
        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setString(1, profesorActualizar.getNombres());
            stmt.setString(2, profesorActualizar.getApellidos());
            stmt.setDate(3, profesorActualizar.getFechaDeNacimiento());
            stmt.setString(4, profesorActualizar.getCui());
            stmt.setString(5, profesorActualizar.getGenero());
            stmt.setString(6, profesorActualizar.getProfesion());
            stmt.setLong(7, profesorActualizar.getId());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }
    }

    public static Boolean eliminar(Long codigo) {
        String sql = " UPDATE profesor "
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

    public static List<Profesor> profesoresSinCurso() {
        List<Profesor> listProfesores = new ArrayList<>();
        String sql = "select b.* from curso a "
                + "right join profesor b on a.profesor_id = b.id "
                + "where a.id is null and b.activo = true ";

        try (Statement stmt = ConexionBaseDeDatos.CONNECTION.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombres = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                String cui = rs.getString("cui");
                String genero = rs.getString("genero");
                Date fechanacimiento = rs.getDate("fechanacimiento");
                String profesion = rs.getString("profesion");

                Profesor profesor = new Profesor();
                profesor.setId(id);
                profesor.setNombres(nombres);
                profesor.setApellidos(apellido);
                profesor.setCui(cui);
                profesor.setGenero(genero);
                profesor.setFechaDeNacimiento(fechanacimiento);
                profesor.setProfesion(profesion);
                

                listProfesores.add(profesor);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listProfesores;

    }
}
