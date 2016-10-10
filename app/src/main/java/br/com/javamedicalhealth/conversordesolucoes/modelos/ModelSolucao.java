package br.com.javamedicalhealth.conversordesolucoes.modelos;

/**
 * Created by isaac on 06/10/16.
 */


public class ModelSolucao {

    private static ModelSolucao mInstance = null;

    //prescrito
    int tipoPrescrito;
    float porcentPrescrito;
    int volumePrescrito;
    //existente
    int tipoExistente;
    float porcentExistente;
    int volumeExistente;
    //ampola
    int tipoAmpola;
    float porcentAmpola;
    int volumeAmpola;

    private ModelSolucao(){
        tipoPrescrito = 0;
        porcentPrescrito = 0f;
        volumePrescrito = 0;
        tipoExistente = 0;
        porcentExistente = 0f;
        volumeExistente = 0;
        tipoAmpola = 0;
        porcentAmpola = 0f;
        volumeAmpola = 0;
    }

    public static synchronized ModelSolucao getInstance (){
        if(mInstance == null){
            mInstance = new ModelSolucao();
        }
        return  mInstance;
    }

    public int getVolumeAmpola() {
        return volumeAmpola;
    }

    public void setVolumeAmpola(int volumeAmpola) {
        this.volumeAmpola = volumeAmpola;
    }

    public int getTipoPrescrito() {
        return tipoPrescrito;
    }

    public void setTipoPrescrito(int tipoPrescrito) {
        this.tipoPrescrito = tipoPrescrito;
    }

    public float getPorcentPrescrito() {
        return porcentPrescrito;
    }

    public void setPorcentPrescrito(float porcentPrescrito) {
        this.porcentPrescrito = porcentPrescrito;
    }

    public int getVolumePrescrito() {
        return volumePrescrito;
    }

    public void setVolumePrescrito(int volumePrescrito) {
        this.volumePrescrito = volumePrescrito;
    }

    public int getTipoExistente() {
        return tipoExistente;
    }

    public void setTipoExistente(int tipoExistente) {
        this.tipoExistente = tipoExistente;
    }

    public float getPorcentExistente() {
        return porcentExistente;
    }

    public void setPorcentExistente(float porcentExistente) {
        this.porcentExistente = porcentExistente;
    }

    public int getVolumeExistente() {
        return volumeExistente;
    }

    public void setVolumeExistente(int volumeExistente) {
        this.volumeExistente = volumeExistente;
    }

    public int getTipoAmpola() {
        return tipoAmpola;
    }

    public void setTipoAmpola(int tipoAmpola) {
        this.tipoAmpola = tipoAmpola;
    }

    public float getPorcentAmpola() {
        return porcentAmpola;
    }

    public void setPorcentAmpola(float porcentAmpola) {
        this.porcentAmpola = porcentAmpola;
    }
}
