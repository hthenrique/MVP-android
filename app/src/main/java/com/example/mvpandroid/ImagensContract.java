package com.example.mvpandroid;

import androidx.annotation.NonNull;

import com.example.mvpandroid.data.model.Imagens;

import java.util.List;

public interface ImagensContract {

    interface View {
        void setCarregando(boolean isAtivo);

        void exibirImagens(List<Imagens> imagens);

        void exibirDetalhesUI (String imagensId);
    }

    interface UserActionsListener{

        void carregarImagens();

        void abrirDetalhes(@NonNull Imagens imagens);
    }

}
