package de.unidue.inf.is.stores;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Clob;
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
//	            connection = DBUtil.getConnection();
	            connection = DBUtil.getExternalConnection();
	            connection.setAutoCommit(false);
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }


	    public  void addFahrt(Drive driveToAdd, User user) throws StoreException {
	        try {
	        	System.out.print(DBUtil.checkDatabaseExistsExternal());
	            PreparedStatement preparedStatement = connection.prepareStatement(""
	            		+ "insert into dbp187.fahrt (startort, zielort, fahrtdatumzeit, maxPlaetze, fahrtkosten, status, anbieter, transportmittel, beschreibung )"
	            		+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	            
	           	System.out.println("\n\nhier kommen die Daten:\n "+
	           			driveToAdd.getStartOrt()+ "\n" +
	           			driveToAdd.getZielOrt() +"\n" +
	           			driveToAdd.getfahrtDatumZeit() +"\n" +
	           			driveToAdd.getMaxPlaetze() +"\n" +
	           			driveToAdd.getFahrtkosten()+"\n" +
	           			driveToAdd.getStatusFahrt()+"\n" +
	           			driveToAdd.getAnbieter()+"\n" +
	           			driveToAdd.getTransportmittel()+"\n" +
	           			driveToAdd.getBeschreibung()+"\n" );
	            preparedStatement.setString(1, driveToAdd.getStartOrt());
	            preparedStatement.setString(2, driveToAdd.getZielOrt());
	            preparedStatement.setTimestamp(3, driveToAdd.getfahrtDatumZeit());
                preparedStatement.setShort(4, driveToAdd.getMaxPlaetze());
	            preparedStatement.setBigDecimal(5, driveToAdd.getFahrtkosten());
	            preparedStatement.setString(6, driveToAdd.getStatusFahrt());
	            preparedStatement.setShort(7, user.getBid());
	            preparedStatement.setShort(8, driveToAdd.getTransportmittel());
	            
	            
	            Clob clobbeschreibung = connection.createClob();
	            clobbeschreibung.setString(1,driveToAdd.getBeschreibung());
	            preparedStatement.setClob(9, clobbeschreibung);

	            
	            preparedStatement.executeUpdate();

	        	
	        	
//	        	//gelöstes Problem, denn im preparedStatement muss auch der Schema/Benutzername vor der Tabelle stehen	
//	        	PreparedStatement preparedStatement2 = connection.prepareStatement(""
//	            		+ "insert into dbp187.fahrt (startort, zielort, fahrtdatumzeit, maxPlaetze, fahrtkosten , anbieter, transportmittel, beschreibung )"
//	            		+ " values ('Berlin', 'gggggg', '2022-02-02-08.00.00.000000', 2, 25, 1, 1, NULL)");
//	           	System.out.println("\n\nhier kommt das prest22\n\n ");
//	            preparedStatement2.executeUpdate();
//	           	System.out.println("\n\nhier kommt das prest31\n\n ");
	        }
	        catch (SQLException e) {
	            throw new StoreException(e);
	        }
	    }


	    public void complete() {
	        this.complete = true;
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

