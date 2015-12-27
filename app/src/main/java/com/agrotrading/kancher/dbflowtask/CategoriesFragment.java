package com.agrotrading.kancher.dbflowtask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.agrotrading.kancher.dbflowtask.adapters.CategoriesAdapter;
import com.agrotrading.kancher.dbflowtask.database.Category;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.list_fragment)
public class CategoriesFragment extends Fragment {

    @ViewById(R.id.context_recyclerview)
    RecyclerView categoriesRecyclerView;

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Click(R.id.fab)
    void startAddCategoryActivity(){
        DialogFragment addingCategoryDialogFragment = new AddingCategoryDialogFragment_();
        addingCategoryDialogFragment.show(getFragmentManager(), "AddingCategoryDialogFragment");
    }

    @Bean
    CategoriesAdapter categoriesAdapter;

    @AfterViews
    void ready() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
        getActivity().setTitle(getString(R.string.nav_drawer_categories));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        getLoaderManager().restartLoader(1, null, new LoaderManager.LoaderCallbacks<List<Category>>() {
            @Override
            public Loader<List<Category>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Category>> loader = new AsyncTaskLoader<List<Category>>(getActivity()) {
                    @Override
                    public List<Category> loadInBackground() {
                        return Category.getAllCategories();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<Category>> loader, List<Category> data) {
                categoriesRecyclerView.setAdapter(categoriesAdapter.setItems(data));
            }

            @Override
            public void onLoaderReset(Loader<List<Category>> loader) {

            }
        });
    }

    public void addCategory(Category category) {
        categoriesAdapter.addItem(category);
    }
}
