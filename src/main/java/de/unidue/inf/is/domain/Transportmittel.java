package de.unidue.inf.is.domain;

public class Transportmittel {
    private short tid ;
	private String name ;
    private String icon;

    public Transportmittel(short tid, String name, String icon) {
        this.tid = tid;
    	this.name = name;
        this.icon = icon;
    }
    public short getTid() { return tid; }
    public String getname() { return name; }
    public String getStringicon() { return icon; }
}
