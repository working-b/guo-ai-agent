package com.gs.guoaiagent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

/**
 * @Author: hzt
 * @CreateTime: 2025-11-01  17:22
 * @Description: 自定义基于阿里云云服务的RAG增强advisor
 * @Version: 1.0
 */
@Configuration
public class LoveAppRagCloudAdvisorConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String dashScopeApiKey;

    private final String KNOWLEDGE_BASE_NAME = "恋爱大师";

    @Bean
    public Advisor loveAppRagCloudAdvisor(){
        DashScopeApi dashScopeApi = new DashScopeApi(dashScopeApiKey);
        DashScopeDocumentRetriever dashScopeDocumentRetriever = new DashScopeDocumentRetriever(dashScopeApi,
                DashScopeDocumentRetrieverOptions.builder()   // 用户制定一些知识库的信息
                        .withIndexName(KNOWLEDGE_BASE_NAME)
                        .build()
        );
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(dashScopeDocumentRetriever)
                .build();
    }
}
