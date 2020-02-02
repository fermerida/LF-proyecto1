package memoria_dinamica;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ListaErrorSintactico {
    private NodoErrorSintactico inicio;
    NodoErrorSintactico actual;
    int numero;
    
    public ListaErrorSintactico(){
        inicio=null;
        actual=null;
    }
    
    public void insertar(NodoErrorSintactico nuevo){
        if(getInicio()==null){
            inicio=nuevo;
        }else{
            actual=getInicio();
            while(actual.sig!=null){
                actual=actual.sig;
            }
            actual.sig=nuevo;
        }
    }
    
    public NodoErrorSintactico getInicio() {
        return inicio;
    }
    
    public void erroresSintacticos(){
        numero=1;
        try{
                FileWriter fw = new FileWriter("Reportes/ErroresSintacticos.html");
                    fw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + "\n"  + "\n");
                    fw.write("<HTML><HEAD><TITLE>LENGUAJES FORMALES DE PROGRAMACIÓN</TITLE></HEAD>" + "\n" + "\n");
                    fw.write("<H1><CENTER><B><FONT SIZE=\"10\" COLOR=\"BLACK\">LISTADO DE</FONT></B><BR><CENTER><B><FONT SIZE=\"20\" COLOR=\"RED\">" + "ERRORES SINTÁCTICOS" + "</FONT></B></H1>" + "\n" + "\n");
                    fw.write("<HR>" + "\n" + "\n");
                    fw.write("<BR><CENTER><TABLE BORDER=1>\n");
                    fw.write("	<TR>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>NO.</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>LEXEMA</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>DESCRIPCIÓN</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>FILA</B></FONT></TD>\n");
                    fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLACK\"><B>COLUMNA</B></FONT></TD>\n");
                    fw.write("	</TR>\n");
                    
                    actual=inicio;
                    while(actual!=null){
                        fw.write("	<TR>\n");
                        fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"RED\">" + numero++ + "</FONT></TD>\n");
                        fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + actual.getLexema() + "</FONT></TD>\n");
                        fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + actual.getDesc() + "</FONT></TD>\n");
                        fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + actual.getFila() + "</FONT></TD>\n");
                        fw.write("	<TD ALIGN=\"CENTER\"><FONT COLOR=\"BLUE\">" + actual.getColumna() + "</FONT></TD>\n");
                        fw.write("	</TR>\n");
                        actual=actual.sig;
                    }
                    fw.write("</TABLE>" + "\n");
                    fw.flush();
                fw.close();

            }catch(IOException er){
                System.out.println(er);
            }
            JOptionPane.showMessageDialog(null, "Reporte de errores sintácticos generado!", "LFP", 1);
            
        try {
            File file = new File("Reportes/ErroresSintacticos.html");
            Desktop.getDesktop().open(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
