package com.example.mvpandroid.detalhes;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
    private TextView filme_producao;
    private TextView filme_faixa;
    private TextView filme_boxoffice;
    private TextView filme_duracao;
    private TextView filme_linguagem;
    private ImageView filme_poster;
    private RatingBar ratingBar;
    private View filme_nome;

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

    @SuppressLint("ResourceType")
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
        String production = getActivity().getIntent().getExtras().getString("production");
        String rated = getActivity().getIntent().getExtras().getString("Rated");
        String boxoffice = getActivity().getIntent().getExtras().getString("BoxOffice");
        String runtime = getActivity().getIntent().getExtras().getString("Runtime");
        String language = getActivity().getIntent().getExtras().getString("Language");
        double imdbrating = getActivity().getIntent().getExtras().getDouble("imdbRating");

        filme_poster = root.findViewById(R.id.filme_poster);
        Picasso.with(getContext())
                .load(poster)
                .fit()
                .placeholder(R.drawable.ic_insert_photo_black_48px)
                .into(filme_poster);

        filme_titulo = root.findViewById(R.id.filme_titulo);

        filme_ano = root.findViewById(R.id.filme_ano);

        filme_atores = root.findViewById(R.id.filme_atores);

        filme_diretor = root.findViewById(R.id.filme_diretor);

        filme_sinopse = root.findViewById(R.id.filme_sinopse);

        filme_genero = root.findViewById(R.id.filme_genero);

        filme_producao = root.findViewById(R.id.filme_producao);

        filme_faixa = root.findViewById(R.id.filme_faixa);

        filme_boxoffice = root.findViewById(R.id.filme_boxoffice);

        filme_duracao = root.findViewById(R.id.filme_duracao);

        filme_linguagem = root.findViewById(R.id.filme_linguagem);

        ratingBar = root.findViewById(R.id.ratingBar);

        presenter.carregarFilme(imdbid);

        return root;
    }

    @Override
    public void exibirDetalhes(FilmeDetalhes filmeDetalhes) {
        ((DetalhesActivity) getActivity())
                .setActionBarTitle(filmeDetalhes.title);

        filme_titulo.setText(filmeDetalhes.title);
        filme_ano.setText(filmeDetalhes.year);
        filme_atores.setText(filmeDetalhes.actors);
        filme_diretor.setText(filmeDetalhes.director);
        filme_sinopse.setText(filmeDetalhes.plot);
        filme_genero.setText(filmeDetalhes.genre);
        filme_producao.setText(filmeDetalhes.production);
        filme_faixa.setText(filmeDetalhes.rated);
        filme_boxoffice.setText(filmeDetalhes.boxoffice);
        filme_duracao.setText(filmeDetalhes.runtime);
        filme_linguagem.setText(filmeDetalhes.language);
        ratingBar.setRating((float)filmeDetalhes.imdbrating/2);
    }
}
