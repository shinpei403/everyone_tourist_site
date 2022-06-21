package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.MemberService;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            MemberService service = new MemberService();

//            idを条件に会員データを論理削除する
            service.destroy(Integer.parseInt(request.getParameter("id")));

            service.close();

            request.setAttribute("flush","退会が完了しました。");

//            トップページにリダイレクト
            response.sendRedirect(request.getContextPath() + "/indextop");

        }
    }

}
