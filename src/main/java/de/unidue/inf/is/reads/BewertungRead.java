package de.unidue.inf.is.reads;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.NestingKind;

import com.ibm.db2.jcc.DB2Administrator;


import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.DateTimeUtil;
import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.StoreException;

	public final class BewertungRead {

	    private Connection connection;
	    private boolean complete;
	    public BewertungRead() throws StoreException {
	        try {
//	            connection = DBUtil.getConnection();
	            connection = DBUtil.getExternalConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }

        public List<Bewertung> getBewertung(Short fid) throws Exception {
            try {                
                PreparedStatement statement = connection.prepareStatement(""
                		+ "SELECT "
                		+ " textnachricht,"
                		+ " erstellungsdatum,"
                		+ " rating,"
                		+ " email,"
                		+ " beid"
                		+ "  FROM dbp187.bewertung b,"
                		+ " dbp187.schreiben s,"
                		+ " dbp187.fahrt f,"
                		+ " dbp187.benutzer bn"
                		+ " WHERE f.fid = ?"
                		+ " AND f.fid = s.fahrt"
                		+ " AND s.bewertung = b.beid"
                		+ " AND s.benutzer = bn.bid"
                		+ " ORDER BY erstellungsdatum");              		
//                + "SELECT (textnachricht,erstellungsdatum, rating, email, )  FROM dbp187.bewertung b,schreiben s, fahrt f WHERE f.fid = ? AND f.fid = s.fid AND s.beid = b.beid ORDER BY erstellungsdatum");
                
                statement.setShort(1, fid);
            	List<Bewertung> bewertungList = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                	Bewertung bewertung = new Bewertung(
                			(result.getClob("textnachricht") != null) ? DriveRead.getClobString(result.getClob("textnachricht")) :"Keine Beschreibung gefunden",
                			result.getShort("rating"),
                			result.getShort("beid"),
                			result.getString("email")

                			);
        			System.out.println("Text: "+ bewertung.gettextnachricht()+"\n"
        								+"Rating: "+bewertung.getrating()+"\n"
        								+"Beid: "+ bewertung.getBeid()+"\n"
        								+"Email: "+ bewertung.getemail()+"\n");
                	bewertungList.add(bewertung);
                	}
                return bewertungList;
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
       }
            
       public String getEmail(Short bid) throws Exception {
                try {
                    PreparedStatement statement = connection.prepareStatement("SELECT email FROM dbp187.benutzer WHERE bid=?");
                    
                    statement.setShort(1, bid);
                	//List<String> emailList = new ArrayList<>();
                    ResultSet result = statement.executeQuery();
                    String email = "Keine Email vorhanden.";
                    while (result.next()) {
						email = result.getString("email");
						
					}
                    //result.getString("email");
                    
                    return email;
                }
                catch (SQLException e) {
                    throw new Exception(e);
                }
				
       
        }
        public void complete() {
    	    this.complete = true;
    	    }


    	public void close() throws IOException {
    	        if (connection != null) {
    	            try {
    	                if (complete) {
    	                    connection.commit();
    	                }
    	                else {
    	                    connection.rollback();
    	                }
    	            }
    	            catch (SQLException e) {
    	            	System.out.print("sql conncetion nicht vorhanden");
    	                throw new StoreException(e);
    	            }
    	            finally {
    	                try {
    	                    connection.close();
    	                    System.out.print("Aus Datenbank gelesen");
    	                }
    	                catch (SQLException e) {
    	                    throw new StoreException(e);
    	                }
    	            }
    	     }
        }
	}
        
       