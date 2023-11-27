package codenamex.smc.viz;

public class BinaryTree {
    public class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Node root;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node currNode = root;
        while (true) {
            if (value < currNode.value) {
                if (currNode.left == null) {
                    currNode.left = newNode;
                    break;
                } else {
                    currNode = currNode.left;
                }
            } else {
                if (currNode.right == null) {
                    currNode.right = newNode;
                    break;
                } else {
                    currNode = currNode.right;
                }
            }
        }
    }

    public boolean contains(int value) {
        Node currNode = root;
        while (currNode != null) {
            if (value < currNode.value) {
                currNode = currNode.left;
            } else if (value > currNode.value) {
                currNode = currNode.right;
            } else {
                return true;
            }
        }

        return false;
    }

    public void print() {
        print(root);
    }

    public void print(Node node) {
        if (node == null) {
            return;
        }

        print(node.left);
        System.out.println(node.value);
        print(node.right);
    }
}
