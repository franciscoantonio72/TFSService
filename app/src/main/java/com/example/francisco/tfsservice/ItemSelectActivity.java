package com.example.francisco.tfsservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.example.francisco.controller.ControllerTFS;
import com.example.francisco.model.TFS;
import com.example.francisco.service.TFSWebApiConsumer;

import java.util.ArrayList;
import java.util.List;

public class ItemSelectActivity extends AppCompatActivity {
    TextView txtId;
    TextView txtTitulo;
    TextView txtDescricao;
    TextView txtResponsavel;
    TextView txtStatus;
    TextView txtPontuacao;
    private ProgressDialog mProgressDialog;
    private static String URL = "http://tec-soft.servehttp.com:8889/api/tfs/";
    private TFS tfsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select);

        AdicionaIconeBarra();

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        try {
            new ConsultarTFS().execute(String.valueOf(id));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void AdicionaIconeBarra() {
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        actionBar.show();
    }

    private class ConsultarTFS extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            createDialog();
        }

        @Override
        protected String doInBackground(String... params) {
            String sprint = params[0];
            String resposta = "";
            try {
                resposta = new TFSWebApiConsumer().postTFSById(URL, sprint);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return resposta;
        }

        @Override
        protected void onPostExecute(String resposta) {
            super.onPostExecute(resposta);
            dismissDialog();

            tfsItem = new ControllerTFS().retornarObjetoTfs(resposta);
            objectToView(tfsItem);
        }
    }

    private void objectToView(TFS tfsItem) {
        txtId = (TextView) findViewById(R.id.txtId);
        txtId.setText("Id: " + String.valueOf(tfsItem.getId()));
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(tfsItem.getTitulo());
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        String descricao = tfsItem.getDescricao().toString();
        Spanned htmlDescricao = Html.fromHtml(descricao);
        txtDescricao.setText(htmlDescricao);
        txtResponsavel = (TextView) findViewById(R.id.txtResponsavel);
        txtResponsavel.setText("Responsavel: " + tfsItem.getResponsavel());
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtStatus.setText("Status: " + tfsItem.getStatus());
        txtPontuacao = (TextView) findViewById(R.id.txtPontuacao);
        txtPontuacao.setText("Pontuação: " + String.valueOf(tfsItem.getPontuacao()));
    }

    private void createDialog(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    private void dismissDialog() {
        mProgressDialog.dismiss();
    }

}
