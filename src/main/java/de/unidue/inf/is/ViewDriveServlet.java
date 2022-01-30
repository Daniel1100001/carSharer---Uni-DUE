package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.reads.BewertungRead;
import de.unidue.inf.is.reads.DriveRead;
import de.unidue.inf.is.reads.ReservierenRead;
import de.unidue.inf.is.domain.Drive;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public final class ViewDriveServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;





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
    	try {
			request.setAttribute("bewertungen", bewertungRead2.getBewertung(fid));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	bewertungRead2.complete();
    	bewertungRead2.close();
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
 
    	Short fid =new Short(request.getParameter("drive.fid"));
    	System.out.println("\nFid ist bei doGet:\n"+fid+"\n");
    	
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
