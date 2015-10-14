package com.example.arthur.project.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.example.arthur.project.model.Voo;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;

/**
 * Created by Arthur on 20/09/2015.
 */
public class VooRequester {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Voo> get(String url, String pOrigem,String pDestino) throws IOException {

        ArrayList<Voo> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .add("origem", pOrigem)
                .add("destino", pDestino)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject)root.get(i);

                int cv = item.getInt("codVoo");
                String origem = item.getString("origem");
                String destino = item.getString("destino");
                double valor = item.getDouble("valor");
                String sdata = item.getString("data");
                String hora = item.getString("hora");
                String situacao = item.getString("situacao");
                int escala = item.getInt("escala");
                int ca = item.getInt("codAeronave");
                String imagem = item.getString("imagem");
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date data = null;
                try {
                    data = (Date)formatter.parse(sdata);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                lista.add(new Voo(cv,origem, destino, valor, data, hora, situacao, escala, ca, imagem));
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Voo());
        }
        return lista;
    }
    public Bitmap getImage(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}