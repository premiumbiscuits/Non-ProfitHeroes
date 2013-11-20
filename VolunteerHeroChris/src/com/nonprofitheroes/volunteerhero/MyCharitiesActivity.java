package com.nonprofitheroes.volunteerhero;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

public class MyCharitiesActivity extends Activity {
    
    private static ArrayList<String> charities = null;
    private static Integer clickedIndex = -1;
    public static final String PREFS_NAME = "MyPrefsFile";
    static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_charities);
        // Show the Up button in the action bar.
        setupActionBar();
        
        //Load charities List
        settings = getSharedPreferences(PREFS_NAME, 0);
        String charityString = settings.getString("charities", "");
        charities = new ArrayList<String>();
        
        for (String charity : Arrays.asList(charityString.split(";"))){
            if (!charity.equals("")){
                charities.add(charity);
            }
        }
        
        // Put Charities into a list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.charity_list_layout, charities);
        
        ListView listView = (ListView) findViewById(R.id.my_charities_layout);
        listView.setAdapter(adapter);
        
        final MyCharitiesActivity currentActivity = this;
        
        // On click handler for list view
        OnItemClickListener messageClickedHandler = new OnItemClickListener() {
            @SuppressWarnings("rawtypes")
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                clickedIndex = position;
                Intent intent = new Intent(currentActivity, CharityInfoActivity.class);
                startActivity(intent);
            }
        };
        
        listView.setOnItemClickListener(messageClickedHandler);
    }
    
    public static String getCharity(){
        return charities.get(clickedIndex);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
