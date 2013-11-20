package com.nonprofitheroes.volunteerhero;

public class RewardLevel implements Comparable<RewardLevel>{
    //Class for an individual reward level, has the name and point value
    
    private String name;
    private Integer pointValue;
    private String reward;
    
    public RewardLevel(String name, Integer pointValue, String reward){
        this.name = name;
        this.pointValue = pointValue;
        this.reward = reward;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Integer getPointValue(){
        return this.pointValue;
    }
    
    public String getReward(){
        return this.reward;
    }

    //Comparator for sorting levels
    @Override
    public int compareTo(RewardLevel level) {
        Integer otherLevelPointValue = level.getPointValue();
        return this.pointValue - otherLevelPointValue;
    }
    
    public String nameAndReward(){
        return this.name + ":  " + this.reward + "\n";
    }
}
