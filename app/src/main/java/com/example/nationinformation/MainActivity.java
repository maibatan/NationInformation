package com.example.nationinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.nationinformation.adapter.CountryAdapter;
import com.example.nationinformation.model.Country;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private List<Country> countries;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private  StringBuffer response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        new  HttpGetTask().execute();


    }

    private class  HttpGetTask extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching conntry data");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String path="http://api.geonames.org/countryInfoJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=aporter";
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    // Reading response from input Stream
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String output;
                    response = new StringBuffer();

                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();
                }}
            catch(Exception e){
                e.printStackTrace();
            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing())
                progressDialog.dismiss();


            try {

                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonarray = jsonObject.getJSONArray("geonames");
                countries=new ArrayList<>();
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    String country = jsonobject.getString("countryName");
                    String countryCode = jsonobject.getString("countryCode");
                    String  area =jsonobject.getString("areaInSqKm");
                    String population = jsonobject.getString("population");
                    Country countryObj=new Country(country,population,area,countryCode);
                    countries.add(countryObj);
                    countryAdapter = new CountryAdapter(getBaseContext(), countries);
                    recyclerView.setAdapter(countryAdapter);
                }

            } catch ( JSONException e) {
                e.printStackTrace();
            }





        }

    }

}
