package model;

import model.Node.NODE_TYPE;

public class NodeBuilder {

  public Node build(
      Double location,
      NODE_TYPE type,
      Point point,
      Node left,
      Node right
  ) {

    Node node = new Node(
        location,
        type,
        point,
        left,
        right
    );
    if (left != null) {
      left.setParent(node);
      node.setLeft(left);
    }
    if (right != null) {
      right.setParent(node);
      node.setRight(right);
    }
    return node;
  }

}
