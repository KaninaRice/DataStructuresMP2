
public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E>{

    RedBlackNode<E> root;

    private RedBlackNode<E> getMinimum(RedBlackNode<E> node)
    {
        RedBlackNode<E> pointer = node;
        while(pointer.left != null)
        {
            pointer = pointer.left;
        }
        return pointer;
    }

    public RedBlackNode<E> min(){
        return getMinimum(this.root);
    }

    private RedBlackNode<E> getMaximum(RedBlackNode<E> node)
    {
        RedBlackNode<E> pointer = node;
        while(pointer.right != null)
        {
            pointer = pointer.right;
        }
        return pointer;
    }

    public RedBlackNode<E> max(){
         return getMaximum(this.root);
    }

    //search if the data exits in the tree
    public E search(E target) {
        return searchSupport(root, target);
    }

    private E searchSupport(RedBlackNode<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        int comparisonResults = target.compareTo(localRoot.data);
        if (comparisonResults == 0)
            return localRoot.data;
        else if (comparisonResults < 0)
            return searchSupport(localRoot.left, target);
        else
            return searchSupport(localRoot.right, target);
    }
    
    
    //search the node that contains the data
    public RedBlackNode<E> nodeSearch(E target) {
        return nodeSearchSupport(root, target);
    }

    private RedBlackNode<E> nodeSearchSupport(RedBlackNode<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        int comparisonResults = target.compareTo(localRoot.data);
        if (comparisonResults == 0)
            return localRoot;
        else if (comparisonResults < 0)
            return nodeSearchSupport(localRoot.left, target);
        else
            return nodeSearchSupport(localRoot.right, target);

    }


    private void rotateLeft(RedBlackNode<E> node){
        RedBlackNode<E> y = node.right;
        node.right = y.left;

        if(node.right != null)
        {
        	node.right.parent = node;
        }

        y.parent = node.parent;

        if(node.parent == null)
        {
            this.root = y;
        }
        else if(node == node.parent.left)
        {
        	node.parent.left = y;
        }
        else
        {
        	node.parent.right = y;
        }

        y.left = node;
        node.parent = y;
    }

    private void rotateRight(RedBlackNode<E> node){
        RedBlackNode<E> y = node.left;
        node.left = y.right;

        if(node.left != null)
        {
            node.left.parent = node;
        }

        y.parent = node.parent;

        if(node.parent == null)
        {
            this.root = y;
        }
        else if(node == node.parent.left)
        {
            node.parent.left = y;
        }
        else
        {
            node.parent.right = y;
        }

        y.right = node;
        node.parent = y;
    }

    private void fixTree(RedBlackNode<E> node)
    {
        RedBlackNode<E> parent = null;
        RedBlackNode<E> grandparent = null;

        while ((node != this.root) && (node.isRed()) && (node.parent.isRed()))
        {
            parent = node.parent;
            grandparent = node.parent.parent;

            if(parent == grandparent.left)
            {
                RedBlackNode<E> uncle = grandparent.right;
                if(uncle != null && uncle.isRed())
                {
                    grandparent.setAsRed(true);
                    parent.setAsRed(false);
                    uncle.setAsRed(false);
                    node = grandparent;
                }
                else
                {
                    if(node == parent.right)
                    {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }

                    rotateRight(grandparent);
                    boolean swap = parent.isRed();
                    parent.setAsRed(grandparent.isRed());
                    grandparent.setAsRed(swap);

                    node = parent;
                }
            }
            else
            {
                RedBlackNode<E> uncle = grandparent.left;
                if(uncle != null && uncle.isRed())
                {
                    grandparent.setAsRed(true);
                    parent.setAsRed(false);
                    uncle.setAsRed(false);
                    node = grandparent;
                }
                else
                {
                    if(node == parent.left)
                    {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }

                    rotateLeft(grandparent);
                    boolean swap = parent.isRed();
                    parent.setAsRed(grandparent.isRed());
                    grandparent.setAsRed(swap);

                    node = parent;
                }
            }
        }
        this.root.setAsRed(false);
    }

    
    private RedBlackNode<E> successorReplace(RedBlackNode<E> node)
    {
        RedBlackNode<E> temp = node;
        while(temp.left != null)
        {
            temp = temp.left;
        }
        return temp;
    }

    private RedBlackNode<E> replace (RedBlackNode<E> node)
    {
        if(node.left != null && node.right != null)
        {
            return successorReplace(node.right);
        }

        if(node.left == null && node.right == null)
        {
            return null;
        }

        if(node.left != null)
        {
            return node.left;
        }
        else
        {
            return node.right;
        }

    }
    
    private RedBlackNode<E> getSibling(RedBlackNode<E> node){
    	if(node == node.parent.right) {
    		return node.parent.left;
    	}
    	else
    		return node.parent.right;
    }

    private void fixDoubleBlack(RedBlackNode<E> node) {
        if(node == this.root) {
            return;
        }

        RedBlackNode<E> sibling = getSibling(node);
        RedBlackNode<E> parent = node.parent;

        if(sibling == null) {
            this.fixDoubleBlack(parent);
        }
        else {
            if(sibling.isRed()) {
                parent.setAsRed(true);
                sibling.setAsRed(false);
                if(sibling == sibling.parent.left) {
                    rotateRight(parent);
                }
                else {
                    rotateLeft(parent);
                }
                fixDoubleBlack(node);
            }
            else {
                if((sibling.left != null && sibling.left.isRed()) || (sibling.right != null && sibling.right.isRed())) {
                    if(sibling.left != null && sibling.left.isRed()) {
                        if(sibling == sibling.parent.left) {
                            sibling.left.setAsRed(sibling.isRed());
                            sibling.setAsRed(parent.isRed());
                            rotateRight(parent);
                        }
                        else {
                            sibling.left.setAsRed(parent.isRed());
                            rotateRight(sibling);
                            rotateLeft(parent);
                        }
                    }
                    else  {
                        if(sibling == sibling.parent.left) {
                            sibling.right.setAsRed(parent.isRed());
                            rotateLeft(sibling);
                            rotateRight(parent);
                        }
                        else {
                            sibling.right.setAsRed(sibling.isRed());
                            sibling.setAsRed(parent.isRed());
                            rotateLeft(parent);
                        }
                    }
                    parent.setAsRed(false);
                } 
                else {
                    sibling.setAsRed(true);
                    if(!parent.isRed()) {
                        fixDoubleBlack(parent);
                    }
                    else {
                        parent.setAsRed(false);
                    }
                }
            }
        }
    }
    
    public boolean insert(E item) {
        if(search(item) == item) {
            return false;
        }
        else {
            this.root = insertSupporter(this.root, item);
            RedBlackNode<E> node = nodeSearch(item);
            fixTree(node);
            return true;
        }
    }

    private RedBlackNode<E> insertSupporter (RedBlackNode<E> node, E item) {
        if(node == null) {
            return (new RedBlackNode<E> (item));
        }
        else if (item.compareTo(node.data) < 0) {
            node.left = insertSupporter(node.left, item);
            node.left.parent = node;
        }
        else if (item.compareTo(node.data) > 0) {
            node.right = insertSupporter(node.right, item);
            node.right.parent = node;
        }
        else {
            return node;
        }
        return node;
    }

    public E delete(E target){
        if(search(target) != target) {
            return null;
        }
        else {
            RedBlackNode<E> node = nodeSearch(target);
            deleteSupporter(node);
            return target;
        }
    }

    private void deleteSupporter(RedBlackNode<E> node) {
        RedBlackNode<E> x = replace(node);

        boolean xyBlack = ((x == null || !x.isRed()) && (!node.isRed()));
        RedBlackNode<E> parent = node.parent;
        if(x == null) {
            if (node == this.root) {
                this.root = null;
            }
            else{
                if(xyBlack) {
                    fixDoubleBlack(node);
                }
                else {
                    RedBlackNode<E> siblingY = (node == node.parent.right) ? node.parent.left : node.parent.right;
                    if(siblingY != null) {
                        siblingY.setAsRed(true);
                    }
                }

                if(node == node.parent.left) {
                    parent.left = null;
                }
                else {
                    parent.right = null;
                }
            }
            node = null;
            return;
        }

        if(node.left == null || node.right == null) {
            if(node == this.root) {
                node.data = x.data;
                node.left = node.right = null;
                x = null;
            }
            else {
                if(node == node.parent.left) {
                    parent.left = x;
                }
                else {
                    parent.right = x;
                }
                node = null;
                x.parent = parent;

                if(xyBlack) {
                    fixDoubleBlack(x);
                }
                else {
                    x.setAsRed(false);
                }
            }
            return;
        }
        E swap = x.data;
        x.changeData(node.data);
        node.changeData(swap);
        deleteSupporter(x);
    }

    public String postorder() {
        StringBuilder postorderTraversal = new StringBuilder();
        postorderSupport(root, postorderTraversal);
        return postorderTraversal.toString();
    }

    public void postorderSup(RedBlackNode<E> root, StringBuilder postorder) {
        if(root != null) {
            postorderSup(root.getLeftChild(), postorder);
            postorderSup(root.getRightChild(), postorder);
            postorder.append(root + " ");
        }
    }

    public String inorder() {
        StringBuilder inorderTraversal = new StringBuilder();
        inorderSupport(root, inorderTraversal);
        return inorderTraversal.toString();
    }

    public void inorderSup(RedBlackNode<E> root, StringBuilder inorder) {
        if(root != null) {
            inorderSup(root.getLeftChild(), inorder);
            inorder.append(root + " ");
            inorderSup(root.getRightChild(), inorder);
        }
    }
    
    public String preorder(){
        StringBuilder preorderTraversal = new StringBuilder();
        preorderSupport(root, preorderTraversal);
        return preorderTraversal.toString();

    }

    private void preorderSupport(RedBlackNode<E> root, StringBuilder preorder){
        if (root != null) {
            preorder.append(root + " ");
            preorderSupport(root.getLeftChild(), preorder);
            preorderSupport(root.getRightChild(), preorder);
        }
    }
    


    private class RedBlackNode<E> extends BinaryTree.BinaryNode {
        private boolean isRed;
        protected E data;
        protected RedBlackNode<E> left;
        protected RedBlackNode<E> right;
        protected RedBlackNode<E> parent;


        @SuppressWarnings("unchecked")
		public RedBlackNode(E data) {
        	super(data);
            this.data = data;
            isRed = true;
        }

        public void changeData(E data) {
            this.data = data;
        }

        public void setAsRed(boolean isRed) {
            this.isRed = isRed;
        }

        public boolean isRed() {
            return this.isRed;
        }

        public RedBlackNode<E> getLeftChild() {
            return left;
        }

        public RedBlackNode<E> getRightChild() {
            return right;
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
            return data.toString() + color;
        }
    }


}