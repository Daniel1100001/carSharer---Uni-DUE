package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Bewertung;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.reads.BewertungRead;
import de.unidue.inf.is.stores.BewertungStore;

import java.io.IOException;


public final class RateDriveServlet extends HttpServlet {
	private static User dummyUser = new User( (short) (1), "DummyUser", "dummy@dummy.com");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Short fid =  Short.parseShort(request.getParameter("drive-fid"));
    	String bewertungString = request.getParameter("bewertungsText");
    	Short rating = Short.parseShort(request.getParameter("rating"));
    	Bewertung neueBewertung = new Bewertung(bewertungString,rating);
    	BewertungStore bewertungStore = new BewertungStore();
    	bewertungStore.addBewertung(neueBewertung, fid, dummyUser.getBid());
    	bewertungStore.complete();
    	bewertungStore.close();
    	request.getRequestDispatcher("/new_rating.ftl").forward(request, response);
    
    
    
    }
}
