package com.example.affectassessment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

public class SPANEActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 // 1. pass context and data to the custom adapter
        MyAdapter adapter = new MyAdapter(this, generateData());
 
        //2. setListAdapter
        setListAdapter(adapter);
	}
	
	 private ArrayList<Item> generateData(){
	        ArrayList<Item> items = new ArrayList<Item>();
	        items.add(new Item("Item 1","First Item on the list"));
	        items.add(new Item("Item 2","Second Item on the list"));
	        items.add(new Item("Item 3","Third Item on the list"));
	 
	        return items;
	    }
}
