package br.com.javamedicalhealth.conversordesolucoes;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

public class MainActivity extends AppCompatActivity {

    //fragmentos
    FragmentPrescricao prescricao = new FragmentPrescricao();
    FragmentExistente existente = new FragmentExistente();
    FragmentAmpolas ampolas = new FragmentAmpolas();
    FragmentBotao botao = new FragmentBotao();

    //modelo
    ModelSolucao modelo;


    //propaganda
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //inicializo o singleton;
        //modelo = ModelSolucao().getInstance();
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
    }
}
