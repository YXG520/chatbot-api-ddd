package cn.bugstack.chatbot.api.domain.zsxq.model.res;

import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Comment;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Meta;

import java.util.List;

public class RespData {
    private Meta meta;

    private List<Comment> comments;

    public void setMeta(Meta meta){
        this.meta = meta;
    }
    public Meta getMeta(){
        return this.meta;
    }
    public void setComments(List<Comment> comments){
        this.comments = comments;
    }
    public List<Comment> getComments(){
        return this.comments;
    }
}
