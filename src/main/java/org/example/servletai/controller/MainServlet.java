package org.example.servletai.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletai.service.AIService;
import org.example.servletai.service.AIServiceFactory;

import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "AI 챗봇");
        req.setAttribute("answer", "반갑습니다!");
        req.getRequestDispatcher("/WEB-INF/chat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String question = req.getParameter("question");
        String model = req.getParameter("model");

        AIService aiService = AIServiceFactory.getService(model);
        String answer = aiService.chat("%s. 꾸미는 텍스트 없이 간략하게 대답".formatted(question), model);
        req.setAttribute("title", "AI 챗봇 답변");
        req.setAttribute("question", question);
        req.setAttribute("model", model);
        req.setAttribute("answer", answer);
        req.getRequestDispatcher("/WEB-INF/chat.jsp").forward(req, resp);
    }
}
