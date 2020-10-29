/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Time;

/**
 *
 * @author chris
 */
public class Asignacion {

    private long id;
    private boolean activo;
    private Alumno alumno;
    private Time horaInicio;
    private Time horaFinal;
    private Curso curso;

    public Asignacion() {
    }

    public Asignacion(long id, boolean activo, Alumno alumno, Time horaInicio, Time horaFinal, Curso curso) {
        this.id = id;
        this.activo = activo;
        this.alumno = alumno;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.curso = curso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

   

}
