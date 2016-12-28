package p02_quadTree;

import java.util.ArrayList;

public class QuadTree {
    private Node root;

    //--------------------------------------------------------
    public QuadTree(int x1, int y1, int x2, int y2) {
        this.setRoot(new Node(x1, y1, x2, y2));
    }

    public Node getRoot() {
        return root;
    }

    private void setRoot(Node root) {
        this.root = root;
    }
    //--------------------------------------------------------

    public boolean insertElement(Node element) {
        return this.root.addElement(element, this.root);
    }

    public ArrayList<Node> getQuadrant(int x1, int y1, int x2, int y2) {    // TODO: optimize
        Rectangle currentArea = new Rectangle(x1, y1, x2, y2);

        ArrayList<Node> nodesInQuadrant = new ArrayList<>();
        addNodesOfTheArea(this.root, nodesInQuadrant, currentArea);

        return nodesInQuadrant;
    }

    private void addNodesOfTheArea(Node node, ArrayList<Node> nodes, Rectangle currentArea) {
        if (currentArea.isInside(node.getBoundaries()) || node.getBoundaries().isInside(currentArea)) {
            addElementTo(nodes, node.getElements());
        } else {
            return;
        }

        if (node.getChildren() != null) {
            for (Node child : node.getChildren()) {
                addNodesOfTheArea(child, nodes, currentArea);
            }
        }

        return;
    }

    private void addElementTo(ArrayList<Node> to, ArrayList<Node> elementsToBeAdded) {
        for (Node element : elementsToBeAdded) {
            to.add(element);
        }
    }
}