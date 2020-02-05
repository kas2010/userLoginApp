import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.getUser(req.getParameter("login"), req.getParameter("password"));

        if (user != null) {

            req.getSession().setAttribute("user_id", user.getId());
            req.getSession().setAttribute("username", user.getSurname() + " " + user.getName());
            req.getSession().setAttribute("user_counter", user.getCounter());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } else {

            req.getSession().setAttribute("errorMessage", "Error! Incorrect login or password.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
