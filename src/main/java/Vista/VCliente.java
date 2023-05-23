package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class VCliente extends javax.swing.JFrame {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String[] destinatario;
    private ClienteInfo miInfo;

    private final DefaultTableModel clientesModel = new DefaultTableModel();
    List<String[]> clientesData;

    public VCliente() throws IOException {
        initComponents();
        initDataTable();
        jLabel5.setText(InetAddress.getLocalHost().getHostAddress());
        jLabel6.setText(java.util.UUID.randomUUID().toString()
                .substring(0, 12)             
                .toUpperCase());
        btn_desconectar.setEnabled(false);
    }

    private void initClientSocket() throws IOException {
        socket = new Socket("localhost", 59001);
        System.out.println("Conexión establecida con el servidor");

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        // Enviar datos al servidor
        String ip = jLabel5.getText();
        String uuid = jLabel6.getText();
        String username = jTextField1.getText();
        Integer estado = 1;
        miInfo = new ClienteInfo();
        miInfo.setIp(ip);
        miInfo.setUuid(uuid);
        miInfo.setUuid(username);
        miInfo.setUuid(username);
        miInfo.setStatus(estado);
        out.writeObject(miInfo);
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
                System.out.println("DESCONECTADO");
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

        dlgChat = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
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
        btn_desconectar = new javax.swing.JButton();
        lbl_nickname = new javax.swing.JLabel();
        btnChat = new javax.swing.JButton();

        dlgChat.setBackground(new java.awt.Color(255, 255, 255));
        dlgChat.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnChat.setEnabled(false);

        txtChat.setColumns(20);
        txtChat.setRows(5);
        txtChat.setEditable(false);
        jScrollPane2.setViewportView(txtChat);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dlgChatLayout = new javax.swing.GroupLayout(dlgChat.getContentPane());
        dlgChat.getContentPane().setLayout(dlgChatLayout);
        dlgChatLayout.setHorizontalGroup(
            dlgChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgChatLayout.setVerticalGroup(
            dlgChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
        jPanel1.add(btn_conectarServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 180, -1));

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
            new Object [][] {
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
            new String [] {
                "IP", "UUID", "NICKNAME", "STATUS"
            }
        ));
        tbl_listaContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_listaContactosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_listaContactos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 490, 200));

        jLabel9.setIcon(new ImageIcon("src/main/java/imagenes/circulo_gris.png"));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 40, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Selecciona un usuario para enviar mensaje");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        btn_desconectar.setText("DESCONECTAR");
        btn_desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_desconectarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_desconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 180, -1));

        lbl_nickname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nickname.setText("... NICKNAME ...");
        jPanel1.add(lbl_nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 410, 90, 20));

        btnChat.setText("CHAT");
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });
        jPanel1.add(btnChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

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

    private void tbl_listaContactosMouseClicked(java.awt.event.MouseEvent evt) {
        int x = tbl_listaContactos.getSelectedRow();
        destinatario = clientesData.get(x);
        btnChat.setEnabled(true);
    }
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {
        if (miInfo.getUuid().equals(destinatario[1])) {
            return;
        }
        txtChat.setText("");
        txtMensaje.setText("");
        dlgChat.setVisible(true);

        dlgChat.setTitle(destinatario[1].concat(" ").concat(destinatario[2]));
        dlgChat.setSize(440, 350);
        btnChat.setEnabled(false);
    }

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
        cambiarEstado(1);
        btn_conectarServidor.setEnabled(false);
        btn_desconectar.setEnabled(true);
        System.out.println("CONECTADO");
    }

    private void cambiarEstado(Integer estado) {
        if (estado == 1) {
            jLabel9.setIcon(new ImageIcon("src/main/java/imagenes/circulo_verde.png"));
        } else if (estado == 2) {
            jLabel9.setIcon(new ImageIcon("src/main/java/imagenes/circulo_gris.png"));
        }
    }

    private void btn_desconectarActionPerformed(java.awt.event.ActionEvent evt){
        try {
            clientesData.clear();
            actualizarTablaCliente();
            out.writeObject("DESCONECTAR");
            out.flush();
            socket.close();
            cambiarEstado(2);
            btn_conectarServidor.setEnabled(true);
            btn_desconectar.setEnabled(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btn_conectarServidor;
    private javax.swing.JButton btn_desconectar;
    private javax.swing.JDialog dlgChat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_nickname;
    private javax.swing.JTable tbl_listaContactos;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
