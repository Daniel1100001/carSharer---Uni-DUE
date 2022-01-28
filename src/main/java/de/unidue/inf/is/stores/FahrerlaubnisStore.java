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


import de.unidue.inf.is.domain.Fahrerlaubnis;
import de.unidue.inf.is.domain.User;

public final class FahrerlaubnisStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public FahrerlaubnisStore() throws StoreException {
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
     //       connection.allowMultiQueries=true;
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void addFahrerlaubnis(Fahrerlaubnis fahrerlaubnisToAdd, User user) throws StoreException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
            		+ "insert into  dbp187.Fahrerlaubnis(fahrer, ablauffatum)"
            		+ " values (?, ?);");
            preparedStatement.setShort(1, user.getBid());
            preparedStatement.setTimestamp(2, fahrerlaubnisToAdd.getAblaufdatum());
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
