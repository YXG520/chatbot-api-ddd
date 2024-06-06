package cn.bugstack.chatbot.api.test;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredCommentAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Comment;
import cn.bugstack.chatbot.api.domain.zsxq.service.ZsxqApi;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootTest.class);
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${chatbot-api.commentable_id}")
    private long commentable_id;

    @Resource
    private ZsxqApi zsxqApi;

    @Test
    public void testApi() throws IOException {
        List<Comment> commentList = zsxqApi.queryUnansweredComments(commentable_id, cookie);
        for (Comment c : commentList) {
            logger.info("output:{}", c.getBody());
        }

    }

}
