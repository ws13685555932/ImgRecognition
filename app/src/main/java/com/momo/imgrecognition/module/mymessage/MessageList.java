package com.momo.imgrecognition.module.mymessage;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class MessageList {
    List<MessageResponse> messageList;

    public List<MessageResponse> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageResponse> messageList) {
        this.messageList = messageList;
    }

    public MessageList(List<MessageResponse> messageList) {

        this.messageList = messageList;
    }
}
