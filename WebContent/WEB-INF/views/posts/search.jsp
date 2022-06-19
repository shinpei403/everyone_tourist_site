<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>投稿検索</h2>

        <form method="GET" action="${pageContext.request.contextPath}/searchresultspost">

            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力内容にエラーがあります。<br />
                    <c:forEach var="error" items="${errors}">
                        ・<c:out value="${error}" /><br />
                    </c:forEach>
                </div>
            </c:if>

            <label for="hometown">地元</label><br />
            <input type="text" name="hometown" />
            <br /><br />

            <%-- <input type="hidden" name="_token" value="${_token}" />--%>
            <button type="submit">検索</button>
        </form>

        <%--<p><a>投稿一覧に戻る</a></p>--%>

    </c:param>
</c:import>