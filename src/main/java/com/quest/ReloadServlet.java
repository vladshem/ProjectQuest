package com.quest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReloadServlet", value = "/reload")
public class ReloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем текущую сессию
        HttpSession currentSession = request.getSession();

        // Получаем текущий диалог из сессии
        Dialog dialog = extractDialog(currentSession);

        // Получим дочерний диалог
        if (!dialog.isLeaf()) {
            // Получаем результат выбора radio button в диалоге
            Integer radioOption = Integer.parseInt(request.getParameter("flexRadio"));
            if (radioOption != null) {
                Dialog childDialog = dialog.getDialogChildList().get(radioOption);
                // Установим новый диалог игрока в сессию
                currentSession.setAttribute("dialogInfo", childDialog);
            }
        }

        // Перенаправление запроса на страницу index.jsp через сервер
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Dialog extractDialog(HttpSession currentSession) {
        Object fieldAttribute = currentSession.getAttribute("dialogInfo");
        if (Dialog.class != fieldAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
        return (Dialog) fieldAttribute;
    }
}
