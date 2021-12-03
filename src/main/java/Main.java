import com.google.common.collect.Lists;
import model.Input;
import model.Point;
import model.Region;
import model.Task;
import service.InputBuilder;

public class Main {

  public static void main(String[] args) throws Exception {

    Task taskExample1InnerRegion = new Task(
        new Input(
            Lists.newArrayList(
                new Point(1d, 5d),
                new Point(2d, 4d),
                new Point(3d, 3d),
                new Point(4d, 2d),
                new Point(5d, 1d)
            ),
            new Region(1d, 5d, 1d, 5d)
        ),
        new Region(1.5d, 3.5d, 1.5d, 6d)
    );

    Task taskExample1OuterRegion = new Task(
        new Input(
            Lists.newArrayList(
                new Point(1d, 5d),
                new Point(2d, 4d),
                new Point(3d, 3d),
                new Point(4d, 2d),
                new Point(5d, 1d)
            ),
            new Region(1d, 5d, 1d, 5d)
        ),
        new Region(9d, 10d, 9d, 10d)
    );

    Task taskExample2InnerRegion = new Task(
        new Input(
            Lists.newArrayList(
                new Point(1d, 4d),
                new Point(2d, 4d),
                new Point(3d, 4d),
                new Point(4d, 4d),
                new Point(1d, 3d),
                new Point(2d, 3d),
                new Point(3d, 3d),
                new Point(4d, 3d),
                new Point(1d, 2d),
                new Point(2d, 2d),
                new Point(3d, 2d),
                new Point(4d, 2d),
                new Point(1d, 1d),
                new Point(2d, 1d),
                new Point(3d, 1d),
                new Point(4d, 1d)
            ),
            new Region(1d, 4d, 1d, 4d)
        ),
        new Region(1.5d, 3.5d, 1.5d, 3.5d)
    );

    Task taskExample2OuterRegion = new Task(
        new Input(
            Lists.newArrayList(
                new Point(1d, 4d),
                new Point(2d, 4d),
                new Point(3d, 4d),
                new Point(4d, 4d),
                new Point(1d, 3d),
                new Point(2d, 3d),
                new Point(3d, 3d),
                new Point(4d, 3d),
                new Point(1d, 2d),
                new Point(2d, 2d),
                new Point(3d, 2d),
                new Point(4d, 2d),
                new Point(1d, 1d),
                new Point(2d, 1d),
                new Point(3d, 1d),
                new Point(4d, 1d)
            ),
            new Region(1d, 4d, 1d, 4d)
        ),
        new Region(-10d, -9d, 1d, 10d)
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
