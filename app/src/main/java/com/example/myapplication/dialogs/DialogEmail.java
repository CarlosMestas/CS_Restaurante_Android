package com.example.myapplication.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.activities.ActivityCategorias;
import com.example.myapplication.clases.Pedido;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class DialogEmail extends AppCompatDialogFragment {

    private EditText editTextEmail;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String recipientEmail = "Origenes1524@gmail.com";
        final String recipientPassword = "oRigEnes1524";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_email, null);
        builder.setView(view)
                .setTitle("Introduzca su correo")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key = editTextEmail.getText().toString();


                    }
                });

        editTextEmail = view.findViewById(R.id.editTextEmail);

        return builder.create();
    }


}