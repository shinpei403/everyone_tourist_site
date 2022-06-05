package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexTopServlet
 */
@WebServlet("/indextop")
public class IndexTopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexTopServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//      セッションにフラッシュメッセージが設定されている場合はリクエストスコープに差し替え、セッションからは削除する
      String flush = (String)request.getSession().getAttribute("flush");

      if (flush != null) {
          request.setAttribute("flush", flush);
          request.getSession().removeAttribute("flush");
      }

      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/indextop.jsp");
      rd.forward(request, response);

    }

}
