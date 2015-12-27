package com.agrotrading.kancher.dbflowtask.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agrotrading.kancher.dbflowtask.database.Category;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(android.R.layout.simple_spinner_item)
public class CategorySpinnerItemView extends LinearLayout {

    @ViewById(android.R.id.text1)
    TextView name;

    public CategorySpinnerItemView(Context context) {
        super(context);
    }

    public void bind(Category category) {
        name.setText(category.getName());
    }

}
