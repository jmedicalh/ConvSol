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
        //recarrego os valores devolta nos campos
        carregaValores();

        //validações dos campos onleave
        txtPorcento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(txtPorcento.getText().length() < 1){
                        txtPorcento.setText("0");
                    }
                    ModelSolucao.getInstance().setPorcentExistente(Float.parseFloat(txtPorcento.getText().toString()));
                }
            }
        });

        txtVolume.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(txtVolume.getTextSize() < 1){
                        txtVolume.setText("0");
                    }
                    ModelSolucao.getInstance().setVolumeExistente(Integer.parseInt(txtVolume.getText().toString()));
                }
            }
        });

    }
    public void carregaValores(){
        txtPorcento.setText(String.valueOf( ModelSolucao.getInstance().getPorcentExistente()));
        txtVolume.setText(String.valueOf(ModelSolucao.getInstance().getVolumeExistente()));
        spnTipo.setSelection(ModelSolucao.getInstance().getTipoExistente());
    }
}