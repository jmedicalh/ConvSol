package br.com.javamedicalhealth.conversordesolucoes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by isaac on 06/10/16.
 */

public class FragmentBotao extends Fragment {

    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_botao,container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn = (Button)getActivity().findViewById(R.id.cmdCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacular(view);
            }
        });

    }

    public void cacular(View view){
        //apago os fragmentos
        ((MainActivity)getActivity()).apagaFragments();
        //chamo metodo de criação do fragmento de resultado
        ((MainActivity)getActivity()).chamaResultado();
    }

}
