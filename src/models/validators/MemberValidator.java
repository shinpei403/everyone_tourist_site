package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import services.MemberService;

public class MemberValidator {

//    会員インスタンスの各項目についてバリデーションを行う

    public static List<String> validate(MemberService service, Member m, Boolean mailDuplicateCheckFlag, Boolean passwordCheckFlag) {

        List<String> errors = new ArrayList<String>();

//        氏名のチェック
        String nameError = validateName(m.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

//        地元のチェック
        String hometownError = validateHometown(m.getHometown());
        if (!hometownError.equals("")) {
            errors.add(hometownError);
        }

//        メースアドレスのチェック
        String mailError = validateMail(service,m.getMail(),mailDuplicateCheckFlag);
        if (!mailError.equals("")) {
            errors.add(mailError);
        }

//        パスワードのチェック
        String passError = validatePassword(m.getPassword(),passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }

//        氏名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却

        private static String validateName(String name) {

            if (name == null || name.equals("")) {
                return "氏名を入力してください。";
            }

            return "";
        }

//        地元に入力値があるかをチェックし、入力値がなければエラーメッセージを返却

        private static String validateHometown(String hometown) {

            if (hometown == null || hometown.equals("")) {
                return "地元を入力してください。";
            }

            return "";
        }

//        メールアドレスの入力チェックを行い、エラーメッセージを返却
//        mailDuplicateCheckFlag　メールアドレスの重複チェックをするかどうか(実施する:true 実施しない:false)

        private static String validateMail(MemberService service, String mail, Boolean mailDuplicateCheckFlag) {

            if (mail == null || mail.equals("")) {
                return "メールアドレスを入力してください。";
            }

            if (mailDuplicateCheckFlag) {
//                メールアドレスの重複チェックを実施

                long membersCount = isDuplicateMember(service, mail);

//                同一メールアドレスが既に登録されている場合はエラーメッセージを返却
                if (membersCount > 0) {
                    return "入力されたメールアドレスの情報は既に存在しています。";
                }
            }

            return "";
        }

//        会員テーブルに登録されている同一のメールアドレスのデータの件数

        private static long isDuplicateMember(MemberService service, String mail) {

            long membersCount = service.countByMail(mail);
            return membersCount;
        }

//        パスワードの入力チェックを行い、エラーメッセージを返却
//        passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)

        private static String validatePassword(String password, Boolean passwordCheckFlag) {

//            入力チェックを実施 かつ 入力値がなければエラーメッセージを返却
            if (passwordCheckFlag && (password == null || password.equals(""))) {
                return "パスワードを入力してください。";
            }

            return "";
        }

 }
