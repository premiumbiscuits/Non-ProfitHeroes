package com.nonprofitheroes.volunteerhero;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.content.SharedPreferences;

public class User {
    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    private String deviceId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private Set<String> skills;
    
    // Create a user object using values from shared preferences. Default values included for testing
    public User(SharedPreferences sharedPreferences){
        settings = sharedPreferences;
        this.deviceId = settings.getString("deviceId", "");
        this.firstName = settings.getString("firstName", "defaultFirst");
        this.lastName = settings.getString("lastName", "defaultLast");
        this.emailAddress = settings.getString("emailAddress", "default@email.com");
        this.streetAddress = settings.getString("streetAddress", "100 Default Road");
        this.city = settings.getString("city", "Default City");
        this.state = settings.getString("state", "DF");
        this.zip = settings.getString("zip", "12345");
        this.phoneNumber = settings.getString("phoneNumber", "5551234567");
        this.skills = new HashSet<String>(Arrays.asList(settings.getString("skills", "").split(";")));
    }
    
    public String getDeviceId(){
        return this.deviceId;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getEmailAddress(){
        return this.emailAddress;
    }
    
    public String getStreetAddress(){
        return this.streetAddress;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public String getZip(){
        return this.zip;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public Set<String> getSkills(){
        return this.skills;
    }
    
    //Setters that update fields in SharedPreferences and Database
    
    public void setFirstName(String value){
        this.firstName = value;
        editor = settings.edit();
        editor.putString("firstName", value);
        editor.commit();
        DatabaseConnection.updateUserField("first_name", value, settings.getString("deviceId", ""));
    }
    
    public void setLastName(String value){
        this.lastName = value;
        editor = settings.edit();
        editor.putString("lastName", value);
        editor.commit();
        DatabaseConnection.updateUserField("last_name", value, settings.getString("deviceId", ""));
    }
    
    public void setEmailAddress(String value){
        this.emailAddress = value;
        editor = settings.edit();
        editor.putString("emailAddress", value);
        editor.commit();
        DatabaseConnection.updateUserField("email_address", value, settings.getString("deviceId", ""));
    }
    
    public void setStreetAddress(String value){
        this.streetAddress = value;
        editor = settings.edit();
        editor.putString("streetAddress", value);
        editor.commit();
        DatabaseConnection.updateUserField("street_address", value, settings.getString("deviceId", ""));
    }
    
    public void setCity(String value){
        this.city = value;
        editor = settings.edit();
        editor.putString("city", value);
        editor.commit();
        DatabaseConnection.updateUserField("city", value, settings.getString("deviceId", ""));
    }
    
    public void setState(String value){
        this.state = value;
        editor = settings.edit();
        editor.putString("state", value);
        editor.commit();
        DatabaseConnection.updateUserField("state", value, settings.getString("deviceId", ""));
    }
    
    public void setZip(String value){
        this.zip = value;
        editor = settings.edit();
        editor.putString("zip", value);
        editor.commit();
        DatabaseConnection.updateUserField("zip", value, settings.getString("deviceId", ""));
    }
    
    public void setPhoneNumber(String value){
        this.phoneNumber = value;
        editor = settings.edit();
        editor.putString("phoneNumber", value);
        editor.commit();
        DatabaseConnection.updateUserField("phone_number", value, settings.getString("deviceId", ""));
    }
}
