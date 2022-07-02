package controllers.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import services.PostService;

/**
 * Servlet implementation class IndexSearch
 */
@WebServlet("/indexsearch")
public class IndexSearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexSearch() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        投稿テーブル操作用のインスタンスを生成
        PostService service = new PostService();

        //	    指定されたページ数の一覧画面に表示するデータを取得
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        List<Post> posts = service.getPerPage(page);

        //        全てに投稿データの件数を取得
        long postCount = service.countAll();

        service.close();

        request.setAttribute("posts", posts);
        request.setAttribute("post_count", postCount);
        request.setAttribute("page", page);
        request.setAttribute("maxRow", 15);

        //        セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = (String)request.getSession().getAttribute("flush");
        if (flush != null) {
            request.setAttribute("flush", flush);
            request.getSession().removeAttribute(flush);
        }

        //        一覧画面を表示
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/results.jsp");
        rd.forward(request, response);
    }
}
