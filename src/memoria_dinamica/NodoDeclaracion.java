package memoria_dinamica;

public class NodoDeclaracion {
    private String tipo;
    private String nombre;
    private String valor;
    public NodoDeclaracion sig;

    public NodoDeclaracion(){
        tipo="";
        nombre="";
        valor="";
        sig=null;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
