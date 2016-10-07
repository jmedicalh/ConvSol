package br.com.javamedicalhealth.conversordesolucoes.conversor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by isaac on 07/10/16.
 */

public class CalculoConversao {
    private float pm;
    private float amp;
    private float exist;
    private float volume;
    private float result;
    //setters
    public void setPM(float pm){ this.pm = pm; }
    public void setAmp(float amp){ this.amp = amp; }
    public void setExist(float exist){ this.exist = exist; }
    public void setVolume(float volume){ this.volume = volume; }
    //fim setters
    public String Calcula(){
        BigDecimal bd1, bd2;
        bd1 = new BigDecimal(((this.pm - this.exist) / (this.amp - this.exist)) * this.volume);
        bd2 = bd1.setScale(2, RoundingMode.HALF_EVEN);
        return bd2.toString();
    }
}
