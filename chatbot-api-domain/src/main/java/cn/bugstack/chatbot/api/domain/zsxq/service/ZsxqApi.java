package cn.bugstack.chatbot.api.domain.zsxq.service;

import cn.bugstack.chatbot.api.domain.zsxq.IZsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredCommentAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.req.ReplyReq;
import cn.bugstack.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Comment;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public List<Comment> queryUnansweredComments(long commentable_id, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://www.yuque.com/api/comments/floor?commentable_type=Doc&commentable_id=" + commentable_id + "&include_section=true&include_to_user=true&include_reactions=true";
        HttpGet get = new HttpGet(url);

        // 设置请求头
        get.setHeader("Content-Type", "application/json");
//        get.setHeader("Cookie", "receive-cookie-deprecation=1; lang=zh-cn; _uab_collina=171273651984528152675161; _yuque_session=jK7lMKFZjjeZOJbSP_HaXxtNj3rKtEszODgJcR3IfrJ7aAFj9B_FYCmBfS4dH-zt27_-1YPNlDqi6nlTAn8_BQ==; tfstk=fbOZsRNJnfhZCBJcYM1VYNTTZT1Oi_nS_IsfoEYc5Gjg15ZDLFTP5Rg9oi-2AMCffC963Pde4SNsXAL0gs1mV0GSNFUOMsmWJFS0slQlylcfmGYhgMDs10GSNFDGWsTnVqHMVCsAosbGnsfn8a7ViSbMmMXhraZGisxD-27Vo52GIt23-NGPGKpu5Z6iX1-DScgIp9skSGY6zBD5CRLdYJyeTdBNqjSUiSAF7eQfugPrhgvRfTsJxXNNgeb2YT9Z45-2oUpcLIri53YDXB5OaxgOUpvBFOJEjSSB1ZWli9z0ie5DItAVZbqFJpY6nCKauSb91Q6V29u0Mt1HNTvkbr3J-1bDDTdj25IMoUdJF_la2NJHz6SrnoQn-cAvQo2VIwQFV2u3FqNWyrPQSLeYHOnh8ggG9-eAIwQFV2uUH-B9KwSSS6C..; yuque_ctoken=hf1ufn4x60v1ih06obgr0mhxlukqaaf7; current_theme=default; aliyungf_tc=c3a9b4f26c035f52da729d7f8be1ea4b33dcf827a7e7f4ea063667bf7a938be1; acw_tc=ac11000117174716467932967e4d7d7c09a7c784630c3b6b251230e16b86cf");
        get.setHeader("Cookie", cookie);

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("请求成功" + responseBody);
                UnansweredCommentAggregates unansweredCommentAggregates = JSON.parseObject(responseBody, UnansweredCommentAggregates.class);
                List<Comment> comments = unansweredCommentAggregates.getRespData().getComments();
                List<Comment> commentsRes = new ArrayList<>();

                for (Comment comment : comments) {
                    if(comment.getSub_comments().isEmpty() || comment.getSub_comments().size() == 0){
                        commentsRes.add(comment);
                    }
                }
                return commentsRes;
            } else {
                System.out.println("Request failed: " + response.getStatusLine().getStatusCode());
                throw new RuntimeException(String.valueOf(response.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setGptAnswer(long commentable_id, long parent_id, long to_user_id, String content, String cookie) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://www.yuque.com/api/comments";
        HttpPost post = new HttpPost(url);

        // 设置请求头
        post.setHeader("Accept", "application/json");
        post.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Content-Type", "application/json");
//        post.setHeader("Cookie", "receive-cookie-deprecation=1; lang=zh-cn; _uab_collina=171273651984528152675161; _yuque_session=jK7lMKFZjjeZOJbSP_HaXxtNj3rKtEszODgJcR3IfrJ7aAFj9B_FYCmBfS4dH-zt27_-1YPNlDqi6nlTAn8_BQ==; tfstk=fbOZsRNJnfhZCBJcYM1VYNTTZT1Oi_nS_IsfoEYc5Gjg15ZDLFTP5Rg9oi-2AMCffC963Pde4SNsXAL0gs1mV0GSNFUOMsmWJFS0slQlylcfmGYhgMDs10GSNFDGWsTnVqHMVCsAosbGnsfn8a7ViSbMmMXhraZGisxD-27Vo52GIt23-NGPGKpu5Z6iX1-DScgIp9skSGY6zBD5CRLdYJyeTdBNqjSUiSAF7eQfugPrhgvRfTsJxXNNgeb2YT9Z45-2oUpcLIri53YDXB5OaxgOUpvBFOJEjSSB1ZWli9z0ie5DItAVZbqFJpY6nCKauSb91Q6V29u0Mt1HNTvkbr3J-1bDDTdj25IMoUdJF_la2NJHz6SrnoQn-cAvQo2VIwQFV2u3FqNWyrPQSLeYHOnh8ggG9-eAIwQFV2uUH-B9KwSSS6C..; yuque_ctoken=hf1ufn4x60v1ih06obgr0mhxlukqaaf7; current_theme=default; aliyungf_tc=c3a9b4f26c035f52da729d7f8be1ea4b33dcf827a7e7f4ea063667bf7a938be1; acw_tc=ac11000117174734484406498e489dfdf63bcfd65297b9ae8a84e208513fc5");
        post.setHeader("Cookie", cookie);
        post.setHeader("Host", "www.yuque.com");
        post.setHeader("Origin", "https://www.yuque.com");
        post.setHeader("Referer", "https://www.yuque.com/jingle-bsl7j/frw4an/rbulpq6m9akgg5gb");
        post.setHeader("Sec-Ch-Ua", "\"Microsoft Edge\";v=\"125\", \"Chromium\";v=\"125\", \"Not.A/Brand\";v=\"24\"");
        post.setHeader("Sec-Ch-Ua-Mobile", "?0");
        post.setHeader("Sec-Ch-Ua-Platform", "\"Windows\"");
        post.setHeader("Sec-Fetch-Dest", "empty");
        post.setHeader("Sec-Fetch-Mode", "cors");
        post.setHeader("Sec-Fetch-Site", "same-origin");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");
        post.setHeader("X-Csrf-Token", "hf1ufn4x60v1ih06obgr0mhxlukqaaf7");
        post.setHeader("X-Login", "jingle-bsl7j");
        post.setHeader("X-Requested-With", "XMLHttpRequest");

        // 设置请求体
//        String json = "{"
//                + "\"commentable_type\": \"Doc\","
//                + "\"commentable_id\":"+ commentable_id +","
//                + "\"parent_id\":" + parent_id + ","
//                + "\"to_user_id\":" + to_user_id + ","
//                + "\"body_asl\": \"<!doctype lake><meta name=\\\"doc-version\\\" content=\\\"1\\\" /><meta name=\\\"viewport\\\" content=\\\"adapt\\\" /><p data-lake-id=\\\"u9a91ed63\\\" id=\\\"u9a91ed63\\\"><span data-lake-id=\\\"u31bd1a73\\\" id=\\\"u31bd1a73\\\">"+content+"</span></p><!65573eee04fea576624b30756bbfa00fc166cabe86d4577fd4c6f0387c069fae>\","
//                + "\"body\": \"<div class=\\\"lake-content\\\" typography=\\\"traditional\\\"><p id=\\\"u9a91ed63\\\" class=\\\"ne-p\\\"><span class=\\\"ne-text\\\">"+content+"</span></p></div>\","
//                + "\"format\": \"lake\","
//                + "\"root_id\":" + commentable_id
//                + "}";
        ReplyReq replyReq = new ReplyReq(new ReqData("Doc", commentable_id, parent_id, to_user_id, content,content,"lake",parent_id));
        String json = JSONObject.toJSONString(replyReq.getReqData());
        logger.info("json:{}",json);
        try {
            post.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(responseBody);
                    return true;
                } else {
                    logger.info("res: " + EntityUtils.toString(response.getEntity(), "UTF-8"));
                    System.out.println("Request failed: " + response.getStatusLine().getStatusCode());
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
