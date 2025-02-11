package cn.bugstack.chatbot.api.application.ext;

import cn.bugstack.chatbot.api.application.job.ChatbotTask;
import cn.bugstack.chatbot.api.application.job.Task;
import cn.bugstack.chatbot.api.common.PropertyUtil;
import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.IZsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.service.ZsxqApi;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 小傅哥，微信：fustack
 * @description 任务注册服务，支持多组任务配置
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Configuration
@EnableScheduling
public class TaskRegistrarAutoConfig implements EnvironmentAware, SchedulingConfigurer {

    private Logger logger = LoggerFactory.getLogger(TaskRegistrarAutoConfig.class);

    /**
     * 任务配置组,testtest
     */
    private Map<String, Map<String, Object>> taskGroupMap = new HashMap<>();

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "chatbot-api.";
        String launchListStr = environment.getProperty(prefix + "launchList");
        if (StringUtils.isEmpty(launchListStr)) return;
        for (String groupKey : launchListStr.split(",")) {
            Map<String, Object> taskGroupProps = PropertyUtil.handle(environment, prefix + groupKey, Map.class);
            taskGroupMap.put(groupKey, taskGroupProps);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Set<String> taskGroups = taskGroupMap.keySet();
        for (String groupKey : taskGroups) {
            Map<String, Object> taskGroup = taskGroupMap.get(groupKey);
            String groupName = taskGroup.get("groupName").toString();
            int commentable_id = (int) taskGroup.get("groupId");
            String cookie = taskGroup.get("cookie").toString();
            String openAiKey = taskGroup.get("openAiKey").toString();
            String cronExpressionBase64 = taskGroup.get("cronExpression").toString();
            String openAiUrl =  taskGroup.get("openAiUrl").toString();
            String taskType = taskGroup.get("taskType").toString();
            String taskInstancePath = taskGroup.get("taskInstancePath").toString();
            logger.info("创建任务 groupName：{} groupId：{} cronExpression：{}", groupName, commentable_id, cronExpressionBase64);
            // 添加任务
            if (taskInstancePath != null) {
                try {
                    // 使用反射根据类的全限定名创建实例
                    Class<?> clazz = Class.forName(taskInstancePath);
                    Task task = (Task) clazz.getDeclaredConstructor().newInstance();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            taskRegistrar.addCronTask(new ChatbotTask(openAiUrl, groupName, cookie, commentable_id, openAiKey, (ZsxqApi) zsxqApi, openAI), cronExpressionBase64);
        }
    }

}
