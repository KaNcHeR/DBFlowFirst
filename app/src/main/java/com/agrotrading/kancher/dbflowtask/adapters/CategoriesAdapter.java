package com.agrotrading.kancher.dbflowtask.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.agrotrading.kancher.dbflowtask.ViewWrapper;
import com.agrotrading.kancher.dbflowtask.database.Category;
import com.agrotrading.kancher.dbflowtask.views.CategoryItemView;
import com.agrotrading.kancher.dbflowtask.views.CategoryItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class CategoriesAdapter extends RecyclerViewAdapterBase<Category, CategoryItemView> {

    @RootContext
    Context context;

    @Override
    public void onBindViewHolder(ViewWrapper<CategoryItemView> holder, int position) {
        CategoryItemView view = holder.getView();
        Category category = items.get(position);
        view.bind(category);
    }

    @Override
    protected CategoryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return CategoryItemView_.build(parent.getContext());
    }

}
