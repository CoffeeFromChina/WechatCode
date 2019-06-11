<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>name</td>
    </tr>

    <c:forEach items="${pageInfo.list}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
        </tr>
    </c:forEach>
</table>
<div style="width: 18%; margin: 0 auto; padding: 0 auto">
    <p>当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
        页,总 ${pageInfo.total } 条记录
    </p>

    <a href="list?pageNo=${pageInfo.navigateFirstPage}">第一页</a>
    <c:if test="${pageInfo.hasPreviousPage }">
        <a href="list?pageNo=${pageInfo.pageNum-1}">上一页</a>
    </c:if>

    <c:if test="${pageInfo.hasNextPage }">
        <a href="list?pageNo=${pageInfo.pageNum+1}">下一页</a>
    </c:if>

    <a href="list?pageNo=${pageInfo.navigateLastPage}">最后页</a>
</div>
</body>
</html>
