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
public class Alumno extends Persona {

    private String gradoAlumno;

    public Alumno() {
    }

    public Alumno(String gradoAlumno) {
        this.gradoAlumno = gradoAlumno;
    }

    public String getGradoAlumno() {
        return gradoAlumno;
    }

    public void setGradoAlumno(String gradoAlumno) {
        this.gradoAlumno = gradoAlumno;
    }

}
