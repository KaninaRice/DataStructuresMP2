

/* AVLTree Implementation huhuhuhuhu
 * reference: Koffman, E.B. & Wolfgang P.A.T. (2016). Data Structures: Abstractions and Designs
 * */
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
					 rebalanceLeft(localRoot);
				 }
			}
    	}
    	else if(item.compareTo(extracted(localRoot)) > 0) {//greater than, add to right side
    		increase=true;
    		//AVLNode<E> right = (AVLNode<E>)localRoot.getRightChild();
    		localRoot.right= inserts((AVLNode<E>)localRoot.right, item); 
    		if (increase) {
				 incrementBalance(localRoot);
				 if (localRoot.balance < AVLNode.LEFT_HEAVY) {
					 increase = false;
					 //rebalanceRight(localRoot);
				 }
			}
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
		 node.balance++;
		 if (node.balance == AVLNode.BALANCED) {
		 /** If now balanced, overall height has not increased. */
		 increase = false;
		 }
	}

	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
		 // Obtain reference to left child.
		 AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
		 // See whether leftâ€�right heavy.
		 if (leftChild.balance > AVLNode.BALANCED) {
		 // Obtain reference to leftâ€�right child.
		 AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
		 /** Adjust the balances to be their new values after
		 the rotations are performed.
		 */
		 if (leftRightChild.balance < AVLNode.BALANCED) {
		 leftChild.balance = AVLNode.BALANCED;
		 leftRightChild.balance = AVLNode.BALANCED;
		 localRoot.balance = AVLNode.RIGHT_HEAVY;
		 } else {
		 leftChild.balance = AVLNode.LEFT_HEAVY;
		 leftRightChild.balance = AVLNode.BALANCED;
		 localRoot.balance = AVLNode.BALANCED;
		 }
		 // Perform left rotation.
		 localRoot.left = rotateLeft(leftChild);
		 } else { //Leftâ€�Left case
		 /** In this case the leftChild (the new root) and the root
		 (new right child) will both be balanced after the rotation.
		 */
		 leftChild.balance = AVLNode.BALANCED;
		 localRoot.balance = AVLNode.BALANCED;
		 }
		 // Now rotate the local root right.
		 return (AVLNode<E>) rotateRight(localRoot);
		}
	
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain a reference to the right child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See whether left-right heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain a reference to the right-left child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            		
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform right rotation.
            localRoot.right = rotateRight(rightChild);
        } else { // Right-right case
            /** In this case the rightChild (the new root) and the root (new left child) will both be balanced
             *  after the rotation. */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }
    @Override
    public E delete(E target){
        if (exists(target)) {
        	deleteNode(root, target);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	private AVLNode<E> deleteNode(AVLNode<E> root, E target){
		if(root == null)
			return root;
    	
		if(target.compareTo(extracted(root)) < 0) {
			root.left = deleteNode((AVLTree<E>.AVLNode<E>) root.left, target);
		}
		else if (target.compareTo(extracted(root)) > 0) {
			root.right = deleteNode((AVLTree<E>.AVLNode<E>) root.left, target);
		}
		
		else {
			if((root.left == null) || (root.right == null)) {
				AVLNode<E> temp;
				if(root.left != null)
					temp = (AVLTree<E>.AVLNode<E>) root.left;
				else
					temp = (AVLTree<E>.AVLNode<E>) root.right;
				
				if(temp == null) {
					temp = root;
					root = null;
				}
				else
					root = temp;
				
				temp = null;
			}
			else {
				AVLNode<E> temp = minValueNode((AVLTree<E>.AVLNode<E>) root.right);
				
				root.data = temp.data;
				
				root.right = deleteNode((AVLTree<E>.AVLNode<E>) root.right, extracted(temp));
				
			}
		}
		if(root == null)
			return root;
		
		root.height = Math.max(getHeight((AVLTree<E>.AVLNode<E>) root.left), getHeight((AVLTree<E>.AVLNode<E>) root.right)) + 1;
		int balance = getBalance(root);
		
		if(balance > 1 && getBalance((AVLTree<E>.AVLNode<E>) root.left) >= 0)
			return (AVLTree<E>.AVLNode<E>) rotateRight(root);
		
		if(balance > 1 && getBalance((AVLTree<E>.AVLNode<E>) root.left) < 0) {
			root.left = rotateLeft(root.left);
			return (AVLTree<E>.AVLNode<E>) rotateRight(root);
		}
		
		if(balance < -1 && getBalance((AVLTree<E>.AVLNode<E>) root.right) <= 0)
			return (AVLTree<E>.AVLNode<E>) rotateLeft(root);
		
		if(balance < -1 && getBalance((AVLTree<E>.AVLNode<E>) root.right) > 0 ) {
			root.right = rotateRight(root.right);
			return (AVLTree<E>.AVLNode<E>) rotateLeft(root);
		}
		return root;
    }
    
    private int getBalance(AVLNode<E> node) {
    	if(node == null)
    		return 0;
    	return treeHeight((AVLTree<E>.AVLNode<E>) node.left) - treeHeight((AVLTree<E>.AVLNode<E>) node.right);
    }
    
    private int getHeight(AVLNode<E> node) {
    	if(node == null)
    		return 0;
    	else{
    		int left = getHeight((AVLNode<E>) root.getLeftChild());
    		int right = getHeight((AVLNode<E>) root.getRightChild());
    		
    		return (Math.max(left, right) + 1);
    	}
    }
    
    private AVLNode<E> minValueNode(AVLNode<E> node){
    	AVLNode<E> current  = node;
    	
    	while(current.left != null)
    		current = (AVLTree<E>.AVLNode<E>) current.left;
    	return current;
    }

    public String preorder(){
        StringBuilder preorderTraversal = new StringBuilder();
        preorderSupport(root, preorderTraversal);
        return preorderTraversal.toString();
    }

    private void preorderSupport(BinaryNode<E> root, StringBuilder preorder){
        if (root != null) {
            preorder.append(root + " ");
            preorderSupport(root.getLeftChild(), preorder);
            preorderSupport(root.getRightChild(), preorder);
        }
    }

    public String postorder() {
        StringBuilder postorderTraversal = new StringBuilder();
        postorderSupport(root, postorderTraversal);
        return postorderTraversal.toString();
        //return "";
    } // end postorder

    public void postorderSupport(BinaryNode<E> root, StringBuilder postorder) {
        if(root != null) {
            postorderSupport(root.getLeftChild(), postorder);
            postorderSupport(root.getRightChild(), postorder);
            postorder.append(root + " ");
        }

    }

    public String inorder() {
        StringBuilder inorderTraversal = new StringBuilder();
        inorderSupport(root, inorderTraversal);
        return inorderTraversal.toString();
    } // end inorder

    public void inorderSupport(BinaryNode<E> root, StringBuilder inorder) {
        if(root != null) {
            inorderSupport(root.getLeftChild(), inorder);
            inorder.append(root + " ");
            inorderSupport(root.getRightChild(), inorder);
        }
    }

    private class AVLNode<E> extends BinaryTree.BinaryNode {
        private static final int LEFT_HEAVY = -1;
        private static final int RIGHT_HEAVY = 1;
        private static final int BALANCED = 0;
        private int balance;
        protected int height;
        
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
