import reader.Command;
import reader.ReaderDAO;
import reader.ReaderService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//TODO niceToHave: addBook() api
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        DisplayController controller = new DisplayController(new ReaderService(new ReaderDAO()));
        Queue<Command> commands = new LinkedList<>();
        commands.add(controller.bookListMenu());

        while (!commands.isEmpty()) {
            Command curr = commands.poll();
            Command next = curr.execute();
            if (next != null) {
                commands.add(next);
            }
        }

        System.out.println("Thanks for reading!");

    }


}
