package com.agrotrading.kancher.dbflowtask.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agrotrading.kancher.dbflowtask.R;
import com.agrotrading.kancher.dbflowtask.database.Category;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.categories_list_item)
public class CategoryItemView extends RelativeLayout {

    @ViewById(R.id.name_text)
    TextView nameText;

    public CategoryItemView(Context context) {
        super(context);
    }

    public void bind(Category category) {
        nameText.setText(category.getName());
    }
}
