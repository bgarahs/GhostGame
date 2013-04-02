package com.example.first;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This activity is used to show game rules
 */
public class DisplayMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		// if screen size is small, set only landscape orientation
		Display display = getWindowManager().getDefaultDisplay();
        if(display.getWidth()<720 && display.getHeight()<1280)
        	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    
        create_rules_view();
        create_author_view();
	}
	
	/**
	 * 
	 */
	private void display_view(String text, TextView textView){
		// Creates the text view with rules
	    //TextView textView = new TextView(this);
		//TextView textView =  (TextView) findViewById(R.id.rules_view);
		//if(textView!=null){
		//setContentView(textView);
	    textView.setTextSize(20);
	    textView.setText(text);
	    textView.setTextColor(getResources().getColor(android.R.color.white));
	    getWindow().setBackgroundDrawableResource(R.drawable.background_image);
        textView.setPadding(20, 20, 20,20);
        
		//}
        //
	}
	
	private void create_rules_view(){
		String rules = "\nRules:\nIn the game of Ghost, you and a computer take turns " +
	    		"building up an English word from left to right. Each player adds " +
	    		"one letter per turn. The goal is to not complete the spelling of " +
	    		"a word: if you add a letter that completes a word (of 4+ letters), " +
	    		"or if you add a letter that produces a string that cannot be extende" +
	    		"d into a word, you lose. \nYou start first.";
		display_view(rules,(TextView) findViewById(R.id.rules_view));
	}
	
	private void create_author_view(){
		String text = "Author tipa";
		display_view(text, (TextView) findViewById(R.id.about_view));
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
