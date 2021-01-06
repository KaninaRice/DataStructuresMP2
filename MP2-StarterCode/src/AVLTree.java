public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    private boolean increase;

    @Override
    public boolean insert(E item) {
        //implement method 
        return false;
    }

    @Override
    public E delete(E target){
        //implement method
        return null;
    }

    public String preorder(){
        return "";
    }

    public String postorder(){
        return "";
    }

    public String inorder(){
        return "";
    }

    private class AVLNode<E> extends BinaryTree.BinaryNode {
        private static final int LEFT_HEAVY = -1;
        private static final int RIGHT_HEAVY = 1;
        private static final int BALANCED = 0;
        private int balance;

        /**
         * Constructor for AVLNode
         *
         * @param data
         *            data to be inserted into new AVLNode
         */
        public AVLNode(E data) {
            super(data);
            balance = BALANCED;
        }

        @Override
        public String toString() {
            return super.toString() + "(" + balance + ")";
        }

        @Override
        protected BinaryNode getLeftChild() {
            return super.getLeftChild();
        }

        @Override
        protected BinaryNode getRightChild() {
            return super.getRightChild();
        }
    }


}