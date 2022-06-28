<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <%-- <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>--%>
        <h2>会員登録</h2>

        <form method="POST" action="${pageContext.request.contextPath}/create">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="${pageContext.request.contextPath}/showlogin">ログイン画面に戻る</a></p>

    </c:param>
</c:import>
