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
///muss verbessert werden
	    
        public List<Bewertung> getBewertung(Drive driveToViewRate) throws Exception {
            try {
                Connection con = DBUtil.getConnection();
                con.setAutoCommit(false);
                
                PreparedStatement statement = con.prepareStatement("SELECT (textnachricht,erstellungsdatum, rating, email, )  FROM bewertung b,schreiben s, fahrt f WHERE f.fid = ? AND f.fid = s.fid AND s.beid = b.beid ORDER BY erstellungsdatum");
                
                statement.setShort(1, driveToViewRate.getFid());
            	List<Bewertung> bewertungList = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while(result.next()) {
                	Bewertung bewertung = new Bewertung(
                			result.getString("textnachricht"),
                			result.getShort("rating"),
                			result.getString("email")
                			);
                	bewertungList.add(bewertung);
                	
                }
                return bewertungList;
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
        }
	}
        
       