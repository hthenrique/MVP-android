package com.example.mvpandroid.detalhes;

import com.example.mvpandroid.data.FilmeServiceApi;
import com.example.mvpandroid.data.FilmeServiceImpl;
import com.example.mvpandroid.data.model.FilmeDetalhes;

public class DetalhesPresenter implements DetalhesContract.Presenter {

    private final FilmeServiceApi mApi;
    private final DetalhesContract.View mDetalhesView;

    DetalhesPresenter(DetalhesContract.View detalhesView) {
        mApi = new FilmeServiceImpl();
        mDetalhesView = detalhesView;
    }

    @Override
    public void carregarFilme(String imdbid) {
        mApi.getFilme(imdbid, mDetalhesView::exibirDetalhes);

    }
}
