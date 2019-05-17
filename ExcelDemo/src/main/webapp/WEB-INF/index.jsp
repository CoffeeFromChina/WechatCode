<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<div class="excel">
    <form action="${pageContext.request.contextPath}/expor">
        <button>导出Excel</button>
    </form>
    <form action="${pageContext.request.contextPath}/import" , method="post" , enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit">
    </form>
</div>
</body>
</html>
