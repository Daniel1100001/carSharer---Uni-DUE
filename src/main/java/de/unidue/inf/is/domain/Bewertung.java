package de.unidue.inf.is.domain;

public class Bewertung {
	private short beid;
	private String textnachricht;
	private java.sql.Timestamp erstellungsdatum;
	private short rating;
	
	public Bewertung(short beid, String textnachricht, java.sql.Timestamp erstellungsdatum, short rating) {
		this.beid = beid;
		this.textnachricht = textnachricht;
	    this.erstellungsdatum = erstellungsdatum;
	    this.rating = rating;
	}

	public short getBeid() {
		return beid;
	}
	public short getrating() {
		return rating;
	}

	public String gettextnachricht() { return textnachricht; }
	public java.sql.Timestamp geterstellungsdatum() { return erstellungsdatum; }
}
