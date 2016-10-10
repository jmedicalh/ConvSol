package br.com.javamedicalhealth.conversordesolucoes.modelos;

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
import com.google.android.gms.common.SignInButton;

import br.com.javamedicalhealth.conversordesolucoes.MainActivity;
import br.com.javamedicalhealth.conversordesolucoes.R;

/**
 * Created by isaac on 10/10/16.
 */

public class FragmentResultado extends Fragment{
    TextView txtLinha1;
    TextView txtLinha2;

    Button button;

    String valor = "";
    String strTipoAmpola = "";

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
                //.addTestDevice("51F83EC4EB4E675EC829C36DF863C8A1")
                .addTestDevice("435FD46EBF905EF6B3A9106B76E6B757")
                .build();
        mAdView.loadAd(adRequest);
        /*fim da inicialização da propaganda*/
    }

    public void setResultado(){
        String msg = getView().getResources().getString(R.string.extrair);
        msg = msg.replace("$", valor);
        String msg2 = getView().getResources().getString(R.string.incluir);
        msg2 = msg2.replace("$", valor);
        msg2 = msg2.replace("%", strTipoAmpola);
        txtLinha1.setText(msg);
        txtLinha2.setText(msg2);
    }

}
