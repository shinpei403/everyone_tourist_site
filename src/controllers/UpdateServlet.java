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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        管理者チェック
        Member mm = (Member) request.getSession().getAttribute("login_member");
        if (mm.getAdminFlag() == true) {

            String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {

                //                会員情報のインスタンスを生成
                Member m = new Member();

                String id = request.getParameter("id");
                m.setId(Integer.parseInt(id));

                String name = request.getParameter("name");
                m.setName(name);

                String hometown = request.getParameter("hometown");
                m.setHometown(hometown);

                String mail = request.getParameter("mail");
                m.setMail(mail);

                String password = request.getParameter("password");
                m.setPassword(password);

                //              　アプリケーションスコープからpepper文字列を取得
                String pepper = (String)request.getServletContext().getAttribute("pepper");

                //              　テーブル操作用のインスタンスを生成
                MemberService service = new MemberService();

                //                会員情報を更新
                List<String> errors = service.update(m, pepper);

                service.close();

                if (errors.size() > 0) {
                    //                    更新中にエラーが発生した場合

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("member", m);
                    request.setAttribute("errors", errors);

                    //                  編集画面を表示
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/members/edit.jsp");
                    rd.forward(request, response);
                } else {

                    //                    更新中にエラーがなかった場合

                    //                  セッションに登録完了のフラッシュメッセージを設定
                    request.getSession().setAttribute("flush","更新が完了しました。");

                    //                  トップページにリダイレクト
                    response.sendRedirect(request.getContextPath() + "/indextop");

                }

            }

        } else {

            //            エラー画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
            rd.forward(request, response);

        }

    }

}
