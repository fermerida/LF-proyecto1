package memoria_dinamica;

public class ListaLexico {
    String metodo;
    private NodoLexico inicio;
    NodoLexico actual, anterior, nodo;
    ListaErrorSintactico listaErrorSintactico;
    private ListaDeclaracion listaDeclaracion;
    private ListaBusqueda listaBusqueda;
    private ListaFunciones listaFunciones;
    boolean erroresSintacticos = false, flagCorchetes=false, flagProy=false, flagMetodo=false, flagValor;
    
    public ListaLexico(){
        inicio=null;
        actual=null;
        nodo=null;
    }
    
    public void insertar(NodoLexico nuevo){
        if(inicio==null){
            inicio=nuevo;
        }else{
            actual=inicio;
            while(actual.sig!=null){
                actual=actual.sig;
            }
            actual.sig=nuevo;
        }
    }
    
    public NodoLexico getInicio(){
        return inicio;
    }
    
    public void generarSintactico(){
        int estado = 0;
        int fila=0, filaAnt;
        int columna=0, columnaAnt;
        String token;
        String desc="";
        String tipo="";
        listaErrorSintactico = new ListaErrorSintactico();
        
        actual=inicio;
        while(actual!=null){
            if(actual.getDesc().equals("Error léxico")){
                actual=actual.sig;
            }
            
            filaAnt = fila;
            columnaAnt = columna + 1;
            token = actual.getLexema();
            fila = actual.getFila();
            columna = actual.getColumna();
            tipo = actual.getDesc();
                
            switch(estado){
                case 0:
                    if(token.equals("Proyecto1")){
                        estado=1;
                    }else{
                        desc = "Error sintáctico: se esperaba ''Proyecto1''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, 0, 0);
                        estado=1;
                        flagProy=true;
                    }
                break;
                case 1:
                    if(token.equals("{")){
                        estado=2;
                    }else{
                        desc = "Error sintáctico: se esperaba ''{''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, 0, 10);
                        estado=2;
                        flagMetodo=true;
                    }
                break;
                case 2:
                    if(token.equals("Declaraciones")){
                        estado=3;
                        metodo="Declaraciones";
                    }else if(token.equals("Busqueda")){
                        estado=15;
                        metodo="Busqueda";
                    }else if(token.equals("Funciones")){
                        estado=23;
                        metodo="Funciones";
                    }else if(token.equals("Entrada")){
                        estado=30;
                        metodo="Entrada";
                    }else if(token.equals("}")){
                        estado=12;
                    }else if(token.equals("var")){
                        desc = "Error sintáctico: se esperaba método";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, fila, 0);
                        actual=anterior;
                        estado=3;
                    }else if(token.equals("Abrir") || token.equals("Busqueda_Web")){
                        desc = "Error sintáctico: se esperaba método";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, fila, 0);
                        actual=anterior;
                        metodo="funciones";
                        estado=23;
                    }else if(token.equals("Buscar")){
                        desc = "Error sintáctico: se esperaba método";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, fila, 0);
                        actual=anterior;
                        metodo="busqueda";
                        estado=15;
                    }else{
                        desc = "Error sintáctico: se esperaba ''}'' o método";
                        erroresSintacticos = true;
                        if(flagCorchetes==true){
                            fila++;
                        }
                        guardarNodoError(token, desc, fila, 0);
                        actual=anterior;
                        if(token.equals("{")){
                            estado=3;
                        }else{
                            estado=12;
                        }
                    }
                break;
                case 3:
                    if(token.equals("{")){
                        estado=4;
                    }else{
                        desc = "Error sintáctico: se esperaba ''{''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, metodo.length());
                        estado=4;
                        actual=anterior;
                    }
                break;
                case 4:
                    if(token.equals("var")){
                        estado=5;
                    }else if(token.equals("}")){
                        estado=11;
                    }else if(token.equals("Buscar")){
                        estado=16;
                        actual=anterior;
                    }else if(token.equals("Abrir")  || token.equals("Busqueda_Web")){
                        estado=24;
                        actual=anterior;
                    }else{
                        if(token.equals(";") || token.equals("")){
                            desc = "Error sintáctico: se esperaba ''}''";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, fila, columna);
                            estado=11;
                            actual=anterior;
                            flagCorchetes=true;
                        }else{
                            desc = "Error!: se esperaba ''var''";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, fila, columna);
                            estado=5;
                            actual=anterior;
                        }
                    }
                break;
                case 5:
                    if(token.equals("cadena") || token.equals("numero_entero") || token.equals("numero_real")){
                        estado=6;
                    }else{
                        desc = "Error sintáctico: se esperaba tipo de variable";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, fila, columna);
                        estado=6;
                        actual=anterior;
                    }
                break;
                case 6:
                    if(tipo.equals("Identificador")){
                        estado=7;
                    }else{
                        desc = "Error sintáctico: se esperaba nombre de la variable";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, fila, columna);
                        estado=7;
                        actual=anterior;
                    }
                break;
                case 7:
                    if(token.equals(";")){
                        estado=4;
                    }else if(token.equals("=")){
                        estado=8;
                    }else{
                        desc = "Error sintáctico: se esperaba '';'' o ''=''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, (columnaAnt + anterior.getLexema().length() - 1));
                        if(token.equals("var") || token.equals("}")){
                            estado=4;
                        }else{
                            estado=8;
                        }
                        actual=anterior;
                    }
                break;
                case 8:
                    if(token.equals("\"") || token.equals("“") || token.equals("”")){
                        estado=9;
                    }else if(tipo.equals("Numero")){
                        estado=13;
                        actual=anterior;
                    }else{
                        desc = "Error sintáctico: se esperaba \" o valor numérico";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                        if(token.equals(";")){
                            estado=4;
                        }else{
                            estado=9;
                            actual=anterior;
                        }
                    }
                break;
                case 9:
                    if(tipo.equals("Identificador")){
                        estado=9;
                    }else if(token.equals("\"") || token.equals("“") || token.equals("”")){
                        estado=10;
                    }else{
                        desc = "Error sintáctico: se esperaba \"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=10;
                        actual=anterior;
                    }
                break;
                case 10:
                    if(token.equals(";")){
                        estado=4;
                    }else{
                        desc = "Error sintáctico: se esperaba '';''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                        estado=4;
                        actual=anterior;
                    }
                break;
                case 11:
                    if(token.equals(";")){
                        estado=2;
                    }else{
                        desc = "Error sintáctico: se esperaba '';''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                        estado=2;
                        actual=anterior;
                    }
                break;
                case 12:
                    if(token.equals(";")){
                        actual=actual.sig;
                    }else{
                        desc = "Error sintáctico: se esperaba '';''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                    }
                break;
                case 13:
                    if(tipo.equals("Numero")){
                        estado=14;
                    }else{
                        desc = "Error sintáctico: se esperaba número";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=14;
                        actual=anterior;
                    }
                break;
                case 14:
                    if(token.equals(";")){
                        estado=4;
                    }else{
                        desc = "Error sintáctico: se esperaba '';''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                        estado=4;
                        actual=anterior;
                    }
                break;
                case 15:
                    if(token.equals("{")){
                        estado=16;
                    }else{
                        desc = "Error sintáctico: se esperaba ''{''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, metodo.length());
                        estado=16;
                        actual=anterior;
                    }
                break;
                case 16:
                    if(token.equals("Buscar")){
                        estado=17;
                    }else if(token.equals("}")){
                        estado=11;
                    }else{
                        if(token.equals(";") || token.equals("")){
                            desc = "Error sintáctico: se esperaba ''}''";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, fila, columna);
                            estado=11;
                            actual=anterior;
                            flagCorchetes=true;
                        }else{
                            desc = "Error sintáctico: se esperaba método \"Buscar\"";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, fila, columna);
                            estado=17;
                            actual=anterior;
                        }
                    }
                break;
                case 17:
                    if(token.equals("(")){
                        estado=18;
                    }else{
                        desc = "Error sintáctico: se esperaba \"(\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=18;
                        actual=anterior;
                    }
                break;
                case 18:
                    if(tipo.equals("Identificador") || tipo.equals("Numero")){
                        estado=19;
                    }else{
                        desc = "Error sintáctico: se esperaba cadena o variable";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=19;
                        actual=anterior;
                    }
                break;  
                case 19:
                    if(token.equals(",")){
                        estado=20;
                    }else{
                        desc = "Error sintáctico: se esperaba \",\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=20;
                        actual=anterior;
                    }
                break;
                case 20:
                    if(tipo.equals("Identificador")){
                        estado=21;
                    }else{
                        desc = "Error sintáctico: se esperaba color";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=21;
                        actual=anterior;
                    }
                break;
                case 21:
                    if(token.equals(")")){
                        estado=22;
                    }else{
                        desc = "Error sintáctico: se esperaba \")\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=22;
                        actual=anterior;
                    }
                break;
                case 22:
                    if(token.equals(";")){
                        estado=16;
                    }else{
                        desc = "Error sintáctico: se esperaba \";\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                        estado=16;
                        actual=anterior;
                    }
                break;
                case 23:
                    if(token.equals("{")){
                        estado=24;
                    }else{
                        desc = "Error sintáctico: se esperaba ''{''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, metodo.length());
                        estado=24;
                        actual=anterior;
                    }
                break;
                case 24:
                    if(token.equals("Abrir") || token.equals("Busqueda_Web")){
                        estado=25;
                    }else if(token.equals("}")){
                        estado=11;
                    }else{
                        if(token.equals(";") || token.equals("")){
                            desc = "Error sintáctico: se esperaba ''}''";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, fila, columna);
                            estado=11;
                            actual=anterior;
                            flagCorchetes=true;
                        }else{
                            desc = "Error sintáctico: se esperaba método \"Abrir\" o \"Busqueda_Web\"";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, fila, columna);
                            estado=25;
                            actual=anterior;
                        }
                    }
                break;
                case 25:
                    if(token.equals("(")){
                        estado=26;
                    }else{
                        desc = "Error sintáctico: se esperaba \"(\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=26;
                        actual=anterior;
                    }
                break;
                case 26:
                    if(tipo.equals("Identificador")){
                        if(actual.sig.getLexema().equals("\"") ||  actual.sig.getLexema().equals("“") || actual.sig.getLexema().equals("”")){
                            desc = "Error sintáctico: se esperaba \"";
                            erroresSintacticos = true;
                            guardarNodoError(token, desc, filaAnt, columna);
                            estado=29;
                            actual=anterior;
                        }else{
                            estado=27;
                        }
                    }else if(token.equals("\"") || token.equals("“") || token.equals("”")){
                        estado=29;
                    }else{
                        desc = "Error sintáctico: se esperaba variable o declaración";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=27;
                        actual=anterior;
                    }
                break;  
                case 27:
                    if(token.equals(")")){
                        estado=28;
                    }else{
                        desc = "Error sintáctico: se esperaba \")\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        estado=28;
                        actual=anterior;
                    }
                break;
                case 28:
                    if(token.equals(";")){
                        estado=24;
                    }else{
                        desc = "Error sintáctico: se esperaba \";\"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columnaAnt);
                        estado=24;
                        actual=anterior;
                    }
                break;
                case 29:
                    if(tipo.equals("Identificador")){
                        estado=29;
                    }else if(token.equals("\"") || token.equals("“") || token.equals("”")){
                        estado=27;
                    }else{
                        desc = "Error sintáctico: se esperaba \"";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, columna);
                        if(token.equals(";")){
                            estado=4;
                        }else{
                            estado=30;
                            actual=anterior;
                        }
                    }
                break;
                case 30:
                    if(token.equals("{")){
                        estado=31;
                    }else{
                        desc = "Error sintáctico: se esperaba ''{''";
                        erroresSintacticos = true;
                        guardarNodoError(token, desc, filaAnt, metodo.length());
                        estado=31;
                        actual=anterior;
                    }
                break;
                case 31:
                    while(!actual.getLexema().equals("}")){
                        if(actual.getLexema().equals("")){
                            break;
                        }else if(actual.getLexema().equals(";")){
                            estado=2;
                            break;
                        }
                        anterior=actual;
                        actual=actual.sig;
                    }
                    actual=anterior;
                    estado=24;
                break;  
                }
            if(flagProy==true || flagMetodo==true){
                flagProy=false;
                flagMetodo=false;
            }else{
                anterior=actual;
                actual=actual.sig;
            }
        }
    }
    
    public void guardarNodoError(String lexema, String desc, int fila, int columna){
        NodoErrorSintactico nuevo = new NodoErrorSintactico();
        nuevo.setLexema(lexema);
        nuevo.setDesc(desc);
        nuevo.setFila(fila);
        nuevo.setColumna(columna);
        listaErrorSintactico.insertar(nuevo);
    }
    
    public boolean errores(){
        return erroresSintacticos;
    }
    
    public void generarErroresSintacticos(){
        listaErrorSintactico.erroresSintacticos();
    }
    
    public void declaraciones(){
        String tipo="", nombre="", valor;
        listaDeclaracion = new ListaDeclaracion();
        flagValor=false;

        actual=inicio;
        while(actual!=null){
            valor="";
            if(actual.getLexema().equals("var")){
                tipo = actual.sig.getLexema();
                nombre = actual.sig.sig.getLexema();
                if(actual.sig.sig.sig.getLexema().equals(";")){
                    if(tipo.equals("cadena")){
                        valor = "";
                    }
                    if(tipo.equals("numero_entero")){
                        valor= "0";
                    }
                    if(tipo.equals("numero_real")){
                        valor= "0.0";
                    }
                }
                if(actual.sig.sig.sig.getLexema().equals("=")){
                    if(tipo.equals("cadena")){
                        nodo=actual.sig.sig.sig.sig.sig;
                        flagValor=true;
                        while(flagValor==true){
                            if(nodo.getLexema().equals("\"") || nodo.getLexema().equals("“") || nodo.getLexema().equals("”")){
                                flagValor=false;
                            }else{
                                valor = valor + nodo.getLexema() + " ";
                            }
                            nodo=nodo.sig;
                        }
                    }
                    if(tipo.equals("numero_entero") || tipo.equals("numero_real")){
                        valor = actual.sig.sig.sig.sig.getLexema();
                    }
                }
                guardarDeclaracion(tipo, nombre, valor.replaceAll("\\s*$",""));
            }
            actual=actual.sig;
        }
//        listaDeclaracion.mostrar();
    }
    
    public void guardarDeclaracion(String tipo, String nombre, String valor){
        NodoDeclaracion nuevo = new NodoDeclaracion();
        nuevo.setTipo(tipo);
        nuevo.setNombre(nombre);
        nuevo.setValor(valor);
        getListaDeclaracion().insertar(nuevo);
    }
    
    public void busqueda(){
        String cadena="", color="";
        listaBusqueda = new ListaBusqueda();
        
        actual=inicio;
        while(actual!=null){
            if(actual.getLexema().equals("Buscar")){
                cadena = actual.sig.sig.getLexema();
                color = actual.sig.sig.sig.sig.getLexema();
                guardarBusqueda(cadena, color);
            }
            actual=actual.sig;
        }
        
//        getListaBusqueda().mostrar();
    }
    
    public void guardarBusqueda(String cadena, String color){
        NodoBusqueda nuevo = new NodoBusqueda();
        nuevo.setCadena(cadena);
        nuevo.setColor(color);
        getListaBusqueda().insertar(nuevo);
    }
    
    public void funciones(){
        String cadena;
        metodo="";
        flagValor=false;
        listaFunciones = new ListaFunciones();
        
        actual=inicio;
        while(actual!=null){
            cadena="";
            metodo = actual.getLexema();
            if(metodo.equals("Abrir")){
                if(actual.sig.sig.getLexema().equals("\"") || actual.sig.sig.getLexema().equals("“") || actual.sig.sig.getLexema().equals("”")){
                    cadena = actual.sig.sig.sig.getLexema(); 
                }else{
                    cadena = actual.sig.sig.getLexema();
                }
                guardarFunciones(metodo, cadena);
            }
            if(actual.getLexema().equals("Busqueda_Web")){
                if(actual.sig.sig.getLexema().equals("\"") || actual.sig.sig.getLexema().equals("“") || actual.sig.sig.getLexema().equals("”")){
                    nodo=actual.sig.sig.sig;
                    flagValor=true;
                    while(flagValor==true){
                        if(nodo.getLexema().equals("\"") || nodo.getLexema().equals("“") || nodo.getLexema().equals("”")){
                            flagValor=false;
                        }else{
                            cadena = cadena + nodo.getLexema() + " ";
                        }
                        nodo=nodo.sig;
                    }
                }else{
                    cadena = actual.sig.sig.getLexema();
                }
                guardarFunciones(metodo, cadena.replaceAll("\\s*$",""));
            }
            actual=actual.sig;
        }
//        listaFunciones.mostrar();
    }
    
    public void guardarFunciones(String metodo, String cadena){
        NodoFunciones nuevo = new NodoFunciones();
        nuevo.setMetodo(metodo);
        nuevo.setCadena(cadena);
        getListaFunciones().insertar(nuevo);
    }

    public ListaBusqueda getListaBusqueda() {
        return listaBusqueda;
    }

    public ListaDeclaracion getListaDeclaracion() {
        return listaDeclaracion;
    }

    public ListaFunciones getListaFunciones() {
        return listaFunciones;
    }
}
