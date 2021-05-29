package com.example.covid19;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.Inflater;

public class AnasayfaFragment extends Fragment {

    private static final String myUrl = "https://disease.sh/v3/covid-19/all";

    private TextView txtToplamVaka , txtToplamOlu , txtToplamIyilesen, txtGuncellenme;
    ProgressBar progressBar;
    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anasayfa,container ,false);
        txtToplamVaka = view.findViewById(R.id.txtToplamVaka);
        txtToplamOlu = view.findViewById(R.id.txtToplamOlu);
        txtToplamIyilesen = view.findViewById(R.id.txtToplamIyilesen);
        progressBar = view.findViewById(R.id.progressBar);
        txtGuncellenme = view.findViewById(R.id.txtGuncellenme);

        progressBar.setVisibility(View.GONE);

        verileriYukle();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        verileriYukle();
    }

    private void verileriYukle(){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);


                    String toplamVaka =jsonObject.getString("cases");
                    String toplamOlu = jsonObject.getString("deaths");
                    String toplamIyilesen = jsonObject.getString("recovered");

                    txtToplamVaka.setText(toplamVaka);
                    txtToplamIyilesen.setText(toplamIyilesen);
                    txtToplamOlu.setText(toplamOlu);
                    txtGuncellenme.setText(getDate(jsonObject.getLong("updated")));


                    progressBar.setVisibility(View.GONE);
                }
                catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        //kuyruğa ekliyoruz
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public static String getDate(long milliSecond){
        //Pazartesi , 23 Mart 2021 02:01:04 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE dd/MM/yyyy ",new Locale("tr"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }


}