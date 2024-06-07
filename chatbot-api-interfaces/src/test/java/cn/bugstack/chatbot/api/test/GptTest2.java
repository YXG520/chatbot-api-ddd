package cn.bugstack.chatbot.api.test;
import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.service.ZsxqApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest
public class GptTest2 {

    private final Logger logger = LoggerFactory.getLogger(GptTest2.class);
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${chatbot-api.commentable_id}")
    private long commentable_id;

    @Resource
    private ZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

//    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
//    private static final String API_KEY = ""; // 替换为您的API密钥
//    @Test
//    public void test1() {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        try {
//            HttpPost post = new HttpPost(API_URL);
//            post.addHeader("Content-Type", "application/json");
//            post.addHeader("Authorization", "Bearer " + API_KEY);
//
//            Map<String, Object> requestBody = new HashMap<>();
//            requestBody.put("model", "gpt-3.5-turbo-1106");
//
//            Map<String, String> systemMessage = new HashMap<>();
//            systemMessage.put("role", "system");
//            systemMessage.put("content", "You are a helpful assistant.");
//
//            Map<String, String> userMessage = new HashMap<>();
//            userMessage.put("role", "user");
//            userMessage.put("content", "Hello!");
//
//            requestBody.put("messages", new Object[]{systemMessage, userMessage});
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String json = objectMapper.writeValueAsString(requestBody);
//
//            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
//            post.setEntity(stringEntity);
//
//            try (CloseableHttpResponse response = httpClient.execute(post)) {
//                if (response.getCode() == 200) {
//                    String responseBody = EntityUtils.toString(response.getEntity());
//                    System.out.println("Response: " + responseBody);
//                } else {
//                    String responseBody = EntityUtils.toString(response.getEntity());
//                    System.err.println("Request failed with status code: " + response.getCode());
//                    System.err.println("Response body: " + responseBody);
//                }
//            }
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
