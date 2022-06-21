package services;

import java.util.List;

import javax.persistence.NoResultException;

import models.Member;
import models.validators.MemberValidator;
import utils.EncryptUtil;

public class MemberService extends ServiceBase {

   public List<Member> getList() {

       List<Member> members = em.createNamedQuery("getAllMembers", Member.class).getResultList();

       return members;
   }

//   画面から入力された会員情報を元にデータを1件作成し、会員テーブルに登録

   public List<String> create(Member m, String pepper) {

//       パスワードをハッシュ化して設定
       String pass = EncryptUtil.getPasswordEncrypt(m.getPassword(), pepper);
       m.setPassword(pass);

//       登録内容のバリデーションを行う

       List<String> errors = MemberValidator.validate(this, m, true, true);

//       バリデーションがなければデータを記録する
       if (errors.size() == 0) {
           create(m);
       }

//       エラーを返却(エラーがなければ0件の空リスト)
       return errors;

   }

//   メールアドレスを条件に該当するデータの件数を取得し、返却する
   public long countByMail(String mail) {

//       指定したメールアドレスを保持する会員の件数を取得する
       long members_count = (long) em.createNamedQuery("memberCountRegisteredByMail", Long.class)
               .setParameter("mail", mail)
               .getSingleResult();
       return members_count;
   }

//   従業員データを1件登録する
   private void create(Member m) {

       em.getTransaction().begin();
       em.persist(m);
       em.getTransaction().commit();

   }

//   メールアドレスとパスワードを条件に検索し、データが取得できるかどうかで認証結果を返却する

   public Boolean validateLogin(String mail, String password, String pepper) {

       boolean isValidMember = false;
       if (mail != null && !mail.equals("") && password != null && !password.equals("")) {
           Member m = findOne(mail, password, pepper);

           if (m != null && m.getId() != null) {

//               データが取得できた場合、認証成功
               isValidMember = true;
           }
       }

       return isValidMember;
   }

   public Member findOne(String mail, String password, String pepper) {

       Member m = null;
       try {
//           パスワードのハッシュ化
           String pass = EncryptUtil.getPasswordEncrypt(password, pepper);

//           メールアドレスとハッシュ化済みパスワードを条件に会員を1件取得する
           m = em.createNamedQuery("membergetByMailAndPass", Member.class)
                   .setParameter("mail", mail)
                   .setParameter("password", pass)
                   .getSingleResult();

       } catch (NoResultException ex) {

       }

       return m;
   }

//   指定されたページの一覧画面に表示する会員データを取得し、Memberのリストで返却する
   public List<Member> getPerPage(int page) {
       List<Member> members = em.createNamedQuery("getAllMembers", Member.class)
               .setFirstResult(15 * (page - 1))
               .setMaxResults(15)
               .getResultList();

       return members;

   }

//   会員テーブルのデータ件数を取得し、返却する
   public long countAll() {
       long memberCount = (long) em.createNamedQuery("memberCount", Long.class)
               .getSingleResult();

       return memberCount;
   }

   public Member findOne(int id) {
       Member m = em.find(Member.class,id);

       return m;
   }

//   idを条件に会員データを論理削除する
   public void destroy(Integer id) {

//       idを条件に登録済みの会員情報を取得する
       Member savedMember = findOne(id);

//       論理削除フラグをたてる
       savedMember.setDeleteFlag(true);

//       更新処理を行う
       update(savedMember);

   }

//   会員データを更新する
   private void update(Member m) {
       em.getTransaction().begin();
       em.getTransaction().commit();
   }

}
