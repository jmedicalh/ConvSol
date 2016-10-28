package br.com.javamedicalhealth.conversordesolucoes;

import br.com.javamedicalhealth.conversordesolucoes.conversor.CalculoOsmolaridade;

/**
 * Created by gaijin on 20/10/2016.
 */

public class CalculoOsmolaridadeTeste {
    CalculoOsmolaridade calculo = new CalculoOsmolaridade();
    public void main (String args[]){
        exibeValoresVariaveis();

    }

    private void exibeValoresVariaveis(){
        System.out.println(CalculoOsmolaridade.getG());
        System.out.println(CalculoOsmolaridade.getNaCl());
    }

}
