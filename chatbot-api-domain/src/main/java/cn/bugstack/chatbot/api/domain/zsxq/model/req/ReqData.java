package cn.bugstack.chatbot.api.domain.zsxq.model.req;

/**
 * @author 小傅哥，微信：fustack
 * @description 请求对象
 * @github https://github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ReqData {
    private String commentable_type;
    private long commentable_id;
    private long parent_id;
    private long to_user_id;

    private String body_asl;
    private String body;
    private String format;
    private long root_id;


    public ReqData(String commentable_type, long commentable_id, long parent_id, long to_user_id, String body_asl, String body, String format, long root_id) {
        this.commentable_type = commentable_type;
        this.commentable_id = commentable_id;
        this.parent_id = parent_id;
        this.to_user_id = to_user_id;
        body_asl = "<!doctype lake><meta name=\\\"doc-version\\\" content=\\\"1\\\" /><meta name=\\\"viewport\\\" content=\\\"adapt\\\" /><p data-lake-id=\\\"u9a91ed63\\\" id=\\\"u9a91ed63\\\"><span data-lake-id=\\\"u31bd1a73\\\" id=\\\"u31bd1a73\\\">"+body_asl+"</span></p><!65573eee04fea576624b30756bbfa00fc166cabe86d4577fd4c6f0387c069fae>";
        this.body_asl = body_asl;
        body = "<div class=\\\\\\\"lake-content\\\\\\\" typography=\\\\\\\"traditional\\\\\\\"><p id=\\\\\\\"u9a91ed63\\\\\\\" class=\\\\\\\"ne-p\\\\\\\"><span class=\\\\\\\"ne-text\\\\\\\">"+body+"</span></p></div>";
        this.body = body;
        this.format = format;
        this.root_id = root_id;
    }
    public String getCommentable_type() {
        return commentable_type;
    }

    public void setCommentable_type(String commentable_type) {
        this.commentable_type = commentable_type;
    }

    public long getCommentable_id() {
        return commentable_id;
    }

    public void setCommentable_id(long commentable_id) {
        this.commentable_id = commentable_id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(long to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getBody_asl() {
        return body_asl;
    }

    public void setBody_asl(String body_asl) {
        body_asl = "<!doctype lake><meta name=\\\"doc-version\\\" content=\\\"1\\\" /><meta name=\\\"viewport\\\" content=\\\"adapt\\\" /><p data-lake-id=\\\"u9a91ed63\\\" id=\\\"u9a91ed63\\\"><span data-lake-id=\\\"u31bd1a73\\\" id=\\\"u31bd1a73\\\">"+body_asl+"</span></p><!65573eee04fea576624b30756bbfa00fc166cabe86d4577fd4c6f0387c069fae>";
        this.body_asl = body_asl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        body = "<div class=\\\\\\\"lake-content\\\\\\\" typography=\\\\\\\"traditional\\\\\\\"><p id=\\\\\\\"u9a91ed63\\\\\\\" class=\\\\\\\"ne-p\\\\\\\"><span class=\\\\\\\"ne-text\\\\\\\">"+body+"</span></p></div>";
        this.body = body;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getRoot_id() {
        return root_id;
    }

    public void setRoot_id(long root_id) {
        this.root_id = root_id;
    }

}