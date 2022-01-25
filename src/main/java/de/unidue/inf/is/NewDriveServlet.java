package de.unidue.inf.is;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.DriveStore;
import de.unidue.inf.is.utils.DateTimeUtil;



/**
 * Einfaches Beispiel, das die Vewendung der Template-Engine zeigt.
 */
public final class NewDriveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Drive driveToAdd = new Drive();
    	request.setAttribute("drive", driveList);
    	request.getRequestDispatcher("/new_drive.ftl").forward(request, response);
    }

        
        
 /*       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Put the user list in request and let freemarker paint it.
        request.setAttribute("users", userList);

        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }

*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //wie bekomme ich aus den request die Daten im richtigen Type raus?
    	
    	String startOrt = request.getParameter("Von");
    	String zielOrt = request.getParameter("Bis");
    	String fahrtZeitString = request.getParameter("drive-time");
    	String fahrtDatumString = request.getParameter("date");
    	Timestamp fahrtDatumZeit = Timestamp.valueOf( DateTimeUtil.convertDateAndTimeToDB2DateTime(fahrtZeitString, fahrtDatumString));
    	short maxPlaetze = Short.parseShort(request.getParameter("Maximale Kapazität"));
    	
        // oder
        // short maxPlaetze  = (short) request.getIntHeader("Maximale Kapazität");
  
    	BigDecimal fahrtkosten = new BigDecimal( request.getParameter("drive-prize"));
    	String status = "offen";
    	short transportmittel;
    	switch (request.getParameter("cartype")) {
		    case "car":
			    transportmittel = 1;
			    break;
		    case "bus":
			    transportmittel = 2;
			    break;
		    case "transporter":
		        transportmittel = 3;
			    break;
		    default:
		    	transportmittel = (Short) null;
			    break;
		    }

        
    	Clob beschreibung = null;
		try {
			beschreibung = new javax.sql.rowset.serial.SerialClob(request.getParameter("Beschreibung").toCharArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        Drive newDrive = new Drive( startOrt, zielOrt, fahrtDatumZeit, maxPlaetze, fahrtkosten, status, maxPlaetze, transportmittel, beschreibung);
        DriveStore.addFahrt(newDrive, );

        doGet(request, response);
    }
}



