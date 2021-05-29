package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IstatistikDetaylar extends AppCompatActivity {
    private TextView txtDetayGunlukVaka, txtDetayGunlukOlum, txtDetayGunlukIyilesen,
            txtDetayToplamVaka, txtDetayToplamOlum, txtDetayToplamIyilesen,
            txtDetayAgirHasta, ulkebaslik, txtUlkeTestSayisi,txtGuncellenme;

    Intent gelenIntent;
    IstatistikFragment istatistikFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istatistik_detaylar);

        txtDetayGunlukVaka = findViewById(R.id.txtDetayGunlukVaka);
        txtDetayGunlukIyilesen = findViewById(R.id.txtDetayGunlukIyilesme);
        txtDetayGunlukOlum = findViewById(R.id.txtDetayGunlukOlum);
        txtDetayToplamOlum = findViewById(R.id.txtDetayToplamOlum);
        txtDetayToplamIyilesen = findViewById(R.id.txtDetayToplamIyilesen);
        txtDetayToplamVaka = findViewById(R.id.txtDetayToplamVaka);
        txtDetayAgirHasta = findViewById(R.id.txtDetayAgirHasta);
        ulkebaslik = findViewById(R.id.ulkebaslik);
        txtUlkeTestSayisi = findViewById(R.id.txtTestSayisi);
        txtGuncellenme = findViewById(R.id.txtDetaylarGuncellenme);

        Intent gelenIntent = getIntent();

        //çagırıyoruz
        String ulkeAdi = gelenIntent.getStringExtra("ulkeadi");
        String toplamVaka = gelenIntent.getStringExtra("toplamvaka");
        String toplamOlum = gelenIntent.getStringExtra("toplamolum");
        String toplamIyilesen = gelenIntent.getStringExtra("toplamiyilesen");
        String gunlukVaka = gelenIntent.getStringExtra("gunlukvaka");
        String gunlukOlum = gelenIntent.getStringExtra("gunlukolum");
        String gunlukIyilesen = gelenIntent.getStringExtra("gunlukiyilesen");
        String agirHasta = gelenIntent.getStringExtra("agirhasta");
        String testSayisi = gelenIntent.getStringExtra("testsayisi");
        String guncellenmeTarihi = gelenIntent.getStringExtra("guncellenmetarihi");

        //yazıyoruz
        ulkebaslik.setText(ulkeAdi);
        txtDetayToplamVaka.setText(toplamVaka);
        txtDetayToplamOlum.setText(toplamOlum);
        txtDetayToplamIyilesen.setText(toplamIyilesen);
        txtDetayGunlukVaka.setText(gunlukVaka);
        txtDetayGunlukOlum.setText(gunlukOlum);
        txtDetayGunlukIyilesen.setText(gunlukIyilesen);
        txtDetayAgirHasta.setText(agirHasta);
        txtUlkeTestSayisi.setText(testSayisi);
        txtGuncellenme.setText(guncellenmeTarihi);

    }


}