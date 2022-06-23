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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//      管理者チェック
      Member mm = (Member) request.getSession().getAttribute("login_member");

      if (mm.getAdminFlag() == true) {

            String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {

//              インスタンスを生成
                Member m = new Member();

                String name = request.getParameter("name");
                m.setName(name);

                String hometown = request.getParameter("hometown");
                m.setHometown(hometown);

                String mail = request.getParameter("mail");
                m.setMail(mail);

                String password = request.getParameter("password");
                m.setPassword(password);

                m.setAdminFlag(false);

                m.setDeleteFlag(false);

                MemberService service = new MemberService();

//               アプリケーションスコープからpepper文字列を取得
                String pepper = (String)request.getServletContext().getAttribute("pepper");

//              会員情報登録
                List<String> errors = service.create(m,pepper);

                service.close();

                if (errors.size() > 0) {
//                  更新中にエラーが発生した場合

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("member", m);
                    request.setAttribute("errors", errors);

    //                会員登録画面を表示
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/members/new.jsp");
                    rd.forward(request, response);
                } else {
    //                登録中にエラーがなかった場合

    //                セッションに登録完了のフラッシュメッセージを設定
                    request.getSession().setAttribute("flush","会員登録が完了しました。");

    //                トップページにリダイレクト
                    response.sendRedirect(request.getContextPath() + "/indextop");
                }

            } else {

//              エラー画面を表示

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
                rd.forward(request, response);
            }
        }
    }
}
