package controllers.post;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import services.PostService;

/**
 * Servlet implementation class ShowPostServlet
 */
@WebServlet("/showpost")
public class ShowPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPostServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        投稿テーブル操作用のインスタンスを生成
        PostService service = new PostService();

//        idを条件に投稿データを取得する
        Post p = service.findOne(Integer.parseInt(request.getParameter("id")));

        service.close();

        if(p != null) {
            request.setAttribute("post", p);

//            詳細画面を表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/show.jsp");
            rd.forward(request, response);
        }
    }

}
