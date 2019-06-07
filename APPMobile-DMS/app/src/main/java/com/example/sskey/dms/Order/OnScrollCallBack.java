package com.example.sskey.dms.Order;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class OnScrollCallBack extends RecyclerView.OnScrollListener {
    private int thresHold = 15;
    private int currentPage = 1;
    private int oldTotal = 0;
    private int startingPage = 1;
    private boolean loading = true;
    private RecyclerView.LayoutManager lmana;

    public OnScrollCallBack(int thresHold) {
        this.thresHold = thresHold;
    }

    public OnScrollCallBack(int thresHold, int startPage) {
        this.thresHold = thresHold;
        this.currentPage = startPage;
        this.startingPage = startPage;
    }

    public void setLayout(LinearLayoutManager llManage) {
        lmana = llManage;
    }

    public void setLayout(GridLayoutManager llManage) {
        lmana = llManage;
        thresHold = thresHold * llManage.getSpanCount();
    }

    public void setLayout(StaggeredGridLayoutManager llManage) {
        lmana = llManage;
        thresHold = thresHold * llManage.getSpanCount();
    }

    public int getLastVisibleIt(int[] VisiblePositions) {
        int maxSize = 0;
        for (int i : VisiblePositions) {
            if (i > maxSize) maxSize = i;
        }
        return maxSize;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        int Itemcount = lmana.getItemCount();
        int lastVisible = 0;
        if (lmana instanceof LinearLayoutManager)
            lastVisible = ((LinearLayoutManager) lmana).findLastVisibleItemPosition();
        if (lmana instanceof GridLayoutManager)
            lastVisible = ((GridLayoutManager) lmana).findLastVisibleItemPosition();
        if (lmana instanceof StaggeredGridLayoutManager) {
            int[] lastItemPos = ((StaggeredGridLayoutManager) lmana).findLastVisibleItemPositions(null);
            lastVisible = getLastVisibleIt(lastItemPos);
        }
//        Log.d("SCROOLL", loading + " last: "+lastVisible + " count: "+Itemcount + " old: " + oldTotal);
//reset list if itemcount = 0 onError occurs
        if (Itemcount < oldTotal) {
//            Log.d("SCROOLL 1", loading + " last: "+lastVisible + " count: "+Itemcount + " old: " + oldTotal);
            this.currentPage = this.startingPage;
            oldTotal = Itemcount;
            if (Itemcount == 0) {
                this.loading = true;
            }
        }
// stop loading if Itemcount > oldtotal
        if (loading && Itemcount > oldTotal) {
//            Log.d("SCROOLL 2", loading + " last: "+lastVisible + " count: "+Itemcount + " old: " + oldTotal);
            loading = false;
            oldTotal = Itemcount;
        }
//load more if                           15
        if (!loading && (lastVisible + thresHold) > Itemcount) {
//            Log.d("SCROOLL 3", loading + " last: "+lastVisible + " count: "+Itemcount + " old: " + oldTotal);
            currentPage++;
            loadMore(currentPage, Itemcount, recyclerView);
            loading = true;
        }
    }

    public abstract Boolean loadMore(int page, int totalItems, RecyclerView v);
}
