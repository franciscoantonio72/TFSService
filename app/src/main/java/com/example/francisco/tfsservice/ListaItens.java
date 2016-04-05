package com.example.francisco.tfsservice;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.francisco.model.TFS;

import java.util.List;

public class ListaItens extends ListActivity {
    private ListaItensAdapter tfsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        List<TFS> lista = getIntent().getParcelableArrayListExtra("listaTFS");

        tfsAdapter = new ListaItensAdapter(this, lista);
        setListAdapter(tfsAdapter);
    }
}
