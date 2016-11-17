package com.example.brain2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ToggleButton;

public class AreaOfConcern extends Activity {
	private static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		//getActionBar().setTitle("Area of Concern");
		//getActionBar().setDisplayShowHomeEnabled(true);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
	    
		setContentView(R.layout.areaofconcernlandscape);
		 //Toast.makeText(context, "test message 1", Toast.LENGTH_SHORT).show();
		 AreaOfConcern.context = AreaOfConcern.this;
		
		 setAllLongClickListeners();
		 
		//Toast.makeText(context, "test message 2", Toast.LENGTH_SHORT).show();
		
		Button next = (Button) findViewById(R.id.next);
		final AreaOfConcern activityObject=this;
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(isSelectionEmpty())
				{
					AlertDialog.Builder info = new AlertDialog.Builder(context);
					info.setMessage("Please select at least one\n area of concern ");
					info.setCancelable(true);
					
					info.setPositiveButton(
					    "OK",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					            dialog.cancel();
					        }
					    });

					AlertDialog infoAlert = info.create();
					infoAlert.show();
				}
				else
					{
					Intent intent = new Intent(context, Strategies.class);
               	 	 IntentData data=new IntentData();
               	 	 intent.putExtra("selectedcontent", data.getSelectedContent(activityObject));
               	 	 intent.putExtra("physical", data.getPhysical());
               	 	 intent.putExtra("behavioral", data.getBehavioral());
               	 	 intent.putExtra("cognition", data.getCognition());
               	 	 startActivity(intent);
					
					}
			}
		});
		
		
		
		
		
		
	}
	
	
		
		
		
		
		
		
	
	
	

	public void setAllLongClickListeners(){
	
		//For fatigue
		ToggleButton fatigue= (ToggleButton) findViewById(R.id.fatigue);
		fatigue.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String fatigueinformation=res.getString(R.string.fatigueinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder fatigueinfo = new AlertDialog.Builder(context);
				fatigueinfo.setMessage(""+fatigueinformation);
				fatigueinfo.setCancelable(true);
				fatigueinfo.setTitle("Fatigue/ Endurance");
				fatigueinfo.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = fatigueinfo.create();
				infoAlert.show();
				
				return true;
			}
			
	    });

		// For Sensory 
		ToggleButton sensory= (ToggleButton) findViewById(R.id.sensory);
		sensory.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String sensoryinformation=res.getString(R.string.sensoryinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+sensoryinformation);
				info.setCancelable(true);
				info.setTitle("Sensory / Motor");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
		
		
		
		// For Self monitoring
		
		ToggleButton self= (ToggleButton) findViewById(R.id.self);
		self.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String selfinformation=res.getString(R.string.selfmonitoringinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+selfinformation);
				info.setCancelable(true);
				info.setTitle("Self Monitoring");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
		
		//For Attention
		
		ToggleButton attention= (ToggleButton) findViewById(R.id.attention);
		attention.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String attentioninformation=res.getString(R.string.attentioninfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+attentioninformation);
				info.setCancelable(true);
				info.setTitle("Attention");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
		
		//FOr receptive
		
		ToggleButton receptive= (ToggleButton) findViewById(R.id.receptive);
		receptive.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String receptiveInformation=res.getString(R.string.receptiveinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+receptiveInformation);
				info.setCancelable(true);
				info.setTitle("Receptive");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
		
		// for expressive
		ToggleButton expressive= (ToggleButton) findViewById(R.id.expressive);
		expressive.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String expressiveInformation=res.getString(R.string.expressiveinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+expressiveInformation);
				info.setCancelable(true);
				info.setTitle("Expressive");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });

		//for speed of information processing
		
		ToggleButton speed= (ToggleButton) findViewById(R.id.speed);
		speed.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String speedInformation=res.getString(R.string.speedinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
					info.setMessage(""+speedInformation);
					info.setCancelable(true);
					info.setTitle("Speed of Information processing");
					info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });

		//planning
		ToggleButton planning= (ToggleButton) findViewById(R.id.planning);
		planning.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String planningInformation=res.getString(R.string.planninginfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+planningInformation);
				info.setCancelable(true);
				info.setTitle("Planning/ Organizing");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });

		
		//Memory
		
		ToggleButton memory= (ToggleButton) findViewById(R.id.memory);
		memory.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String memoryInformation=res.getString(R.string.memoryinfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+memoryInformation);
				info.setCancelable(true);
				info.setTitle("Memory");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
		

		//Reasoning
		
		ToggleButton reasoning= (ToggleButton) findViewById(R.id.reasoning);
		reasoning.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String reasoningInformation=res.getString(R.string.reasoninginfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+reasoningInformation);
				info.setCancelable(true);
				info.setTitle("Reasoning");
				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
		
		
		//problem solving
		
		ToggleButton solving= (ToggleButton) findViewById(R.id.solving);
		solving.setOnLongClickListener(new OnLongClickListener() { 
	      
			Resources res = getResources();
			String solvingInformation=res.getString(R.string.problemsolvinginfo);
			@Override
			public boolean onLongClick(View v) {
				
				AlertDialog.Builder info = new AlertDialog.Builder(context);
				info.setMessage(""+solvingInformation);
				info.setTitle("Problem Solving");
				info.setCancelable(true);

				info.setPositiveButton(
				    "OK",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog infoAlert = info.create();
				infoAlert.show();
				
				return true;
			}
			
	    });
	
	}
	
	public boolean isSelectionEmpty(){
		
		ToggleButton fatigue,self,sensory,attention,receptive;
		ToggleButton expressive,speed,memory,reasoning,solving,planning;
		// get all toggleButtons
		fatigue= (ToggleButton)findViewById(R.id.fatigue);
		self= (ToggleButton)findViewById(R.id.self);
		sensory= (ToggleButton)findViewById(R.id.sensory);
		attention= (ToggleButton)findViewById(R.id.attention);
		receptive= (ToggleButton)findViewById(R.id.receptive);
		expressive= (ToggleButton)findViewById(R.id.expressive);
		speed= (ToggleButton)findViewById(R.id.speed);
		memory= (ToggleButton)findViewById(R.id.memory);
		reasoning= (ToggleButton)findViewById(R.id.reasoning);
		solving= (ToggleButton)findViewById(R.id.solving);
		planning= (ToggleButton)findViewById(R.id.planning);
		
		//check if either one of them is checked if yes then selection is not empty
		if(fatigue.isChecked()||self.isChecked()||sensory.isChecked()||attention.isChecked()
				||receptive.isChecked()||expressive.isChecked()||speed.isChecked()
				||memory.isChecked()||reasoning.isChecked()||solving.isChecked()
				||planning.isChecked())
		return false;
		
		else
			return true;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.area_of_concern, menu);
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
