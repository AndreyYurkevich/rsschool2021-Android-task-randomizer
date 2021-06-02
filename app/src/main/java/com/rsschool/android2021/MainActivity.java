package com.rsschool.android2021;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Interface {
 // public SecondFragment secondFragment = null;
 private static final String SECOND_FRAGMENT_TAG = "secondFragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
        // TODO: invoke function which apply changes of the transaction
    }

    private void openSecondFragment(int min, int max) {
        // TODO: implement it
        final  Fragment secondFragment = SecondFragment.newInstance(min,max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment,SECOND_FRAGMENT_TAG);
        transaction.commit();
    }


    public void onBackPressed() {
        SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager()
                .findFragmentByTag(SECOND_FRAGMENT_TAG);

        if ( secondFragment != null && secondFragment.isAdded() ) {
            openFirstFragment(secondFragment.Back());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setFirstFragment_toStart(int early_number) {
        openFirstFragment(early_number);

    }

    @Override
    public void setSecondFragment_toStart(int max, int min) {
        openSecondFragment(max, min);

    }
}
