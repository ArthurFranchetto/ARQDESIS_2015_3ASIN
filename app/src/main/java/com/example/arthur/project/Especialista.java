package com.example.arthur.project;

/**
 * Created by Arthur on 13/09/2015.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import java.text.DateFormat;

public class Especialista {
    private static final ArrayList<Voo> voos = cadastroDeVoos();

    public TreeSet<Voo> listarVoo(String origem, String destino){
        TreeSet<Voo> voos = new TreeSet<>();

        if(origem. length() > 0 && destino.length()> 0){
            voos = buscarVoos(origem, destino);
        }
        return voos;
    }

    private TreeSet<Voo> buscarVoos(String origem, String destino){
        TreeSet<Voo> lista = new TreeSet<>();
        for(Voo voo: voos){
            if(origem.equals( voo.getOrigem())
                && destino.equals(voo.getDestino())){
                lista.add(voo);
            }
        }
        return lista;
    }


    private static ArrayList<Voo> cadastroDeVoos() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date date = null;
        try {
            date = (Date) formatter.parse("13/12/2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        voos.add(new Voo(815, "São Paulo", "Rio de Janeiro", 100.00, date, "10:00", "Atrasado", 0, 1, "fotinho"));
        voos.add(new Voo(816, "Rio de Janeiro", "São Paulo", 100.00, date, "10:00", "Atrasado", 0, 1, "fotinho"));

        return voos;
    }

    public void converte()
    {


    }
}

