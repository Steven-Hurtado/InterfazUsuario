package com.example.interfazusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebServices;

public class MainActivity extends AppCompatActivity implements Asynchtask{

    private TextView txtContact;
    private ListView LstOpciones;
    //DataSource

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LstOpciones = (ListView)findViewById(R.id.lstLista);

        View header = getLayoutInflater().inflate(R.layout.ly_cabecera, null);
        LstOpciones.addHeaderView(header);
        Map<String, String> datos = new HashMap<String, String>();
        WebServices ws= new WebServices("https://api.androidhive.info/contacts/",datos,MainActivity.this, MainActivity.this);
        ws.execute("");
    }
    public Noticias[] noticias;


    public void processFinish(String result) throws JSONException {
        Log.i("processFinish", result);
        //Leer el JSON en un ArrayList
        JSONObject jsonObj = new JSONObject(result);
        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();

        JSONArray contacts = jsonObj.getJSONArray("contacts");

        for (int i = 0; i < contacts.length(); i++) {
            JSONObject p = contacts.getJSONObject(i);
            String id = p.getString("id");
            String name = p.getString("name");
            String email = p.getString("email");
            String address = p.getString("address");
            String gender = p.getString("gender");

            JSONObject phone = p.getJSONObject("phone");
            String mobile = phone.getString("mobile");
            String home = phone.getString("home");
            String office = phone.getString("office");

            HashMap<String, String> contactos = new HashMap<String, String>();
            contactos.put("id", id);
            contactos.put("name", name);
            contactos.put("email", email);
            contactList.add(contactos);
        }
        //datos parceados
        String a = "";
        for (int j = 0; j < contactList.size(); j++) {
            HashMap<String, String> item = contactList.get(j);
            for (Map.Entry<String, String> entry : item.entrySet()) {
                a = a + " " + entry.getKey() + ": " + entry.getValue() + "\n";
            }
            a = a + "\n";
            noticias = new Noticias[]{new Noticias(" ", a)
            };
            
        }
        View header = getLayoutInflater().inflate(R.layout.ly_cabecera, null);
        LstOpciones.addHeaderView(header);

        AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);
        LstOpciones.setAdapter(adaptadornoticias);
    }
}
