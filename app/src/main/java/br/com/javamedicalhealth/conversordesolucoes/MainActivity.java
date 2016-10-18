package br.com.javamedicalhealth.conversordesolucoes;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

public class MainActivity extends AppCompatActivity {

    //fragmentos
    FragmentPrescricao prescricao = new FragmentPrescricao();
    FragmentExistente existente = new FragmentExistente();
    FragmentAmpolas ampolas = new FragmentAmpolas();
    FragmentBotao botao = new FragmentBotao();
    FragmentResultado resultado = new FragmentResultado();

    //modelo
    private ModelSolucao modelSolucao;
    //propaganda
    private AdView mAdView;
    //para SharedPreferences
    public static final String MY_PREFS_NAME = "PrefsFile";
    public SharedPreferences.Editor preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        if(savedInstanceState == null){
            inflaFragmentos();
        }
        //para propaganda trabalhar
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("51F83EC4EB4E675EC829C36DF863C8A1")
                .addTestDevice("435FD46EBF905EF6B3A9106B76E6B757")
                .addTestDevice("04307137381340F4DFFFC5DDFBB5C8E3")
                .build();
        mAdView.loadAd(adRequest);
        /*fim da inicialização da propaganda*/
        //caso nao tenha dado la dentro gero a sharedpreferences


    }

    //para propaganda rodar corretamente
    //
    @Override
    protected void onResume() {
        super.onResume();
        if(mAdView != null){
            mAdView.resume();
        }
    }

    @Override
    protected void onPause() {
        if(mAdView != null){
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mAdView != null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
    //fim das necessidades das propagandas

    private void inflaFragmentos() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft
                .add(R.id.fragPrescricao, prescricao, "fragPrescricao")
                .add(R.id.fragExistente, existente, "fragExistente")
                .add(R.id.fragAmpola, ampolas, "fragAmpolas")
                .add(R.id.fragBotao, botao, "fragBotao")
                .addToBackStack(null)
                .commit();

        setaValoresNasPreferencias();
    }



    public void chamaResultado() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragPrescricao, resultado);
        ft.commit();
    }

    public void apagaFragments() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    public void reInflaFragments(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft
                .replace(R.id.fragPrescricao, prescricao, "fragPrescricao")
                .add(R.id.fragExistente, existente, "fragExistente")
                .add(R.id.fragAmpola, ampolas, "fragAmpolas")
                .add(R.id.fragBotao, botao, "fragBotao")
                .addToBackStack(null)
                .commit();
    }

    public void setaValoresNasPreferencias(){

        preferences.putInt("tipoPrescrito", 0);
        preferences.putFloat("porcentPrescrito", 0f);
        preferences.putInt("volumePrescrito", 0);
        preferences.putInt("tipoExistente", 0);
        preferences.putFloat("porcentExistente", 0f);
        preferences.putInt("volumeExistente",0);
        preferences.putInt("tipoAmpola",0);
        preferences.putFloat("porcentAmpola",0f);
        preferences.putInt("volumeAmpola",0);
        preferences.commit();
    }
}
