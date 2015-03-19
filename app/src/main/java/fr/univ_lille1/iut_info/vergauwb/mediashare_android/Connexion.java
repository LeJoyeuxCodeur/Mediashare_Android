package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Connexion extends Activity{
	public void connect(View v){
		try{
			EditText et = (EditText) findViewById(R.id.loginCo);
			String login = et.getText().toString();
			
			et = (EditText) findViewById(R.id.mdpCo);
			String mdp = et.getText().toString();

            // Changer l'adresse à chaque fois
			URL obj = new URL("http://bouleau08.iut-infobio.priv.univ-lille1.fr:8080/v1/connect");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type","application/json");  
		    con.setRequestProperty("User-Agent", "GYUserAgentAndroid");
		    con.setRequestProperty("Accept","*/*");
			con.setDoOutput(true);

            String urlParameters = "pseudo=" + login + "&mdp=" + mdp;

            int responseCode = con.getResponseCode();
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();

            System.out.println(response.toString());

			if(responseCode == HttpURLConnection.HTTP_OK)
				startActivity(new Intent(Connexion.this, Home.class));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
        setContentView(R.layout.connexion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
    }
}