<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>id : ${member.id} の会員情報 編集ページ</h2>
        <p> (パスワードは変更する場合のみ入力してください) </p>

        <form method="POST" action="${pageContext.request.contextPath}/update">
            <c:import url="_form.jsp" />
        </form>

        <p>
            <a href="${pageContext.request.contextPath}/index">管理者画面に戻る</a>
        </p>
    </c:param>
</c:import>