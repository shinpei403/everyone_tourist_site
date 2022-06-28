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

        //      管理者チェック
        Member mm = (Member) request.getSession().getAttribute("login_member");

        if(mm.getAdminFlag() == true) {

            String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {

                MemberService service = new MemberService();

                //                idを条件に会員データを論理削除する
                service.destroy(Integer.parseInt(request.getParameter("id")));

                service.close();

                //              セッションに登録完了のフラッシュメッセージを設定
                request.getSession().setAttribute("flush","退会が完了しました。");

                //              トップページにリダイレクト
                response.sendRedirect(request.getContextPath() + "/indextop");

            } else {

                //              エラー画面を表示
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
                rd.forward(request, response);

            }
        }
    }
}
