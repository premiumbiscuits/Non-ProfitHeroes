package com.nonprofitheroes.volunteerhero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class FirstUseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_use);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_use, menu);
        return true;
    }
    public void sendMessageCreateAccount(View view){
    	
    	Intent i = new Intent(this, NewUserActivity.class);
		startActivity(i);
    }
    public void sendMessageRecoverAccount(View view){
    	Intent i = new Intent(this, RecoverAccountActivity.class);
		startActivity(i);
    	
    }
    
}