package codenamex.smc.viz;//package com.example.DataStructureVisualizer;
//
//import javafx.fxml.FXML;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//
//public class BinaryTreeController {
//
//    @FXML
//    private TextField valueTextField;
//
//    @FXML
//    private Button insertButton;
//
//    @FXML
//    private Canvas canvas;
//
//    private BinaryTree binaryTree;
//
//    public void initialize() {
//        // Create a new binary tree.
//        binaryTree = new BinaryTree();
//
//        // Insert the root node.
//        binaryTree.insert(10);
//
//        // Insert a new node when the user clicks the button.
//        insertButton.setOnAction(event -> {
//            int value = Integer.parseInt(valueTextField.getText());
//            binaryTree.insert(value);
//
//            // Visualize the binary tree.
//            visualizeBinaryTree();
//        });
//    }
//
//    private void visualizeBinaryTree() {
//        // Get the graphics context of the canvas.
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        // Clear the canvas.
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//        // Draw the binary tree recursively.
//        drawBinaryTree(gc, binaryTree.getRoot(), canvas.getWidth() / 2, canvas.getHeight() / 2);
//    }
//
//    private void drawBinaryTree(GraphicsContext gc, BinaryTree.Node node, double x, double y) {
//        if (node == null) {
//            return;
//        }
//
//        // Draw the node.
//        gc.setFill(Color.BLACK);
//        gc.fillOval(x - 10, y - 10, 20, 20);
//
//        // Draw the node's value.
//        gc.setFill(Color.BLACK);
//        gc.setFont(new Font(12));
//        gc.fillText(String.valueOf(node.value), x - 5, y + 5);
//
//        // Draw the node's left and right children.
//        drawBinaryTree(gc, node.left, x - 50, y + 50);
//        drawBinaryTree(gc, node.right, x + 50, y + 50);
//    }
//}
