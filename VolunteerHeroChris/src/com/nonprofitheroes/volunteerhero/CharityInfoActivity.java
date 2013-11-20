package com.nonprofitheroes.volunteerhero;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CharityInfoActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    static SharedPreferences settings;
    private Charity charity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_info);
        
        //Get charity selected on previous screen
        this.charity = DatabaseConnection.getCharity(MyCharitiesActivity.getCharity());
        
        //Fill out info fields on screen from charity
        TextView charityName = (TextView) findViewById(R.id.charity_info_name);
        charityName.setText(this.charity.getName());
        
        TextView charityContent = (TextView) findViewById(R.id.charity_info_content);
        charityContent.setText(this.charity.getContent());
        
        //get user points with this charity
        settings = getSharedPreferences(PREFS_NAME, 0);
        this.charity.setPoints(settings.getString("deviceId", ""));
        this.charity.setLevels();
        
        //If the charity has reward levels, fill out the corresponding views
        if (this.charity.getLevels().size() != 0){
            fillRewardLevels();
        }
        
    }
    
    private void fillRewardLevels(){
        //Set text of reward levels completed
        TextView completed = (TextView) findViewById(R.id.charity_info_completed);
        completed.setText(getResources().getString(R.string.completed));
        
        List<RewardLevel> completedLevels = this.charity.getCompleted();
        if (completedLevels.size() != 0){
            TextView completedLevelsFill = (TextView) findViewById(R.id.charity_info_completed_fill);
            
            StringBuilder builder = new StringBuilder();
            for (RewardLevel level : completedLevels){
                builder.append(level.nameAndReward());
            }
            
            completedLevelsFill.setText(builder.toString());
        }
        
        //Find the next reward level if one exists and fill info
        RewardLevel nextLevel = this.charity.getNextLevel();
        
        if (nextLevel != null){
            TextView next = (TextView) findViewById(R.id.charity_info_next);
            next.setText(getResources().getString(R.string.next));
            
            TextView nextFill = (TextView) findViewById(R.id.charity_info_next_fill);
            nextFill.setText(nextLevel.nameAndReward());
            
            //Show user progress towards this reward level
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.reward_progress);
            progressBar.setMax(100);
            progressBar.setProgress((this.charity.getPoints() * 100) / nextLevel.getPointValue());
            progressBar.setVisibility(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.charity_info, menu);
        return true;
    }

}
