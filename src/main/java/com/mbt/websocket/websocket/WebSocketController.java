package com.mbt.websocket.websocket;

import com.mbt.websocket.websocket.dto.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    private final WSService wsService;

    public WebSocketController(WSService wsService) {
        this.wsService = wsService;
    }

    @PostMapping("/send-message")
    public void send(@RequestBody final Message message) {
        wsService.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivate(@PathVariable("id")String userId, @RequestBody final Message message) {
        wsService.notifyUser(userId,message.getMessageContent());
    }
}
