package com.example.mvpandroid.filmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.example.mvpandroid.R;

public class FilmesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmes_activity);

        if (null == savedInstanceState){
            initFragment(FilmesFragment.newInstance());
        }

    }

    private void initFragment(Fragment filmesFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, filmesFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchView mSearchView = (SearchView) menu.findItem(R.id.search)
        .getActionView();
        mSearchView.setQueryHint("Pesquisa");

        //mSearchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

        return true;
    }
}
