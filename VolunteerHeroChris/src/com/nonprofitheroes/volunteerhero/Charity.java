package com.nonprofitheroes.volunteerhero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Charity {
    
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private Integer points;
    private List<RewardLevel> rewardLevels = new ArrayList<RewardLevel>();
    private RewardLevel nextLevel = null;
    
    public Charity(String id, String name, String address, String city, String state, String zip, String phone, String email){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getContent(){
        //Construct contents to display to user
        StringBuilder builder = new StringBuilder();
        
        builder.append("Adress: ");
        builder.append(this.address);
        builder.append("\nCity: ");
        builder.append(this.city);
        builder.append("\nState: ");
        builder.append(this.state);
        builder.append("\nZip Code: ");
        builder.append(this.zip);
        builder.append("\nPhone Number: ");
        builder.append(this.phone);
        builder.append("\nEmail: ");
        builder.append(this.email);
        builder.append("\n\n");
        
        return builder.toString();
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setPoints(String deviceId){
        this.points = DatabaseConnection.getPoints(deviceId, this.name);
    }
    
    
    public Integer getPoints(){
        return this.points;
    }
    
    public void setLevels(){
        this.rewardLevels = DatabaseConnection.getLevels(this.id);
        Collections.sort(this.rewardLevels);
    }
    
    public List<RewardLevel> getLevels(){
        return this.rewardLevels;
    }
    
    public List<RewardLevel> getCompleted(){
        ArrayList<RewardLevel> completed = new ArrayList<RewardLevel>();
        
        //Find all levels user has completed and level in progress
        // levels are sorted by points, so lower points are first in list
        for (int i = 0; i < this.rewardLevels.size(); i++){
            if (this.points >= this.rewardLevels.get(i).getPointValue()){
                completed.add(this.rewardLevels.get(i));
            }
            else{
                this.nextLevel = this.rewardLevels.get(i);
                break;
            }
        }
        
        return completed;
    }
    
    public RewardLevel getNextLevel(){
        return this.nextLevel;
    }

}
