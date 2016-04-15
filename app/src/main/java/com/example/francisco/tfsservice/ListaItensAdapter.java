package com.example.francisco.tfsservice;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.francisco.model.TFS;

import java.util.List;

/**
 * Created by Francisco on 03/04/2016.
 */
public class ListaItensAdapter extends ArrayAdapter<TFS> {
    private Context context;
    private List<TFS> listaTfs = null;

    public ListaItensAdapter(Context context, List<TFS> tfs) {
        super(context, 0, tfs);
        this.context = context;
        this.listaTfs = tfs;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TFS tfs = listaTfs.get(position);
        ViewHolder viewHolder = null;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lista_tfs, null);

            viewHolder = new ViewHolder();
            viewHolder.txtId = (TextView) view.findViewById(R.id.text_view_id);
            viewHolder.txtTitle = (TextView) view.findViewById(R.id.text_view_title);
            viewHolder.txtResponsavel = (TextView) view.findViewById(R.id.text_view_responsavel);
            viewHolder.txtPontos = (TextView) view.findViewById(R.id.text_view_pontos);
            viewHolder.layout = (LinearLayout) view.findViewById(R.id.layoutItem);
            viewHolder.barraLateral = (LinearLayout) view.findViewById(R.id.barraLateral);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.txtId.setText(Integer.toString(tfs.getId()));
        viewHolder.txtTitle.setText(tfs.getTitulo());
        viewHolder.txtResponsavel.setText(tfs.getResponsavel());
        viewHolder.txtPontos.setText(Integer.toString(tfs.getPontuacao()));

        //if (tfs.getCssClass().equals("backlog-item-colors")) {
        if (!tfs.getTitulo().contains("[NP]")) {
            viewHolder.barraLateral.setBackgroundColor(Color.rgb(0, 156, 204));
            viewHolder.layout.setBackgroundColor(Color.rgb(214, 236, 242));
        } else {
            viewHolder.barraLateral.setBackgroundColor(Color.rgb(204, 41, 61));
            viewHolder.layout.setBackgroundColor(Color.rgb(250, 234, 229));
        }
        return view;
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtTitle;
        TextView txtResponsavel;
        TextView txtPontos;
        LinearLayout layout;
        LinearLayout barraLateral;
    }
}
