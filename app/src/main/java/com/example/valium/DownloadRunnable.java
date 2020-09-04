package com.example.valium;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadRunnable implements Runnable {
    private String url;
    private Context c;
    private String title;

    public DownloadRunnable(String url, Context c,String title) {
        this.url = url;
        this.c = c;
        this.title = title;
    }

    public void run(){
        String url = this.url;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Contenuto ricetta medica del dottor Mbiwa");
        request.setTitle(title);
// in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Ricetta.jpg");

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
