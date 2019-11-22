package com.example.mvpandroid.filmes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvpandroid.R;
import com.example.mvpandroid.utils.MyReceiver;

public class FilmesActivity extends AppCompatActivity {

    private FilmesFragment filmesFragment;
    private Context context;
    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmes_activity);
        MyReceiver = new MyReceiver();
        broadcastIntent();

        if (null == savedInstanceState){
            filmesFragment = new FilmesFragment();
            initFragment(FilmesFragment.newInstance());
        }


    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }


    private void initFragment(Fragment filmesFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, filmesFragment);
        transaction.commit();
    }

}

