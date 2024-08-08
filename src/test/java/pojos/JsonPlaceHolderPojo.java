package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//bilinmeyen bir değer varsa görmezden gel bu durum databaseden gelen taskte karsılıgı olmayan ıd için filan gerekli
public class JsonPlaceHolderPojo {
    // private variables oluşturulur(null durumunda)
    private Integer userId;
    private String title;
    private Boolean completed;

    // Constructorlar oluşturulur (default ve tüm parametreleri içeren)
    public JsonPlaceHolderPojo() {
    }

    // Default constructor de-serialization aşamasında gerekebiliyor.


    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    // Getters ve Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // toString methodu oluşturulur


    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}