package com.example.chatapp;


import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class Http extends AsyncTask<String, String, String> {
    ArrayList<Par> dados = null;
    private Http.Listener listener;

    interface Listener {
        void onResult(String resultado);
    }

    public void setListener(Http.Listener listener) {
        this.listener = listener;
    }

    public Http(ArrayList<Par> dados) {
        if (dados != null) {
            this.dados = dados;
        }
    }

    protected String doInBackground(String... parametros) {
        String retorno="";
        BufferedReader leitor = null;
        HttpURLConnection conexao = null;
        try {
            URL url = new URL(parametros[0]);

            conexao = (HttpURLConnection) url.openConnection();



            //enviando os dados
            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);

            OutputStreamWriter saida = new OutputStreamWriter(conexao.getOutputStream());

            String envio = "";
            for (int i = 0; i < dados.size(); i++) {
                if (i!=0) envio+="&";
                envio += java.net.URLEncoder.encode(dados.get(i).getNome(), "UTF-8") + "=" +
                        java.net.URLEncoder.encode(dados.get(i).getValor(), "UTF-8");

            }

            saida.write(envio);
            saida.flush();

            //recebendo o resultado
            int resposta = conexao.getResponseCode();
            leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder minhaString = new StringBuilder();
            String linha = null;

            while ((linha = leitor.readLine()) != null) {
                minhaString.append(linha + "\n");
            }

            retorno = minhaString.toString();

        } catch (Exception e) {
            Log.i("POST", "erro 1=" + e.toString());

        } finally {
            try {
                if (conexao != null) conexao.disconnect();
                if (leitor != null) leitor.close();
            } catch (Exception e) {
                Log.i("POST", "erro 2=" + e.getLocalizedMessage());
            }
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(String resultado) {
        // something...
        if (listener != null) {
            listener.onResult(resultado);
        }
    }

}

