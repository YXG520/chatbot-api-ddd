package cn.bugstack.chatbot.api.domain.zsxq;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredCommentAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Comment;

import java.io.IOException;
import java.util.List;

public interface IZsxqApi {
    /**
     *
     * @param commentable_id 文档id
     * @param cookie 权限
     * @throws IOException IO异常
     */
    List<Comment> queryUnansweredComments(long commentable_id, String cookie) throws IOException;

    boolean setGptAnswer(long commentable_id, long parent_id, long to_user_id, String content, String cookie);

}
