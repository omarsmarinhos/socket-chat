package Vista;

import models.Estado;
import models.ClienteInfo;
import models.Mensaje;
import repositories.ClientesRepositoryImpl;
import repositories.ClientesRespository;
import repositories.MensajesRepository;
import repositories.MensajesRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VServidor extends javax.swing.JFrame {

    private static final ClientesRespository clientesRepository = new ClientesRepositoryImpl();
    private static final MensajesRepository mensajesRepository = new MensajesRepositoryImpl();

    private static final List<ClienteRunnable> clientes = new ArrayList<>();
    private ServerSocket serverSocket;

    static DefaultTableModel clientesModel = new DefaultTableModel();

    public VServidor() throws IOException {
        initComponents();
        initDataTable();
        initServerSocket();
        btn_start.setEnabled(false);
    }

    private void initServerSocket() throws IOException {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                serverSocket = new ServerSocket(59001);
                System.out.println("Servidor está a la escucha...");
                while (true) {
                    Socket socket = serverSocket.accept();
                    ClienteRunnable cliente = new ClienteRunnable(socket);
                    Thread clienteThread = new Thread(cliente);
                    clienteThread.start();
                }
            }

            @Override
            protected void done() {
                // Este método se llama cuando el método doInBackground() termina
            }
        };
        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_start = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaClientes = new javax.swing.JTable();
        btn_bloquearClienteServidor = new javax.swing.JButton();
        btn_conectarClienteServidor = new javax.swing.JButton();
        lblCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SERVIDOR");

        btn_start.setText("START");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        btn_stop.setText("STOP");
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LISTA DE CLIENTES ONLINE");

        tblListaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "ID", "UUID", "NICKNAME", "STATUS"
                }
        ));
        jScrollPane1.setViewportView(tblListaClientes);

        btn_bloquearClienteServidor.setText("Bloquear");
        btn_bloquearClienteServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bloquearClienteServidorActionPerformed(evt);
            }
        });

        btn_conectarClienteServidor.setText("Conectar");
        btn_conectarClienteServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarClienteServidorActionPerformed(evt);
            }
        });

        lblCliente.setText("... nickname ...");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Seleccione cliente para conectar o bloquear");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                                .addComponent(btn_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(btn_conectarClienteServidor)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btn_bloquearClienteServidor)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblCliente))))
                                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_start)
                                        .addComponent(btn_stop))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_bloquearClienteServidor)
                                        .addComponent(btn_conectarClienteServidor)
                                        .addComponent(lblCliente))
                                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {
        //Activar el servidor
        try {
            initServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btn_start.setEnabled(false);
        btn_stop.setEnabled(true);

        //RECORDAR: QUE LA TABLA DEBE ESTAR AL ESCUCHA CUANDO UN USUARIO SE CONECTE AL SERVIDOR,
        // PARA QUE APARESCA SU IP NICKANME Y UUID
    }

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {
        // desactivar el servidor
        try {
            serverSocket.close();
            btn_start.setEnabled(true);
            btn_stop.setEnabled(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // los clientes en VCliente debe aparecer como desconectado(circulo_gris)
    }

    private void btn_conectarClienteServidorActionPerformed(java.awt.event.ActionEvent evt) {
        // se activa el boton solo cuando el cliente esta bloqueado

        // permite la conección al servidor al cliente

    }

    private void btn_bloquearClienteServidorActionPerformed(java.awt.event.ActionEvent evt) {
        //valida si hay selección en la tabla 
        // bloquea al cliente del servidor
        // en VCliente debe pasar a estado bloqueado (circulo_rojo)
    }

    private static class ClienteRunnable implements Runnable {
        private final Socket socket;
        private ClienteInfo clienteInfo;

        private ObjectInputStream in;
        private ObjectOutputStream out;

        public ClienteRunnable(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Escuchar la entrada del cliente
                    Object entrada = in.readObject();

                    if (entrada != null) {

                        if (entrada instanceof ClienteInfo) {
                            clienteInfo = (ClienteInfo) entrada;
                            synchronized (clientes) {
                                clientes.add(this);
                                clientesRepository.actualizarEstado(clienteInfo.getUuid(), Estado.CONECTADO);
                            }
                        } else if (entrada instanceof String) {
                            synchronized (clientes) {
                                clientes.remove(this);
                                clientesRepository.actualizarEstado(clienteInfo.getUuid(), Estado.DESCONECTADO);
                            }
                        } else if (entrada instanceof Mensaje mensaje) {
                            mensajesRepository.crearMensaje(mensaje);
                            enviarMensaje(mensaje);
                        }

                        actualizarTablaClientes();
                        enviarListaClientes();
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                synchronized (clientes) {
                    clientes.remove(this);
                    clientesRepository.actualizarEstado(clienteInfo.getUuid(), 2);
                }
                actualizarTablaClientes();
            }
        }

    }

    private static void enviarMensaje(Mensaje mensaje) throws IOException {
        synchronized (clientes) {
            Optional<ClienteRunnable> cliente = clientes.stream()
                    .filter(c -> Objects.equals(c.clienteInfo.getId(), mensaje.getDestinatarioId()))
                    .findFirst();

            cliente.ifPresent(c -> {
                try {
                    c.out.writeObject(mensaje);
                    c.out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void enviarListaClientes() throws IOException {
        synchronized (clientes) {
            List<ClienteInfo> clientesData = clientesRepository.getClientes();

            for (ClienteRunnable cliente : clientes) {
                cliente.out.writeObject(clientesData);
                cliente.out.flush();
            }
        }
    }

    private static void actualizarTablaClientes() {
        // Borrar todas las filas existentes en la tabla
        clientesModel.setRowCount(0);
        // Agregar los clientes a la tabla
        synchronized (clientes) {
            for (ClienteRunnable cliente : clientes) {
                Object[] fila = {
                        cliente.clienteInfo.getIp(),
                        cliente.clienteInfo.getUuid(),
                        cliente.clienteInfo.getUsername(),
                        cliente.clienteInfo.getStatus()};
                clientesModel.addRow(fila);
            }
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
            java.util.logging.Logger.getLogger(VServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VServidor().setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void initDataTable() {
        clientesModel.setColumnIdentifiers(new Object[]{
                "Ip", "UUID", "NickName", "Status"
        });
        tblListaClientes.setModel(clientesModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bloquearClienteServidor;
    private javax.swing.JButton btn_conectarClienteServidor;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_stop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JTable tblListaClientes;
    // End of variables declaration//GEN-END:variables
}
