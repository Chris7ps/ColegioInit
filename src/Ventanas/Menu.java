/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author chris
 */
public class Menu extends JFrame {

    JDesktopPane panel;
    JDialog dialogo;
    JFrame frame;
    JMenuBar barra;
    JMenu inicio;
    JMenu administracion;
    JMenu reportes;
    JMenu ayuda;

    JMenuItem itemAlumno;
    JMenuItem itemProfesor;
    JMenuItem itemCursos;
    JMenuItem itemMatricular;
    JMenuItem itemReporte1;
    JMenuItem itemAcercaDe;

    private void init() {
        panel = new JDesktopPane();
        frame = new JFrame();
        barra = new JMenuBar();
        dialogo = new JDialog();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
                jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
                jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(dialogo.getContentPane());
        dialogo.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Colegio La Unión");

        panel.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        panel.setName("Colegio La Unión"); // 

        barra.setFont(new java.awt.Font("Dialog", 1, 14));

        crearMenu();
    }

    public Menu() {
        init();
    }

    private void crearMenu() {

        inicio = new JMenu("Inicio");
        administracion = new JMenu("Administración");
        reportes = new JMenu("Reportes");
        ayuda = new JMenu("Ayuda");

        itemAlumno = new JMenuItem("Alumno");
        itemAlumno.addActionListener((java.awt.event.ActionEvent evt) -> {
            alumnoPerformed();
        });
        itemProfesor = new JMenuItem("Profesor");
        itemCursos = new JMenuItem("Curso");
        itemMatricular = new JMenuItem("Matricular");
        itemReporte1 = new JMenuItem("Reporte alumnos sin cursos");
        itemAcercaDe = new JMenuItem("Acerca de ");

        administracion.add(itemAlumno);
        administracion.add(itemProfesor);
        administracion.add(itemCursos);
        administracion.add(itemMatricular);

        reportes.add(itemReporte1);

        ayuda.add(itemAcercaDe);
        barra.add(inicio);
        barra.add(administracion);
        barra.add(reportes);
        barra.add(ayuda);

        setJMenuBar(barra);

    }

    private void alumnoPerformed() {
        VentanaAlumno ventanaAlumno = new VentanaAlumno();
        panel.add(ventanaAlumno);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        ventanaAlumno.setSize(400, 500);
        Dimension ventana = ventanaAlumno.getSize();
        ventanaAlumno.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 25);
        ventanaAlumno.setVisible(true);

    }
}
