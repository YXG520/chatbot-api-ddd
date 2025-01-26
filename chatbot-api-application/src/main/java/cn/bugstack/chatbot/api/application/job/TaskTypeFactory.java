package cn.bugstack.chatbot.api.application.job;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.reflections.Reflections;

public class TaskTypeFactory {

    private static final Map<String, Task> tasks = new ConcurrentHashMap<>();
    static {
        Reflections reflections = new Reflections("cn\\bugstack\\chatbot\\api\\application\\job");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(TaskType.class);
        for (Class<?> clazz : annotatedClasses) {
            TaskType annotation = clazz.getAnnotation(TaskType.class);
            try {
                Task task = (Task) clazz.getDeclaredConstructor().newInstance();
                tasks.put(annotation.value(), task);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
