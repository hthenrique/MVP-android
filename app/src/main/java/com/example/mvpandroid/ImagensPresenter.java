package com.example.mvpandroid;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.ImagensServiceApi;
import com.example.mvpandroid.data.model.Imagens;
import com.example.mvpandroid.data.model.ImagensResultadoBusca;

public class ImagensPresenter implements ImagensContract.UserActionsListener {
    private final ImagensServiceApi mApi;
    private final ImagensContract.View mImagensView;

    public ImagensPresenter(ImagensServiceApi api, ImagensContract.View imagensView) {
        mApi = api;
        mImagensView = imagensView;
    }

    @Override
    public void carregarImagens() {
        mImagensView.setCarregando(true);

        mApi.getImagens(new ImagensServiceApi.ImagensServiceCallback<ImagensResultadoBusca>() {
            @Override
            public void onLoaded(ImagensResultadoBusca resultadoBusca) {
                mImagensView.setCarregando(false);
                mImagensView.exibirImagens(resultadoBusca.imagens);
            }
        });
    }

    @Override
    public void abrirDetalhes(@NonNull Imagens imagens) {

    }
}
