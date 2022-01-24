package de.unidue.inf.is.reads;
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

	public final class DriveRead {

	    private Connection connection;

        public DriveRead() throws Exception {
            try {
                Connection con = DBUtil.getConnection();
                con.setAutoCommit(false);

                PreparedStatement statement = con.prepareStatement("SELECT * FROM benutzer");

                ResultSet result = statement.executeQuery();
                while(result.next()) {
                    System.out.println(result/*.getString("first")*/);
                }
            }
            catch (SQLException e) {
                throw new Exception(e);
            }
        }

	}

