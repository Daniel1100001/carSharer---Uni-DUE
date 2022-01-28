package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.reads.DriveRead;
import de.unidue.inf.is.domain.Drive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public final class ViewDriveServlet extends HttpServlet {
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	Short fid = Short.parseShort(getInitParameter("drive-fid"));
    	DriveRead driveRead = new DriveRead();
    	Drive driveToView = null;
		try {
			driveToView = driveRead.getDrive(fid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	driveRead.complete();
    	driveRead.close();
    	Map<String, Object> templateData = new HashMap<>();
    	templateData.put("drive-to-view",driveToView );
    	

    	request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
    }
}
