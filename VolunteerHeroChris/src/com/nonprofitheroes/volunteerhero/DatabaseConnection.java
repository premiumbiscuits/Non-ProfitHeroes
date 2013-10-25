package com.nonprofitheroes.volunteerhero;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;

public class DatabaseConnection {
    
    private static String url;

    private DatabaseConnection(){}
    
    public static void getUrl(Context context){
        Resources resources = context.getResources();
        url = resources.getString(R.string.database_url);
    }
    
    public static List<Integer> getEventIds(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }
    
    public static Event loadEvent(int eventId){
        
        return new Event();
    }
    
}
