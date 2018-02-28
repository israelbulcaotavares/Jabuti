package com.example.mechatronicse.jabuti2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mechatronicse.jabuti2.R;
import com.example.mechatronicse.jabuti2.activities.TelaCadastroActivity;


public class Ola3Fragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Ola3Fragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.etapa_3_fragment, container, false);

        Button botao_avancar = (Button) view.findViewById(R.id.botao_avancar);
        botao_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirOutraActivity = new Intent(getActivity(), TelaCadastroActivity.class);
                startActivity(abrirOutraActivity);

            }
        });


        return view;
    }





}
