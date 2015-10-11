package com.example.arthur.project.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.arthur.project.R;
import com.example.arthur.project.adapter.VooAdapter;
import com.example.arthur.project.model.Voo;

import java.util.ArrayList;


public class ListaDeVoos extends ListActivity {
    ListView listView;
    Activity atividade;
    public final static String VOO = "com.example.arthur.Voo";
    Voo[] voos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_voos);
        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();
        //voos = ((ArrayList<Voo>)intent.getSerializableExtra(ListarVoo.VOO)).toArray(new Voo[][0]);

        //cria o listview de cervejas
        listView = (ListView) findViewById(R.id.view_lista_voo);

        VooAdapter adapter = new VooAdapter(this, voos);

        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
      //          Intent intent = new Intent(atividade, DetalheCervejaActivity.class);
        //        intent.putExtra(CERVEJA, cervejas[position]);

          //      startActivity(intent);

            }

        });
    }

}
