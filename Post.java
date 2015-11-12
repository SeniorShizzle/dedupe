import java.util.Calendar;
import java.util.GregorianCalendar;

public class Post implements Cloneable {

	private final int month;
	private final int day;
	private final int year;
	private final int hour;
	private final int minute;
	private final int seconds;
	
	private final String postId;
	private final int authorId;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Post() {
		postId = StdRandom.uniform(Integer.MAX_VALUE)+"_"+StdRandom.uniform(Integer.MAX_VALUE);
		authorId = StdRandom.uniform(Integer.MAX_VALUE);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, StdRandom.uniform(2001, 2016));
		int dOy = StdRandom.uniform(1, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
		cal.set(Calendar.DAY_OF_YEAR, dOy);
		
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		hour = StdRandom.uniform(0, 24);
		minute = StdRandom.uniform(0, 60);
		seconds = StdRandom.uniform(0,60);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub   <--- your implementation here
		return false;
	}

	@Override
	public int hashCode() {
		// TODO implement hashCode         <---- your implementation here
		return 0;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSeconds() {
		return seconds;
	}


	public String getPostId() {
		return postId;
	}

	public int getAuthorId() {
		return authorId;
	}

	@Override
	public String toString() {
		return postId+" "+authorId+" "+year+"-"+
	    ((month < 10) ? "0"+month : month)
	    +"-"+((day < 10 ) ? "0"+day : day)
	    +" "+((hour < 10 ) ? "0"+hour : hour)
	    +":"+((minute < 10) ? "0"+minute : minute)
	    +":"+((seconds < 10) ? "0"+seconds : seconds);
	}	
}
