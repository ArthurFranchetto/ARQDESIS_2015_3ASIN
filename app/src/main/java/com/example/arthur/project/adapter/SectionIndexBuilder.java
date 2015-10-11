package com.example.arthur.project.adapter;

import com.example.arthur.project.model.Voo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

/**
 * Created by Arthur on 20/09/2015.
 */
public class SectionIndexBuilder {

    public static Object[] BuildSectionHeaders(Voo[] voos)
    {
        ArrayList<String> results = new ArrayList<>();
        TreeSet<String> used    = new TreeSet<>();
        if(voos != null) {
            for (Voo item : voos) {
                String letter = item.getOrigem().substring(0, 1);

                if (!used.contains(letter))
                    results.add(letter);

                used.add(letter);
            }
        }
        return results.toArray(new Object[0]);
    }

    //cria um mapa para responder: posicao --> secao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> BuildSectionForPositionMap(Voo[] voos)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(voos != null) {
            for (int i = 0; i < voos.length; i++) {
                String letter = voos[i].getOrigem().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                }

                results.put(i, section);
            }
        }
        return results;
    }

    //cria um mapa para responder: secao --> posicao de dados ordenados pelo nome
    public static Hashtable<Integer, Integer> BuildPositionForSectionMap(Voo[] voos)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(voos != null) {
            for (int i = 0; i < voos.length; i++) {
                String letter = voos[i].getOrigem().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                    results.put(section, i);
                }
            }
        }
        return results;
    }
}