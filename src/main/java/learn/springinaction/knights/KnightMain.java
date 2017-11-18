package learn.springinaction.knights;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("knights-config.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();

        Knight knight1 = (Knight)context.getBean("knight");
        System.out.println(knight == knight1);
    }
}
