package com.nonprofitheroes.volunteerhero;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DatabaseConnection {

    private DatabaseConnection(){}
    
    private static InputStream doPost(String url, List<NameValuePair> data){
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            if (data != null){
                httppost.setEntity(new UrlEncodedFormEntity(data));
            }
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            return entity.getContent();
        } catch(Exception e){
            Log.e("log_tag", "Error in http connection"+e.toString());
            return null;
        }
    }
    
    private static String getResponse(InputStream inputStream){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line="0";
          
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
             
            inputStream.close();
            return sb.toString();
             
        } catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
            return null;
        }
    }
    
    public static void addUser(String firstName, String lastName, String email, String phone, String address,
                               String city, String state, String zip, String deviceId){
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("first_name", firstName));
        data.add(new BasicNameValuePair("last_name", lastName));
        data.add(new BasicNameValuePair("email_address", email));
        data.add(new BasicNameValuePair("street_address", address));
        data.add(new BasicNameValuePair("city", city));
        data.add(new BasicNameValuePair("state", state));
        data.add(new BasicNameValuePair("zip", zip));
        data.add(new BasicNameValuePair("phone_number", phone));
        data.add(new BasicNameValuePair("device_id", deviceId));
        
        String response = getResponse(doPost("http://54.235.144.121/VolunteerHero/AndroidDatabaseScripts/new_volunteer.php", data));
        System.out.println(response);
        
    }
    
    public static ArrayList<Event> getEvents(){
        ArrayList<Event> list = new ArrayList<Event>();
        
        String response = getResponse(doPost("http://54.235.144.121/VolunteerHero/AndroidDatabaseScripts/all_events.php", null));
        
        try{
            JSONArray jsonArray = new JSONArray(response);
             
            for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    Integer eventId = jsonData.getInt("id");
                    String organization = jsonData.getString("organization_id");
                    String name = jsonData.getString("title");
                    String eventType = jsonData.getString("event_type_id");
                    String description = jsonData.getString("description");
                    String date = jsonData.getString("date");
                    String time = jsonData.getString("time");
                    String address = jsonData.getString("street_address");
                    String city = jsonData.getString("city");
                    String state = jsonData.getString("state");
                    String zip = jsonData.getString("zip");
                    
                    list.add(new Event(eventId, organization, name, eventType, description, date, time, address, city, state, zip));
            }
             
            }catch(JSONException e1){
                e1.printStackTrace();
            }catch (ParseException e1){
                e1.printStackTrace();
            }
        
        return list;
    }
    
    public static Event loadEvent(int eventId){
        
        return new Event();
    }
    
}
