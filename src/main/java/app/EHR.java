package app;

import java.util.Date;

public class EHR {
	private long EHRID, patientID;
	private int conceptIDFromConcept;
	private Date EHRDateTime;
	private boolean EHRSentStatus;

	public EHR(long EHRID, long patientID, int conceptIDFromConcept,
			Date EHRDateTime, boolean EHRSentStatus) {
		this.EHRID = EHRID;
		this.patientID = patientID;
		this.conceptIDFromConcept = conceptIDFromConcept;
		this.EHRDateTime = EHRDateTime;
		this.EHRSentStatus = EHRSentStatus;
	}

	public long getEHRID() {
		return EHRID;
	}

	public void setEHRID(long eHRID) {
		EHRID = eHRID;
	}

	public long getPassiendID() {
		return patientID;
	}

	public void setPassiendID(long patientID) {
		this.patientID = patientID;
	}

	public int getConceptIDFromConcept() {
		return conceptIDFromConcept;
	}

	public void setConceptIDFromConcept(int conceptIDFromConcept) {
		this.conceptIDFromConcept = conceptIDFromConcept;
	}

	public Date getEHRDateTime() {
		return EHRDateTime;
	}

	public void setEHRDateTime(Date eHRDateTime) {
		EHRDateTime = eHRDateTime;
	}

	public boolean isEHRSentStatus() {
		return EHRSentStatus;
	}

	public void setEHRSentStatus(boolean eHRSentStatus) {
		EHRSentStatus = eHRSentStatus;
	}
}
