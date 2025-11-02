package com.gs.guoaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hzt
 * @CreateTime: 2025-10-30  15:40
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class LoveAppVectorStoreConfig {
    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

    // 已经通过 PgVectorVectorStore代替
//    @Bean
//    public VectorStore loveAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
//        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
//        simpleVectorStore.add(loveAppDocumentLoader.loadMarkdowns());
//        return simpleVectorStore;
//    }
}
