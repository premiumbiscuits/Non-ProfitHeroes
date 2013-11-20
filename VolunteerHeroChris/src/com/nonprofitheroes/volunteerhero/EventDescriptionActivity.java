package com.nonprofitheroes.volunteerhero;

import java.util.Arrays;
import java.util.HashSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventDescriptionActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    static SharedPreferences settings;
    SharedPreferences.Editor editor;
    
    private Event event = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        
        //Load event selected on previous screen
        this.event = FindLocalEventsActivity.getEvent();
        
        //Fill out info fields for event
        TextView eventName = (TextView) findViewById(R.id.event_name);
        eventName.setText(event.getName());
        
        TextView eventContent = (TextView) findViewById(R.id.event_name_content);
        eventContent.setText(event.getContent());
        
    }
    
    public void rsvp(View view){
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        
        //Load events user has already RSVP'd to into hash set
        String eventString = settings.getString("events", "");
        HashSet<String> eventList = new HashSet<String>(Arrays.asList(eventString.split(";")));
        
        if(eventList.contains(this.event.getId())){
            warnAlreadyRsvp();
            return;
        }
        
        //Reconstruct event list with new event added
        StringBuilder newEventList = new StringBuilder(eventString);
        newEventList.append(this.event.getId() + ";");
        
        editor.putString("events", newEventList.toString());
        editor.commit();
        
        //Add event's charity to charities list
        HashSet<String> charityList = new HashSet<String>(Arrays.asList(settings.getString("charities", "").split(";")));
        charityList.add(this.event.getOrganization());
        
        //Reconstruct charity list
        StringBuilder newCharityList = new StringBuilder();
        for (String charity : charityList){
            newCharityList.append(charity + ";");
        }
        
        editor.putString("charities", newCharityList.toString());
        editor.commit();
        
        //Add rsvp information to database
        DatabaseConnection.rsvp(this.event, settings.getString("deviceId", ""));
        
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.rsvp_thanks);
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }
    
    private void warnAlreadyRsvp(){
        //Dialog warning if user rsvps to an event a second time
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.warn_rsvp);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dailog, int id) {}
        });
        AlertDialog dialog = builder.create();
        
        dialog.show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_description, menu);
        return true;
    }

}
