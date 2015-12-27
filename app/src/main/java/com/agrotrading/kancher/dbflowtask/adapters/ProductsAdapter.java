package com.agrotrading.kancher.dbflowtask.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.agrotrading.kancher.dbflowtask.ViewWrapper;
import com.agrotrading.kancher.dbflowtask.database.Product;
import com.agrotrading.kancher.dbflowtask.views.ProductItemView;
import com.agrotrading.kancher.dbflowtask.views.ProductItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ProductsAdapter extends RecyclerViewAdapterBase<Product, ProductItemView> {

    @RootContext
    Context context;

    @Override
    public void onBindViewHolder(ViewWrapper<ProductItemView> holder, int position) {
        ProductItemView view = holder.getView();
        Product product = items.get(position);
        view.bind(product);
    }

    @Override
    protected ProductItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ProductItemView_.build(parent.getContext());
    }

}
