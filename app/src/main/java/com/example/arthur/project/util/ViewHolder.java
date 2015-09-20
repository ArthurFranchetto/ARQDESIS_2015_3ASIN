package com.example.arthur.project.util; /**
 * Created by Arthur on 13/09/2015.
 */
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Arthur on 9/7/15.
 */
public class ViewHolder {
    private ImageView fotinhoAviao;
    private TextView origem, destino, data;

    public ViewHolder(ImageView fotinhoAviao, TextView origem, TextView destino, TextView data) {
        this.fotinhoAviao = fotinhoAviao;
        this.origem = origem;
        this.destino = destino;
    }

    public ImageView getFotinhoAviao() {
        return fotinhoAviao;
    }

    public void setFotinhoAviao(ImageView fotinhoAviao) {
        this.fotinhoAviao = fotinhoAviao;
    }

    public TextView getOrigem() {
        return origem;
    }

    public void setOrigem(TextView origem) {
        this.origem = origem;
    }

    public TextView getDestino() {
        return destino;
    }

    public void setDestino(TextView destino) {
        this.destino = destino;
    }

    public TextView getData() {
        return data;
    }

    public void setData(TextView data) {
        this.data = data;
    }
}
