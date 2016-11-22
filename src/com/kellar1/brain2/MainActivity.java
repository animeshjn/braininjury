package com.kellar1.brain2;

import com.kellar2.brain2.R;
import com.kellar2.brain2.AreaOfConcern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class MainActivity extends Activity {
  	private static Context context;
  	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
  
    	
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    	
    	super.onCreate(savedInstanceState);
    	getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
    	MainActivity.context = getApplicationContext();
    	setContentView(R.layout.splash1);
        //display the logo for 5 seconds,
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished){} 

            @Override
            public void onFinish(){
            	setContentView(R.layout.splash2);
            	new CountDownTimer(5000,1000){
                    @Override
                    public void onTick(long millisUntilFinished){} 

                    @Override
                    public void onFinish(){
                           //set the new Content of your activity
                    	Intent intent = new Intent(context, AreaOfConcern.class);
                    	 startActivity(intent);
                           //MainActivity.this.setContentView(R.layout.areaofconcernlandscape);
                    }
               }.start();
            }
       }.start();
       
       
    	//setContentView(R.layout.areaofconcernlandscape);
    	//setContentView(R.layout.strategies);
        //setContentView(R.layout.activity_main);
        
        
        
    
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
