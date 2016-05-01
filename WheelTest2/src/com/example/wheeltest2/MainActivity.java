package com.example.wheeltest2;

import java.util.Arrays;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private String[] strings = new String[] {"1", "2", "3", "4", "5", "6"};
	private WheelView mWheelView = null;
	private HLDatePicker mDatePicker = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews(){
		mWheelView = (WheelView) findViewById(R.id.wheelview);
		mWheelView.setItems(Arrays.asList(strings));
		mDatePicker = (HLDatePicker) findViewById(R.id.datePicker);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initDatePicker();
			}
		}, 100);
	}
	
	private void initDatePicker(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) - 1899;
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		mDatePicker.setCurrentDay(day);
		mDatePicker.setCurrentMonth(month);
		mDatePicker.setCurrentYear(year);
	}
	
	private Handler mHandler = new Handler();
}
