package com.nonprofitheroes.volunteerhero;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class EventDescriptionActivity extends Activity {
    
    private Event event = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        
        this.event = FindLocalEventsActivity.getEvent();
        
        TextView eventName = (TextView) findViewById(R.id.event_name);
        eventName.setText(event.getName());
        
        TextView eventContent = (TextView) findViewById(R.id.event_name_content);
        eventContent.setText(event.getContent());
        
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_description, menu);
        return true;
    }

}
