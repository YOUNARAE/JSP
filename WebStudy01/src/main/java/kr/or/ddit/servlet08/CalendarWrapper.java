package kr.or.ddit.servlet08;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*; //캘린터가 가지고 있는 모든 것은 이 안에 들어왔다는 의미의 코드

import java.text.DateFormatSymbols;

public class CalendarWrapper {
	private Calendar adaptee;
	private Locale locale;
	
	// 어댑티는 어댑터 없이는 생성될 수 없다
	private int offset; // 4 -- 앞에 빈칸 4개, 목요일이 일요일에서부터 5번째 날짜라서 계산 나온 방식
	private int dayOfWeekFirst;
	private int lastDate;
	private int beforeYear; 
	private int beforeMonth;
	private int currentYear;
	private int currentMonth;
	private int nextYear; 
	private int nextMonth;
	
	private String[] weekDays;
	private String[] months; 
	

	public CalendarWrapper(Calendar adaptee, Locale locale) { // 이 생성자가 만들어지면서 기본 생성자가 없어진다
		super();
		this.adaptee = adaptee;
		this.locale = locale;
	
  	    DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
	    weekDays = dfs.getWeekdays();
	    months = dfs.getMonths();
		//pageContext.setAttribute("weekDays", weekDays);	
		
		adaptee.set(Calendar.DAY_OF_MONTH, 1); // 캘린터의 상수를 이용, 날짜가 12월 1일로 바뀜, 목요일이 필요해서 바꿈.
		dayOfWeekFirst = adaptee.get(DAY_OF_WEEK); // 일주일에서 몇 번째 
		offset = dayOfWeekFirst -SUNDAY;
		// dayOfWeekFirst는 목요일이라는 값을 갖게 된다.
		lastDate = adaptee.getActualMaximum(DAY_OF_MONTH);
		
		currentYear = adaptee.get(YEAR); //현재년도 꺼내놓기
		currentMonth = adaptee.get(MONTH);
		
		
		adaptee.add(MONTH, -1); // 한 달을 뺀다는 의미 -2022.11로 된 상태
		beforeYear = adaptee.get(YEAR);
		beforeMonth = adaptee.get(MONTH);
		
		adaptee.add(MONTH, 2);
		nextYear = adaptee.get(YEAR);
		nextMonth = adaptee.get(MONTH);
		
		adaptee.add(MONTH, -1); //다시 12월로 돌린다. 
		
		//어댑터라는 디자인패턴을 안 썼더라면 위의 코드가 모두 콘트롤러로 들어갔어야했다
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	
	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
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
	
	public String[] getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

	@Override
	public String toString() {
		return String.format(locale, "%1$tY, %1$tB", adaptee); //넘어가는 아규먼트가 1개밖에 없어서 인덱스 개념을 써야한다 
	}
	
}
