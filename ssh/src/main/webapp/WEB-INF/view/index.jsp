<%@page language="java" pageEncoding="UTF-8" %>
<%--<%@include file="/WEB-INF/layout/taglib/jspTaglib.jsp"%>--%>
<!doctype html>
<html>
<head>
    <title>首页title中的内容</title>
    <meta name='description' content='首页head中的内容'>
    <link rel="stylesheet" type="text/css" href="${CSS}/index.css?${V}"/>
</head>
<body>
<div class="index">首页body中的内容</div>
<%--<img src="${CTX}/code/gif"/>--%>
<img id="img1"  src="${CTX}/code/jpg"/>
<canvas id="canvas1">1</canvas>
<script type="text/javascript" src="${JS}/index.js?${V}"></script>

</body>
</html>