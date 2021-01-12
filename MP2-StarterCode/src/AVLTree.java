public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    private boolean increase;
    private AVLNode <E> root;

    @Override
    public boolean insert(E item) {
    	increase=false;
        root= inserts(root, item);
        return addReturn;
    }
    
    public AVLNode<E> inserts(AVLNode <E> localRoot, E item){
    	if (localRoot==null) { //empty tree, make new one
    		addReturn=true;
    		increase=true;
    		return new AVLNode<E>(item);
    	}
    	else if (item.compareTo(extracted(localRoot)) == 0) {//duplicate; return false
    		addReturn=false;
    		increase=false;
    		return null;
    	}
    	else if(item.compareTo(extracted(localRoot)) < 0) {//less than, add to left side
    		increase=true;
			localRoot.left= inserts((AVLNode<E>)localRoot.left, item); //recursive adding to the left subtree
			if (increase) {
				 decrementBalance(localRoot);
				 if (localRoot.balance < AVLNode.LEFT_HEAVY) {
					 increase = false;
					 rotateLeft(localRoot);
				 }
			}
    	}
    	else if(item.compareTo(extracted(localRoot)) > 0) {//greater than, add to right side
    		increase=true;
    		//AVLNode<E> right = (AVLNode<E>)localRoot.getRightChild();
    		localRoot.right= inserts((AVLNode<E>)localRoot.right, item); 
    		}
		return localRoot;
    }

	private E extracted(AVLNode<E> localRoot) {
		return (E)localRoot.data;
	}
	
	private void decrementBalance(AVLNode<E> node) {
		 // Decrement the balance.
		 node.balance--;
		 if (node.balance == AVLNode.BALANCED) {
		 /** If now balanced, overall height has not increased. */
		 increase = false;
		 }
	}
	private void incrementBalance(AVLNode<E> node) {
		 // Increment the balance.
		 node.balance--;
		 if (node.balance == AVLNode.BALANCED) {
		 /** If now balanced, overall height has not increased. */
		 increase = false;
		 }
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
            return (AVLNode) super.getLeftChild();
        }

        @Override
        protected AVLNode getRightChild() {
            return (AVLNode) super.getRightChild();
        }
    }


}