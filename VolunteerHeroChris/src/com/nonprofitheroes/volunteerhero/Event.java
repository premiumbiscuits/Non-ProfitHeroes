package com.nonprofitheroes.volunteerhero;

public class Event {
    
    private Integer eventId;
    private String organization;
    private String name;
    private String description;
    private String eventType;
    private String date;
    private String time;
    private String address;
    private String city;
    private String state;
    private String zip;
    
    public Event(){
        this.eventId = -1;
        this.organization = "Default Charity";
        this.name = "Default Event";
        this.description = "This is a default event used for testing purposes! This is a totally awesome event that you should come to, it'll be fun. No really, if you don't come you're a loser. Hopefull this is long enough.";
        this.eventType = "Default";
        this.date = "1/1/1492";
        this.time = "12:45";
        this.address = "10 Test St";
        this.city = "Defaultville";
        this.state = "DF";
        this.zip = "00000";  
        
    }
    
    public Event(Integer eventId, String organization, String name, String eventType, String description,
                 String date, String time, String address, String city, String state, String zip){
        this.eventId = eventId;
        this.organization = organization;
        this.name = name;
        this.description = description;
        this.eventType = eventType;
        this.date = date;
        this.time = time;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    @Override
    public String toString(){
        String shortDescription = this.description;
        if(shortDescription.length() > 100){
            shortDescription = shortDescription.substring(0, 100) + "...";
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        builder.append("\n");
        builder.append(this.organization);
        builder.append("\n");
        builder.append(this.date + " " + this.time);
        builder.append("\nDescription: ");
        builder.append(shortDescription);
        return builder.toString();
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getContent(){
        StringBuilder builder = new StringBuilder();
        
        builder.append("Hosted by: ");
        builder.append(this.organization);
        builder.append("\nDate/Time: ");
        builder.append(this.date + " " + this.time);
        builder.append("\nAddress: ");
        builder.append(this.address);
        builder.append(", ");
        builder.append(this.city);
        builder.append(", ");
        builder.append(this.state);
        builder.append(" ");
        builder.append(this.zip);
        builder.append("\nDescription:\n");
        builder.append(this.description);
        return builder.toString();
    }
}
