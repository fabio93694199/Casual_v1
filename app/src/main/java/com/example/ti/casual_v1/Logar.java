package com.example.ti.casual_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Logar extends ClasseMaeCadastroLogin {

    private Usuario usuario ;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        firebase = ClassePadrao.getFirebase();
        initViews();
        verificaUsuarioLogado();
    }

    @Override
    protected void initViews() {
        email = (AutoCompleteTextView) findViewById(R.id.IDemailLogin);
        senha = (EditText) findViewById(R.id.IDsenhaLogin);
        progresso=(ProgressBar) findViewById(R.id.IDprogressologin);
    }

    @Override
    protected void initUsuario() {
        usuario = new Usuario();
        usuario.setEmail(email.getText().toString().trim());
        usuario.setSenha(senha.getText().toString().trim());
        //usuario.gerarCryptSenha();
    }

    public void chamaCadastro(View view){
        Intent intent = new Intent(this, Cadastrar.class);
        startActivity(intent);
        finish();
    }

    public void enviarDadosLogin(View view){
        iniciaProgresso();
        initUsuario();
        verificaLogin();
    }

    private void verificaUsuarioLogado(){
        if (firebase.getAuth()!=null){
            chamarActivityPrincipal();
        }else {
            initUsuario();
            if(!usuario.getTokenSP(this).isEmpty()){
                firebase.authWithPassword(
                        "password",
                        usuario.getTokenSP(this),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                usuario.saveTokenSP(Logar.this,authData.getToken());
                                chamarActivityPrincipal();
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {}
                        }
                );
            }
        }
    }

    private void verificaLogin(){
        firebase.authWithPassword(
                usuario.getEmail(),
                usuario.getSenha(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        usuario.saveTokenSP(Logar.this,authData.getToken());
                        fechaProgresso();
                        chamarActivityPrincipal();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        showSnackBar(firebaseError.getMessage());
                        fechaProgresso();
                    }
                }
        );
    }

    private void chamarActivityPrincipal(){
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
        finish();
    }
}
