package controllers.post;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import models.Post;
import services.PostService;

/**
 * Servlet implementation class UpdatePostServlet
 */
@WebServlet("/updatepost")
@MultipartConfig(maxFileSize=104857600)
public class UpdatePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePostServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            //            投稿テーブル操作のインスタンスを作成
            PostService service = new PostService();

            //            idを条件に投稿データを取得する
            Post p = service.findOne(Integer.parseInt(request.getParameter("id")));

            //            フォームの内容を各フィールドに上書き
            String title = request.getParameter("title");
            p.setTitle(title);

            String content = request.getParameter("content");
            p.setContent(content);

            //            更新日時のみ上書き
            LocalDateTime now = LocalDateTime.now();
            p.setUpdatedAt(now);

            //ファイルデータを更新
            Part part = request.getPart("uploadFile");

            //          try-with-resources文を利用して、InputStreamの変数を宣言
            try (
                    //                  ファイルストリームを取得
                    InputStream fileStream = part.getInputStream();
                    ) {

                //              ファイルデータをByte[]に型変換し、設定
                p.setData(IOUtils.toByteArray(fileStream));

            } catch (IOException e) {
                throw e;
            }

            //            チェックボックスの情報を取得

            boolean deleteFlag = false;

            if (request.getParameter("deleteFlag") != null && "on".equals(request.getParameter("deleteFlag"))) {
                //                deleteFlagのcheckboxがチェックされている場合
                deleteFlag = true;
            }

            //            日報データを更新
            List<String> errors = service.update(p, deleteFlag);

            service.close();

            if (errors.size() > 0) {
                //                更新中にエラーが発生した場合

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("post", p);
                request.setAttribute("errors", errors);

                //                編集画面を再表示
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp");
                rd.forward(request, response);
            } else {
                //                更新中にエラーがなかった場合

                //                セッションに更新完了のフラッシュメッセージを設定
                request.getSession().setAttribute("flush", "更新が完了しました。");

                //              トップページにリダイレクト
                response.sendRedirect(request.getContextPath() + "/indextop");
            }

        }

    }

}
