package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/showlogin")
public class ShowLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowLoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

//        セッションにフラッシュメッセージが登録されている場合はリクエストスコープに設定する
        String flush = (String)request.getSession().getAttribute("flush");

        if (flush != null) {
            request.setAttribute("flush", flush);
            request.getSession().removeAttribute(flush);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/showlogin.jsp");
        rd.forward(request, response);

    }

}
