package com.gs.guoaiagent.demo;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: hzt
 * @CreateTime: 2025-10-26  16:28
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class SpringAiOllamaInvoke implements CommandLineRunner {

    @Resource
    private ChatModel ollamaChatModel;


    @Override
    public void run(String... args) throws Exception {
        AssistantMessage assistantMessage = ollamaChatModel.call(new Prompt("hello,你是谁")).getResult().getOutput();
        System.out.println(assistantMessage.getText());
    }
}
