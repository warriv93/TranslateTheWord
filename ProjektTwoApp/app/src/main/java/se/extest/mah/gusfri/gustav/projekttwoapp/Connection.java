package se.extest.mah.gusfri.gustav.projekttwoapp;

import android.os.AsyncTask;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.EventListener;
import java.util.Scanner;

/**
 * Created by gustav on 2014-10-23.
 */
public class Connection extends AsyncTask<String, ConnectionEvent, Boolean> {

    private final String TAG = "CONNECTION";

    private URL url;
    private String urlString;
//    private HttpURLConnection connection;
    //    private InputStream input;
    private OutputStream output;
    private String receivedData;
    private MyActivity act;

//    private HttpResponse<JsonNode> response;

    public Connection(String urlString, MyActivity act) {
        this.urlString = urlString;
        this.act = act;
        this.execute(urlString);

    }

    @Override
    protected Boolean doInBackground(String... params) {


        try {
            HttpClient client = new DefaultHttpClient();
//            String getURL = "https://currencyconverter.p.mashape.com/?from=USD&from_amount=1&to=SEK";
//            String getURL = "https://theshub.p.mashape.com/random?language=sv";
//            String getURL = "https://theshub.p.mashape.com/translate?from=sv&to=en&word=sända";
            HttpGet get = new HttpGet(params[0]);
            get.setHeader("X-Mashape-Key", "JjRKQLR6pDmshK0xPNDV2anSAINFp1azu2TjsnIcAegmY9ILXu");
            org.apache.http.HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            String res = EntityUtils.toString(resEntityGet);
            if (resEntityGet != null) {
                Log.i("GET ", res);
//                Log.i("GET", res);
                publishProgress(new ConnectionEvent(this, ConnectionEvent.INCOMMING_MESSAGE, res));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
    protected void onProgressUpdate(ConnectionEvent... event) {
        fireMyEvent(event[0]);
    }

    protected void onPostExecute(Integer... integers) {
    }
    ConnectionListener listener;
    // Listener Interface
    public interface ConnectionListener extends EventListener {
        public void myEventOccurred(ConnectionEvent evt);
    }
    public void addEventListener(ConnectionListener listener) {
        this.listener=listener;
    }
    public void removeEventEventListener(ConnectionListener listener) {
        listener=null;
    }
    public void removeEventEventListener() {
        listener=null;
    }
    void fireMyEvent(ConnectionEvent evt) {
        listener. myEventOccurred(evt);
    }

}
