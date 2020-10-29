/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import Ventanas.Principal;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author chris
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal principal = new Principal();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = principal.getSize();
        principal.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        principal.setVisible(true);
    }

}
