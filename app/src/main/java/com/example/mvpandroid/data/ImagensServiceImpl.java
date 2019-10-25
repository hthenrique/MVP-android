package com.example.mvpandroid.data;

import android.widget.SearchView;

import com.example.mvpandroid.MainActivity;
import com.example.mvpandroid.data.model.Imagens;
import com.example.mvpandroid.data.model.ImagensResultadoBusca;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImagensServiceImpl implements ImagensServiceApi {
    RetrofitEndpoint mRetrofit;

    private SearchView searchView;

    public ImagensServiceImpl(SearchView searchView){
        this.searchView = searchView;
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    public ImagensServiceImpl() {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    @Override
    public void getImagens(final ImagensServiceCallback<ImagensResultadoBusca> callback) {
        Call<ImagensResultadoBusca> callImagens = mRetrofit.busca(searchView.getQuery().toString(),"json");
        callImagens.enqueue(new Callback<ImagensResultadoBusca>() {
            @Override
            public void onResponse(Call<ImagensResultadoBusca> call, Response<ImagensResultadoBusca> response) {
                if (response.code()==200){
                    ImagensResultadoBusca resultadoBusca = response.body();
                    callback.onLoaded(resultadoBusca);
                }
            }

            @Override
            public void onFailure(Call<ImagensResultadoBusca> call, Throwable t) {

            }
        });
    }

    @Override
    public void getImagem(final String imgensId, ImagensServiceCallback<Imagens> callBack) {

    }
}
