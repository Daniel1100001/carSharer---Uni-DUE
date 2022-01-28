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
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.DateTimeUtil;



/**
 * Einfaches Beispiel, das die Vewendung der Template-Engine zeigt.
 */
public final class NewDriveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    Drive driveToAdd = new Drive();
    //	request.setAttribute("drive", driveList);
    	request.getRequestDispatcher("/new_drive.ftl").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String startOrt = request.getParameter("city-start");
    	String zielOrt = request.getParameter("city-end");
       	String fahrtZeitString =  request.getParameter("drive-time").toString();
       	String fahrtDatumString = request.getParameter("drive-date").toString();
       

    	String fahrtDatumZeitString =   fahrtDatumString +" "+ fahrtZeitString+":00.000000";
    	java.util.Date fahrtDatumZeit = DateTimeUtil.connectDateTimeToDate(fahrtDatumZeitString);
    	Timestamp fahrtDatumZeitTimestamp = new Timestamp(fahrtDatumZeit.getTime());
//test der Timpstamp convertierung 
    	String timestampString = DateTimeUtil.convertDateAndTimeToDB2DateTime(fahrtDatumString, fahrtZeitString);
       	Timestamp fahrtDatumZeitTimestamp2 = Timestamp.valueOf(timestampString);
    	
    	short maxPlaetze = Short.parseShort(request.getParameter("drive-capacity"));
        // oder
        // short maxPlaetze  = (short) request.getIntHeader("Maximale Kapazität");
    	BigDecimal fahrtkosten = new BigDecimal( request.getParameter("drive-prize"));
    	String status = "offen";
    	short transportmittel = 0;
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
			    break;
		    }

        
    	String beschreibung = request.getParameter("drive-description");
//Direkte Methode um in Clob umzuwandeln
    	//		try {
//			beschreibung = new javax.sql.rowset.serial.SerialClob(request.getParameter("drive-description").toCharArray());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
//    	String fahrtZeitString =  DateTimeUtil.extractTimeFromDB2DateTimeString( request.getParameter("drive-time").toString());
//    	String fahrtDatumString = DateTimeUtil.extractDateFromDB2DateTimeString( request.getParameter("trip-date").toString());
//    	String fahrtZeitString =  DateTimeUtil.extractTimeFromDB2DateTimeString( request.getParameter("drive-time"));
//    	String fahrtDatumString = DateTimeUtil.extractDateFromDB2DateTimeString( request.getParameter("trip-date"));
//    	String fahrtZeitString = (String) request.getAttribute("drive-time");
//    	String fahrtDatumString =  request.getAttribute("trip-date");
    	System.out.println(fahrtZeitString.getClass().getName());
    	System.out.println(fahrtZeitString);
    	System.out.println(fahrtDatumString.getClass().getName());
    	System.out.println(fahrtDatumString);

       	System.out.println(fahrtDatumZeit.getClass().getName());
    	System.out.println(fahrtDatumZeit);
    	System.out.println("Timestamp: "+fahrtDatumZeitTimestamp);
    	System.out.println(fahrtDatumZeitString);
    	System.out.println(beschreibung);
    	
    

    	



    	
		//normal Vorgehensweise für Speicherung
        Drive newDrive = new Drive(startOrt, zielOrt, fahrtDatumZeitTimestamp, maxPlaetze, fahrtkosten, status, maxPlaetze, transportmittel, beschreibung);
        DriveStore driveStore = new DriveStore();
 //       DBUtil.getExternalConnection();

        driveStore.addFahrt(newDrive, new User( (short) (1), "DummyUser", "dummy@dummy.com"));
        driveStore.complete();
        driveStore.close();
        response.sendRedirect("/view_main");
        doGet(request, response);
    }
}



