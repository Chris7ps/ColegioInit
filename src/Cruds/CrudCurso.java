/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Clases.Curso;
import Clases.Profesor;
import General.ConexionBaseDeDatos;
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
public class CrudCurso {

    public static List<Curso> universo() {
        List<Curso> listCursos = new ArrayList<>();
        String sql = "SELECT a.id as cursoId, a.nombreCurso, "
                + " a.duracionCurso, b.id as profesorId, b.activo, "
                + " b.nombre as nombreProfesor, b.apellido as apellido "
                + " FROM curso a "
                + " JOIN profesor b on a.profesor_id = b.id"
                + " WHERE a.activo = true ORDER BY a.id DESC ";

        try (PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("cursoId");
                String nombre = rs.getString("nombreCurso");
                int duracionCurso = rs.getInt("duracionCurso");

                Profesor profesor = new Profesor();
                profesor.setId(rs.getLong("profesorId"));
                profesor.setNombres(rs.getString("nombreProfesor"));
                profesor.setApellidos(rs.getString("apellido"));

                Curso curso = new Curso();
                curso.setId(id);
                curso.setNombreCurso(nombre);
                curso.setDuracionCurso(duracionCurso);
                curso.setProfesor(profesor);

                listCursos.add(curso);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listCursos;

    }

    public static Curso buscarCurso(Long codigo) {
        Curso curso = null;
        String sql = "SELECT a.id as cursoId, a.nombreCurso, "
                + " a.duracionCurso, b.id as profesorId, b.activo, "
                + " b.nombre as nombreProfesor, b.apellido as apellido "
                + " FROM curso a "
                + " JOIN profesor b ON a.profesor_id = b.id"
                + " WHERE a.activo = true and a.id =" + codigo;

        try (Statement stmt = ConexionBaseDeDatos.CONNECTION.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("cursoId");
                String nombre = rs.getString("nombreCurso");
                int duracionCurso = rs.getInt("duracionCurso");

                Profesor profesor = new Profesor();
                profesor.setId(rs.getLong("profesorId"));
                profesor.setNombres(rs.getString("nombreProfesor"));
                profesor.setApellidos(rs.getString("apellido"));

                curso = new Curso();
                curso.setId(id);
                curso.setNombreCurso(nombre);
                curso.setProfesor(profesor);
                curso.setDuracionCurso(duracionCurso);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return curso;
    }

    public static Boolean insertar(Curso cursoInsertar) {
        String sql = " INSERT INTO curso (activo, nombreCurso, duracionCurso, profesor_id)  "
                + " VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, cursoInsertar.getNombreCurso());
            stmt.setInt(3, cursoInsertar.getDuracionCurso());
            stmt.setLong(4, cursoInsertar.getProfesor().getId());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }

    }

    public static Boolean actualizar(Curso cursoActualizar) {
        String sql = " UPDATE curso "
                + " SET nombreCurso = ?, duracionCurso = ?, "
                + " profesor_id = ? "
                + " WHERE id = ? ";
        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setString(1, cursoActualizar.getNombreCurso());
            stmt.setInt(2, cursoActualizar.getDuracionCurso());
            stmt.setLong(3, cursoActualizar.getProfesor().getId());
            stmt.setLong(4, cursoActualizar.getId());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }
    }

    public static Boolean eliminar(Long codigo) {
        String sql = " UPDATE curso "
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
