package cn.bugstack.chatbot.api.application.job;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TaskType {
    String  value();
}
