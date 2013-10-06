package com.nonprofitheroes.volunteerhero;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MyProfileActivity extends FragmentActivity implements ProfileEditDialogFragment.ProfileEditDialogListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    private static SharedPreferences settings;
    private User user;
    
    private int field = -1;
    
    @Override
    public void onDialogPositiveClick(ProfileEditDialogFragment dialog) {
        // User touched the dialog's positive button
        String value = dialog.getValue();
        
        switch (this.field){
            case FIRST:  updateFirst(value);
                         break;
            case LAST:   updateLast(value);
                         break;
            case EMAIL:  updateEmail(value);
                         break;
            case STREET: updateStreet(value);
                         break;
            case CITY:   updateCity(value);
                         break;
            case STATE:  updateState(value);
                         break;
            case ZIP:    updateZip(value);
                         break;
            case PHONE:  updatePhone(value);
                         break;
            default:     break;
        }
    }

    @Override
    public void onDialogNegativeClick(ProfileEditDialogFragment dialog) {
        // User touched the dialog's negative button
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        // Show the Up button in the action bar.
        setupActionBar();
    }
    
    @Override
    protected void onResume(){
        super.onResume();
        
        settings = getSharedPreferences(PREFS_NAME, 0);
        this.user = new User(settings);
        
        setTextViews();
        
    }

    private void setTextViews(){
        TextView firstName = (TextView) findViewById(R.id.my_profile_first_value);
        firstName.setText(this.user.getFirstName());
        
        TextView lastName = (TextView) findViewById(R.id.my_profile_last_value);
        lastName.setText(this.user.getLastName());
        
        TextView email = (TextView) findViewById(R.id.my_profile_email_value);
        email.setText(this.user.getEmailAddress());
        
        TextView street = (TextView) findViewById(R.id.my_profile_street_value);
        street.setText(this.user.getStreetAddress());
        
        TextView city = (TextView) findViewById(R.id.my_profile_city_value);
        city.setText(this.user.getCity());
        
        TextView state = (TextView) findViewById(R.id.my_profile_state_value);
        state.setText(this.user.getState());
        
        TextView zip = (TextView) findViewById(R.id.my_profile_zip_value);
        zip.setText(this.user.getZip());
        
        TextView phone = (TextView) findViewById(R.id.my_profile_phone_value);
        phone.setText(this.user.getPhoneNumber());
    }
    
    private void openDialog(){
        ProfileEditDialogFragment newFragment = new ProfileEditDialogFragment();
        newFragment.show(getSupportFragmentManager(), "edit_fragment");
    }
    
    private void updateField(final int viewId, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) findViewById(viewId);
                textView.setText(value);
            }
        });
    }
    
    private void warningDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dailog, int id) {}
        });
        AlertDialog dialog = builder.create();
        
        dialog.show();
        
    }
    
    public void firstClick(View view){
        this.field = FIRST;
        openDialog();
    }
    
    private void updateFirst(String value){
        if (value.equals(this.user.getFirstName())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(value.length() > 20){
            warningDialog(getString(R.string.too_long));
            return;
        }
        
        updateField(R.id.my_profile_first_value, value);
        this.user.setFirstName(value);
    }
    
    public void lastClick(View view){
        this.field = LAST;
        openDialog();
    }
    
    private void updateLast(String value){
        if (value.equals(this.user.getLastName())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(value.length() > 20){
            warningDialog(getString(R.string.too_long));
            return;
        }
        
        updateField(R.id.my_profile_last_value, value);
        this.user.setLastName(value);
    }
    
    public void emailClick(View view){
        this.field = EMAIL;
        openDialog();
    }
    
    private void updateEmail(String value){
        if (value.equals(this.user.getEmailAddress())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(!Patterns.EMAIL_ADDRESS.matcher(value).matches() || value.length() > 100){
            warningDialog(getString(R.string.bad_email));
            return;
        }
        
        updateField(R.id.my_profile_email_value, value);
        this.user.setEmailAddress(value);
    }
    
    public void streetClick(View view){
        this.field = STREET;
        openDialog();
    }
    
    private void updateStreet(String value){
        if (value.equals(this.user.getStreetAddress())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(value.length() > 100){
            warningDialog(getString(R.string.too_long));
            return;
        }
        
        updateField(R.id.my_profile_street_value, value);
        this.user.setStreetAddress(value);
    }
    
    public void cityClick(View view){
        this.field = CITY;
        openDialog();
    }
    
    private void updateCity(String value){
        if (value.equals(this.user.getCity())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(value.length() > 20){
            warningDialog(getString(R.string.too_long));
            return;
        }
        
        updateField(R.id.my_profile_city_value, value);
        this.user.setCity(value);
    }
    
    public void stateClick(View view){
        this.field = STATE;
        openDialog();
    }
    
    private void updateState(String value){
        if (value.equals(this.user.getState())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(!value.matches("AL|AK|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY")){
            warningDialog(getString(R.string.bad_state));
            return;
        }
        
        updateField(R.id.my_profile_state_value, value);
        this.user.setState(value);
    }
    
    public void zipClick(View view){
        this.field = ZIP;
        openDialog();
    }
    
    private void updateZip(String value){
        if (value.equals(this.user.getZip())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(!value.matches("\\d\\d\\d\\d\\d")){
            warningDialog(getString(R.string.bad_zip));
            return;
        }
        
        updateField(R.id.my_profile_zip_value, value);
        this.user.setZip(value);
    }
    
    public void phoneClick(View view){
        this.field = PHONE;
        openDialog();
    }
    
    private void updatePhone(String value){
        if (value.equals(this.user.getPhoneNumber())){
            warningDialog(getString(R.string.unchanged));
            return;
        }
        
        if(!value.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d")){
            warningDialog(getString(R.string.bad_phone));
            return;
        }
        
        updateField(R.id.my_profile_phone_value, value);
        this.user.setPhoneNumber(value);
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
    
    private static final int FIRST = 1;
    private static final int LAST = 2;
    private static final int EMAIL = 3;
    private static final int STREET = 4;
    private static final int CITY = 5;
    private static final int STATE = 6;
    private static final int ZIP = 7;
    private static final int PHONE = 8;

}
