package com.quest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DialogTest {
    private Dialog rootDialog = new Dialog();

    @BeforeEach
    void setUp() {
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

    @AfterEach
    void setDown() {
        rootDialog = null;
    }

    @Test
    void rootDialogIsNotLeaf() {
        assertEquals(false, rootDialog.isLeaf());
    }

    @Test
    void lastDialogIsLeaf() {
        assertEquals(true, rootDialog.getDialogChildList().get(1).isLeaf());
    }

    @Test
    void whenCountChildGreateTwoThenException() {
        assertThrows(IndexOutOfBoundsException.class, () -> rootDialog.getDialogChildList().get(2));
    }

    @Test
    void countChildEqualsTwo() {
        assertEquals(2, rootDialog.getDialogChildList().size());
    }

    @Test
    void testHeaderNameRootDialog() {
        assertEquals("Ты потерял память. Принять вызов НЛО ?", rootDialog.getHeaderName());
    }

    @Test
    void testFirstLabelRootDialog() {
        assertEquals("Принять вызов", rootDialog.getFirstLabel());
    }

    @Test
    void testSecondLabelRootDialog() {
        assertEquals("Отклонить вызов", rootDialog.getSecondLabel());
    }

}