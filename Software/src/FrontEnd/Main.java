/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import BackEnd.ConfigPedidos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author samuel
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private ConfigPedidos configPedidos;
    private String archiveChoose;

    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);

        this.setTitle("One pizza");
        this.archiveChoose = "";

        this.txtTituloResultado.setText(" ");
        this.txtResultado.setText(" ");
        this.btnExportar.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFundo = new javax.swing.JPanel();
        pnlBarraInferior = new javax.swing.JPanel();
        btnEscolherArquivo = new javax.swing.JButton();
        txtTituloEnd = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JLabel();
        sep = new javax.swing.JSeparator();
        pnlResultado = new javax.swing.JPanel();
        txtTituloResultado = new javax.swing.JLabel();
        txtResultado = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlFundo.setBackground(new java.awt.Color(247, 243, 227));

        pnlBarraInferior.setBackground(new java.awt.Color(168, 118, 62));

        btnEscolherArquivo.setBackground(new java.awt.Color(111, 26, 7));
        btnEscolherArquivo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEscolherArquivo.setForeground(new java.awt.Color(255, 255, 255));
        btnEscolherArquivo.setText("Escolha um arquivo");
        btnEscolherArquivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEscolherArquivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlBarraInferiorLayout = new javax.swing.GroupLayout(pnlBarraInferior);
        pnlBarraInferior.setLayout(pnlBarraInferiorLayout);
        pnlBarraInferiorLayout.setHorizontalGroup(
            pnlBarraInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBarraInferiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEscolherArquivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBarraInferiorLayout.setVerticalGroup(
            pnlBarraInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBarraInferiorLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(btnEscolherArquivo)
                .addGap(33, 33, 33))
        );

        txtTituloEnd.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTituloEnd.setForeground(new java.awt.Color(0, 0, 0));
        txtTituloEnd.setText("Endereço do arquivo:");

        txtEndereco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEndereco.setForeground(new java.awt.Color(0, 0, 0));

        pnlResultado.setBackground(new java.awt.Color(247, 243, 227));
        pnlResultado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTituloResultado.setBackground(new java.awt.Color(204, 204, 204));
        txtTituloResultado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTituloResultado.setForeground(new java.awt.Color(0, 0, 0));
        txtTituloResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTituloResultado.setText("A PIZZA FINAL SERÁ:");
        pnlResultado.add(txtTituloResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 620, -1));

        txtResultado.setBackground(new java.awt.Color(204, 204, 204));
        txtResultado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtResultado.setForeground(new java.awt.Color(111, 26, 7));
        txtResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtResultado.setText("Nome");
        pnlResultado.add(txtResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 540, 22));

        btnExportar.setBackground(new java.awt.Color(111, 26, 7));
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("exportar");
        btnExportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportarMouseClicked(evt);
            }
        });
        pnlResultado.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        javax.swing.GroupLayout pnlFundoLayout = new javax.swing.GroupLayout(pnlFundo);
        pnlFundo.setLayout(pnlFundoLayout);
        pnlFundoLayout.setHorizontalGroup(
            pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFundoLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFundoLayout.createSequentialGroup()
                        .addComponent(txtTituloEnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBarraInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addComponent(sep))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlFundoLayout.setVerticalGroup(
            pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFundoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTituloEnd)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(pnlResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(pnlBarraInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEscolherArquivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEscolherArquivoMouseClicked
        this.configPedidos = new ConfigPedidos();

        // ABRE A CAIXA DE NAVEGAÇÃO PARA PESQUISAR O ARQUIVO
        JFileChooser arquivoEscolhido = new JFileChooser();
        arquivoEscolhido.setDialogTitle("Escolha o arquivo");

        // EXIBE OS ARQUIVOS
        arquivoEscolhido.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // FILTRA O ARQUIVO PARA EXIBIR APENAS .TXT
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        arquivoEscolhido.setFileFilter(filtro);

        // CASO O USUÁRIO ESCOLHA UM ARQUIVO
        if (arquivoEscolhido.showSaveDialog(null) == 0) {
            // COLETA O ARQUIVO SELECIONADO
            File arquivo = arquivoEscolhido.getSelectedFile();
            this.txtEndereco.setText(arquivo.getPath());
            this.archiveChoose = arquivo.getName();

            try {
                this.configPedidos.leitor(txtEndereco.getText());

                // LISTA OS INGREDIENTES QUE PODEM E QUE NÃO PODEM
                this.configPedidos.setListarFavoraveis();
                this.configPedidos.setListarDesfavoraveis();

                // RECEBE OS ELEMENTOS E QTD VEZES ELE SE REPETIU
                this.configPedidos.setSelecionarRentaveis();

                // MOSTRA O RESULTADO DA PIZZA
                this.btnExportar.setVisible(true);
                this.txtTituloResultado.setText("A PIZZA FINAL SERÁ:");
                this.txtResultado.setText(configPedidos.getIngredientes());
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            // MUDA O NOME DO BOTÃO
            this.btnEscolherArquivo.setText("Escolha outro arquivo");
        }
    }//GEN-LAST:event_btnEscolherArquivoMouseClicked

    private void btnExportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarMouseClicked
        // ABRE A CAIXA DE NAVEGAÇÃO PARA PESQUISAR O ARQUIVO
        JFileChooser arquivoEscolhido = new JFileChooser();
        arquivoEscolhido.setDialogTitle("Escolha o local para salvar o arquivo");

        // EXIBE OS ARQUIVOS
        arquivoEscolhido.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // CASO O USUÁRIO ESCOLHA UM ARQUIVO
        if (arquivoEscolhido.showSaveDialog(null) == 0) {
            // INSTANCIA O ARQUIVO E PEGA O ENDEREÇO DELE
            File arquivo = arquivoEscolhido.getSelectedFile();

            try {
                OutputStream os = new FileOutputStream(arquivo.getPath().replace("\\", "\\\\") + "\\Result-" + this.archiveChoose); // nome do arquivo que será escrito
                Writer wr = new OutputStreamWriter(os); // criação de um escritor
                BufferedWriter br = new BufferedWriter(wr); // adiciono a um escritor de buffer

                br.write(txtResultado.getText());
                br.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "erro ao exportar arquivo");
            }
        }


    }//GEN-LAST:event_btnExportarMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscolherArquivo;
    private javax.swing.JButton btnExportar;
    private javax.swing.JPanel pnlBarraInferior;
    private javax.swing.JPanel pnlFundo;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JSeparator sep;
    private javax.swing.JLabel txtEndereco;
    private javax.swing.JLabel txtResultado;
    private javax.swing.JLabel txtTituloEnd;
    private javax.swing.JLabel txtTituloResultado;
    // End of variables declaration//GEN-END:variables
}
