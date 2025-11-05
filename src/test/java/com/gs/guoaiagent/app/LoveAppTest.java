package com.gs.guoaiagent.app;

import com.gs.guoaiagent.rag.LoveAppDocumentLoader;
import com.gs.guoaiagent.tools.FileOperationTool;
import com.gs.guoaiagent.tools.WebSearchTool;
import jakarta.annotation.Resource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${search-api.api-key}")
    private String searchApiKey;

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

    @Test
    public void testFileTool(){
        FileOperationTool fileOperationTool = new FileOperationTool();
        fileOperationTool.writeFile("guoshi.txt","我是果实\\n dasd啊");
    }

    @Test
    public void testWebSearchTool(){
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
        String l = webSearchTool.searchWeb("李云龙");
        System.out.println(l);
    }
    @Test
    void doChatWithTools() {
        // 测试联网搜索问题的答案
        testMessage("周末想带女朋友去上海约会，推荐几个适合情侣的小众打卡地？");

//        // 测试网页抓取：恋爱案例分析
//        testMessage("最近和对象吵架了，看看编程导航网站（codefather.cn）的其他情侣是怎么解决矛盾的？");
//
//        // 测试资源下载：图片下载
//        testMessage("直接下载一张适合做手机壁纸的星空情侣图片为文件");
//
//        // 测试终端操作：执行代码
//        testMessage("执行 Python3 脚本来生成数据分析报告");
//
//        // 测试文件操作：保存用户档案
//        testMessage("保存我的恋爱档案为文件");

        // 测试 PDF 生成
//        testMessage("生成一份‘七夕约会计划’PDF，包含餐厅预订、活动流程和礼物清单");
    }

    private void testMessage(String message) {
        String chatId = UUID.randomUUID().toString();
        String answer = loveApp.doChatWithTools(message, chatId);
        org.junit.jupiter.api.Assertions.assertNotNull(answer);
    }

}
