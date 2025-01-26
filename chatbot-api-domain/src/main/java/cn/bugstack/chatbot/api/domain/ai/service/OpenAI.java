package cn.bugstack.chatbot.api.domain.ai.service;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;

import cn.bugstack.chatbot.api.domain.ai.model.aggregates.GptAnswer;
import com.alibaba.fastjson.JSON;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAI implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

//    @Value("${openai.API_URL}")
//    private String OPEN_API_URL;
//    @Value("${openai.API_KEY}")
//    private String OPEN_API_KEY;

    @Override
    public String doChatGpt(String OPEN_API_URL, String OPEN_API_KEY, String question) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        logger.info("apiUrl:{}， OPEN_API_KEY:{}", OPEN_API_URL, OPEN_API_KEY);

        try {
            HttpPost post = new HttpPost(OPEN_API_URL);
            post.addHeader("Content-Type", "application/json");
            post.addHeader("Authorization", "Bearer " + OPEN_API_KEY);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo-1106");

            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", "You are a helpful assistant.");

            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", question);

            requestBody.put("messages", new Object[]{systemMessage, userMessage});

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(requestBody);

            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(stringEntity);

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                if (response.getCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    GptAnswer gptAnswer = JSON.parseObject(responseBody, GptAnswer.class);
                    System.out.println("Response: " + responseBody);
                    return gptAnswer.getChoices().get(0).getMessage().getContent();
                } else {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    System.err.println("Request failed with status code: " + response.getCode());
                    System.err.println("Response body: " + responseBody);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
