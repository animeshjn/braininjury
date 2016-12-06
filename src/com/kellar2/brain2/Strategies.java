package com.kellar2.brain2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commonsware.cwac.merge.MergeAdapter;
import com.kellar2.brain2.R;
import com.kellar.brain2.CheckHandle;
import com.kellar.brain2.PdfBean;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.Space;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Strategies extends Activity {
	ArrayAdapter<String> adapter;
	ArrayAdapter<View> viewadapter;
	ArrayAdapter<View> subviewadapter;
	TextView item;
	TextView first;
	String sid;
	public static final String LOG_TAG = "BRAIN";
	Context context;
	MergeAdapter merge = new MergeAdapter();
	ArrayList<String> physicalItems = new ArrayList<String>();
	ArrayList<String> behavioralItems = new ArrayList<String>();
	ArrayList<String> cognitionItems = new ArrayList<String>();
	Map<String, Integer> areasLayouts;
	ArrayList<String> selected;
	ArrayList<String> selectedPhysical;
	ArrayList<String> selectedBehavioral;
	ArrayList<String> selectedCognition;
	CheckBox checkBox;
	CheckHandle check = new CheckHandle();
	CheckHandle check2 = new CheckHandle();
	TableLayout table;
	String unchecked;
	boolean allUnchecked=false;
	public static int subcheckid = 99;
	boolean isAnyActiveSubstrategy;

	/* Main Method */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		check.setContext(context);
		Log.d(Strategies.LOG_TAG, "Application started");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayUseLogoEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setHomeButtonEnabled(false);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.actionbarlayout);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.strategies_landscape);
		Log.d(Strategies.LOG_TAG, "Content view set");
		initMap();
		generateList();
		selectFirstItem();

	}

	private CharSequence[] getSubStrategies(String area, int checkBoxId) {

		// to do find substrategies for an area
		CharSequence[] rowStrings = null;

		if (area.contains("Fatigue")) {

			TypedArray fatigueSubs = getResources().obtainTypedArray(
					R.array.fatiguesubstrategiesarray);
			rowStrings = fatigueSubs.getTextArray(checkBoxId);
			fatigueSubs.recycle();

		} else if (area.contains("Sensory")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.sensorysubsarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();

		}

		else if (area.contains("Self")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.selfsubsarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();

		} else if (area.contains("Attention")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.attentionsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();

		}

		else if (area.contains("Receptive")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.receptivesubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Speed")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.speedsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Reasoning")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.reasoningsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Memory")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.memorysubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Planning")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.planningsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Problem")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.problemsubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		} else if (area.contains("Expressive")) {
			TypedArray subs = getResources().obtainTypedArray(
					R.array.expressivesubarray);
			rowStrings = subs.getTextArray(checkBoxId);
			subs.recycle();
		}

		return rowStrings;
	}

	// initialize map (mapping name to the layouts used to be added to list
	// view)
	public void initMap() {
		// change this method to add or remove area of concern(s)
		areasLayouts = new HashMap<String, Integer>();

		// physical
		areasLayouts.put("Fatigue", R.layout.fatigueheader);
		areasLayouts.put("Sensory", R.layout.sensoryheader);

		// Behavioral
		areasLayouts.put("Self", R.layout.selfmonitoring);
		areasLayouts.put("Attention", R.layout.attentionheader);

		// Cognition
		areasLayouts.put("Receptive", R.layout.receptiveheader);
		areasLayouts.put("Expressive", R.layout.expressiveheader);
		areasLayouts.put("Speed", R.layout.speedheader);
		areasLayouts.put("Memory", R.layout.memoryheader);
		areasLayouts.put("Reasoning", R.layout.reasoningheader);
		areasLayouts.put("Planning", R.layout.planningheader);
		areasLayouts.put("Problem", R.layout.problemheader);

	}

	// method to generate table row for sub strategy

	@SuppressLint({ "NewApi", "InlinedApi" })
	/*
	 * METHOD TO GENERATE THE VALUE OF THE A TABLE ROW GETTING SUBSSTRATEGY ROW
	 * BY PASSING AN ID CALLS CHECKHANDLE LOGICS TO MAINTAIN STATE OF CHECKBOXES
	 */
	public TableRow getSubStrategyRow(CharSequence subStrategies[],
			final int checkBoxId, final String area) {

		TableRow row = new TableRow(context);

		TableRow.LayoutParams params = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT);
		row.setLayoutParams(params);
		LinearLayout layout = new LinearLayout(table.getContext());
		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT,
				TableRow.LayoutParams.WRAP_CONTENT);

		layout.setLayoutParams(layoutParams);
		layout.setOrientation(LinearLayout.VERTICAL);

		// create a sub-layout with horizontal
		TextView header = new TextView(context);
		header.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		header.setText("Sub-Strategies : ");
		header.setTypeface(null, Typeface.BOLD);
		header.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
		header.setPadding(10, 10, 10, 10);
		layout.addView(header);

		for (int i = 0; i < subStrategies.length; i++) {
			LinearLayout sublayout = new LinearLayout(table.getContext());
			sublayout.setOrientation(LinearLayout.HORIZONTAL);
			sublayout.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));
			Space space = new Space(context);
			space.setLayoutParams(new LinearLayout.LayoutParams(20,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			CheckBox subs = new CheckBox(context);
			subs.setLayoutParams(layoutParams);
			subs.setText(subStrategies[i]);

			// Set ID should be same for particular sub-strategy every time but
			// should be unique as well
			subs.setId(check.getSubCheckId(checkBoxId, area, i));
			check.subStrategyMapping.put(subs.getId(), subStrategies[i]);
			subs.setChecked(check.getSubstrategies(subs.getId()));
			subs.setTag(area + "" + checkBoxId);
			subs.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {

					CheckBox cb = (CheckBox) findViewById(checkBoxId);

					if (isChecked) {
						check.setSubstrategies(buttonView.getId(), true);
						cb.setChecked(true);

					} else {
						check.setSubstrategies(buttonView.getId(), false);

					}

				}
			});
			sublayout.setBackground(getResources().getDrawable(
					R.drawable.subsback));
			sublayout.addView(space);
			sublayout.addView(subs);
			layout.addView(sublayout);

		}

		row.addView(layout);

		return row;
	}

	// get selected physical views change to add or remove area of concerns
	/*
	 * HANDLES THE GENERATION OF VIEWS IN PHYSICAL AREA
	 * 
	 * --------------------------------------------------------------------------
	 * --
	 * ------------------------------------------------------------------------
	 * -------------- LOGICAL DIVISION
	 */
	public ArrayList<View> getSelectedPhysicalViews() {

		ArrayList<View> views = new ArrayList<View>();
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout;

		// search one by one into the map
		// loop has big else-if ladder as required by the Application.

		for (String selected : selectedPhysical) {
			if (selected.contains("Fatigue")) {

				layout = inflater.inflate(areasLayouts.get("Fatigue"), null);

				final TextView item = (TextView) layout
						.findViewWithTag("header");
				item.setText(selected);
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim);
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();
						String[] array = res
								.getStringArray(R.array.fatiguestrategies);

						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue("Fatigue",
									i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {
										// check if all others are unchecked
										check.setUnchecked(item,
												(CheckBox) buttonView);
										// Uncheck all substrategies
										// get all substrategies

										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Fatigue" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {/*
																			 * SAFETY
																			 * ERROR
																			 * HANDLING
																			 */
												CheckBox cb = (CheckBox) view;
												/* CAST FOR CHECKBOX PROPERTIES */
												cb.setChecked(false);
												/* UNCHECK IN VIEW */
												check.setSubstrategies(
														cb.getId(), false);
												/* UNCHECK IN LOGIC */
											}
										}

										// UnCheck hidden SubStrategies
										List<Integer> physical = check.subAreaTagMapping
												.get("Fatigue"
														+ buttonView.getId());
										for (Integer i : physical) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Fatigue")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}
									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));
							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;
									// int rowId = checkBoxId;

									// get subStrategies from string.xml
									CharSequence subStrategies[] = getSubStrategies(
											"Fatigue", checkBoxId);

									// get TableRow for extracted subStrategies
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Fatigue");

									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);
							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}
						handleComments((TextView) v);
					}
				});

				views.add(layout);
			} else if (selected.contains("Sensory")) {
				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * ****| CASE FOR SENSORY | CASE FOR SENSORY | CASE FOR SENSORY
				 * | CASE FOR SENSORY | CASE FOR SENSORY | CASE FOR SENSORY
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Sensory"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.sensorystrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue("Sensory",
									i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										// get all substrategies

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Sensory" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> sensory = check.subAreaTagMapping
												.get("Sensory"
														+ buttonView.getId());
										for (Integer i : sensory) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Sensory")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Sensory", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Sensory");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}
						handleComments((TextView) v);
					}
				});

				views.add(layout);

			}

		}

		return views;

	}

	// to append selected from Physical area of concern to merge adapter
	@SuppressLint("InflateParams")
	public void appendPhysicalItems() {

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View relativelayout = inflater.inflate(R.layout.header, null);
		TextView header = (TextView) relativelayout
				.findViewById(R.id.listheader);
		header.setText("Physical");

		merge.addView(relativelayout);
		merge.addViews(getSelectedPhysicalViews());
	}

	// to append selected from Behavioral area of concern to merge adapter
	@SuppressLint("InflateParams")
	public void appendBehavioralItems() {

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View relativelayout = inflater
				.inflate(R.layout.behaviouralheader, null);
		TextView header = (TextView) relativelayout
				.findViewById(R.id.blistheader);
		header.setText("Behavioral/Social");

		merge.addView(relativelayout);

		merge.addViews(getSelectedBehavioralViews());

	}

	// to append selected from Cognitive area of concern to merge adapter
	@SuppressLint("InflateParams")
	public void appendCognitiveItems() {

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View relativelayout = inflater.inflate(R.layout.header, null);
		TextView header = (TextView) relativelayout
				.findViewById(R.id.listheader);
		header.setText("General Cognition");
		merge.addView(relativelayout);
		merge.addViews(getSelectedCognitiveViews());

	}

	/*
	 * ==========================================================================
	 * ==
	 * ========================================================================
	 * =======================
	 * 
	 * BEHAVIORAL VIEWS || logical division | BEHAVIORAL VIEWS || logical
	 * division | BEHAVIORAL VIEWS || logical division | logical division |
	 * logical division | logical division | logical division | logical division
	 * | logical division |
	 * ======================================================
	 * ====================
	 * ======================================================
	 * ======================================
	 */

	private ArrayList<View> getSelectedBehavioralViews() {
		ArrayList<View> views = new ArrayList<View>();
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout;

		// search one by one into the map
		// loop has big else-if ladder as required by the Application.

		for (String selected : selectedBehavioral) {
			if (selected.contains("Self")) {
				layout = inflater.inflate(areasLayouts.get("Self"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				item.setText(selected);

				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim);
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();
						String[] array = res
								.getStringArray(R.array.selfstrategies);

						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue("Self", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {
										// check if all others are unchecked
										check.setUnchecked(item,
												(CheckBox) buttonView);
										// Uncheck all substrategies
										// get all substrategies

										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Self" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;// cast
												cb.setChecked(false); // uncheck
												check.setSubstrategies(
														cb.getId(), false);// uncheck
											}
										}

										// uncheck hidden substrategies
										List<Integer> self = check.subAreaTagMapping
												.get("Self"
														+ buttonView.getId());
										for (Integer i : self) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Self")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;
									// int rowId = checkBoxId;

									// get subStrategies from string.xml
									CharSequence subStrategies[] = getSubStrategies(
											"Self", checkBoxId);

									// get TableRow for extracted subStrategies
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId, "Self");

									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							/* SETUP BACKGROUND */
							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							layout.addView(checkboxlayout);
							layout.addView(sub);

							row.addView(layout);
							table.addView(row, i);

						}
						handleComments((TextView) v);
					}
				});

				views.add(layout);
				/*-----------------------------------------------------------------------------------------------------
				 * --------------------ATTENTION------------------------------------------------------------------- */
			} else if (selected.contains("Attention")) {

				layout = inflater.inflate(areasLayouts.get("Attention"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				item.setText(selected);
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim);
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();
						String[] array = res
								.getStringArray(R.array.attentionstrategies);

						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue(
									"Attention", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {
										// check if all others are unchecked
										check.setUnchecked(item,
												(CheckBox) buttonView);
										// Uncheck all substrategies
										// get all substrategies

										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Attention"
														+ buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;// cast
												cb.setChecked(false); // uncheck
												check.setSubstrategies(
														cb.getId(), false);// uncheck
											}
										}

										// uncheck hidden substrategies
										List<Integer> attention = check.subAreaTagMapping
												.get("Attention"
														+ buttonView.getId());
										for (Integer i : attention) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Attention")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							// ib.setLayoutParams(params);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;
									// int rowId = checkBoxId;

									// get subStrategies from string.xml
									CharSequence subStrategies[] = getSubStrategies(
											"Attention", checkBoxId);

									// get TableRow for extracted subStrategies
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Attention");

									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);
							/* SETUP BACKGROUND */
							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);
						}
						handleComments((TextView) v);
					}
				});

				views.add(layout);

			}

		}
		return views;
	}

	/*
	 * =======================================|||||||||||||||||||||||||||||||||
	 * 
	 * LOGICAL DIVISION
	 * 
	 * COGNITIVE VIEWS
	 * 
	 * LOGIC STARTS HERE FOR BIGGEST AREA OF CONCERN
	 */

	public ArrayList<View> getSelectedCognitiveViews() {

		ArrayList<View> views = new ArrayList<View>();
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout;

		// search one by one into the map
		// loop has big else-if ladder as required by the Application.

		for (String selected : selectedCognition) {
			if (selected.contains("Receptive")) {
				layout = inflater.inflate(areasLayouts.get("Receptive"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				item.setText(selected);
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim);
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();
						String[] array = res
								.getStringArray(R.array.receptivestrategies);

						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue(
									"Receptive", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {
										// check if all others are unchecked
										check.setUnchecked(item,
												(CheckBox) buttonView);
										// Uncheck all substrategies
										// get all substrategies

										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Receptive"
														+ buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {/*
																			 * SAFETY
																			 * ERROR
																			 * HANDLING
																			 */
												CheckBox cb = (CheckBox) view;
												/* CAST FOR CHECKBOX PROPERTIES */
												cb.setChecked(false);
												/* UNCHECK IN VIEW */
												check.setSubstrategies(
														cb.getId(), false);
												/* UNCHECK IN LOGIC */
											}
										}

										// UnCheck hidden SubStrategies
										List<Integer> self = check.subAreaTagMapping
												.get("Receptive"
														+ buttonView.getId());
										for (Integer i : self) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Receptive")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}
									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));
							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;
									// get subStrategies from string.xml
									CharSequence subStrategies[] = getSubStrategies(
											"Receptive", checkBoxId);
									// get TableRow for extracted subStrategies
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Receptive");

									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;
								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);
							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}
						handleComments((TextView) v);
					}
					
				});

				views.add(layout);
			} else if (selected.contains("Expressive")) {
				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * ****| CASE FOR SENSORY | CASE FOR SENSORY | CASE FOR SENSORY
				 * | CASE FOR SENSORY | CASE FOR SENSORY | CASE FOR SENSORY
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Expressive"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.expressivestrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue(
									"Expressive", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Expressive"
														+ buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> attention = check.subAreaTagMapping
												.get("Expressive"
														+ buttonView.getId());
										for (Integer i : attention) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Expressive")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Expressive", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Expressive");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}
						handleComments((TextView) v);
					}
				});

				views.add(layout);
				/* the next brace closes the else-if condition for this sub-area */
			}
			// speed
			else if (selected.contains("Speed")) {

				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * 
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Speed"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.speedstrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue("Speed", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Speed" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> tagged = check.subAreaTagMapping
												.get("Speed"
														+ buttonView.getId());
										for (Integer i : tagged) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Speed")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Speed", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId, "Speed");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}handleComments((TextView) v);
					}
				});

				views.add(layout);
				/* the next brace closes the else-if condition for this sub-area */

			}

			else if (selected.contains("Planning")) {

				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * 
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Planning"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.planningstrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue("Planning",
									i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Planning" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> tagged = check.subAreaTagMapping
												.get("Planning"
														+ buttonView.getId());
										for (Integer i : tagged) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Planning")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Planning", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Planning");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}handleComments((TextView) v);
					}
				});

				views.add(layout);
				/* the next brace closes the else-if condition for this sub-area */

			}

			else if (selected.contains("Memory")) {

				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * 
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Memory"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.memorystrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check
									.getCheckValue("Memory", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Memory" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> tagged = check.subAreaTagMapping
												.get("Memory"
														+ buttonView.getId());
										for (Integer i : tagged) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Memory")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Memory", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId, "Memory");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}handleComments((TextView) v);
					}
				});

				views.add(layout);
				/* the next brace closes the else-if condition for this sub-area */

			}

			else if (selected.contains("Reasoning")) {

				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * 
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Reasoning"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.reasoningstrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue(
									"Reasoning", i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Reasoning"
														+ buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> tagged = check.subAreaTagMapping
												.get("Reasoning"
														+ buttonView.getId());
										for (Integer i : tagged) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Reasoning")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Reasoning", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Reasoning");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}handleComments((TextView) v);
					}
				});

				views.add(layout);
				/* the next brace closes the else-if condition for this sub-area */

			}

			else if (selected.contains("Problem")) {

				/*
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 * 
				 * 
				 * |****
				 * 
				 * LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |LAYOUT AREA CHANGE |
				 * LAYOUT AREA CHANGE |LAYOUT AREA CHANGE | LAYOUT AREA CHANGE |
				 */

				// GET LAYOUT FORM THE MAP
				layout = inflater.inflate(areasLayouts.get("Problem"), null);
				final TextView item = (TextView) layout
						.findViewWithTag("header");
				// GET TEXT VIEW FROM LAYOUT
				item.setText(selected);
				// SET THE CURRENT TEXT
				Drawable exclaim = getResources().getDrawable(
						R.drawable.exclaim); // DRAW
				item.setCompoundDrawablesWithIntrinsicBounds(null, null,
						exclaim, null);

				// LISTEN FOR THE CLICK AND GENERATE STRATEGIES
				item.setOnClickListener(new OnClickListener() {

					@SuppressLint("InlinedApi")
					@Override
					public void onClick(View v) {
						// append rows to table
						highlightThis((TextView) v);
						table = (TableLayout) findViewById(R.id.subareatable);
						table.removeAllViews();
						Resources res = getResources();

						// GET RESOURCE ARRAY OF SENSORY STRATEGIES(!Not
						// Substrategies!!!)
						String[] array = res
								.getStringArray(R.array.problemstrategies);

						// PROCESS THE TABLE
						for (int i = 0; i < array.length; i++) {
							TableRow row = new TableRow(context);

							TableRow.LayoutParams params = new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT);
							row.setLayoutParams(params);
							RelativeLayout layout = new RelativeLayout(table
									.getContext());

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.MATCH_PARENT,
									TableRow.LayoutParams.MATCH_PARENT));

							LinearLayout checkboxlayout = new LinearLayout(
									table.getContext());

							RelativeLayout.LayoutParams checkboxparam = new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.WRAP_CONTENT,
									RelativeLayout.LayoutParams.WRAP_CONTENT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							checkboxparam
									.addRule(RelativeLayout.ALIGN_PARENT_START);
							checkboxlayout.setLayoutParams(checkboxparam);

							layout.setLayoutParams(new TableRow.LayoutParams(
									TableRow.LayoutParams.WRAP_CONTENT,
									TableRow.LayoutParams.WRAP_CONTENT));

							checkBox = new CheckBox(context);
							checkBox.setText(array[i]);
							checkBox.setTag("a" + i);
							checkBox.setId(i);
							checkBox.setChecked(check.getCheckValue("Problem",
									i));
							checkBox.setLayoutParams(new LinearLayout.LayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));

							checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									// change image in the text view
									if (isChecked) {
										check.setChecked(item,
												(CheckBox) buttonView);
										Drawable tick = getResources()
												.getDrawable(R.drawable.tick);
										item.setCompoundDrawablesWithIntrinsicBounds(
												null, null, tick, null);

									} else {

										/* CHECK if all others are unchecked */
										check.setUnchecked(item,
												(CheckBox) buttonView);

										/* Uncheck all substrategies */

										/*
										 * FOR THE SUBSTRATEGIES VISIBLE IN THE
										 * VIEW RIGHT NOW
										 */
										ArrayList<View> views = getViewsByTag(
												(ViewGroup) findViewById(R.id.subarealinearlayout),
												"Problem" + buttonView.getId());
										for (View view : views) {

											if (view instanceof CheckBox) {
												CheckBox cb = (CheckBox) view;
												cb.setChecked(false); // uncheck

												check.setSubstrategies(
														cb.getId(), false);
												// uncheck
												// in
												// logic

											}
										}

										/* UNCHECK HIDDEN SUBSTRATEGIES */
										List<Integer> tagged = check.subAreaTagMapping
												.get("Problem"
														+ buttonView.getId());
										for (Integer i : tagged) {
											check.substrategies[i] = false;
										}

										if (check.isAllUnchecked("Problem")) {
											Drawable exclaim = getResources()
													.getDrawable(
															R.drawable.exclaim);
											item.setCompoundDrawablesWithIntrinsicBounds(
													null, null, exclaim, null);

										}

									}
								}
							});

							Button sub = new Button(context);
							RelativeLayout.LayoutParams buttonparam = new RelativeLayout.LayoutParams(
									40, 40);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_END);
							buttonparam
									.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							sub.setLayoutParams(buttonparam);
							sub.setBackground(getResources().getDrawable(
									R.drawable.plusbuttondraw));

							sub.setId(20 + i);
							sub.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {

									/* handle this substrategy(i) */
									item.callOnClick();
									int plusid = v.getId();
									int checkBoxId = plusid - 20;

									/* GET subStrategies from string.xml */
									CharSequence subStrategies[] = getSubStrategies(
											"Problem", checkBoxId);

									/* GET TableRow for extracted subStrategies */
									TableRow tr = getSubStrategyRow(
											subStrategies, checkBoxId,
											"Problem");

									/* GET THE TABLE TO ADD ROW */
									table = (TableLayout) findViewById(R.id.subareatable);

									table.addView(tr, checkBoxId + 1);
									isAnyActiveSubstrategy = false;

								}

							});

							checkboxlayout.addView(checkBox);
							layout.addView(checkboxlayout);
							layout.addView(sub);

							layout.setBackground(getResources().getDrawable(
									R.drawable.checkback));
							row.addView(layout);
							table.addView(row, i);

						}handleComments((TextView) v);
					}
				});

				views.add(layout);

			}

		}

		return views;

	}

	/*
	 * ==========================================================================
	 * ======================================= /* MAIN LOGIC EXECUTION FOR
	 * STRATEGIES DISPLAY AND STORE STARTS HERE
	 * 
	 * ==========================================================================
	 * ==================================
	 */

	@SuppressLint("InflateParams")
	public void generateList() {

		/* MAIN LOGIC EXECUTION FOR STRATEGIES DISPLAY AND STORE STARTS HERE */
		ListView v = (ListView) findViewById(R.id.listAreas);
		StrategyLogic strategy = new StrategyLogic(this);
		// GET SELECTED DATA FROM INTENT
		selected = strategy.getSelectedAreas();
		selectedPhysical = strategy.getSelectedPhysical(); // PHYSICAL
		selectedBehavioral = strategy.getSelectedBehavioral(); // BEHAVIOURAL
		selectedCognition = strategy.getSelectedCognition(); // COGNITION (THE
																// LONGER ONE)
		// GET INFLATOR

		if (!selectedPhysical.isEmpty()) {
			// TOOK IT ALL IN THE DIFFERENT METHOD
			appendPhysicalItems();
		}

		if (!selectedBehavioral.isEmpty()) {
			appendBehavioralItems();
		}
		if (!selectedCognition.isEmpty()) {
			appendCognitiveItems();
		}

		v.setAdapter(merge);

		Button next = (Button) findViewById(R.id.nextbuttonstrategieslandscape);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				final PdfBean pdf=new PdfBean();
				pdf.setContext(context);
				pdf.setCheck(check);
				
				
				if(checkAllChecked()){
				Toast.makeText(context, "Generating PDF Please Wait...",
						Toast.LENGTH_LONG).show();
				
				new Thread(pdf).run();
				
				}
				else if(!(allUnchecked))
					//Something Unchecked
				{
//						Alert
					AlertDialog.Builder info = new AlertDialog.Builder(context);
					info.setTitle("Do you wish to continue?");
				
					info.setMessage("Following area of concern(s) is/are not checked:\n"+unchecked+
												""	);
					info.setCancelable(true);
					info.setPositiveButton("Continue",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Toast.makeText(context, "Generating PDF Please Wait...", Toast.LENGTH_LONG).show();
									new Thread(pdf).run();
			                    	 
								}
							});
					info.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							
						}
					});
					
					info.show();
				}
				else
					Toast.makeText(context, "Please Check Atleast one Area of Concern", Toast.LENGTH_SHORT+1).show();
				//on yes generate PDF
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.strategies, menu);
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

	public void highlightThis(TextView tv) {
		// TODO highlight this
		// Unhighlight all views

		// ArrayList<View> allTextView = getViewsByTag(
		// (ViewGroup) findViewById(R.id.listAreas), "header");
		ListView lv = (ListView) findViewById(R.id.listAreas);
		MergeAdapter m = (MergeAdapter) lv.getAdapter();
		m.getCount();
		for (int c = 0; c <= m.getCount(); c++) {
			View tt = (View) m.getItem(c);
			if (tt instanceof RelativeLayout) {
				RelativeLayout rl = (RelativeLayout) tt;
				TextView tc = (TextView) rl.getChildAt(0);
				String value = (String) tc.getText();
				if ((!value.contains("Physical"))
						&& (!value.contains("Behavioral"))
						&& (!value.contains("General"))) {
					tc.setBackgroundResource(0);
				}
			}
		}

		// Toast.makeText(context,""+m.getCount(),Toast.LENGTH_LONG).show();
		// int i=0;
		// for (View view : allTextView) {
		// TextView text = (TextView) view;
		//
		//
		// String value = (String) text.getText();
		// if ((!value.contains("Physical"))
		// && (!value.contains("Behavioral"))
		// && (!value.contains("General")))
		// {text.setBackgroundResource(0);
		// i++;
		// if(i==1)
		// first=text;
		// }
		//
		// }

		tv.setBackground(getResources().getDrawable(R.drawable.highlighted));

	}

	public void selectFirstItem() {
		ListView lv = (ListView) findViewById(R.id.listAreas);
		MergeAdapter m = (MergeAdapter) lv.getAdapter();
		m.getCount();
		View tt = (View) m.getItem(1);
		RelativeLayout rl = (RelativeLayout) tt;
		TextView tc = (TextView) rl.getChildAt(0);
		tc.callOnClick();
		Drawable d[] = tc.getCompoundDrawables();

	}

	/*
	 * CUSTOM IMPLEMENTATION TO GET VIEWS BY A PARTICULAR TAG FROM THE CURRENT
	 * (ROOT) VIEW
	 */
	private static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
		ArrayList<View> views = new ArrayList<View>();
		final int childCount = root.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = root.getChildAt(i);
			if (child instanceof ViewGroup) {
				views.addAll(getViewsByTag((ViewGroup) child, tag));
			}

			final Object tagObj = child.getTag();
			if (tagObj != null && tagObj.equals(tag)) {
				views.add(child);
			}

		}
		return views;
	}

	/*
	 * HANDLE ADDITIONAL COMMENTS
	 */
	public void handleComments(final TextView area) {
		//TODO
		String[] cases = { "Fatigue", "Sensory",
				"Self", "Attention", "Receptive",
				"Expressive", "Speed", "Planning",
				"Memory", "Reasoning", "Problem" };

		int i;
		for (i = 0; i < cases.length; i++)
			if (area.getText().toString()
					.contains(cases[i]))
				break;
		final int index=i;
		EditText editText =((EditText) findViewById(R.id.comments));
		editText.setSingleLine(true);
		  editText.setLines(5);
		  editText.setHorizontallyScrolling(false);
		  editText.setText(check.getCommentText(index));
		
				editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						String rightDraw;

						if (actionId == EditorInfo.IME_ACTION_SEARCH
								|| actionId == EditorInfo.IME_ACTION_DONE
								|| event.getAction() == KeyEvent.ACTION_DOWN
								|| event.getKeyCode() == KeyEvent.KEYCODE_ENTER|| event.getKeyCode() == KeyEvent.KEYCODE_SOFT_LEFT 
								) {
							//if (!event.isShiftPressed()) 
							{
								// The user is done typing.
								
								check.setComments(true, index);
								check.setCommentText("" + v.getText(), index);
								Toast.makeText(context,
										"Comment Saved ",
										Toast.LENGTH_LONG).show();
								v.clearFocus();
								//hide keyboard
								InputMethodManager inputMethodManager = 
									        (InputMethodManager) getSystemService(
									            Activity.INPUT_METHOD_SERVICE);
									    inputMethodManager.hideSoftInputFromWindow(
									        getCurrentFocus().getWindowToken(), 0);
								return true; // consume.
							}
						}
						return false; // pass on to other listeners.
					}
				});

		((EditText) findViewById(R.id.comments))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						//TODO
//						Toast.makeText(context,
//								"Press Enter to complete Comment",
//								Toast.LENGTH_SHORT).show();
					}
				});
		((EditText) findViewById(R.id.comments)).setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
					Toast.makeText(context,
							"Press Enter to complete Comment",
							Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	public boolean checkAllChecked() {

		ListView lv = (ListView) findViewById(R.id.listAreas);
		MergeAdapter m = (MergeAdapter) lv.getAdapter();
		m.getCount();
		unchecked="";
		int uncheckCount=0;
		int textCount=0;
		allUnchecked=false;
		boolean isAnyExclaimed=false;
		for(int i=0;i<m.getCount();i++)
		{
			View tt = (View) m.getItem(i);
			RelativeLayout rl = (RelativeLayout) tt;
			TextView tc = (TextView) rl.getChildAt(0);
			Drawable drawables[]=tc.getCompoundDrawables();
			
			Drawable right=null;
			for(Drawable drawable:drawables)
			{
				if(!(drawable==null))
				{
					right=drawable;
				}
			}
			if(right == null)
				continue;
			textCount++;
			if(right.getConstantState().equals(getResources().getDrawable(R.drawable.exclaim).getConstantState()))
				{isAnyExclaimed=true;
				unchecked+=" *"+tc.getText()+"\n";
				uncheckCount++;
				}
		}
		
		if(uncheckCount==textCount)
			allUnchecked=true;
		return !isAnyExclaimed;
	}

}
