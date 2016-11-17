package com.example.brain2;

import java.io.Serializable;

import java.util.ArrayList;

import android.widget.ToggleButton;

public class IntentData implements Serializable{

	public ArrayList<String> physical=new ArrayList<String>();
	public ArrayList<String> behavioral=new ArrayList<String>();
	public ArrayList<String> cognition=new ArrayList<String>();
	ArrayList<String> selected;
	private static final long serialVersionUID = 1L;

	
public ArrayList<String> getSelectedContent(AreaOfConcern activity){
		
		
		
		
		ToggleButton fatigue,self,sensory,attention,receptive;
		ToggleButton expressive,speed,memory,reasoning,solving,planning;
		// get all toggleButtons
		fatigue= (ToggleButton)activity.findViewById(R.id.fatigue);
		self= (ToggleButton)activity.findViewById(R.id.self);
		sensory= (ToggleButton)activity.findViewById(R.id.sensory);
		attention= (ToggleButton)activity.findViewById(R.id.attention);
		receptive= (ToggleButton)activity.findViewById(R.id.receptive);
		expressive= (ToggleButton)activity.findViewById(R.id.expressive);
		speed= (ToggleButton)activity.findViewById(R.id.speed);
		memory= (ToggleButton)activity.findViewById(R.id.memory);
		reasoning= (ToggleButton)activity.findViewById(R.id.reasoning);
		solving= (ToggleButton)activity.findViewById(R.id.solving);
		planning= (ToggleButton)activity.findViewById(R.id.planning);
		
		ArrayList<ToggleButton> allButtons =new ArrayList<ToggleButton>();
		allButtons.add(fatigue);
		allButtons.add(self);
		allButtons.add(sensory);
		allButtons.add(attention);
		allButtons.add(receptive);
		allButtons.add(expressive);
		allButtons.add(speed);
		allButtons.add(memory);
		allButtons.add(reasoning);
		allButtons.add(planning);
		allButtons.add(solving);
		 
		selected=new ArrayList<String>();
		for (ToggleButton toggleButton : allButtons) {
			if(toggleButton.isChecked())
			{
				selected.add((String) toggleButton.getText());
				
				String text=(String) toggleButton.getText();
				if(text.contains("Fatigue")||text.contains("Sensory"))
				{
					physical.add(text);
				}
				else if(text.contains("Self-Monitoring")||text.contains("Attention"))
				{
					behavioral.add(text);
				}
				else
					cognition.add(text);
				
				//				if(toggleButton.getId().equals("")){}
				
			}
		}
		
		
		
//		    for (int i = 0; i < layout.getChildCount(); i++) {
//		        View v = layout.getChildAt(i);
//		        if (v instanceof ToggleButton) {
//		          ToggleButton tb=(ToggleButton)v;
//		        	selected.add(""+tb.getText());
//		        	Toast.makeText(activity, tb.getText(), Toast.LENGTH_SHORT).show();
//		        } 
//		   }
		    
		    
		return selected;
		}


public ArrayList<String> getPhysical() {
	return physical;
}


public ArrayList<String> getBehavioral() {
	return behavioral;
}


public ArrayList<String> getCognition() {
	return cognition;
}
	
	
	
}
