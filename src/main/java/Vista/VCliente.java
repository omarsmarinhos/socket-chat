package Vista;

import models.Estado;
import models.ClienteInfo;
import models.Mensaje;
import models.PaginadorParams;
import repositories.MensajesRepository;
import repositories.MensajesRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class VCliente extends javax.swing.JFrame {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private final ClienteInfo miInfo;
    private ClienteInfo destinatarioObj;

    private final DefaultTableModel clientesModel = new DefaultTableModel();
    List<ClienteInfo> clientesData;

    private final MensajesRepository mensajesRepository = new MensajesRepositoryImpl();

    public VCliente(ClienteInfo clienteInfo) throws IOException {
        initComponents();
        initDataTable();
        miInfo = clienteInfo;
        lblUsername.setText(miInfo.getUsername());
        lblIp.setText(miInfo.getIp());
        lblUuid.setText(miInfo.getUuid());

        btn_desconectar.setEnabled(false);
        btnChat.setEnabled(false);
    }

    private void initClientSocket() throws IOException {
        socket = new Socket("localhost", 59001);
        System.out.println("Conexión establecida con el servidor");

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        // Enviar datos al servidor
        miInfo.setStatus(Estado.CONECTADO);
        out.writeObject(miInfo);
        out.flush();

        Thread receiveThread = new Thread(() -> {
            try {
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof List) {
                        clientesData = (List<ClienteInfo>) obj;
                        clientesData.remove(miInfo);
                        // Actualizar la tabla de clientes en la interfaz gráfica
                        SwingUtilities.invokeLater(this::actualizarTablaCliente);
                    } else if (obj instanceof Mensaje mensaje) {
                        if (dlgChat.isVisible()) {
                            SwingUtilities.invokeLater(this::actualizarChat);
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Tienes un mensaje de " + mensaje.getRemitenteUsername(),
                                    "Joption en vista " + miInfo.getUsername(),
                                    JOptionPane.INFORMATION_MESSAGE, null);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("exception DESCONECTADO");
            }
        });
        receiveThread.start();
    }

    private void actualizarTablaCliente() {
        clientesModel.setRowCount(0);
        for (ClienteInfo data : clientesData) {
            clientesModel.addRow(new Object[]{
                    data.getIp(),
                    data.getUuid(),
                    data.getUsername(),
                    data.getStatus()
            });
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
        btn_conectarServidor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblIp = new javax.swing.JLabel();
        lblUuid = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_listaContactos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_desconectar = new javax.swing.JButton();
        lblUsernameDestinatario = new javax.swing.JLabel();
        btnChat = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();

        dlgChat.setBackground(new java.awt.Color(255, 255, 255));
        dlgChat.setModal(true);
        dlgChat.setResizable(false);
        dlgChat.setSize(new java.awt.Dimension(440, 350));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtChat.setEditable(false);
        txtChat.setBackground(new java.awt.Color(255, 255, 255));
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane2.setViewportView(txtChat);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnEnviarActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
                                        .addComponent(txtMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
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
        jLabel2.setText("USERNAME");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

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

        lblIp.setText("...");
        jPanel1.add(lblIp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, -1));

        lblUuid.setText("...");
        jPanel1.add(lblUuid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, -1));

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

        lblUsernameDestinatario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsernameDestinatario.setText("...");
        jPanel1.add(lblUsernameDestinatario, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 410, 90, 20));

        btnChat.setText("CHAT");
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });
        jPanel1.add(btnChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

        lblUsername.setText("...");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 130, -1));

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
        destinatarioObj = clientesData.get(x);
        btnChat.setEnabled(true);
    }

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        String contenido = txtMensaje.getText();
        if (contenido.isBlank()) {
            return;
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitenteId(miInfo.getId());
        mensaje.setDestinatarioId(destinatarioObj.getId());
        mensaje.setContenido(contenido);
        mensaje.setRemitenteUsername(miInfo.getUsername());

        out.writeObject(mensaje);
        out.flush();
        txtMensaje.setText("");
        txtChat.append(miInfo.getUsername() + ": " + contenido + "\n");
    }

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {

        dlgChat.setTitle("Chat con ".concat(destinatarioObj.getUsername()));
        dlgChat.setSize(440, 350);

        actualizarChat();

        dlgChat.setVisible(true);
        btnChat.setEnabled(false);
    }

    private void actualizarChat() {
        if (destinatarioObj == null) {
            return;
        }
        txtChat.setText("");
        List<Mensaje> chat = mensajesRepository.getMensajes(miInfo.getId(), destinatarioObj.getId()
                , new PaginadorParams(0, 30));
        chat.forEach(mensaje -> {
            txtChat.append(mensaje.getRemitenteUsername() + ": ".concat(mensaje.getContenido().concat("\n")));
        });
    }

    private void btn_conectarServidorActionPerformed(java.awt.event.ActionEvent evt) {
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

    private void btn_desconectarActionPerformed(java.awt.event.ActionEvent evt) {
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
                    new VCliente(null).setVisible(true);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIp;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernameDestinatario;
    private javax.swing.JLabel lblUuid;
    private javax.swing.JTable tbl_listaContactos;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
