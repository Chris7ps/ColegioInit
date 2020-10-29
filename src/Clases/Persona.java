/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;

/**
 *
 * @author chris
 */
public class Persona extends BaseEntidad {

    private String nombres;
    private String apellidos;
    private Date fechaDeNacimiento;
    private String cui;
    private String genero;

    public Persona() {
    }

    public Persona(String nombres, String apellidos, Date fechaDeNacimiento, String cui, String genero) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.cui = cui;
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
