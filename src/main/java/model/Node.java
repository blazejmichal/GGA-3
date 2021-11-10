package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Node {

  private Double location;
  private NODE_TYPE type;
  private Point point;
  private Node parent;
  private Node left;
  private Node right;
  private Region region;

  public Node(
      Double location,
      NODE_TYPE type,
      Point point,
      Node left,
      Node right
  ) {
    this.location = location;
    this.type = type;
    this.point = point;
    this.left = left;
    this.right = right;
  }

  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }

  public void setRegion(Region region) {
    this.region = new Region();
    this.region.of(region);
  }

  public boolean isLeft() {
    return this == this.parent.left;
  }

  public boolean isRight() {
    return this == this.parent.right;
  }

  @Override
  public String toString() {
    return "Node{" + "point=" + this.point + '}';
  }

  public enum NODE_TYPE {
    LEAF,
    VERTICAL_LINE,
    HORIZONTAL_LINE;
  }

}
