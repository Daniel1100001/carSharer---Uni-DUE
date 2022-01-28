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
import com.ibm.db2.jcc.sqlj.n;

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
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbp187.fahrt WHERE fid != (SELECT fid FROM dbp187.reservieren WHERE kunde = ?) "
                		+ "AND status = 'offen'");
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

                PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbp187.fahrt  WHERE fid = (SELECT fid FROM dbp187.reservieren WHERE kunde = ?) "
                		+ "AND status = 'offen'");
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
	public Drive getDrive(Short fid) throws Exception {
        try {
        	System.out.print(DBUtil.checkDatabaseExistsExternal());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbp187.fahrt WHERE fid = ? ");
            statement.setShort(1, fid);

        	
            ResultSet result = statement.executeQuery();
            Drive driveToView = null;
            while (result.next()) {
            		driveToView = new Drive(
            		result.getString("startort"),
        			result.getString("zielort"),
        			result.getTimestamp("fahrtdatumZeit"),
        			result.getShort("maxPlaetze"),
        			result.getBigDecimal("fahrtkosten"),
        			result.getString("status"),
        			result.getShort("anbieter"),
        			result.getShort("transportmittel"),
        			result.getString("beschreibung"),
        			result.getShort("fid"));
            		
            }
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
	                    System.out.print("Aus Datenbank gelesen");
	                }
	                catch (SQLException e) {
	                    throw new StoreException(e);
	                }
	            }
	     }
    }
}
    

