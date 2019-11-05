package com.example.mvpandroid.detalhes;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpandroid.R;
import com.example.mvpandroid.data.FilmeServiceApi;
import com.example.mvpandroid.data.FilmeServiceImpl;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.filmes.FilmesContract;

public class DetalhesFragment extends Fragment implements DetalhesContract.View {

    private TextView filme_titulo;

    private DetalhesContract.Presenter presenter;

    private String imdbId;

    private DetalhesContract.UserActionsListener mActionListener;

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

        imdbId = getActivity().getIntent().getExtras().getString("imdbId");
        presenter.carregarFilme(imdbId);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.detalhes_fragment, container, false);


        //String poster = getActivity().getIntent().getExtras().getString("Poster");
        *//*String actors = getActivity().getIntent().getExtras().getString("Actors");
        String title = getActivity().getIntent().getExtras().getString("Title");
        String director = getActivity().getIntent().getExtras().getString("Director");
        String plot = getActivity().getIntent().getExtras().getString("Plot");
        String imdbid = getActivity().getIntent().getExtras().getString("imdbID");
        String runtime = getActivity().getIntent().getExtras().getString("Runtime");*//*

        *//*TextView filme_titulo = root.findViewById(R.id.filme_titulo);
        filme_titulo.setText(title);
        TextView filme_atores = root.findViewById(R.id.filme_atores);
        filme_atores.setText(actors);
        TextView filme_diretor = root.findViewById(R.id.filme_diretor);
        filme_diretor.setText(director);
        TextView filme_sinopse = root.findViewById(R.id.filme_sinopse);
        filme_sinopse.setText(plot);*//*

        return root;
    }*/

    @Override
    public void exibirDetalhes(FilmeDetalhes filmeDetalhes) {



        Toast.makeText(getActivity(), filmeDetalhes.title, Toast.LENGTH_SHORT).show();
    }
}
