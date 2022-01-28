package de.unidue.inf.is.deletes;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.StoreException;
import de.unidue.inf.is.utils.DBUtil;

public final class DriveDelete implements Closeable {

    private Connection connection;
    private boolean complete;


    public DriveDelete() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public  void deleteFahrt(Drive driveToDelete, User user) throws StoreException {
        try {
        	System.out.print(DBUtil.checkDatabaseExistsExternal());
            PreparedStatement preparedStatement = connection.prepareStatement(""
            		+ "DELETE FROM fahrt WHERE fid = ?;"
            		+ "DELETE FROM reservieren WHERE fid = ?;"
            		+ "DELETE FROM bewertung WHERE beid = SELECT beid FROM schreiben WHERE fid = ? ;"
            		+ "DELETE FROM schreiben WHERE fid = ? ;");
           	System.out.println("\n\nhier kommt das prest\n\n "+preparedStatement);
            preparedStatement.setShort(1, driveToDelete.getFid());
            preparedStatement.setShort(2, driveToDelete.getFid());
            preparedStatement.setShort(3, driveToDelete.getFid());
            preparedStatement.setShort(4, driveToDelete.getFid());
            preparedStatement.executeUpdate();
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


