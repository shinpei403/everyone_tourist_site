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
<c:choose>
    <c:when test="${post.member == null}"><c:out value="${sessionScope.login_member.name}" /></c:when>
    <c:otherwise><c:out value="${post.member.name}" /></c:otherwise>
</c:choose>
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${post.title}" />
<br /><br />


<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${post.content}</textarea>
<br /><br />

<c:if test="${post.data != null}">
    <p>【登録済みの画像】
        <img src="${pageContext.request.contextPath}/show_image?id=${post.id}">
    </p>
    <label for="deleteFlag">画像を削除する</label>
    <input type="checkbox" name="deleteFlag" />
    <br /><br />
</c:if>

<input name="uploadFile" type="file" />
<br /><br />

<input type="hidden" name="id" value="${post.id}" />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>
