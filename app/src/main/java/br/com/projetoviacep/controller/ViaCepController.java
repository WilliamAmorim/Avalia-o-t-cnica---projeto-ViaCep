package br.com.projetoviacep.controller;

import android.content.Context;

import java.util.concurrent.ExecutionException;

import br.com.projetoviacep.api.ViaCep;
import br.com.projetoviacep.common.Utility;
import br.com.projetoviacep.dao.ViaCepCacheDAO;
import br.com.projetoviacep.model.CepModel;

/**
 * Classe responsavel pela regra de negócio
 */
public class ViaCepController {
    private CepModel cepModel = null;
    private Utility utility;
    private ViaCepCacheDAO cepCacheDAO;
    private ViaCep viaCep;

    public ViaCepController(Context context){
        this.cepCacheDAO = new ViaCepCacheDAO(context);
        this.utility = new Utility(context);
    }

    public CepModel buscarCEP(String cep){
        if(utility.verificarConexao()){
            if(!cep.trim().isEmpty()){
                if (cepCacheDAO.isCep(cep)) {
                    buscarCepCache(cep);
                } else {
                    buscarCepApi(cep);
                }
            }else{
                utility.showToast(utility.getString("preenchaCampos"));
            }
        }else{
            utility.showToast(utility.getString("SemConexãoInternet"));
        }
        return cepModel;
    }

    private void buscarCepCache(String cep){
        cepModel = cepCacheDAO.buscarCep(cep);
    }

    private void buscarCepApi(String cep){
        try {
            viaCep = new ViaCep(cep);
            cepModel = viaCep.execute().get();
            cepCacheDAO.novoCEP(cepModel);
            if(cepModel == null){
                utility.showToast(utility.getString("nenhumDado"));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
