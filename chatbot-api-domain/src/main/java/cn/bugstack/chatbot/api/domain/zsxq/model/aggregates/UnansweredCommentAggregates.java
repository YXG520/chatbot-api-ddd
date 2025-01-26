package cn.bugstack.chatbot.api.domain.zsxq.model.aggregates;

import cn.bugstack.chatbot.api.domain.zsxq.model.res.RespData;
import com.alibaba.fastjson.annotation.JSONField;

public class UnansweredCommentAggregates {
    @JSONField(alternateNames = "data")
    private RespData respData;

    public RespData getRespData() {
        return respData;
    }

    public void setRespData(RespData respData) {
        this.respData = respData;
    }
}
