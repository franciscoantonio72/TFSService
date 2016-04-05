package com.example.francisco.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * Created by Francisco on 02/04/2016.
 */
@JsonDeserialize()
public class TFS implements Parcelable {
    public TFS() {

    }

    int Id;
    String Titulo;
    String Descricao;
    String Responsavel;
    String Status;
    int Pontuacao;
    String Sprint;
    String CssClass;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String responsavel) {
        Responsavel = responsavel;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getPontuacao() {
        return Pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        Pontuacao = pontuacao;
    }

    public String getSprint() {
        return Sprint;
    }

    public void setSprint(String sprint) {
        Sprint = sprint;
    }

    public String getCssClass() {
        return CssClass;
    }

    public void setCssClass(String cssClass) {
        CssClass = cssClass;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Titulo);
        dest.writeString(Descricao);
        dest.writeString(Responsavel);
        dest.writeString(Status);
        dest.writeInt(Pontuacao);
        dest.writeString(Sprint);
        dest.writeString(CssClass);
    }

    private TFS(Parcel in){
        this.Id = in.readInt();
        this.Titulo = in.readString();
        this.Descricao = in.readString();
        this.Responsavel = in.readString();
        this.Status = in.readString();
        this.Pontuacao = in.readInt();
        this.Sprint = in.readString();
        this.CssClass = in.readString();
    }

    public static final Parcelable.Creator<TFS> CREATOR = new Parcelable.Creator<TFS>() {

        @Override
        public TFS createFromParcel(Parcel source) {
            return new TFS(source);
        }

        @Override
        public TFS[] newArray(int size) {
            return new TFS[size];
        }
    };
}
