package di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(Store.class);
//        context.register(ConsoleInput.class);
//        context.register(StartUI.class);
        context.scan("di");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
        StartUI uiAnother = context.getBean(StartUI.class);
        uiAnother.print();
    }
}
