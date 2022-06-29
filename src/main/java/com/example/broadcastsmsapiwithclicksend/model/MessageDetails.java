package com.example.broadcastsmsapiwithclicksend.model;

public class MessageDetails {

    private String messageBody;

    private int contactListOfMultipleNumbers;

    private String sendingSource;

    public MessageDetails() {
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public int getContactListOfMultipleNumbers() {
        return contactListOfMultipleNumbers;
    }

    public void setContactListOfMultipleNumbers(int contactListOfMultipleNumbers) {
        this.contactListOfMultipleNumbers = contactListOfMultipleNumbers;
    }

    public String getSendingSource() {
        return sendingSource;
    }

    public void setSendingSource(String sendingSource) {
        this.sendingSource = sendingSource;
    }
}
