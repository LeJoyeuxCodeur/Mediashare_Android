package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Profil extends Activity{
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.profil);
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
        startActivity(new Intent(Profil.this, MainActivity.class));
    }

    public void modifierProfil(View v){
    //    startActivity(new Intent(Profil.this, ProfilModifier.class));
    }

    public void newPost(View v){
        startActivity(new Intent(Profil.this, UploadFile.class));
    }

    public void recherche(View v){
        startActivity(new Intent(Profil.this, Recherche.class));
    }

    public void menu(View v){
        startActivity(new Intent(Profil.this, Home.class));
    }
}
