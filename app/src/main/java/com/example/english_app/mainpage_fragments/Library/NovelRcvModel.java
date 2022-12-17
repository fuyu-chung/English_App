package com.example.english_app.mainpage_fragments.Library;

public class NovelRcvModel {
    private String novelTitle, novelAuthor;

    public String getNovelTitle() {
        return novelTitle;
    }

    public String getNovelAuthor() {
        return novelAuthor;
    }

    public NovelRcvModel(String novelTitle, String novelAuthor) {
        this.novelTitle = novelTitle;
        this.novelAuthor = novelAuthor;
    }
}
