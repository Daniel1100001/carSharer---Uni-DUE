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

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;

	public final class ReservierenStore implements Closeable {

	    private Connection connection;
	    private boolean complete;


	    public ReservierenStore() throws StoreException {
	        try {
	            connection = DBUtil.getExternalConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }


	    public void reservieren(short fid, User user, short anzPlaetze) throws StoreException {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(""
	            		+ "insert into  dbp187.reservieren (kunde, fahrt, anzPlaetze )"
	            		+ " values (?, ?, ?)");
	            preparedStatement.setShort(1, user.getBid());
	            preparedStatement.setShort(2, fid);
	            preparedStatement.setShort(3, anzPlaetze);
	            preparedStatement.executeUpdate();
	    		System.out.println("\n Fahrt ist reserviert");
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




