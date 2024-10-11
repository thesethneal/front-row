package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Message;
import com.techelevator.model.MessageBandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @GetMapping("/inbox")
    public List<MessageBandDto> getMyMessages(Principal principal) {
        return messageDao.getInboxMessages(principal);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/inbox/{messageId}/read")
    public void markMessageAsRead(@PathVariable long messageId, Principal principal) {
        messageDao.markMessageAsRead(messageId, principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Message message) {
        messageDao.sendMessage(message);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/outbox")
    public List<MessageBandDto> getMySentMessages(Principal principal) {
        return messageDao.getOutboxMessages(principal);
    }

    @PutMapping("/inbox/read-all")
    public void markAllAsRead(Principal principal) {
        messageDao.markAllAsRead(principal);
    }



}
