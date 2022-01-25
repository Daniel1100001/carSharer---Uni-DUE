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

	public final class DriveRead {

	    private Connection connection;

        public List<Drive> getDriveOffen(User user) throws Exception {
            try {
                Connection con = DBUtil.getConnection();
                con.setAutoCommit(false);
                
                PreparedStatement statement = con.prepareStatement("SELECT * FROM drive WHERE fid != (SELECT fid FROM reservieren WHERE kunde = ?) "
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
                			result.getClob("beschreibung"),
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
                
                PreparedStatement statement = con.prepareStatement("SELECT * FROM drive WHERE fid = (SELECT fid FROM reservieren WHERE kunde = ?) "
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
                			result.getClob("beschreibung"),
                			result.getShort("fid"));
                	driveList.add(offenerDrive);
                	
                }
                return driveList;
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
        }

	}

