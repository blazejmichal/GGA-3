package model;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import model.Node.NODE_TYPE;

public class Task {

  public Region treeRegion = new Region(
      0.0,
      10.0,
      0.0,
      10.0
  );
  public Region queryRegion;
  private List<Point> input = Lists.newArrayList();
  private Node tree;
  private List<Point> reportedLeaves = Lists.newLinkedList();
  private List<Node> reportedSubtrees = Lists.newLinkedList();

  public Task(List<Point> input) {
    this.input = input;
    this.run();
  }

  // gdzie new Region(x1, x2, y1, y2) && x1 <= x2 && y1 <= y2
  public Task(
      List<Point> input,
      Region queryRegion
  ) {
    this.input = input;
    this.queryRegion = queryRegion;
    this.run();
  }

  public void run() {

    try {
    this.validate();
    this.tree = this.buildKdTree(
        this.input,
        0
    );
    this.tree.setRegion(this.treeRegion);
    this.defineRegion(this.tree);
    this.checkAffiliationToQueryRegion(this.tree);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }

  public void validate() throws Exception {

    this.validateAmountOfPoints();
    this.validateQueryRegion();
    for (Point leftPoint : this.input) {
      for (Point rightPoint : this.input) {
        if (!leftPoint.equals(rightPoint)) {
          this.validateDifferenceOfX(
              leftPoint,
              rightPoint
          );
          this.validateDifferenceOfY(
              leftPoint,
              rightPoint
          );
        }
      }
    }
  }

  public void validateQueryRegion() throws Exception {

    if (this.queryRegion != null) {
      if (this.queryRegion.getMinX() > this.queryRegion.getMaxX()
          || this.queryRegion.getMinY() > this.queryRegion.getMaxY()) {
        throw new Exception("Nieprawidłowy obszar zapytania");
      }
    }
  }

  public void validateAmountOfPoints() throws Exception {

    if (this.input.size() == 0) {
      throw new Exception("Nie podano punktów.");
    }
  }

  public void validateDifferenceOfX(
      Point left,
      Point right
  ) throws Exception {

    if (left.getX() == right.getX()) {
      throw new Exception("Współrzędne x są takie same w punktach");
    }
  }

  public void validateDifferenceOfY(
      Point left,
      Point right
  ) throws Exception {

    if (left.getY() == right.getY()) {
      throw new Exception("Współrzędne y są takie same w punktach");
    }
  }

  public Node buildKdTree(
      List<Point> points,
      Integer d
  ) {

    if (points.size() == 1) {
      return new NodeBuilder().build(
          0d,
          NODE_TYPE.LEAF,
          points.get(0),
          null,
          null
      );
    } else if (points.size() == 0) {
      return null;
    }
    Integer axis = d % 2;
    NODE_TYPE type = axis == 0 ? NODE_TYPE.VERTICAL_LINE : NODE_TYPE.HORIZONTAL_LINE;
    double median = this.calculateMedian(
        axis,
        points
    );
    List<Point> leftPoints = Lists.newLinkedList();
    List<Point> rightPoints = Lists.newLinkedList();
    for (Point temporary : points) {
      double value = axis == 0 ? temporary.getX() : temporary.getY();
      if (value <= median) {
        leftPoints.add(temporary);
      } else {
        rightPoints.add(temporary);
      }
    }
    Node leftSubtree = this.buildKdTree(
        leftPoints,
        d + 1
    );
    Node rightSubtree = this.buildKdTree(
        rightPoints,
        d + 1
    );
    Node newNode = new NodeBuilder().build(
        median,
        type,
        null,
        leftSubtree,
        rightSubtree
    );

    return newNode;
  }

  public Double calculateMedian(
      Integer axis,
      List<Point> points
  ) {

    Double median = axis == 0 ? this.calculateMedianByX(points) : this.calculateMedianByY(points);

    return median;
  }

  public Double calculateMedianByX(
      List<Point> points
  ) {

    this.sortByX(points);
    Integer half = points.size() / 2;
    Double median = (points.size() % 2 == 0) ?
        ((points.get(half).getX() + points.get(half - 1).getX()) / 2)
        : points.get(half).getX();

    return median;
  }

  public Double calculateMedianByY(
      List<Point> points
  ) {

    this.sortByY(points);
    Integer half = points.size() / 2;
    Double median = (points.size() % 2 == 0) ?
        ((points.get(half).getY() + points.get(half - 1).getY()) / 2)
        : points.get(half).getY();

    return median;
  }

  public void sortByX(List<Point> points) {

    points.sort((p1, p2) -> {
      if ((p1.getX() < p2.getX()) || (p1.getX() == p2.getX() && p1.getY() < p2.getY())) {
        return -1;
      } else if (p1.getX() > p2.getX()) {
        return 1;
      } else {
        return 0;
      }
    });
  }

  public void sortByY(List<Point> points) {

    Collections.sort(points, (p1, p2) -> {
      if ((p1.getY() < p2.getY()) || (p1.getY() == p2.getY() && p1.getX() < p2.getX())) {
        return -1;
      } else if (p1.getY() > p2.getY()) {
        return 1;
      } else {
        return 0;
      }
    });
  }

  public void defineRegion(Node node) {

    if (!node.isLeaf()) {
      if (node.getParent() != null) {
        node.setRegion(
            node.getParent().getRegion()
        );
        Region nodeRegion = node.getRegion();
        Double location = node.getParent().getLocation();
        if (nodeRegion != null && location != null) {
          if (NODE_TYPE.HORIZONTAL_LINE.equals(node.getType())) {
            if (node.isLeft()) {
              nodeRegion.setMaxX(location);
            } else {
              nodeRegion.setMinX(location);
            }
          } else {
            if (node.isLeft()) {
              nodeRegion.setMaxY(location);
            } else {
              nodeRegion.setMinY(location);
            }
          }
          node.setRegion(nodeRegion);
        }
      }
      if (node.getLeft() != null) {
        this.defineRegion(
            node.getLeft()
        );
      }
      if (node.getRight() != null) {
        this.defineRegion(
            node.getRight()
        );
      }
    }
  }

  public void checkAffiliationToQueryRegion(Node node) {

    if (node.isLeaf()) {
      this.checkLeafAffiliationToQueryRegion(node);
    } else {
      if (node.getLeft().isLeaf()) {
        this.checkLeafAffiliationToQueryRegion(node.getLeft());
      } else {
        this.checkSubtreeAffiliationToQueryRegion(node.getLeft());
      }
      if (node.getRight().isLeaf()) {
        this.checkLeafAffiliationToQueryRegion(node.getRight());
      } else {
        this.checkSubtreeAffiliationToQueryRegion(node.getRight());
      }
    }
  }

  public void checkLeafAffiliationToQueryRegion(Node node) {

    Boolean contained = this.containedInRegion(
        this.queryRegion,
        node.getPoint()
    );
    if (contained) {
      this.reportedLeaves.add(
          node.getPoint()
      );
    }
  }

  public void checkSubtreeAffiliationToQueryRegion(Node node) {

    if (
        node != null
            && this.queryRegion != null
            && new RegionComparator(
            node.getRegion(),
            this.queryRegion).isNodeRegionContainedInQueryRegion()
    ) {
      this.reportedSubtrees.add(node);
    } else if (
        node != null
            && this.queryRegion != null
            && new RegionComparator(
            node.getRegion(),
            this.queryRegion).isNodeRegionIntersectionOfQueryRegion()
    ) {
      this.checkAffiliationToQueryRegion(node);
    } else {
      if (node.isLeaf()) {
        this.checkLeafAffiliationToQueryRegion(node);
      }
    }
  }

  public Boolean containedInRegion(
      Region region,
      Point point
  ) {

    Boolean pointInRegion = point.getX() >= region.getMinX() && point.getX() <= region.getMaxX()
        && point.getY() >= region.getMinY() && point.getY() <= region.getMaxY();

    return pointInRegion;
  }

}
