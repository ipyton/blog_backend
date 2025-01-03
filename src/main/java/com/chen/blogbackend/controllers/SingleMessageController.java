package com.chen.blogbackend.controllers;

import com.alibaba.fastjson.JSON;
import com.chen.blogbackend.entities.MessageSendingReceipt;
import com.chen.blogbackend.entities.NotificationMessage;
import com.chen.blogbackend.entities.SendingReceipt;
import com.chen.blogbackend.entities.deprecated.SingleMessage;
import com.chen.blogbackend.responseMessage.LoginMessage;
import com.chen.blogbackend.services.ChatGroupService;
import com.chen.blogbackend.services.FriendsService;
import com.chen.blogbackend.services.SearchService;
import com.chen.blogbackend.services.SingleMessageService;
import com.chen.blogbackend.util.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("chat")
@Controller()
@ResponseBody
public class SingleMessageController {

    @Autowired
    SingleMessageService service;

    @Autowired
    ChatGroupService groupService;
    @Autowired
    FriendsService friendsService;

    @Autowired
    SearchService searchService;

    @RequestMapping("get_messages")
    public LoginMessage getMessagesByUserId(String userId, String receiverId, String pageState){
        service.getMessageByUserId(userId, receiverId,pageState);
        return new LoginMessage(-1, "");
    }

    @RequestMapping("sendMessage")
    public SendingReceipt sendMessage(HttpServletRequest request, String receiverId, Long groupId, String content, String type) throws Exception {
        String senderId = (String) request.getAttribute("userEmail");

        if (type.equals("single")) {
            System.out.println(receiverId);
            System.out.println(content);
            System.out.println(type);
            System.out.println(groupId);
            return service.sendMessage(senderId, receiverId, content, type);
        } else if (type.equals("group")) {
            System.out.println(receiverId);
            System.out.println(content);
            System.out.println(type);
            System.out.println(groupId);
            return groupService.sendGroupMessage(senderId, groupId, content, type);
        }
        return new SendingReceipt(false, -1);
    }

    @RequestMapping("block")
    public LoginMessage blockUser(String userId, String receiverId) {
        service.blockUser(userId,receiverId);
        return new LoginMessage(-1, "");
    }

    @RequestMapping("unblock")
    public LoginMessage unblock(String userId, String receiverId) {
        service.unblockUser(userId, receiverId);
        return new LoginMessage(-1, "");

    }

    @RequestMapping("recall")
    public LoginMessage recall(String userId, String receiverId, String messageId) {
        service.recall(userId, receiverId, messageId);
        return new LoginMessage(-1, "");
    }


//    /*
//    * update chat list for a specific pair of users.
//    * */
//    @RequestMapping("getChatRecord")
//    public LoginMessage getChatList(String userId, String friendId) {
//
//    }


    ///get all
    @RequestMapping("getNewestMessages")
    public LoginMessage getNewestRecords(Long userId,Long timestamp, String pageState) {
        System.out.println(timestamp);
        List<NotificationMessage> newRecords = service.getNewRecords(userId, timestamp,pageState);
        System.out.println(JSON.toJSONString(newRecords));
        return new LoginMessage(1, JSON.toJSONString(newRecords));

    }


//    @RequestMapping("getRequestCache")
//    public LoginMessage getRequestCache(String userId) {
//
//    }
}
