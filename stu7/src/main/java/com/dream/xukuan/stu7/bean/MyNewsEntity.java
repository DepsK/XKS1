package com.dream.xukuan.stu7.bean;

/**
 * @author XK
 * @date 2018/2/10.
 */
public class MyNewsEntity {
    String title;
    String fromName;
    String content;
    String imageUrl;


    public MyNewsEntity(String title, String fromName, String content, String imageUrl) {
        this.title = title;
        this.fromName = fromName;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "MyNewsEntity{" +
                "title='" + title + '\'' +
                ", fromName='" + fromName + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}