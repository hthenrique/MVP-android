package com.example.mvpandroid.utils;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private int minItemsBeforeNextLoad = 5;
    private int startingPage = 1;
    private int currentPage = 1;
    private int latestTotalItemCount = 0;
    private boolean isLoading = true;

    LinearLayoutManager layoutManager;
    private Object RecyclerView;

    public InfiniteScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        minItemsBeforeNextLoad *= layoutManager.getBaseline();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        //int lastVisibleItemPosition = layoutManager.getItemCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }

            /*// Assume list was invalidated -- set back to default
            if (totalItemCount < latestTotalItemCount) {
                this.currentPage = this.startingPage;
                this.latestTotalItemCount = totalItemCount;
            }

            // If still loading and dataset size has been updated,
            // update load state and last item count
            if (isLoading && totalItemCount > latestTotalItemCount) {
                isLoading = false;
                latestTotalItemCount = totalItemCount;
            }

            // If not loading and within threashold limit, increase current page and load more data
            if (!isLoading && (lastVisibleItemPosition + minItemsBeforeNextLoad > totalItemCount)) {
                currentPage++;
                onLoadMore(currentPage, totalItemCount, recyclerView);
                isLoading = true;
            }*/
    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

}


