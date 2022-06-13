package services;

import java.util.List;

import models.Post;
import models.validators.PostValidator;

//投稿テーブル操作に関わる処理を行うクラス

public class PostService extends ServiceBase {

    public List<String> create(Post p) {
        List<String> errors = PostValidator.validate(p);
        if(errors.size() == 0) {
            createInternal(p);
        }

//        バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    private void createInternal(Post p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

//    指定されたページ数の一覧画面に表示するデータを取得し、Postのリストで返却する
    public List<Post> getPerPage(int page) {
        List<Post> posts = em.createNamedQuery("postgetAll", Post.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        return posts;

    }

//    投稿テーブルのデータの件数を取得し、返却する
    public long countAll() {
        long posCount = (long) em.createNamedQuery("postcount", Long.class)
                .getSingleResult();

        return posCount;
    }

//    idを条件に取得したデータをPostのインスタンスで返却する
    public Post findOne(int id) {
        Post p = findOneInternal(id);

        return p;
    }

//    idを条件にデータを1件取得し、Postのインスタンスで返却する
    private Post findOneInternal(int id) {
        Post p = em.find(Post.class, id);

        return p;
    }





}
