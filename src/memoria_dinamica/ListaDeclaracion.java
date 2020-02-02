package memoria_dinamica;

public class ListaDeclaracion {
    private NodoDeclaracion inicio;
    NodoDeclaracion actual;
    
    public ListaDeclaracion(){
        inicio=null;
        actual=null;
    }

    public void insertar(NodoDeclaracion nuevo){
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
    
    public NodoDeclaracion getInicio() {
        return inicio;
    }
}
