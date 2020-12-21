package com.example.myapplication.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.configuration.LectorQR;
import com.example.myapplication.database.DataBase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SignInKey extends AppCompatDialogFragment {
    private EditText editTextUniqueKey;
    private EditText editTextNumber;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_signin, null);
        builder.setView(view)
                .setTitle("Configuración")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key = editTextUniqueKey.getText().toString();
                        int number = Integer.parseInt(editTextNumber.getText().toString());
                        Intent intent = new Intent(getActivity(), LectorQR.class);
                        if(key.equals("admin123")) {
                            MainActivity.tableNumber = number;
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getActivity().getApplicationContext(),"No puede ingresar a la configuración", Toast.LENGTH_SHORT).show();
                    }
                });

        editTextUniqueKey = view.findViewById(R.id.edit_uniquePass);
        editTextNumber = view.findViewById(R.id.edit_number);

        return builder.create();
    }

}