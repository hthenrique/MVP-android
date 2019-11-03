package com.example.mvpandroid.detalhes;

import androidx.fragment.app.Fragment;

import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.filmes.FilmesContract;

import java.util.List;

public class DetalhesFragment extends Fragment implements FilmesContract.View2 {

    public static Fragment newInstance() {
        return new DetalhesFragment();
    }

    @Override
    public void setCarregando(boolean isAtivo) {

    }

    @Override
    public void exibirFilmes(List<FilmeDetalhes> filme) {
    }

    @Override
    public void exibirDetalhesUI(FilmeDetalhes filme) {

    }
}
