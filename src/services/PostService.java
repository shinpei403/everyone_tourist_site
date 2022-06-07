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


}
