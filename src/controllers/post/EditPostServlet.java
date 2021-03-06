package controllers.post;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Member;
import models.Post;
import services.PostService;

/**
 * Servlet implementation class EditPostServlet
 */
@WebServlet("/editpost")
public class EditPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPostServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //      投稿テーブル操作用のインスタンスを生成
        PostService service = new PostService();

        //      idを条件に投稿データを取得する
        Post p = service.findOne(Integer.parseInt(request.getParameter("id")));

        service.close();

        //    セッションからログイン中の会員情報を取得
        Member m = (Member) request.getSession().getAttribute("login_member");

        //      該当の投稿データが存在する、ログインしている会員が投稿の作成者
        if (p != null && m.getId() == p.getMember().getId() || m.getAdminFlag() == true) {
            //        CSRF対策
            request.setAttribute("_token", request.getSession().getId());

            request.setAttribute("post", p);

            //      編集画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp");
            rd.forward(request, response);
        } else {
//          エラー画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/unknown.jsp");
            rd.forward(request, response);
        }

    }

}
