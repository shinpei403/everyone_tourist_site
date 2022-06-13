<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

    <h2><c:out value="${post.title}" /></h2>

    <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${post.member.name}" /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${post.content}" /></pre></td>
                </tr>
                <tr>
                    <th>写真</th>
                    <td><img src="${pageContext.request.contextPath}/show_image?id=${post.id}"></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${post.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${post.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_member.id == post.member.id}">
            <p>
                <a href="${pageContext.request.contextPath}/editpost?id=${post.id}">この投稿を編集する</a>
            </p>
        </c:if>

        <p>
            <a href="${pageContext.request.contextPath}/indexsearch">投稿検索画面に戻る</a>
        </p>

    </c:param>
</c:import>
