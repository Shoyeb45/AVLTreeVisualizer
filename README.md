# <b>AVL Tree Visualization in JavaFX</b>

This JavaFX application demonstrates the visualization of an <b>AVL Tree</b> (Adelson-Velsky and Landis Tree), a type of self-balancing binary search tree. The application provides functionalities to insert, search, delete, and perform different tree traversals with visual animations. The AVL Tree maintains a balance factor at each node to ensure the height remains logarithmic, providing efficient operations.

## <b>Features</b>

- <b>Insertion</b>: Add nodes to the AVL Tree, ensuring it remains balanced. Visualize the balancing process with animations.
- <b>Deletion</b>: Remove nodes while maintaining the balance of the tree.
- <b>Search</b>: Find a node in the tree with visual feedback indicating whether the node exists.
- <b>Traversals</b>:
  - <b>Inorder</b>: Traverse the tree in an ascending order.
  - <b>Preorder</b>: Traverse the tree in a root-left-right order.
  - <b>Postorder</b>: Traverse the tree in a left-right-root order.
  - <b>Level Order</b>: Traverse the tree level by level.
  - <b>DFS</b>: Perform a depth-first traversal.
- <b>Clear</b>: Reset the AVL Tree to an empty state.
- <b>Visual Feedback</b>: Animations for insertion, deletion, and traversal steps to provide an engaging learning experience.

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

### **Deletion**

To delete a node:

- **Find the Node**: Locate the node to be deleted.
- **Remove the Node**: Handle nodes with no children, one child, or two children.
- **Balance the Tree**: Ensure the tree remains balanced.

**Diagrams:**

1. **Delete a Node with No Children (Leaf Node)**

Before Deletion: 30 /
20 40 / 10

Delete 10: 30 /
20 40

sql
Copy code

2. **Delete a Node with One Child**

Before Deletion: 30 /
20 40
25

Delete 20: 30 /
25 40

markdown
Copy code

3. **Delete a Node with Two Children**

Before Deletion: 30 /
20 40 / \
10 25 50

Delete 20: 30 /
25 40 /
10 50

markdown
Copy code

### **Rotations**

1. **Right Rotation (Single Rotation)**

Before Rotation: 30 / 20

Right Rotate around 30:

markdown
Copy code
 20
   \
    30
markdown
Copy code

2. **Left Rotation (Single Rotation)**

Before Rotation: 30
40

Left Rotate around 30:

Copy code
40
/ 30

scss
Copy code

3. **Left-Right Rotation (Double Rotation)**

Before Rotation: 30 /
40 / 20

Left Rotate on 40:

Copy code
 30
/  
20
40

Right Rotate on 30:

Copy code
30
/
20 40

scss
Copy code

4. **Right-Left Rotation (Double Rotation)**

Before Rotation: 30
20
40

Right Rotate on 20:

30
40 / 20

Left Rotate on 30:

Copy code
30
/
20 40

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

- <b>resizeWidths(AVLNode node)</b>: Calculates and updates the left and right widths of nodes for proper positioning.

## <b>How to Use</b>

1. <b>Insert Nodes</b>: Click the insert button to add nodes to the tree. The tree will automatically balance itself.
2. <b>Search Nodes</b>: Use the search function to locate a specific value in the tree.
3. <b>Delete Nodes</b>: Click the delete button to remove a node and see the balancing in action.
4. <b>Perform Traversals</b>: Choose from different traversal options to see the order of nodes visually.
5. <b>Clear Tree</b>: Reset the tree using the clear function.

## <b>Requirements</b>

- <b>Java Development Kit (JDK) 11 or higher</b>
- <b>JavaFX SDK</b> (included in most modern Java IDEs)

## <b>How to Run</b>

1. Clone the repository.
2. Import the project into your Java IDE (like IntelliJ IDEA, Eclipse with e(fx)clipse).
3. Ensure JavaFX is properly configured in your IDE.
4. Run the main class, which initializes the JavaFX application and displays the AVL Tree visualization.
