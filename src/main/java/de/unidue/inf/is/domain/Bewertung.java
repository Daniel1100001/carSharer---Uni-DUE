package de.unidue.inf.is.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

public class Bewertung {
	private short beid;
	private String textnachricht;
	private java.sql.Timestamp erstellungsdatum;
	private short rating;
	private String email;
	public Bewertung(String textnachricht, short rating) {
		this.textnachricht = textnachricht;
		this.rating = rating;
		
	}
	public Bewertung(String textnachricht, short rating,short beid, String email) {
		this.beid = beid;
		this.textnachricht = textnachricht;
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
	public static String getClobString(Clob clob) throws SQLException,IOException {
		BufferedReader stringReader = new BufferedReader(clob.getCharacterStream());
		String singleLine = null;
		StringBuffer strBuff = new StringBuffer();
		while ((singleLine = stringReader.readLine()) != null) {
			strBuff.append(singleLine);
		}
		return strBuff.toString();
}
}
