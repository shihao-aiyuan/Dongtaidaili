import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMapMethodArgumentResolver;

/**
 * @author shihao
 * @create 2020-10-23 17:01
 */
public class TestApplicationContent {

    public static void main(String[] args) {
        ApplicationContext context =new ClassPathXmlApplicationContext("classpath:applicationContent.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)context.getAutowireCapableBeanFactory();

    }
}
