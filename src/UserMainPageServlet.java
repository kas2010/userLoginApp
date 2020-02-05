import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/", "/index"})
public class UserMainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = req.getSession().getAttribute("user_id") == null ? null :
                (Long) req.getSession().getAttribute("user_id");
        Integer userCounter = req.getSession().getAttribute("user_counter") == null ? null :
                (Integer) req.getSession().getAttribute("user_counter") + 1;

        if (userId != null) {
            UserService.setCounter(userId, userCounter);
        }

        req.getSession().setAttribute("user_counter", userCounter);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
