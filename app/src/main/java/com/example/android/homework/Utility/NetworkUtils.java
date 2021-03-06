package com.example.android.homework.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;


public class NetworkUtils {

    public static String BASEURL = "newsapi.org";

    public static String APIKEY = "6047cd7990a546409d7d975107919c01";

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            httpsURLConnection.disconnect();
        }
    }


    public static String appendURL(String mBaseUrl, String apiKey) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(mBaseUrl)
                .appendPath("v1")
                .appendPath("articles")
                .appendQueryParameter("source", "the-next-web")
                .appendQueryParameter("sortBy", "latest")
                .appendQueryParameter("apiKey", apiKey);

        return builder.build().toString();
    }

}
