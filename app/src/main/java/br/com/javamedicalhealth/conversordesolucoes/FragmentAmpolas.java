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

public class FragmentAmpolas extends Fragment {

    private TextView txtPorcento;
    private TextView txtVolume;
    private Spinner spnTipo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ampolas, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtPorcento = (TextView)getActivity().findViewById(R.id.txtPorcentoAmpola);
        txtVolume = (TextView)getActivity().findViewById(R.id.txtVolumeAmpola);
        spnTipo = (Spinner)getActivity().findViewById(R.id.spnAmpola);
        //carrego os valores devolta nos campos
        carregaValores();
        //validações dos campos onleave
        txtPorcento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(txtPorcento.getText().length() < 1){
                        txtPorcento.setText("0");
                    }
                    ModelSolucao.getInstance().setPorcentAmpola(Float.parseFloat(txtPorcento.getText().toString()));
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
                    ModelSolucao.getInstance().setVolumeAmpola(Integer.parseInt(txtVolume.getText().toString()));
                }
            }
        });

    }

    /**
     * Metodo em que seto os valores contidos no modelo devolta nos campos
     */
    public void carregaValores(){
        txtPorcento.setText(String.valueOf( ModelSolucao.getInstance().getPorcentAmpola()));
        txtVolume.setText(String.valueOf(ModelSolucao.getInstance().getVolumeAmpola()));
        spnTipo.setSelection(ModelSolucao.getInstance().getTipoAmpola());
    }
}
