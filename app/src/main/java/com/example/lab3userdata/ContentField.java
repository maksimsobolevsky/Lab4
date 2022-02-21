package com.example.lab3userdata;


public class ContentField {
    String content;
    Types type;

    public ContentField(String content, Types type) {
        this.content = content;
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public Types getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ContentField{" +
                "content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
