package br.com.javamedicalhealth.conversordesolucoes;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.javamedicalhealth.conversordesolucoes.conversor.CalculoOsmolaridade;
import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

public class MainActivity extends AppCompatActivity {

    //para saber o posicionamento
    String posicao = "";
    //fragmentos
    FragmentPrescricao prescricao = new FragmentPrescricao();
    FragmentExistente existente = new FragmentExistente();
    FragmentAmpolas ampolas = new FragmentAmpolas();
    FragmentBotao botao = new FragmentBotao();
    FragmentResultado resultado = new FragmentResultado();

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

        Log.i("NACL", CalculoOsmolaridade.getNaCl().toString());
        Log.i("GICOSE", CalculoOsmolaridade.getG().toString());

        CalculoOsmolaridade calculoOsmolaridade = new CalculoOsmolaridade();
        Log.i("RESULTADO - SF 0,9", calculoOsmolaridade.calculaOsmolaridade(0.9, "SF"));
        Log.i("RESULTADO - SF 0,45", calculoOsmolaridade.calculaOsmolaridade(0.45, "SF"));
        Log.i("RESULTADO - G 5", calculoOsmolaridade.calculaOsmolaridade(5.0, "G"));
        Log.i("RESULTADO - G 10", calculoOsmolaridade.calculaOsmolaridade(10.0, "G"));

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
                    .addToBackStack("fragPrescricao")
                    .commit();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String tag = getSupportFragmentManager().getBackStackEntryAt(
                getSupportFragmentManager().getBackStackEntryCount() -1).getName();
        if(tag.equals("")){
            inflaFragmentos();
        }else{
            reInflaFragments();
        }

    }

    public void chamaResultado() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragPrescricao, resultado, "fragResultado")
        .addToBackStack("fragResultado");
        ft.commit();
    }

    public void apagaFragments() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    public void reInflaFragments(){
        //verifico se estou no modo landscap ou portrait
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            posicao = "landscape";
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            posicao = "portrait";
        }

        if (posicao.equals("portrait")) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft
                .replace(R.id.fragPrescricao, prescricao, "fragPrescricao")
                .add(R.id.fragExistente, existente, "fragExistente")
                .add(R.id.fragAmpola, ampolas, "fragAmpolas")
                .add(R.id.fragBotao, botao, "fragBotao")
                .addToBackStack(null)
                .commit();
        }else{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft
                    .replace(R.id.fragPrescricao, prescricao, "fragPrescricao")
                    .add(R.id.fragExistente, existente, "fragExistente")
                    .addToBackStack("fragPrescricao")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        // initialize variables
        FragmentManager fm = getSupportFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
        int i = fm.getBackStackEntryCount();

        // check to see if stack is empty
        if (i > 0) {

            String tag = getSupportFragmentManager().getBackStackEntryAt(
                    getSupportFragmentManager().getBackStackEntryCount() -1).getName();

            if(tag.equals("fragResultado")){
                reInflaFragments();
            }
        }

    }
}
