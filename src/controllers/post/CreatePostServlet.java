package controllers.post;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
 * Servlet implementation class CreatePostServlet
 */
@WebServlet("/createpost")
public class CreatePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePostServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

//            投稿インスタンスを作成
            Post p = new Post();

//              セッションからログイン中の会員情報を取得
            Member m = (Member) request.getSession().getAttribute("login_member");

            String title = request.getParameter("title");
            p.setTitle(title);

            String content = request.getParameter("content");
            p.setContent(content);

            p.setMember(m);

//            登録日時、更新日時は現在時刻を設定
            LocalDateTime now = LocalDateTime.now();
            p.setCreatedAt(now);
            p.setUpdatedAt(now);

//　　　　　　投稿テーブル操作のインスタンスを作成
            PostService service = new PostService();

//            投稿情報登録
            List<String> errors = service.create(p);

            if(errors.size() > 0) {
//                投稿中にエラーがあった場合

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("post", p);
                request.setAttribute("errors", errors);

//                新規投稿画面を再表示
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/new.jsp");
                rd.forward(request, response);
            } else {
//                投稿中にエラーがなかった場合

//                セッションに登録完了のフラッシュメッセージを設定
                request.getSession().setAttribute("flush", "登録が完了しました。");

//                トップページにリダイレクト
                response.sendRedirect(request.getContextPath() + "/indextop");
            }
        }
    }
}
