package service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Point;
import model.Region;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionComparator {

  private Point topRightQueryPoint;
  private Point bottomLeftQueryPoint;
  private Point topRightNodePoint;
  private Point bottomLeftNodePoint;

  public RegionComparator(
      Region node,
      Region query
  ) {
    this.topRightQueryPoint = new Point(
        query.getMaxX(),
        query.getMaxY()
    );
    this.bottomLeftQueryPoint = new Point(
        query.getMinX(),
        query.getMinY()
    );
    this.topRightNodePoint = new Point(
        node.getMaxX(),
        node.getMaxY()
    );
    this.bottomLeftNodePoint = new Point(
        node.getMinX(),
        node.getMinY()
    );
  }

  /**
   * Sprawdza czy node znajduje sie w obszarze zapytania
   */
  public Boolean isNodeRegionContainedInQueryRegion() {

    boolean condition1 = this.topRightNodePoint.getX() <= this.topRightQueryPoint.getX();
    boolean condition2 = this.topRightNodePoint.getY() <= this.topRightQueryPoint.getY();
    boolean condition3 = this.bottomLeftNodePoint.getX() >= this.bottomLeftQueryPoint.getX();
    boolean condition4 = this.bottomLeftNodePoint.getY() >= this.bottomLeftQueryPoint.getY();
    boolean result = condition1 && condition2 && condition3 && condition4;

    return result;
  }

  /**
   * Sprawdza czy node przecina obszarz zapytania
   */
  public Boolean isNodeRegionIntersectionOfQueryRegion() {

    boolean condition1 = this.topRightQueryPoint.getX() < this.bottomLeftNodePoint.getX();
    boolean condition2 = this.bottomLeftQueryPoint.getX() > this.topRightNodePoint.getX();
    boolean condition3 = this.topRightQueryPoint.getY() < this.bottomLeftNodePoint.getY();
    boolean condition4 = this.bottomLeftQueryPoint.getY() > this.topRightNodePoint.getY();
    boolean result = condition1 || condition2 || condition3 || condition4 ? false : true;

    return result;
  }

}