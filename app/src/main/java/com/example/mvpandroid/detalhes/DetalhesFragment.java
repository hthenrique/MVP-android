package com.example.mvpandroid.detalhes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvpandroid.R;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.squareup.picasso.Picasso;

public class DetalhesFragment extends Fragment implements DetalhesContract.View {

    private DetalhesContract.Presenter presenter;

    private TextView filme_titulo;
    private TextView filme_ano;
    private TextView filme_diretor;
    private TextView filme_atores;
    private TextView filme_sinopse;
    private TextView filme_genero;
    private ImageView filme_poster;

    public DetalhesFragment(){
    }

    public static Fragment newInstance(){
        return new DetalhesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mActionListener = new DetalhesPresenter(new FilmeServiceImpl(), this);
        setHasOptionsMenu(true);
        presenter = new DetalhesPresenter(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.detalhes_fragment, container, false);

        String imdbid = getActivity().getIntent().getExtras().getString("imdbid");
        String title = getActivity().getIntent().getExtras().getString("title");
        String year = getActivity().getIntent().getExtras().getString("year");
        String actors = getActivity().getIntent().getExtras().getString("actors");
        String director = getActivity().getIntent().getExtras().getString("director");
        String plot = getActivity().getIntent().getExtras().getString("plot");
        String poster = getActivity().getIntent().getExtras().getString("poster");
        String genre = getActivity().getIntent().getExtras().getString("genre");


        filme_poster = root.findViewById(R.id.filme_poster);
        Picasso.with(getContext())
                .load(poster)
                .fit()
                .placeholder(R.drawable.ic_insert_photo_black_48px)
                .into(filme_poster);

        filme_titulo = root.findViewById(R.id.filme_titulo);
        filme_titulo.setText(title);

        filme_ano = root.findViewById(R.id.filme_ano);
        filme_ano.setText(year);

        filme_atores = root.findViewById(R.id.filme_atores);
        filme_atores.setText(actors);

        filme_diretor = root.findViewById(R.id.filme_diretor);
        filme_diretor.setText(director);

        filme_sinopse = root.findViewById(R.id.filme_sinopse);
        filme_sinopse.setText(plot);

        filme_genero = root.findViewById(R.id.filme_genero);
        filme_genero.setText(genre);

        presenter.carregarFilme(imdbid);

        return root;
    }

    @Override
    public void exibirDetalhes(FilmeDetalhes filmeDetalhes) {
        filme_titulo.setText(filmeDetalhes.title);
        filme_ano.setText(filmeDetalhes.year);
        filme_atores.setText(filmeDetalhes.actors);
        filme_diretor.setText(filmeDetalhes.director);
        filme_sinopse.setText(filmeDetalhes.plot);
        filme_genero.setText(filmeDetalhes.genre);
    }
}
