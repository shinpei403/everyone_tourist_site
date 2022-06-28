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
import search.SearchValidator;
import services.PostService;

/**
 * Servlet implementation class SearchResultsPost
 */
@WebServlet("/searchresultspost")
public class SearchResultsPost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResultsPost() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostService service = new PostService();

        String hometown = (String)request.getParameter("hometown");

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        List<String> errors = SearchValidator.validate(hometown);

        if (errors.size() == 0) {

            //          指定した地元の投稿を指定されたページ数の一覧画面に表示する分取得する
            List<Post> posts = service.getHometownPage(hometown, page);

            //            指定した地元の投稿の件数を取得
            long postsCount = service.countHometown(hometown);

            service.close();

            request.setAttribute("posts", posts);
            request.setAttribute("post_count", postsCount);
            request.setAttribute("page", page);
            request.setAttribute("maxRow", 15);
            request.setAttribute("hometown", hometown);

            ////          セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
            //          String flush = (String)request.getSession().getAttribute("flush");
            //          if (flush != null) {
            //              request.setAttribute("flush", flush);
            //              request.getSession().removeAttribute(flush);
            //          }

            //          検索結果を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/searchresults.jsp");
            rd.forward(request, response);

        } else {

            request.setAttribute("errors", errors);

            //          検索画面を再表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/search.jsp");
            rd.forward(request, response);
        }

    }

}
