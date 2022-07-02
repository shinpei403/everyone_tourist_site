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
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        管理者チェック
        Member mm = (Member) request.getSession().getAttribute("login_member");

        if (mm.getAdminFlag() == true) {

            MemberService service = new MemberService();

            //            idを条件に会員データを取得する
            Member m = service.findOne(Integer.parseInt(request.getParameter("id")));

            service.close();

            if (m == null) {

                //                エラー画面を表示
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
                rd.forward(request, response);

                return;

            }

            //            CSRF対策
            request.setAttribute("_token", request.getSession().getId());

            //            取得した会員情報
            request.setAttribute("member", m);

            //            編集画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/members/edit.jsp");
            rd.forward(request, response);

        } else {

            //          エラー画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
            rd.forward(request, response);

        }
    }
}
