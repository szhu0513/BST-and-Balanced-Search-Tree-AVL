/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: P2_BST and Balanced Search Tree AVL
// Files: BST.java
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

import java.util.ArrayList; // allowed for creating traversal lists
import java.util.List; // required for returning List<K>


/**
 * This class mainly write some method that based on binary searh tree
 * 
 * @author Shiyu Zhu
 *
 * @param <K> the general type
 * @param <V> the general type
 */
// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root;
  // number of keys in BST
  protected int numKeys;

  /**
   * This is a constructor which initialize the root and numKeys
   */
  public BST() {
    this.root = null;
    this.numKeys = 0;
  }

  /**
   * This is a helper method to add the keys of the data structure in sorted order.
   * 
   * @param node          the node that start to traverse
   * @param traversalList the list to display the keys in sorted order
   * @return List of Keys in-order
   */
  protected List<K> inorder(BSTNode<K, V> node, List<K> traversalList) {
    if (node == null) {
      return traversalList;
    } else {
      // check the left subtree
      inorder(node.left, traversalList);
      traversalList.add(node.key);
      // check the right subtree
      inorder(node.right, traversalList);
      return traversalList;
    }
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   * @see SearchTreeADT#getInOrderTraversal()
   */
  @Override
  public List<K> getInOrderTraversal() {
    List<K> inOrderTraversalList = new ArrayList<>();
    inOrderTraversalList=inorder(this.root, inOrderTraversalList);
    return inOrderTraversalList;
  }

  /**
   * This is a helper method to add the keys of the data structure in pre-order.
   * 
   * @param node          the node that start to traverse
   * @param traversalList the list to display the keys in sorted order
   * @return List of Keys pre-order
   */
  protected List<K> preorder(BSTNode<K, V> node, List<K> traversalList) {
    if (node == null) {
      return traversalList;
    } else {
      traversalList.add(node.key);
      // check the left subtree
      preorder(node.left, traversalList);
      // check the right subtree
      preorder(node.right, traversalList);
      return traversalList;
    }
  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   * @see SearchTreeADT#getPreOrderTraversal()
   */
  @Override
  public List<K> getPreOrderTraversal() {
    List<K> preOrderTraversalList = new ArrayList<>();
    preOrderTraversalList=preorder(this.root, preOrderTraversalList);
    return preOrderTraversalList;
  }

  /**
   * This is a helper method to add the keys of the data structure in post-order.
   * 
   * @param node          the node that start to traverse
   * @param traversalList the list to display the keys in sorted order
   * @return List of Keys post-order
   */
  protected List<K> postorder(BSTNode<K, V> node, List<K> traversalList) {
    if (node == null) {
      return traversalList;
    } else {
      // check the left subtree
      postorder(node.left, traversalList);
      // check the right subtree
      postorder(node.right, traversalList);
      traversalList.add(node.key);
      return traversalList;
    }
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   * @see SearchTreeADT#getPostOrderTraversal()
   */
  @Override
  public List<K> getPostOrderTraversal() {
    List<K> postOrderTraversalList = new ArrayList<>();
    postOrderTraversalList=postorder(this.root, postOrderTraversalList);
    return postOrderTraversalList;
  }

  /**
   * This is a helper method to add the keys of the data structure in level-order.
   * 
   * @param node          the node that start to traverse
   * @param traversalList the list to display the keys in sorted order
   * @param maxHeight     the maxHeight(level) of the BST
   * 
   */
  private List<K> levelorder(BSTNode<K, V> node, int maxHeight, List<K> traversalList) {
    if (node == null) { // if BST is empty, return empty list
      return traversalList;
    }
    if (maxHeight == 1) { // if BST only have one level
      traversalList.add(node.key);
      return traversalList;
    } else { // if BST only have more than one level
      levelorder(node.left, maxHeight - 1, traversalList);
      levelorder(node.right, maxHeight - 1, traversalList);
    }
    return traversalList;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order. The root is first in the
   * list, then the keys found in the next level down, and so on. If the SearchTree is empty, an
   * empty list is returned.
   * 
   * @return List of Keys in level-order
   * @see SearchTreeADT#getLevelOrderTraversal()
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    List<K> levelOrderTraversalList = new ArrayList<>();
    int height = getHeight();
    for (int i = 1; i <= height; i++) {
      levelOrderTraversalList=levelorder(this.root, i, levelOrderTraversalList);
    }
    return levelOrderTraversalList;
  }

  /**
   * This is a helper method which add the key,value pairs to the appropriate place of the BST.And
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
    // If key is already in data structure, throws DuplicateKeyException();
    if (key.compareTo(node.key) == 0) {
      throw new DuplicateKeyException();
    }

    if (key.compareTo(node.key) < 0) {
      node.left = insertHelper(node.left, key, value);// check the left subtree
    } else {
      node.right = insertHelper(node.right, key, value);// check the right subtree
    }

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
   * The help method to find the in order predecessor of a specific key
   * 
   * @param node the node start traverse
   * @param key  the specific key
   * @return in order predecessor of a specific key
   */
  protected K inOrderPredecessor(BSTNode<K, V> node, K key) {
    List<K> inOrderTraversalListForPredecessor = new ArrayList<>();
    inOrderTraversalListForPredecessor = getInOrderTraversal();
    for (int i = 0; i < inOrderTraversalListForPredecessor.size(); i++) {
      if (inOrderTraversalListForPredecessor.get(i).equals(key)) {// InOrderPredecessor found
        return inOrderTraversalListForPredecessor.get(i - 1);
      }
    }
    return null; // not found
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
    if (node == null) { // BST is null
      return node;
    }

    // Otherwise, recursive down the tree
    if (key.compareTo(node.key) < 0) // key is smaller than current root, recursive the left subtree
      node.left = removeHelper(root.left, key);
    else if (key.compareTo(node.key) > 0)// key is greater than current root recursive right subtree
      node.right = removeHelper(root.right, key);
    // if key is same as root's key, then This is the node to be deleted
    else {
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

  /**
   * This is the help method to returns the value associated with the specified key
   * 
   * @param node the node with specific key
   * @param key  the specified key
   * @return the value associated with the specified key
   * @throws KeyNotFoundException If key is null, throw IllegalNullKeyException
   */
  protected V getHelper(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    if (node == null) { // key is not found
      throw new KeyNotFoundException();
    }
    if (key.compareTo(node.key) < 0) {
      return getHelper(node.left, key); // the key is in the left subtree
    } else if (key.compareTo(node.key) > 0) {
      return getHelper(node.right, key); // the key is in the right subtree
    } else {
      return node.value; // find the node with specific key, return that
    }
  }

  /**
   * Returns the value associated with the specified key.Does not remove key or decrease number of
   * keys
   * 
   * @param key the specified key
   * @throws KeyNotFoundException    If key is not found, throw KeyNotFoundException().
   * @throws IllegalNullKeyException If key is null, throw IllegalNullKeyException
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    return getHelper(this.root, key);
  }

  /**
   * The help method to see if the key is in the data structure
   * 
   * @param node the node with specific key
   * @param key  the specified key
   * @return true if the key is in the data structure, false if key is not null and is not present
   */
  protected boolean containsHelper(BSTNode<K, V> node, K key) {
    if (node == null) // key is not found
      return false;
    if (key.compareTo(node.key) < 0) {
      return containsHelper(node.left, key); // the key is in the left subtree
    } else if (key.compareTo(node.key) > 0) {
      return containsHelper(node.right, key); // the key is in the right subtree
    } else {
      return true; // find the node with specific key, return that
    }
  }

  /**
   * This method is to see if the key is in the data structure
   * 
   * @param key the specified key
   * @return true if the key is in the data structure, false if key is not null and is not present
   * @throws IllegalNullKeyException If key is null, throw IllegalNullKeyException
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    return containsHelper(this.root, key);
  }

  /**
   * Returns the number of key,value pairs in the data structure
   * 
   * @return the number of pairs in the data structure
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   * @see BSTADT#getKeyAtRoot()
   */
  @Override
  public K getKeyAtRoot() {
    if (this.root == null) {
      return null;
    } else {
      return this.root.key;
    }
  }

  /**
   * The helper method to get the node with specific key
   * 
   * @param node the node where start to search
   * @param key  specific key
   * @return the node with specific key
   * @throws KeyNotFoundException  if key is not found in this BST
   */
  protected BSTNode<K, V> getNodeHelper(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    if (node == null) { // key is not found
      throw new KeyNotFoundException();
    }
    if (key.compareTo(node.key) < 0) {
      return getNodeHelper(node.left, key); // the key is in the left subtree
    } else if (key.compareTo(node.key) > 0) {
      return getNodeHelper(node.right, key); // the key is in the right subtree
    } else {
      return node; // return the node with specific key
    }
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the key that is in the left child. If the left child of the found node is null, returns
   * null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException    if key is not found in this BST
   * @see BSTADT#getKeyOfLeftChildOf()
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    if (!this.contains(key)) {
      throw new KeyNotFoundException();
    } // key is not found
    else {
      BSTNode<K, V> nodeWithKey = getNodeHelper(this.root, key);
      return nodeWithKey.left.key;
    }
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the right child. If the right child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found in this BST
   * @see BSTADT#getKeyOfRightChildOf()
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalArgumentException("null key");
    }
    if (!this.contains(key)) {
      throw new KeyNotFoundException();
    } // key is not found
    else {
      BSTNode<K, V> nodeWithKey = getNodeHelper(this.root, key);
      return nodeWithKey.right.key;
    }
  }

  /**
   * This is a helper method which return the height of node.
   * 
   * @param root the node start traverse
   * @return the height of node
   */
  protected int getHeightHelper(BSTNode<K, V> node) {
    if (node == null) { // if BST is empty
      return 0;
    }
    if (node.left == null && node.right == null) {// if node is root
      return 1;
    }
    return Math.max(getHeightHelper(node.left), getHeightHelper(node.right)) + 1;
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    return getHeightHelper(this.root);
  }
}
