package kr.or.ddit.servlet08;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*; //캘린터가 가지고 있는 모든 것은 이 안에 들어왔다는 의미의 코드

import java.text.DateFormatSymbols;

public class CalendarWrapper_copy {
	private Calendar adaptee;
	
	//
	private int offset; //4 -- 앞에 빈칸 4개, 목요일이 일요일에서부터 5번째 날짜라서 계산 나온 방식
	private int dayOfWeekFirst;
	private int lastDate;
	private int beforeYear;
	private int beforeMonth;
	private int currentYear;
	private int currentMonth;
	private int nextYear;
	private int nextMonth;
	
	private String[] weekDays; // 월화수목금토일을 담아놓은 배열
	/*
	월요일 = 0
	화요일 = 1
	수요일 = 2
	목요일 = 3
	금요일 = 4
	토요일 = 5
	일요일 = 6
	*/
	private String[] months; //월들이 들어가있는 배열
	
	//adaptee를 매개변수로 가지고 있는 생성자를 만든다
	public CalendarWrapper_copy(Calendar adaptee) {
		super();
		this.adaptee = adaptee;
		
		DateFormatSymbols dfs = DateFormatSymbols.getInstance();
		weekDays = dfs.getWeekdays();
		months = dfs.getMonths();
		
		
		
		
	}

	public Calendar getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Calendar adaptee) {
		this.adaptee = adaptee;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getDayOfWeekFirst() {
		return dayOfWeekFirst;
	}

	public void setDayOfWeekFirst(int dayOfWeekFirst) {
		this.dayOfWeekFirst = dayOfWeekFirst;
	}

	public int getLastDate() {
		return lastDate;
	}

	public void setLastDate(int lastDate) {
		this.lastDate = lastDate;
	}

	public int getBeforeYear() {
		return beforeYear;
	}

	public void setBeforeYear(int beforeYear) {
		this.beforeYear = beforeYear;
	}

	public int getBeforeMonth() {
		return beforeMonth;
	}

	public void setBeforeMonth(int beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public int getNextYear() {
		return nextYear;
	}

	public void setNextYear(int nextYear) {
		this.nextYear = nextYear;
	}

	public int getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth;
	}

	public String[] getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	@Override
	public String toString() {
		return String.format("%1$tY, %1$tB", adaptee);
	}

}
