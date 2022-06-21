package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Member;
import services.MemberService;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MemberService service = new MemberService();

//        idを条件に会員データを取得する
        Member m = service.findOne(Integer.parseInt(request.getParameter("id")));

        service.close();

        if (m == null) {

//      エラー画面を表示

            return;
        }

//      CSRF対策
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("member", m);

//      詳細画面を表示
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/members/show.jsp");
        rd.forward(request, response);

    }

}
