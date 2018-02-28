package com.example.mechatronicse.jabuti2.activities;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechatronicse.jabuti2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class TelaInicialActivity extends AppCompatActivity {

    private static final int NOTIFY_ID = 1;
    private static final String YES_ACTION = "YES";
    //private static final String MAYBE_ACTION = "AYBE_ACTION";
    private static final String NO_ACTION = "NO";


    private NotificationManager notificationManager;



    private FirebaseAuth mAuth;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        mAuth = FirebaseAuth.getInstance();


        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mostrarNotificacao();

        FloatingActionButton botao_deslogar = (FloatingActionButton) findViewById(R.id.botao_deslogar);
        botao_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // logoff();
                alertaBotoesDeslogar();

                /*FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TelaInicialActivity.this, MainActivity.class));
                finish();

                */



            }
        });




        ImageButton botao_assalto = (ImageButton) findViewById(R.id.botao_assalto);
        botao_assalto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertaBotoes();
            }
        });



    }


    private Intent receberNotificacaoIntent() {
        Intent intent = new Intent(TelaInicialActivity.this, TelaInicialActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }



    @Override
    protected void onStart() {
        super.onStart();
        //  notification();
        mostrarNotificacao();

    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void mostrarNotificacao() {
        Intent yesIntent = receberNotificacaoIntent();
        yesIntent.setAction(YES_ACTION);

      //  Intent maybeIntent = receberNotificacaoIntent();
      //  maybeIntent.setAction(MAYBE_ACTION);

        Intent noIntent = receberNotificacaoIntent();
        noIntent.setAction(NO_ACTION);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentIntent(PendingIntent.getActivity(this, 0, receberNotificacaoIntent(), PendingIntent.FLAG_UPDATE_CURRENT))
                .setSmallIcon(R.mipmap.ic_launcher)
              //  .setTicker("------")
                .setContentTitle("JABUTI AQUI!")
                .setContentText("Escolha as opções rapido abaixo!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setOngoing(true)
                .addAction(new NotificationCompat.Action(
                        R.drawable.botao,
                        getString(R.string.enviar),
                        PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT)))

           /*    .addAction(new NotificationCompat.Action(
                        R.mipmap.ic_thumbs_up_down_black_36dp,
                        getString(R.string.opcional),
                        PendingIntent.getActivity(this, 0, maybeIntent, PendingIntent.FLAG_UPDATE_CURRENT)))  */

                .addAction(new NotificationCompat.Action(
                        R.mipmap.ic_thumb_down_black_36dp,
                        getString(R.string.deslogar),
                        PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT)))

                .build();

        notificationManager.notify(NOTIFY_ID, notification);

    }


    private void removerNotificacao() {
        NotificationManagerCompat nm = NotificationManagerCompat.from(TelaInicialActivity.this);

        nm.cancel(NOTIFY_ID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntentAction(intent);
        super.onNewIntent(intent);
    }

    private void processIntentAction(Intent intent) {
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case YES_ACTION:

                    alertaBotoes();

                  /*  TextView txtAviso = new TextView(TelaInicialActivity.this);
                    txtAviso.setText("Localizacao %d recebida | Bairro %d | Avenida %d | CEP XXXXX-XXXXX");

                    Ion.with(getBaseContext())
                            .load("http://192.168.0.14/chat/enviar_socorro.php")  //CONEXAO LOCAL
                            .setBodyParameter("bip", txtAviso.getText().toString())
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if (result.get("retorno").getAsString().equals("YES")){
                                        Toast.makeText(getBaseContext(), "ALERTA DE SOCORRO ENVIADO!", Toast.LENGTH_LONG).show();

                                    }else if (result.get("retorno").getAsString().equals("NO")) {
                                        Toast.makeText(getBaseContext(), "Não enviado! Wifi não conectado", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                        */

                    break;
            /*    case MAYBE_ACTION:

                    //ALGUMA IDEIA????
                    break;

                    */
                case NO_ACTION:

                    alertaBotoesDeslogar();
                    removerNotificacao();
                    break;
            }
        }
    }





    private void BotaoenviarAlerta(){

        ImageButton botao_assalto = (ImageButton) findViewById(R.id.botao_assalto);
        botao_assalto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                                //  TextView txtAviso = (TextView) findViewById(R.id.txtAviso);
                                TextView txtAviso = new TextView(TelaInicialActivity.this);
                                txtAviso.setText("Localizacao %d recebida | Bairro %d | Avenida %d | CEP XXXXX-XXXXX");

                                Ion.with(getBaseContext())
                                        .load("http://192.168.0.14/chat/enviar_socorro.php")  //CONEXAO LOCAL
                                        .setBodyParameter("bip", txtAviso.getText().toString())
                                        .asJsonObject()
                                        .setCallback(new FutureCallback<JsonObject>() {
                                            @Override
                                            public void onCompleted(Exception e, JsonObject result) {
                                                if (result.get("retorno").getAsString().equals("YES")){
                                                    Toast.makeText(getBaseContext(), "ALERTA DE SOCORRO ENVIADO!", Toast.LENGTH_LONG).show();

                                                }else if (result.get("retorno").getAsString().equals("NO")) {
                                                    Toast.makeText(getBaseContext(), "Não enviado! Wifi não conectado", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });





            }
        });

    }


    private void alertaBotoes(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaInicialActivity.this);


                 alertDialog.setTitle("                  AVISO!!!");

        alertDialog.setMessage("Tem certeza ?");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(TelaInicialActivity.this);



        alertDialog.setPositiveButton("Enviar Socorro!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                TextView txtAviso = (TextView) findViewById(R.id.txtAviso);


                Ion.with(getBaseContext())
                        .load("http://192.168.0.14/chat/enviar_socorro.php")  //CONEXAO LOCAL
                        .setBodyParameter("bip", txtAviso.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result.get("retorno").getAsString().equals("YES")){
                                    Toast.makeText(getBaseContext(), "ALERTA DE SOCORRO ENVIADO!", Toast.LENGTH_SHORT).show();

                                }else if (result.get("retorno").getAsString().equals("NO")) {
                                    Toast.makeText(getBaseContext(), "Não enviado! Wifi não conectado", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(TelaInicialActivity.this, "CANCELADO!!!", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.create();
        alertDialog.show();

    }



    private void enviarAlerta(){
        TextView txtAviso = (TextView) findViewById(R.id.txtAviso);


        Ion.with(getBaseContext())
                .load("http://192.168.0.14/chat/enviar_socorro.php")  //CONEXAO LOCAL
                .setBodyParameter("bip", txtAviso.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result.get("retorno").getAsString().equals("YES")){
                            Toast.makeText(getBaseContext(), "ALERTA DE SOCORRO ENVIADO!", Toast.LENGTH_SHORT).show();

                        }else if (result.get("retorno").getAsString().equals("NO")) {
                            Toast.makeText(getBaseContext(), "Não enviado! Wifi não conectado", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }






    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    mostrarNotificacao();
                    alertaBotoes(); // com o botão físicoo aumentar volume
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {

                    alertaBotoes();
                    // com o botao físico diminuir de VOLUME
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void logoff(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(TelaInicialActivity.this, MainActivity.class));
        removerNotificacao() ;
       finish();


    }


    private void alertaBotoesDeslogar(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaInicialActivity.this);


        alertDialog.setTitle("         Tem certeza?");


        alertDialog.setCancelable(false);

       // final EditText editText = new EditText(TelaInicialActivity.this);



        alertDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TelaInicialActivity.this, MainActivity.class));
                removerNotificacao() ;
                finish();

            }
        });

        alertDialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               //
            }
        });

        alertDialog.create();
        alertDialog.show();

    }


   /* public void notification(){
        Intent itStop = new Intent(this, TelaInicialActivity.class);
        itStop.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        itStop.putExtra(EXTRA_ACAO, ACAO_STOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(TelaInicialActivity.class);
        stackBuilder.addNextIntent(new Intent(this, TelaInicialActivity.class));

        TaskStackBuilder stackBuilder2 = TaskStackBuilder.create(this);
        stackBuilder2.addParentStack(TelaInicialActivity.class);
        stackBuilder2.addNextIntent(new Intent(this, TelaInicialActivity.class));


        //Intent  it = new Intent(getApplicationContext(), TelaInicialActivity.class);
        //  it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // it.putExtra("SAIR", true);

        // startActivity(it);

        PendingIntent pitStop = PendingIntent.getService(this, 3, itStop, 0);

        PendingIntent pitActivity = stackBuilder.getPendingIntent(3, PendingIntent.FLAG_UPDATE_CURRENT);


        RemoteViews views = new RemoteViews(getPackageName(), R.layout.layout_notificacao);

        views.setOnClickPendingIntent(R.id.imgBtnClose, pitStop);


        views.setOnClickPendingIntent(R.id.abrirTelaInicial, pitActivity);


        Notification n = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(views)
                .setOngoing(true)
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(1, n);


    }



    private void removerNotificacao() {
        NotificationManagerCompat nm = NotificationManagerCompat.from(this);

        nm.cancel(1);
    }


*/




}
