package di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Scope("prototype")
@Component
public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }
}
