package com.example.arthur.project.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.arthur.project.R;
import com.example.arthur.project.model.Voo;

import java.util.ArrayList;

public class ListarVoo extends AppCompatActivity {

    Spinner spinnerOrigem, spinnerDestino;
    String destino, origem;
    Button btnConsultar;
    ArrayList<Voo> voos;
//    final String servidor =


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_voo);
        setupViews();
    }

    private void setupViews(){
        destino = "";
        origem = "";
        btnConsultar = (Button) findViewById(R.id.btn_pesquisar);
        spinnerOrigem =(Spinner) findViewById(R.id.lista_estados);
        spinnerOrigem.setOnItemSelectedListener(new OrigemSelecionada());
        spinnerDestino =(Spinner) findViewById(R.id.lista_estados2);
        spinnerDestino.setOnItemSelectedListener(new DestinoSelecionado());
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

    public final static String ORIGEM = "com.example.arthur.ORIGEM";
    public final static String DESTINO = "com.example.arthur.DESTINO";

    public void startListaDeVoos(View view)
    {
        String pOrigem = this.origem.equals("Escolha a origem")?"":origem;
        String pDestino = this.destino.equals("Escolha o destino")?"":destino;

        Intent intent = new Intent (this, ListaDeVoos.class);
        intent.putExtra(ORIGEM, pOrigem);
        intent.putExtra(DESTINO, pDestino);
        startActivity(intent);
    }



    protected void onStart() {

        super.onStart();
    }

    protected void onRestart()
    {
        super.onRestart();
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
