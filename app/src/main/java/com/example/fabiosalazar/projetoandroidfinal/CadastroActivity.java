package com.example.fabiosalazar.projetoandroidfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    EditText etName;
    Button btnSubmit,btngetdata;
    DatabaseHelper helper;
    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbList= new ArrayList<DatabaseModel>();
        etName = (EditText)findViewById(R.id.etName);

        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btngetdata =(Button)findViewById(R.id.btngetdata);

        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, DadosActivity.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();

                if(name.equals("")){
                    Toast.makeText(CadastroActivity.this,"Por favor, informe um nome!",Toast.LENGTH_LONG).show();
                } else {
                    helper = new DatabaseHelper(CadastroActivity.this);
                    helper.insertIntoDB(name);
                    Toast.makeText(CadastroActivity.this,"Registro inserido com sucesso!",Toast.LENGTH_LONG).show();
                }

                etName.setText("");

                Toast.makeText(CadastroActivity.this, "Insira um valor!", Toast.LENGTH_LONG);

            }
        });

    }
}
