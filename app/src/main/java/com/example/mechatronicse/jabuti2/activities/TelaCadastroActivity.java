package com.example.mechatronicse.jabuti2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mechatronicse.jabuti2.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaCadastroActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText edtNome, edtCPF, edtEmail, edtNomedaMae, edtSenha, edtConfSenha,edtDataNascimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();
        edtNome = (EditText) findViewById(R.id.editText);
        edtCPF = (EditText) findViewById(R.id.editText2);
        edtDataNascimento = (EditText) findViewById(R.id.editText19);
        edtEmail = (EditText) findViewById(R.id.editText3);
        edtNomedaMae = (EditText) findViewById(R.id.editText4);
        edtSenha = (EditText) findViewById(R.id.editText5);
        edtConfSenha = (EditText) findViewById(R.id.editText6);



        SimpleMaskFormatter simpleMaskCPF= new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskCPF= new MaskTextWatcher(edtCPF, simpleMaskCPF);
        edtCPF.addTextChangedListener( maskCPF );

        SimpleMaskFormatter simpleMaskDataNascimento= new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskDataNascimento= new MaskTextWatcher(edtDataNascimento, simpleMaskDataNascimento);
        edtDataNascimento.addTextChangedListener( maskDataNascimento );


        Button btnVoltar = (Button) findViewById(R.id.button4);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TelaCadastroActivity.super.onBackPressed();
            }
        });
    }

    public void cadastro (View view){
        if (!edtEmail.getText().toString().equals("")){
            if (edtSenha.getText().toString().equals(edtConfSenha.getText().toString())){
                if (!edtNome.getText().toString().equals("")){
                if (!edtCPF.getText().toString().equals("")){
                    if (!edtNomedaMae.getText().toString().equals("")){
                        if (!edtSenha.getText().toString().equals("")){







                firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            String erro = "";
                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                erro = "Escolha uma senha que contenha, letras e números.";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erro = "Email indicado não é válido.";
                            } catch (FirebaseAuthUserCollisionException e) {
                                erro = "Já existe uma conta com esse e-mail.";
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(TelaCadastroActivity.this, "Erro ao cadastrar usuário: " + erro, Toast.LENGTH_LONG ).show();

                        }else{
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("Nome Completo").setValue(edtNome.getText().toString());
                            databaseReference.child("CPF").setValue(edtCPF.getText().toString());
                            databaseReference.child("Email").setValue(edtEmail.getText().toString());
                            databaseReference.child("Nome da mãe").setValue(edtNomedaMae.getText().toString());
                            databaseReference.child("Senha").setValue(edtSenha.getText().toString());

                            Toast.makeText(TelaCadastroActivity.this, "Usuario criado com sucesso!", Toast.LENGTH_LONG).show();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(TelaCadastroActivity.this, "Email de verificação enviado!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            startActivity(new Intent(TelaCadastroActivity.this, TelaLoginActivity.class));
                            finish();
                        }
                    }
                });



                        }else{
                            Toast.makeText(TelaCadastroActivity.this, "Campo de senha vazio", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(TelaCadastroActivity.this, "Insira o nome da sua mãe", Toast.LENGTH_LONG).show();
                    }


                    }else{
                        Toast.makeText(TelaCadastroActivity.this, "Insira seu CPF", Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(TelaCadastroActivity.this, "Insira seu nome Completo!", Toast.LENGTH_LONG).show();
            }


            }else{
                Toast.makeText(TelaCadastroActivity.this, "Senhas diferentes!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(TelaCadastroActivity.this, "Preencha o campo do e-mail!", Toast.LENGTH_LONG).show();
        }
    }



}
