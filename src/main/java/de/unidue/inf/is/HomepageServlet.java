package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.ibm.db2.jcc.a.e;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.reads.DriveRead;


public final class HomepageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static List<Drive> driveList = new ArrayList<>();
    private static Drive driveWantToView ;
    private static User dummyUser = new User( (short) (1), "DummyUser", "dummy@dummy.com");
 
    
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
			
    		
    		Map<String, Object> templateData = new HashMap<>();
    		List<Drive> resevedDrives = driveRead.getDriveReserviert(dummyUser);
    		List<Drive> openDrives = driveRead.getDriveOffen(dummyUser);
    		if (resevedDrives.size() > 0) {
    			templateData.put("reserveddrives", resevedDrives);
    		}
    		else {
				templateData.put("reserveddriveslenght", 0);
			}
    		if (openDrives.size() > 0)
    			templateData.put("opendrives", openDrives );
    		else {
				templateData.put("opendriveslenght", 0);
			}
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
//    	if ( request.getParameter("drive") != null) {
//        	driveWantToView  = (Drive) request.getAttribute("drive");
//        	 request.getRequestDispatcher("/view_drives.ftl").forward(request, response);
//    	}

//        if (request.getParameter("viewdrive"/*"picture/buttom pressed*/) = "pressed" ) {
//        	request.getRequestDispatcher("/view_drives.ftl").forward(request, response);
//        	response.sendRedirect("/view_drive");
//        }
// //eigentlich nicht notwendig, da                <a href="new_drive">Fahrt erstellen</a> im template steht
////    	else if(request.getParameter(/*"Neue Fahrt buttom pressed*/)){
////    			response.sendRedirect("/new_drive");
////		}
//    	else {
        doGet(request, response);
//    	}
    }
}
