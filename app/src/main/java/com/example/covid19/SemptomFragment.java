package com.example.covid19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SemptomFragment extends Fragment {

    private ImageButton btnAlertDiaog , btnAlertSeyrek;
    Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_semptom,container,false);

        btnAlertDiaog = view.findViewById(R.id.btnAlertCiddi);


        ciddiAlert();

        return view;
    }

    private void ciddiAlert(){
        btnAlertDiaog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("COVID-19");
                builder.setMessage("Ciddi semptomlar gösteriyorsanız derhal tıbbi yardım alın. " +
                        "Doktorunuzu veya sağlık tesisini ziyaret etmeden önce mutlaka telefonla arayın.");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.alertdialogicon);
                builder.setPositiveButton("Anladım", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}