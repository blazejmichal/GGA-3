package model;

import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Point {

  private UUID id = UUID.randomUUID();

  private double x;

  private double y;

  public Point(Double x, Double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {

    return Objects.hash(x, y);
  }

}
