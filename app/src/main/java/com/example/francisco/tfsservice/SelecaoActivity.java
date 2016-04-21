package com.example.francisco.tfsservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
    Button btnBuscar;
    EditText edtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        AdicionaIconeBarra();

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
        edtBuscar = (EditText) findViewById(R.id.edtTFSOcomon);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ListaItens.class);
        listaTFSporStatus = new ArrayList<TFS>();
        switch (v.getId()) {
            case R.id.btnBuscar:
                Intent intentDetalhe = new Intent(this, ItemSelectActivity.class);
                intentDetalhe.putExtra("id", Integer.parseInt(edtBuscar.getText().toString()));
                startActivity(intentDetalhe);
                break;
            case R.id.btnAguardando :
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("New") && (item.getResponsavel().equals("Aguardando"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmDesenvolvimento:
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("New") && (!item.getResponsavel().equals("Aguardando"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmRevisao:
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Committed") && (item.getResponsavel().equals("Revisao_Codigo"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmTeste:
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Committed") && (!item.getResponsavel().equals("Revisao_Codigo"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnEmAceitacao:
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Approved") && (item.getResponsavel().equals("Aceitação"))){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            case R.id.btnConcluido:
                for (TFS item: listaTFS) {
                    if (item.getStatus().equals("Done")){
                        listaTFSporStatus.add(item);
                    }
                }
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFSporStatus);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void AdicionaIconeBarra() {
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        actionBar.show();
    }
}
