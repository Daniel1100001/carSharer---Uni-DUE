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

import com.ibm.db2.jcc.DB2Administrator;


import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.DateTimeUtil;
import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.StoreException;

	public final class ReservierenRead {

	    private Connection connection;
	    private boolean complete;
	    public ReservierenRead() throws StoreException {
	        try {
//	            connection = DBUtil.getConnection();
	            connection = DBUtil.getExternalConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }

        public Short getReserviertePlaetze(Short fid) throws Exception {
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT fahrt, sum(anzPlaetze) as sum FROM dbp187.reservieren WHERE fahrt = ? GROUP BY fahrt");
                
                statement.setShort(1, fid);
                ResultSet result = statement.executeQuery();
                Short anzPlaetze = 0;
                while(result.next()) {
                			anzPlaetze =(short) result.getInt("sum");
                }
                return anzPlaetze;
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
                    while(result.next()) {
                    			return result.getString("email");
                    	}
                    ;
                }
                catch (SQLException e) {
                    throw new Exception(e);
                }
				return "Keine Email vorhander.";
				
       
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
        
       