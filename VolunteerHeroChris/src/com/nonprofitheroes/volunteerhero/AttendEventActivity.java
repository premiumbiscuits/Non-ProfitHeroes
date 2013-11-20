package com.nonprofitheroes.volunteerhero;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AttendEventActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    static SharedPreferences settings;
    private Event event = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_event);
        
        //Get the event the user selected on previous screen
        this.event = MyCalendarActivity.getEvent();
        
        //Fill out info fields on screen from event
        TextView eventName = (TextView) findViewById(R.id.attend_event_name);
        eventName.setText(this.event.getName());
        
        TextView eventContent = (TextView) findViewById(R.id.attend_event_name_content);
        eventContent.setText(this.event.getContent());
    }
    
    public void attend(View view){
        //Check send confirmation code to server
        settings = getSharedPreferences(PREFS_NAME, 0);
        EditText editTextConfirm = (EditText) findViewById(R.id.confirmation_code);
        String confirmationCode = editTextConfirm.getText().toString();
        String multiplier;
        // Get multiplier if necessary
        if (this.event.getHasMultiplier()){
            EditText editTextMultiplier = (EditText) findViewById(R.id.hours);
            multiplier = editTextMultiplier.getText().toString();
        }
        else{
            multiplier = "1";
        }
        
        String response = DatabaseConnection.confirmAttendance(this.event, settings.getString("deviceId", ""), confirmationCode, multiplier);
        // Check if code was invalid
        if (response.replaceAll("\\s+","").equals("confirm_code")){
            warnWrongCode();
        }
        else{
            //Use an Android toast to tell user confirmation was successful
            Context context = getApplicationContext();
            CharSequence text = getResources().getString(R.string.good_confirm);
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }
    }
    
    private void warnWrongCode(){
        //Dialog warning if user enters invalid confirmation code
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.bad_confirm);
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
        getMenuInflater().inflate(R.menu.attend_event, menu);
        return true;
    }

}
