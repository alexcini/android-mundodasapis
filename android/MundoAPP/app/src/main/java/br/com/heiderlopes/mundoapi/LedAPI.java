package br.com.heiderlopes.mundoapi;

import retrofit2.Call;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface LedAPI {

    @PATCH("/led/{ledID}/ligar")
    Call<Void> ligar(@Path("ledID") int ledId);
}
