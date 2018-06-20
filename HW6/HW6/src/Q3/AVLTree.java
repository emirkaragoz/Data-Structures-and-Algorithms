package Q3;


import Q1.BinarySearchTreeWithRotate;
import Q1.BinaryTree;

import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis.
 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    /**
     * No Parameter Constructor
     */
    public AVLTree(){}

    /**
     * Takes BinaryTree Constructor
     * @param b given BinaryTree
     */
    public AVLTree(BinaryTree<E> b){
        if (isAVLTree(b.getRoot())) //checks whether given BinaryTree is an AVL tree
            root = b.getRoot();
        else
            throw new InvalidParameterException("This Binary Tree is not AVL Tree!");
    }

    // Insert nested class AVLNode<E> here.
    /** Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods
        /**
         * Construct a node with the given item as the data field.
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    // Data Fields
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;

    /** order of added elements */
    private LinkedList<E> order = new LinkedList<>();

    // Methods
    /**
     * add starter method.
     * @pre the item to insert implements the Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     * @post addReturn is set true if the item is inserted,
     *       false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item
     *         inserted
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            if (!order.contains(item))
                order.add(item);
            return new AVLNode<E>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            if (!order.contains(item))
                order.add(item);
            return localRoot; // Rebalance not needed.
        } else {
            // item > data
            localRoot.right = add((AVLNode<E>) localRoot.right, item);

            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            if (!order.contains(item))
                order.add(item);
            return localRoot; // Rebalance not needed.
        }
    }

    /**
     * Deletes given element from tree if there is, otherwise do nothing
     * @param target The object to be deleted
     * @return deleted element or null
     */
    @Override
    public E delete(E target) {
        if (order.contains(target)) {
            root = root.left = root.right = null;   //clean the all tree
            LinkedList<E> tmpOrder = new LinkedList<>(order);
            for (int i = 0; i < order.size(); ++i) {
                if (!tmpOrder.peek().equals(target))    //add all elements except target
                    add(tmpOrder.poll());
                else
                    tmpOrder.poll();
            }
            order.remove(target);
            return target;
        } else
            return null;
    }

    /**
     * Method to rebalance left.
     * @pre localRoot is the root of an AVL subtree that is
     *      critically left-heavy.
     * @post Balance is restored.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { //Left-Left case
            // In this case the leftChild (the new root)
            // and the root (new right child) will both be balanced
            // after the rotation.
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /** rebalanceRight
     @pre localRoot is the root of an AVL subtree that is
     more than one right heavy.
     @post balance is restored and increase is set false
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain reference to right child
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See if right-left heavy
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            /* Adjust the balances to be their new values after
             the rotates are performed.
            */
            if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }

            localRoot.right = rotateRight(rightChild);
        } else {
            /* After the rotates the overall height will be
             reduced thus increase is now false, but
             decrease is now true.
             */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }

        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * Method to decrement the balance field and to reset the value of
     * increase.
     * @pre The balance field was correct prior to an insertion [or
     *      removal,] and an item is either been added to the left[
     *      or removed from the right].
     * @post The balance is decremented and the increase flags is set
     *       to false if the overall height of this subtree has not
     *       changed.
     * @param node The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;
        }
    }

    /** Method to increment the balance field and to reset the value of increase or decrease.
     *@param node The AVL node whose balance is to be incremented
     */
    private void incrementBalance(AVLNode<E> node) {
        // Increment the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;
        }
    }

    /**
     * Checks whether given node's tree is a AVL tree
     * @param n given node
     * @return true or false
     */
    private boolean isAVLTree(Node n) {
        if (n == null)
            return true;

        int rightHeight = height(n.right);
        int leftHeight = height(n.left);

        return (Math.abs(rightHeight - leftHeight) < 2 && isAVLTree(n.left) && isAVLTree(n.right));
    }

    /**
     * Calculate height of Tree which has given root
     * @param node given root
     * @return height of tree
     */
    private int height(Node node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

}
