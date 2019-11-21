package com.example.mvpandroid.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaginationScrollListener extends RecyclerView.OnScrollListener {
    LinearLayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }
    }

    protected void loadMoreItems() {
    }

    public int getTotalPageCount() {
        return 0;
    }

    public boolean isLastPage() {
        return false;
    }

    public boolean isLoading() {
        return false;
    }
}
