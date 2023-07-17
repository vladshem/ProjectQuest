package com.quest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "StartServlet", value = "/start")
public class StartServlet extends HttpServlet {
    private Dialog dialog;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем текущую сессию
        HttpSession currentSession = request.getSession();

        // Получаем имя игрока из запроса
        String namePlayer = request.getParameter("namePlayer");

        if (namePlayer == null || namePlayer.isEmpty()) {
            // Если имя игрока не задано, возвращаем на исходную страницу
            response.sendRedirect("/prologue.html");
            return;
        }

        // Добавляем имя игрока в сессию
        currentSession.setAttribute("namePlayer", namePlayer);

        // Добавление в сессию IP адреса
        currentSession.setAttribute("address", request.getRemoteAddr());

        // Получим количество сыгранных игр из сессии
        Integer countGames = (Integer) currentSession.getAttribute("countGames");
        if (countGames == null) {
            countGames = 1;
        } else {
            countGames++;
        }

        // Обновим число игр в сессии
        currentSession.setAttribute("countGames", countGames);

        // Создадим иерархию диалогов
        dialog = new Dialog();
        createDialogHierarchy(dialog);

        // Добавим диалог игрока в сессию
        currentSession.setAttribute("dialogInfo", dialog);

        // Перенаправление запроса на страницу index.jsp через сервер
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void createDialogHierarchy(Dialog rootDialog) {
        rootDialog.setHeaderName("Ты потерял память. Принять вызов НЛО ?");
        rootDialog.setFirstLabel("Принять вызов");
        rootDialog.setSecondLabel("Отклонить вызов");
        ArrayList<Dialog> dialogChildList = new ArrayList<>();

        Dialog dialog11 = new Dialog();
        dialog11.setHeaderName("Ты принял вызов. Поднимаешься на мостик к капитану?");
        dialog11.setFirstLabel("Подняться на мостик");
        dialog11.setSecondLabel("Отказаться подниматься на мостик");
        dialogChildList.add(dialog11);

        Dialog dialog12 = new Dialog();
        dialog12.setHeaderName("Ты отклонил вызов. Поражение");
        dialogChildList.add(dialog12);

        ArrayList<Dialog> dialogChildList1 = new ArrayList<>();
        Dialog dialog21 = new Dialog();
        dialog21.setHeaderName("Ты поднялся на мостик. Ты кто?");
        dialog21.setFirstLabel("Рассказать правду о себе");
        dialog21.setSecondLabel("Солгать о себе");
        dialogChildList1.add(dialog21);

        Dialog dialog22 = new Dialog();
        dialog22.setHeaderName("Ты не пошел на переговоры. Поражение");
        dialogChildList1.add(dialog22);

        ArrayList<Dialog> dialogChildList2 = new ArrayList<>();
        Dialog dialog31 = new Dialog();
        dialog31.setHeaderName("Тебя вернули домой. Победа");
        dialogChildList2.add(dialog31);

        Dialog dialog32 = new Dialog();
        dialog32.setHeaderName("Твою ложь разоблачили. Поражение");
        dialogChildList2.add(dialog32);

        dialog21.setDialogChildList(dialogChildList2);
        dialog11.setDialogChildList(dialogChildList1);
        rootDialog.setDialogChildList(dialogChildList);
    }
}
