<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="name">氏名</label><br />
<input type="text" name="name" value="${member.name}" />
<br /><br />

<label for="hometown">地元</label><br />
<input type="text" name="hometown" value="${member.hometown}" />
<br /><br />

<label for="mail">メールアドレス</label><br />
<input type="text" name="mail" value="${member.mail}" />
<br /><br />

<label for="password">パスワード</label><br />
<input type="text" name="password" value="${member.password}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>


