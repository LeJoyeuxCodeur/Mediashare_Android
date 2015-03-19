package com.example.mediashare_android;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Connexion extends Activity{
	public void connect(View v){
		try{
			EditText et = (EditText) findViewById(R.id.loginCo);
			String login = et.getText().toString();
			
			et = (EditText) findViewById(R.id.mdpCo);
			String mdp = et.getText().toString();
			
			URL obj = new URL("http://iut.azae.net/mediashare/v1/connect/");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
		    con.setRequestProperty("Content-Type","application/json");  
		    con.setRequestProperty("User-Agent", "GYUserAgentAndroid");
		    con.setRequestProperty("Accept","*/*");
			con.setDoOutput(true);
			con.setDoInput(true);
			
			String urlParameters = "pseudo=" + login + "&mdp=" + mdp;
			 
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
	 
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
				startActivity(new Intent(Connexion.this, Home.class));
			else{
				System.out.println(con.getResponseCode());
				System.out.println(con.getResponseMessage());
			}
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