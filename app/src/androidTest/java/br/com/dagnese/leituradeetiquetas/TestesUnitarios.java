package br.com.dagnese.leituradeetiquetas;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLOutput;

import static org.junit.Assert.*;

public class TestesUnitarios {

    @Rule
    public IntentsTestRule<CameraActivity> intentsTestRule = new IntentsTestRule<>(CameraActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> rule  = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getInfoFabricacao_testQuantidadeLiberada() throws Exception {
        MainActivity activity = rule.getActivity();
        activity.getInfoFabricacao(227101, 0);
        Thread.sleep(6000);
        Integer qtdRecebida = activity.fabricacaoDados.body().getData().getRow().get(0).getQuantLib().getValue();
        Log.d("QTDRECEBIDA", qtdRecebida.toString());
        assertEquals(60, activity.fabricacaoDados.body().getData().getRow().get(0).getQuantLib().getValue());
    }

    @Test
    public void insertFabricacaoChecagem_testDesenho() throws Exception {
        MainActivity activity = rule.getActivity();
        activity.insertFabricacaoChecagem(74, 9898, 9, "F1447", "CB1447A", 227101, 60,"J", "227101.60.0");
        Thread.sleep(6000);
        String desenhoRecebido = activity.fabricacaoDados.body().getData().getRow().get(0).getDesenhoId().getValue();
        Log.d("DESENHO", desenhoRecebido);
        assertEquals("F1447", activity.fabricacaoDados.body().getData().getRow().get(0).getDesenhoId().getValue());
    }

    @Test
    public void parse() {
        Intent resultData = new Intent();
        resultData.putExtra("tipo", "1");
        resultData.putExtra("leitura", "227102.2.0");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }
}