<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>管理者</h2>
        <h3>【会員一覧】</h3>
        <table id="member_list">
            <tbody>
                <tr>
                    <th>メールアドレス</th>
                    <th>氏名</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="member" items="${members}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${member.mail}" /></td>
                        <td><c:out value="${member.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${member.deleteFlag == true}">
                                    （退会済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/show?id=${member.id}">詳細を見る</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${memberCount} 件）<br />
            <c:forEach var="i" begin="1" end="${((memberCount - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="${pageContext.request.contextPath}/new">会員登録</a></p>

    </c:param>
</c:import>