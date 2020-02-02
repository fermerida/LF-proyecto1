package memoria_dinamica;

public class ListaBusqueda {
    private NodoBusqueda inicio;
    NodoBusqueda actual;
    
    public ListaBusqueda(){
        inicio=null;
        actual=null;
    }
    
    public void insertar(NodoBusqueda nuevo){
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

    public NodoBusqueda getInicio() {
        return inicio;
    }
}
