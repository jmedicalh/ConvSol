package br.com.javamedicalhealth.conversordesolucoes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

import br.com.javamedicalhealth.conversordesolucoes.conversor.CalculoOsmolaridade;
import br.com.javamedicalhealth.conversordesolucoes.modelos.ModelSolucao;

import static android.content.Context.MODE_PRIVATE;
import static br.com.javamedicalhealth.conversordesolucoes.MainActivity.MY_PREFS_NAME;

/**
 * Created by isaac on 06/10/16.
 */

public class FragmentPrescricao extends Fragment {

    private TextView txtPorcento;
    private TextView txtVolume;
    private Spinner spnTipo;

    SharedPreferences.Editor preferences;

    double valorPorcento = 0.0;
    String strValorPorcento = "";
    String strTipo= "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_prescrito, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        preferences = this.getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        txtPorcento = (TextView)getActivity().findViewById(R.id.txtPorcentoPrescrito);
        txtVolume = (TextView)getActivity().findViewById(R.id.txtVolumePrescrito);
        spnTipo = (Spinner)getActivity().findViewById(R.id.spnPrescrito);

        super.onActivityCreated(savedInstanceState);

        Resources resources = getResources();
        String [] tipos = resources.getStringArray(R.array.tipoSoro);

        if(savedInstanceState != null){
            txtPorcento.setText(String.valueOf(savedInstanceState.getSerializable("txtPorcento")));
            valorPorcento =  Double.valueOf (savedInstanceState.getSerializable("txtPorcento").toString());
            txtVolume.setText(String.valueOf(savedInstanceState.getSerializable("txtVolume")));
            spnTipo.setSelection(Integer.parseInt(savedInstanceState.getSerializable("spnTipo").toString()));
            strTipo = tipos[Integer.parseInt(savedInstanceState.getSerializable("spnTipo").toString())];
        } else {
            Context context = getActivity();
            SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

            txtPorcento.setText(String.valueOf(prefs.getFloat("porcentPrescrito", 0f)));
            valorPorcento = prefs.getFloat("porcentPrescrito", 0f);
            txtVolume.setText(String.valueOf(prefs.getInt("volumePrescrito", 0)));

            spnTipo.setSelection(prefs.getInt("tipoPrescrito", 0));
            strTipo = tipos[prefs.getInt("tipoPrescrito", 0)];
        }

        //validações dos campos onleave
        txtPorcento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    if(txtPorcento.getText().length() < 1){
                        txtPorcento.setText("0");
                    }
                    strValorPorcento = txtPorcento.getText().toString();
                    if(strValorPorcento.indexOf(".") == 0){
                        strValorPorcento = "0" + strValorPorcento;
                    }else if(strValorPorcento.endsWith(".")){
                        strValorPorcento += "0";
                    }
                    txtPorcento.setText(strValorPorcento);
                    salvaValores();
                    valorPorcento = Double.parseDouble(strValorPorcento);
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
                strTipo = adapterView.getSelectedItem().toString();
                salvaValores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        caculaOsmolaridade();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("txtPorcento", txtPorcento.getText().toString());
        outState.putSerializable("txtVolume", txtVolume.getText().toString());
        outState.putSerializable("spnTipo", spnTipo.getSelectedItemPosition());
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
        preferences.putFloat("porcentPrescrito", Float.parseFloat( txtPorcento.getText().toString()));
        preferences.putInt("volumePrescrito", Integer.parseInt(txtVolume.getText().toString()));
        preferences.putInt("tipoPrescrito", spnTipo.getSelectedItemPosition());
        preferences.commit();
    }
    private void caculaOsmolaridade(){
        Resources resources = getResources();
        String osmolaridade = resources.getString(R.string.osmolaridade);
        CalculoOsmolaridade calculoOsmolaridade = new CalculoOsmolaridade();
        String valor = calculoOsmolaridade.calculaOsmolaridade(valorPorcento, strTipo);
        TextView txtOsmolaridade = (TextView)getActivity().findViewById(R.id.txtOsmolaridadePrescrito);
        txtOsmolaridade.setText(osmolaridade + " é de " + valor);
    }
}
