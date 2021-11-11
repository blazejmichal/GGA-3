import model.Region;
import model.Task;
import service.InputBuilder;

public class Main {

  public static void main(String[] args) throws Exception {

    Task task10 = new Task(
        new InputBuilder(10).build(),
        new Region(
            1.0d,
            50.0d,
            1.0d,
            50.0d
        )
    );
    Task task1000 = new Task(
        new InputBuilder(1000).build(),
        new Region(
            1.0d,
            5000.0d,
            1.0d,
            5000.0d
        )
    );
    Task task100000 = new Task(
        new InputBuilder(100000).build(),
        new Region(
            1.0d,
            500000.0d,
            1.0d,
            500000.0d
        )
    );
    Task task10000000 = new Task(
        new InputBuilder(10000000).build(),
        new Region(
            1.0d,
            5000000.0d,
            1.0d,
            5000000.0d
        )
    );
  }

}
