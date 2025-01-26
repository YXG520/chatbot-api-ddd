package cn.bugstack.chatbot.api.domain.zsxq.model.vo;
public class Reply
{
    private int id;

    private int user_id;

    private User user;

    private int parent_id;

    private int root_id;

    private String format;

    private String body;

    private String body_asl;

    private String likes_count;

    private int mood;

    private String created_at;

    private String updated_at;

    private String deleted_at;

    private int status;

    private int to_user_id;

    private String type;

    private String selection_id;

    private String selection_type;

    private String source_type;

    private String source_id;

    private String region;

    private To_user to_user;

    private String _serializer;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }
    public int getUser_id(){
        return this.user_id;
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
    public void setParent_id(int parent_id){
        this.parent_id = parent_id;
    }
    public int getParent_id(){
        return this.parent_id;
    }
    public void setRoot_id(int root_id){
        this.root_id = root_id;
    }
    public int getRoot_id(){
        return this.root_id;
    }
    public void setFormat(String format){
        this.format = format;
    }
    public String getFormat(){
        return this.format;
    }
    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return this.body;
    }
    public void setBody_asl(String body_asl){
        this.body_asl = body_asl;
    }
    public String getBody_asl(){
        return this.body_asl;
    }
    public void setLikes_count(String likes_count){
        this.likes_count = likes_count;
    }
    public String getLikes_count(){
        return this.likes_count;
    }
    public void setMood(int mood){
        this.mood = mood;
    }
    public int getMood(){
        return this.mood;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
    public void setDeleted_at(String deleted_at){
        this.deleted_at = deleted_at;
    }
    public String getDeleted_at(){
        return this.deleted_at;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setTo_user_id(int to_user_id){
        this.to_user_id = to_user_id;
    }
    public int getTo_user_id(){
        return this.to_user_id;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setSelection_id(String selection_id){
        this.selection_id = selection_id;
    }
    public String getSelection_id(){
        return this.selection_id;
    }
    public void setSelection_type(String selection_type){
        this.selection_type = selection_type;
    }
    public String getSelection_type(){
        return this.selection_type;
    }
    public void setSource_type(String source_type){
        this.source_type = source_type;
    }
    public String getSource_type(){
        return this.source_type;
    }
    public void setSource_id(String source_id){
        this.source_id = source_id;
    }
    public String getSource_id(){
        return this.source_id;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
    public void setTo_user(To_user to_user){
        this.to_user = to_user;
    }
    public To_user getTo_user(){
        return this.to_user;
    }
    public void set_serializer(String _serializer){
        this._serializer = _serializer;
    }
    public String get_serializer(){
        return this._serializer;
    }
}