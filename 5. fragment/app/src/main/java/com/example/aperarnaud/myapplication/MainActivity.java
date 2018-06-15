package com.example.aperarnaud.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends FragmentActivity {
    boolean switchScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {

        Fragment newFragment;

        if(switchScreen) {
            newFragment = new MyFragment();
        } else {
            newFragment = new FragmentAlain();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        transaction.setCustomAnimations(R.anim.fade_in,
                R.anim.fade_out);

        transaction.replace(R.id.fragment, newFragment);

        transaction.commit();

        switchScreen = !switchScreen;
    }
}
