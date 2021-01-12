


public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    protected boolean addReturn;
    protected E deleteReturn;


    public boolean insert(E item) {
        root = insertSupport(root, item);
        return addReturn;
    }

    private BinaryNode<E> insertSupport(BinaryNode<E> localRoot, E item){
        if (localRoot == null){
            addReturn = true;
            return new BinaryNode<E>(item);
        } else if(item.compareTo(localRoot.data) == 0) {
            addReturn = false;
        } else if(item.compareTo(localRoot.data) < 0) {
            localRoot.left = insertSupport(localRoot.left, item);
        } else {
            localRoot.right = insertSupport(localRoot.right, item);
        }
        return localRoot;
    }

    public boolean exists(E target) {
        return search(target) != null;
    }

    public E search(E target) {
        return searchSupport(root, target);
    }

    private E searchSupport(BinaryNode<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        //
        int comparisonResults = target.compareTo(localRoot.data);
        if (comparisonResults == 0)
            return localRoot.data;
        else if (comparisonResults < 0)
            return searchSupport(localRoot.left, target);
        else
            return searchSupport(localRoot.right, target);

    }
    public BinaryNode<E> searchNode(E target) {
        return searchNodeSupport(root, target);
    }

    private BinaryNode<E> searchNodeSupport(BinaryNode<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        //
        int comparisonResults = target.compareTo(localRoot.data);
        if (comparisonResults == 0)
            return localRoot;
        else if (comparisonResults < 0)
            return searchNodeSupport(localRoot.left, target);
        else
            return searchNodeSupport(localRoot.right, target);

    }

    public E delete(E target) {
        root = deleteSupport(root, target);
        return deleteReturn;

    }

    private BinaryNode<E> deleteSupport(BinaryNode<E> localRoot, E target){
        if(localRoot == null){
            deleteReturn = null;
        } else {
            int comparisonResults = target.compareTo(localRoot.data);
            if (comparisonResults == 0) {
                deleteReturn = localRoot.data;
                if (localRoot.left == null && localRoot.right == null) {
                    return null;
                } else if (localRoot.left == null){
                    return localRoot.right;
                } else if (localRoot.right == null){
                    return localRoot.left;
                } else {
                    if (localRoot.left.right == null) {
                        localRoot.left.right = localRoot.right;
                        return localRoot.left;
                    }

                    localRoot.data = rightMostBinaryNodeDataDelete(localRoot, localRoot.left);

                }
            }
            else if(comparisonResults < 0){
                localRoot.left = deleteSupport(localRoot.left, target);
            } else {
                localRoot.right = deleteSupport(localRoot.right, target);
            }
        }
        return localRoot;
    }

    protected E rightMostBinaryNodeDataDelete(BinaryNode<E> root, BinaryNode<E> parentRoot) {
        if (root.right == null){
            root.right = root.left;
            return root.data;
        } else {
            return rightMostBinaryNodeDataDelete(root.right, root);
        }
    }

    public boolean remove(E target){
        return (delete(target) != null);
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
    /**
     * Returns a postorder representation of the binary search tree
     *
     * @return postorder string of the binary search tree
     */
    //Complete this method
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

    public String levelorder(){
        StringBuilder levelorderTraversal = new StringBuilder();
        String levelorder;
        levelorder = levelorderSupport(root, levelorderTraversal);
        return levelorder;
    }

    public String levelorderSupport(BinaryNode<E> root, StringBuilder levelorder){
        for(int i = 1; i <= treeHeight(root); i++) {
        	appendLevel(root, i, levelorder);
        }
        
        return levelorder.toString();
    }
    
    void appendLevel(BinaryNode<E> root, int level, StringBuilder levelOrder) {
    	if(root == null)
    		return;
        if(level ==1 )
        	levelOrder.append(root.data + " ");
        else if (level > 1) {
        	appendLevel(root.left, level-1, levelOrder);
        	appendLevel(root.right, level - 1, levelOrder);
        }
    }
    
    public int treeHeight(BinaryNode<E> tree) {
    	if(tree == null)
    		return 0;
    	else {
    		int lheight = treeHeight(tree.left);
    		int rheight = treeHeight(tree.right);
    		
    		if(lheight > rheight)
    			return lheight + 1;
    		else
    			return rheight +1;
    	}
    }

    public BinaryNode<E> minimum(){
    	BinaryNode<E> minRoot;
    	minRoot = root;
    	while(minRoot.left != null) {
    		minRoot = minRoot.getLeftChild();
    	}
    	
        return minRoot;
    }
    
    public BinaryNode<E> maximum(){
    	BinaryNode<E> maxRoot;
    	maxRoot = root;
    	while(maxRoot.right != null) {
    		maxRoot = maxRoot.getRightChild();
    	}
    	
        return maxRoot;
    }

    public BinaryNode<E> successor(E target){
    	BinaryNode<E> currentNode = searchNode(target);
    	BinaryNode<E> localroot = root;
    	
    	if(currentNode.right != null) {
    		return minValue(currentNode.right);
    	}
    	BinaryNode<E> succ = null;
    	
    	while(localroot != null) {
    		if(target.compareTo(localroot.data) < 0) {
    			succ = localroot;
    			localroot = localroot.left;
    		}
    		else if (target.compareTo(localroot.data) > 0) {
    			localroot = localroot.right;
    		}
    		else
    			break;
    	}
    	return succ;
    } 
    
    BinaryNode<E> minValue(BinaryNode<E> node) 
    { 
        BinaryNode<E> current = node; 

        while (current.left != null) { 
            current = current.left; 
        } 
        return current; 
    } 
	
   
    public BinaryNode<E> predecessor(E target){
    	BinaryNode<E> currentNode = searchNode(target);
    	BinaryNode<E> localroot = root;
   	
    	if(currentNode.left != null) {
    		return maxValue(currentNode.left);
    	}
   	
    	BinaryNode<E> pred = null;
   	
    	while(localroot != null) {
    		if(target.compareTo(localroot.data) > 0) {
    			pred = localroot;
    			localroot = localroot.left;
    		}
    		else if (target.compareTo(localroot.data) < 0) {
    			localroot = localroot.left;
    		}
    		else
    			break;
    	}
    	return pred;
    }
    
    BinaryNode<E> maxValue(BinaryNode<E> node) 
    { 
        BinaryNode<E> current = node; 
        while (current.right != null) { 
            current = current.right; 
        } 
        return current; 
    } 
	


}
