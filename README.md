# <p align=center><b>AVL Tree Visualization in JavaFX</b></p>

<p align=center>
  <img src="./public/image/logo.png" width="200px" height="200px">
</p>

<br>
This JavaFX application demonstrates the visualization of an <b>AVL Tree</b> (Adelson-Velsky and Landis Tree), a type of self-balancing binary search tree. The application provides functionalities to insert, search, delete, and perform different tree traversals with visual animations. 
<br>
<br>

![Dashboard of Visualizer](./public/image/image1.png)
<p align=center><i>Dashboard of AVL Tree Visualizer</i></p>

The AVL Tree maintains a balance factor at each node to ensure the height remains logarithmic, providing efficient operations.
Since the AVL tree is a self-balancing binary tree, so whenever the tree will get unbalanced the balancing algorithm will balance the tree.

The balancing of the tree is taken care by <b>different rotations of AVL Tree</b>. There are two types of rotations of an AVL tree, these are:

1. <b>Left Rotation</b>

    Consider the situation of an tree nodes:

    ![LeftRotation](./public/image/lr.png)

    To balance this we perform following operations:
    <br>
    - `45` becomes the new root.
    - `13` takes ownership of `45`'s left child as its right child, or in this case, null.
    - `45` takes ownership of `13` as its left child. 

    Now tree will look like:

    ![LRBalanced](./public/image/lrBalanced.png)

    <br>
2. <b>Right Rotation</b>

    Consider the situation of an tree nodes:

    ![RightRotation](./public/image/rr.png)

    To balance this we perform following operations:
    <br>
    - `20` becomes the new root.
    - `30` takes ownership of `20`'s right child, as its left child. In this case, that value is null.
    - `20` takes ownership of `30`, as it's right child. 

    Now tree will look like:

    ![RRBalanced](./public/image/rrBalanced.png)


<br>
These two rotaions will take care the following four unbalancing cases of AVL Tree.:

1. Left-Left Unbalancing Case 

    This case looks like:

    ![LeftLeft](./public/image/ll.png)

    To balance this we'll perform single time right-rotation on `10`.

2. Left-Right Unbalancing Case 

    This case looks like:

    ![LeftRight](./public/image/lrun.png)

    To balance this, we'll perform:

    - First, left-rotation on `10`(`left-child of current node`).
    - Then, right-rotation on `20`. 
3. Right-Right Unbalancing Case 

    This case looks like:

    ![RightRotation](./public/image/rr.png)

    To balance this we'll perform single time left-rotation on `30`.
4. Right-Left Unbalancing Case 

    This case looks like:

    ![RightLeft](./public/image/rlun.png)

    To balance this, we'll perform:

    - First, right-rotation on `30`(`right-child of current node`).
    - Then, left-rotation on `10`. 


<br>
So just by performing these rotations we can balance our binary search tree.

## <b>How to Use</b>

1. <b>Insert Nodes</b>: Click the insert button to add nodes to the tree. The tree will automatically balance itself.
2. <b>Search Nodes</b>: Use the search function to locate a specific value in the tree.
3. <b>Delete Nodes</b>: Click the delete button to remove a node and see the balancing in action.
4. <b>Perform Traversals</b>: Choose from different traversal options to see the order of nodes visually.
5. <b>Clear Tree</b>: Reset the tree using the clear function.

## <b>Features</b>

### <b>Text Box</b>
  The node of tree can contain integer value. The integer value can be entered in the given text box, if the value entered will not be valid then it will not accept it. 

  So the user should input the value ranging from `-2,147,483,648` to `2,147,483,647`.

  But for better visualisation try to insert lower integer values.
  <p align=center>
    <img src="./public/image/textBox.png">
  </p>
  <p align=center><i>Text Box for giving values</i></p>

## 1. Operations
- <b>Insertion</b>:

   Add nodes to the AVL Tree, ensuring it remains balanced. Visualize the balancing process with animations.

   Node value can inserted by using `insert` button on left side below the text-box. 
    
    <p align="center">
      <img src="./public/image/insertBtn.png" width=350> 
    </p>
    <p align=center><i>Button for inserting the value</i></p>

    <p align="center">
      <img src="./public/image/insertDemon.png"> 
    </p>
    <p align=center><i>Demonstration of using insert button</i></p>


- <b>Search</b>: 

  Find a node in the tree with visual feedback indicating whether the node exists.

  We can use `search` button to search the value in AVL Tree. The searching will happen by comparing the value to be searched to the value of current node, and according to the value, the search will go to the left or right subtree of an AVL tree.


    <p align="center">
      <img src="./public/image/searchBtn.png" width=350> 
    </p>
    <p align=center><i>Button for inserting the value</i></p>

    <p align="center">
      <img src="./public/image/searchDemon.gif"> 
    </p>
    <p align=center><i>Demonstration of searching value 80 in AVL Tree</i></p>
- <b>Deletion</b>: 

  Remove nodes while maintaining the balance of the tree.

  We can use `delete` button to delete the value in AVL Tree. 

  <p align="center">
      <img src="./public/image/deleteBtn.png" width=350> 
  </p>
    <p align=center><i>Button for deleting the value</i></p>

  There are four cases of deleting a node:

  1. Node to be deleted is <b>leaf node</b>

      <p align="center">
          <img src="./public/image/deleteLeaf.gif" width=350> 
      </p>
      <p align=center>
        <i>Node to be deleted - 80: which is leaf node</i>
      </p>
    
  2. Node to be deleted has one child node - only at left
      <p align="center">
          <img src="./public/image/deleteLeft.gif" width=350> 
      </p>
      <p align=center>
        <i>Node to be deleted - 120: which has only left node</i>
      </p>

  3. Node to be deleted has one child node - only at right

      <p align="center">
          <img src="./public/image/deleteRight.gif" width=350> 
      </p>
      <p align=center>
        <i>Node to be deleted - 23: which has only right node</i>
      </p>
  

  4. Node to be deleted has both of children

      <p align="center">
          <img src="./public/image/deleteBoth.gif"> 
      </p>
      <p align=center>
        <i>Node to be deleted - 30: which has both the child</i>
      </p>

  
## 2. <b>Traversals</b>

  - <b>Inorder</b>: 
  
    Traverse the tree in an ascending order.

    <p align=center>
      <img src="./public/image/inorder.gif" width=550>
    </p>

  - <b>Preorder</b>: 
  
    Traverse the tree in a root-left-right order.

    <p align=center>
      <img src="./public/image/preorder.gif" width=550>
    </p>

  - <b>Postorder</b>: 
  
    Traverse the tree in a left-right-root order.

    <p align=center>
      <img src="./public/image/postorder.gif" width=550>
    </p>


  - <b>Level Order</b>: 
  
    Traverse the tree level by level.

    <p align=center>
      <img src="./public/image/levelOrder.gif" width=550>
    </p>

  - <b>DFS</b>: 
  
    Perform a depth-first traversal.

    <p align=center>
      <img src="./public/image/dfs.gif" width=550>
    </p>

  - <b>Visual Feedback</b>: Animations for insertion, deletion, and traversal steps to provide an engaging learning experience.

- <b>Clear</b>

  Reset the AVL Tree to an empty state.

  Clear the screen by pressing on `clear Tree` button.
  <p align=center>
    <img src="./public/image/clearBtn.png">
    <p align=center><i>Clear button</i></p>
  </p>
  <br>
  <br>

  <p align=center>
    <img src="./public/image/clear.gif" width=550>
    <p align=center>Clearing tree</p>
  </p>

## Code Structure 

- [<b>Main.java</b>](./src/application/Main.java) 

  It's the main file of application, it loads FXML file and set the scene and then show the stage. Entire application runs from this file.

- [<b>AVLTree.java</b>](./src/application/AVLTree.java) 

   Contains all the methods and animation related code to AVL tree. All the drawing and animations are executing from this file.

- [<b>AVLNode.java</b>](./src/application/AVLNode.java) 

   This file contains a node structure of an AVL tree. It consists of a methods to set the positions of circle and line. 

- [<b>Main.fxml</b>](./src/application/Main.fxml) 

   It is used for basic UI element using scene-builder. AnchorPane, Label, TextBox,..etc elements are used from scene builder.

- [<b>Controller.java</b>](./src/application/Controller.java) 

   This file contains implementation of all on-action methods required for basic operation. Like insert, search, delete...

> <b>See each file for more detailed documentation, and in-depth explanations of how each class functions</b>

<!-- 
## <b>Class Details</b>

### <b>AVLTree Class</b>

The `AVLTree` class handles the logic and animation of the AVL tree. Below is a breakdown of the key components and methods:

### <b>Attributes</b>

- <b>START_Y</b>: Initial Y-coordinate of the AVL tree nodes.
- <b>DELTA_X</b>: Horizontal spacing between nodes.
- <b>DELTA_Y</b>: Vertical spacing between levels.
- <b>K</b>: Distance from the center used for calculating the start of edges.
- <b>DIST</b>: Distance from the edge point starting to the perpendicular bisector.

### <b>Constructor</b>

- <b>AVLTree(Pane newPane)</b>: Initializes the AVL Tree with an empty root and sets up the pane for displaying the nodes.

### <b>Core Methods</b>





### <b>Utility Methods</b>

- <b>contains(AVLNode root, int val)</b>: Checks if a node is present in the tree.

- <b>insertUtil(AVLNode root, int val, double centerX, double centerY, boolean isLeft)</b>: A helper function that recursively inserts a node into the correct position in the tree.

- <b>updateNode(AVLNode node)</b>: Updates the height and balance factor of a node.

- <b>balanceTree(AVLNode node)</b>: Balances the AVL tree using rotations.

- <b>leftLeftCase(AVLNode node)</b>: Handles left-left imbalance by performing a right rotation.

- <b>leftRightCase(AVLNode node)</b>: Handles left-right imbalance with a left rotation followed by a right rotation.

- <b>rightRightCase(AVLNode node)</b>: Handles right-right imbalance by performing a left rotation.

- <b>rightLeftCase(AVLNode node)</b>: Handles right-left imbalance with a right rotation followed by a left rotation.

- <b>rightRotate(AVLNode node)</b>: Performs a right rotation on the subtree.

- <b>leftRotate(AVLNode node)</b>: Performs a left rotation on the subtree.

- <b>nodeAppearance(AVLNode newNode)</b>: Animates the appearance of a newly inserted node.

- <b>lineGrowing(Line line, double endX, double endY)</b>: Animates the growth of a line between nodes.

- <b>resizeTree()</b>: Re-adjusts the positions of nodes visually after insertion or deletion to ensure the tree looks balanced.

- <b>setNewPositions(AVLNode node, double xPosition, double yPosition, byte side, double lineStartX, double lineStartY, Line line)</b>: Updates the positions of nodes recursively to maintain the visual structure.

- <b>resizeWidths(AVLNode node)</b>: Calculates and updates the left and right widths of nodes for proper positioning. -->
## Built With

- Programming Langauge: `Java 17.0.0`
- Library Used: `JavaFX 22.0.2`

## <b>Requirements</b>

- <b>Java Development Kit (JDK) 11 or higher</b>
- <b>JavaFX SDK</b> (included in most modern Java IDEs)

## <b>How to Run</b>

1. Clone the repository.
2. Import the project into your Java IDE (like IntelliJ IDEA, Eclipse with e(fx)clipse).
3. Ensure JavaFX is properly configured in your IDE.
4. Run the main class, which initializes the JavaFX application and displays the AVL Tree visualization.
