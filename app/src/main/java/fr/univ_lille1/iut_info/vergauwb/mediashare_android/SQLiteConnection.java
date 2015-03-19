package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConnection extends SQLiteOpenHelper {
    protected Context context = null ;
    protected static String SQL_NAME = "";
    protected final static int SQL_VERSION = 1 ;
    public SQLiteDatabase db = null;
    
    public SQLiteConnection(Context context) {
        super(context, SQL_NAME, null, SQL_VERSION);
        this.context = context ;
        db = this.getWritableDatabase() ;
    }
    
    public void onCreate(SQLiteDatabase db) {
    	String tmpDir = System.getProperty("java.io.tmpdir");
    	tmpDir = tmpDir.replace("\\", "/");
    	SQL_NAME = tmpDir + "media_bdd";
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
