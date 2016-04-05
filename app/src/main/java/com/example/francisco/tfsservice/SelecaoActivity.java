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
    Button btnAguardando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        listaTFS = getIntent().getParcelableArrayListExtra("TFS");

        btnAguardando = (Button) findViewById(R.id.btnAguardando);
        btnAguardando.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAguardando :
                Intent intent = new Intent(this, ListaItens.class);
                intent.putParcelableArrayListExtra("listaTFS", (ArrayList<? extends Parcelable>) listaTFS);
                startActivity(intent);
                break;
            case R.id.btnEmDesenvolvimento:
                break;
            case R.id.btnEmTeste:
                break;
            case R.id.btnEmAceitacao:
                break;
            case R.id.btnConcluido:
                break;
            default:
                break;
        }
    }
}
