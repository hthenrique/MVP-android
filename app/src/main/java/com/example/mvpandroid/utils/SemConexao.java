package com.example.mvpandroid.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpandroid.R;
import com.example.mvpandroid.filmes.FilmesActivity;
import com.example.mvpandroid.filmes.NenhumResultadoActivity;

public class SemConexao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_conexao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(SemConexao.this, FilmesActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
