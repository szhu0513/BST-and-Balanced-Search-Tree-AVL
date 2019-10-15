import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: add imports as needed for your JUnit tests

/**
 * ABSTRACT super-class with DataStructureADT JUnit tests.
 * 
 * This class contains methods for testing the basic functionality of a DataStructureADT
 * implementation. Such a d.s. type was designed and tested in Program 1.
 * 
 * This class will now be the super-class of SearchTreeADTTest. This means that SearchTreeADTTest
 * inherit all of tests (public and protect methods) from DataStructureADTTest.
 * 
 * For Program 2, almost all tests from your p1 DataStructureADTTest class can be copied and run
 * here without changes. There are some required changes.
 * 
 * TODO: 1. Copy your DataStructureADTTest methods to this class 2. Edit your tests to handle the
 * changed names, types, and exception handling requirements for insert, remove, and get methods.
 * 
 * See @DataStructureADT for more details
 * 
 * NOTE: this class has changed the visibility of dataStructureInstance and added
 * dataStructureInstance2, and dataStructureInstance3.
 * 
 * dataStructureInstance is still a DataStructure<String,String>. dataStructureInstance2 is a
 * DataStructure<Integer,String>. dataStructureInstance3 is a DataStructure<Integer,Integer>. DO NOT
 * CHANGE THE TYPES, NAMES, OR VISIBLITY OF THOSE FIELDS
 * 
 * @author Debra Deppeler (deppeler@cs.wisc.edu)
 */
abstract class DataStructureADTTest {

  // CHANGED FROM P1: fields are protected (so they may be accessed from sub-classes)
  protected DataStructureADT<String, String> dataStructureInstance;

  // ADDED FROM P1: added another dataStructureInstance type <Integer,String>
  protected DataStructureADT<Integer, String> dataStructureInstance2;


  // CHANGED FROM P1: methods are protected (so they may be accessed from sub-classes)
  protected abstract DataStructureADT<String, String> createInstance();

  // ADDED FROM P1: added method to create another dataStructureInstance type <Integer,String>
  protected abstract DataStructureADT<Integer, String> createInstance2();

  @BeforeAll
  static void setUpBeforeClass() {
    // UNUSED - may be removed if not using
  }

  @AfterAll
  static void tearDownAfterClass() {
    // UNUSED - may be removed if not using
  }

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
    dataStructureInstance2 = createInstance2();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
    dataStructureInstance2 = null;
  }

  /**
   * Provided Utility Method for comparing List<K> with other List<K>
   * 
   * Helper assert method for comparing lists of various element types. List must have the same
   * number of elements, be of the same type, and have elements that are the same in the same order.
   * 
   * @param list1<?>
   * @param list2<?>
   */
  public void assertEquals(List<?> list1, List<?> list2) {
    assertTrue(list1 != null);
    assertTrue(list2 != null);
    assertTrue(list1.size() == list2.size());
    for (int i = 0; i < list1.size(); i++) {
      assertTrue(list1.get(i).equals(list2.get(i)));
    }
  }

  @Test
  void testDS00_empty_ds_size() {
    // It it works for one test, should work for all
    assertTrue(dataStructureInstance.numKeys() == 0);
    assertTrue(dataStructureInstance2.numKeys() == 0);
  }

  @Test
  void testDS01_insert_one_ds_size() throws IllegalNullKeyException {
    try {
      // It it works for one test, should work for all
      dataStructureInstance.insert("mykey1", "myvalue1");
      dataStructureInstance2.insert(2, "myvalue2");
      System.out.println(dataStructureInstance.numKeys());

      assertTrue(dataStructureInstance.numKeys() == 1);
      assertTrue(dataStructureInstance2.numKeys() == 1);

    } catch (DuplicateKeyException e) {
      e.printStackTrace();
      fail("Unexpected exception DS01: " + e.getMessage());
    }
  }

  /**
   * This method is going to insert one key,value pair and remove it, and check whether the size
   * will still be 0. If no fail occurs, it counts as a pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_zero()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // insert one key value pair into the data structure
    dataStructureInstance.insert("1", "CSHOO");
    // remove one key value pair from the data structure
    dataStructureInstance.remove("1");
    // if size changed, fail occurs
    if (dataStructureInstance.numKeys() != 0)
      fail("data structure should be empty after insert one and remove one, with size=0, but size="
          + dataStructureInstance.numKeys());

    // insert one key value pair into the data structure
    dataStructureInstance2.insert(1, "CSHOO");
    // remove one key value pair from the data structure
    dataStructureInstance2.remove(1);
    // if size changed, fail occurs
    if (dataStructureInstance2.numKeys() != 0)
      fail("data structure should be empty after insert one and remove one, with size=0, but size="
          + dataStructureInstance2.numKeys());
  }

  /**
   * This method is going to test whether will the RuntimeException throw after inserting a few
   * key,value pairs such that one of them has the same key as an earlier one.If no fail occurs, it
   * counts as a pass.
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    assertThrows(DuplicateKeyException.class, () -> {
      // insert a few key,value pairs such that one of them has the same key as an earlier one
      dataStructureInstance.insert("1", "CSHOO");
      dataStructureInstance.insert("2", "ABCDE");
      dataStructureInstance.insert("3", "EFGHI");
      dataStructureInstance.insert("3", "JKLMN");
      dataStructureInstance.insert("4", "OPQRS");
    });

    assertThrows(DuplicateKeyException.class, () -> {
      // insert a few key,value pairs such that one of them has the same key as an earlier one
      dataStructureInstance2.insert(1, "CSHOO");
      dataStructureInstance2.insert(2, "ABCDE");
      dataStructureInstance2.insert(3, "EFGHI");
      dataStructureInstance2.insert(3, "JKLMN");
      dataStructureInstance2.insert(4, "OPQRS");
    });
  }

  /**
   * This method is going to insert some key,value pairs, then try removing a key that was not
   * inserted.The remove() should throw KeyNotFoundException. If no fail occurs, it counts as a
   * pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void test04_KeyNotFoundException_is_thorw()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    assertThrows(KeyNotFoundException.class, () -> {
      // insert a few key,value pairs and then remove a node with the key that is not insert
      dataStructureInstance.insert("1", "CSHOO");
      dataStructureInstance.insert("2", "ABCDE");
      dataStructureInstance.insert("3", "EFGHI");
      dataStructureInstance.remove("4");
    });

    assertThrows(KeyNotFoundException.class, () -> {
      // insert a few key,value pairs and then remove a node with the key that is not insert
      dataStructureInstance2.insert(1, "CSHOO");
      dataStructureInstance2.insert(2, "ABCDE");
      dataStructureInstance2.insert(3, "EFGHI");
      dataStructureInstance2.remove(4);
    });
  }

  /**
   * This method is going to test if the size will change to 5, after inserting 5 key,value pairs.If
   * no fail occurs, it counts as a pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test05_insert_5() throws IllegalNullKeyException, DuplicateKeyException {
    // insert 5 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
    }
    if (dataStructureInstance.numKeys() != 5)
      fail("data structure should have 50 items, but size = " + dataStructureInstance.numKeys());

    // insert 5 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance2.insert(i, Integer.toString(i));
    }
    if (dataStructureInstance2.numKeys() != 5)
      fail("data structure should have 50 items, but size = " + dataStructureInstance2.numKeys());
  }

  /**
   * This method is going to test if the size will change to 25, after inserting 50 key,value pairs
   * and remove 25 pairs. If no fail occurs, it counts as a pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void test06_insert_5_remove_2()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // insert 50 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
    }
    // remove 50 key value pairs into the data structure
    for (int i = 0; i < 2; i++) {
      dataStructureInstance.remove(Integer.toString(i));
    }
    if (dataStructureInstance.numKeys() != 3)
      fail("data structure should have 3 items, but size = " + dataStructureInstance.numKeys());

    // insert 50 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance2.insert(i, Integer.toString(i));
    }
    // remove 50 key value pairs into the data structure
    for (int i = 0; i < 2; i++) {
      dataStructureInstance2.remove(i);
    }
    if (dataStructureInstance2.numKeys() != 3)
      fail("data structure should have 3 items, but size = " + dataStructureInstance2.numKeys());
  }

  /**
   * This method is going to test if the size will change, after inserting 5 key,value pairs and
   * remove 5 pairs. If no fail occurs, it counts as a pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void test07_insert_5_remove_5()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // insert 500 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
    }
    // remove 500 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance.remove(Integer.toString(i));
    }
    if (dataStructureInstance.numKeys() != 0)
      fail("data structure should have 0 items, but size = " + dataStructureInstance.numKeys());

    // insert 500 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance2.insert(i, Integer.toString(i));
    }
    // remove 500 key value pairs into the data structure
    for (int i = 0; i < 5; i++) {
      dataStructureInstance2.remove(i);
    }
    if (dataStructureInstance2.numKeys() != 0)
      fail("data structure should have 0 items, but size = " + dataStructureInstance2.numKeys());
  }

  /**
   * This method is going to test the contains() method.If no fail occurs, it counts as a pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  void test08_insert_contains() throws IllegalNullKeyException, DuplicateKeyException {
    // insert one key value pair into the data structure
    dataStructureInstance.insert("1", "CSHOO");
    if (dataStructureInstance.contains("1") == false)
      fail("data structure should containds the value which pairs to the specific key");

    // insert one key value pair into the data structure
    dataStructureInstance2.insert(1, "CSHOO");
    if (dataStructureInstance2.contains(1) == false)
      fail("data structure should containds the value which pairs to the specific key");
  }

  /**
   * This method is going to test the get() method by inserting two pairs and get the value with the
   * key not null.If no fail occurs, it counts as a pass.
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  void test09_insert_get()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // insert two key value pair into the data structure
    dataStructureInstance.insert("1", "CSHOO");
    dataStructureInstance.insert("2", "ABCDE");
    if (!dataStructureInstance.get("1").equals("CSHOO"))
      fail("data structure should return the value pairs to the specific key, but the value it "
          + "return is " + dataStructureInstance.get("1"));

    // insert two key value pair into the data structure
    dataStructureInstance2.insert(1, "CSHOO");
    dataStructureInstance2.insert(2, "ABCDE");
    if (!dataStructureInstance2.get(1).equals("CSHOO"))
      fail("data structure should return the value pairs to the specific key, but the value it "
          + "return is " + dataStructureInstance2.get(1));
  }

  /**
   * This method is going to test the get() method with null key.If no fail occurs, it counts as a
   * pass.
   */
  @Test
  void test10_get_null() {
    assertThrows(IllegalArgumentException.class, () -> {
      // insert four key value pairs into the data structure
      dataStructureInstance.insert("1", "CSHOO");
      dataStructureInstance.insert("2", "ABCDE");
      dataStructureInstance.insert("3", "ABCDE");
      dataStructureInstance.insert("4", "EFGHI");
      dataStructureInstance.get(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      // insert four key value pairs into the data structure
      dataStructureInstance2.insert(1, "CSHOO");
      dataStructureInstance2.insert(2, "ABCDE");
      dataStructureInstance2.insert(3, "ABCDE");
      dataStructureInstance2.insert(4, "EFGHI");
      dataStructureInstance2.get(null);
    });
  }

}
