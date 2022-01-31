package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.io.FromNetASCIIInputStream;

import de.unidue.inf.is.reads.BewertungRead;
import de.unidue.inf.is.reads.DriveRead;
import de.unidue.inf.is.reads.ReservierenRead;
import de.unidue.inf.is.stores.ReservierenStore;
import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.file.spi.FileSystemProvider;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public final class ViewDriveServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static User dummyUser = new User( (short) (1), "DummyUser", "dummy@dummy.com");
	final DecimalFormat df = new DecimalFormat("0.00");




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		//wichtig ist, dass der Html-Form-Name immer unterschiedlich(speziell) ist, weil sonst immer nur die erste Form einmalig gefüllt wird
    	Short fid = Short.parseShort(request.getParameter("driveFid"));
		
    	
    	
    	System.out.println("fid ist bei DoPost:    "+fid);


    	DriveRead driveRead = new DriveRead();
    	Drive driveToView = null;
		try {
			driveToView = driveRead.getDriveWithBeschreibung(fid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Anbieter  " + driveToView.getAnbieter());
		System.out.println("fahrtdatum  " + driveToView.getfahrtDatumString()+"\n");
		System.out.println("fid  "  + driveToView.getFid()+"\n");
		System.out.println("Zeit: " + driveToView.getfahrtZeitString()+"\n");
		System.out.println("mp  " + driveToView.getMaxPlaetze()+"\n");
		System.out.println("startort   : " + driveToView.getStartOrt()+"\n");
		System.out.println("zielo    " + driveToView.getZielOrt()+"\n");
		System.out.println("Transpm    " + driveToView.getTransportmittel()+"\n");
		System.out.println("Fahrtkosten:   : " + driveToView.getFahrtkosten()+"\n");
		System.out.println("Timpstamp: "+ driveToView.getfahrtDatumZeit() +"\n");
		System.out.printf("Beschreibung:   ", driveToView.getBeschreibung() + "\n");
    	driveRead.complete();
    	driveRead.close();

    	request.setAttribute("driveToView", driveToView);
    	BewertungRead bewertungRead = new BewertungRead();
    	
    	String emailAnbieter = "Keine Email vorhanden";
		try {
			emailAnbieter = bewertungRead.getEmail(driveToView.getAnbieter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bewertungRead.complete();
    	bewertungRead.close();
    	ReservierenRead reservierenRead = new ReservierenRead();
    	Short anzahlReserviertePlaetze = -1;
		try {
			anzahlReserviertePlaetze = reservierenRead.getReserviertePlaetze(fid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Short freiePlaetze = (short) (driveToView.getMaxPlaetze() - anzahlReserviertePlaetze) ;
		;
		request.setAttribute("emailAnbieter", emailAnbieter );
    	request.setAttribute("freiePlaetze", freiePlaetze );
    	reservierenRead.complete();
    	reservierenRead.close();
    	BewertungRead bewertungRead2 = new BewertungRead();
    	List<Bewertung> bewertungen = new ArrayList<>();
		try {
			bewertungen = bewertungRead2.getBewertung(fid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
			request.setAttribute("bewertungen",bewertungen );
		} catch (Exception e) {
			e.printStackTrace();
		}
    	bewertungRead2.complete();
    	bewertungRead2.close();
    	Double durchschnittsRating = 0d;
    	for (Bewertung bewertung : bewertungen) {
    		durchschnittsRating = (durchschnittsRating + Double.valueOf(bewertung.getrating()));
    		System.out.printf("\n Durchsschnittrating :"+ durchschnittsRating+"/n");
    	}
    	Double dRBD;
    	if (bewertungen.size()>0) {
    		System.out.println( (durchschnittsRating/( bewertungen.size()) ));
    		dRBD = (durchschnittsRating/bewertungen.size());
    		System.out.printf("\n Anzahl Bewertungen :"+ bewertungen.size()+"/n");
        	request.setAttribute("durchschnittsRating",  dRBD);
    		System.out.printf("\n Durchsschnittrating :"+ dRBD+"/n");

		}
    	else {
        	request.setAttribute("durchschnittsRating",  durchschnittsRating);
		}
    	

    	

    	
    	request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
    }
    	
    	
    	
    	
    	
//        if (fid != null) {
//        	request.setAttribute("drive.fid", fid );
//        	request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
//        	//response.sendRedirect("view_drive");
//        }
//      else {
//            request.getRequestDispatcher("/view_main.ftl").forward(request, response);
//		}
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
    		Short fid = Short.parseShort(request.getParameter("driveFid"));
			System.out.println("\nFid  " +fid+"\nPlätze  "+request.getParameter("zuResP")+"\nFahrtreservieren   "+request.getParameter("fahrtreservieren"));
    	if ( fid != null && request.getParameter("zuResP")!= null&& request.getParameter("fahrtreservieren").equals("fahrtreservieren") ) {
    		

    		System.out.println("\n Fid die zu resevieren ist: "+fid);
    		Short zuResPShort = Short.valueOf(request.getParameter("zuResP"));
    		System.out.println("\n Anzahl der zu reservierenden Personen: "+zuResPShort);
    		ReservierenStore reservierenStore = new ReservierenStore();
    		reservierenStore.reservieren(fid, dummyUser, zuResPShort );
    		reservierenStore.complete();
    		reservierenStore.close();
    	}
    	
    	System.out.println("\nFid ist bei doGet ViewDrive  :\n"+fid+"\n");
    	
    	DriveRead driveRead = new DriveRead();
    	Drive driveToView = null;
		try {
			driveToView = driveRead.getDrive(fid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Anbieter  " + driveToView.getAnbieter());
		System.out.println("fahrtdatum  " + driveToView.getfahrtDatumString()+"\n");
		System.out.println("fid  "  + driveToView.getFid()+"\n");
		System.out.println("Zeit: " + driveToView.getfahrtZeitString()+"\n");
		System.out.println("mp  " + driveToView.getMaxPlaetze()+"\n");
		System.out.println("startort   : " + driveToView.getStartOrt()+"\n");
		System.out.println("zielo    " + driveToView.getZielOrt()+"\n");
		System.out.println("Transpm    " + driveToView.getTransportmittel()+"\n");
		System.out.println("Fahrtkosten:   : " + driveToView.getFahrtkosten()+"\n");
		System.out.println("Timpstamp: "+ driveToView.getfahrtDatumZeit() +"\n");
		System.out.println("Beschreibung: "+ driveToView.getBeschreibung() +"\n");
    	driveRead.complete();
    	driveRead.close();

    	request.setAttribute("driveToView", driveToView);
    	
    	BewertungRead bewertungRead = new BewertungRead();
    	String emailAnbieter = "Keine Email vorhanden";
		try {
			emailAnbieter = bewertungRead.getEmail(driveToView.getAnbieter());
		} catch (Exception e) {
		}
    	bewertungRead.complete();
 //   	bewertungRead.close();
    	ReservierenRead reservierenRead = new ReservierenRead();
    	Short anzahlReserviertePlaetze = 0;
		try {
			anzahlReserviertePlaetze = reservierenRead.getReserviertePlaetze(fid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Short freiePlaetze = (short) (driveToView.getMaxPlaetze() - anzahlReserviertePlaetze) ;
		;
		request.setAttribute("emailAnbieter", emailAnbieter );
    	request.setAttribute("freiePlaetze", freiePlaetze );
    	reservierenRead.complete();
    	reservierenRead.close();
    	BewertungRead bewertungRead2 = new BewertungRead();
    	
    	try {
			request.setAttribute("bewertungen", bewertungRead2.getBewertung(fid));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	bewertungRead2.complete();
    	bewertungRead2.close();

    	request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
    }

}
