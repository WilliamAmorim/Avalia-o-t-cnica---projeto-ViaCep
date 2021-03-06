package br.com.projetoviacep.api;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.com.projetoviacep.model.CepModel;

/**
 * Classe responsavel por fazer a comunicação com o site ViaCEP
 */
public class ViaCep extends AsyncTask<Void,Void, CepModel> {

    private final String CEP;

    public ViaCep(String cep) {
        this.CEP = cep;
    }


    @Override
    protected CepModel doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("https://viacep.com.br/ws/"+ CEP +"/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                resposta.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(resposta.toString(), CepModel.class);
    }
}

