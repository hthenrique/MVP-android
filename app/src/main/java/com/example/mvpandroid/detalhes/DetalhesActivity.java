package com.example.mvpandroid.detalhes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvpandroid.R;
import com.example.mvpandroid.filmes.FilmesActivity;
import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity {

    private TextView filme_titulo;
    private TextView filme_ano;
    private TextView filme_diretor;
    private TextView filme_genero;
    private TextView filme_sinope;
    private ImageView filme_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        filme_titulo = findViewById(R.id.filme_titulo);
        filme_ano = findViewById(R.id.filme_ano);
        filme_diretor = findViewById(R.id.filme_diretor);
        filme_genero = findViewById(R.id.filme_genero);
        filme_sinope = findViewById(R.id.filme_sinopse);
        filme_poster = findViewById(R.id.filme_poster);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null){
            detalhes(extras);
        }else{
            //finish();
        }

    }

    private void detalhes(Bundle extras) {
        Picasso.with(this)
                .load(extras.getString("Poster"))
                .fit().centerCrop()
                .into(filme_poster);

        filme_titulo.setText(extras.getString("Title"));
        filme_ano.setText(extras.getString("Year"));
        filme_diretor.setText(extras.getString("Director"));
        filme_genero.setText(extras.getString("Genre"));
        filme_sinope.setText(extras.getString("Plot"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
