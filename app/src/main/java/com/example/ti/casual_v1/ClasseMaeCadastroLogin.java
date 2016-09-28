package com.example.ti.casual_v1;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by ti on 28/09/16.
 */

public abstract class ClasseMaeCadastroLogin extends AppCompatActivity {

    protected AutoCompleteTextView email;
    protected EditText senha;
    protected ProgressBar progresso;

    protected void showSnackBar(String mensagem){
        Snackbar.make(progresso,mensagem,Snackbar.LENGTH_LONG).setAction("Action",null).show();
    }

    protected void showToast(String mensagem){
        Toast.makeText(this,mensagem,Toast.LENGTH_LONG).show();
    }

    protected void iniciaProgresso(){
        progresso.setVisibility(View.VISIBLE);
    }

    protected void fechaProgresso(){
        progresso.setVisibility(View.GONE);
    }

    abstract protected void initViews();

    abstract protected void initUsuario();
}
