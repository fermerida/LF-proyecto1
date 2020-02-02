package memoria_dinamica;

public class NodoFunciones {
    private String metodo;
    private String cadena;
    public NodoFunciones sig;
    
    public NodoFunciones(){
        metodo="";
        cadena="";
        sig=null;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getCadena() {
        return cadena;
    }
    
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
}
