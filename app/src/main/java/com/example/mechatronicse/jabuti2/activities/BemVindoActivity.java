package com.example.mechatronicse.jabuti2.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mechatronicse.jabuti2.R;
import com.example.mechatronicse.jabuti2.adapter.PagerAdapter;
import com.example.mechatronicse.jabuti2.helper.SlidingTabLayout;


public class BemVindoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contrato de uso do aplicativo");
        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);


        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));


        PagerAdapter pagerAdapter = new PagerAdapter( getSupportFragmentManager() );
        viewPager.setAdapter(pagerAdapter);

        slidingTabLayout.setViewPager(viewPager);
    }




}
