package com.nonprofitheroes.volunteerhero;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    static SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstLaunch = settings.getBoolean("firstLaunch", true);
        
        if(firstLaunch){
            Intent intent = new Intent(this, FirstUseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        
    }
    
    @Override
    protected void onResume(){
        super.onResume();
        
        settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstLaunch = settings.getBoolean("firstLaunch", true);
        
        if(firstLaunch){
            Intent intent = new Intent(this, FirstUseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void openMyCharities(View view){
        Intent intent = new Intent(this, MyCharitiesActivity.class);
        startActivity(intent);
    }
    
    public void openMyCalendar(View view){
        Intent intent = new Intent(this, MyCalendarActivity.class);
        startActivity(intent);
    }

    public void openFindLocalEvents(View view){
        Intent intent = new Intent(this, FindLocalEventsActivity.class);
        startActivity(intent);
    }

    public void openMyProfile(View view){
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }
    
    public void resetProfile(View view){
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        
        editor.clear();
        editor.commit();
        
        editor = settings.edit();
        editor.putBoolean("firstLaunch", true);
        editor.commit();
        
        Intent intent = new Intent(this, FirstUseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    
}
