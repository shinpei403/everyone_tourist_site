<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>みんなの観光サイト</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="${pageContext.request.contextPath}/indextop">みんなの観光サイト</a></h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_member != null}">
                        <c:if test="${sessionScope.login_member.adminFlag == true}" >
                            <a href="${pageContext.request.contextPath}/index">管理者</a>&nbsp;
                        </c:if>
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_member != null}">
                    <div id="member_name">
                        <c:out value="${sessionScope.login_member.name}" />
                        &nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/logout">ログアウト</a>
                    </div>
                </c:if>
            </div>

            <div id="content">${param.content}</div>
            <div id="footer">by NANA SHIOMI.</div>
        </div>
    </body>
</html>