package com.example.francisco.tfsservice;

import android.test.InstrumentationTestCase;

import com.example.francisco.controller.ControllerTFS;
import com.example.francisco.model.TFS;
import com.example.francisco.service.TFSWebApiConsumer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Francisco on 03/04/2016.
 */
public class GsonTest extends InstrumentationTestCase{

        public void testeMapeamentoModel() throws Exception {
            String teste = "        {"
            + "                \"Id\": \"1\","
            + "                \"Titulo\":\"Teste\","
            + "                \"Descricao\":\"\","
            + "                \"Responsavel\":\"Francisco\","
            + "                \"Status\":\"Done\","
            + "                \"Pontuacao\":\"13\","
            + "                \"Sprint\":\"Sprint 149\","
            + "                \"CssClass\":\"backlog-item-colors\""
            + " } ";
            Gson gson = new Gson();
            TFS tfs = gson.fromJson(teste, TFS.class);

            assertEquals(1, tfs.getId() );
            assertEquals("Teste", tfs.getTitulo().toString() );
            assertEquals("", tfs.getDescricao().toString() );
            assertEquals("Francisco", tfs.getResponsavel().toString());
            assertEquals("Done", tfs.getStatus().toString() );
            assertEquals(13, tfs.getPontuacao());
            assertEquals("Sprint 149", tfs.getSprint().toString() );
            assertEquals("backlog-item-colors", tfs.getCssClass().toString() );
        }

    public void testeMapeamentoListModel() throws Exception {
        String teste = " [ "
                + "        {"
                + "                \"Id\": \"1\","
                + "                \"Titulo\":\"Teste\","
                + "                \"Descricao\":\"\","
                + "                \"Responsavel\":\"Francisco\","
                + "                \"Status\":\"Done\","
                + "                \"Pontuacao\":\"13\","
                + "                \"Sprint\":\"Sprint 149\","
                + "                \"CssClass\":\"backlog-item-colors\""
                + " }, "
                + "        {"
                + "                \"Id\": \"2\","
                + "                \"Titulo\":\"Teste2\","
                + "                \"Descricao\":\"\","
                + "                \"Responsavel\":\"Lindemberg\","
                + "                \"Status\":\"Done\","
                + "                \"Pontuacao\":\"21\","
                + "                \"Sprint\":\"Sprint 149\","
                + "                \"CssClass\":\"backlog-item-colors\""
                + " } "
                + " ] ";
        Type type = new TypeToken<List<TFS>>() {}.getType();
        Gson gson = new Gson();
        List<TFS> listTfs = gson.fromJson(teste, type);

        assertEquals(2, listTfs.size() );
    }

    public void testeMapeamento() throws Exception {
        String URL = "http://tec-soft.servehttp.com:8889/tfs";
        String resposta = "";
        try {
            resposta = new TFSWebApiConsumer().getTFS(URL, "149");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        List<TFS> listaTFS = new ControllerTFS().retornarObjeto(resposta);
        assertNotNull(listaTFS);
    }
}
