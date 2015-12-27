package com.agrotrading.kancher.dbflowtask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.agrotrading.kancher.dbflowtask.adapters.CategoriesSpinnerAdapter;
import com.agrotrading.kancher.dbflowtask.database.Category;
import com.agrotrading.kancher.dbflowtask.database.Product;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment
public class AddingProductDialogFragment extends DialogFragment {

    private AddingProductListener addingProductListener;

    public interface AddingProductListener {
        void onProductAdded(Product product);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addingProductListener = (AddingProductListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement AddingCategoryListener");
        }
    }

    @Bean
    CategoriesSpinnerAdapter categoriesSpinnerAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Product product = new Product();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final TextInputLayout tilProductName;
        final EditText etProductName;
        Spinner spinnerCategories;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View container = inflater.inflate(R.layout.dialog_product, null);

        tilProductName = (TextInputLayout) container.findViewById(R.id.til_product_name);
        etProductName = tilProductName.getEditText();

        spinnerCategories = (Spinner) container.findViewById(R.id.spinner_categories);

        spinnerCategories.setAdapter(categoriesSpinnerAdapter);

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product.associateCategory(categoriesSpinnerAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setTitle(R.string.adding_product_dialog);
        builder.setView(container);

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                product.setName(etProductName.getText().toString());
                product.save();
                addingProductListener.onProductAdded(product);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                if (etProductName.length() == 0) {
                    positiveButton.setEnabled(false);
                    tilProductName.setError(getResources().getString(R.string.dialog_error_empty_title));
                }

                etProductName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) {
                            positiveButton.setEnabled(false);
                            tilProductName.setError(getResources().getString(R.string.dialog_error_empty_title));
                        } else {
                            positiveButton.setEnabled(true);
                            tilProductName.setErrorEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        return alertDialog;
    }
}
