package com.chen.blogbackend.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chen.blogbackend.entities.*;
import com.chen.blogbackend.entities.GroupMessage;
import com.chen.blogbackend.entities.deprecated.SingleMessage;
import com.chen.blogbackend.responseMessage.LoginMessage;
import com.chen.blogbackend.services.AccountService;
import com.chen.blogbackend.services.AuthorizationService;
import com.chen.blogbackend.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@RequestMapping("search")
@Controller()
@ResponseBody
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    AccountService accountService;


    @PostMapping("contactById")
    public LoginMessage searchContactById(String userId) {
        List<Account> userDetails = accountService.searchUserById(userId);
        System.out.println(JSON.toJSONString(userDetails));

        return new LoginMessage(0, JSON.toJSONString(userDetails, SerializerFeature.SkipTransientField, SerializerFeature.WriteMapNullValue));
    }

    @GetMapping("getProfileById")
    public Account getProfileById(String userId) {
        return accountService.getProfileById(userId);
    }

    @PostMapping("contactByName")
    public void searchContactByName(String userName) {
        //using elasticsearch
    }

    @PostMapping("getChat")
    public LoginMessage getChatSearchResult(String keyword, int from) throws IOException {
        List<SingleMessage> singleMessages = searchService.searchSingleMessage(keyword, from);
        List<GroupMessage> groupMessages = searchService.searchGroupMessage(keyword, from);
        String content = JSON.toJSONString(singleMessages) + "__||||__" + JSON.toJSONString(groupMessages);
        return new LoginMessage(-1, content);
    }

    @PostMapping("setSingleChat")
    public LoginMessage setSingleChat(SingleMessage message) throws IOException, InterruptedException {
        searchService.setSearchSingleMessage(message);
        return new LoginMessage(-1, "");
    }

    @PostMapping("setGroupChat")
    public LoginMessage setGroupChat(GroupMessage message) throws IOException, InterruptedException {
        searchService.setGroupMessage(message);
        return new LoginMessage(-1, "");
    }


    @PostMapping("setUser")
    public LoginMessage setUser(Friend friend) throws IOException, InterruptedException {
        searchService.setUserIndex(friend);
        return new LoginMessage(-1, "");
    }

    @PostMapping("getUser")
    public LoginMessage getUser(String keyword, int from) throws IOException {
        searchService.searchByUser(keyword, from);
         return new LoginMessage(-1, "");
    }

    @PostMapping("setContent")
    public LoginMessage setContent(Post post) throws IOException, InterruptedException {
        searchService.setArticleIndex(post);
        return new LoginMessage(-1, "");
    }

    @PostMapping("getContent")
    public LoginMessage getContent(String userId, String keyword, int from) throws IOException {
        searchService.searchByArticle(userId, keyword, from);
        return new LoginMessage(-1, "");
    }


}
