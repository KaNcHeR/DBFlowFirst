package com.agrotrading.kancher.dbflowtask.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agrotrading.kancher.dbflowtask.R;
import com.agrotrading.kancher.dbflowtask.database.Product;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.products_list_item)
public class ProductItemView extends RelativeLayout {

    @ViewById(R.id.name_text)
    TextView nameText;

    @ViewById(R.id.category_text)
    TextView categoryText;

    public ProductItemView(Context context) {
        super(context);
    }

    public void bind(Product product) {
        nameText.setText(product.getName());
        categoryText.setText(product.getCategory().getName());
    }
}
