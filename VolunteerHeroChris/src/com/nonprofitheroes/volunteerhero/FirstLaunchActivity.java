package com.nonprofitheroes.volunteerhero;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class FirstLaunchActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    static SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);
    }
    
    public void createProfile(View view){
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        
        editor.putBoolean("firstLaunch", false);
        editor.commit();
        
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    
    public void resetProfile(View view){
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        
        editor.putBoolean("firstLaunch", true);
        editor.commit();
    }

}
