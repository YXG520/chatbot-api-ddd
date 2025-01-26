package cn.bugstack.chatbot.api.domain.zsxq.model.res;

public class ReplyRes {
    public boolean succeeded;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public ReplyRes(boolean succeeded) {
        this.succeeded = succeeded;
    }
}
