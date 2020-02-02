package lfp_Proyecto1;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class LFP_Proyecto1 {

    public static void main(String[] args) {
        try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFEditor editor = new JFEditor();
            editor.setLocationRelativeTo(null);
            editor.setResizable(false);
            editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editor.setTitle("Lenguajes formales de programación - Editor de texto");
            editor.show(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
