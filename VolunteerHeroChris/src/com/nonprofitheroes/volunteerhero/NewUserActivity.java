package com.nonprofitheroes.volunteerhero;


import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class NewUserActivity extends Activity  {

    private boolean infoComplete = false;
    public static final String PREFS_NAME = "MyPrefsFile";
    private static SharedPreferences settings;
    private SharedPreferences.Editor editor;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String address;
	private String state;
	private String city;
	private String zip;
	//private String deviceId;
	
	 
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
		setContentView(R.layout.activity_new_user);
		
		settings = getSharedPreferences(PREFS_NAME, 0);
		
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
		getMenuInflater().inflate(R.menu.new_user, menu);
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
	public void sendMessageCreateProfile(View view){
		
		this.fullName = ((EditText)findViewById(R.id.editText1)).getText().toString();
		this.email = ((EditText)findViewById(R.id.editText2)).getText().toString();
		this.phoneNumber = ((EditText)findViewById(R.id.editText3)).getText().toString();
		this.address = ((EditText)findViewById(R.id.editText4)).getText().toString();
		this.state = ((EditText)findViewById(R.id.editText5)).getText().toString();
		this.city = ((EditText)findViewById(R.id.editText6)).getText().toString();
		this.zip = ((EditText)findViewById(R.id.editText7)).getText().toString();
		
		//int check = 0; //if check stays 0, all of the input is valid
		if(!checkAllFilled(this.fullName, this.email, this.phoneNumber, this.address, this.city, this.state, this.zip)){
		    //use warningdialog to tell user to fill all fields
		    //warningDialog(this.fullName + " " + this.email + " " + this.phoneNumber + " " + this.address + " " + this.city + " " + this.state + " " + this.zip);
			warningDialog(getString(R.string.warning_incomplete));
		}
		else{
			infoComplete = true;
		}
		int check = checkValidity(this.email, this.phoneNumber, this.zip);
		if(check != 0)
			invalidInput(check);
		if(check == 0 && infoComplete){
			addUser(this.fullName, this.email, this.phoneNumber, this.address, this.city, this.state, this.zip);
			
			editor = settings.edit();
	        
	        editor.putBoolean("firstLaunch", false);
	        editor.commit();
	        
			Intent i = new Intent(this, MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}			
	}
	private boolean checkAllFilled(String n, String e, String p, String a, String c, String s, String z){
		if(n.equals(""))
			return false;
		if(e.equals(""))
			return false;
		if(p.equals(""))
			return false;
		if(a.equals(""))
			return false;
		if(c.equals(""))
			return false;
		if(s.equals(""))
			return false;
		if(z.equals(""))
			return false;
		return true;
	}
	private int checkValidity(String e, String p, String z){
		int c = 0;
		if(!checkValidityEmail(email))
			c += 1;
		if(!checkValidityPhone(this.phoneNumber))
			c += 10;
		if(!checkValidityZip(this.zip))
			c += 100;
		return c;
	}
	private boolean checkValidityEmail(String value) {
        return EMAIL_ADDRESS_PATTERN.matcher(value).matches();
	}
	private boolean checkValidityPhone(String value) {
		if(!value.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d"))
			return false;
		return true;
	}
	private boolean checkValidityZip(String value) {
		if(value.length() != 5) //zip codes must be 5 digits
			return false;
		try{
			Integer.parseInt(value); //zip codes must be integers
		}
		catch(NumberFormatException nFE){
			return false;
		}
		return true;
	}
	private void addUser(String name, String email, String phone, String adress, String city, String state, String zip){
		//editor.putBoolean("silentMode", true);
		this.setFullName(name);
		this.setEmailAddress(email);
		this.setPhoneNumber(phone);
		this.setStreetAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setDeviceId();
	}
	/*public void setFirstName(String value){
        editor = settings.edit();
        editor.putString("firstName", value);
        editor.commit();
    }
    
    public void setLastName(String value){
        editor = settings.edit();
        editor.putString("lastName", value);
        editor.commit();
    }*/
	
    private void setFullName(String value){
    	editor = settings.edit();
    	/*
    	editor.putString("fullName", value);
    	*/
    	
    	//HACKY WORK AROUND, NEED TO SEPERATE FIRST/LAST NAMES IN USER INPUT
    	String[] names = value.split(" ");
    	editor.putString("firstName", names[0]);
    	editor.putString("lastName", names[1]);
    	editor.commit();
    	
    }
    
    private void setEmailAddress(String value){
        editor = settings.edit();
        editor.putString("emailAddress", value);
        editor.commit();
    }
    private void setStreetAddress(String value){
        editor = settings.edit();
        editor.putString("streetAddress", value);
        editor.commit();
    }
    
    private void setCity(String value){
        editor = settings.edit();
        editor.putString("city", value);
        editor.commit();
    }
    
    private void setState(String value){
        editor = settings.edit();
        editor.putString("state", value);
        editor.commit();
    }
    
    private void setZip(String value){
        editor = settings.edit();
        editor.putString("zip", value);
        editor.commit();
    }
    
    private void setPhoneNumber(String value){
        editor = settings.edit();
        editor.putString("phoneNumber", value);
        editor.commit();
    }
    private void setDeviceId(){
    	editor = settings.edit();
    	
    	
    }
    private void invalidInput(int value){
    	if(value == 1){ //invalid email
    		warningDialog(getString(R.string.bad_email));
    	}
    	else if(value == 10){ //invalid phone#
    		warningDialog(getString(R.string.bad_phone));
    	}
    	else if(value == 100){ //invalid ZIP

    		warningDialog(getString(R.string.bad_zip));
    	}
    	else if(value == 11){//invalid email and phone

    		warningDialog(getString(R.string.bad_email));
    		warningDialog(getString(R.string.bad_phone));
    	}
    	else if(value == 101){ //invalid email and zip

    		warningDialog(getString(R.string.bad_email));
    		warningDialog(getString(R.string.bad_zip));
    	}
    	else if(value == 110){ //invalid phone and zip

    		warningDialog(getString(R.string.bad_phone));
    		warningDialog(getString(R.string.bad_zip));
    	}
    	else if(value == 111){ //invalid email, phone, and zip
    		warningDialog(getString(R.string.bad_email));
    		warningDialog(getString(R.string.bad_phone));
    		warningDialog(getString(R.string.bad_zip));
    	}
    }
    private void warningDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(R.string.invalid_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dailog, int id)  {}
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        
    }
    

}