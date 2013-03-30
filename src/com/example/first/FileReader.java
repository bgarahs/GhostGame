package com.example.first;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

/**
 * This class is for reading and returning the dictionary
 */
public class FileReader{
	
  /**
   * Reads all words from the dictionary 'words.txt', then creates 25 lists (every 
   * list to hold words starting with different symbols from 'a' to 'z') and adds 
   * words to these lists. Then adds these 25 lists to a new list and returns it. 
   */
  public static ArrayList<ArrayList<String>> readFile(Context context, TextView tview){
    ArrayList<ArrayList<String>> list = null;
    try{
      AssetManager am = context.getAssets();
      InputStream is = am.open("words.txt");
 
      list = new ArrayList<ArrayList<String>>(26);
      for(int i=0;i<26;i++)
    	  list.add(new ArrayList<String>());
      // creates hash map with symbols "a",..,"z"
      HashMap<String,Integer> dict = new HashMap<String,Integer>(26);
      int k=0;
      for(char i='a';i<='z';i++){
    	  dict.put(i+"",k);
    	  k=k+1;
      }
      
      BufferedReader r = new BufferedReader(new InputStreamReader(is));
      StringBuilder total = new StringBuilder();
      String line;
      // reads all lines from the file
      while ((line = r.readLine()) != null) {
          total.append(line+" ");
      }
      r.close();
      
      // splits the line for words and adds them to the appropriate lists
      String[] sss = total.toString().split(" ");
      for(int i=0;i<sss.length;i++)
        list.get(dict.get(sss[i].charAt(0)+"")).add(sss[i]);
    }
    catch(Exception e){ System.out.println("File cannot be opened"); return null;}
     
    return list;
  }
}