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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agrotrading.kancher.dbflowtask.database.Category;

import org.androidannotations.annotations.EFragment;

@EFragment
public class AddingCategoryDialogFragment extends DialogFragment {

    private AddingCategoryListener addingCategoryListener;

    public interface AddingCategoryListener {
        void onCategoryAdded(Category category);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            addingCategoryListener = (AddingCategoryListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement AddingCategoryListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Category category = new Category();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final TextInputLayout tilCategoryName;
        final EditText etCategoryName;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View container = inflater.inflate(R.layout.dialog_category, null);

        tilCategoryName = (TextInputLayout) container.findViewById(R.id.til_category_name);
        etCategoryName = tilCategoryName.getEditText();


        builder.setTitle(R.string.adding_category_dialog);
        builder.setView(container);

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                category.setName(etCategoryName.getText().toString());
                category.save();
                addingCategoryListener.onCategoryAdded(category);
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
                if (etCategoryName.length() == 0) {
                    positiveButton.setEnabled(false);
                    tilCategoryName.setError(getResources().getString(R.string.dialog_error_empty_title));
                }

                etCategoryName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) {
                            positiveButton.setEnabled(false);
                            tilCategoryName.setError(getResources().getString(R.string.dialog_error_empty_title));
                        } else {
                            positiveButton.setEnabled(true);
                            tilCategoryName.setErrorEnabled(false);
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
