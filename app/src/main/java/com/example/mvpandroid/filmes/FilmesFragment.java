package com.example.mvpandroid.filmes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvpandroid.R;
import com.example.mvpandroid.data.FilmeServiceImpl;
import com.example.mvpandroid.data.model.Filme;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.data.model.OnItemListClick;
import com.example.mvpandroid.detalhes.DetalhesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmesFragment extends Fragment implements FilmesContract.View {

    private FilmesContract.UserActionsListener mActionsListener;
    private FilmesAdapter mListAdapter;

    public FilmesFragment(){
    }

    public static Fragment newInstance() {
        return new FilmesFragment();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListAdapter = new FilmesAdapter(new ArrayList<Filme>(0), mItemListener);
        mActionsListener = new FilmesPresenter(new FilmeServiceImpl(), this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.carregarFilmes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.filmes_fragment, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.filmes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = 1;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(() -> mActionsListener.carregarFilmes());
        return root;
    }

    @Override
    public void setCarregando(final boolean Ativo) {
        if (getView() == null){
            return;
        }
        final  SwipeRefreshLayout srl = getView().findViewById(R.id.SwipeRefresh);

        srl.post(() -> srl.setRefreshing(Ativo));
    }

    @Override
    public void exibirFilmes(List<Filme> filmes) {
        mListAdapter.replaceData(filmes);
    }

    @Override
    public void exibirDetalhesUI(FilmeDetalhes filme) {

        Intent intent = new Intent(getActivity().getBaseContext(), DetalhesActivity.class);
        intent.putExtra("Actors", filme.actors);
        intent.putExtra("Title", filme.title);
        intent.putExtra("Genre", filme.director);
        intent.putExtra("Plot", filme.plot);
        intent.putExtra("imdbID", filme.imdbid);
        intent.putExtra("Runtime", filme.runtime);
        getActivity().startActivity(intent);

    }

    ItemListener mItemListener = new ItemListener(){
        @Override
        public void onFilmeClick(Filme filme) {
            mActionsListener.abrirDetalhes(filme);
            Intent intent = new Intent(getActivity(), DetalhesActivity.class);
            getActivity().startActivity(intent);
        }

    };

    private class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder>{

        private List<Filme> mFilmes;
        private ItemListener mItemListener;

        public FilmesAdapter(List<Filme> filmes, ItemListener itemListener){
            setList(filmes);
            mItemListener = itemListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.filme_item, parent, false);
            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            Filme filme = mFilmes.get(position);

            Picasso.with(viewHolder.thumbnail.getContext())
                    .load(filme.posterUrl)
                    .fit().centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_48px)
                    .into(viewHolder.thumbnail);

            viewHolder.titulo.setText(filme.titulo);
            viewHolder.ano.setText(filme.ano);

        }

        public void replaceData(List<Filme> notes){
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Filme> notes) {
            mFilmes = notes;
        }

        @Override
        public int getItemCount() {
            return mFilmes.size();
        }

        public Filme getItem(int position){
            return mFilmes.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView thumbnail;
            public TextView titulo;
            public TextView ano;
            public View view;

            public ViewHolder(@NonNull View itemView, ItemListener listener) {
                super(itemView);

                mItemListener = listener;
                titulo = itemView.findViewById(R.id.filme_titulo);
                ano = itemView.findViewById(R.id.filme_ano);
                thumbnail =  itemView.findViewById(R.id.filme_thumbnail);
                view = itemView.findViewById(R.id.item);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Filme filme = getItem(position);
                mItemListener.onFilmeClick(filme);

            }
        }
    }

    public interface ItemListener{
        void onFilmeClick(Filme clickedNote);

    }

}
