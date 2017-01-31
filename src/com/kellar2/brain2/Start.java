package com.kellar2.brain2;




import com.kellar.brain2.PdfBean;
import com.kellar2.brain2.R;
import com.kellar2.brain2.R.menu;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
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
		
		TextView agreement = new TextView(this);
		agreement.setText(getResources().getString(R.string.agreement).toString());
		agreement.setTextSize(18);
		agreement.setPadding(10, 10, 10, 10);
		agreement.setGravity(Gravity.CENTER_HORIZONTAL);
		
		//info.setMessage(getResources().getString(R.string.agreement).toString());
		info.setView(agreement);
		info.setCancelable(false);
		
		info.setPositiveButton("Agree",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						       	 dialog.dismiss();
					}
				});
		
		 AlertDialog infoAlert = info.create();
		 infoAlert.setCanceledOnTouchOutside(false);
		 infoAlert.setCancelable(false);
		 infoAlert.show();
//		 infoAlert.getWindow()
		 
		// infoAlert.show();
		Button start = (Button) findViewById(R.id.start);
		start.setText("Start");
		start.setTextColor(Color.WHITE);
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
	
	@SuppressLint("NewApi") public void buttonHandler(View view)
	{
	int id=view.getId();
    	int abswidth=0;
		switch (view.getId()) {

 		case R.id.mainactivity_kihdbttn:
 			LinearLayout menu=(LinearLayout)findViewById(R.id.mainactivity_flyupmenu);
 			//int h=menu.getHeight();
 			if(menu.getHeight()>0){
 				for(int i=0;i<4;i++){
 					LinearLayout menu_ll=(LinearLayout)findViewById(100+i);
 					menu.removeView(menu_ll);
 				}
    			return;
    		}
 			//pull menu up
 			DisplayMetrics display = this.getResources().getDisplayMetrics();
 	        int scrn_width = display.widthPixels;
 	        
 	        if(scrn_width>640){
 	        	scrn_width=640;//
 	        //menu.setPadding((display.widthPixels-scrn_width)/2, 0, (display.widthPixels-scrn_width)/2, 0);
 	        }
 	       
 	        int scrn_height = display.heightPixels;
 	        int bttnSz=scrn_height/7;
 	        
 	        
 	        if(bttnSz<50 && scrn_width>=200){bttnSz=50;}
 	        
    		LinearLayout.LayoutParams llparams =new LinearLayout.LayoutParams(0, bttnSz);
    		llparams.weight=0.25f;
    		llparams.setMarginStart(0);
    		llparams.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    		for(int i=0;i<4;i++){
    			LinearLayout menu_ll=new LinearLayout(this);//(LinearLayout)findViewById(R.id.chapters_ll);
    			menu_ll.setId(100+i);
    			menu_ll.setLayoutParams(llparams);
    			ImageButton ib=new ImageButton(this);
    			Drawable dr = null;
    			switch (i){
    				case 0:
    					dr=getResources().getDrawable(R.drawable.about_icon);
    					break;
    				case 1:
        				dr=getResources().getDrawable(R.drawable.legal_icon);
        				break;
    				case 2:
        				dr=getResources().getDrawable(R.drawable.help_icon);
        				break;
    				case 3:
        				dr=getResources().getDrawable(R.drawable.more_icon);
        				break;
    			}
    			ib.setMaxHeight(bttnSz);
    		    ib.setMaxWidth(bttnSz);
    		    ib.setScaleType(ScaleType.FIT_START);
    		    ib.setBackgroundColor(Color.TRANSPARENT);
    		    ib.setBackground(getResources().getDrawable(R.drawable.infobuttons));
    		    ib.setImageDrawable(dr);// .setI BackgroundResource(img);
    		    ib.setId(1000+i);
    		    
    		    ib.setOnClickListener(new View.OnClickListener(){
    		        @Override
    		        public void onClick(View v) {
    		              // put code on click operation
    		        	buttonHandler(v);
    		        }
    		   });
    		    menu_ll.addView(ib);
    		   menu_ll.setBackground(getResources().getDrawable(R.drawable.infobuttons));
    		   menu.setBackground(getResources().getDrawable(R.drawable.infobuttons));
    			menu.addView(menu_ll);
    			//abswidth=menu.getWidth();
    			
    		}
    		ViewGroup.LayoutParams ivL;
    		//new LinearLayout.LayoutParams(bttnSz*4+bttnSz/2, view.getHeight());
    		ImageView curr= (ImageView)view;
 			ivL=view.getLayoutParams();
 			curr.setLayoutParams(menu.getLayoutParams());
 			//curr.setRight(0);
    		//ivL.width=bttnSz*4+bttnSz/2+1;
    		//ivL.gravity=Gravity.END;
    		
    		//ivL.setLayoutDirection(LinearLayout.LAYOUT_DIRECTION_RTL);
    		//ivL.setMarginEnd(0);
    		//curr.setLayoutParams(ivL);
 			
    		break;
	    }
		
		
		if(id>=1000 && id<=1003){
			switch(id){
			case 1000: {//about
				startActivity(new Intent(context,AboutActivity.class));
    			break;
    		}
			case 1001: {//legal
				startActivity(new Intent(context,LegalActivity.class));
	    		break;
    		}
			case 1002: {//help
    			
				new PdfBean().openHelp(context);
	    		
	    		break;
    		}
			case 1003:{//more
				
    			String hrf="http://atwaresolutions.com/apps";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(hrf)));
				return;
    		}
			}
			
    		//Intent intentAbout = new Intent(MainActivity.this, HelpPage.class);
    		//startActivity(intentAbout);
    	}
	}
	
	
}
