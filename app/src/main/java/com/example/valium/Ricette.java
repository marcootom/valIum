package com.example.valium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Ricette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricette);
        String username = MappaUtenti.getUtenteAttuale();
        final ListView mylist = (ListView) findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, MappaRicette.ricetteUtente(username));
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
                // recupero il titolo memorizzato nella riga tramite l'ArrayAdapter
                final String titoloriga = (String) adattatore.getItemAtPosition(pos);
                final char a = ' ';
                int nr = 0;
                StringBuilder dio = new StringBuilder();
                for(int i = 1; i < titoloriga.length() && titoloriga.charAt(i) != a; i++) {
                    dio.append(titoloriga.charAt(i));
                }
                Log.d("List", "Ho cliccato sull'elemento con id" + dio);
                nr = Integer.parseInt(dio.toString());
                String urlString = MappaRicette.ricettaPerId(nr).getFilePath();
                urlString = "https://www.lastelladelmattino.org/wp-content/uploads/2014/06/Camaldoli-giugno-2014-.pdf";
                if (urlString != null) {

                    try {

                        File root = Environment.getRootDirectory();
                        URL u = new URL(urlString);
                        HttpURLConnection c = (HttpURLConnection) u.openConnection();
                        c.setRequestMethod("GET");
                        c.setDoOutput(true);
                        c.connect();


                        int lenghtOfFile = c.getContentLength();

                        FileOutputStream f = new FileOutputStream(new File(root, "ciao"));

                        InputStream in = c.getInputStream();

                        byte[] buffer = new byte[1024];
                        int len1 = 0;
                        long total = 0;

                        while ((len1 = in.read(buffer)) > 0) {
                            total += len1; //total = total + len1
                            //publishProgress("" + (int)((total*100)/lenghtOfFile));
                            f.write(buffer, 0, len1);
                        }
                        f.close();
                    } catch (Exception e) {
                        Log.d("Downloader", "Non funziona coglione");
                    }
                }
            }
        });
    }

}


