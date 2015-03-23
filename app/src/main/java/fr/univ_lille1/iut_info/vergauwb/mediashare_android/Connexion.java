package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
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
import org.json.JSONObject;

public class Connexion extends Activity{
	public void connect(View v){
		try{
			EditText et = (EditText) findViewById(R.id.loginCo);
			String login = et.getText().toString();
			
			et = (EditText) findViewById(R.id.mdpCo);
			String mdp = et.getText().toString();

            // Changer l'adresse à chaque fois
			URL obj = new URL("http://bouleau09.iut-infobio.priv.univ-lille1.fr:8080/v1/connect");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type","application/json");  
		    con.setRequestProperty("User-Agent", "GYUserAgentAndroid");
		    con.setRequestProperty("Accept","*/*");
			con.setDoOutput(true);

            String json = "{'pseudo':'" + login + "','mdp':'" + mdp + "'}";
            JSONObject jsonObject = new JSONObject(json);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Post parameters : " + json.toString());
            System.out.println("Response Code : " + responseCode);

            String line, lineTmp="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((line = reader.readLine()) != null) {
                lineTmp = line;
                System.out.println(line);
            }
            reader.close();

            JSONObject response = new JSONObject(lineTmp);
            int id = response.getInt("id");

            if(id != -1)
				startActivity(new Intent(Connexion.this, Home.class));
		} catch (Exception e){
			e.printStackTrace();
		}
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