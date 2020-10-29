/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author chris
 */
public class Curso {
    private String nombreCurso;
    private int duracionCurso;
    private Profesor profesor;
    private long id;
    private boolean activo;

    public Curso() {
    }

    public Curso(String nombreCurso, int duracionCurso, Profesor profesor, long id, boolean activo) {
        this.nombreCurso = nombreCurso;
        this.duracionCurso = duracionCurso;
        this.profesor = profesor;
        this.id = id;
        this.activo = activo;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getDuracionCurso() {
        return duracionCurso;
    }

    public void setDuracionCurso(int duracionCurso) {
        this.duracionCurso = duracionCurso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    @Override
    public String toString() {
        return this.getNombreCurso();
    }
    
    
}
