/**
 * Name: Giovanni Joubert
 * 
 * Student number: u18009035
 */

public class SplayTree<T extends Comparable<T>> {

    public TreeNode<T> root;

    public SplayTree() {
        // Your code here...
        root = null;
    }

    /**
     * Returns true if the key exists in the tree, otherwise false
     */
    public Boolean contains(T key) {
        // Your code here...
        return contains(root, key);
    }

    public boolean contains(TreeNode<T> root, T key)
	{
		if (root == null)
			return false;
		if (root.key.equals(key))
			return true;
		
		if(root.key.compareTo(key) == 1)
			return contains(root.left, key);
		
		return contains(root.right, key);
	}


    /**
     * Insert the given key into the tree.
     * Duplicate keys should be ignored.
     * No Splaying should take place.
     */
    public void insert(T key) {
        // Your code here...
        if(contains(key)){
            //doing nothing yo
        } else {
            root = insertR(root, key); 
        }
    }

    
	public TreeNode<T>  insertR(TreeNode<T> root, T key) { /*recursive insert of node element*/
  
        if (root == null){
            root = new 	TreeNode<T>(key); 
            return root; 
        } 
  
        if (key.compareTo(root.key) < 0) 
            root.left = insertR(root.left, key); 
        else if (key.compareTo(root.key) == 1) 
            root.right = insertR(root.right, key); 
  
        return root;  // unchanged
    } 


    /**
     * Return the successor of the given key.
     * If the given key does not exist return null.
     * If the given key does not have a successor, return null.
     */


    public TreeNode<T> Minm(TreeNode<T>  root)
    {
        while (root.left != null) {
            root = root.left;
        }
 
        return root;
    }
  

    public T findSuccessor(T key) {
        if(! contains(key)){
            return null; 
        }
        if(findSuccessor(root, null, key) == null){
            return null;
        } else {
            return findSuccessor(root, null, key).key;
        }
    }

    public TreeNode<T> findSuccessor(TreeNode<T>  root, TreeNode<T>  suc, T key)
    {
        if (root == null) {
            return suc;
        }
 
        if (root.key.equals(key))
        {
            if (root.right != null) {
                return Minm(root.right);
            }
        }

        else if (key.compareTo(root.key) < 0)
        {
            suc = root;
            return findSuccessor(root.left, suc, key);
        }
        else {
            return findSuccessor(root.right, suc, key);
        }
 
        return suc;
    }



    /**
     * Move the accessed key closer to the root using the semi-splaying strategy.
     * If the key does not exist, insert it without splaying
     */
    public void access(T key) {
        // Your code here...
        if(!contains(key)){ // If the key does not exist, insert it without splaying
            insert(key);
        } else {
        

            splay(root, key);
              
        }
    }

    public TreeNode<T> splay(TreeNode<T> node, T key){

        if (node == null)
            return null;

        if (this.root.key.equals(key)) 
            return null; //no splay needed -- access already at root

        //case 1
        if(node.left != null)
        if(this.root == node && node.left.key.equals(key)){
            this.root = rotateRight(node);
           return null;
        }

        if(node.right != null)
        if(this.root == node && node.right.key.equals(key)){
            this.root = rotateLeft(node);
            return null;
         }
         //case 2

         T savekey;

         TreeNode<T> parent = getSuccessor(key);
         if (parent != null){
            TreeNode<T> grandparent = getSuccessor(parent.key);

        if(grandparent.left != null && parent.left != null) {
         if(grandparent.left == parent && parent.left.key.equals(key)){
            savekey = parent.key;

            if(getSuccessor(grandparent.key) != null){
                getSuccessor(grandparent.key).left = parent;
            } 
            
             rotateRight(grandparent);
             if(this.root.key.equals(grandparent.key)){
                 root = parent;
                 return null;
             } else {
             splay(node, savekey);
             }
             
         }
        }

        if(grandparent.right != null && parent.right != null) {
        if (grandparent.right == parent && parent.right.key.equals(key)) {
            savekey = parent.key;
            if(getSuccessor(grandparent.key) != null){
                getSuccessor(grandparent.key).right = parent;
            } 

             rotateLeft(grandparent);
             if(this.root.key.equals(grandparent.key)){
                 root = parent;
                 return null;
             } else {
             splay(node, savekey);
             }
            
         }
        }

        }
        

          //case 3
         parent = getSuccessor(key);
         if (parent != null){
            TreeNode<T> grandparent = getSuccessor(parent.key);
            if(grandparent!= null) {
                if(grandparent.left == parent && parent.right.key.equals(key)){
                    grandparent.left = parent.right;
                    rotateLeft(parent);
                    splay(node, key);
                }
                if(grandparent.right == parent && parent.left.key.equals(key)){
                    grandparent.right = parent.left;
                    rotateRight(parent);
                    splay(node, key);
                }

            }
        }


          
          



          return null;
         
    }

   

public TreeNode<T> getSuccessor(T key) {
        // Your code here...
        if(! contains(key)){ //If the given key does not exist return null.
            return null;
        } 

        if (root.key.equals(key)){ //If the given key does not have a successor, return null.
            return null;
        }

        TreeNode<T> Transverse = root;
        while(Transverse != null){
            if(Transverse.left != null){
                if(Transverse.left.key.equals(key)){
                    return Transverse;
                }
            }

            if(Transverse.right != null){
                if(Transverse.right.key.equals(key)){
                    return Transverse;
                }
            }

            if(Transverse.key.compareTo(key) == 1){
                Transverse = Transverse.left;
            } else {Transverse = Transverse.right;}
        }

        return null;

    }
    


    // right rotate
    private TreeNode<T> rotateRight(TreeNode<T> h) {
        TreeNode<T> x = h.left; 
        h.left = x.right; 
        x.right = h;
        return x; 
    }

    // left rotate
    private TreeNode<T> rotateLeft(TreeNode<T> h) {
        TreeNode<T> x = h.right; 
        h.right = x.left;  
        x.left = h; 
        return x;
    }


}