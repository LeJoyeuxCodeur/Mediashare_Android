package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Home extends Activity{
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.home);

        TextView t = (TextView) findViewById(R.id.bonjour);
        t.setText("Bonjour " + Data.pseudo + " !");
        reload();
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

    public void reload(View v) {
        reload();
    }

    private void reload() {
        try {
            Toast.makeText(getApplicationContext(), "Reloading...", Toast.LENGTH_SHORT).show();

            URL obj = new URL(Data.URL + "/reload");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", "GYUserAgentAndroid");
            con.setRequestProperty("Accept", "*/*");
            con.setDoOutput(true);

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            String line = "", accumul = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            line = reader.readLine();
            while (line != null){
                accumul += line;
                line = reader.readLine();
            }
            reader.close();

            JSONArray response = new JSONArray(accumul);

            ListView l = (ListView) findViewById(R.id.liste);

            List<BinaryList> tmp = new ArrayList<BinaryList>();

            for(int i = 0; i < response.length(); i++) {
                JSONObject local = (JSONObject) response.get(i);

                URL url2 = new URL(Data.URL_WITHOUT_V1 + "/" + local.getString("path"));
                Object content = url2.getContent();

                InputStream inputstream = (InputStream)content;
                tmp.add(new BinaryList(local.getString("pseudo"), local.getString("description"), Drawable.createFromStream(inputstream, null)));
            }
            l.setAdapter(new MyListAdapter(getApplicationContext(), R.layout.listimagelayout, R.id.pseudo, tmp));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deconnexion(View v) {
        Data.pseudo = "";
        Toast.makeText(getApplicationContext(), "Déconnexion reussie", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Home.this, MainActivity.class));
    }

    public void newPost(View v){
        startActivity(new Intent(Home.this, UploadFile.class));
    }

}