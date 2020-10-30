/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruds;

import Clases.Alumno;
import Clases.Asignacion;
import Clases.Curso;
import Clases.Profesor;
import General.ConexionBaseDeDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class CrudAsignacion {

    public static List<Asignacion> obtenerCursosAsignadosAlumno(Long codigo) {
        List<Asignacion> listAsignaciones = new ArrayList<>();
        String sql = "select a.id, a.horainicio, a.horafinal, "
                + "b.nombres as nombreAlumno, b.apellidos as apellidoAlumno, "
                + "c.nombrecurso, d.nombres as nombreProfesor, "
                + "d.apellidos as apellidoProfesor "
                + "from asignacion a "
                + "join alumno b on a.alumno_id = b.id "
                + "join curso c on a.curso_id = c.id "
                + "join profesor d on c.profesor_id = d.id "
                + "where a.activo = true "
                + " and c.activo = true and a.alumno_id = " + codigo;

        try (PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Time horaInicio = rs.getTime("horainicio");
                Time horafinal = rs.getTime("horafinal");

                Alumno alumno = new Alumno();
                alumno.setNombres(rs.getString("nombreAlumno"));
                alumno.setApellidos(rs.getString("apellidoAlumno"));

                Profesor profesor = new Profesor();
                profesor.setNombres(rs.getString("nombreProfesor"));
                profesor.setApellidos(rs.getString("apellidoProfesor"));

                Curso curso = new Curso();
                curso.setNombreCurso(rs.getString("nombrecurso"));
                curso.setProfesor(profesor);

                Asignacion asignacion = new Asignacion();
                asignacion.setId(id);
                asignacion.setHoraInicio(horaInicio);
                asignacion.setHoraFinal(horafinal);
                asignacion.setAlumno(alumno);
                asignacion.setCurso(curso);

                listAsignaciones.add(asignacion);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listAsignaciones;

    }

    public static Asignacion buscarAsignacion(Long codigo) {
        Asignacion asignacion = null;
        String sql = "SELECT a.id as idAsignacion, a.horaInicio, a.horaFinal,"
                + " b.id as idCurso, b.nombreCurso, b.duracionCurso"
                + " FROM asignacion a "
                + " JOIN curso b ON a.curso_id = b.id "
                + " WHERE a.activo = true and a.id =" + codigo;

        try (Statement stmt = ConexionBaseDeDatos.CONNECTION.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("idAsignacion");
                Time horaInicio = rs.getTime("horaInicio");
                Time horaFinal = rs.getTime("horaFinal");

                Curso curso = new Curso();
                curso.setId(rs.getLong("idCurso"));
                curso.setNombreCurso(rs.getString("nombreCurso"));
                curso.setDuracionCurso(rs.getInt("duracionCurso"));

                asignacion = new Asignacion();
                asignacion.setId(id);
                asignacion.setHoraInicio(horaInicio);
                asignacion.setHoraFinal(horaFinal);
                asignacion.setCurso(curso);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return asignacion;
    }

    public static Asignacion buscarAsignacionPorHorario(Long codigo, Time horaInicio, Time horaFinal, Long idAsignacion, Boolean update) {
        Asignacion asignacion = null;
        String sql = "SELECT * "
                + " FROM asignacion "
                + " WHERE activo = true AND alumno_id = ?"
                + " AND ('YESTERDAY'::date + ? ::time) "
                + " BETWEEN ('YESTERDAY'::date + horainicio) "
                + " AND ('YESTERDAY'::date + horafinal)"
                + " AND ('YESTERDAY'::date + ? ::time) "
                + " BETWEEN ('YESTERDAY'::date + horainicio) "
                + " AND ('YESTERDAY'::date + horafinal) ";

        if (update) {
            sql += " AND id = ? ";
        }

        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setLong(1, codigo);
            stmt.setTime(2, horaInicio);
            stmt.setTime(3, horaFinal);

            if (update) {
                stmt.setLong(4, idAsignacion);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                asignacion = new Asignacion();
                asignacion.setId(rs.getLong("id"));
                asignacion.setHoraInicio(rs.getTime("horaInicio"));
                asignacion.setHoraFinal(rs.getTime("horaFinal"));
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return asignacion;
    }

    public static Boolean insertar(Asignacion asignacionInsertar) {
        String sql = " INSERT INTO asignacion (activo, horaInicio, horaFinal, curso_id, alumno_id)  "
                + " VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setTime(2, asignacionInsertar.getHoraInicio());
            stmt.setTime(3, asignacionInsertar.getHoraFinal());
            stmt.setLong(4, asignacionInsertar.getCurso().getId());
            stmt.setLong(5, asignacionInsertar.getAlumno().getId());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }

    }

    public static Boolean actualizar(Asignacion asignacionActualizar) {
        String sql = " UPDATE asignacion "
                + " SET horaInicio = ?, horaFinal = ?, curso_id = ? "
                + " WHERE id = ? ";
        try {
            PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql);
            stmt.setTime(1, asignacionActualizar.getHoraInicio());
            stmt.setTime(2, asignacionActualizar.getHoraFinal());
            stmt.setLong(3, asignacionActualizar.getCurso().getId());
            stmt.setLong(4, asignacionActualizar.getId());
            stmt.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return Boolean.FALSE;
        }
    }

    public static Boolean eliminar(Long codigo) {
        String sql = " UPDATE asignacion "
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

    public static List<Asignacion> alumnosAsignadosPorCurso(Long codigoCurso) {
        List<Asignacion> listAsignaciones = new ArrayList<>();
        String sql = "select a.id, a.horainicio, a.horafinal, "
                + "c.id as alumnoId, c.cui, c.nombres, c.apellidos, "
                + "c.fechanacimiento, c.grado, c.genero "
                + "from asignacion a "
                + "join curso b on a.curso_id = b.id "
                + "join alumno c on a.alumno_id = c.id "
                + "where a.activo = true and b.id=" + codigoCurso;

        try (PreparedStatement stmt = ConexionBaseDeDatos.CONNECTION.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Time horaInicio = rs.getTime("horainicio");
                Time horafinal = rs.getTime("horafinal");

                Alumno alumno = new Alumno();
                alumno.setId(rs.getLong("alumnoId"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setCui(rs.getString("cui"));
                alumno.setGenero(rs.getString("genero"));
                alumno.setGradoAlumno(rs.getString("grado"));
                alumno.setFechaDeNacimiento(rs.getDate("fechaNacimiento"));

                Asignacion asignacion = new Asignacion();
                asignacion.setId(id);
                asignacion.setHoraInicio(horaInicio);
                asignacion.setHoraFinal(horafinal);
                asignacion.setAlumno(alumno);

                listAsignaciones.add(asignacion);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listAsignaciones;
    }

}
