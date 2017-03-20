package com.example.fabiosalazar.projetoandroidfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditarActivity extends AppCompatActivity {

    DatabaseHelper helper;
    List<DatabaseModel> dbList;
    int position;
    TextView editarNome;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");

        editarNome =(TextView)findViewById(R.id.editarNome);

        helper = new DatabaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helper.getDataFromDB();

        if(dbList.size()>0){
            String name= dbList.get(position).getName();
            editarNome.setText(name);
        }

        Toast.makeText(EditarActivity.this, dbList.toString(), Toast.LENGTH_LONG);

        btnEditar = (Button) findViewById(R.id.btnEditar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(editarNome.getText().toString());
            }
        });
    }

    private void update(String novoNome){
        DatabaseHelper db = new DatabaseHelper(this);
        db.openDB();
        long result = db.Editar(novoNome);

        if(result > 0){
            editarNome.setText(novoNome);
            Toast.makeText(EditarActivity.this, "Editado com sucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(EditarActivity.this, "Não foi possível editar o nome!", Toast.LENGTH_LONG).show();
        }

        db.close();

    }

}

















