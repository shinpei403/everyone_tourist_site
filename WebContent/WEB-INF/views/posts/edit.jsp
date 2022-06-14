<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>投稿 編集</h2>
        <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/updatepost" >
            <c:import url="_form.jsp" />
        </form>

        <%--<p><a>投稿一覧に戻る</a></p>--%>

    </c:param>
</c:import>