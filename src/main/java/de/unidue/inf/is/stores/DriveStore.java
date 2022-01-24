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

	public final class DriveStore implements Closeable {

	    private Connection connection;
	    private boolean complete;


	    public DriveStore() throws StoreException {
	        try {
	            connection = DBUtil.getConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }


	    public void addFahrt(Drive driveToAdd, User user) throws StoreException {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(""
	            		+ "insert into fahrt (startort, zielort, fahrtdatumzeit, maxPlaetze, fahrkosten, status, anbieter, transportmittel, beschreibung )"
	            		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
	            preparedStatement.setString(1, driveToAdd.getStartOrt());
	            preparedStatement.setString(2, driveToAdd.getZielOrt());
	            preparedStatement.setTimestamp(3,
	                   Timestamp.valueOf(DateTimeUtil.convertDateAndTimeToDB2DateTime(driveToAdd.getfahrtDatumString(), driveToAdd.getfahrtZeitString())));
                preparedStatement.setShort(4, driveToAdd.getMaxPlaetze());
	            preparedStatement.setBigDecimal(5, driveToAdd.getFahrtkosten());
	            preparedStatement.setString(6, driveToAdd.getStatusFahrt());
	            preparedStatement.setShort(7, user.getBid());
	            preparedStatement.setShort(8, driveToAdd.getTransportmittel());
	            preparedStatement.setClob(9, driveToAdd.getBeschreibung());
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

