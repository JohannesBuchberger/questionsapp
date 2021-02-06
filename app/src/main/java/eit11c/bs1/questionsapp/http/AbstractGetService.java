package eit11c.bs1.questionsapp.http;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import eit11c.bs1.questionsapp.pojos.AbstractPojo;

public abstract class AbstractGetService<T extends AbstractPojo> extends AsyncTask<String, Void, T> {

    @Override
    protected T doInBackground(String... urls) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            urlConnection.disconnect();
        } catch (MalformedURLException malExep) {
            malExep.printStackTrace();
        } catch (IOException ioExep) {
            ioExep.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), getRelevantClass());
    }

    protected abstract Class<T> getRelevantClass();

    @Override
    protected void onPostExecute(T result) {
        EventBus.getDefault().post(result);
    }
}
