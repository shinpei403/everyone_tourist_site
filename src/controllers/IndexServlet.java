package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Member;
import services.MemberService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        管理者チェック
        Member mm = (Member) request.getSession().getAttribute("login_member");

        if(mm.getAdminFlag() == true) {


            MemberService service = new MemberService();
            //
            //          List<Member> members = service.getList();

            //          指定されたページ数の一覧画面に表示する会員データを取得
            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch(NumberFormatException e) {}

            List<Member> members = service.getPerPage(page);

            //          全ての会員データの件数を取得
            long memberCount = service.countAll();

            service.close();

            request.setAttribute("members", members);
            request.setAttribute("memberCount", memberCount);
            request.setAttribute("page", page);
            request.setAttribute("maxRow", 15);

            ////        セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            //        String flush = (String)request.getSession().getAttribute("flush");
            //
            //        if (flush != null) {
            //            request.setAttribute("flush", flush);
            //            request.getSession().removeAttribute("flush");
            //        }

            //        会員一覧画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/members/index.jsp");
            rd.forward(request, response);

        } else {

            //            エラー画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
            rd.forward(request, response);

        }

    }
}
