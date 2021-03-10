package com.example.trialsinthewild;

public class Message {
    final public static int BASE_MESSAGE = -1;

    private int message_id;
    private int replies_to;
    private String message;

    public Message(int message_id, int replies_to, String message) {
        this.message_id = message_id;
        this.replies_to = replies_to;
        this.message = message;
    }
}
