package br.com.heiderlopes.mundoapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ligar(View v) {
        final String credentials = "heiderlopes:android";
        final String basicAuthToken = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        LedAPI ledAPI = ServiceGeneratorBasicAuth.createService(LedAPI.class, basicAuthToken);
        Call<Void> call = ledAPI.ligar(1);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 401:
                        Toast.makeText(getApplicationContext(), "Usuario nao autorizado", Toast.LENGTH_SHORT).show();
                        break;

                    case 200:
                        Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
