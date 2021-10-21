
package views;

import java.awt.Color;
import javax.swing.JPanel;

public class ViewReportes extends javax.swing.JPanel {

    /**
     * Creates new form Catalogo
     */
    public ViewReportes() {
        initComponents();
        
        resetColor(item1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        item1 = new javax.swing.JPanel();
        btnReporteMascotas = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbxEstadoMascota = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMascotas = new javax.swing.JTable();
        btnBuscarMascotas = new javax.swing.JButton();
        item2 = new javax.swing.JPanel();
        btnReporteAdopciones = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtFechaFin = new com.toedter.calendar.JDateChooser();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdopciones = new javax.swing.JTable();
        btnBuscarAdopciones = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        item1.setBackground(new java.awt.Color(255, 255, 255));
        item1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mascotas"));
        item1.setMinimumSize(new java.awt.Dimension(250, 220));
        item1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                item1MouseClicked(evt);
            }
        });
        item1.setLayout(null);

        btnReporteMascotas.setBackground(new java.awt.Color(84, 56, 220));
        btnReporteMascotas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReporteMascotas.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteMascotas.setText("Generar reporte");
        btnReporteMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteMascotasActionPerformed(evt);
            }
        });
        item1.add(btnReporteMascotas);
        btnReporteMascotas.setBounds(520, 180, 170, 31);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(84, 56, 220));
        jLabel9.setText("Estado");
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 20));
        item1.add(jLabel9);
        jLabel9.setBounds(30, 40, 80, 20);

        cbxEstadoMascota.setBackground(new java.awt.Color(84, 56, 220));
        cbxEstadoMascota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxEstadoMascota.setForeground(new java.awt.Color(255, 255, 255));
        cbxEstadoMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nuevo", "Disponible", "Adoptado" }));
        cbxEstadoMascota.setPreferredSize(new java.awt.Dimension(120, 20));
        cbxEstadoMascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoMascotaActionPerformed(evt);
            }
        });
        item1.add(cbxEstadoMascota);
        cbxEstadoMascota.setBounds(120, 40, 150, 30);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblMascotas.setAutoCreateRowSorter(true);
        tblMascotas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "NOMBRE", "FECHA DE INGRESO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblMascotas.setGridColor(new java.awt.Color(255, 255, 255));
        tblMascotas.setIntercellSpacing(new java.awt.Dimension(5, 5));
        jScrollPane2.setViewportView(tblMascotas);

        item1.add(jScrollPane2);
        jScrollPane2.setBounds(300, 30, 390, 130);

        btnBuscarMascotas.setBackground(new java.awt.Color(84, 56, 220));
        btnBuscarMascotas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBuscarMascotas.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarMascotas.setText("Buscar");
        btnBuscarMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMascotasActionPerformed(evt);
            }
        });
        item1.add(btnBuscarMascotas);
        btnBuscarMascotas.setBounds(30, 100, 170, 31);

        add(item1);
        item1.setBounds(20, 10, 710, 230);

        item2.setBackground(new java.awt.Color(255, 255, 255));
        item2.setBorder(javax.swing.BorderFactory.createTitledBorder("Adopciones"));
        item2.setMinimumSize(new java.awt.Dimension(250, 220));
        item2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                item2MouseClicked(evt);
            }
        });
        item2.setLayout(null);

        btnReporteAdopciones.setBackground(new java.awt.Color(84, 56, 220));
        btnReporteAdopciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReporteAdopciones.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteAdopciones.setText("Generar reporte");
        btnReporteAdopciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteAdopcionesActionPerformed(evt);
            }
        });
        item2.add(btnReporteAdopciones);
        btnReporteAdopciones.setBounds(520, 200, 170, 31);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(84, 56, 220));
        jLabel7.setText("Hasta");
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 20));
        item2.add(jLabel7);
        jLabel7.setBounds(30, 70, 80, 20);
        item2.add(txtFechaFin);
        txtFechaFin.setBounds(120, 70, 150, 20);
        item2.add(txtFechaInicio);
        txtFechaInicio.setBounds(120, 30, 150, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(84, 56, 220));
        jLabel10.setText("Desde");
        jLabel10.setPreferredSize(new java.awt.Dimension(120, 20));
        item2.add(jLabel10);
        jLabel10.setBounds(30, 30, 80, 20);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblAdopciones.setAutoCreateRowSorter(true);
        tblAdopciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblAdopciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CÓDIGO", "ADOPTANTE", "MASCOTA", "FECHA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAdopciones.setGridColor(new java.awt.Color(255, 255, 255));
        tblAdopciones.setIntercellSpacing(new java.awt.Dimension(5, 5));
        jScrollPane1.setViewportView(tblAdopciones);

        item2.add(jScrollPane1);
        jScrollPane1.setBounds(300, 30, 390, 150);

        btnBuscarAdopciones.setBackground(new java.awt.Color(84, 56, 220));
        btnBuscarAdopciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBuscarAdopciones.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarAdopciones.setText("Buscar");
        btnBuscarAdopciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAdopcionesActionPerformed(evt);
            }
        });
        item2.add(btnBuscarAdopciones);
        btnBuscarAdopciones.setBounds(30, 120, 170, 31);

        add(item2);
        item2.setBounds(20, 260, 710, 250);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteAdopcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteAdopcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteAdopcionesActionPerformed

    private void item1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item1MouseClicked
        // TODO add your handling code here:
        setColor(item1);
    }//GEN-LAST:event_item1MouseClicked

    private void item2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_item2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_item2MouseClicked

    private void btnReporteMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteMascotasActionPerformed

    private void cbxEstadoMascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoMascotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoMascotaActionPerformed

    private void btnBuscarMascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMascotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarMascotasActionPerformed

    private void btnBuscarAdopcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAdopcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarAdopcionesActionPerformed

    void setColor(JPanel panel) {
        panel.setBackground(new Color(255,86,201));
    }
    
    void resetColor(JPanel panel) {
        panel.setBackground(new Color(84,56,220));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarAdopciones;
    public javax.swing.JButton btnBuscarMascotas;
    public javax.swing.JButton btnReporteAdopciones;
    public javax.swing.JButton btnReporteMascotas;
    public javax.swing.JComboBox<String> cbxEstadoMascota;
    private javax.swing.JPanel item1;
    private javax.swing.JPanel item2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tblAdopciones;
    public javax.swing.JTable tblMascotas;
    public com.toedter.calendar.JDateChooser txtFechaFin;
    public com.toedter.calendar.JDateChooser txtFechaInicio;
    // End of variables declaration//GEN-END:variables
}
