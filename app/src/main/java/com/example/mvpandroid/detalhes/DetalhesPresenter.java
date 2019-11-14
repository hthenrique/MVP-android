package com.example.mvpandroid.detalhes;

import android.content.Context;

import com.example.mvpandroid.data.FilmeServiceApi;
import com.example.mvpandroid.data.FilmeServiceImpl;

public class DetalhesPresenter implements DetalhesContract.Presenter {

    private final FilmeServiceApi mApi;
    private final DetalhesContract.View mDetalhesView;
    Context context;

    DetalhesPresenter(DetalhesContract.View detalhesView) {
        mApi = new FilmeServiceImpl(context);
        mDetalhesView = detalhesView;
    }

    @Override
    public void carregarFilme(String imdbid) {
        mApi.getFilme(imdbid, mDetalhesView::exibirDetalhes);

    }
}
