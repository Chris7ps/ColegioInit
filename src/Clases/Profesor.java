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
public class Profesor extends Persona {

    private String profesion;

    public Profesor() {
    }

    public Profesor(String profesion) {
        this.profesion = profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return this.getNombres() + " " + this.getApellidos();
    }

}
