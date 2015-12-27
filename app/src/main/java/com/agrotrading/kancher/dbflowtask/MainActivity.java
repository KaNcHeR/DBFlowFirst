package com.agrotrading.kancher.dbflowtask;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.agrotrading.kancher.dbflowtask.database.Category;
import com.agrotrading.kancher.dbflowtask.database.Product;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements AddingProductDialogFragment.AddingProductListener, AddingCategoryDialogFragment.AddingCategoryListener {

    private Fragment fragment;

    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.navigation_view)
    NavigationView navigationView;

    @InstanceState
    Bundle savedInstanceState;

    @AfterViews
    void ready() {
        setupToolbar();
        setupDrawer();
        testData();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ProductsFragment_()).commit();
        }
    }

    @OptionsItem(android.R.id.home)
    void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void setupToolbar() {

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(getString(R.string.add_product));
    }

    private void testData() {
        Category category = new Category();
        category.setName("Электроника");
        category.save();

        Product product = new Product();
        product.setName("SSD Samsung 120Gb");
        product.associateCategory(category);
        product.save();
    }

    private void setupDrawer() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.drawer_products:
                        fragment = new ProductsFragment_();
                        break;
                    case R.id.drawer_categories:
                        fragment = new CategoriesFragment_();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
            return;
        }

        super.onBackPressed();

        Fragment findingFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

        if(findingFragment != null) {
            int itemId = R.id.drawer_products;

            if(findingFragment instanceof ProductsFragment_) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                itemId = R.id.drawer_products;
            } else if(findingFragment instanceof CategoriesFragment_) {
                itemId = R.id.drawer_categories;
            }

            navigationView.getMenu().findItem(itemId).setChecked(true);
        }

    }

    @Override
    public void onProductAdded(Product product) {
        ProductsFragment productsFragment = (ProductsFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        if(productsFragment != null) {
            productsFragment.addProduct(product);
        }
    }

    @Override
    public void onCategoryAdded(Category category) {
        CategoriesFragment categoriesFragment = (CategoriesFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        if(categoriesFragment != null) {
            categoriesFragment.addCategory(category);
        }
    }
}
