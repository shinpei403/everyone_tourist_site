package controllers.post;

import java.io.IOException;
import java.io.OutputStream;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import utils.DBUtil;


/**
 * Servlet implementation class ShowImageServlet
 */
@WebServlet("/show_image")
public class ShowImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowImageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        リクエストパラメータからファイルIDを取得
        String id = request.getParameter("id");

        // EntityManagerのインスタンスを生成
        EntityManager em = DBUtil.createEntityManager();

        // ファイルIDをキーにファイル情報を取得
        Post file = em.find(Post.class, Integer.parseInt(id));

     // ファイルデータを取得
        byte[] fileData = file.getData();

     // try-with-resources文を利用して、OutputStreamの変数を宣言
        try (
//                ResponseのOutputStreamを代入
                OutputStream os = response.getOutputStream();
                ) {
//            OutputStreamをファイルデータに書き込む
            os.write(fileData);

//            OutputStreamを強制的に書き込み
            os.flush();

//            EntityManagerのインスタンスを閉じる
            em.close();

        } catch (IOException e) {
            throw e;
        }

    }

}
