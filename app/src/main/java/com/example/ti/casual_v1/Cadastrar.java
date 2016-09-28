package com.example.ti.casual_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class Cadastrar extends ClasseMaeCadastroLogin {

    private AutoCompleteTextView nome;
    private Firebase firebase;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebase = ClassePadrao.getFirebase();
        initViews();
    }

    @Override
    protected void initViews() {
        nome  = (AutoCompleteTextView) findViewById(R.id.IDnomeCadastro);
        email = (AutoCompleteTextView) findViewById(R.id.IDemailCadastro);
        senha = (EditText) findViewById(R.id.IDsenhaCadastro);
        progresso = (ProgressBar) findViewById(R.id.IDprogressoCadastro);
    }

    @Override
    protected void initUsuario() {
        usuario = new Usuario();
        usuario.setNome(nome.getText().toString().trim());
        usuario.setEmail(email.getText().toString().trim());
        usuario.setSenha(senha.getText().toString().trim());
        //usuario.gerarCryptSenha();
    }

    public void enviarDadosCadastro(View view){
        iniciaProgresso();
        initUsuario();
        salvarUsuario();
    }

    private void salvarUsuario(){
        firebase.createUser(
                usuario.getEmail(),
                usuario.getSenha(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        usuario.setId(stringObjectMap.get("uid").toString());
                        usuario.saveDB();
                        firebase.unauth();
                        showToast("Usuario Criado com Sucesso !!!");
                        fechaProgresso();
                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        showSnackBar(firebaseError.getMessage());
                        fechaProgresso();
                    }
                }
        );
    }
}
