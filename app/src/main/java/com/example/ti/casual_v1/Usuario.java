package com.example.ti.casual_v1;

import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by ti on 28/09/16.
 */

public class Usuario {

    public static String TOKEN = "com.example.ti.casual_v1.Usuario.TOKEN";

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario (){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
/*
    public void gerarCryptSenha(){
        senha = CryptWithMD5.cryptWithMD5(senha);
    }
*/
    public String getTokenSP(Context context){
        String token = ClassePadrao.getSP(context,TOKEN);
        return (token);
    }

    public void saveTokenSP(Context context,String token){
        ClassePadrao.saveSP(context,TOKEN,token);
    }

    public void saveDB (){
        Firebase firebase = ClassePadrao.getFirebase();
        firebase.child("usuarios").child(getId());
        setId(null);
        setSenha(null);
        firebase.setValue(this);
    }
}
