package cn.bugstack.chatbot.api.application.job;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Comment;
import cn.bugstack.chatbot.api.domain.zsxq.service.ZsxqApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TaskType("yuque-chatbot")
public class ChatbotTask implements Runnable, Task{

    // test test=====
    Logger logger = LoggerFactory.getLogger(ChatbotTask.class);

    private String cookie;

    private String groupName;

    private long commentable_id;

    private ZsxqApi zsxqApi;

    private IOpenAI openAI;

    private String openAiUrl;

    private String openAiKey;


    public ChatbotTask(String openAiUrl, String groupName, String cookie, long commentable_id, String openAiKey, ZsxqApi zsxqApi, IOpenAI openAI) {
        this.openAiUrl = openAiUrl;
        this.groupName = groupName;
        this.cookie = cookie;
        this.commentable_id = commentable_id;
        this.openAiKey = openAiKey;
        this.zsxqApi = zsxqApi;
        this.openAI = openAI;
    }


    @Override
    public void run() {
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
        List<Comment> comments = null;
        try {
            comments = zsxqApi.queryUnansweredComments(commentable_id, cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            String gptReply = null;
            try {
                gptReply = openAI.doChatGpt(openAiUrl, openAiKey, matchedContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("gpt 答案: {}", gptReply);
            // 问题回复
            boolean res = zsxqApi.setGptAnswer(commentable_id, (long) comment.getId(), comment.getUser_id(),gptReply,cookie);
            logger.info("评论编号: {}, 评论内容: {}, 回复内容: {}, 回复状态: {}", comment.getId(), matchedContent, gptReply, res);
        }else {
            logger.info("没有提取出纯文本！");
        }

    }
}
