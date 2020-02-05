import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class UserRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("id"),
                req.getParameter("surname"),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("repassword"),
                req.getParameter("born"),
                req.getParameter("counter"));

        List<String> errors = UserService.validate(user);

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("surname", user.getSurname());
            req.setAttribute("name", user.getName());
            req.setAttribute("login", user.getLogin());
            req.setAttribute("born", user.getBorn());
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            return;

        } else {

            User newUser = UserService.createUser(user);

            if (newUser != null) {
                req.getSession().setAttribute("user_id", newUser.getId());
                req.getSession().setAttribute("username", newUser.getSurname() + " " + newUser.getName());
                req.getSession().setAttribute("user_counter", newUser.getCounter());
                req.getRequestDispatcher("/index.jsp").forward(req, resp);

            } else {
                errors.add("Error creating user!");
                req.setAttribute("errors", errors);
                req.setAttribute("surname", user.getSurname());
                req.setAttribute("name", user.getName());
                req.setAttribute("login", user.getLogin());
                req.setAttribute("born", user.getBorn());
                req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            }

        }

    }
}
