package com.example.mvpandroid.filmes;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvpandroid.R;
import com.example.mvpandroid.data.model.FilmeDetalhes;
import com.example.mvpandroid.detalhes.DetalhesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.content.Context.SEARCH_SERVICE;

public class FilmesFragment extends Fragment implements FilmesContract.View {

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private String querySearch;

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
        mListAdapter = new FilmesAdapter(new ArrayList<FilmeDetalhes>(0), mItemListener);
        mActionsListener = new FilmesPresenter(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.carregarFilmes(querySearch);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.filmes_fragment, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.filmes_list);
        recyclerView.setAdapter(mListAdapter);
        Toolbar mToolbar = root.findViewById(R.id.main_toolbar);
        recyclerView.setAdapter(mListAdapter);

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

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
    public void setCarregando(final boolean isAtivo) {
        if (getView() == null){
            return;
        }
        final  SwipeRefreshLayout srl = getView().findViewById(R.id.SwipeRefresh);

        srl.post(() -> srl.setRefreshing(isAtivo));
    }

    @Override
    public void exibirFilmes(List<FilmeDetalhes> filmes) {
        mListAdapter.replaceData(filmes);
    }


    @Override
    public void exibirDetalhesUI(FilmeDetalhes filme) {

        Intent intent = new Intent(getActivity().getBaseContext(), DetalhesActivity.class);
        intent.putExtra("imdbid", filme.imdbid);
        intent.putExtra("title", filme.title);
        intent.putExtra("year", filme.year);
        intent.putExtra("actors", filme.actors);
        intent.putExtra("Director", filme.director);
        intent.putExtra("plot", filme.plot);
        intent.putExtra("poster", filme.poster);
        intent.putExtra("genre",filme.genre);

        getActivity().startActivity(intent);

    }

    ItemListener mItemListener = filme -> exibirDetalhesUI(filme);

    private class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder>{

        private List<FilmeDetalhes> mFilmes;
        private ItemListener mItemListener;

        public FilmesAdapter(List<FilmeDetalhes> filmes, ItemListener itemListener){
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
            FilmeDetalhes filme = mFilmes.get(position);

            Picasso.with(viewHolder.thumbnail.getContext())
                    .load(filme.poster)
                    .fit().centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_48px)
                    .into(viewHolder.thumbnail);

            viewHolder.titulo.setText(filme.title);
            viewHolder.ano.setText(filme.year);

        }

        void replaceData(List<FilmeDetalhes> notes){
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<FilmeDetalhes> notes) {
            mFilmes = notes;
        }

        @Override
        public int getItemCount() {
            return mFilmes.size();
        }

        FilmeDetalhes getItem(int position){
            return mFilmes.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView thumbnail;
            TextView titulo;
            TextView ano;
            View view;

            ViewHolder(@NonNull View itemView, ItemListener listener) {
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
                FilmeDetalhes filme = getItem(position);
                mItemListener.onFilmeClick(filme);
            }
        }
    }

    public interface ItemListener{
        void onFilmeClick(FilmeDetalhes clickedNote);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.mi_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(SEARCH_SERVICE);

        if (searchItem != null)
            searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    querySearch = query;
                    mActionsListener.carregarFilmes(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_search:
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

}
