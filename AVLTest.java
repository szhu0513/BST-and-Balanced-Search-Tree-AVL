/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: P2_BST and Balanced Search Tree AVL
// Files: AVLTest.java
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;

/**
 * This class is going to add tests to test the rebalancing of the AVL operations
 * 
 * @author zhushiyu
 *
 */
// @SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

  AVL<String, String> avl;
  AVL<Integer, String> avl2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = bst = avl = createInstance();
    dataStructureInstance2 = bst2 = avl2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    avl = null;
    avl2 = null;
  }

  /**
   * This method is going to create an instance with the type of key is string, and type of the
   * value is String
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected AVL<String, String> createInstance() {
    return new AVL<String, String>();
  }

  /**
   * This method is going to create an instance with the type of key is Integer, and type of the
   * value is String
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected AVL<Integer, String> createInstance2() {
    return new AVL<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testAVL_001_insert_sorted_order_simple() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(30, "30");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 001: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testAVL_002_insert_reversed_sorted_order_simple() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to left child of root does not work");

      avl2.insert(10, "10");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 002: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_003_insert_smallest_largest_middle_order_simple() {
    try {
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(30, "30");
      if (!avl2.getKeyOfRightChildOf(10).equals(30))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");

      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 003: " + e.getMessage());
    }
  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_004_insert_largest_smallest_middle_order_simple() {
    try {
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to left child of root does not work");

      avl2.insert(20, "20");

      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 004: " + e.getMessage());
    }
  }

  /**
   * Insert more than three values in AVL tree and then check the root, left, and right keys to see
   * if rebalancing occurred. The last node is inserted in the left subtree of the left subtree.
   * 
   */
  @Test
  void testAVL_005_insert_lastNode_in_leftSubtree_of_leftSubtree_complex() {
    try {
      avl2.insert(8, "8");
      if (!avl2.getKeyAtRoot().equals(8))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(8).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(7, "7");
      if (!avl2.getKeyOfLeftChildOf(8).equals(7))
        fail("avl insert to left child of root does not work");

      avl2.insert(5, "5");
      if (!avl2.getKeyOfLeftChildOf(7).equals(5))
        fail("avl insert to left child of node 7 does not work");

      avl2.insert(3, "3");
      Integer k = avl2.getKeyOfLeftChildOf(8);
      if (!k.equals(5))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 5 as its left child and 10 as its right child
      // the left child of 5 should change to 3 and right child of 5 should be 7

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(5));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(5), new Integer(3));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(5), new Integer(7));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 005: " + e.getMessage());
    }
  }

  /**
   * /** Insert more than three values in AVL tree and then check the root, left, and right keys to
   * see if rebalancing occurred. The last node is inserted in the right subtree of the right
   * subtree.
   * 
   */
  @Test
  void testAVL_006_insert_lastNode_in_rightSubtree_of_rightSubtree_complex() {
    try {
      avl2.insert(8, "8");
      if (!avl2.getKeyAtRoot().equals(8))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(8).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(7, "7");
      if (!avl2.getKeyOfLeftChildOf(8).equals(7))
        fail("avl insert to left child of root does not work");

      avl2.insert(13, "13");
      if (!avl2.getKeyOfRightChildOf(10).equals(13))
        fail("avl insert to right child of node 10 does not work");

      avl2.insert(15, "15");
      Integer k = avl2.getKeyOfRightChildOf(8);
      if (!k.equals(13))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 7 as its left child and 13 as its right child
      // the left child of 13 should change to 10 and right child of 13 should be 15

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(7));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(13));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(13), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(13), new Integer(15));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 006: " + e.getMessage());
    }
  }

  /**
   * /** Insert more than three values in AVL tree and then check the root, left, and right keys to
   * see if rebalancing occurred. The last node is inserted in the right subtree of the left
   * subtree.
   * 
   */
  @Test
  void testAVL_007_insert_lastNode_in_rightSubtree_of_leftSubtree_complex() {
    try {
      avl2.insert(8, "8");
      if (!avl2.getKeyAtRoot().equals(8))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(8).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(7, "7");
      if (!avl2.getKeyOfLeftChildOf(8).equals(7))
        fail("avl insert to left child of root does not work");

      avl2.insert(5, "5");
      if (!avl2.getKeyOfLeftChildOf(7).equals(5))
        fail("avl insert to left child of node 7 does not work");

      avl2.insert(6, "6");
      Integer k = avl2.getKeyOfLeftChildOf(8);
      if (!k.equals(6))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 6 as its left child and 10 as its right child
      // the left child of 6 should change to 5 and right child of 6 should be 7

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(6));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(6), new Integer(5));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(6), new Integer(7));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 007: " + e.getMessage());
    }
  }

  /**
   * /** Insert more than three values in AVL tree and then check the root, left, and right keys to
   * see if rebalancing occurred. The last node is inserted in the left subtree of the right
   * subtree.
   * 
   */
  @Test
  void testAVL_008_insert_lastNode_in_leftSubtree_of_rightSubtree_complex() {
    try {
      avl2.insert(8, "8");
      if (!avl2.getKeyAtRoot().equals(8))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(8).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(7, "7");
      if (!avl2.getKeyOfLeftChildOf(8).equals(7))
        fail("avl insert to left child of root does not work");

      avl2.insert(13, "13");
      if (!avl2.getKeyOfRightChildOf(10).equals(13))
        fail("avl insert to right child of node 10 does not work");

      avl2.insert(11, "11");
      Integer k = avl2.getKeyOfRightChildOf(8);
      if (!k.equals(11))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 7 as its left child and 11 as its right child
      // the left child of 11 should change to 10 and right child of 11 should be 13

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(7));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(11));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(11), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(11), new Integer(13));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 008: " + e.getMessage());
    }
  }

  /**
   * /** Insert more than three values in AVL tree and then check the root, left, and right keys to
   * see if rebalancing occurred. The last node is inserted in the left subtree of the right
   * subtree. If the rebalancing occurred, check to see if the height change
   * 
   */
  @Test
  void testAVL_009_insert_lastNode_in_leftSubtree_of_rightSubtree_complex_check_height() {
    try {
      avl2.insert(8, "8");
      if (!avl2.getKeyAtRoot().equals(8))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(8).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(7, "7");
      if (!avl2.getKeyOfLeftChildOf(8).equals(7))
        fail("avl insert to left child of root does not work");

      avl2.insert(13, "13");
      if (!avl2.getKeyOfRightChildOf(10).equals(13))
        fail("avl insert to right child of node 10 does not work");

      avl2.insert(11, "11");
      Integer k = avl2.getKeyOfRightChildOf(8);
      if (!k.equals(11))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 7 as its left child and 11 as its right child
      // the left child of 11 should change to 10 and right child of 11 should be 13

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(7));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(11));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(11), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(11), new Integer(13));

      // if the rebalancing happen, the height will change from 4 to 3
      int actualHeight = avl2.getHeight();
      if (actualHeight != 3) {
        fail("avl rotate does not work, the height dosen't change");
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 009: " + e.getMessage());
    }
  }

  /**
   * /** Insert more than three values in AVL tree and then check the root, left, and right keys to
   * see if rebalancing occurred. The last node is inserted in the left subtree of the right
   * subtree.
   * 
   */
  @Test
  void testAVL_010_insert_lastNode_in_leftSubtree_of_rightSubtree_complex_check_get() {
    try {
      avl2.insert(8, "8");
      if (!avl2.getKeyAtRoot().equals(8))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfRightChildOf(8).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(7, "7");
      if (!avl2.getKeyOfLeftChildOf(8).equals(7))
        fail("avl insert to left child of root does not work");

      avl2.insert(13, "13");
      if (!avl2.getKeyOfRightChildOf(10).equals(13))
        fail("avl insert to right child of node 10 does not work");

      avl2.insert(11, "11");
      Integer k = avl2.getKeyOfRightChildOf(8);
      if (!k.equals(11))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 7 as its left child and 11 as its right child
      // the left child of 11 should change to 10 and right child of 11 should be 13

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(7));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(11));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(11), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(11), new Integer(13));

      String exceptValue = "7";
      String actualValue = avl2.get(7);
      if (!exceptValue.equals(actualValue))
        fail("can't get the correct value");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 010: " + e.getMessage());
    }
  }

  /**
   * /** Insert more than three values in AVL tree and then check the root, left, and right keys to
   * see if rebalancing occurred. And then delete a node from AVL tree, and see if rebalancing
   * occurred
   * 
   */
  @Test
  void testAVL_011_insert_check_delete() {
    try {
      avl2.insert(8, "8");
      avl2.insert(10, "10");
      avl2.insert(7, "7");
      avl2.insert(5, "5");
      avl2.insert(6, "6");

      // IF rebalancing is working,
      // the tree should still have 8 at the root
      // but 6 as its left child and 10 as its right child
      // the left child of 6 should change to 5 and right child of 6 should be 7

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(6));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(8), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(6), new Integer(5));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(6), new Integer(7));

      avl2.remove(10);

      // IF rebalancing is working,
      // the tree have 6 at the root
      // but 5 as its left child and 8 as its right child
      // the left child of 8 should change to 7

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(6));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(6), new Integer(5));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(6), new Integer(8));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(8), new Integer(7));

      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(6))
        fail("avl rotate does not work");

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 011: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Pre-Order traversal order: 20-10-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  @Override
  void testBST_008_check_preOrder_for_not_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    try {
      avl2.insert(10, "1st key inserted");
      avl2.insert(20, "2nd key inserted");
      avl2.insert(30, "3rd key inserted");

      // expected PRE_Order 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(10);
      expectedOrder.add(30);

      // GET PRE-ORDER and check
      List<Integer> actualOrder = avl2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL_BST 008: " + e.getMessage());
    }
  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Post-Order traversal order: 10-30-20
   */
  @Test
  @Override
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {
    try {
      // insert 10-20-30 UNBALANCED
      avl2.insert(10, "1st key inserted");
      avl2.insert(20, "2nd key inserted");
      avl2.insert(30, "3rd key inserted");

      // expected Post-Order 10-30-20
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      expectedOrder.add(30);
      expectedOrder.add(20);

      // GET POST-ORDER and check
      List<Integer> actualOrder = avl2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL_BST 009: " + e.getMessage());
    }
  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Level-Order traversal order: 20-10-30 (FIXED ON 2/14/18)
   * 
   * @throws KeyNotFoundException
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  @Override
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
    try {
      avl2.insert(10, "1st key inserted");
      avl2.insert(20, "2nd key inserted");
      avl2.insert(30, "3rd key inserted");

      // expected traversal Order 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(10);
      expectedOrder.add(30);

      // GET traversal Order and check
      List<Integer> actualOrder = avl2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL_BST 010: " + e.getMessage());
    }

  }

}
