package br.com.dagnese.leituradeetiquetas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.dagnese.leituradeetiquetas.R;
import br.com.dagnese.plune.PluneServices;
import br.com.dagnese.plune.model.DagneseFabricacao;
import br.com.dagnese.plune.model.DagneseFabricacaoChecagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private static PluneServices service;
    private static TextView lblQtdApontar;
    private static TextView lblQtdLiberada;
    private static TextView idObra;
    private static TextView idPeca;
    private static TextView nomeObra;
    private static Button scan;
    private static ProgressBar progressBar;
    private static EditText qtdApontar;
    private static Button enviar;
    private static TextView qtdLiberada;
    private static Button btn_adicionar;
    private static Button btn_remover;
    private static boolean firstRun = true;
    public static Response<DagneseFabricacao> fabricacaoDados;
    public static Response<DagneseFabricacaoChecagem> fabricacaoChecagemDados;
    private static String tipoScan, resultScan;
    private long expireTime = 0;
    private String activeUser;
    private int loginExpireSecs = 10;

    public void setActiveUser(String user) {
        this.activeUser = user;
        expireTime = System.currentTimeMillis() + (loginExpireSecs * 1000);
    }

    public String getActiveUser() {
        return this.activeUser;
    }

    public boolean isUserActive() {
        long currentTime = System.currentTimeMillis();
        if (expireTime <= 0 || currentTime >= expireTime) {
            return false;
        } else {
            expireTime = currentTime + (loginExpireSecs * 1000);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                resultScan = data.getStringExtra("leitura");
                tipoScan = data.getStringExtra("tipo");
                if (tipoScan.equals("0") && (!isUserActive() || !getActiveUser().equals(resultScan) || getActiveUser().equals(null))) {
                    setActiveUser(resultScan);
                    Toast.makeText(getApplicationContext(), "Usuário " + getActiveUser() + " registrado", Toast.LENGTH_LONG).show();
                    iniciarScan();
                }
                else if (tipoScan.equals("1") && isUserActive()) {
                    int fabricacaoId = Integer.parseInt(resultScan.split("\\.")[0]);
                    int qtdEtiqueta = Integer.parseInt(resultScan.split("\\.")[1]);
                    getInfoFabricacao(fabricacaoId, qtdEtiqueta);
                } else if (!isUserActive()) {
                    Toast.makeText(getApplicationContext(), "Favor ler o crachá", Toast.LENGTH_LONG).show();
                    iniciarScan();
                } else {
                    Toast.makeText(getApplicationContext(), "Leitura inválida", Toast.LENGTH_LONG).show();
                    iniciarScan();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                hideLoader();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Context contexto = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idPeca = (TextView) findViewById(R.id.idPeca);
        idObra = (TextView) findViewById(R.id.idObra);
        nomeObra = (TextView) findViewById(R.id.nomeObra);
        lblQtdApontar = (TextView) findViewById(R.id.textQtdApontar);
        lblQtdLiberada = (TextView) findViewById(R.id.textQtdLiberada);

        qtdLiberada = (TextView) findViewById(R.id.qtdLiberada);
        qtdApontar = (EditText) findViewById(R.id.qtdApontar);

        btn_adicionar = (Button) findViewById(R.id.apontarMais);
        btn_remover = (Button) findViewById(R.id.apontarMenos);
        scan = (Button) findViewById(R.id.ler);
        enviar = (Button) findViewById(R.id.enviar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        if (firstRun) {
            firstRun = false;
            iniciarScan();
        }

        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_remover.setEnabled(true);
                int quantidade = Integer.parseInt(qtdApontar.getText().toString());
                qtdApontar.setText(String.valueOf(Integer.parseInt(qtdApontar.getText().toString()) + 1));
            }
        });

        btn_remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtdApontar.setText(String.valueOf(Integer.parseInt(qtdApontar.getText().toString()) - 1));
                if (Integer.parseInt(qtdApontar.getText().toString()) > 1) {
                    btn_remover.setEnabled(true);
                } else {
                    btn_remover.setEnabled(false);
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarScan();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoader();
                insertFabricacaoChecagem(
                        fabricacaoDados.body().getData().getRow().get(0).getCompanyId().getValue(),
                        fabricacaoDados.body().getData().getRow().get(0).getObraId().getValue(),
                        fabricacaoDados.body().getData().getRow().get(0).getEtapaId().getValue(),
                        fabricacaoDados.body().getData().getRow().get(0).getDesenhoId().getValue(),
                        fabricacaoDados.body().getData().getRow().get(0).getPecaId().getValue(),
                        fabricacaoDados.body().getData().getRow().get(0).getId().getValue(),
                        Integer.parseInt(qtdApontar.getText().toString()),
                        "J",
                        resultScan
                );
            }
        });
    }

    private void showLoader() {
        idPeca.setVisibility(View.INVISIBLE);
        idObra.setVisibility(View.INVISIBLE);
        nomeObra.setVisibility(View.INVISIBLE);
        qtdLiberada.setVisibility(View.INVISIBLE);
        qtdApontar.setVisibility(View.INVISIBLE);
        lblQtdLiberada.setVisibility(View.INVISIBLE);
        lblQtdApontar.setVisibility(View.INVISIBLE);
        btn_remover.setVisibility(View.INVISIBLE);
        btn_adicionar.setVisibility(View.INVISIBLE);
        enviar.setVisibility(View.INVISIBLE);
        scan.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        idPeca.setVisibility(View.VISIBLE);
        idObra.setVisibility(View.VISIBLE);
        nomeObra.setVisibility(View.VISIBLE);
        qtdLiberada.setVisibility(View.VISIBLE);
        qtdApontar.setVisibility(View.VISIBLE);
        lblQtdLiberada.setVisibility(View.VISIBLE);
        lblQtdApontar.setVisibility(View.VISIBLE);
        btn_remover.setVisibility(View.VISIBLE);
        btn_adicionar.setVisibility(View.VISIBLE);
        enviar.setVisibility(View.VISIBLE);
        scan.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void iniciarScan() {
        showLoader();
        Intent startScan = new Intent(this, CameraActivity.class);
        startActivityForResult(startScan, 1);
    }

    public void insertFabricacaoChecagem(int companyId, int obraId, int etapaId, String desenhoId, String pecaId, int fabricacaoId, int quantidadePacote, String setorId, String codigoBarras) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PluneServices.URL_FABRICACAO_CHECAGEM)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(PluneServices. class);
        Call<DagneseFabricacaoChecagem> pluneCall = service.insertFabricacaoChecagem(companyId, obraId, etapaId, desenhoId, pecaId, fabricacaoId, quantidadePacote, setorId, codigoBarras);
        pluneCall.enqueue(new Callback<DagneseFabricacaoChecagem>() {
            @Override
            public void onResponse(Call<DagneseFabricacaoChecagem> pluneCall, Response<DagneseFabricacaoChecagem> response) {
                hideLoader();
                Toast.makeText(getApplicationContext(), "Sucesso!", Toast.LENGTH_LONG).show();
                iniciarScan();
            }

            @Override
            public void onFailure(Call<DagneseFabricacaoChecagem> pluneCall, Throwable t) {
                hideLoader();
                if (t instanceof IOException) {
                    Log.e("Sem internet", t.toString());
                    Toast.makeText(getApplicationContext(), "Sem internet", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("Falha na leitura", t.toString());
                    Toast.makeText(getApplicationContext(), "Falha na leitura", Toast.LENGTH_LONG).show();
                }
                iniciarScan();
            }
        });
    }

    public void getInfoFabricacao(int fabricacaoId, final int qtdEtiqueta) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PluneServices.URL_FABRICACAO)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(PluneServices. class);
        Call<DagneseFabricacao> pluneCall = service.browseFabricacao(fabricacaoId);

        pluneCall.enqueue(new Callback<DagneseFabricacao>() {
            @Override
            public void onResponse(Call<DagneseFabricacao> pluneCall, Response<DagneseFabricacao> response) {
                fabricacaoDados = response;
                idPeca.setText(response.body().getData().getRow().get(0).getPecaId().getResolved());
                idObra.setText(String.valueOf(response.body().getData().getRow().get(0).getObraId().getValue()));
                nomeObra.setText(response.body().getData().getRow().get(0).getObraApelido().getResolved());
                qtdLiberada.setText(response.body().getData().getRow().get(0).getQuantLib().getResolved());
                qtdApontar.setText(String.valueOf(qtdEtiqueta));
                if (Integer.parseInt(String.valueOf(qtdEtiqueta)) == 1) btn_remover.setEnabled(false);
                hideLoader();
            }

            @Override
            public void onFailure(Call<DagneseFabricacao> pluneCall, Throwable t) {
                hideLoader();
                if (t instanceof IOException) {
                    Log.e("Sem internet", t.toString());
                    Toast.makeText(getApplicationContext(), "Sem internet", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("Falha na leitura", t.toString());
                    Toast.makeText(getApplicationContext(), "Falha na leitura", Toast.LENGTH_LONG).show();
                }
                iniciarScan();
            }
        });
    }
}