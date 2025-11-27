<%@ page import="kr.java.mybatis.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>마이바티스 게시판 프로젝트</title>
</head>
<body>
    <nav>
        <a href="posts/create" >작성하기</a>
    </nav>
    <h1>게시글 목록</h1>
    <%
        List<Post> posts = (List<Post>) request.getAttribute("posts");
        for (Post p : posts) {
    %>
        <p>
            작성자 : <%= p.getAuthor().getNickname() %> <br>
            제목 : <%= p.getTitle() %> <br>
            내용 : <%= p.getContent() %> <br>
            <%-- 추천.. --%>
        </p>
    <% } %>
</body>
</html>
