package com.gs.guoaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.UUID;


@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;
    @Test
    void doChat() {

        String chatId = UUID.randomUUID().toString();
        // 第一轮
        loveApp.doChat( "你好，我是程序员果实",chatId);
        // 第二轮
        loveApp.doChat("我想让另一半（篮球）更爱我",chatId);
        // 第三轮
        loveApp.doChat("我的另一半叫什么？我忘了，帮我回忆一下",chatId);
    }

    @Test
    void doChat2() {
        String chatId = UUID.randomUUID().toString();
        loveApp.doChatWithRAG("我已经结婚了，但是婚后关系不太亲密，怎么办",chatId);
    }
}