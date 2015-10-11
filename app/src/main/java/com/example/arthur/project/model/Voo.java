package com.example.arthur.project.model;

/**
 * Created by Arthur on 13/09/2015.
 */
import java.io.Serializable;
import java.util.*;

public class Voo implements Comparable<Voo>, Serializable {
    private String origem,destino,situacao, hora, imagem;
    private int codVoo, codAeronave, escala;
    private double valor;
    private Date data;
    public static final String NAO_ENCONTRADO = "Não encontrado.";


    public Voo()
    {

    }

    public Voo(int cv, String orig,String dest,double vl, Date dat, String hora, String sit, int es, int ca, String imagem )
    {
        this.imagem = imagem;
        setCodVoo(cv);
        setOrigem(orig);
        setDestino(dest);
        setValor(vl);
        setData(dat);
        setHora(hora);
        setSituacao(sit);
        setEscala(es);
        setCodAeronave(ca);
    }

    public String getImagem()
    {
        return imagem;
    }

    public void setDestino(String dest)
    {
        destino = dest;
    }

    public void setCodVoo(int cd)
    {
        codVoo = cd;
    }

    public void setOrigem(String orig)
    {
        origem = orig;
    }

    public void setSituacao(String sit)
    {
        situacao = sit;
    }

    public void setValor(double vl)
    {
        valor = vl;
    }
    public void setData(Date d)
    {
        data = d;
    }

    public void setHora (String h)
    {
        hora = h;
    }

    public void setCodAeronave (int ca)
    {
        codAeronave = ca;
    }

    public void setEscala (int es)
    {
        escala = es;
    }

    public String getDestino()
    {
        return destino;
    }

    public double getValor()
    {
        return valor;
    }
    public int getCodVoo()
    {
        return codVoo;
    }

    public String getOrigem()
    {
        return origem;
    }

    public int getCodAeronave()
    {
        return codAeronave;
    }

    public String getSituacao()
    {
        return situacao;
    }

    public String getHora()
    {
        return hora;
    }

    public Date getData()
    {
        return data;
    }

    public int getEscala ()
    {
        return escala;
    }




    public String toString(){
        return "com.example.arthur.project.Voo{" +
                "codVoo='" + codVoo + '\'' +
                ", imagem='" + imagem + '\'' +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", valor='" + valor + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", situacao='" + situacao + '\'' +
                ", escala='" + escala + '\'' +
                ", codAeronave='" + codAeronave + '\'' +
                '}';
    }


    public int compareTo(Voo voo) {
        if (codVoo == (getCodVoo())
                && data.equals(voo.getData())
                && hora == (voo.getHora())
                && valor == (voo.getValor())
                && origem.equals(voo.getOrigem())
                && destino.equals(voo.getDestino())
                && situacao.equals(voo.getSituacao())
                && codAeronave == (voo.getCodAeronave())
                && escala == (voo.getEscala())) {
            return 0;
        }

        return this.getOrigem().compareTo(voo.getOrigem());
    }


}//Fim da Classe