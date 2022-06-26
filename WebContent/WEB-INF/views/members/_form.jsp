<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

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
<input type="password" name="password"  />
<br /><br />

<input type="hidden" name="id" value="${member.id}" />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>


