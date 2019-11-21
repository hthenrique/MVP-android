package com.example.mvpandroid.utils;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpandroid.R;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.filmes.FilmesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private List<FilmeDetalhes> movieResults;
    private Context context;

    private boolean isLoadingAdded = false;
    public PaginationAdapter(Context context) {
        this.context = context;
        movieResults = new ArrayList<>();
    }

    public List<FilmeDetalhes> getFilmes() {
        return movieResults;
    }

    public void setFilmes(List<FilmeDetalhes> movieResults) {
        this.movieResults = movieResults;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                //View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                //viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.filme_item, parent, false);
        viewHolder = new MovieVH(v1, mItemListener);
        return viewHolder;
    }
    
    FilmesFragment.ItemListener mItemListener = filme -> exibirDetalhesUI(filme);

    private void exibirDetalhesUI(FilmeDetalhes filme) {
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FilmeDetalhes result = movieResults.get(position); // Movie

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;

                movieVH.titulo.setText(result.title);

                /**
                 * Using Glide to handle image loading.
                 * Learn more about Glide here:
                 *
                 */
                Picasso.with(movieVH.thumbnail.getContext())
                        .load(result.poster)
                        .fit().centerCrop()
                        .placeholder(R.drawable.ic_insert_photo_black_48px)
                        .into(movieVH.thumbnail);

                movieVH.titulo.setText(result.title);
                movieVH.ano.setText(result.year);

                break;

            case LOADING:
                //Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return movieResults == null ? 0 : movieResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movieResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(FilmeDetalhes r) {
        movieResults.add(r);
        notifyItemInserted(movieResults.size() - 1);
    }

    public void addAll(List<FilmeDetalhes> moveResults) {
        for (FilmeDetalhes result : moveResults) {
            add(result);
        }
    }

    public void remove(FilmeDetalhes r) {
        int position = movieResults.indexOf(r);
        if (position > -1) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new FilmeDetalhes());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieResults.size() - 1;
        FilmeDetalhes result = getItem(position);

        if (result != null) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public FilmeDetalhes getItem(int position) {
        return movieResults.get(position);
    }

    protected class MovieVH extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView titulo;
        TextView ano;
        View view;

        MovieVH(@NonNull View itemView, FilmesFragment.ItemListener listener) {
            super(itemView);

            titulo = itemView.findViewById(R.id.filme_titulo);
            ano = itemView.findViewById(R.id.filme_ano);
            thumbnail =  itemView.findViewById(R.id.filme_thumbnail);
            view = itemView.findViewById(R.id.item);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
