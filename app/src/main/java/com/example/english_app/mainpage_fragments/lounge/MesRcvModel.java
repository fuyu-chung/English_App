package com.example.english_app.mainpage_fragments.lounge;

import java.sql.Timestamp;

public class MesRcvModel {

    private final String userName, userMsg;
    private final int userId;
    private final Timestamp uerMsgTime;
    public MesRcvModel(String userName, int userId, String userMsg, Timestamp uerMsgTime) {
        this.userName = userName;
        this.userId = userId;
        this.userMsg = userMsg;
        this.uerMsgTime = uerMsgTime;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public Timestamp getUerMsgTime() {
        return uerMsgTime;
    }
}
