package com.kellar2.brain2;




import com.kellar2.brain2.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Start extends Activity {
	private static Context context;
	int backButtonCount=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayUseLogoEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setHomeButtonEnabled(false);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.startaction);
		
		
		setContentView(R.layout.activity_start);
		
		context = this;

		AlertDialog.Builder info = new AlertDialog.Builder(context);
		info.setTitle("\t\tConfidentiality Agreement");
	
		info.setMessage(getResources().getString(R.string.agreement).toString());
		info.setCancelable(true);
		
		info.setPositiveButton("Agree",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						
                    	 dialog.dismiss();
					}
				});

		 AlertDialog infoAlert = info.create();
		 infoAlert.setCanceledOnTouchOutside(false);
		 infoAlert.show();
		// infoAlert.show();
		Button start = (Button) findViewById(R.id.start);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, AreaOfConcern.class);
           	 startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
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
	
	@Override
	public void onBackPressed()
	{
	    if(backButtonCount >= 1)
	    {
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
	    }
	    else
	    {
	        Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
	        backButtonCount++;
	    }
	}
	
}
