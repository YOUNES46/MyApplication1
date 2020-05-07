package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampeDialog extends AppCompatDialogFragment {
    EditText name,eamil,wilaya;
    ExampleDialogListener Listner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.input_dialog,null);
        name =view.findViewById(R.id.nameEditText);
        eamil=view.findViewById(R.id.emailEditText);
        wilaya =view.findViewById(R.id.wilayaEditText);
        builder.setView(view)
                .setTitle("Ajouter Etudiant")
                .setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String nom = name.getText().toString();
                        String emaill = eamil.getText().toString();
                        String wilayaa = wilaya.getText().toString();
                        if(nom.length()==0){
                         name.setError("Entrer d'abord le Nom");
                        }
                        else if(emaill.length()==0){
                          eamil.setError("Entrer l'Email");
                        }
                        else if (wilayaa.length()==0){
                          wilaya.setError("Entrer La Wilaya");
                        }
                        else{
                        Listner.applyTexts(nom,emaill,wilayaa);}
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            Listner =(ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+"must implement ExamalpleDialoge");
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String name,String email,String wilaya);
    }
}
