package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ibm.db2.jcc.DB2Administrator;

import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.DateTimeUtil;


import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.reads.BewertungRead;

public final class BewertungStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public BewertungStore() throws StoreException {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void addBewertung(Bewertung bewertungToAdd, Short fid, Short bid) throws StoreException {
        try {
        	PreparedStatement preparedStatement2 = connection.prepareStatement(""
            		+ "select beid From (insert into dbp187.bewertung (textnachricht, rating)"
            		+ "values (?, ?))");
            preparedStatement2.setString(1, bewertungToAdd.gettextnachricht());
            preparedStatement2.setShort(2, bewertungToAdd.getrating() );
        	ResultSet rs  = preparedStatement2.executeQuery();
        	Short beid = null ;
        	while (rs.next()) {
        		beid =  rs.getShort("beid");
        		System.out.println("BewertungsID die zu speichern ist:   "+beid);
			}
            
            
            
            PreparedStatement preparedStatement3 = connection.prepareStatement(""
    		+ "insert into dpb187.schreiben (benutzer, fahrt, bewertung)"
    		+ "values (?, ?, ?)");
            preparedStatement3.setShort(1, bid);
            preparedStatement3.setShort(2, fid);
            preparedStatement3.setShort(3, beid);
            preparedStatement3.executeUpdate();
            System.out.println("\nBewertung geschrieben\n");
        	
        	
//        	
//            PreparedStatement preparedStatement1 = connection.prepareStatement(""
//            		+ "insert into dbp187.bewertung (textnachricht, erstellungsdatum, rating)"
//            		+ " values (?, ?, ?)");
//
//            preparedStatement1.setString(1, bewertungToAdd.gettextnachricht());
//            preparedStatement1.setTimestamp(2, bewertungToAdd.geterstellungsdatum());
//            preparedStatement1.setShort(3, bewertungToAdd.getrating());
//            
//        	PreparedStatement preparedStatement2 = connection.prepareStatement(""
//            		+ "select beid From dbp187.bewertung where textnachricht= ? AND rating= ?)");
//        	preparedStatement2.setString(1, bewertungToAdd.gettextnachricht());
//        	preparedStatement1.setShort(2, bewertungToAdd.getrating());
//        	ResultSet rs  = preparedStatement2.executeQuery();
//        	Short beid = null ;
//        	while (rs.next()) {
//        		beid =  rs.getShort("beid");
//			}
//            
//            
//            
//            PreparedStatement preparedStatement3 = connection.prepareStatement(""
//    		+ "insert into dpb187.schreiben (benutzer, fahrt, bewertung)"
//    		+ "values (?, ?, ?)");
//            preparedStatement3.setShort(1, bid);
//            preparedStatement3.setShort(2, fid);
//            preparedStatement3.setShort(3, beid);
//            preparedStatement3.executeUpdate();
//           
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void complete() {
        complete = true;
    }


    @Override
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
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }

}



