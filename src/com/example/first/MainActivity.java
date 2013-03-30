package com.example.first;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Main activity of the game
 * @author Bogdan
 */
public class MainActivity extends Activity{
   
	private GhostGame game; // game logic
	private ArrayList<ArrayList<String>> words; // words from dictionary
	private boolean hintOn = false; // if 'hint' button is pressed
	private TextView press_key_view;  // text view to show 'press any key'
	private TextView text_message_view; // view to show hint words
	private TextView text_word_view;  // view to show getWord()
	
	// initializing everything
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        press_key_view = (TextView) findViewById(R.id.pressKey);
        text_message_view = (TextView) findViewById(R.id.text_message);
        text_word_view = (TextView) findViewById(R.id.theWord);
        
        // for smaller displays, set only landscape orientation
        Display display = getWindowManager().getDefaultDisplay();
        if(display.getWidth()<720 && display.getHeight()<1280)
        	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        addKeyBoard(); // adds keyboard for the game
        createGame(); // reads the dictionary and creates a game
    }

    /**
     * Reads the dictionary and creates the game
     * @ensure game != null
     */
    private void createGame(){
        words = readFile();
        if(words == null)
        	text_message_view.setText("Error reading file");
        else
            game = new GhostGame(words); // creates game
    }
    
    /**
     * Reads file and returns list with data. This list contains 25 lists, and 
     * each of the 25 lists contain all words starting with specific symbol, from
     * 'a' to 'z'
     */
    private ArrayList<ArrayList<String>> readFile(){
    	text_message_view.setText("Reading data");
    	ArrayList<ArrayList<String>> list = FileReader.readFile(getApplicationContext(),text_message_view);
    	text_message_view.setText("");
    	return list;
    }
    
    /**
     * Creates new game when New Game button is pressed
     * @param view
     */
    public void newGame(View view){
    	Button btn = (Button) findViewById(R.id.new_game_btn);
    	btn.setBackgroundResource(R.drawable.menu_button_clicked);
    	game = new GhostGame(words);
    	press_key_view.setText("Press any key:");
    	text_message_view.setText("");
    	text_word_view.setText("");
    	changeButtonBackground(btn, R.drawable.menu_button, 100);
    }
     
    /**
     * Changes background of a button after 'delay'. Used to highlight pressed button
     * @param button
     * @param backgroundResource
     * @param delay
     */
    private void changeButtonBackground(Button button, int backgroundResource, int delay ){
    	final Button btn = button;
    	final int res = backgroundResource;
    	final Handler handler = new Handler();
    	handler.postDelayed(new Runnable() {
    	  @Override
    	  public void run() { // changes backgorund image after 'delay' miliseconds
    		  btn.setBackgroundResource(res);
    	  }
    	}, delay);
    }
    
    /**
     * Starts a new activity to show rules of the game when Rules button is pressed
     * @param view
     */
    public void gameRules(View view){
    	Button btn = (Button) findViewById(R.id.rules_btn);
    	btn.setBackgroundResource(R.drawable.menu_button_clicked);
    	Intent intObj = new Intent(this,DisplayMessageActivity.class);
    	changeButtonBackground(btn, R.drawable.menu_button, 100);
    	startActivity(intObj);
    }
    
    /**
     * Exits the app after Exit button has been pressed
     * @param view
     */
    public void exitGame(View view){
    	Button btn = (Button) findViewById(R.id.exit_btn);
    	btn.setBackgroundResource(R.drawable.menu_button_clicked);
    	changeButtonBackground(btn, R.drawable.menu_button, 100);
    	finish();
        System.exit(0);
    }
    
    /**
     * Creates and returns a button used to add non-click buttons to keyboard
     * @param sym
     * @param i
     * @return
     */
    private Button createButton(String sym, int i, OnClickListener listener){
    	Button btn = new Button(this);
    	final Button button = btn;
    	final String symbol = new String(sym+"");
    	btn.setOnClickListener(createListener(button, symbol));
    	btn.setId(i);
    	btn.setTypeface(null, Typeface.BOLD);
    	btn.setBackgroundResource(R.drawable.small_button);
    	btn.setTextColor(Color.parseColor("#ffffff"));
    	btn.setText(sym);
    	return btn;
    }
    
    /**
     * @return Returns new listener for any of 25 'a'-to-'z' buttons on keyboard
     * @param button
     * @param symbol
     */
    private OnClickListener createListener(final Button button, final String symbol){
    	return new OnClickListener() {
    	    public void onClick(View v) {
    	    	button.setBackgroundResource(R.drawable.small_button_clicked);
    	    	sendMessage(symbol);
    	    	changeButtonBackground(button, R.drawable.small_button,100);
    	      }
    	  };
    }
    
    /**
     * @return Returns new Relative Layout params
     */
    private RelativeLayout.LayoutParams createButtonLayoutParams(){
    	RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
    			RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    	return lp;
    }
 
    /**
     * Adds keyboard with 25 buttons with text from 'a' to 'z'. When pressed, button is highlighted,
     * and symbol added to theWord
     */
    private void addKeyBoard(){
    	int i=6; // button id
        int j=1; // to help add proper rules to buttons (5 buttons in a row)
        // adding buttons withh text from 'a' to 'z'
        for(char sym='a';sym<='z';sym++){
        	RelativeLayout rl=(RelativeLayout)findViewById(R.id.gra_layout);
        	RelativeLayout.LayoutParams lp = createButtonLayoutParams();
        	j = addRuleToLayoutParam(i,j,lp);  // adds rules to layout params
        	i=addLetterButton(sym,i,rl,lp); // adds a button
        }
        
        // adds 4 empty non-clickable buttons
        for(int t=0;t<4;t++)
        	addFalseButt("",t+32);
		
        //now adds button to show hint
        addHintButton();
    }
    
    /**
     * Adds a hint button to the keyboard
     */
    private void addHintButton(){
    	 //now adds button to show hint
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.gra_layout);
    	RelativeLayout.LayoutParams lp = createButtonLayoutParams();
    	
    	Button btn = createButton("hint", 35, null);
    	final Button btnfinal = btn;
    	btn.setOnClickListener(new OnClickListener(){
        		public void onClick(View v) {
    	    			TextView textView1 = (TextView) findViewById(R.id.text_message);
    	    	        if(!hintOn){ 
    	    	        	showHint();
    	    	        	changeButtonBackground(btnfinal, R.drawable.small_button_clicked,100);
    	    	        	hintOn = true;
    	     	        }
    	    	        else{
    	    	        	textView1.setText("");
    	    	        	changeButtonBackground(btnfinal, R.drawable.small_button,100);
    	    	        	hintOn = false;
    	    	        }
    	    	      }
    	    	});
    	lp.addRule(RelativeLayout.RIGHT_OF,34);
		lp.addRule(RelativeLayout.BELOW,30);
    	rl.addView(btn,lp);
    }
    
    /**
     * @return Returns rules for our keyboard buttons (buttons from 'a' to 'z'  
     * 5 buttons in a row).
     * @param i
     * @param j
     * @param lp
     */
    private int addRuleToLayoutParam(int i, int j, RelativeLayout.LayoutParams lp){
    	if(i==6) // first button
    		lp.addRule(RelativeLayout.LEFT_OF);
    	else if((i+j)%6==0 && i!=31){ // start new row below
    		lp.addRule(RelativeLayout.BELOW,i-5);
    		lp.addRule(RelativeLayout.LEFT_OF);
    		j++;
    	}
    	else if(i==31) // last button ('z')
    		lp.addRule(RelativeLayout.BELOW,i-3);
    	else{ // other buttons
    		lp.addRule(RelativeLayout.RIGHT_OF,i-1);
    		lp.addRule(RelativeLayout.BELOW,i-5);
    	}
    	return j;
    }
    
    /**
     * Adds a letter button to the keyboard
     * @param sym
     * @param i
     * @param rl
     * @param lp
     * @return Return id of the next button
     */
    private int addLetterButton(char sym, int i,RelativeLayout rl,RelativeLayout.LayoutParams lp){
    	Button btn = createButton(sym+"",i, null);
    	final Button button = btn;
    	btn.setOnClickListener(createListener(button, sym+""));
    	i=i+1;
    	rl.addView(btn,lp);
    	return i;
    }
    
    /**
     * Adds non-clickable space fillers buttons to keyboard. 
     * @param sym
     * @param i
     */
    private void addFalseButt(String sym, int i){
    	Button b1 = createButton(sym, i, null);
        RelativeLayout rl1=(RelativeLayout)findViewById(R.id.gra_layout);
    	RelativeLayout.LayoutParams lp1 = createButtonLayoutParams();
    	lp1.addRule(RelativeLayout.RIGHT_OF,i-1);
    	lp1.addRule(RelativeLayout.BELOW,30);
    	b1.setEnabled(false);
    	rl1.addView(b1,lp1);
    }
    
    /** 
     * Called when the user clicks any of 25 a-z buttons on keyboard. 
     * Adds symbol to the word, checks if game is not finished, sets text for labels
     * @require symbol.length() == 1
     * @ensure
     */
    private void sendMessage(String symbol) {
        assert(symbol.length()==1);
    	if(game != null && !game.isGameOver()){
    		game.acceptUserLetter(symbol);
    		if(game.isGameOver()){
    			text_message_view.setTextSize(20);
    		   	press_key_view.setText("Winner: "+game.getWinner().toString());
    			performGameOver();
    		}
    		else if(hintOn)
    			showHint();
    	}
    	else
    		press_key_view.setText("Winner: "+game.getWinner());	
        text_word_view.setText(game.getWord());
    }
    		
    // does something when game is over
    private void performGameOver(){
    	text_message_view.setText("");
    }
    
    // gets possible words for user to use for game - hint for user
    /**
     * @return Returns string of possible words user can use to choose a symbol
     * This is a hint words for user
     */
    private String getPossibleWords(){
    	StringBuilder strbld = new StringBuilder("\n");
    	if(game.getWord().length()>=3){
    		strbld.append("\n");
    		ArrayList<String> l = game.getPossibleWords();
    		for(String word: l)
    			strbld.append("    "+word+"\n");
    	}
    	return strbld.toString();
    }
 
    // 
    /**
     * Shows all hint words if game is not over
     */
    private void showHint(){
    	if(!game.isGameOver()){
    		String hint = getPossibleWords();
    		if(hint.length()>1 && game.getWord().length()>=3)
    		    text_message_view.setText("Possible words:\n"+hint);
    	}
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // abu tse vsjo testyvatu, treba zabratu z first Manifest stroku android:configChanges="keyboardHidden|orientation|screenSize"   
    
    // functions to save state of the game
    @Override
    public void onPause() { // tyt store persistent data
        super.onPause();
        
    }
    
    @Override
    public void onStop() {
        super.onStop();
        //SharedPreferences settings = getSharedPreferences("saving_data", MODE_PRIVATE);
        //SharedPreferences.Editor editor = settings.edit();
        //editor.putSe
        //editor.putBoolean("silentMode", mSilentMode);
        // Commit the edits!
        //editor.commit();
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }
   
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { // tyt store GUI
      super.onSaveInstanceState(savedInstanceState);
      savedInstanceState.putSerializable("game", game);
    }
  
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
      
      if ((savedInstanceState != null) && (savedInstanceState.getSerializable("game") != null)){ 
    	  game = (GhostGame) savedInstanceState.getSerializable("game");
    	  TextView textView1 = (TextView) findViewById(R.id.pressKey);
          TextView textView2 = (TextView) findViewById(R.id.theWord);
          TextView textView3 = (TextView) findViewById(R.id.text_message);
          textView1.setText(R.string.pressKey);
          textView2.setText(game.getWord());
        
          if(!game.isGameOver() && hintOn)
        	  showHint();
          else press_key_view.setText("Winner: "+game.getWinner().toString());
          
      }
    }
}
