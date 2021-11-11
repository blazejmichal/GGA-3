package service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Input;
import model.Point;
import model.Region;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputBuilder {

  private Integer amount;

  /**
   * Tworzy liste z losowymi wartosciami oraz rejon w obrebie ktorego znajduja sie te wartosci
   */
  public Input build() {

    List<Point> points = Lists.newLinkedList();
    List<Double> xPoints = this.buildListOfRandomDouble();
    List<Double> yPoints = this.buildListOfRandomDouble();
    Double minX = xPoints.get(0);
    Double maxX = xPoints.get(0);
    Double minY = yPoints.get(0);
    Double maxY = yPoints.get(0);
    for (int i = 0; i < this.amount; i++) {
      Point point = new Point(
          xPoints.get(i),
          yPoints.get(i)
      );
      points.add(point);
      if (point.getX() < minX) {
        minX = point.getX();
      }
      if (point.getX() > maxX) {
        maxX = point.getX();
      }
      if (point.getY() < minY) {
        minY = point.getY();
      }
      if (point.getY() > maxY) {
        maxY = point.getY();
      }
    }
    Region region = new Region(
        minX,
        maxX,
        minY,
        maxY
    );
    Input input = new Input(
        points,
        region
    );

    return input;
  }

  /**
   * Tworzy liste z losowymi wartosciami
   */
  public List<Double> buildListOfRandomDouble() {

    Set<Double> doubles = Sets.newLinkedHashSet();
    double randomDouble;
    int randomInt;
    Random random = new Random();
    while (doubles.size() != this.amount) {
      randomInt = random.nextInt(this.amount * 10);
      randomInt = randomInt < 0 ? randomInt * -1 : randomInt;
      randomDouble = randomInt;
      doubles.add(randomDouble);
    }
    List<Double> result = doubles.stream().toList();

    return result;
  }

}
