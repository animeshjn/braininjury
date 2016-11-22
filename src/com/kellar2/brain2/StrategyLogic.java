package com.kellar2.brain2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

public class StrategyLogic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> selectedAreas;
	ArrayList<String> selectedPhysical;
	
	public ArrayList<String> getSelectedPhysical()
	{
		return selectedPhysical;
	}

	public ArrayList<String> getSelectedBehavioral() 
	{
		return selectedBehavioral;
	}

	public ArrayList<String> getSelectedCognition() {
		return selectedCognition;
	}


	ArrayList<String> selectedBehavioral;
	ArrayList<String> selectedCognition;
	Map <String,ArrayList<String>> map;
	
	public ArrayList<String> getSelectedAreas()
	{
		return selectedAreas;
	}
	
	public StrategyLogic() {
		super();
	}

	@SuppressWarnings("unchecked")
	public StrategyLogic(Activity activity)
	{
		super();
		 selectedAreas = (ArrayList<String>)activity.getIntent()
					.getSerializableExtra("selectedcontent");
		 
		 selectedPhysical = (ArrayList<String>)activity.getIntent()
					.getSerializableExtra("physical");
		 
		 selectedBehavioral = (ArrayList<String>)activity.getIntent()
					.getSerializableExtra("behavioral");
		 selectedCognition=(ArrayList<String>)activity.getIntent()
					.getSerializableExtra("cognition");
		
	}
	public void setSelectedAreas(Activity activity)
	{
		
			
	}
	
	public String getHeader(String text){
		//take id 
		//search in strings
		//return array 
		String header=null;
		if(text.contains("Fatigue")||text.contains("Sensory"))
		{
			header="Physical";
		}
		else if(text.contains("Self-Monitoring")||text.contains("Attention"))
		{
			header="Behavioural / Social";
		}
		else
			header="General Cognition";
		
		return header;
	}
	
	
public void initMap(){
	
	@SuppressWarnings("unused")
	Map <String,ArrayList<String>> map=new HashMap <String,ArrayList<String>>();
	
	
	
}
	
	
}
