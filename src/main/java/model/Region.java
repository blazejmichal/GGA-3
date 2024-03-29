package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Region {

  private Double minX;
  private Double maxX;
  private Double minY;
  private Double maxY;

  /**
   * Stworzenie rejonu z prawodilowy przypisaniem wartosci minimalnych/maksymlanych
   *
   * @param minX
   * @param maxX
   * @param minY
   * @param maxY
   */
  public Region(
      Double minX,
      Double maxX,
      Double minY,
      Double maxY
  ) {

    double temporary;
    if (minX > maxX) {
      temporary = minX;
      minX = maxX;
      maxX = temporary;
    }
    if (minY > maxY) {
      temporary = minY;
      minY = maxY;
      maxY = temporary;
    }
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
  }

  /**
   * Skopiowanie wartosci z danego rejonu do instacji
   *
   * @param region
   */
  public void of(Region region) {

    this.minX = region.getMinX();
    this.maxX = region.getMaxX();
    this.minY = region.getMinY();
    this.maxY = region.getMaxY();
  }

}
