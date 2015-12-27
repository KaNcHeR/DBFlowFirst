package com.agrotrading.kancher.dbflowtask.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.agrotrading.kancher.dbflowtask.ViewWrapper;

import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items;

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    public RecyclerViewAdapterBase setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

}
