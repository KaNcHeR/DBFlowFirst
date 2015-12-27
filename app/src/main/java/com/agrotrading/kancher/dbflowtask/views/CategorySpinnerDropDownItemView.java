package com.agrotrading.kancher.dbflowtask.views;

import android.content.Context;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.agrotrading.kancher.dbflowtask.database.Category;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(android.R.layout.simple_spinner_dropdown_item)
public class CategorySpinnerDropDownItemView extends LinearLayout {

    @ViewById(android.R.id.text1)
    CheckedTextView name;

    public CategorySpinnerDropDownItemView(Context context) {
        super(context);
    }

    public void bind(Category category) {
        name.setText(category.getName());
    }

}
