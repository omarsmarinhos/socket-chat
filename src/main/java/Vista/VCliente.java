package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class VCliente extends javax.swing.JFrame {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private final DefaultTableModel clientesModel = new DefaultTableModel();
    List<String[]> clientesData;

    public VCliente() throws IOException {
        initComponents();
        initDataTable();
        jLabel5.setText(InetAddress.getLocalHost().getHostAddress());
        jLabel6.setText(java.util.UUID.randomUUID().toString()
                .substring(0, 12)
                .toUpperCase());
    }

    private void initClientSocket() throws IOException {
        socket = new Socket("localhost", 59001);
        System.out.println("Conexión establecida con el servidor");

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        // Enviar datos al servidor
        String ip = jLabel5.getText();
        String uuid = jLabel6.getText();
        String nickname = jTextField1.getText();
        String estado = "conectado";
        ClienteInfo info = new ClienteInfo(ip, uuid, nickname, estado);
        out.writeObject(info);
        out.flush();

        Thread receiveThread = new Thread(() -> {
            try {
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof List) {
                        clientesData = (List<String[]>) obj;
                        // Actualizar la tabla de clientes en la interfaz gráfica
                        SwingUtilities.invokeLater(this::actualizarTablaCliente);
                    } else {
                        // Manejar otros tipos de mensajes recibidos del servidor
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        receiveThread.start();
    }

    private void actualizarTablaCliente() {
        // Borrar todas las filas existentes en la tabla
        clientesModel.setRowCount(0);
        // Agregar los clientes a la tabla
        for (String[] data : clientesData) {
            clientesModel.addRow(data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btn_conectarServidor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_listaContactos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_conectarCon = new javax.swing.JButton();
        lbl_nickname = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENTE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 16, 550, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("NICKNAME");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 85, -1));

        btn_conectarServidor.setText("CONECTAR CON SERVIDOR");
        btn_conectarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarServidorActionPerformed(evt);
            }
        });
        jPanel1.add(btn_conectarServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("IP");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 61, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("UUID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 61, -1));

        jLabel5.setText("...");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 85, -1));

        jLabel6.setText("...");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 85, -1));

        tbl_listaContactos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "IP", "UUID", "NICKNAME", "STATUS"
                }
        ));
        jScrollPane1.setViewportView(tbl_listaContactos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 490, 200));

        jLabel9.setIcon(new ImageIcon("src/main/java/imagenes/circulo_gris.png"));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 94, 40, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Selecciona un usuario para enviar mensaje");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        btn_conectarCon.setText("CONECTAR CON");
        btn_conectarCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarConActionPerformed(evt);
            }
        });
        jPanel1.add(btn_conectarCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, -1, -1));

        lbl_nickname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nickname.setText("... NICKNAME ...");
        jPanel1.add(lbl_nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 410, 90, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_conectarServidorActionPerformed(java.awt.event.ActionEvent evt) {
        // VALIDAR SI EL CAMPO DEL NICKNAME ESTA VACIO O NO
        if (jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nickname");
            return;
        }

        //SE CONECTA AL SERVIDOR
        try {
            initClientSocket();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "EL servidor está apagado");
            return;
        }

        //BLOQUEAR BTN DE CONECTAR CON EL SERVIDOR
        btn_conectarServidor.setEnabled(false);

        //CAMBIAR ESTADO a ACTIVO
        cambiarEstado("Activo");
    }

    private void cambiarEstado(String estado) {
        if (estado.equals("Activo")) {
            jLabel9.setIcon(new ImageIcon("src/main/java/imagenes/circulo_verde.png"));
        }
    }

    private void btn_conectarConActionPerformed(java.awt.event.ActionEvent evt) {
        // VaLidar si se ha seleccionado el usuario (al que desea enviarle un mensaje) en la tabla tbl_listaContactos 
        // Redirigir a otra ventana de chat

        //RECORDAR: Que cuando el usuario seleccione en la tabla, el label lbl_nickname cambie al nickanme al que desea conectarse
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VCliente().setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void initDataTable() {
        clientesModel.setColumnIdentifiers(new String[]{
                "IP", "UUID", "NickName", "Status"
        });
        tbl_listaContactos.setModel(clientesModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_conectarCon;
    private javax.swing.JButton btn_conectarServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_nickname;
    private javax.swing.JTable tbl_listaContactos;
    // End of variables declaration//GEN-END:variables
}
