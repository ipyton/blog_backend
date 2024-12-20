package com.chen.notification.entities;

import com.datastax.oss.driver.api.mapper.annotations.Entity;

import java.time.Instant;
import java.util.List;

@Entity
public class SingleMessage {
    private String messageId;
    private String userId;
    private String receiverId;
    private String type;
    private Instant sendTime;
    private String content;
    private String referMessageId;
    private List<String> referUserIds;

    public SingleMessage(String messageId, String userId, String receiverId, String type, Instant sendTime, String content, String referMessageId, List<String> referUserIds, String messageType) {
        this.messageId = messageId;
        this.userId = userId;
        this.receiverId = receiverId;
        this.type = type;
        this.sendTime = sendTime;
        this.content = content;
        this.referMessageId = referMessageId;
        this.referUserIds = referUserIds;
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    private String messageType;



    public SingleMessage() {

    }

    public SingleMessage(String messageId, String userId, String receiverId, String type, Instant timestamp, String content, String referMessageId, List<String> referUserIds) {
        this.messageId = messageId;
        this.userId = userId;
        this.receiverId = receiverId;
        this.type = type;
        this.sendTime = timestamp;
        this.content = content;
        this.referMessageId = referMessageId;
        this.referUserIds = referUserIds;
    }




    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getSendTime() {
        return sendTime;
    }

    public void setSendTime(Instant timestamp) {
        this.sendTime = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReferMessageId() {
        return referMessageId;
    }

    public void setReferMessageId(String referMessageId) {
        this.referMessageId = referMessageId;
    }

    public List<String> getReferUserIds() {
        return referUserIds;
    }

    public void setReferUserIds(List<String> referUserIds) {
        this.referUserIds = referUserIds;
    }

    @Override
    public String toString() {
        return "SingleMessage{" +
                "messageId='" + messageId + '\'' +
                ", userId='" + userId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", type='" + type + '\'' +
                ", sendTime=" + sendTime +
                ", content='" + content + '\'' +
                ", referMessageId='" + referMessageId + '\'' +
                ", referUserIds=" + referUserIds +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
