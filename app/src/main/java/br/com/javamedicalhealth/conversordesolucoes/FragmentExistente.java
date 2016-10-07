package br.com.javamedicalhealth.conversordesolucoes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by isaac on 06/10/16.
 */

public class FragmentExistente extends Fragment {

    private TextView txtPorcento;
    private TextView txtVolume;
    private Spinner spnTipo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_existente, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtPorcento = (TextView)getActivity().findViewById(R.id.txtPorcentoExistente);
        txtVolume = (TextView)getActivity().findViewById(R.id.txtVolumeExistente);
        spnTipo = (Spinner)getActivity().findViewById(R.id.spnExistente);

    }

}