import com.google.common.collect.Lists;
import model.Input;
import model.Point;
import model.Region;
import model.Task;
import service.InputBuilder;

public class Main {

  public static void main(String[] args) throws Exception {

    Task task0 = new Task(
        new Input(
            Lists.newArrayList(
                new Point(4d, 4d),
                new Point(2.2d, 10d),
                new Point(0d, 6d),
                new Point(2.1d, 5d),
                new Point(1d, 1.2d),
                new Point(2d, 2d),
                new Point(4.1d, 1.1d),
                new Point(10.1d, 2.5d),
                new Point(10d, 3.5d),
                new Point(5d, 5.1d)
            ),
            new Region(0.0, 10.0, 0.0, 10.0)
        ),
        new Region(2.0d, 4.0d, 2.0d, 4.0d)
    );

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
