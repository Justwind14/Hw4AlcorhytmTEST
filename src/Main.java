public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        tree.add(15);
        tree.add(9);
        tree.add(24);
        tree.add(3);
        tree.add(20);
        tree.add(28);
        tree.add(12);


        tree.printTree();

    }
}