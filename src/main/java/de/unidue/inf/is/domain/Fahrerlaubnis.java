package de.unidue.inf.is.domain;

public final class Fahrerlaubnis {
	private short fahrer;
    private short nummer;
    private java.sql.Timestamp ablaufdatum;

    public Fahrerlaubnis(short fahrer, short nummer,java.sql.Timestamp ablaufdatum) {
        this.fahrer = fahrer;
    	this.ablaufdatum = ablaufdatum;
        this.nummer = nummer;
    }
    public short getFahrer() { return fahrer; }
    public short getNummer() { return nummer; }
    public java.sql.Timestamp getAblaufdatum() { return ablaufdatum; }
}
