package com.gs.guoaiagent.app;

import com.gs.guoaiagent.rag.LoveAppDocumentLoader;
import jakarta.annotation.Resource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.UUID;


@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Resource
    private VectorStore ragApp;
    @Autowired
    private PgVectorStore pgVectorStore;
    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

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

    @Test
    void doChat3() {
        String chatId = UUID.randomUUID().toString();
        loveApp.doChatWithRAG("我已经结婚了，但是婚后关系不太亲密，怎么办",chatId);
    }

    @Test
    void doChat4() {
        List<Document> list = List.of(
          new Document("我是程序员果实，今天是11月2号，明天要上班啦"),
          new Document("遇到问题先问deepseek"),
          new Document("果实很帅")
        );
        pgVectorStore.add(list);
        pgVectorStore.add(loveAppDocumentLoader.loadMarkdowns());


        List<Document> result = pgVectorStore.similaritySearch(SearchRequest.builder().query("你是谁").build());
        if (result != null) {
            Assertions.assertThat(result.size()).isEqualTo(3);
        }

    }
    @Test
    public void doChat5(){
        String result = loveApp.doChatWithRAG2("你是谁？", "s2133111");
        System.out.println(result);
    }
}
