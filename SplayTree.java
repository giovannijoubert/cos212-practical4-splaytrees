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
		if (root.key == key)
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
    public T findSuccessor(T key) {
        // Your code here...
        if(! contains(key)){ //If the given key does not exist return null.
            return null;
        } 

        if (root.key == key){ //If the given key does not have a successor, return null.
            return null;
        }

        TreeNode<T> Transverse = root;
        while(Transverse != null){
            if(Transverse.left != null){
                if(Transverse.left.key == key){
                    return Transverse.key;
                }
            }

            if(Transverse.right != null){
                if(Transverse.right.key == key){
                    return Transverse.key;
                }
            }

            if(Transverse.key.compareTo(key) == 1){
                Transverse = Transverse.left;
            } else {Transverse = Transverse.right;}
        }

        return null;

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
            if (root.key == key) 
                 return; //no splay needed -- access already at root

            //case 1
            if(root.left != null)
            if(root.left.key == key){
               root = rotateRight(root);
               return;
            }

            if(root.right != null)
            if(root.right.key == key){
                root = rotateLeft(root);
                return;
             }

             //case 2
             if(root.left.left != null)
             if(root.left.left.key == key){
                root = rotateRight(root);
               
                return;
             }
 
             if(root.right.right != null)
             if(root.right.right.key == key){
                 root = rotateLeft(root);
              
                 return;
              }

              //case 3
              TreeNode<T> rotateMe = getSuccessor(key);
              
              if(rotateMe.left.key == key){
                getSuccessor(rotateMe.key).right = rotateMe.left;
                rotateMe = rotateRight(rotateMe);
                return;
            }

            if(rotateMe.right.key == key){
                getSuccessor(rotateMe.key).left = rotateMe.right;
                rotateMe = rotateLeft(rotateMe);
                return;
            }

              
        }
    }

public TreeNode<T> getSuccessor(T key) {
        // Your code here...
        if(! contains(key)){ //If the given key does not exist return null.
            return null;
        } 

        if (root.key == key){ //If the given key does not have a successor, return null.
            return null;
        }

        TreeNode<T> Transverse = root;
        while(Transverse != null){
            if(Transverse.left != null){
                if(Transverse.left.key == key){
                    return Transverse;
                }
            }

            if(Transverse.right != null){
                if(Transverse.right.key == key){
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