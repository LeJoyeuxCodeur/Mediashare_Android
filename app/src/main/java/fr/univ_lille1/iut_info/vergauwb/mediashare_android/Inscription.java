package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Inscription extends Activity{
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.inscription);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void register(View v){
        try{
            EditText et = (EditText) findViewById(R.id.pseudoInscr);
            String login = et.getText().toString();

            et = (EditText) findViewById(R.id.mdpInscr);
            String mdp = et.getText().toString();

            et = (EditText) findViewById(R.id.mdp2Inscr);
            String mdp2 = et.getText().toString();

            et = (EditText) findViewById(R.id.mailInscr);
            String mail = et.getText().toString();

            URL obj = new URL(Data.URL+"/register");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("User-Agent", "GYUserAgentAndroid");
            con.setRequestProperty("Accept","*/*");
            con.setDoOutput(true);

            String json = "{" +
                            "'pseudo':'" + login + "'," +
                            "'email':'" + mail + "'," +
                            "'mdp':'" + mdp + "'," +
                            "'mdp2':'" + mdp2 + "'" +
                          "}";
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

            Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
            if(response.getBoolean("success"))
                startActivity(new Intent(Inscription.this, Connexion.class));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void connexion(View v){
        startActivity(new Intent(Inscription.this, Connexion.class));
    }

    public void accueil(View v){
        startActivity(new Intent(Inscription.this, MainActivity.class));
    }

}