package cn.bugstack.chatbot.api.domain.ai;

import java.io.IOException;

public interface IOpenAI {
    //test rebase2
    String doChatGpt(String OPEN_API_URL, String OPEN_API_KEY, String question) throws IOException;
}
