/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.Profesor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author chris
 */
public class ProfesoresSinCursos extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlumnosPorCurso
     */
    public ProfesoresSinCursos() {
        initComponents();
        tabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Profesores sin cursos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        btnExportar.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reports2.png"))); // NOI18N
        btnExportar.setText("EXPORTAR");
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(374, 374, 374)
                        .addComponent(jLabel1)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(392, 392, 392))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        try {
            exportarExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void tabla() {
        try {
            DefaultTableModel tableModel = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            String[] columnas = {"Codigo", "Nombres", "Apellidos", "CUI", "Fecha de nacimiento", "Genero"};
            tableModel.setColumnIdentifiers(columnas);
            jTable1.setModel(tableModel);
            Object[] fila = new Object[tableModel.getColumnCount()];
            List<Profesor> profesores = Cruds.CrudProfesor.profesoresSinCurso();

            for (Profesor profesor : profesores) {
                fila[0] = profesor.getId();
                fila[1] = profesor.getNombres();
                fila[2] = profesor.getApellidos();
                fila[3] = profesor.getCui();
                fila[4] = new SimpleDateFormat("dd/MM/yyyy").format(profesor.getFechaDeNacimiento());
                fila[5] = profesor.getGenero();

                tableModel.addRow(fila);
            }

        } catch (NullPointerException e) {
        }

        if (jTable1.getModel().getRowCount() > 0) {
            btnExportar.setEnabled(true);
        } else {
            btnExportar.setEnabled(false);
        }
    }

    private void exportarExcel() throws IOException {
        Date date = new Date();
        String fileName = "ProfesoresSinCursos" + date.getTime();
        File file = new File("C:\\COLEGIO\\Reportes", fileName + ".xls");
        TableModel model = jTable1.getModel();
        try (BufferedWriter excel = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {

            for (int i = 0; i < model.getColumnCount(); i++) {
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i, j).toString() + "\t");
                }
                excel.write("\n");
            }
        }

        String message = "Reporte generado en la ubación C:\\COLEGIO\\Reportes con el nombre: " + fileName;
        JOptionPane.showMessageDialog(null, message);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
