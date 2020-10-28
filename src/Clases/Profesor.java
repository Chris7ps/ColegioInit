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

    private String gradoAcademico;

    public Profesor() {
    }

    public Profesor(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    @Override
    public String toString() {
        return this.getNombres() + " " + this.getApellidos();
    }

}
