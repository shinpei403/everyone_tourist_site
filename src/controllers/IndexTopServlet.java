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
import models.Post;
import services.PostService;

/**
 * Servlet implementation class IndexTopServlet
 */
@WebServlet("/indextop")
public class IndexTopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexTopServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member loginMember = (Member) request.getSession().getAttribute("login_member");

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        PostService service = new PostService();

        //        ログイン中の会員が作成した投稿データを指定されたページ数の一覧画面に表示する分取得する
        List<Post> posts = service.getMinePerPage(loginMember, page);

        //        ログイン中の会員が作成した投稿データの件数を取得
        long myPostsCount = service.countAllMine(loginMember);

        request.setAttribute("posts", posts);
        request.setAttribute("posts_count", myPostsCount);
        request.setAttribute("page", page);
        request.setAttribute("maxRow", 15);

        //      セッションにフラッシュメッセージが設定されている場合はリクエストスコープに差し替え、セッションからは削除する
        String flush = (String)request.getSession().getAttribute("flush");

        if (flush != null) {
            request.setAttribute("flush", flush);
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/indextop.jsp");
        rd.forward(request, response);

    }

}
