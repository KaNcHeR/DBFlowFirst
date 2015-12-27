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

import com.agrotrading.kancher.dbflowtask.adapters.ProductsAdapter;
import com.agrotrading.kancher.dbflowtask.database.Product;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.list_fragment)
public class ProductsFragment extends Fragment {

    @ViewById(R.id.context_recyclerview)
    RecyclerView expensesRecyclerView;

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Click(R.id.fab)
    void startAddProductActivity(){
        DialogFragment addingProductDialogFragment = new AddingProductDialogFragment_();
        addingProductDialogFragment.show(getFragmentManager(), "AddingProductDialogFragment");
    }

    @Bean
    ProductsAdapter productsAdapter;

    @AfterViews
    void ready() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        expensesRecyclerView.setLayoutManager(linearLayoutManager);
        getActivity().setTitle(getString(R.string.nav_drawer_products));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<Product>>() {
            @Override
            public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<Product>> loader = new AsyncTaskLoader<List<Product>>(getActivity()) {
                    @Override
                    public List<Product> loadInBackground() {
                        return Product.getAllProducts();
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
                expensesRecyclerView.setAdapter(productsAdapter.setItems(data));
            }

            @Override
            public void onLoaderReset(Loader<List<Product>> loader) {

            }
        });
    }

    public void addProduct(Product product) {
        productsAdapter.addItem(product);
    }
}
