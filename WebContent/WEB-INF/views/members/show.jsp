<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>id : ${member.id} の会員情報詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>メールアドレス</th>
                    <td><c:out value="${member.mail}" /></td>
                </tr>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${member.name}" /></td>
                </tr>
                <tr>
                    <th>地元</th>
                    <td><c:out value="${member.hometown}" /></td>
                </tr>
                <tr>
                    <th>権限</th>
                    <td><c:choose>
                            <c:when test="${member.adminFlag == true}">管理者</c:when>
                            <c:otherwise>一般</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="${pageContext.request.contextPath}/edit?id=${member.id}">この会員情報を編集する</a>
        </p>

        <p>
            <a href="#" onclick="confirmDestroy();">この会員を退会させる</a>
        </p>
        <form method="POST"
            action="${pageContext.request.contextPath}/destroy">
            <input type="hidden" name="id" value="${member.id}" />
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if (confirm("本当に削除してよろしいですか？")) {
                    document.forms[0].submit();
                }
            }
        </script>

        <p>
            <a href="${pageContext.request.contextPath}/index">管理者画面に戻る</a>
        </p>
    </c:param>
</c:import>