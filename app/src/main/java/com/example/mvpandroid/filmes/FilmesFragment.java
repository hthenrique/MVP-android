package com.example.mvpandroid.filmes;

import android.content.Context;
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
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.filmes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = 1;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.carregarFilmes();
            }
        });
        return root;
    }

    @Override
    public void setCarregando(final boolean Ativo) {
        if (getView() == null){
            return;
        }
        final  SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.SwipeRefresh);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(Ativo);
            }
        });
    }

    @Override
    public void exibirFilmes(List<Filme> filmes) {
        mListAdapter.replaceData(filmes);
    }

    @Override
    public void exibirDetalhesUI(String filmeId) {

    }

    ItemListener mItemListener = new ItemListener(){
        @Override
        public void onFilmeClick(Filme filme) {
            mActionsListener.abrirDetalhes(filme);
        }

    };

    private static class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder>{

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

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public ImageView thumbnail;
            public TextView titulo;
            private ItemListener mItemListener;

            public ViewHolder(@NonNull View itemView, ItemListener listener) {
                super(itemView);
                mItemListener = listener;
                titulo = (TextView) itemView.findViewById(R.id.filme_titulo);
                thumbnail = (ImageView) itemView.findViewById(R.id.filme_thumbnail);
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
