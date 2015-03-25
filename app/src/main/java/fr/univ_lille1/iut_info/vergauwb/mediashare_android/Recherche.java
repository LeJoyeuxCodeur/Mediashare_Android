package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Recherche extends Activity{
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.recherche);
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

    public void deconnexion(View v) {
        Data.pseudo = "";
        Toast.makeText(getApplicationContext(), "Déconnexion reussie", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Recherche.this, MainActivity.class));
    }

    public void newPost(View v){
        startActivity(new Intent(Recherche.this, UploadFile.class));
    }

    public void profil(View v){
        startActivity(new Intent(Recherche.this, Profil.class));
    }

    public void menu(View v){
        startActivity(new Intent(Recherche.this, Home.class));
    }

    public void chercher(View v){

    }

}
