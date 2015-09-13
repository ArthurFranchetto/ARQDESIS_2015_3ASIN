package com.example.arthur.project;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListaDeVoos extends ListActivity {

    String origem, destino;
    public final static String VOO = "com.example.arthur.project.VOO";
    Voo[] disponiveis;
    Activity atividade;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        atividade = this;

        Especialista especialista = new Especialista();

        Intent intent = getIntent();
        String origem = intent.getStringExtra(ListarVoo.ORIGEM);
        String destino = intent.getStringExtra(ListarVoo.DESTINO);

        disponiveis = especialista.listarVoo(origem,destino).toArray(new Voo[0]);
        String[] lista = null;

        listView = (ListView) findViewById(R.id.view_lista_voo);
        BaseAdapter adapter;
        adapter = new VooAdapter(this, disponiveis);
        listView.setAdapter(adapter);

        for (int i = 0; i < disponiveis.length; i++) {
            lista[i] = disponiveis[i].getOrigem();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(atividade, DetalheVooActivity.class);
                intent.putExtra(VOO, disponiveis[position]);

                startActivity(intent);

            }

        });


        // Binding resources Array to ListAdapter
        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, disponiveis));
    }
/*
    public void setArray()
    {
        /*if(origem.equals("Escolha a origem")&& destino.equals("Escolha o destino")) {
            disponiveis = getResources().getStringArray(R.array.SPRJ);}
        if(origem.equals("São Paulo")&& destino.equals("Rio de Janeiro")) {
            disponiveis = getResources().getStringArray(R.array.SPRJ);}
        else if(origem.equals("Rio de Janeiro") && destino.equals("São Paulo")){
            disponiveis = getResources().getStringArray(R.array.RJSP);
        }
        else if(origem.equals("Rio de Janeiro") && destino.equals("Amazonas")){
            disponiveis = getResources().getStringArray(R.array.RJAM);
        }
        else if(origem.equals("São Paulo") && destino.equals("Amazonas")){
            disponiveis = getResources().getStringArray(R.array.SPAM);
        }
        else if(origem.equals("Amazonas") && destino.equals("São Paulo")){
            disponiveis = getResources().getStringArray(R.array.AMSP);
        }
        else if(origem.equals("Rio de Janeiro") && destino.equals("Amazonas")){
            disponiveis = getResources().getStringArray(R.array.AMRJ);
        }
        else if(origem.equals("Rio de Janeiro") && destino.equals("Amazonas")){
            disponiveis = getResources().getStringArray(R.array.AMRJ);
        }
        else if(origem.equals("Rio de Janeiro") && destino.equals("Amazonas")){
            disponiveis = getResources().getStringArray(R.array.AMRJ);
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_de_voos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
