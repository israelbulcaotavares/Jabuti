package com.example.mechatronicse.jabuti2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mechatronicse.jabuti2.R;


public class ContratoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato);



        Button botao_entrar_cadastro= (Button) findViewById(R.id.botao_entrar_cadastro);
        botao_entrar_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirCadastro();
            }
        });

    }


    private  void abrirCadastro(){

        Intent intent = new Intent(ContratoActivity.this, TelaCadastroActivity.class);
        startActivity(intent);
    }



}
