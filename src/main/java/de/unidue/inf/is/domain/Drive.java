package de.unidue.inf.is.domain;

import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.DateTimeUtil;

public final class Drive {
	private short fid;
	private String startOrt;
	private String zielOrt;
	private java.sql.Time fahrtZeit;
	private java.sql.Date fahrtDatum;
	private java.sql.Timestamp fahrtDatumZeit;
	private String fahrtZeitString;
	private String fahrtDatumString;
	private short maxPlaetze;
	private java.math.BigDecimal fahrtkosten;
	private String status;
	private short anbieter;
	private short transportmittel;
	private String beschreibung;

	public Drive() {
	}

	public Drive(String startOrt, String zielOrt, java.sql.Timestamp fahrtDatumZeit, short maxPlaetze, java.math.BigDecimal fahrtkosten, String status, short anbieter, short transportmittel, String beschreibung) {
		this.fahrtDatumZeit = fahrtDatumZeit;
		this.startOrt = startOrt;
		this.zielOrt = zielOrt;
		this.maxPlaetze = maxPlaetze;
		this.fahrtkosten = fahrtkosten;
		this.status = status;
		this.anbieter = anbieter;
		this.transportmittel = transportmittel;
		this.beschreibung = beschreibung;
	}
	

	public Drive(String startOrt, String zielOrt, java.sql.Timestamp fahrtDatumZeit, short maxPlaetze, java.math.BigDecimal fahrtkosten, String status, short anbieter, short transportmittel, String beschreibung,short fid) {
		this.fahrtDatumZeit = fahrtDatumZeit;
		String fahrtDatumZeitString = fahrtDatumZeit.toString();
		this.fahrtZeitString = DateTimeUtil.extractTimeFromDB2DateTimeString(fahrtDatumZeitString);
		this.fahrtDatumString = DateTimeUtil.extractDateFromDB2DateTimeString(fahrtDatumZeitString);
		this.startOrt = startOrt;
		this.zielOrt = zielOrt;
		this.maxPlaetze = maxPlaetze;
		this.fahrtkosten = fahrtkosten;
		this.status = status;
		this.anbieter = anbieter;
		this.transportmittel = transportmittel;
		this.beschreibung = beschreibung;
		this.fid = fid;
	}
	
	
	public Timestamp getfahrtDatumZeit() {return fahrtDatumZeit;}
	public short getFid() {return fid;}
	public String getStatus() {return status;}
	public String getStartOrt() { return startOrt; }
	public String getZielOrt() { return zielOrt; }
	public short getMaxPlaetze() { return maxPlaetze; }
	public java.math.BigDecimal getFahrtkosten() { return fahrtkosten; }
	public String getStatusFahrt() { return status; }
	public short getAnbieter() { return anbieter; }
	public short getTransportmittel() { return transportmittel; }
	public String getBeschreibung() { return beschreibung; }
	public String getfahrtZeitString() { return fahrtZeitString; }
	public String getfahrtDatumString() { return fahrtDatumString ; }
}