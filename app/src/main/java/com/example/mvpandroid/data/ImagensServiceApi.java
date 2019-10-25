package com.example.mvpandroid.data;

import com.example.mvpandroid.data.model.Imagens;
import com.example.mvpandroid.data.model.ImagensResultadoBusca;

public interface ImagensServiceApi {

    interface ImagensServiceCallback<T>{
        void onLoaded(T imagens);
    }
    void getImagens(ImagensServiceCallback<ImagensResultadoBusca> callback);
    void getImagem(String imgensId, ImagensServiceCallback<Imagens> callback);
}
