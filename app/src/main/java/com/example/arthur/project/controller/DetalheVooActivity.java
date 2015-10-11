package com.example.arthur.project.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthur.project.R;
import com.example.arthur.project.network.VooRequester;
import com.example.arthur.project.util.Util;
import com.example.arthur.project.model.Voo;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DetalheVooActivity extends ActionBarActivity {

    TextView codVoo;
    ImageView vooImageView;
    TextView vooPreco;
    TextView origem;
    TextView destino;
    TextView data;
    TextView hora;
    VooRequester requester;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_voo);

        Intent intent = getIntent();
        final Voo voo = (Voo)intent.getSerializableExtra(ListarVoo.VOOS);
        setupViews(voo);

        if(!voo.getOrigem().equals(Voo.NAO_ENCONTRADO)) {
            requester = new VooRequester();
            if (requester.isConnected(this)) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mProgress.setVisibility(View.VISIBLE);
                            final Bitmap img = requester.getImage(voo.getImagem());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    vooImageView.setImageBitmap(img);
                                    mProgress.setVisibility(View.INVISIBLE);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Resources res = getResources();
                Drawable drawable = res.getDrawable(R.drawable.voo_np);
                vooImageView.setImageDrawable(drawable);
                Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.voo_np);
            vooImageView.setImageDrawable(drawable);
        }

    }


    private void setupViews(Voo voo) {
        codVoo = (TextView) findViewById(R.id.txt_voo_nome);
        codVoo.setText(voo.getCodVoo());
        vooImageView = (ImageView) findViewById(R.id.voo_image_view);
        Drawable drawable = Util.getDrawable(this, voo.getImagem());
        vooImageView.setImageDrawable(drawable);
        vooPreco = (TextView) findViewById(R.id.txt_voo_preco);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        vooPreco.setText(""+formatter.format(voo.getValor()));
        origem = (TextView) findViewById(R.id.txt_origem);
        origem.setText(voo.getOrigem());
        destino = (TextView) findViewById(R.id.destino);
        destino.setText(voo.getDestino());
        data = (TextView) findViewById(R.id.txt_data);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(voo.getData());
        data.setText(formattedDate);
        hora = (TextView) findViewById(R.id.txt_hora);
        hora.setText(voo.getHora());
    }

}