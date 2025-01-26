package cn.bugstack.chatbot.api.domain.zsxq.model.vo;
public class User
{
    private int id;

    private String login;

    private String name;

    private String avatar_url;

    private int followers_count;

    private int following_count;

    private boolean isPaid;

    private int status;

    private String _serializer;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return this.login;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setFollowers_count(int followers_count){
        this.followers_count = followers_count;
    }
    public int getFollowers_count(){
        return this.followers_count;
    }
    public void setFollowing_count(int following_count){
        this.following_count = following_count;
    }
    public int getFollowing_count(){
        return this.following_count;
    }
    public void setIsPaid(boolean isPaid){
        this.isPaid = isPaid;
    }
    public boolean getIsPaid(){
        return this.isPaid;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void set_serializer(String _serializer){
        this._serializer = _serializer;
    }
    public String get_serializer(){
        return this._serializer;
    }
}
