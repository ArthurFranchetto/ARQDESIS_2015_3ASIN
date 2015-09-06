package com.example.arthur.project;

/**
 * Created by Arthur on 06/09/2015.
 */
public class Estados implements Comparable<Estados> {

    public String origem, destino;

    public Estados(String origem, String destino)
    {
        this.origem = origem;
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public String getOrigem() {
        return origem;
    }

    public String toString(){
       return "com.example.arthur.project{"+
               "origem='" + origem + '\'' +
               "destino='" + destino + '\'' +
               '}';
    }

    public int compareTo(Estados estados){
        if(origem.equals(estados.getOrigem())
                && destino.equals(estados.getDestino())){
            return 0;
        }
        return this.getOrigem().compareTo(estados.getOrigem());
    }
}
