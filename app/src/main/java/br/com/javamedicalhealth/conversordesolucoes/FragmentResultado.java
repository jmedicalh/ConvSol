package br.com.javamedicalhealth.conversordesolucoes;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.javamedicalhealth.conversordesolucoes.conversor.CalculoConversao;
import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

/**
 * Created by isaac on 10/10/16.
 */

public class FragmentResultado extends Fragment{
    TextView txtLinha1;
    TextView txtLinha2;

    Button button;

    //para o calculo
    CalculoConversao calcular = new CalculoConversao();

    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_resultado, container, false);
        return view;
    }

    //para propaganda rodar corretamente
    //
    @Override
    public void onResume() {
        super.onResume();
        if(mAdView != null){
            mAdView.resume();
        }
    }

    @Override
    public void onPause() {
        if(mAdView != null){
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if(mAdView != null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
    //fim da propaganda

    //apos a criação e sabendeo que poderei trabalhar com resultados

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        txtLinha1 = (TextView)getActivity().findViewById(R.id.txtMenssagem);
        txtLinha2 = (TextView)getActivity().findViewById(R.id.txtMenssagem2);
        button = (Button) getActivity().findViewById(R.id.cmdOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).apagaFragments();
                ((MainActivity)getActivity()).reInflaFragments();
            }
        });
        //trabalhando com a propaganda
        mAdView = (AdView)view.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("04307137381340F4DFFFC5DDFBB5C8E3")
                .addTestDevice("435FD46EBF905EF6B3A9106B76E6B757")
                .build();
        mAdView.loadAd(adRequest);
        /*fim da inicialização da propaganda*/

        //calculo o soro
        calcularSoro();
    }

    private void calcularSoro(){
        calcular.setPM(ModelSolucao.getInstance().getPorcentPrescrito());
        calcular.setAmp(ModelSolucao.getInstance().getPorcentAmpola());
        calcular.setExist(ModelSolucao.getInstance().getPorcentExistente());
        //verifico volumes
        int volJogarFora;
        int numBolsas;
        int volPrescrito = ModelSolucao.getInstance().getVolumePrescrito();
        int volExistente = ModelSolucao.getInstance().getVolumeExistente();
        if(volPrescrito < volExistente){
            volJogarFora = volExistente - volPrescrito;
            calcular.setVolume(volPrescrito);
            numBolsas = 1;
        }else{
            volJogarFora = 0;
            calcular.setVolume(volExistente);
            numBolsas = volPrescrito /volExistente;
        }
        Resources resources = getResources();
        String [] tipoAmpola = resources.getStringArray(R.array.ampola);

        setResultado(calcular.Calcula(), tipoAmpola[ModelSolucao.getInstance().getTipoAmpola()], Integer.toString(numBolsas), volJogarFora);

    }

    public void setResultado(String valor, String strTipoAmpola, String frascos, int volJogarFora){
        String msg = getView().getResources().getString(R.string.extrair);
        if(volJogarFora > 0){
            float f = (float) volJogarFora;
            f = f +  Float.parseFloat(valor);
            msg = msg.replace("$", Float.toString(f));
        }else {
            msg = msg.replace("$", valor);
        }
        String msg2 = getView().getResources().getString(R.string.incluir);
        msg2 = msg2.replace("$", valor);
        msg2 = msg2.replace("%", strTipoAmpola);
        msg2 = msg2.replace("#", frascos);
        txtLinha1.setText(msg);
        txtLinha2.setText(msg2);
    }

}
