package br.com.javamedicalhealth.conversordesolucoes.conversor;

import java.text.DecimalFormat;

/**
 * Created by gaijin on 20/10/2016.
 */

public class CalculoOsmolaridade {
    private static final Double NaCl = 22.98976928 + 35.45;
    private static final Double G = (12.011 * 6) + (1.008 * 12) + (15.999 * 6);


    public static Double getNaCl() {
        return NaCl;
    }

    public static Double getG() {
        return G;
    }


    public String calculaOsmolaridade(Double porcentagem, String tipo){
        
        double resultado = 0f;

        if(tipo.equals("SF"))
            resultado = ( (porcentagem / 0.100) *( 1 / NaCl)) * 2 * 1000;
        else if (tipo.equals("G")  || tipo.equals("SG"))
            resultado = ((porcentagem / 0.100) * (1 / G)) * 1 * 1000;

        DecimalFormat formato = new DecimalFormat("#.##");
        resultado = Double.valueOf(formato.format(resultado));
        return String.valueOf(resultado);
    }
}
