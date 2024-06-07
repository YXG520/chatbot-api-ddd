package cn.bugstack.chatbot.api.application.job;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredCommentAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Comment;
import cn.bugstack.chatbot.api.domain.zsxq.service.ZsxqApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EnableScheduling
@Configuration
public class ChatbotSchedule {
    Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${chatbot-api.commentable_id}")
    private long commentable_id;

    @Resource
    private ZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Scheduled(cron = "0 0/1 * * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
    public void run() throws IOException {
        if(new Random().nextBoolean()) {
            logger.info("随机打烊中..");
            return;
        }
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour>22||hour<7) {
            logger.info("打烊时间不工作，AI 下班了!");
            return;
        }
        List<Comment> comments = zsxqApi.queryUnansweredComments(commentable_id, cookie);

        if (comments.isEmpty() || comments.size() == 0) {
            logger.info("没有未回答的问题!");
            return;
        }

        Comment comment =comments.get(0);

        String htmlString = comment.getBody();

        // 正则表达式匹配 <span class="ne-text"> 标签中的内容
        String pattern = "<span.*?>(.*?)<\\/span>";

        // 编译正则表达式
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(htmlString);

        // 查找匹配的问题
        if (matcher.find()) {
            String matchedContent = matcher.group(1);
            logger.info("匹配到的评论文本: " + matchedContent);
            // ai回答
            String gptReply = openAI.doChatGpt(matchedContent);
            logger.info("gpt 答案: {}", gptReply);
            // 问题回复
            boolean res = zsxqApi.setGptAnswer(commentable_id, (long) comment.getId(), comment.getUser_id(),gptReply,cookie);
            logger.info("评论编号: {}, 评论内容: {}, 回复内容: {}, 回复状态: {}", comment.getId(), matchedContent, gptReply, res);
        }else {
            logger.info("没有提取出纯文本！");
        }

    }
}
