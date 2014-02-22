package vitalsignals;

import java.util.Calendar;
import java.util.Date;

public abstract class Measurements {
	private Date date;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		if (date == null) {
			Calendar calendar = Calendar.getInstance();
			return calendar.getTime();
		}
		return date;
	}
}
