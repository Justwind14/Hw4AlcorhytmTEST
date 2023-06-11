public class RBTree extends Node{
    Node root;


    public void add (int value) {
        if (root == null) {
            root = new Node();
            addNode(root, value);
            return;
        } else if (root.value > value && root.left == null) {
            Node node = new Node();
            root.left = node;
            node.value = value;
            node.clr = ColorNode.RED;
            node.parent = root;
            return;
        } else if (root.value < value && root.right == null) {
            Node node = new Node();
            root.right = node;
            node.value = value;
            node.clr = ColorNode.RED;
            node.parent = root;
            return;
        }
        if (root.value > value) {
            addNode(root.left, value);
        } else addNode(root.right, value);
    }
    public void addNode (Node node, int value){
        if(node.parent == null){
            node.value = value;
            node.clr = ColorNode.BLACK;
        }
        else addNode2(node, value);
    }



    public Node getUncle(Node node){
        if (node.parent.parent == null) {
            return null;
        }
        else if (node.parent == node.parent.parent.left) {
            return node.parent.parent.right;
        }
        else return node.parent.parent.left;
    }

    private void addNode2(Node node, int value){
//        if (node.parent.clr == ColorNode.BLACK) {
        if (node.clr == ColorNode.BLACK) {
            if(node.value > value){ // но этот иф\елз - неточно
                Node temp = new Node();
                temp.parent = node;
                node.left = temp;
                temp.value = value;
                temp.clr = ColorNode.RED;
            }
            else {
                Node temp = new Node();
                temp.parent = node;
                node.right = temp;
                temp.value = value;
                temp.clr = ColorNode.RED;
            }

        }
        else addNode3(node, value);
    }

    private void addNode3(Node node, int value){
        Node uncle = getUncle(node);
        if(uncle != null && uncle.clr == ColorNode.RED){  // && (node.parent.clr == ColorNode.RED) Второе условие проверяется в insert_case2, то есть родитель уже является красным.
            node.parent.clr = ColorNode.BLACK;
            uncle.clr = ColorNode.BLACK;
            node.parent.parent.clr = ColorNode.RED;
            addNode(node.parent.parent, node.parent.parent.value);
            if(node.value > value){ // но этот иф\елз - неточно
                Node temp = new Node();
                temp.parent = node;
                node.left = temp;
                temp.value = value;
                temp.clr = ColorNode.RED;
            }
            else {
                Node temp = new Node();
                temp.parent = node;
                node.right = temp;
                temp.value = value;
                temp.clr = ColorNode.RED;
            }
        }
        else addNode4(node, value);
    }
    private void addNode4(Node node, int value){
        if ((node == node.parent.right && node.parent == node.parent.parent.left) || (node == node.parent.left && node.parent == node.parent.parent.right)){
            if(node.value > value){ // но этот иф\елз - неточно
                Node temp = new Node();
                temp.parent = node;
                node.left = temp;
                temp.value = value;
                temp.clr = ColorNode.RED;
            }
            else {
                Node temp = new Node();
                temp.parent = node;
                node.right = temp;
                temp.value = value;
                temp.clr = ColorNode.RED;
            }
            if (node == node.parent.right && node.parent == node.parent.parent.left) {
                rotateLeft(node.parent);
            }
            else  rotateRight(node.parent);
        }
        else addNode5(node,value);
    }

    private void addNode5(Node node, int value){
        node.parent.clr = ColorNode.BLACK;
        node.parent.parent.clr = ColorNode.RED;
        if(node.value > value){ // но этот иф\елз - неточно
            Node temp = new Node();
            temp.parent = node;
            node.left = temp;
            temp.value = value;
            temp.clr = ColorNode.RED;
        }
        else {
            Node temp = new Node();
            temp.parent = node;
            node.right = temp;
            temp.value = value;
            temp.clr = ColorNode.RED;
        }
        if (node == node.parent.left && node.parent == node.parent.parent.left){
            rotateRight(node.parent.parent);
        }
        else rotateLeft(node.parent.parent);
    }







    private static void rotateLeft(Node node){
        Node temp = node.right;
        temp.parent = node.parent;
        if(node.parent != null){
            if(node.parent.left == node){
                node.parent.left = temp;
            }
            else node.parent.right = temp;
        }
        node.right = temp.left;
        if(temp.left != null){
            temp.left.parent = node;
        }
        node.parent = temp;
        temp.left = node;
    }

    private static void rotateRight(Node node)
    {
        Node temp = node.left;

        temp.parent = node.parent;
        if (node.parent != null) {
            if (node.parent.left == node)
                node.parent.left = temp;
            else
                node.parent.right = temp;
        }
        node.left = temp.right;
        if (temp.right != null)
            temp.right.parent = node;
        node.parent = temp;
        temp.right = node;
    }


    void printTreeHelper(Node root, int space)
    {
        int i;
        if(root != null)
        {
            space = space + 10;
            printTreeHelper(root.right, space);
            System.out.printf("\n");
            for ( i = 10; i < space; i++)
            {
                System.out.printf(" ");
            }
            if (root.clr == ColorNode.RED) {
                System.out.printf(ConsoleColors.RED + root.value + ConsoleColors.RESET);
            }
            else System.out.printf("%d", root.value);
            System.out.printf("\n");
            printTreeHelper(root.left, space);
        }
    }
    // function to print the tree.
    public void printTree()
    {
        printTreeHelper(root, 0);
    }

}
