package com.example.arthur.project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalheVooActivity extends AppCompatActivity {

    TextView codVoo;
    ImageView vooImageView;
    TextView vooPreco;
    TextView origem;
    TextView destino;
    TextView data;
    TextView hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_voo);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(ListaDeVoos.VOO);
        Voo voo = (Voo)obj;
        setupViews(voo);

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
