package de.unidue.inf.is.deletes;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


    public  void deleteFahrt(Short fid) throws StoreException {
        try {
        	System.out.print(DBUtil.checkDatabaseExistsExternal());
 //       	preparedBatc preparedBatch = connection.();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dbp187.fahrt WHERE fid = ? ");
            preparedStatement.setShort(1, fid);
            preparedStatement.executeUpdate();
            System.out.println("Daten aus Fahrt gelöscht.\n");
            
//            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM dbp187.reservieren WHERE fahrt = ?");
//            preparedStatement2.setShort(1, fid);
//            preparedStatement2.executeUpdate();
//            System.out.println("Daten aus reservieren gelöscht.\n");
//            
//            PreparedStatement preparedStatement31 = connection.prepareStatement("SELECT dbp187.bewertung FROM schreiben WHERE fahrt = ?");
//            preparedStatement31.setShort(1, fid);
//            ResultSet resultSet = preparedStatement31.executeQuery();
//            System.out.println("Daten aus bewertrungen gelesen.");
//            PreparedStatement preparedStatement3 = connection.prepareStatement("DELETE FROM dbp187.bewertung WHERE beid = ?");
//            while (resultSet.next()) {
//            	preparedStatement3.setShort(1, resultSet.getShort("beid"));
//            	preparedStatement3.addBatch();
//			}
//            preparedStatement3.executeUpdate();
//            System.out.println("Daten aus Bewertungen gelöscht.");
//            
//            PreparedStatement preparedStatement4 = connection.prepareStatement("DELETE FROM dbp187.schreiben WHERE fahrt = ?");
//            preparedStatement4.setShort(1, fid);
//            preparedStatement4.executeUpdate();

            

//            preparedBatch.addBatch(preparedStatement.toString());
//            preparedBatch.addBatch(preparedStatement2.toString());
//            preparedBatch.addBatch(preparedStatement3.toString());
//            preparedBatch.addBatch(preparedStatement4.toString());
//            preparedBatch.executeBatch();

            System.out.println("\n Fahrt wurde in Tabelle fahrt gelöscht");
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


