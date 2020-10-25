/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author chris
 */
public class Init implements ActionListener {

    JDesktopPane desk;
    JInternalFrame iframe;
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

    public Init() {
        desk = new JDesktopPane();
        frame = new JFrame();
        frame.setTitle("Colegio la Unión");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        desk.add(frame);
        crearMenu();
    }

    private void crearMenu() {
        barra = new JMenuBar();
        inicio = new JMenu("Inicio");
        administracion = new JMenu("Administración");
        reportes = new JMenu("Reportes");
        ayuda = new JMenu("Ayuda");

        itemAlumno = new JMenuItem("Alumno");
        itemAlumno.addActionListener(this);
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

        frame.setJMenuBar(barra);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String a = e.getActionCommand();
        if (a.equalsIgnoreCase("encrypt")) {
            System.out.println("Begin RAMBIT7 encryption.");
            //encryptRAMBIT7(input);
        } else if (a.equalsIgnoreCase("decrypt")) {
            System.out.println("Begin RAMBIT7 decryption.");
            //decryptRAMBIT7(input);
        } else if (a.equalsIgnoreCase("exit")) {
            System.out.println("Terminating...");
            System.exit(0);
        }

    }
}
