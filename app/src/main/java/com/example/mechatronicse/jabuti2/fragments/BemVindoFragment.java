package com.example.mechatronicse.jabuti2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mechatronicse.jabuti2.R;
import com.example.mechatronicse.jabuti2.activities.MainActivity;


public class BemVindoFragment extends Fragment {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public BemVindoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.etapa_0_bem_vindo_fragment, container, false);


        Button botao_voltar = (Button) view.findViewById(R.id.botao_voltar);
        botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
            }
        });


        return view;




    }







}
