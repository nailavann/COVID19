package com.example.covid19;

public class Ulke {
    private String ulkeAdi, ulkeResmi, ulkeToplamVaka,
            ulkeToplamOlum, ulkeToplamIyilesen, ulkeGunlukVaka,
            ulkeGunlukOlum, ulkeGunlukIyilesen, ulkeAgirHasta,
            ulkeTestSayisi,detaylarGuncellenmeTarihi;


    public Ulke(String ulkeAdi, String ulkeResmi, String ulkeToplamVaka, String ulkeToplamOlum, String ulkeToplamIyilesen, String ulkeGunlukVaka, String ulkeGunlukOlum, String ulkeGunlukIyilesen, String ulkeAgirHasta, String ulkeTestSayisi, String detaylarGuncellenmeTarihi) {
        this.ulkeAdi = ulkeAdi;
        this.ulkeResmi = ulkeResmi;
        this.ulkeToplamVaka = ulkeToplamVaka;
        this.ulkeToplamOlum = ulkeToplamOlum;
        this.ulkeToplamIyilesen = ulkeToplamIyilesen;
        this.ulkeGunlukVaka = ulkeGunlukVaka;
        this.ulkeGunlukOlum = ulkeGunlukOlum;
        this.ulkeGunlukIyilesen = ulkeGunlukIyilesen;
        this.ulkeAgirHasta = ulkeAgirHasta;
        this.ulkeTestSayisi = ulkeTestSayisi;
        this.detaylarGuncellenmeTarihi = detaylarGuncellenmeTarihi;
    }

    public  String getUlkeAdi() {
        return ulkeAdi;
    }


    public String getUlkeResmi() {
        return ulkeResmi;
    }


    public String getUlkeToplamVaka() {
        return ulkeToplamVaka;
    }


    public String getUlkeToplamOlum() {
        return ulkeToplamOlum;
    }


    public String getUlkeToplamIyilesen() {
        return ulkeToplamIyilesen;
    }


    public String getUlkeGunlukVaka() {
        return ulkeGunlukVaka;
    }


    public String getUlkeGunlukOlum() {
        return ulkeGunlukOlum;
    }


    public String getUlkeGunlukIyilesen() {
        return ulkeGunlukIyilesen;
    }


    public String getUlkeAgirHasta() {
        return ulkeAgirHasta;
    }

    public String getUlkeTestSayisi() {
        return ulkeTestSayisi;
    }

    public String getDetaylarGuncellenmeTarihi() {
        return detaylarGuncellenmeTarihi;
    }



}
