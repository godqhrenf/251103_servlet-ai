<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String question = request.getAttribute("question") != null ? request.getAttribute("question").toString() : "";
    String model = request.getAttribute("model") != null ? request.getAttribute("model").toString() : "";
%>
<html>
<head>
    <title><%= request.getAttribute("title") %></title>
</head>
<body>
    <form method="post">
        <input name="question" placeholder="질문을 입력해주세요" value="<%= question %>">
        <select name="model">
            <option value="gemini-2.5-flash" <%= "gemini-2.5-flash".equals(model) ? "selected" : "" %>>Gemini 2.5 Flash</option>
            <option value="gemini-2.5-flash-lite" <%= "gemini-2.5-flash-lite".equals(model) ? "selected" : "" %>>Gemini 2.5 Flash Lite</option>
            <option value="openai/gpt-oss-120b" <%= "openai/gpt-oss-120b".equals(model) ? "selected" : "" %>>GPT OSS 120B</option>
            <option value="llama-3.1-8b-instant" <%= "llama-3.1-8b-instant".equals(model) ? "selected" : "" %>>Llama 3.1 8B</option>
            <option value="minimax/minimax-m2:free" <%= "minimax/minimax-m2:free".equals(model) ? "selected" : "" %>>MiniMax M2</option>
            <option value="alibaba/tongyi-deepresearch-30b-a3b:free" <%= "alibaba/tongyi-deepresearch-30b-a3b:free".equals(model) ? "selected" : "" %>>Tongyi DeepResearch 30B A3B</option>
        </select>
        <button>질문하기</button>
    </form>
    <p>
        🤖 : <%= request.getAttribute("answer") %>
    </p>
</body>
</html>
