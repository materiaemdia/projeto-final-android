package com.example.fabiosalazar.projetoandroidfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkConnected();

        EditText usuario = (EditText) findViewById(R.id.edtUsuario);
        EditText senha = (EditText) findViewById(R.id.edtSenha);

        String user = "android";
        String password = "mobile";

        usuario.setText(user);
        senha.setText(password);
    }

    public void logar(View v){

        String usuario = ((EditText) findViewById(R.id.edtUsuario)).getText().toString();
        String senha = ((EditText) findViewById(R.id.edtSenha)).getText().toString();

        CheckBox cbManterLogado = (CheckBox) findViewById(R.id.cbManterLogado);

        if(usuario.equals("android") && senha.equals("mobile")){
            salvarPreferencias(usuario, cbManterLogado.isChecked());
            abrirTela();
        } else {
            Toast.makeText(getApplicationContext(), "Usuário e/ou senha não existem!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkConnected(){
        SharedPreferences settings = getSharedPreferences("PREFERENCIAS", MODE_PRIVATE);

        if(settings.getBoolean("manterLogado", false)){
            abrirTela();
        }
    }

    private void abrirTela(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void salvarPreferencias (String usuario, boolean isConnected){
        SharedPreferences settings = getSharedPreferences("PREFERENCIAS", MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario", usuario);
        editor.putBoolean("manterLogado", isConnected);
        editor.commit();
    }
}
