package com.example.arthur.project.controller;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arthur.project.R;
import com.example.arthur.project.data.CategoriasDb;
import com.example.arthur.project.model.Voo;
import com.example.arthur.project.network.VooRequester;

import java.io.IOException;
import java.util.ArrayList;

public class ListarVoo extends AppCompatActivity {

    Spinner spinnerOrigem, spinnerDestino;
    String destino, origem;
    Button btnConsultar;
    ArrayList<Voo> voos;
    final String servidor = "10.0.2.2:8080/ProjetoIntegrado_ARQUIDESIS_14.10_2.0/";
    VooRequester requester;
    ProgressBar mProgress;
    Intent intent;
    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_voo);
        this.contexto = this;
        setupViews();
    }

    private void setupViews(){
        destino = "";
        origem = "";
        btnConsultar = (Button) findViewById(R.id.btn_pesquisar);

        mProgress = (ProgressBar) findViewById(R.id.carregando);

        spinnerOrigem =(Spinner) findViewById(R.id.lista_estados);
        new CarregaSpinnerOrigem().execute(CategoriasDb.ORIGEM);
        spinnerOrigem.setOnItemSelectedListener(new OrigemSelecionada());

        spinnerDestino =(Spinner) findViewById(R.id.lista_estados2);
        new CarregaSpinnerDestino().execute(CategoriasDb.DESTINO);
        spinnerDestino.setOnItemSelectedListener(new DestinoSelecionado());

        mProgress.setVisibility(View.INVISIBLE);
    }

    private class OrigemSelecionada implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            origem = (String) parent.getItemAtPosition(position);
        }

        public void onNothingSelected(AdapterView<?> parent){

        }
    }

    private class DestinoSelecionado implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            destino = (String) parent.getItemAtPosition(position);
        }

        public void onNothingSelected(AdapterView<?> parent){

        }
    }

   // public final static String ORIGEM = "com.example.arthur.ORIGEM";
    //public final static String DESTINO = "com.example.arthur.DESTINO";

    public final static String VOOS = "com.example.arthur.Voo";

    public void startListaDeVoos(View view)
    {
        final String pOrigem = this.origem.equals("Escolha a origem")?"":origem;
        final String pDestino = this.destino.equals("Escolha o destino")?"":destino;

       requester = new VooRequester();
        if(requester.isConnected(this)){
            intent = new Intent(this, ListaDeVoos.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        voos = requester.get("http://" + servidor + "/consultarVoo.json", pOrigem, pDestino);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(VOOS, voos);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private class CarregaSpinnerOrigem extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            CategoriasDb db = new CategoriasDb(contexto);
            ArrayList<String> lista = db.selecionaOrigem();
            if (lista.size() == 1)
                db.insereOrigem();
            lista = db.selecionaOrigem();
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            ArrayAdapter<String> estiloAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerOrigem.setAdapter(estiloAdapter);
        }
    }

        private class CarregaSpinnerDestino extends AsyncTask<String, Void, ArrayList<String>> {

            @Override
            protected ArrayList<String> doInBackground(String... params) {
                CategoriasDb db = new CategoriasDb(contexto);
                ArrayList<String> lista = db.selecionaDestino();
                if(lista.size() == 1)
                    db.insereDestino();
                lista = db.selecionaDestino();
                return lista;
            }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            ArrayAdapter<String> estiloAdapter = new ArrayAdapter<String>(contexto,
                    android.R.layout.simple_spinner_item, result);
            spinnerDestino.setAdapter(estiloAdapter);
        }
    }



    protected void onStart() {

        super.onStart();
    }

    protected void onRestart()
    {
        super.onRestart();
        spinnerOrigem.setSelection(0);
        spinnerDestino.setSelection(0);

    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onStop()
    {
        super.onStop();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    public void finishListarVoo(View v)
    {
        ListarVoo.this.finish();
    }
}
