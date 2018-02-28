package com.example.mechatronicse.jabuti2.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechatronicse.jabuti2.R;
import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class TelaLoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private FirebaseAuth firebaseAuth;
    CallbackManager callbackManager;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        firebaseAuth = FirebaseAuth.getInstance();

        edtEmail = (EditText) findViewById(R.id.editText7);
        edtSenha = (EditText) findViewById(R.id.editText8);


        TextView txtRedSenha = (TextView) findViewById(R.id.textView4);
        txtRedSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaLoginActivity.this, RedefinirSenhaActivity.class));
            }
        });


        Button btnVoltar = (Button) findViewById(R.id.button5);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TelaLoginActivity.super.onBackPressed();
            }
        });


        Button botao_logar = (Button) findViewById(R.id.botao_logar);
        botao_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logar();
            }
        });



    }


    private void BotaoenviarAlerta(){

        ImageButton botao_assalto = (ImageButton) findViewById(R.id.botao_assalto);
        botao_assalto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtAviso = (TextView) findViewById(R.id.txtAviso);


                Ion.with(getBaseContext())
                        .load("http://192.168.0.14/chat/enviar_socorro.php")  //CONEXAO LOCAL
                        .setBodyParameter("bip", txtAviso.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result.get("retorno").getAsString().equals("YES")){
                                    Toast.makeText(getBaseContext(), "ALERTA DE SOCORRO ENVIADO!", Toast.LENGTH_LONG).show();

                                }else if (result.get("retorno").getAsString().equals("NO")){

                                    Toast.makeText(getBaseContext(), "ALERTA DE SOCORRO NAO  ENVIADO!", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });

    }


    private void logar(){

        if (!edtEmail.getText().toString().equals("")){
            if (!edtSenha.getText().toString().equals("")){
        firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(TelaLoginActivity.this, "Não foi possível!", Toast.LENGTH_SHORT).show();
                }else{
                    //   gerarNotificacao();

                    Toast.makeText(TelaLoginActivity.this, "Login efetudado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TelaLoginActivity.this, TelaInicialActivity.class));


                    finish();
                }
            }
        });

            }else{
                Toast.makeText(TelaLoginActivity.this, "Campo de senha vazio", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(TelaLoginActivity.this, "Preencha o campo do e-mail!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




  /*  public void gerarNotificacao(){
        Intent itStop = new Intent(this, TelaInicialActivity.class);
        itStop.putExtra(EXTRA_ACAO, ACAO_STOP);



        PendingIntent pitStop = PendingIntent.getService(this, 3, itStop, 0);


        RemoteViews views = new RemoteViews(getPackageName(), R.layout.layout_notificacao);

        views.setOnClickPendingIntent(R.id.imgBtnClose, pitStop);

        Notification n = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(views)
                .setOngoing(true) //NOTIFICAÇÃO FICA TRAVADA SEM CONSEGUIR DESLIZA-LA
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(1, n);


    }
    */


}
