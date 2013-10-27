package com.nonprofitheroes.volunteerhero;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class FindLocalEventsActivity extends Activity {
    
    private static ArrayList<Event> eventList = null;
    private static Integer clickedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_local_events);
        // Show the Up button in the action bar.
        setupActionBar();
        
        
        eventList = DatabaseConnection.getEvents();
        
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, R.layout.event_list_layout, eventList);
        
        ListView listView = (ListView) findViewById(R.id.find_local_events_layout);
        listView.setAdapter(adapter);
        
        final FindLocalEventsActivity currentActivity = this;
        
        OnItemClickListener messageClickedHandler = new OnItemClickListener() {
            @SuppressWarnings("rawtypes")
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
                clickedIndex = position;
                Intent intent = new Intent(currentActivity, EventDescriptionActivity.class);
                startActivity(intent);
            }
        };
        
        listView.setOnItemClickListener(messageClickedHandler);
        
    }

    
    public static Event getEvent(){
        return eventList.get(clickedIndex);
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
