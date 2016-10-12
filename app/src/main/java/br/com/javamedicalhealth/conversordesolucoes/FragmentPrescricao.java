package br.com.javamedicalhealth.conversordesolucoes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
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

        //carrego os valores salvos
        carregaValores();
        //validações dos campos onleave
        txtPorcento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(txtPorcento.getText().length() < 1){
                        txtPorcento.setText("0");
                    }
                    String v = txtPorcento.getText().toString();
                    if(v.indexOf(".") == 0){
                        v = "0" + v;
                        txtPorcento.setText(v);
                    }
                    ModelSolucao.getInstance().setPorcentPrescrito(Float.parseFloat(v));
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
                    ModelSolucao.getInstance().setVolumePrescrito(Integer.parseInt(txtVolume.getText().toString()));
                }
            }
        });

    }

    /**
     * Metodo em que seto os valores contidos no modelo devolta nos campos
     */
    public void carregaValores(){
        txtPorcento.setText(String.valueOf( ModelSolucao.getInstance().getPorcentPrescrito()));
        txtVolume.setText(String.valueOf(ModelSolucao.getInstance().getVolumePrescrito()));
        spnTipo.setSelection(ModelSolucao.getInstance().getTipoPrescrito());
    }



}
