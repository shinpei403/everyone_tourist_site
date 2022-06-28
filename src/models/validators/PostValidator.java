package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Post;

public class PostValidator {

    public static List<String> validate(Post p) {
        List<String> errors = new ArrayList<String>();

        //        タイトルのチェック
        String titleError = validateTitle(p.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        //        内容のチェック
        String contentError = validateContent(p.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        return errors;

    }

    //    タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却

    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return "タイトルを入力して下さい。";
        }

        //        入力値がある場合は空文字を返却
        return "";
    }

    //    内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却

    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return "内容を入力して下さい。";
        }

        //        入力値がある場合は空文字を返却
        return "";
    }
}
