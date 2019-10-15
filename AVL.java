/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: P2_BST and Balanced Search Tree AVL
// Files: AVL.java
// Semester: Spring 2019
// Course: CS400
// Lecture: 002
// Due Date: Before 10pm on February 24, 2019
// Author: Shiyu Zhu
// Email: SZHU227@wisc.edu
// Lecture's Name: Deb Deppeler
// Bugs: no known bugs
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This is a BST search tree that maintains its balance using AVL rotations.
 * @author Shiyu Zhu
 *
 * @param <K> the general type
 * @param <V> the general type
 */
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

  /**
   * This method is right rotate (if the insert node is left child) to re-balance the AVL tree
   * 
   * @param g the node where BF=2
   * @return the root after rotate
   */
  private BSTNode<K, V> rightRotate(BSTNode<K, V> g) {
    BSTNode<K, V> p = g.left; // the left child of the g node
    g.left = p.right; // change the right child of p to the left child of g
    p.right = g; // change g to the p's right child
    // update height
    g.height = Math.max(getHeightHelper(g.left), getHeightHelper(g.right)) + 1;
    p.height = Math.max(getHeightHelper(p.left), getHeightHelper(p.right)) + 1;
    return p; // p is now the root after rotate.
  }

  /**
   * This method is left rotate (if the insert node is right child) to re-balance the AVL tree
   * 
   * @param g the node where BF=2
   * @return the root after rotate
   */
  private BSTNode<K, V> leftRotate(BSTNode<K, V> g) {
    BSTNode<K, V> p = g.right; // the right child of the g node
    g.right = p.left; // change the left child of p to the right child of g
    p.left = g; // change g to the p's left child
    // update height
    g.height = Math.max(getHeightHelper(g.left), getHeightHelper(g.right)) + 1;
    p.height = Math.max(getHeightHelper(p.left), getHeightHelper(p.right)) + 1;
    return p; // p is now the root after rotate.
  }

  /**
   * This method is left right rotate (if the insert node is right child) to re-balance the AVL tree
   * 
   * @param g the node where BF=2
   * @return the root after rotate
   */
  private BSTNode<K, V> leftRightRotate(BSTNode<K, V> g) {
    g.left = leftRotate(g.left);
    return rightRotate(g);
  }

  /**
   * This method is right left rotate (if the insert node is left child) to re-balance the AVL tree
   * 
   * @param g the node where BF=2
   * @return the root after rotate
   */
  private BSTNode<K, V> rightLeftRotate(BSTNode<K, V> g) {
    g.right = rightRotate(g.right);
    return leftRotate(g);
  }

  /**
   * This is a helper method which add the key,value pairs to the appropriate place of the AVL.And
   * increase the number of keys. Suppose that key is not null
   * 
   * @param node  check the place to insert from node
   * @param key   The key to be inserted
   * @param value The value to be inserted
   * @return the node that insert
   * @throws DuplicateKeyException If key is already in data structure, throw
   *                               DuplicateKeyException();
   */
  protected BSTNode<K, V> insertHelper(BSTNode<K, V> node, K key, V value)
      throws DuplicateKeyException {
    // check root
    if (node == null) {
      node = new BSTNode<K, V>(key, value);
      this.numKeys++;
      return node;
    }
    
    if (key.compareTo(node.key) < 0) { // check the left subtree
      node.left = insertHelper(node.left, key, value);
      // after insert, check the BF of the node because now we insert a left child, so left
      // subtree's height is greater than the right subtree's height
      if (getHeightHelper(node.left) - getHeightHelper(node.right) == 2) {
        if (key.compareTo(node.left.key) < 0) {
          node = rightRotate(node);
        } else {
          node = leftRightRotate(node);
        }
      }
    } else if (key.compareTo(node.key) > 0) {// check the left subtree
      node.right = insertHelper(node.right, key, value);
      // after insert, check the BF of the node because now we insert a right child, so right
      // subtree's height is greater than the left subtree's height
      if (getHeightHelper(node.right) - getHeightHelper(node.left) == 2) {
        if (key.compareTo(node.right.key) > 0) {
          node = leftRotate(node);
        } else {
          node = rightLeftRotate(node);
        }
      }
    } else {
      throw new DuplicateKeyException(); // If key is already in data structure, throws
                                         // DuplicateKeyException();
    }
    node.height = Math.max(getHeightHelper(node.left), getHeightHelper(node.right)) + 1;
    return node;
  }

  /**
   * Add the key,value pair to the data structure and increase the number of keys.
   * 
   * @param key   The key to be inserted
   * @param value The value to be inserted
   * @throws DuplicateKeyException   If key is already in data structure, throw
   *                                 DuplicateKeyException()
   * @throws IllegalNullKeyException If key is null, throw IllegalNullKeyException
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    // insert the new node
    this.root = insertHelper(this.root, key, value);
  }

  /**
   * This is a helper method which remove the key,value pair from the data structure and decrease
   * num keys. Suppose that key is not null
   * 
   * @param node check the place from node
   * @param key  The key to be remove
   * @return the node after delete
   * @throws IllegalNullKeyException If key is null, throw IllegalNullKeyException
   * @throws KeyNotFoundException    If key is not found, throw KeyNotFoundException().
   */
  protected BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    if (node == null) { // AVL is null
      return null;
    }
    // Otherwise, recursive down the tree
    if (key.compareTo(node.key) < 0) { // key is smaller than current root, recursive the left
                                       // subtree
      node.left = removeHelper(node.left, key);
      // because we delete one node from left subtree, so we need to check the BF, now the height of
      // right subtree is greater than the left subtree
      // if the AVL tree is not balance
      if (getHeightHelper(node.right) - getHeightHelper(node.left) == 2) {
        if (getHeightHelper(node.right.left) > getHeightHelper(node.right.right)) {
          // if the height of node.right's left subtree is greater than the height of node.right's
          // right subtree
          node=rightLeftRotate(node);
        } else {
          // if the height of node.right's left subtree is less than the height of node.right's
          // right subtree
          node=leftRotate(node);
        }
      }
    } else if (key.compareTo(node.key) > 0) {// key is greater than current root, recursive the
                                             // right subtree
      node.right = removeHelper(node.right, key);
      // because we delete one node from right subtree, so we need to check the BF, now the height
      // of
      // left subtree is greater than the right subtree
      // if the AVL tree is not balance
      if (getHeightHelper(node.left) - getHeightHelper(node.right) == 2) {
        if (getHeightHelper(node.left.right) > getHeightHelper(node.left.left)) {
          // if the height of node.right's left subtree is greater than the height of node.left's
          // left subtree
          node=leftRightRotate(node);
        } else {
          // if the height of node.left's left subtree is less than the height of node.left's
          // right subtree
          node=rightRotate(node);
        }
      }
    } else {
      // decrease num keys.
      this.numKeys--;
      if (node.left == null && node.right == null) { // node has no children
        return null;
      } else if (node.left == null) {// node has one right subtrees
        return node.right;
      } else if (node.right == null) {// node has one left subtrees
        return node.left;
      } else { // node has two subtrees
        K newKey = inOrderPredecessor(node, key);
        V newValue = this.get(newKey);
        node.key = newKey;
        node.value = newValue;
        node.left = removeHelper(node.left, newKey);
        return node;
      }
    }
    node.height = Math.max(getHeightHelper(node.left), getHeightHelper(node.right)) + 1;
    return node;
  }

  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is null, throw IllegalNullKeyException If key is not found, throw KeyNotFoundException().
   * 
   * @param key The key to be remove
   * @throws KeyNotFoundException    If key is not found, throw KeyNotFoundException().
   * @throws IllegalNullKeyException If key is null, throw IllegalNullKeyException
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }

    if (!this.contains(key)) {
      throw new KeyNotFoundException(); // key is not found
    } else {
      this.root = removeHelper(root, key);
      return true;
    }
  }

}

