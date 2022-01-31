package de.unidue.inf.is;

import javax.print.attribute.standard.Fidelity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.apache.commons.io.input.ReaderInputStream;

import com.ibm.db2.jcc.a.e;

import java.awt.datatransfer.SystemFlavorMap;
import java.awt.font.FontRenderContext;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.deletes.DriveDelete;
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.reads.DriveRead;
import de.unidue.inf.is.stores.ReservierenStore;
import de.unidue.inf.is.stores.StoreException;
import de.unidue.inf.is.stores.ReservierenStore;


public final class HomepageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Drive driveWantToView ;
    private static User dummyUser = new User( (short) (1), "DummyUser", "dummy@dummy.com");
    //private static List<String> reservedDrives = new ArrayList<>();
    
    //static {
    //    reservedDrives.add("Toyota");
    //    reservedDrives.add("Mitsubishi");
    //    reservedDrives.add("Honda");
    //}
    
    /*nicht notwendig weil keine Tabelle mit den Usern existiert
    *     private static List<User> getUsers(User user) throws Exception {

        try {
            Connection con = DBUtil.getConnection();
            con.setAutoCommit(false);

            PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            return userList;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DriveRead driveRead = new DriveRead();
    	try {
    		//request.setAttribute("drive.startort", driveRead.getDriveOffen(dummyUser) );
    	
    		List<Drive> reservedDrives = driveRead.getDriveReserviert(dummyUser);
    		List<Drive> openDrives = driveRead.getDriveOffen(dummyUser);
//Printen der Fahrten:
 //    		for (Drive drive : reservedDrives) {
//        		System.out.println("Anbieter  " + drive.getAnbieter());
//        		System.out.println("fahrtdatum  " + drive.getfahrtDatumString()+"\n");
//        		System.out.println("fid  "  + drive.getFid()+"\n");
//        		System.out.println("Zeit: " + drive.getfahrtZeitString()+"\n");
//        		System.out.println("mp  " + drive.getMaxPlaetze()+"\n");
//        		System.out.println("startort   : " + drive.getStartOrt()+"\n");
//        		System.out.println("zielo    " + drive.getZielOrt()+"\n");
//        		System.out.println("Transpm    " + drive.getTransportmittel()+"\n");
//        		System.out.println("Reserved Drives ist: " + drive.getFahrtkosten()+"\n");
//        		System.out.println("Timpstamp: "+ drive.getfahrtDatumZeit() +"\n");
//        		
//    		}
//    		System.out.println("hier die offenen:");
//    		
    		
//    		for (Drive drive : openDrives) {
//    			System.out.print("Offene\n\n\n");
//        		System.out.println("Anbieter  " + drive.getAnbieter());
//        		System.out.println("fahrtdatum  " + drive.getfahrtDatumString()+"\n");
//        		System.out.println("fid  "  + drive.getFid()+"\n");
//        		System.out.println("Zeit: " + drive.getfahrtZeitString()+"\n");
//        		System.out.println("mp  " + drive.getMaxPlaetze()+"\n");
//        		System.out.println("startort   : " + drive.getStartOrt()+"\n");
//        		System.out.println("zielo    " + drive.getZielOrt()+"\n");
//        		System.out.println("Transpm    " + drive.getTransportmittel()+"\n");
//        		System.out.println("Reserved Drives ist: " + drive.getFahrtkosten()+"\n");
//        		
//    		}

    			request.setAttribute("reservedDrives", reservedDrives);

    			request.setAttribute("openDrives", openDrives );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	driveRead.complete();
    	driveRead.close();
        request.getRequestDispatcher("/view_main.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	

    	if  ( request.getParameter("driveFid") != null && request.getParameter("fahrtlöschen").equals("fahrtlöschen" ) ) {
    		Short fid = Short.parseShort(request.getParameter("driveFid"));
    		System.out.println("\n Fid die zu löschen ist "+fid);
    		try (DriveDelete driveDelete = new DriveDelete()) {
				driveDelete.deleteFahrt(fid);
				driveDelete.complete();
				driveDelete.close();
			} catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		doGet(request, response);

    	}
    	else {
    		doGet(request, response);
    	}
    	
    		
//        if (request.getParameter("viewdrive"/*"picture/buttom pressed*/) = "pressed" ) {
//        	request.getRequestDispatcher("/view_drives.ftl").forward(request, response);
//        	response.sendRedirect("/view_drive");
//        }
// //eigentlich nicht notwendig, da                <a href="new_drive">Fahrt erstellen</a> im template steht
////    	else if(request.getParameter(/*"Neue Fahrt buttom pressed*/)){
////    			response.sendRedirect("/new_drive");
////		}
//    	else {
    	
//    	Short fid = Short.parseShort(request.getParameter("drive.fid"));
//    	System.out.println("fid ist :    "+fid);
//        if (fid != null) {
//        	request.setAttribute("drive.fid", fid );
//        	response.sendRedirect("new_drive");
//        }
//      else {
        	
            //request.getRequestDispatcher("/view_main.ftl").forward(request, response);
//		}

    }
}
