package br.com.javamedicalhealth.conversordesolucoes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

import static android.content.Context.MODE_PRIVATE;
import static br.com.javamedicalhealth.conversordesolucoes.MainActivity.MY_PREFS_NAME;

/**
 * Created by isaac on 06/10/16.
 */

public class FragmentAmpolas extends Fragment {

    private TextView txtPorcento;
    private TextView txtVolume;
    private Spinner spnTipo;

    SharedPreferences.Editor preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ampolas, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        preferences = this.getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        txtPorcento = (TextView)getActivity().findViewById(R.id.txtPorcentoAmpola);
        txtVolume = (TextView)getActivity().findViewById(R.id.txtVolumeAmpola);
        spnTipo = (Spinner)getActivity().findViewById(R.id.spnAmpola);

        super.onActivityCreated(savedInstanceState);

        //verificando caso tenha algo guardado para mudança de posicionamento
        if(savedInstanceState != null){
            txtPorcento.setText(String.valueOf( savedInstanceState.getSerializable("txtPorcento")));
            txtVolume.setText(String.valueOf(savedInstanceState.getSerializable("txtVolume")));
            spnTipo.setSelection(Integer.parseInt(savedInstanceState.getSerializable("spnTipo").toString()));
        } else {
            Context context = getActivity();
            SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String val = String.valueOf(prefs.getFloat("porcentAmpola", 0f));
            txtPorcento.setText(val);
            val =  String.valueOf(prefs.getInt("volumeAmpola", 0));
            txtVolume.setText(val);
            int x = prefs.getInt("tipoAmpola", 0);
            spnTipo.setSelection(x);
        }


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
                    }else {
                        v += "0";
                    }
                    txtPorcento.setText(v);
                    salvaValores();
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
                    salvaValores();
                }
            }
        });

        spnTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                salvaValores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("spnTipo", spnTipo.getSelectedItemPosition());
        outState.putSerializable("txtVolume", txtVolume.getText().toString());
        outState.putSerializable("txtPorcento", txtPorcento.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        salvaValores();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        salvaValores();
        super.onDestroy();
    }

    private void salvaValores(){
        preferences.putFloat("porcentAmpola", Float.parseFloat( txtPorcento.getText().toString()));
        preferences.putInt("volumeAmpola", Integer.parseInt(txtVolume.getText().toString()));
        preferences.putInt("tipoAmpola", spnTipo.getSelectedItemPosition());
        preferences.commit();
    }
}
