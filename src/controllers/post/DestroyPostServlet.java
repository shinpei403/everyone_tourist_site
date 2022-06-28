package controllers.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.PostService;

/**
 * Servlet implementation class DestroyPostServlet
 */
@WebServlet("/destroypost")
public class DestroyPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyPostServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            //            テーブル操作用のインスタンスを生成
            PostService service = new PostService();

            service.destroy(Integer.parseInt(request.getParameter("id")));

            service.close();

            //            セッションに削除完了のフラッシュメッセージを設定
            request.getSession().setAttribute("flush", "削除が完了しました。");

            //            トップページにリダイレクト
            response.sendRedirect(request.getContextPath() + "/indextop");

        }
    }

}
