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

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.DriveStore;
import de.unidue.inf.is.stores.StoreException;

	public final class DriveRead {

	    private Connection connection;
	    private boolean complete;
	    public DriveRead() throws StoreException {
	        try {
//	            connection = DBUtil.getConnection();
	            connection = DBUtil.getExternalConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }

	    
        public List<Drive> getDriveOffen(User user) throws Exception {
            try {
                Connection con = DBUtil.getConnection();
                con.setAutoCommit(false);
                
                PreparedStatement statement = con.prepareStatement("SELECT * FROM dbp187.drive WHERE fid != (SELECT fid FROM reservieren WHERE kunde = ?) "
                		+ "AND status = 'offen' ;");
                statement.setShort(1, user.getBid());
            	List<Drive> driveList = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                	Drive offenerDrive = new Drive(
                			result.getString("startort"),
                			result.getString("zielort"),
                			result.getTimestamp("fahrtDatumZeit"),
                			result.getShort("maxPlaetze"),
                			result.getBigDecimal("fahrtkosten"),
                			result.getString("status"),
                			result.getShort("anbieter"),
                			result.getShort("transportmittel"),
                			result.getString("beschreibung"),
                			result.getShort("fid"));
                	driveList.add(offenerDrive);
                	
                }
                return driveList;
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
        }
        
        public List<Drive> getDriveReserviert(User user) throws Exception {
            try {
                Connection con = DBUtil.getConnection();
                con.setAutoCommit(false);
                
                PreparedStatement statement = con.prepareStatement("SELECT * FROM dbp187.drive WHERE fid = (SELECT fid FROM reservieren WHERE kunde = ?) "
                		+ "AND status = 'offen' ;");
                statement.setShort(1, user.getBid());
            	List<Drive> driveList = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                	Drive offenerDrive = new Drive(
                			result.getString("startort"),
                			result.getString("zielort"),
                			result.getTimestamp("fahrtDatumZeit"),
                			result.getShort("maxPlaetze"),
                			result.getBigDecimal("fahrtkosten"),
                			result.getString("status"),
                			result.getShort("anbieter"),
                			result.getShort("transportmittel"),
                			result.getString("beschreibung"),
                			result.getShort("fid"));
                	driveList.add(offenerDrive);
                	
                }
                return driveList;
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
        }
// Methode die eigentlich nicht benötigt wird
	public Drive getDrive(Drive drive) throws Exception {
        try {
            Connection con = DBUtil.getConnection();
            con.setAutoCommit(false);
            
            PreparedStatement statement = con.prepareStatement("SELECT * FROM dbp187.drive WHERE fid = ? ;");
            statement.setShort(1, drive.getFid());

        	
            ResultSet result = statement.executeQuery();
            Drive driveToView = new Drive(
            		result.getString("startort"),
        			result.getString("zielort"),
        			result.getTimestamp("fahrtDatumZeit"),
        			result.getShort("maxPlaetze"),
        			result.getBigDecimal("fahrtkosten"),
        			result.getString("status"),
        			result.getShort("anbieter"),
        			result.getShort("transportmittel"),
        			result.getString("beschreibung"),
        			result.getShort("fid"));
            

            return driveToView;
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
	                    System.out.print("Daten in Datenbank geschrieben");
	                }
	                catch (SQLException e) {
	                    throw new StoreException(e);
	                }
	            }
	     }
    }
}
    

