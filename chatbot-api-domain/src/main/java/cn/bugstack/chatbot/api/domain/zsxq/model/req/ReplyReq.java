package cn.bugstack.chatbot.api.domain.zsxq.model.req;

public class ReplyReq {
    public ReqData reqData;

    public ReplyReq(ReqData reqData) {
        this.reqData = reqData;
    }

    public ReqData getReqData() {
        return reqData;
    }

    public void setReqData(ReqData reqData) {
        this.reqData = reqData;
    }
}
