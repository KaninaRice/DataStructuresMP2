public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(21);
        bst.insert(10);
        bst.insert(40);
        bst.insert(42);
        bst.insert(31);
        bst.insert(22);
        bst.insert(35);

        System.out.println("Searching for 31: "+bst.search(31)); //31
        System.out.println("The minimum key is: "+bst.minimum()); //10
        System.out.println("The maximum key is: "+bst.maximum()); //42
        System.out.println("The successor of 10 is: "+ bst.successor(10)); //21
        System.out.println("The predecessor of 10 is: "+bst.predecessor(10)); //null
        System.out.println("Successor of 22 is " + bst.successor(22)); //31
        System.out.println("Predecessor of 22 is " + bst.predecessor(22)); //21
        System.out.println("The preorder traversal of bst: "+bst.preorder()); // 21 10 40 31 22 35 42
        System.out.println("The inorder traversal of bst: "+bst.inorder()); // 10 21 22 31 35 40 42
        System.out.println("The postorder traversal of bst: "+bst.postorder()); // 10 22 35 31 42 40 21
        System.out.println("The level order traversal of bst: "+bst.levelorder()); //21 10 40 31 42 22 35

        System.out.println("Deleting 31: " + bst.delete(31)); //31
        System.out.println("Deleting 10: " + bst.delete(10)); //10
        System.out.println("Deleting 35: " + bst.delete(35)); //35
        System.out.println("Deleting 8: " + bst.delete(8)); //null

        System.out.println("Searching for 31: " + bst.search(31)); //null
        System.out.println("Searching for 10: " + bst.search(10)); //null
        
        RedBlackTree<Integer> rb = new RedBlackTree<Integer>();
        rb.insert(7);
        rb.insert(5);
        rb.insert(3);
        rb.insert(2);
        rb.insert(4);
        rb.insert(9);
        rb.insert(1);
        rb.delete(3);
        System.out.println("Preorder: " + rb.preorder());
        //5(black) 2(red) 1(black) 4(black) 7(black) 9(red)
        System.out.println("Inorder: " + rb.inorder());
        //1(black) 2(red) 4(black) 5(black) 7(black) 9(red) 
        System.out.println("Postorder: " + rb.postorder());
        //1(black) 4(black) 2(red) 9(red) 7(black) 5(black) kkk

        RedBlackTree<String> rb2 = new RedBlackTree<String>();
        rb2.insert("Sirius");
        rb2.insert("Canopus");
        rb2.insert("Alpha Centauri");
        rb2.insert("Acherner");
        rb2.insert("Arcturus");
        rb2.insert("Betelgeuse");
        rb2.insert("Capella");
        rb2.insert("Rigel");
        rb2.insert("Procyon");
        rb2.insert("Vega");
        rb2.delete("Canopus");

        System.out.println("Preorder: " + rb2.preorder());
        System.out.println("Inorder: " + rb2.inorder());
        System.out.println("Postorder: " + rb2.postorder());


    }
}
