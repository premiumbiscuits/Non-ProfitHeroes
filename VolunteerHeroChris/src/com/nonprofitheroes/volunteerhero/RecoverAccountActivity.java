package com.nonprofitheroes.volunteerhero;


import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
//import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class RecoverAccountActivity extends Activity {
	
	private String email;
	private String conCode;

	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	          "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	          "\\@" +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	          "(" +
	          "\\." +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	          ")+"
	      );
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recover_account);
		// Show the Up button in the action bar.
		setupActionBar();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recover_account, menu);
		return true;
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
	public void sendMessageRecoverAccount(){
		
		this.email = ((EditText)findViewById(R.id.editText1)).getText().toString();
		this.conCode = ((EditText)findViewById(R.id.editText2)).getText().toString();
		boolean validInput = checkInputValidity(this.email, this.conCode);
		if(validInput){
			//do database stuff here
		}
	}
	private boolean checkInputValidity(String e, String c){
		if(e.equals("") || c.equals("")){ //if either field is empty
			warningDialog(getString(R.string.warning_incomplete));
			return false;
		}
		else if(!checkValidityEmail(e)){
			warningDialog(getString(R.string.bad_email));
			return false;
		}
			
		return true;
	}
	private boolean checkValidityEmail(String value) {
        return EMAIL_ADDRESS_PATTERN.matcher(value).matches();
	}
	private void warningDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(R.string.invalid_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dailog, int id) {}
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        
    }

}
