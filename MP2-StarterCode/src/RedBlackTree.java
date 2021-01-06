
public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>{
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

    private class RedBlackNode<E> extends BinaryTree.BinaryNode {
        private boolean isRed;


        public RedBlackNode(E data) {
            super(data);
            isRed = true;
        }

        @Override
        public String toString() {
            /**
            if(isRed){
                return "Red : " + super.toString();
            } else {
                return "Black : " + super.toString();
            }
             */
            String color = isRed ? "(red)" : "(black)";
            return super.toString() + color;
        }
    }


}
