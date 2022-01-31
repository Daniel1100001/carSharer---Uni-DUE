package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.reads.BewertungRead;
import de.unidue.inf.is.stores.BewertungStore;
import de.unidue.inf.is.stores.StoreException;

import java.io.IOException;


public final class RateDriveServlet extends HttpServlet {
	private static User dummyUser = new User( (short) (1), "DummyUser", "dummy@dummy.com");
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Short fid =  Short.parseShort(request.getParameter("driveFid"));

    	String bewertungString = request.getParameter("bewertungsText");
    	Short rating = Short.parseShort(request.getParameter("rating"));
    	System.out.println("\nFid bei doPost von RateDrive:  \n"+ fid
				+"\nBewertungstext:  " +bewertungString
				+"\nRating:    "+rating);
    	Bewertung neueBewertung = new Bewertung(bewertungString,rating);
    	try (BewertungStore bewertungStore = new BewertungStore()) {
			bewertungStore.addBewertung(neueBewertung, fid, dummyUser.getBid());
			bewertungStore.complete();
		} catch (StoreException e) {
			System.out.println("Berwertung erfolgreich gespeichert! ");
			e.printStackTrace();
		}
    	//   	bewertungStore.close();
//        response.sendRedirect("/view_main");
        request.getRequestDispatcher("/view_main.ftl").forward(request, response);
    
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Short fid =  Short.parseShort(request.getParameter("driveFid"));
    	request.setAttribute("driveFid", fid);
    	request.getRequestDispatcher("/new_rating.ftl").forward(request, response);
    
    }
}
