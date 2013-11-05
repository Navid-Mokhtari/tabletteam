package vitalsignals;

import java.util.Date;

public abstract class Measurements {
	private Date date;

	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate()
	{
		return date;
	}
}
