package com.example.wheeltest2;

import java.util.Arrays;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	private String[] strings = new String[] {"1", "2", "3", "4", "5", "6"};
	private WheelView mWheelView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews(){
		mWheelView = (WheelView) findViewById(R.id.wheelview);
		mWheelView.setItems(Arrays.asList(strings));
	}
}
