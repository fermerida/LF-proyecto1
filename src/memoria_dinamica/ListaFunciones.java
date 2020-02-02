package memoria_dinamica;

public class ListaFunciones {
    private NodoFunciones inicio;
    NodoFunciones actual;
    
    public ListaFunciones(){
        inicio=null;
        actual=null;
    }
    
    public void insertar(NodoFunciones nuevo){
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
    
    public NodoFunciones getInicio() {
        return inicio;
    }
}
