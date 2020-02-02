package memoria_dinamica;

public class NodoErrorSintactico {
    private int fila;
    private int columna;
    private String lexema;
    private String desc;
    NodoErrorSintactico sig;
    
    public NodoErrorSintactico(){
        fila=0;
        columna=0;
        lexema="";
        desc="";
        sig=null;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
