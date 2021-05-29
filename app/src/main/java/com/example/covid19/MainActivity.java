package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
   //Arayüzü giriş
    private SmoothBottomBar bottomBar;
    private TextView txtBaslik;
    private ImageButton refreshBtn;
    //Fragment giriş
    private Fragment anasayfaFragment , istatistikFragment , semptomFragment;
    private Fragment aktifFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Arayüz binding
        bottomBar = findViewById(R.id.bottomBar);
        txtBaslik = findViewById(R.id.txtBaslik);
        refreshBtn = findViewById(R.id.refreshButton);

        txtBaslik.setText("Genel İstatistik");

        anasayfaFragment = new AnasayfaFragment();
        istatistikFragment = new IstatistikFragment();
        semptomFragment = new SemptomFragment();
        fragmentManager = getSupportFragmentManager();
        aktifFragment = anasayfaFragment;
        fragmentManager.beginTransaction().add(R.id.frameLayout,anasayfaFragment, "anasayfaFragment").commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout,istatistikFragment,"istatistikFragment").hide(istatistikFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout,semptomFragment,"semptomFragment").hide(semptomFragment).commit();

        //Refresh Button
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anasayfaFragment.onResume();
                istatistikFragment.onResume();
            }
        });

        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i){
                    //Anasayfa Fragment
                    case 0:
                        anasayfaFragmentIslem();
                        break;
                    //İstatistik Fragment
                    case 1:
                        istatistikFragmentIslem();
                        break;
                    //Semptom Fragment
                    case 2:
                        semptomFragmentIslem();
                        break;
                }
                return false;
            }
        });


    }

    private void anasayfaFragmentIslem(){
        txtBaslik.setText("Genel İstatistik");
        fragmentManager.beginTransaction().hide(aktifFragment).show(anasayfaFragment).commit();
        aktifFragment = anasayfaFragment;
    }
    private void istatistikFragmentIslem(){
        txtBaslik.setText("Detaylı İstatistik");
        fragmentManager.beginTransaction().hide(aktifFragment).show(istatistikFragment).commit();
        aktifFragment = istatistikFragment;
    }
    private void semptomFragmentIslem(){
        txtBaslik.setText("Semptomlar");
        fragmentManager.beginTransaction().hide(aktifFragment).show(semptomFragment).commit();
        aktifFragment = semptomFragment;
    }
}