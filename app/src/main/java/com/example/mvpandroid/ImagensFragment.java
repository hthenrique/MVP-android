package com.example.mvpandroid;

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

import com.example.mvpandroid.data.ImagensServiceImpl;
import com.example.mvpandroid.data.model.Imagens;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class ImagensFragment extends Fragment implements ImagensContract.View {

    private ImagensContract.UserActionsListener mActionsListener;

    private ImagensAdapter mListAdapter;

    public  ImagensFragment(){
    }

    public static Fragment newInstance() {
        return new ImagensFragment();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mListAdapter = new ImagensAdapter(new ArrayList<Imagens>(0), mItemListener);
        mActionsListener = new ImagensPresenter(new ImagensServiceImpl(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.carregarImagens();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.imagens_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.imagens_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = 1;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.carregarImagens();
            }
        });
        return root;
    }

    @Override
    public void setCarregando(final boolean isAtivo) {
        if (getView() == null){
            return;
        }
        final  SwipeRefreshLayout srl = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(isAtivo);
            }
        });
    }

    @Override
    public void exibirImagens(List<Imagens> imagens) {
        mListAdapter.replaceData(imagens);
    }

    @Override
    public void exibirDetalhesUI(String imagensId) {

    }

    ItemListener mItemListener = new ItemListener(){
        @Override
        public void onImagensClick(Imagens imagens){
            mActionsListener.abrirDetalhes(imagens);
        }
    };

    private static class ImagensAdapter extends RecyclerView.Adapter<ImagensAdapter.ViewHolder>{

        private List<Imagens> mImagens;
        private ItemListener mItemListener;

        public ImagensAdapter(List<Imagens> imagens, ItemListener itemListener){
            setList(imagens);
            mItemListener = itemListener;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.imagem_item, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ImagensAdapter.ViewHolder holder, int position) {
            Imagens imagens = mImagens.get(position);

            Picasso.with(holder.thumbnail.getContext())
                    .load(imagens.posterUrl)
                    .fit().centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_48px)
                    .into(holder.thumbnail);

            holder.titulo.setText(imagens.titulo);

        }

        public void replaceData(List<Imagens> notes){
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Imagens> notes) {
            mImagens = notes;
        }

        @Override
        public int getItemCount() {
            return mImagens.size();
        }

        public Imagens getItem(int position){
            return mImagens.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public ImageView thumbnail;
            public TextView titulo;
            private ItemListener mItemListener;

            public ViewHolder(@NonNull View itemView, ItemListener listener) {
                super(itemView);
                mItemListener = listener;
                titulo = (TextView) itemView.findViewById(R.id.imagem_titulo);
                thumbnail = (ImageView) itemView.findViewById(R.id.imagem_thumbnail);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Imagens imagens = getItem(position);
                mItemListener.onImagensClick(imagens);
            }
        }
    }

    public interface ItemListener{
        void onImagensClick(Imagens clickedNote);
    }

}
