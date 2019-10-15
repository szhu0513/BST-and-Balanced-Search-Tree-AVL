/////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ///////////////////////
// Title: P2_BST and Balanced Search Tree AVL
// Files: BSTTest.java
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

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is going to test that binary search tree operations work
 * 
 * @author Shiyu Zhu
 *
 */
public class BSTTest extends DataStructureADTTest {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructureInstance = bst = createInstance();
    dataStructureInstance2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = bst = null;
    dataStructureInstance2 = bst2 = null;
  }

  /**
   * This method is going to create an instance with the type of key is string, and type of the
   * value is String
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /**
   * This method is going to create an instance with the type of key is Integer, and type of the
   * value is String
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each of the four
   * traversal orders.
   */
  @Test
  void testBST_001_empty_traversal_orders() {
    try {

      List<String> expectedOrder = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 001: " + e.getMessage());
    }

  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for each of the four
   * traversal orders.
   */
  @Test
  void testBST_002_check_traversals_after_insert_one() {

    try {

      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      bst2.insert(10, "ten");
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      System.out.println("   EXPECTED: " + expectedOrder);
      System.out.println("   In Order: " + inOrder);
      System.out.println("  Pre Order: " + preOrder);
      System.out.println(" Post Order: " + postOrder);
      System.out.println("Level Order: " + levelOrder);

      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_003_check_inOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Pre-Order traversal order: 20-10-30
   */
  @Test
  void testBST_004_check_preOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected PRE_Order 20 10 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20); // V
      expectedOrder.add(10); // L
      expectedOrder.add(30); // R

      // GET PRE-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }
  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Post-Order traversal order: 10-30-20
   */
  @Test
  void testBST_005_check_postOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected Post-Order 10 30 20
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(30); // R
      expectedOrder.add(20); // V

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }
  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 Level-Order traversal order: 20-10-30
   */
  @Test
  void testBST_006_check_levelOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected traversal Order 20-10-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20); // level one
      expectedOrder.add(10); // the left most of level two
      expectedOrder.add(30); // level two

      // GET traversal Order and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }
  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_007_check_inOrder_for_not_balanced_insert_order() {
    // insert 10-20-30 UNBALANCED
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      expectedOrder.add(20);
      expectedOrder.add(30);

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Pre-Order traversal order: 10-20-30
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order()
      throws IllegalNullKeyException, DuplicateKeyException {
    // insert 10-20-30 UNBALANCED
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected PRE_Order 10-20-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      expectedOrder.add(20);
      expectedOrder.add(30);

      // GET PRE-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }


  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Post-Order traversal order: 30-20-10
   */
  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {
    try {
      // insert 10-20-30 UNBALANCED
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected Post-Order 30-20-10
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(30);
      expectedOrder.add(20);
      expectedOrder.add(10);

      // GET POST-ORDER and check
      List<Integer> actualOrder = bst2.getPostOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }
  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 10-20-30 Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
   * 
   * @throws KeyNotFoundException
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
    // insert 10-20-30 UNBALANCED
    try {
      bst2.insert(10, "1st key inserted");
      bst2.insert(20, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected traversal Order 10-20-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      expectedOrder.add(20);
      expectedOrder.add(30);

      // GET traversal Order and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 010: " + e.getMessage());
    }
  }

  /**
   * Test that if the get method work good after insert, fail if the value return by get method
   * doesn't equal to the value that expected to return
   */
  @Test
  void testBST_011_insert_then_check_get() {
    try {
      // insert 20-10-30 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected value
      String expectedValue = "2nd key inserted";

      // get value returned by get method and check
      String valueReturn = bst2.get(10);
      if (!valueReturn.equals(expectedValue))
        fail("should return the value pairs to the specific key, but the value it return is "
            + valueReturn);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 011: " + e.getMessage());
    }
  }

  /**
   * Test the height method after insert, fail if the height return doesn't equal to the expected
   * height
   */
  @Test
  void testBST_012_insert_then_check_height() {
    try {
      // insert 20-10-30-5-15-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(5, "4rd key inserted");
      bst2.insert(15, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");

      // expected Height
      int expectedHeight = 3;

      // get value returned by get method and check
      int heightReturn = bst2.getHeight();
      if (heightReturn != expectedHeight)
        fail("should return the height of BST which is 3, but the value it return is "
            + heightReturn);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 012: " + e.getMessage());
    }
  }

  /**
   * Test the contain method after insert, fail if the contain method doesn't return the correct
   * boolean value
   */
  @Test
  void testBST_013_insert_then_check_contain() {
    try {
      // insert 20-10-30 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // check the contain method
      if (bst2.contains(10) != true)
        fail("should return true, but the value it return is " + bst2.contains(10));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 013: " + e.getMessage());
    }
  }

  /**
   * Test that if the get method throw KeyNotFoundException after insert and remove method. Fail if
   * the get method doesn't throw exception
   */
  @Test
  void testBST_014_insert_remove_then_check_get() {
    assertThrows(KeyNotFoundException.class, () -> {
      // insert 20-10-30-5-15-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(5, "4rd key inserted");
      bst2.insert(15, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");
      // remove the node with key 10
      bst2.remove(10);
      // check to see if the BST still can get the value with key 10
      bst2.get(10);
    });
  }

  /**
   * Test that the level order after remove node with two child is correct or not. Fail if the level
   * order after remove doesn't equal to the expected list
   */
  @Test
  void testBST_015_insert_remove_node_with_two_child() {
    try {
      // insert 20-10-30-5-15-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(5, "4rd key inserted");
      bst2.insert(15, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");

      // remove the node with key 10(the node with two child)
      bst2.remove(10);

      // expected level-Order 20-5-30-15-25-35
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(5);
      expectedOrder.add(30);
      expectedOrder.add(15);
      expectedOrder.add(25);
      expectedOrder.add(35);

      // get the level order after remove and then check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 015: " + e.getMessage());
    }
  }

  /**
   * Test that the level order after remove the leaf node is correct or not. Fail if the level order
   * after remove doesn't equal to the expected list
   */
  @Test
  void testBST_016_insert_remove_leaf_node() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // remove the node with key 10(the leaf node)
      bst2.remove(10);

      // expected level-Order 20-30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(30);

      // get the level order after remove and then check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 016: " + e.getMessage());
    }
  }

  /**
   * Test that the level order after remove the node with right child is correct or not. Fail if the
   * level order after remove doesn't equal to the expected list
   */
  @Test
  void testBST_017_insert_remove_node_with_right_child() {
    try {
      // insert 20-10-30-15-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(15, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");

      // remove the node with key 10(the node with right child)
      bst2.remove(10);

      // expected level-Order 20-15-30-25-35
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(15);
      expectedOrder.add(30);
      expectedOrder.add(25);
      expectedOrder.add(35);

      // get the level order after remove and then check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 017: " + e.getMessage());
    }
  }

  /**
   * Test that the level order after remove the node with left child is correct or not. Fail if the
   * level order after remove doesn't equal to the expected list
   */
  @Test
  void testBST_018_insert_remove_node_with_left_child() {
    try {
      // insert 20-10-30-5-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(5, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");

      // remove the node with key 10(the node with left child)
      bst2.remove(10);

      // expected level-Order 20-5-30-25-35
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(20);
      expectedOrder.add(5);
      expectedOrder.add(30);
      expectedOrder.add(25);
      expectedOrder.add(35);

      // get the level order after remove and then check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 018: " + e.getMessage());
    }
  }

  /**
   * Test the getKetAtRoot() method. Fail if the key doesn't equal to the expected key
   */
  @Test
  void testBST_019_insert_get_key_at_root() {
    try {
      // insert 20-10-30-5-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(5, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");

      // Expected key
      int expectedKey = 20;

      // get the key at root and then check
      int keyReturn = bst2.getKeyAtRoot();
      if (keyReturn != expectedKey)
        fail("should return the key of root in BST which is 20, but the value it return is "
            + keyReturn);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 019: " + e.getMessage());
    }
  }

  /**
   * Test the getKeyOfRightChildOf(K key) method. Fail if the key doesn't equal to the expected key
   */
  @Test
  void testBST_020_insert_get_key_of_right_child_of_a_node() {
    try {
      // insert 20-10-30-5-25-35 BALANCED
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");
      bst2.insert(5, "5rd key inserted");
      bst2.insert(25, "6rd key inserted");
      bst2.insert(35, "7rd key inserted");

      // Expected key
      int expectedKey = 35;

      // get the key at root and then check
      int keyReturn = bst2.getKeyOfRightChildOf(30);
      if (keyReturn != expectedKey)
        fail("should return the key of root in BST which is 35, but the value it return is "
            + keyReturn);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 020: " + e.getMessage());
    }
  }
}
