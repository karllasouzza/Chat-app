package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView web = (WebView) findViewById(R.id.webview);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("file:///android_asset/inicio.html");


        web.addJavascriptInterface(new Ponte(this), "Ponte");
    }


    class Ponte {
        Context context;
        public Ponte(Context context) {
            this.context = context;
        }

        public boolean login = false;

        @JavascriptInterface
        public boolean getLogin(){
            return login;
        }

        @JavascriptInterface
        public void setLogin(boolean value){
            login = value;
        }

        // Guardando o usuario
        public String userEmail;
        public  String userPassword;

        @JavascriptInterface
        public String getUserEmail() {
            return userEmail;
        }
        @JavascriptInterface
        public String getUserPassword() {
            return userPassword;
        }
        @JavascriptInterface
        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }
        @JavascriptInterface
        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
        //

        // Buscando usuario
        public String userName;

        @JavascriptInterface
        public String getUserName() {
            return userName;
        }

        //

        // Buscando mensagem
        public String messages;

        @JavascriptInterface
        public String getMessages() {
            return messages;
        }
        //

        @JavascriptInterface
        public void mensagem(String data) {
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public int insereUsuario(String nome, String email, String senha) {
            ArrayList<Par> dados = new ArrayList<Par>();
            dados.add(new Par("name", nome));
            dados.add(new Par("email", email));
            dados.add(new Par("password", senha));

            Http tarefa = new Http(dados);

            tarefa.setListener(new Http.Listener(){

                @Override
                public void onResult(String resultado) {
                    mensagem(resultado);

                    Log.i("insereUsuario", "Resultado: "+ resultado);
                }
            });

            tarefa.execute("http://gamers-notes.atwebpages.com/api/cadastro.php");
            return 0;
        }



        @JavascriptInterface
        public int validaUsuario(String email, String password) {
            ArrayList<Par> dados = new ArrayList<Par>();
            dados.add(new Par("email", email));
            dados.add(new Par("password", password));

            Http2 tarefa = new Http2(dados);

            tarefa.setListener2(new Http2.Listener2(){

                @Override
                public void onResult2(String resultado) {
                      if(resultado.equals("")){
                          login = false;

                      }else{
                          login = true;
                         }

                    userName = resultado;
                    Log.i("validaUsuario", "Resultado: "+ resultado);
                }
            });

            tarefa.execute("http://gamers-notes.atwebpages.com/api/Login2.php");
            return 0;
        }


        @JavascriptInterface
        public int seachUser(String email, String password) {
        ArrayList<Par> dados = new ArrayList<Par>();
        dados.add(new Par("email", email));
        dados.add(new Par("password", password));

        Http2 tarefa = new Http2(dados);

        tarefa.setListener2(new Http2.Listener2(){

            @Override
            public void onResult2(String resultado) {
                if(resultado.equals("")){
                   userName = "False";

                }else{
                    userName = resultado;
                }

                Log.i("SeachUser", "Resultado: "+ resultado);
            }
        });

        tarefa.execute("http://gamers-notes.atwebpages.com/api/seachName.php");
        return 0;
    }


        @JavascriptInterface
        public int seachMessages(String email, String password) {
            ArrayList<Par> dados = new ArrayList<Par>();
            dados.add(new Par("email", email));
            dados.add(new Par("password", password));

            Http2 tarefa = new Http2(dados);

            tarefa.setListener2(new Http2.Listener2(){

                @Override
                public void onResult2(String resultado) {
                    if(resultado.equals("")){
                        messages = "False";

                    }else{
                        messages = resultado;
                    }
                    Log.i("SeachMsg", "Resultado: "+ resultado);
                }
            });

            tarefa.execute("http://gamers-notes.atwebpages.com/api/seachMsg.php");
            return 0;
        }

       @JavascriptInterface
       public int insereMsg( String content, String username) {
           ArrayList<Par> dados = new ArrayList<Par>();
           dados.add(new Par("content", content));
           dados.add(new Par("username", username));

           Http tasks = new Http(dados);

           tasks.setListener(new Http.Listener(){

               @Override
               public void onResult(String resultado) {

                   Log.i("InsereMsg", "Resultado: "+ resultado);
               }
           });

           tasks.execute("http://gamers-notes.atwebpages.com/api/cadastra_Msg.php");
           return 0;
       }
    }
}