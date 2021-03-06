<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>検索結果</h2>

        <h3>【${hometown}の投稿一覧】</h3>

        <table id="post_list">
            <tbody>
                <tr>
                    <th class="post_name">氏名</th>
                    <th class="post_title">タイトル</th>
                    <th class="post_action">操作</th>
                </tr>
                <c:forEach var="post" items="${posts}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="post_name"><c:out value="${post.member.name}" /></td>
                        <td class="post_title">${post.title}</td>
                        <td class="post_action"><a href="${pageContext.request.contextPath}/showpost?id=${post.id}">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${post_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((post_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/searchresultspost?page=${i}"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="${pageContext.request.contextPath}/newpost">新規投稿</a></p>
        <p><a href="${pageContext.request.contextPath}/indexsearch">投稿一覧に戻る</a></p>
    </c:param>
</c:import>

