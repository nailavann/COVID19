package com.example.covid19;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class IstatistikFragment extends Fragment{

    Context context;
    private RecyclerView recyclerView;
    private EditText edtTxt;
    private static final String myUrl = "https://corona.lmao.ninja/v2/countries";
    private ArrayList<Ulke> arrayList;
    Ulke ulke;
    UlkeAdapter ulkeAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_istatistik, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        edtTxt = view.findViewById(R.id.edtTxt);

        //Verileri çağır
        verileriYukle();
        //Recycler etkinlestir
        recyclerViewSettings();

        //edtTxt
        edtTxtSettings();

        //recyclerview click eventi
        recyclerViewOnClick();

        return view;
    }



    private void recyclerViewOnClick(){
        ulkeAdapter.setOnItemClickListener(new UlkeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Ulke ulke, int position) {
                Intent intent = new Intent(context,IstatistikDetaylar.class);
                intent.putExtra("ulkeadi",ulke.getUlkeAdi());
                intent.putExtra("toplamvaka",ulke.getUlkeToplamVaka());
                intent.putExtra("toplamolum",ulke.getUlkeToplamOlum());
                intent.putExtra("toplamiyilesen",ulke.getUlkeToplamIyilesen());
                intent.putExtra("gunlukvaka",ulke.getUlkeGunlukVaka());
                intent.putExtra("gunlukolum",ulke.getUlkeGunlukOlum());
                intent.putExtra("gunlukiyilesen",ulke.getUlkeGunlukIyilesen());
                intent.putExtra("agirhasta",ulke.getUlkeAgirHasta());
                intent.putExtra("testsayisi",ulke.getUlkeTestSayisi());
                intent.putExtra("guncellenmetarihi",ulke.getDetaylarGuncellenmeTarihi());
                startActivity(intent);
            }
        });
    }

    private void verileriYukle() {
        arrayList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        JSONObject ulkeBilgi = data.getJSONObject("countryInfo");
                        String ulkeAdi= data.getString("country");
                        String ulkeToplamVaka = data.getString("cases");
                        String ulkeToplamOlum = data.getString("deaths");
                        String ulkeToplamIyilesen = data.getString("recovered");
                        String ulkeGunlukVaka = data.getString("todayCases");
                        String ulkeGunlukOlum = data.getString("todayDeaths");
                        String ulkeGunlukIyilesen = data.getString("todayRecovered");
                        String ulkeAgirHasta = data.getString("critical");
                        String ulkeTestSayisi = data.getString("tests");
                        String ulkeResmi = ulkeBilgi.getString("flag");
                        String güncellenmeTarihi = AnasayfaFragment.getDate(data.getLong("updated"));



                        Ulke ulke =new Ulke(ulkeAdi,ulkeResmi,
                                ulkeToplamVaka,ulkeToplamOlum,
                                ulkeToplamIyilesen,ulkeGunlukVaka,
                                ulkeGunlukOlum,ulkeGunlukIyilesen,
                                ulkeAgirHasta,ulkeTestSayisi,güncellenmeTarihi);
                        arrayList.add(ulke);
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, "HATA", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void recyclerViewSettings() {
        ulkeAdapter = new UlkeAdapter(arrayList, context);
        recyclerView.setAdapter(ulkeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void edtTxtSettings() {
        edtTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    ulkeAdapter.getFilter().filter(s);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}