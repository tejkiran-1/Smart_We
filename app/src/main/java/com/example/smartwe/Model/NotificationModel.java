package com.example.smartwe.Model;

public class NotificationModel {
    int notification_ProfileImg;
    String notificationTXT, timeTXT;

    public NotificationModel(int notification_ProfileImg, String notificationTXT, String timeTXT) {
        this.notification_ProfileImg = notification_ProfileImg;
        this.notificationTXT = notificationTXT;
        this.timeTXT = timeTXT;
    }

    public int getNotification_ProfileImg() {
        return notification_ProfileImg;
    }

    public void setNotification_ProfileImg(int notification_ProfileImg) {
        this.notification_ProfileImg = notification_ProfileImg;
    }

    public String getNotificationTXT() {
        return notificationTXT;
    }

    public void setNotificationTXT(String notificationTXT) {
        this.notificationTXT = notificationTXT;
    }

    public String getTimeTXT() {
        return timeTXT;
    }

    public void setTimeTXT(String timeTXT) {
        this.timeTXT = timeTXT;
    }
}
