package com.example.mvpandroid.filmes;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvpandroid.R;

public class FilmesActivity extends AppCompatActivity {

    private FilmesFragment filmesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmes_activity);


        if (null == savedInstanceState){
            filmesFragment = new FilmesFragment();
            initFragment(FilmesFragment.newInstance());
        }
    }

    private void initFragment(Fragment filmesFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, filmesFragment);
        transaction.commit();
    }

}

