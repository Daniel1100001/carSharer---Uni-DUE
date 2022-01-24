package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.unidue.inf.is.domain.Drive;


public final class HomepageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static List<Drive> driveList = new ArrayList<>();

    // Statisches Beispiel
    // static { }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("drives", driveList);
        request.getRequestDispatcher("/view_main.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // GET Drive Database here and return

        doGet(request, response);
    }
}
