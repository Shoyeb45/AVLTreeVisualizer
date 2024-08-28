# Java Application for AVL Tree Visualization

<b>This project provides an overview of AVL visualization and explains the mechanics of balancing within AVL trees.</b>
### What is AVL Tree?
AVL tree is a self-balancing binary search tree specifically designed to maintain a height difference of at most 1 between the left and right subtrees of every node. This strict balancing condition guarantees that the tree's operations such as search, insert, and delete have a time complexity of O(log n) while ensuring the tree remains balanced at all times.



## Code Structure 
- <b>Main.java</b> : It's the main file of application, it loads FXML file and set the scene and then show the stage. Entire application sits on this file.
- <b>Main.fxml</b> : It is used for basic UI element using scene-builder.
- <b>Controller.java</b> : This file contains implementation of all on-action methods required for basic operation. Like insert, search, delete...
- <b>BSTNode.java</b> : This file contains a node structure of binary search tree.
- <b>BinarySearchTree.java</b> : Contains all the methods and animation related to binary search tree. All the drawing and animations are executing from this this file.
