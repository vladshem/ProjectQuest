package com.quest;

import java.util.ArrayList;
import java.util.List;

public class Dialog {
    // Заголовок страницы диалога
    private String headerName;

    // Наименование первого переключателя
    private String firstLabel;

    // Наименование второго переключателя
    private String secondLabel;


    // Список дочерних диалогов    // Признак победы
    //    private boolean isWin;
    private List<Dialog> dialogChildList = new ArrayList<>();

    // Признак последнего диалога
    public boolean isLeaf() {
        return (dialogChildList.size() == 0);
    }

    public List<Dialog> getDialogChildList() {
        return dialogChildList;
    }

    public void setDialogChildList(List<Dialog> dialogChildList) {
        this.dialogChildList = dialogChildList;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getFirstLabel() {
        return firstLabel;
    }

    public void setFirstLabel(String firstLabel) {
        this.firstLabel = firstLabel;
    }

    public String getSecondLabel() {
        return secondLabel;
    }

    public void setSecondLabel(String secondLabel) {
        this.secondLabel = secondLabel;
    }
}
