package com.example.myapplication.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.activities.ActivityChef;

public class SignInChef extends AppCompatDialogFragment {
    private EditText editTextUniqueKeyChef;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_signinchef, null);
        builder.setView(view)
                .setTitle("Introduzca clave para ingresar")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key = editTextUniqueKeyChef.getText().toString();
                        Intent intent = new Intent(getActivity(), ActivityChef.class);
                        if(key.equals("chef")) {
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getActivity().getApplicationContext(),"No puede ingresar a la vista del chef", Toast.LENGTH_SHORT).show();
                    }
                });

        editTextUniqueKeyChef = view.findViewById(R.id.edit_uniquePassC);

        return builder.create();
    }

}