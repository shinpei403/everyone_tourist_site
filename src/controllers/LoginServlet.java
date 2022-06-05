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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member m = new Member();

        MemberService service = new MemberService();


        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String pepper = (String)request.getServletContext().getAttribute("pepper");

        Boolean isValidMember = service.validateLogin(mail, password, pepper);

        if (isValidMember) {
//            認証成功の場合

//            CSRF対策 tokenのチェック
            String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {

//                ログインした会員のDBデータを取得
                m = service.findOne(mail, password, pepper);

//                セッションにログインした会員を設定
                request.getSession().setAttribute("login_member", m);

//                セッションにログイン完了のフラッシュメッセージを設定
                request.getSession().setAttribute("flush", "ログインしました");

//                トップページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/indextop");

            } else {
//                認証失敗の場合

//                CSRF対策用トークンを設定
                request.setAttribute("_token", request.getSession().getId());

//                認証失敗エラーメッセージ表示フラグをたてる
                request.setAttribute("loginError", true);

//                入力されたメールアドレスを設定
                request.setAttribute("mail", mail);

//                ログイン画面を表示
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/showlogin.jsp");
                rd.forward(request, response);
            }

            service.close();
        }

    }

}
