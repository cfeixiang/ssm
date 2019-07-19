<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/17 0017
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试RESTful</title>
</head>
<body>
    <form action="/test/update" method="post">
        <input type="hidden" name="_method" value="put">
        <input name="name" value="威远">
        <input name="age" value="22">
        <button>提交</button>
    </form>

</body>
</html>
