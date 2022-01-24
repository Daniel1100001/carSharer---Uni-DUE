package de.unidue.inf.is.domain;

public final class User {
    private String name ;
    private String email;
    private Short bid;
    public User(short bid, String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getname() { return name; }
    public String getemail() { return email; }

    
    
    
   //Bid ist hardcodiert 
	public short getBid() {
		return 1;
	}
}