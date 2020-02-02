package memoria_dinamica;

public class NodoBusqueda {
    private String cadena;
    private String color;
    public NodoBusqueda sig;
    
    public NodoBusqueda(){
        cadena="";
        color="";
        sig=null;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
