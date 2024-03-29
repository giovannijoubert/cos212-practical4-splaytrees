/**
 * Name: Giovanni Joubert
 * 
 * Student Number: u18009035
 */
public class TreeNode<T extends Comparable<T>> {
    public T key;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T key) {
        // Your Code here...
        this.key = key;
        left = null;
        right = null;
    }

    /**
     * Return a string representation of the TreeNode
     */
    public String toString() {
        String left = this.left == null ? "null" : this.left.key.toString();
        String right = this.right == null ? "null" : this.right.key.toString();

        return "[" + this.key + ", L:" + left + ", R:" + right + "]";   
    }
}