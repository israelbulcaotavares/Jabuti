package com.example.mechatronicse.jabuti2.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mechatronicse.jabuti2.fragments.BemVindoFragment;
import com.example.mechatronicse.jabuti2.fragments.Ola1Fragment;
import com.example.mechatronicse.jabuti2.fragments.Ola2Fragment;
import com.example.mechatronicse.jabuti2.fragments.Ola3Fragment;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"Bem Vindo", "Etapa 1","Etapa 2","Etapa final"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new BemVindoFragment();
                break;

            case 1:
                fragment = new Ola1Fragment();
                break;
            case 2:
                fragment = new Ola2Fragment();
                break;

            case 3:
                fragment = new Ola3Fragment();
                break;


        }

        return fragment;

    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
