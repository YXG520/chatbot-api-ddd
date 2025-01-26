package cn.bugstack.chatbot.api.domain.zsxq.model.vo;
public class Meta
{
    private int rootTotal;

    private int total;

    private int commentCount;

    private int lastId;

    private boolean hasMore;

    public void setRootTotal(int rootTotal){
        this.rootTotal = rootTotal;
    }
    public int getRootTotal(){
        return this.rootTotal;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setCommentCount(int commentCount){
        this.commentCount = commentCount;
    }
    public int getCommentCount(){
        return this.commentCount;
    }
    public void setLastId(int lastId){
        this.lastId = lastId;
    }
    public int getLastId(){
        return this.lastId;
    }
    public void setHasMore(boolean hasMore){
        this.hasMore = hasMore;
    }
    public boolean getHasMore(){
        return this.hasMore;
    }
}