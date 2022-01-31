package de.unidue.inf.is.stores;

import java.beans.Statement;
import java.io.Closeable;
import java.io.IOException;
import java.security.interfaces.RSAKey;
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
//	    		String sqlExistString = ("Select * FROM dbp187.reservieren WHERE fahrt ="+Short.toString(fid) +" AND kunde = "+Short.toString(user.getBid()).toString());

//	        	PreparedStatement preparedStatement0 = connection.prepareStatement(sqlExistString);
	    //		System.out.println("\n Schon vorhandene Einträge:"+ resultSet.next());
	    		
    			String sqlExistString = "update dbp187.reservieren"
    					+ " set anzPlaetze = "+ Short.toString(anzPlaetze)
    					+ " WHERE fahrt ="+Short.toString(fid) +" AND kunde = "+Short.toString(user.getBid());
	        	System.out.print("Boolean von reservieren zurückgegeben:  "+connection.createStatement().execute(sqlExistString));
	    		System.out.println(sqlExistString);
	        	if(connection.createStatement().execute(sqlExistString)) {
		        	PreparedStatement preparedStatement = connection.prepareStatement(""
		            		+ "update dbp187.reservieren "
		            		+ "set anzPlaetze = ? "
		            		+ "WHERE kunde = ? "
		            		+ "AND fahrt = ? ");
		        			preparedStatement.setShort(1, anzPlaetze);
		        			preparedStatement.setShort(2, user.getBid());
		        			preparedStatement.setShort(3, fid);
		        			preparedStatement.executeUpdate();
		    	    		System.out.println("\n Reservierung wurde geupdatet");
					
				}
	        	else {
	        		PreparedStatement preparedStatement1 = connection.prepareStatement(""
		            		+ "insert into  dbp187.reservieren (kunde, fahrt, anzPlaetze )"
		            		+ " values (?, ?, ?)");
		        			preparedStatement1.setShort(1, user.getBid());
		        			preparedStatement1.setShort(2, fid);
		        			preparedStatement1.setShort(3, anzPlaetze);
		        			preparedStatement1.executeUpdate();
		    	    		System.out.println("\n Fahrt ist reserviert");
				} 

	    	}
	        catch (SQLException e) {
	        	e.printStackTrace();
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




