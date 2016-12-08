package com.kellar.brain2;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kellar2.brain2.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.widget.CheckBox;
import android.widget.TextView;

/*Class Designed to Handle check box logic very important to handle,store and restore the states of checkboxes*/
public class CheckHandle {

	// Handling checkBoxes
	boolean isFatigue, isSelf, isSensory, isAttention;
	boolean isReceptive, isExpressive, isSpeed;
	boolean isPlanning, isMemory, isReasoning, isProblem;
	Context context;
	boolean strategies[][] = new boolean[15][150]; // considering default to
	public boolean substrategies[] = new boolean[200];
	boolean firstSubflags[] = new boolean[25];
	boolean comments[]=new boolean[15];
	String commentText[]= new String[15];
		
	public boolean getComments(int index) {
		return comments[index];
	}

	public void setComments(boolean comments, int index) {
		this.comments[index] = comments;
	}

	public String getCommentText(int index) {
		return commentText[index];
	}

	public void setCommentText(String commentText, int index) {
		this.commentText[index] = commentText;
	}

	public Map<String, List<Integer>> subAreaTagMapping = new HashMap<String, List<Integer>>();
	@SuppressLint("UseSparseArrays") 
	public Map<Integer, CharSequence> subStrategyMapping = new HashMap<Integer, CharSequence>();
	
	public boolean[][] getStrategies() {
		return strategies;
	}

	// false
	
	public CheckHandle() {
		super();
		for (boolean sub[] : strategies) {
			Arrays.fill(sub, Boolean.FALSE);
			Arrays.fill(firstSubflags, Boolean.TRUE);

		}
		Arrays.fill(comments, Boolean.FALSE);
		initMap();
		Arrays.fill(substrategies, Boolean.FALSE);

	}

	/*
	 * THIS METHOD INITTIALIZES THE MAP WHICH IS GIVEN HERE TO MAP SUBSTRATEGIES
	 * WITH A PARTICULAR TAG
	 * 
	 * THE AREA IS TAGGED WITH 0,1,2 ETC DEPENDING UPON NUMBER OF SUBSTRATEGIES
	 * A PARTICULAR STRATEGY HAS
	 * 
	 * THIS ENABLES THE APPLICATION TO RECOGNIZE THE SUBSTRAGIES UNIQUELY AS
	 * WELL AS GROUPING THEM TOGETHER
	 */
	private void initMap() {

		// Fatigue0
		ArrayList<Integer> fatigue0 = new ArrayList<Integer>();
		for (int i = 0; i <= 4; i++) {
			fatigue0.add(i);
		}

		subAreaTagMapping.put("Fatigue0", fatigue0);

		// Fatigue1
		ArrayList<Integer> fatigue1 = new ArrayList<Integer>();
		for (int i = 5; i <= 5; i++) {
			fatigue1.add(i);
		}

		subAreaTagMapping.put("Fatigue1", fatigue1);

		//
		// Fatigue2
		ArrayList<Integer> fatigue2 = new ArrayList<Integer>();
		for (int i = 6; i <= 8; i++) {
			fatigue2.add(i);
		}

		subAreaTagMapping.put("Fatigue2", fatigue2);
		// /-------------------------------------------------------------------------------------//
		// sensory
		// Sensory0
		ArrayList<Integer> sensory0 = new ArrayList<Integer>();
		for (int i = 9; i <= 13; i++) {
			sensory0.add(i);
		}

		subAreaTagMapping.put("Sensory0", sensory0);

		// Sensory1
		ArrayList<Integer> sensory1 = new ArrayList<Integer>();
		for (int i = 14; i <= 19; i++) {
			sensory1.add(i);
		}

		subAreaTagMapping.put("Sensory1", sensory1);

		// Sensory2
		ArrayList<Integer> sensory2 = new ArrayList<Integer>();
		for (int i = 20; i <= 26; i++) {
			sensory2.add(i);
		}

		subAreaTagMapping.put("Sensory2", sensory2);
		// /-------------------------------------------------------------------------------------//
		// self 0 to 6
		// Self0
		ArrayList<Integer> self0 = new ArrayList<Integer>();
		for (int i = 27; i <= 28; i++) {
			self0.add(i);
		}

		subAreaTagMapping.put("Self0", self0);

		// Self1
		ArrayList<Integer> self1 = new ArrayList<Integer>();
		for (int i = 29; i <= 32; i++) {
			self1.add(i);
		}

		subAreaTagMapping.put("Self1", self1);

		// Self2
		ArrayList<Integer> self2 = new ArrayList<Integer>();
		for (int i = 33; i <= 33; i++) {
			self2.add(i);
		}

		subAreaTagMapping.put("Self2", self2);

		// Self3
		ArrayList<Integer> self3 = new ArrayList<Integer>();
		for (int i = 34; i <= 34; i++) {
			self3.add(i);
		}

		subAreaTagMapping.put("Self3", self3);

		// Self4
		ArrayList<Integer> self4 = new ArrayList<Integer>();
		for (int i = 35; i <= 35; i++) {
			self4.add(i);
		}

		subAreaTagMapping.put("Self4", self4);

		// Self5
		ArrayList<Integer> self5 = new ArrayList<Integer>();
		for (int i = 36; i <= 36; i++) {
			self5.add(i);
		}

		subAreaTagMapping.put("Self5", self5);

		// Self6
		ArrayList<Integer> self6 = new ArrayList<Integer>();
		for (int i = 37; i <= 39; i++) {
			self6.add(i);
		}

		subAreaTagMapping.put("Self6", self6);

		// Attention Data mapping//
		// -------------------------------------------------------------------------------------//

		// Attention0
		ArrayList<Integer> attention0 = new ArrayList<Integer>();
		for (int i = 40; i <= 41; i++) {
			attention0.add(i);
		}

		subAreaTagMapping.put("Attention0", attention0);

		// Attention1
		ArrayList<Integer> attention1 = new ArrayList<Integer>();
		for (int i = 42; i <= 45; i++) {
			attention1.add(i);
		}

		subAreaTagMapping.put("Attention1", attention1);

		// Attention2
		ArrayList<Integer> attention2 = new ArrayList<Integer>();
		for (int i = 46; i <= 46; i++) {
			attention2.add(i);
		}

		subAreaTagMapping.put("Attention2", attention2);

		// Attention3
		ArrayList<Integer> attention3 = new ArrayList<Integer>();
		for (int i = 47; i <= 48; i++) {
			attention3.add(i);
		}

		subAreaTagMapping.put("Attention3", attention3);

		// Attention4
		ArrayList<Integer> attention4 = new ArrayList<Integer>();
		for (int i = 49; i <= 50; i++) {
			attention4.add(i);
		}

		subAreaTagMapping.put("Attention4", attention4);

		// RECEPTIVE//
		// -------------------------------------------------------------------------------------//

		// Receptive0
		ArrayList<Integer> receptive0 = new ArrayList<Integer>();
		for (int i = 51; i <= 54; i++) {
			receptive0.add(i);
		}
		subAreaTagMapping.put("Receptive0", receptive0);

		// Receptive1
		ArrayList<Integer> receptive1 = new ArrayList<Integer>();
		for (int i = 55; i <= 60; i++) {
			receptive1.add(i);
		}
		subAreaTagMapping.put("Receptive1", receptive1);

		// Receptive2
		ArrayList<Integer> receptive2 = new ArrayList<Integer>();
		for (int i = 61; i <= 65; i++) {
			receptive2.add(i);
		}
		subAreaTagMapping.put("Receptive2", receptive2);

		// Receptive3
		ArrayList<Integer> receptive3 = new ArrayList<Integer>();
		for (int i = 66; i <= 67; i++) {
			receptive3.add(i);
		}
		subAreaTagMapping.put("Receptive3", receptive3);

		// Receptive4
		ArrayList<Integer> receptive4 = new ArrayList<Integer>();
		for (int i = 68; i <= 73; i++) {
			receptive4.add(i);
		}
		subAreaTagMapping.put("Receptive4", receptive4);

		// Expressive//
		// -------------------------------------------------------------------------------------//

		// Expressive0
		ArrayList<Integer> expressive0 = new ArrayList<Integer>();
		for (int i = 74; i <= 76; i++) {
			expressive0.add(i);
		}
		subAreaTagMapping.put("Expressive0", expressive0);

		// Expressive1
		ArrayList<Integer> expressive1 = new ArrayList<Integer>();
		for (int i = 77; i <= 78; i++) {
			expressive1.add(i);
		}
		subAreaTagMapping.put("Expressive1", expressive1);

		// Expressive2
		ArrayList<Integer> expressive2 = new ArrayList<Integer>();
		for (int i = 79; i <= 81; i++) {
			expressive2.add(i);
		}
		subAreaTagMapping.put("Expressive2", expressive2);

		// SPEED //
		// -------------------------------------------------------------------------------------//

		// Speed0
		ArrayList<Integer> speed0 = new ArrayList<Integer>();
		for (int i = 82; i <= 83; i++) {
			speed0.add(i);
		}
		subAreaTagMapping.put("Speed0", speed0);

		// Speed1
		ArrayList<Integer> speed1 = new ArrayList<Integer>();
		for (int i = 84; i <= 85; i++) {
			speed1.add(i);
		}
		subAreaTagMapping.put("Speed1", speed1);

		// Speed2
		ArrayList<Integer> speed2 = new ArrayList<Integer>();
		for (int i = 86; i <= 86; i++) {
			speed2.add(i);
		}
		subAreaTagMapping.put("Speed2", speed2);

		// Speed3
		ArrayList<Integer> speed3 = new ArrayList<Integer>();
		for (int i = 87; i <= 92; i++) {
			speed3.add(i);
		}
		subAreaTagMapping.put("Speed3", speed3);

		// Speed4
		ArrayList<Integer> speed4 = new ArrayList<Integer>();
		for (int i = 93; i <= 96; i++) {
			speed4.add(i);
		}
		subAreaTagMapping.put("Speed4", speed4);

		// Speed5
		ArrayList<Integer> speed5 = new ArrayList<Integer>();
		for (int i = 97; i <= 101; i++) {
			speed5.add(i);
		}
		subAreaTagMapping.put("Speed5", speed5);

		
		// MEMORY//
				// -------------------------------------------------------------------------------------//

				// Memory0
				ArrayList<Integer> memory0 = new ArrayList<Integer>();
				for (int i = 102; i <= 104; i++) {
					memory0.add(i);
				}
				subAreaTagMapping.put("Memory0", memory0);

				// Memory1
				ArrayList<Integer> memory1 = new ArrayList<Integer>();
				for (int i = 105; i <= 107; i++) {
					memory1.add(i);
				}
				subAreaTagMapping.put("Memory1", memory1);

				// Memory2
				ArrayList<Integer> memory2 = new ArrayList<Integer>();
				for (int i = 108; i <= 114; i++) {
					memory2.add(i);
				}
				subAreaTagMapping.put("Memory2", memory2);

				// REASONING//
				// -------------------------------------------------------------------------------------//

				// Reasoning0
				ArrayList<Integer> reasoning0 = new ArrayList<Integer>();
				for (int i = 115; i <= 116; i++) {
					reasoning0.add(i);
				}
				subAreaTagMapping.put("Reasoning0", reasoning0);

				// Reasoning1
				ArrayList<Integer> reasoning1 = new ArrayList<Integer>();
				for (int i = 117; i <= 119; i++) {
					reasoning1.add(i);
				}
				subAreaTagMapping.put("Reasoning1", reasoning1);

				// Reasoning2
				ArrayList<Integer> reasoning2 = new ArrayList<Integer>();
				for (int i = 120; i <= 121; i++) {
					reasoning2.add(i);
				}
				subAreaTagMapping.put("Reasoning2", reasoning2);

				// Reasoning3
				ArrayList<Integer> reasoning3 = new ArrayList<Integer>();
				for (int i = 122; i <= 125; i++) {
					reasoning3.add(i);
				}
				subAreaTagMapping.put("Reasoning3", reasoning3);

		// Planning//
		// -------------------------------------------------------------------------------------//
		// Planning0
		ArrayList<Integer> planning0 = new ArrayList<Integer>();
		for (int i = 126; i <= 127; i++) {
			planning0.add(i);
		}
		subAreaTagMapping.put("Planning0", planning0);
		// Planning1
		ArrayList<Integer> planning1 = new ArrayList<Integer>();
		for (int i = 128; i <= 129; i++) {
			planning1.add(i);
		}
		subAreaTagMapping.put("Planning1", planning1);

		// Planning2
		ArrayList<Integer> planning2 = new ArrayList<Integer>();
		for (int i = 130; i <= 134; i++) {
			planning2.add(i);
		}
		subAreaTagMapping.put("Planning2", planning2);

		
		// PROBLEM SOLVING //
		// -------------------------------------------------------------------------------------//

		// Problem0
		ArrayList<Integer> problem0 = new ArrayList<Integer>();
		for (int i = 135; i <= 139; i++) {
			problem0.add(i);
		}
		subAreaTagMapping.put("Problem0", problem0);

		// Problem1
		ArrayList<Integer> problem1 = new ArrayList<Integer>();
		for (int i = 140; i <= 141; i++) {
			problem1.add(i);
		}
		subAreaTagMapping.put("Problem1", problem1);

		// Problem2
		ArrayList<Integer> problem2 = new ArrayList<Integer>();
		for (int i = 142; i <= 143; i++) {
			problem2.add(i);
		}
		subAreaTagMapping.put("Problem2", problem2);

		// Problem3
		ArrayList<Integer> problem3 = new ArrayList<Integer>();
		for (int i = 144; i <= 144; i++) {
			problem3.add(i);
		}
		subAreaTagMapping.put("Problem3", problem3);

		// Problem4
		ArrayList<Integer> problem4 = new ArrayList<Integer>();
		for (int i = 145; i <= 147; i++) {
			problem4.add(i);
		}
		subAreaTagMapping.put("Problem4", problem4);

	}

	// GET THE SUBSTRATEGIES BOOLEAN VALUE BY INDEX
	public boolean getSubstrategies(int index) {
		return substrategies[index];
	}

	public void setSubstrategies(int index, boolean substrategies) {
		this.substrategies[index] = substrategies;

	}

	/*
	 * THIS ADDS THE CASES FOR THE ADJUSTMENT FACTOR TO THE PROVIDED STRATEGY
	 * 
	 * AND HENCE GENERATES A UNIQUE NON-RANDOM ID
	 */
	public int getSubCheckId(int strategyId, String strategyArea,
			int subStrategy) {

		String[] cases = { "Fatigue", "Sensory", "Self", "Attention",
				"Receptive", "Expressive", "Speed", "Planning", "Memory",
				"Reasoning", "Problem" };

		int i;
		for (i = 0; i < cases.length; i++)
			if (strategyArea.contains(cases[i]))
				break;

		int adjustment = 0;

		switch (i) {

		case 0: // For Fatigue
				// Strategies
			switch (strategyId) { // 3 strategies for this case
			case 0:
				adjustment = 0;
				break;
			case 1:
				adjustment = 5;
				break;
			case 2:
				adjustment = 6;
				break;
			}

			break;

		case 1: // For Sensory
			switch (strategyId) { // 3 strategies for this case
			case 0:
				adjustment = 9;
				break;
			case 1:
				adjustment = 14;
				break;
			case 2:
				adjustment = 20;
				break;
			}
			break;

		case 2: // For Self-Monitoring

			switch (strategyId) { // 5 strategies for this case
			case 0:
				adjustment = 27;
				break;
			case 1:
				adjustment = 29;
				break;
			case 2:
				adjustment = 33;
				break;

			case 3:
				adjustment = 34;
				break;
			case 4:
				adjustment = 35;
				break;
			case 5:
				adjustment = 36;
				break;

			case 6:
				adjustment = 37;
				break;

			}
			break;

		case 3: // For Attention

			switch (strategyId) { // 3 strategies for this case
			case 0:
				adjustment = 40;
				break;
			case 1:
				adjustment = 42;
				break;
			case 2:
				adjustment = 46;
				break;

			case 3:
				adjustment = 47;
				break;
			case 4:
				adjustment = 49;
				break;

			}
			break;

		case 4: // For Receptive Communication

			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 51;
				break;
			case 1:
				adjustment = 55;
				break;
			case 2:
				adjustment = 61;
				break;

			case 3:
				adjustment = 66;
				break;
			case 4:
				adjustment = 68;
				break;

			}
			break;
		case 5:// For Expressive communication

			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 74;
				break;
			case 1:
				adjustment = 77;
				break;
			case 2:
				adjustment = 79;
				break;

			}
			break;

		case 6:// Speed of information processing
			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 82;
				break;
			case 1:
				adjustment = 84;
				break;
			case 2:
				adjustment = 86;
				break;
			case 3:
				adjustment = 87;
				break;
			case 4:
				adjustment = 93;
				break;
			case 5:
				adjustment = 97;
				break;

			}
			break;
		case 7:// Planning
			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 126;
				break;
			case 1:
				adjustment = 128;
				break;
			case 2:
				adjustment = 130;
				break;

			}
			break;

		case 8:// Memory
			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 102;
				break;
			case 1:
				adjustment = 105;
				break;
			case 2:
				adjustment = 108;
				break;

			}
			break;
		case 9:// Reasoning
			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 115;
				break;
			case 1:
				adjustment = 117;
				break;
			case 2:
				adjustment = 120;
				break;

			case 3:
				adjustment = 122;
				break;

			}
			break;

		case 10:// Problem Solving
			switch (strategyId) { // strategies for this case
			case 0:
				adjustment = 135;
				break;
			case 1:
				adjustment = 140;
				break;
			case 2:
				adjustment = 142;
				break;

			case 3:
				adjustment = 144;
				break;

			case 4:
				adjustment = 145;
				break;

			}
			break;

		}
		return adjustment + subStrategy;
	}

	public void setSubStrategy(int id, boolean value) {
		substrategies[id] = value;

	}

	public boolean getSubStrategyValue(int id) {
		return substrategies[id];

	}

	public boolean getCheckValue(String text, int id) {
		// String text= (String) tv.getText();
		int i = -1;
		if (text.contains("Fatigue")) {
			isFatigue = false;
			i = 0;
		} else if (text.contains("Self")) {
			isSelf = false;
			i = 2;

		} else if (text.contains("Sensory")) {
			isSensory = false;
			i = 1;
		} else if (text.contains("Attention")) {
			isAttention = false;
			i = 3;
		} else if (text.contains("Receptive")) {
			isReceptive = false;
			i = 4;
		} else if (text.contains("Expressive")) {

			isExpressive = false;
			i = 5;
		} else if (text.contains("Speed")) {
			isSpeed = false;
			i = 6;

		} else if (text.contains("Planning")) {
			isPlanning = false;
			i = 7;
		} else if (text.contains("Memory")) {
			isMemory = false;
			i = 8;
		} else if (text.contains("Reasoning")) {
			isReasoning = false;
			i = 9;
		} else if (text.contains("Problem")) {
			isProblem = false;
			i = 10;
		}

		return strategies[i][id];

	}

	public void setChecked(TextView tv, CheckBox cb) {

		String text = (String) tv.getText();
		if (text.contains("Fatigue")) {
			isFatigue = true;
			strategies[0][cb.getId()] = true;

		} else if (text.contains("Self")) {

			isSelf = true;
			strategies[2][cb.getId()] = true;

		} else if (text.contains("Sensory")) {

			isSensory = true;
			strategies[1][cb.getId()] = true;

		} else if (text.contains("Attention")) {
			isAttention = true;
			strategies[3][cb.getId()] = true;

		} else if (text.contains("Receptive")) {
			isReceptive = true;
			strategies[4][cb.getId()] = true;

		} else if (text.contains("Expressive")) {
			isExpressive = true;
			strategies[5][cb.getId()] = true;

		} else if (text.contains("Speed")) {
			isSpeed = true;
			strategies[6][cb.getId()] = true;
		} else if (text.contains("Planning")) {
			isPlanning = true;
			strategies[7][cb.getId()] = true;

		} else if (text.contains("Memory")) {
			isMemory = true;
			strategies[8][cb.getId()] = true;

		} else if (text.contains("Reasoning")) {
			isReasoning = true;
			strategies[9][cb.getId()] = true;

		} else if (text.contains("Problem")) {
			isProblem = true;
			strategies[10][cb.getId()] = true;

		}

	}

	public void setUnchecked(TextView tv, CheckBox cb) {

		// Setting Up CheckBoxes.
		String text = (String) tv.getText();
		int i = -1;
		if (text.contains("Fatigue")) {
			isFatigue = false;
			i = 0;
		} else if (text.contains("Self")) {
			isSelf = false;
			i = 2;
		} else if (text.contains("Sensory")) {
			isSensory = false;
			i = 1;
		} else if (text.contains("Attention")) {
			isAttention = false;
			i = 3;
		} else if (text.contains("Receptive")) {
			isReceptive = false;
			i = 4;
		} else if (text.contains("Expressive")) {

			isExpressive = false;
			i = 5;
		} else if (text.contains("Speed")) {
			isSpeed = false;
			i = 6;

		} else if (text.contains("Planning")) {
			isPlanning = false;
			i = 7;
		} else if (text.contains("Memory")) {
			isMemory = false;
			i = 8;
		} else if (text.contains("Reasoning")) {
			isReasoning = false;
			i = 9;
		} else if (text.contains("Problem")) {
			isProblem = false;
			i = 10;
		}

		strategies[i][cb.getId()] = false;

	}

	public boolean isAllUnchecked(String area) {
		boolean flag = true;
		// get sub area checkboxes
		String text = area;
		int i = -1;
		if (text.contains("Fatigue")) {
			i = 0;
		} else if (text.contains("Self")) {
			i = 2;
		} else if (text.contains("Sensory")) {
			i = 1;
		} else if (text.contains("Attention")) {
			i = 3;
		} else if (text.contains("Receptive")) {
			i = 4;
		} else if (text.contains("Expressive")) {
			i = 5;
		} else if (text.contains("Speed")) {
			i = 6;
		} else if (text.contains("Planning")) {
			i = 7;
		} else if (text.contains("Memory")) {
			i = 8;
		} else if (text.contains("Reasoning")) {
			i = 9;
		} else if (text.contains("Problem")) {
			i = 10;
		}

		for (boolean strategy : strategies[i]) {

			if (strategy) {
				flag = false;
				break;
			}

		}

		return flag;
	}

	public boolean isPhysicalChecked(CheckBox cb, Context context, TextView tv) {

		return false;
	}

	public boolean isFatigue() {
		return isFatigue;
	}

	public void setFatigue(boolean isFatigue) {
		this.isFatigue = isFatigue;
	}
	public boolean isSelf() {
		return isSelf;
	}
	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}
	public boolean isSensory() {
		return isSensory;
	}
	public void setSensory(boolean isSensory) {
		this.isSensory = isSensory;
	}
	public boolean isAttention() {
		return isAttention;
	}

	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}

	public boolean isReceptive() {
		return isReceptive;
	}

	public void setReceptive(boolean isReceptive) {
		this.isReceptive = isReceptive;
	}

	public boolean isExpressive() {
		return isExpressive;
	}

	public void setExpressive(boolean isExpressive) {
		this.isExpressive = isExpressive;
	}

	public boolean isSpeed() {
		return isSpeed;
	}

	public void setSpeed(boolean isSpeed) {
		this.isSpeed = isSpeed;
	}
	public boolean isPlanning() {
		return isPlanning;
	}
	public void setPlanning(boolean isPlanning) {
		this.isPlanning = isPlanning;
	}
	public boolean isMemory() {
		return isMemory;
	}
	public void setMemory(boolean isMemory) {
		this.isMemory = isMemory;
	}

	public boolean isReasoning() {
		return isReasoning;
	}

	public void setReasoning(boolean isReasoning) {
		this.isReasoning = isReasoning;
	}

	public boolean isProblem() {
		return isProblem;
	}

	public void setProblem(boolean isProblem) {
		this.isProblem = isProblem;
	}

	public boolean allUnchecked(CheckBox checkBox, String string) {
		return false;
	}

	public int getAreaId(int virtualId)
	{
		int id=0;
		switch(virtualId)
		{
		case 0:
			id=com.kellar2.brain2.R.array.fatiguestrategies;
			break;
		case 1:
			id=com.kellar2.brain2.R.array.sensorystrategies;
			break;
		case 2:
			id=R.array.selfstrategies;
			break;
		case 3:
			id=R.array.attentionstrategies;
			break;
		case 4:
			id=R.array.receptivestrategies;
			break;
		case 5:
			id=R.array.expressivestrategies;
			break;
		case 6:
			id=R.array.speedstrategies;
			break;
		case 7:
			id=R.array.planningstrategies;
			break;
			
		case 8:
			id=R.array.memorystrategies;
			break;
			
		case 9:
			id=R.array.reasoningstrategies;
			break;
		case 10:
			id=R.array.problemstrategies;
			break;
		}
		return id;
	}
	public String getAreaName(int virtualId)
	{
		String areaName=null;
		switch(virtualId)
		{
		case 0:
			areaName="Fatigue";
			break;
		case 1:
			areaName="Sensory";
			break;
		case 2:
			areaName="Self";
			break;
		case 3:
			areaName="Attention";
			break;
		case 4:
			areaName="Receptive";
			break;
		case 5:
			areaName="Expressive";
			break;
		case 6:
			areaName="Speed";
			break;
		case 7:
			areaName="Planning";
			break;
		case 8:
			areaName="Memory";
			break;
		case 9:
			areaName="Reasoning";
			break;
			
		case 10:
			areaName="Problem";
			break;
			
		}
		return areaName;
		
		
	}
	
	public String getStrategy(int areaId,int strategyId)
	{
		int id=getAreaId(areaId);
		String strategies[]=context.getResources().getStringArray(id);
		return strategies[strategyId];
	}
	
	 CharSequence[] getSubStrategies(String area, int checkBoxId) {
		// to do find substrategies for an area
		CharSequence[] rowStrings = null;
		if (area.contains("Fatigue")) {
			TypedArray fatigueSubs = context.getResources().obtainTypedArray(
					R.array.fatiguesubstrategiesarray);
			rowStrings = fatigueSubs.getTextArray(checkBoxId);
			fatigueSubs.recycle();
		} else if (area.contains("Sensory")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.sensorysubsarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		}
		else if (area.contains("Self")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.selfsubsarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();

		} else if (area.contains("Attention")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.attentionsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		}

		else if (area.contains("Receptive")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.receptivesubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Speed")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.speedsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Reasoning")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.reasoningsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Memory")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.memorysubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Planning")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.planningsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Problem")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.problemsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Expressive")) {
			TypedArray subs = context.getResources().obtainTypedArray(
					R.array.expressivesubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		}

		return rowStrings;
	}
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public boolean[] getSubstrategies() {
		return substrategies;
	}

	public void setSubstrategies(boolean[] substrategies) {
		this.substrategies = substrategies;
	}

	public boolean[] getFirstSubflags() {
		return firstSubflags;
	}

	public void setFirstSubflags(boolean[] firstSubflags) {
		this.firstSubflags = firstSubflags;
	}

	public Map<String, List<Integer>> getSubAreaTagMapping() {
		return subAreaTagMapping;
	}

	public void setSubAreaTagMapping(Map<String, List<Integer>> subAreaTagMapping) {
		this.subAreaTagMapping = subAreaTagMapping;
	}

	public String getAreaFullName(int virtualId)
	{
		String areaName=null;
		switch(virtualId)
		{
		case 0:
			areaName="Fatigue / Endurance";
			break;
		case 1:
			areaName="Sensory / Motor";
			break;
		case 2:
			areaName="Self - Monitoring";
			break;
		case 3:
			areaName="Attention ";
			break;
		case 4:
			areaName="Receptive Communication";
			break;
		case 5:
			areaName="Expressive Communication";
			break;
		case 6:
			areaName="Speed Of Information Processing";
			break;
		case 7:
			areaName="Planning / Organizing";
			break;
		case 8:
			areaName="Memory";
			break;
		case 9:
			areaName="Reasoning";
			break;
			
		case 10:
			areaName="Problem Solving";
			break;
			
		}
		return areaName;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public Map<Integer, CharSequence> getSubStrategyMapping() {
		return subStrategyMapping;
	}

	public void setSubStrategyMapping(Map<Integer, CharSequence> subStrategyMapping) {
		this.subStrategyMapping = subStrategyMapping;
	}

	public void setStrategies(boolean[][] strategies) {
		this.strategies = strategies;
	}
	
	
	
}
