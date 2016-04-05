package com.example.francisco.controller;

import android.util.JsonReader;

import com.example.francisco.model.TFS;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Francisco on 03/04/2016.
 */
public class ControllerTFS {

    public List<TFS> retornarObjeto(String json) {
        List<TFS> listTfs = null;
        try {
            Type type = new TypeToken<List<TFS>>() {
            }.getType();
            Gson gson = new Gson();
            listTfs = gson.fromJson(json.trim(), type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTfs;
    }
}
