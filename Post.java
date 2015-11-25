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

	private static final boolean SIMPLE_HASHING = false;
    private static int postHash = 0;
    private final int myHash;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Post() {
		postId = StdRandom.uniform(Integer.MAX_VALUE) + "_" + StdRandom.uniform(Integer.MAX_VALUE);
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
		seconds = StdRandom.uniform(0, 60);

        myHash = postHash++; // serialized
	}

	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof Post)) return false;

		// we can do this because it's functionally impossible for two Post to .equals() if they're not .clone() of eachother
        Post that = (Post)obj;

		if (SIMPLE_HASHING) return this.hashCode() == that.hashCode(); // No two objects can have the same serial hash code

		// No longer simple hashing, check more complex things now
        if (this.hashCode() != that.hashCode()) return false; // simple check, but not exclusively true, only negating


		boolean same = false;

		// attempt to prove we're not equal
        same |= this.year     != that.getYear();
        same |= this.month    != that.getMonth();
        same |= this.day      != that.getDay();
        same |= this.hour     != that.getHour();
        same |= this.minute   != that.getMinute();
        same |= this.seconds  != that.getSeconds();

        same |= this.authorId != that.getAuthorId();
        same |= !this.postId.equals(that.getPostId());

		return !same; // invert
	}

	@Override
	public int hashCode() {

        if (SIMPLE_HASHING) return myHash; // Is this cheating? I don't think it's cheating.

        String[] hashStrings = postId.split("_");
        if (hashStrings.length < 2) return Integer.parseInt(hashStrings[0]);

        int hash = 0;
        hash |= Integer.parseInt(hashStrings[0]);
        hash = hash << 16;
        hash |= Integer.parseInt(hashStrings[1]);
        hash = hash << 16;
        hash ^= authorId;
        hash = hash >> day;
        hash ^= authorId;

		return hash;

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
