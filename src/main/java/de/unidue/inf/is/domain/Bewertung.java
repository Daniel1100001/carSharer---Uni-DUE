package de.unidue.inf.is.domain;

public class Bewertung {
	private short beid;
	private String textnachricht;
	private java.sql.Timestamp erstellungsdatum;
	private short rating;
	private String email;
	public Bewertung(String textnachricht, short rating, String email) {
		this.textnachricht = textnachricht;
		this.rating = rating;
		this.email = email;
		
	}
	public Bewertung(short beid, String textnachricht, java.sql.Timestamp erstellungsdatum, short rating, String email) {
		this.beid = beid;
		this.textnachricht = textnachricht;
	    this.erstellungsdatum = erstellungsdatum;
	    this.rating = rating;
	    this.email = email;
	}

	public short getBeid() {
		return beid;
	}
	public short getrating() {
		return rating;
	}
	public String getemail () {return email;}
	public String gettextnachricht() { return textnachricht; }
	public java.sql.Timestamp geterstellungsdatum() { return erstellungsdatum; }
}
