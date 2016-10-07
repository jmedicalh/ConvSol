package br.com.javamedicalhealth.conversordesolucoes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

/**
 * Created by isaac on 06/10/16.
 */

public class FragmentPrescricao extends Fragment {

    private TextView txtPorcento;
    private TextView txtVolume;
    private Spinner spnTipo;

    ModelSolucao modelo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_prescrito, container, false);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtPorcento = (TextView)getActivity().findViewById(R.id.txtPorcentoPrescrito);
        txtVolume = (TextView)getActivity().findViewById(R.id.txtVolumePrescrito);
        spnTipo = (Spinner)getActivity().findViewById(R.id.spnPrescrito);

    }

    public void setaValores(float porcento, int volume, int tipo){
        int m = ModelSolucao.getInstance().getVolumeAmpola();

        ModelSolucao.getInstance().setVolumeAmpola(10);

        m = ModelSolucao.getInstance().getVolumeAmpola();
    }
}
