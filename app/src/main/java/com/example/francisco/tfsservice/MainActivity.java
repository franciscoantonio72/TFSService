package com.example.francisco.tfsservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.francisco.controller.ControllerTFS;
import com.example.francisco.model.TFS;
import com.example.francisco.service.TFSWebApiConsumer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtSprint;
    private Button btnConsultar;
    private ProgressDialog mProgressDialog;
    private static String URL = "http://tec-soft.servehttp.com:8889/tfs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSprint = (EditText) findViewById(R.id.edtSprint);
        btnConsultar = (Button) findViewById(R.id.btnConectar);
        btnConsultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isValidFields())
            new ConsultarTFS().execute(edtSprint.getText().toString());
        else
            Toast.makeText(this, "Sprint em branco", Toast.LENGTH_LONG).show();
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
                resposta = new TFSWebApiConsumer().getTFS(URL, sprint);
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

            List<TFS> listaTFS = new ControllerTFS().retornarObjeto(resposta);
            closeActivityAndGoToSelecao(listaTFS);
        }
    }

    private void closeActivityAndGoToSelecao(List<TFS> listaTfs){
        Intent intent = new Intent(this, SelecaoActivity.class);
        intent.putParcelableArrayListExtra("TFS", (ArrayList<? extends Parcelable>) listaTfs);
        startActivity(intent);
        this.finish();
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

    private boolean isValidFields() {
        return !edtSprint.getText().toString().equals("");
    }

}
