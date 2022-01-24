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
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;

	public final class schreibenStore implements Closeable {

	    private Connection connection;
	    private boolean complete;


	    public schreibenStore() throws StoreException {
	        try {
	            connection = DBUtil.getConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }


	    public void schreiben(Drive driveToRate, User user, Bewertung bewertung) throws StoreException {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(""
	            		+ "insert into schreiben (benutzer, fahrt, bewertung )"
	            		+ " values (?, ?, ?);");
	            preparedStatement.setShort(1, user.getBid());
	            preparedStatement.setShort(2, driveToRate.getFid());
	            preparedStatement.setShort(3, bewertung.getBeid());
	            preparedStatement.executeUpdate();
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


