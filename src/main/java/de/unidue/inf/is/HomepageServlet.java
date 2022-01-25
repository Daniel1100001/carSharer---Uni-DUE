package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;


public final class HomepageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static List<Drive> driveList = new ArrayList<>();

    private static List<User> getUsers(User user) throws Exception {
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
