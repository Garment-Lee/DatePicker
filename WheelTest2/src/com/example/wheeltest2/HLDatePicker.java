package com.example.wheeltest2;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.wheeltest2.WheelView.OnScrollListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class HLDatePicker extends LinearLayout implements OnScrollListener{

	private WheelView yearWheelView = null;
	private WheelView monthWheelView = null;
	private WheelView dayWheelView = null;
	private Context mContext = null;
	private List<String> yearWheelViewDataList = null;
	private List<String> monthWheelViewDataList = null;
	private List<String> dayWheelViewDataList = null;
	private LinearLayout.LayoutParams layoutParams = null;
	public HLDatePicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public HLDatePicker(Context context, AttributeSet attrs){
		super(context, attrs);
		init(context);
	}
	
	@SuppressLint("NewApi")
	public HLDatePicker(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		Log.i("Garment0424", "HLDatePicker init");
		mContext = context;
		yearWheelView = new WheelView(mContext);
		monthWheelView = new WheelView(mContext);
		dayWheelView = new WheelView(mContext);
		
		yearWheelView.setVerticalScrollBarEnabled(false);
		monthWheelView.setVerticalScrollBarEnabled(false);
		dayWheelView.setVerticalScrollBarEnabled(false);
		
		yearWheelView.setScrollbarFadingEnabled(false);
		monthWheelView.setScrollbarFadingEnabled(false);
		dayWheelView.setScrollbarFadingEnabled(false);
		
		this.setOrientation(HORIZONTAL);
		layoutParams = new LayoutParams(100,LayoutParams.WRAP_CONTENT);
		yearWheelView.setLayoutParams(layoutParams);
		monthWheelView.setLayoutParams(layoutParams);
		dayWheelView.setLayoutParams(layoutParams);
		
		this.addView(yearWheelView);
		this.addView(monthWheelView);
		this.addView(dayWheelView);
		initYearWheelViewData();
		initMonthWheelData();
		initDayWheelData(1900, 1);
		
		yearWheelView.registerOnScrollListener(this);
		monthWheelView.registerOnScrollListener(this);
		
	}
	
	private void initYearWheelViewData(){
		if(yearWheelViewDataList == null){
			yearWheelViewDataList = new ArrayList<String>();
		}
		yearWheelViewDataList.clear();
		for(int i = 1900; i <= 2100; i ++ ){
			yearWheelViewDataList.add(String.valueOf(i));
		}
		yearWheelView.setItems(yearWheelViewDataList);
	}
	
	private void initMonthWheelData(){
		if(monthWheelViewDataList == null){
			monthWheelViewDataList = new ArrayList<String>();
		}
		monthWheelViewDataList.clear();
		for(int i = 1; i <= 12; i ++){
			monthWheelViewDataList.add(String.valueOf(i));
		}
		monthWheelView.setItems(monthWheelViewDataList);
	}
	
	private void initDayWheelData(int year, int month){
		int dayCount = getDaysByYearMonth(year, month);
		Log.i("Garment0424", "initDayWheelData dacount:" + dayCount);
		if(dayWheelViewDataList == null){
			dayWheelViewDataList = new ArrayList<String>();
		}
		dayWheelViewDataList.clear();
		for (int i = 1; i <= dayCount; i++) {
			dayWheelViewDataList.add(String.valueOf(i));
		}
		dayWheelView.setItems(dayWheelViewDataList);
	}
	
	/**refresh the daywheel according the year and the month*/
	private void refreshDayWheel(int year, int month){
		int dayCount = getDaysByYearMonth(year, month);
		//no need to refresh if the daycount not change
		if(dayCount == dayWheelViewDataList.size())
			return;
		dayWheelViewDataList.clear();
		for (int i = 1; i <= dayCount; i++) {
			dayWheelViewDataList.add(String.valueOf(i));
		}
		dayWheelView.setItems(dayWheelViewDataList);
		Log.i("Garment0424", "initDayWheelData dayWheelView.currentIndex:" + dayWheelView.getCurrentIndex());
		if(dayWheelView.getCurrentIndex() > dayCount){
			dayWheelView.setCurrentItem(dayCount);
		} else {
			dayWheelView.setCurrentItem(dayWheelView.getCurrentIndex());
		}
	}
	
	/**
	 * get the count of day according to the year and month
	 * @param year
	 * @param month
	 * @return
	 */
	public int getDaysByYearMonth(int year, int month) {  
        
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }

	@Override
	public void onRefreshDayWheelView() {
		// TODO Auto-generated method stub
		int year = yearWheelView.getCurrentIndex();
		int month = monthWheelView.getCurrentIndex();
		Log.i("Garment0424", "onRefreshDayWheelView year:" + year + ";month:" + month);
		refreshDayWheel(year, month);
	}  
	
	public void setCurrentDay(int day){
		if (dayWheelView != null) {
			dayWheelView.setCurrentItem(day);
		}
	}
	
	public void setCurrentMonth(int month){
		if(monthWheelView != null){
			monthWheelView.setCurrentItem(month);
		}
	}
	
	public void setCurrentYear(int year){
		if(yearWheelView != null){
			yearWheelView.setCurrentItem(year);
		}
	}
	
}
