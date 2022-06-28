package controllers.post;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchPost
 */
@WebServlet("/searchpost")
public class SearchPost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPost() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //      CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        //      投稿検索画面を表示
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/search.jsp");
        rd.forward(request, response);

    }


}
