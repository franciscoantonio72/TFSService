package com.example.francisco.tfsservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.francisco.model.TFS;

import java.util.ArrayList;
import java.util.List;

public class SelecaoActivity extends AppCompatActivity implements OnClickListener {
    List<TFS> listaTFS = null;
    List<TFS> listaTFSporStatus = null;
    Button btnAguardando;
    Button btnEmDesenvolvimento;
    Button btnEmRevisao;
    Button btnEmTeste;
    Button btnEmAceitacao;
    Button btnConcluido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        listaTFS = getIntent().getParcelableArrayListExtra("TFS");

        btnAguardando = (Button) findViewById(R.id.btnAguardando);
        btnAguardando.setOnClickListener(this);
        btnEmDesenvolvimento = (Button) findViewById(R.id.btnEmDesenvolvimento);
        btnEmDesenvolvimento.setOnClickListener(this);
        btnEmRevisao = (Button) findViewById(R.id.btnEmRevisao);
        btnEmRevisao.setOnClickListener(this);
        btnEmTeste = (Button) findViewById(R.id.btnEmTeste);
        btnEmTeste.setOnClickListener(this);
        btnEmAceitacao = (Button) findViewById(R.id.btnEmAceitacao);
        btnEmAceitacao.setOnClickListener(this);
        btnConcluido = (Button) findViewById(R.id.btnConcluido);
        btnConcluido.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ListaItens.class);
        switch (v.getId()) {
            case R.id.btnAguardando :
                listaTFSporStatus = new ArrayList<TFS>();
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("New") && (item.getResponsavel().equals("Aguardando"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmDesenvolvimento:
                listaTFSporStatus = new ArrayList<TFS>();
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("New") && (!item.getResponsavel().equals("Aguardando"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmRevisao:
                listaTFSporStatus = new ArrayList<TFS>();
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Committed") && (item.getResponsavel().equals("Revisao_Codigo"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmTeste:
                listaTFSporStatus = new ArrayList<TFS>();
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Committed") && (!item.getResponsavel().equals("Revisao_Codigo"))){
                        listaTFSporStatus.add(item);
                    }
                }
                break;
            case R.id.btnEmAceitacao:
                listaTFSporStatus = new ArrayList<TFS>();
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Approved") && (item.getResponsavel().equals("Aceitação"))){
                        listaTFSporStatus.add(item);
                    }
                }
                break;
            case R.id.btnConcluido:
                listaTFSporStatus = new ArrayList<TFS>();
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Done")){
                        listaTFSporStatus.add(item);
                    }
                }
                break;
            default:
                break;
        }
    }
}
