   package com.example.womensafety;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.womensafety.data.MyDBHandler;
import com.example.womensafety.model.Contacts;

public class contactDialog extends AppCompatDialogFragment {

    private EditText editTextConName;
    private EditText editTextConPh;
    private contactListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_contact,null);

        builder.setView(view)
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String first = editTextConName.getText().toString();
                        String second = editTextConPh.getText().toString();
                        listener.applyText(first,second);


                    }
                });

        editTextConName = view.findViewById(R.id.addName);
        editTextConPh = view.findViewById(R.id.addPh);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (contactListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement contactsistener");
        }
    }

    public interface contactListener{
        void applyText(String name,String phNo);
    }
}
