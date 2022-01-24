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

	public final class BewertungRead {

	    private Connection connection;

        public List<Bewertung> getBewertung(Drive driveToViewRate) throws Exception {
            try {
                Connection con = DBUtil.getConnection();
                con.setAutoCommit(false);
                
                PreparedStatement statement = con.prepareStatement("SELECT * FROM bewertung WHERE fid = ?");
                statement.setShort(1, driveToViewRate.getFid());
            	List<Bewertung> bewertungList = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                	Drive offenerDrive = new Drive(result.getShort("fid"),
                			result.getString("startort"),
                			result.getString("zielort"),
                			result.getTimestamp("fahrtDatumZeit"),
                			result.getShort("maxPlaetze"),
                			result.getBigDecimal("fahrtkosten"),
                			result.getString("status"),
                			result.getShort("anbieter"),
                			result.getShort("transportmittel"),
                			result.getClob("beschreibung"));
                	bewertungList.add(offenerDrive);
                	
                }
                return bewertungList;
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
                	Drive offenerDrive = new Drive(result.getShort("fid"),
                			result.getString("startort"),
                			result.getString("zielort"),
                			result.getTimestamp("fahrtDatumZeit"),
                			result.getShort("maxPlaetze"),
                			result.getBigDecimal("fahrtkosten"),
                			result.getString("status"),
                			result.getShort("anbieter"),
                			result.getShort("transportmittel"),
                			result.getClob("beschreibung"));
                	driveList.add(offenerDrive);
                	
                }
                return driveList;
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
        }

	}


public class BewertungRead {

}
