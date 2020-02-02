package lfp_Proyecto1;

import edu.stanford.ejalbert.BrowserLauncher;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import memoria_dinamica.ListaBusqueda;
import memoria_dinamica.ListaErrorSintactico;
import memoria_dinamica.ListaLexico;
import memoria_dinamica.NodoBusqueda;
import memoria_dinamica.NodoDeclaracion;
import memoria_dinamica.NodoFunciones;
import memoria_dinamica.NodoLexico;

public class JFEditor extends javax.swing.JFrame {
    Border borde = new LineBorder(Color.BLACK);
    String path="";
    String letras = "aábcdeéfghiíjklmnñoópqrstuúvwxyz/_\\";
    String numeros= "0123456789";
    String signos = "(){}[],;:.=\"“”";
    String cadena, tipo="", num="";
    int columna, fila, inicio, numero, token;
    boolean erroresLexicos = false;
    ListaLexico listaLexico;
    ListaErrorSintactico listaErrorSintactico;
    ListaBusqueda listaBusqueda;
    NodoDeclaracion actualD = new NodoDeclaracion();
    Highlighter hilit = new DefaultHighlighter();
    Highlighter.HighlightPainter painter;

    public JFEditor() {
        initComponents();
        jTextArea1.requestFocus();
        jTextArea1.addCaretListener(new CaretListener(){
            public void caretUpdate(CaretEvent e){
                int x=0, y;
                try{
                    Rectangle rectangle = jTextArea1.modelToView(jTextArea1.getCaretPosition());
                    y = (int)rectangle.getY()/17;
                    x = jTextArea1.getCaretPosition();
                    jLabel2.setText(String.valueOf(y));
                    jLabel4.setText(String.valueOf(x));
                }
                catch(Exception ble) {}
            }
        });
        
        jTextArea1.setBorder(borde);
        jTextArea2.setBorder(borde);
        jTextArea3.setBorder(borde);
        jTextArea6.setBorder(borde);
        jTextArea7.setBorder(borde);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Realizar análisis léxico-sintáctico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Fila:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" ");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Columna:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(" ");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Declaraciones:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Busqueda:");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane6.setViewportView(jTextArea6);

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jScrollPane7.setViewportView(jTextArea7);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Funciones:");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Entrada:");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Abrir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Busqueda web");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu1.setText("Menú");

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edición");

        jMenuItem4.setText("Copiar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Cortar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Pegar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");

        jMenuItem7.setText("Manual de usuario");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Manual técnico");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Acerca de");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(33, 33, 33))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser chooser=new JFileChooser();
        FileNameExtensionFilter filtro=new FileNameExtensionFilter("Lenguajes formales de programación - Práctica 1","pro1lfp");
        chooser.setFileFilter(filtro);
        chooser.setCurrentDirectory(new File("/home/lorente/Escritorio"));
        chooser.showOpenDialog(this);
        path = chooser.getSelectedFile().getAbsolutePath();

        jTextArea1.setText("");

        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine()) != null){
                jTextArea1.append(linea + "\n");
            }
            br.close();
            fr.close();
        }catch(Exception er){
            System.out.println(er);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(path==""){
            JFileChooser chooser=new JFileChooser();
            FileNameExtensionFilter filtro=new FileNameExtensionFilter("Lenguajes formales de programación - Práctica 1","prac1lfp");
            chooser.setFileFilter(filtro);
            chooser.setCurrentDirectory(new File("/home/lorente/Escritorio"));
            chooser.showSaveDialog(this);
            
            try{
                FileWriter fw = new FileWriter(chooser.getSelectedFile().getAbsolutePath());
                    fw.write(jTextArea1.getText());
                    fw.flush();
                fw.close();
                JOptionPane.showMessageDialog(null, "Archivo guadado!", "LFP_P1 - Editor", 1);
            }catch(IOException er){
                System.out.println(er);
            }
        }else{
            try{
                FileWriter fw = new FileWriter(path);
                    fw.write(jTextArea1.getText());
                    fw.flush();
                fw.close();
                JOptionPane.showMessageDialog(null, "Archivo guadado!", "LFP_P1 - Editor", 1);
            }catch(IOException er){
                System.out.println(er);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        erroresLexicos=false;
        jTextArea2.setText("");
        jTextArea3.setText("");
        jTextArea6.setText("");
        jTextArea7.setText("");
        if(jTextArea1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese un texto para analizar!", "Lenguajes formales de programación", 2);
        }else{
            lexico(jTextArea1.getText());
            listaLexico.generarSintactico();
            if(erroresLexicos==false && listaLexico.errores()==false){
                tablatokens();
                abrirArchivos("Reportes/Tokens.html");
                agregarMetodos();
                listaLexico.declaraciones();
                listaLexico.busqueda();
                listaLexico.funciones();
            }else{
                if(erroresLexicos==true){
                    erroresLexicos();
                    abrirArchivos("Reportes/ErroresLexicos.html");
                }
                if(listaLexico.errores()==true){
                    listaLexico.generarErroresSintacticos();
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jTextArea1.copy();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jTextArea1.cut();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jTextArea1.paste();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        abrirArchivos("Manuales/LFP - Manual de usuario.pdf");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        abrirArchivos("Manuales/LFP - Manual técnico.pdf");
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFAcerca acerca = new JFAcerca();
            acerca.setLocationRelativeTo(null);
            acerca.setResizable(false);
            acerca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            acerca.setTitle("LFP - Editor de texto");
            acerca.show(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Color color=null;
        String cadenaNodo="", colorNodo="";
        NodoBusqueda actualB = new NodoBusqueda();
        actualB=listaLexico.getListaBusqueda().getInicio();
        jTextArea3.setHighlighter(hilit);

        while(actualB!=null){
            cadenaNodo = actualB.getCadena();
            colorNodo = actualB.getColor();
            actualD=listaLexico.getListaDeclaracion().getInicio();            
            while(actualD!=null){
                if(actualD.getNombre().equals(cadenaNodo)){
                    cadenaNodo=actualD.getValor();
                    break;
                }
                actualD=actualD.sig;
            }
            switch(colorNodo){
                case "rojo":
                    color = Color.RED;
                break;
                case "amarillo":
                    color = Color.YELLOW;
                break;
                case "azul":
                    color = Color.BLUE;
                break;
                case "verde":
                    color = Color.GREEN;
                break;
                case "morado":
                    color = Color.MAGENTA;
                break;
                case "rosado":
                    color = Color.PINK;
                break;
                case "cafe":
                    color = null;
                break;
                case "celeste":
                    color = Color.CYAN;
                break;
                case "naranja":
                    color = Color.ORANGE;
                break;
                case "gris":
                    color = Color.LIGHT_GRAY;
                break;
            }
            buscarTexto(cadenaNodo, color);
            actualB=actualB.sig;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String metodo, path;
        NodoFunciones actualF = new NodoFunciones();
        actualF = listaLexico.getListaFunciones().getInicio();
        
        while(actualF!=null){
            metodo = actualF.getMetodo();
            if(metodo.equals("Abrir")){
                path = actualF.getCadena();
                if(path.toLowerCase().equals("calculadora")){
                   ejecutarProg("/usr/bin/gnome-calculator");
                }else if(path.toLowerCase().equals("paint")){
                    ejecutarProg("/usr/bin/gpaint");
                }else{
                    actualD = listaLexico.getListaDeclaracion().getInicio();
                    while(actualD!=null){
                        if(actualD.getNombre().equals(path)){
                            path=actualD.getValor();
                            break;
                        }
                        actualD=actualD.sig;
                    }
                    abrirArchivos(path);
                }
            }
            actualF=actualF.sig;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String metodo, path;
        NodoFunciones actualF = new NodoFunciones();
        actualF = listaLexico.getListaFunciones().getInicio();
        
        while(actualF!=null){
            metodo = actualF.getMetodo();
            if(metodo.equals("Busqueda_Web")){
                path = actualF.getCadena();
                actualD = listaLexico.getListaDeclaracion().getInicio();
                while(actualD!=null){
                    if(actualD.getNombre().equals(path)){
                        path=actualD.getValor();
                        break;
                    }
                    actualD=actualD.sig;
                }
                buscarGoogle(path.replace(" ","+"));
            }
            actualF=actualF.sig;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(JFEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    // End of variables declaration//GEN-END:variables

    public void lexico(String texto){
        String caracter, sig="";
        int estado = 0;
        columna=0;
        fila=0;
        inicio=0;
        cadena="";
        listaLexico = new ListaLexico();

        for(int i=0; i<texto.length(); i++){
            caracter = String.valueOf(texto.charAt(i)).toLowerCase();
            switch(estado){
                case 0:
                    if(letras.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        tipo = "Identificador";
                        inicio=columna;
                        columna++;
                        estado=1;
                    }else if(numeros.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        tipo = "Numero";
                        inicio=columna;
                        columna++;
                        estado=4;
                    }else if(signos.contains(caracter)){
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Signo";
                        inicio=columna;
                        columna++;
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        estado=0;
                    }else if(caracter.equals(" ")){
                        columna++;
                        estado=0;
                    }else if(caracter.equals("\n")){
                        columna=0;
                        fila++;
                        estado=0;
                    }else{
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                break;
                case 1:
                    if(letras.contains(caracter) || numeros.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        columna++;
                        estado=1;
                    }else if(signos.contains(caracter)){
                        if(caracter.equals(".")){
                            if(String.valueOf(texto.charAt(i+1)).equals(" ") || String.valueOf(texto.charAt(i+1)).equals("\n")){
                                guardarlexico(cadena, tipo, inicio, fila);
                                cadena = String.valueOf(texto.charAt(i));
                                tipo = "Signo";
                                guardarlexico(cadena, tipo, columna, fila);
                                cadena="";
                                columna++;
                                estado=0;
                            }else if(!signos.contains(String.valueOf(texto.charAt(i+1)))){
                                cadena = cadena + String.valueOf(texto.charAt(i));
                                columna++;
                                estado=2;
                            }
                        }else if(caracter.equals(":")){
                            if(String.valueOf(texto.charAt(i+1)).equals(" ") || String.valueOf(texto.charAt(i+1)).equals("\n")){
                                guardarlexico(cadena, tipo, inicio, fila);
                                cadena = String.valueOf(texto.charAt(i));
                                tipo = "Signo";
                                guardarlexico(cadena, tipo, columna, fila);
                                cadena="";
                                columna++;
                                estado=0;
                            }else{
                                cadena = cadena + String.valueOf(texto.charAt(i));
                                columna++;
                                estado=1;
                            }
                        }else{
                            guardarlexico(cadena, tipo, inicio, fila);
                            cadena = String.valueOf(texto.charAt(i));
                            tipo = "Signo";
                            guardarlexico(cadena, tipo, columna, fila);
                            cadena="";
                            columna++;
                            estado=0;
                        }
                    }else if(caracter.equals(" ")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals("\n")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna=0;
                        fila++;
                        estado=0;
                    }else{
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                break;
                case 2:
                    cadena = cadena + String.valueOf(texto.charAt(i));
                    columna++;
                    estado=3;
                break;
                case 3:
                    if(letras.contains(caracter) || numeros.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        columna++;
                        estado=3;
                    }else if(signos.contains(caracter)){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Signo";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals(" ")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals("\n")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna=0;
                        fila++;
                        estado=0;
                    }else{
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                break;
                case 4:
                    if(numeros.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        columna++;
                        estado=4;
                    }else if(letras.contains(caracter)){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        tipo="Identificador";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                    else if(caracter.equals(".")){
                        num=cadena;
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        columna++;
                        estado=5;
                    }else if(signos.contains(caracter)){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Signo";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals(" ")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals("\n")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna=0;
                        fila++;
                        estado=0;
                    }else{
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                break;
                case 5:
                    if(numeros.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        columna++;
                        estado=6;
                    }else if(letras.contains(caracter)){
                        cadena = num;
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = ".";
                        tipo="Signo";
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        tipo="Identificador";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }else if(signos.contains(caracter)){
                        cadena = num;
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = ".";
                        tipo="Signo";
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        tipo="Signo";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }else if(caracter.equals(" ")){
                        cadena = num;
                        tipo = "Numero";
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = ".";
                        tipo = "Signo";
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals("\n")){
                        cadena = num;
                        tipo = "Numero";
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = ".";
                        tipo = "Signo";
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna=0;
                        fila++;
                        estado=0;
                    }else{
                        cadena = num;
                        tipo = "Numero";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena = ".";
                        tipo = "Signo";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                break;
                case 6:
                    if(numeros.contains(caracter)){
                        cadena = cadena + String.valueOf(texto.charAt(i));
                        columna++;
                        estado=6;
                    }else if(signos.contains(caracter)){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Signo";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals(" ")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna++;
                        estado=0;
                    }else if(caracter.equals("\n")){
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena="";
                        columna=0;
                        fila++;
                        estado=0;
                    }else{
                        guardarlexico(cadena, tipo, inicio, fila);
                        cadena = String.valueOf(texto.charAt(i));
                        tipo = "Error léxico";
                        guardarlexico(cadena, tipo, columna, fila);
                        cadena="";
                        columna++;
                        erroresLexicos = true;
                        estado=0;
                    }
                break;
            }
        }
        guardarlexico("", "Error léxico", 0, fila);
    }
        
    public void guardarlexico(String cadena, String tipo, int columna, int fila){
        switch(tipo){
            case "Identificador":
                token = 1;
            break;
            case "Numero":
                token = 2;
            break;
            case "Signo":
                token = 3;
            break;
            case "Error léxico":
                token = 4;
            break;
        }
        
        NodoLexico nuevo = new NodoLexico();
        nuevo.setToken(token);
        nuevo.setLexema(cadena);
        nuevo.setDesc(tipo);
        nuevo.setColumna(columna);
        nuevo.setFila(fila);
        listaLexico.insertar(nuevo);
    }
    
    public void tablatokens(){
        erroresLexicos = false;
        numero=0;
        try{
                FileWriter fw = new FileWriter("Reportes/Tokens.html");
                    fw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + "\n"  + "\n");
                    fw.write("<HTML><HEAD><TITLE>LENGUAJES FORMALES DE PROGRAMACIÓN</TITLE></HEAD>" + "\n" + "\n");
                    fw.write("<H1><CENTER><B><FONT SIZE=\"10\" COLOR=\"BLACK\">LISTADO DE</FONT></B><BR><CENTER><B><FONT SIZE=\"20\" COLOR=\"RED\">" + "TOKEN'S" + "</FONT></B></H1>" + "\n" + "\n");
                    fw.write("<HR>" + "\n" + "\n");
                    fw.write("<BR><CENTER><TABLE BORDER=1>\n");
                    fw.write("	<TR>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>NO.</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>TOKEN</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>LEXEMA</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>DESCRIPCIÓN</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>FILA</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>COLUMNA</B></FONT></TD>\n");
                    fw.write("	</TR>\n");
                    
                    NodoLexico registro = new NodoLexico();
                    registro = listaLexico.getInicio();
                    while(registro!=null){
                        numero++;
                        if(registro.getDesc()!="Error léxico"){
                            fw.write("	<TR>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"RED\">" + numero + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getToken() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getLexema() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getDesc() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getFila() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getColumna() + "</FONT></TD>\n");
                            fw.write("	</TR>\n");
                        }
                        registro=registro.sig;
                    }
                    fw.write("</TABLE>" + "\n");
                    fw.flush();
                fw.close();

            }catch(IOException er){
                System.out.println(er);
            }
            JOptionPane.showMessageDialog(null, "Reporte de token's generado!", "LFP", 1);
    }
    
    public void erroresLexicos(){
        erroresLexicos=false;
        numero=1;
        try{
                FileWriter fw = new FileWriter("Reportes/ErroresLexicos.html");
                    fw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + "\n"  + "\n");
                    fw.write("<HTML><HEAD><TITLE>LENGUAJES FORMALES DE PROGRAMACIÓN</TITLE></HEAD>" + "\n" + "\n");
                    fw.write("<H1><CENTER><B><FONT SIZE=\"10\" COLOR=\"BLACK\">LISTADO DE</FONT></B><BR><CENTER><B><FONT SIZE=\"20\" COLOR=\"RED\">" + "ERRORES LÉXICOS" + "</FONT></B></H1>" + "\n" + "\n");
                    fw.write("<HR>" + "\n" + "\n");
                    fw.write("<BR><CENTER><TABLE BORDER=1>\n");
                    fw.write("	<TR>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>NO.</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>LEXEMA</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>DESCRIPCIÓN</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>FILA</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>COLUMNA</B></FONT></TD>\n");
                    fw.write("	</TR>\n");
                    
                    NodoLexico registro = new NodoLexico();
                    registro = listaLexico.getInicio();
                    while(registro!=null){
                        if(registro.getDesc()=="Error léxico" && registro.getLexema()!=""){
                            fw.write("	<TR>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"RED\">" + numero++ + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getLexema() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getDesc() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getFila() + "</FONT></TD>\n");
                            fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + registro.getColumna() + "</FONT></TD>\n");
                            fw.write("	</TR>\n");
                        }
                        registro=registro.sig;
                    }
                    fw.write("</TABLE>" + "\n");
                    fw.flush();
                fw.close();

            }catch(IOException er){
                System.out.println(er);
            }
            JOptionPane.showMessageDialog(null, "Reporte de errores léxicos generado!", "LFP", 1);
    }

    public void buscarTexto(String palabra, Color color) {
        String texto = jTextArea3.getText();
        String[] palabras = new String[texto.length()];
        StringTokenizer separar = new StringTokenizer(texto);
        int tama=0;
        while(separar.hasMoreTokens()){
            palabras[tama] = separar.nextToken();
            tama++;
        }
        String bus ="";
        if(!palabras[0].equals("")){
            bus = palabras[tama];
            while(bus!=null){
                bus = palabras[tama];
                tama++;
            }
        }
        
        painter = new DefaultHighlighter.DefaultHighlightPainter(color);
        texto = palabra;
        int pos=0;
        String cadena = jTextArea3.getText();
        while(pos < cadena.length()){
            if (texto.length() > 0) {
             String contenido = jTextArea3.getText();
                int index = contenido.indexOf(texto, pos);
                if (index >= 0) {
                    try {
                        int end = index + texto.length();
                        hilit.addHighlight(index, end, painter);
                        jTextArea3.setCaretPosition(end);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
            pos++;
        }
    }
    
    public void abrirArchivos(String path){
        try {
            File file = new File(path);
            Desktop.getDesktop().open(file);
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "La dirección: \"" + path + "\" no es válida para este sistema operativo!", "Lenguajes formales de programación", 2);
        }
    }
    
    public void agregarMetodos(){
        String lineaAnt="";
        boolean declaraciones=false, busqueda=false, funciones=false, entrada=false;
        
        jTextArea2.setText("");
        jTextArea3.setText("");
        jTextArea6.setText("");
        jTextArea7.setText("");

        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine()) != null){
                
                if(lineaAnt.equals("Declaraciones{")){
                    declaraciones=true;
                }
                if(linea.equals("};")){
                    declaraciones=false;
                }
                if(declaraciones==true){
                    jTextArea6.append(linea + "\n");
                }

                if(lineaAnt.equals("Busqueda{")){
                    busqueda=true;
                }
                if(linea.equals("};")){
                    busqueda=false;
                }
                if(busqueda==true){
                    jTextArea7.append(linea + "\n");
                }

                if(lineaAnt.equals("Funciones{")){
                    funciones=true;
                }
                if(linea.equals("};")){
                    funciones=false;
                }
                if(funciones==true){
                    jTextArea2.append(linea + "\n");
                }

                if(lineaAnt.equals("Entrada{")){
                    entrada=true;
                }
                if(linea.equals("};")){
                    entrada=false;
                }
                if(entrada==true){
                    jTextArea3.append(linea + "\n");
                }

                lineaAnt=linea;
            }
            br.close();
            fr.close();
        }catch(Exception er){
            System.out.println(er);
        }
    }
    
    public void ejecutarProg(String path){
        try{
            Runtime rt = Runtime.getRuntime();           
            Process p = rt.exec(path);            
        }catch ( IOException ioe ){            
            ioe.printStackTrace();
        }
    }
    
    public void buscarGoogle(String cadena){
        try{
        BrowserLauncher launcher = new BrowserLauncher();
        launcher.openURLinBrowser("http://google.com/search?q=" + cadena);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}