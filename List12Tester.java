
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Before; 

/**
 * List12Tester provides methods to test the following methods of List12:
 * <ul>
 * <li> public boolean add(E o) </li> 
 * <li> public void add(int index, E element) </li> 
 * <li> public boolean contains(Object o) </li>
 * <li> public E get(int index) </li>
 * <li> public Iterator<E> iterator() </li>
 * <li> public boolean remove(Object o) </li>
 * <li> public E remove(int index) </li>
 * <li> public E set(int index, E element) </li> 
 * <li> public int size() </li>
 * </ul> 
 * All tests will be performed on a List12 with integer values 1-10 in that 
 * order. Traversals are from left to right in ascending order increasing
 * the index by 1, and should start at index 0 unless otherwise specified.
 * 
 * @author Andrew Tsun, login: cs12soo
 * @version Programming Assignment #1
 * 
 * @see List12
 * @see LinkedList
 */
public class List12Tester extends junit.framework.TestCase{
   
   private List12<Integer> testList; // list to be tested
   
   /**
    * Instantiate the testList object. 
    */
   public List12Tester(){
      testList = new List12<Integer>(); // instantiate list
   }
   
   /** 
    *  Initializes the List12 of integers to be a list of numbers from 1 to 10. 
    *  @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception {
      for(int i = 1; i <= 10; i++){
         testList.add(new Integer(i));
      }
   }
   
   /**
    *  Clears the List12 of integers to be an empty list.  
    */
   public void tearDown() throws Exception {
      testList = new List12<Integer>(); 
   }
   
   public void testClone(){
      List12<Integer> copy = new List12<Integer>();
      try{
         copy = (List12<Integer>)testList.clone();
      }
      catch (ClassCastException e) { System.out.print("ClassCastExceptionE");
         fail("Object not cast successfully");}
      catch(Exception e){System.out.print("Other  Error"); 
         fail( "Object not instantiated successfully");}
      for(int i = 0; i < testList.size(); i++){
         assertEquals(testList.get(i), copy.get(i));
      }
   }
   
   // Test 1st Add method.
   
   /**
    * Tests to see if something can be successfully added to the beginning of the list. 
    */
   public void testBoolAddBeginning(){
      testList.add(new Integer (5));
      assertEquals(new Integer (5), testList.get(10)); 
   }
   
   /**
    *  Tests that adding Integers to the end of a List12 of Integers 
    *  is successful for integers from 11 to 20. 
    */
   public void testBoolAddReturn(){
      for (int i = 11; i <= 20; i++)
         // add(E o) returns true if successful
         assertTrue(testList.add(new Integer(i))); 
   }
   
   /**
    *  Tests that once an Integer is added to the end of a List12 of Integers,
    *  the List12 contains that Integer. Tested on integers from 11 to 20. 
    */
   public void testBoolAddContains(){
      for (int i = 11; i <= 20; i++){
         Integer insertion = new Integer(i); // Create List12 to be inserted.
         testList.add(insertion); // Add Integer to end of List12
         assertTrue(testList.contains(insertion)); 
         // See if added Integer is in List12 
      }
   }
   
   /** 
    *  Tests that once an integer is added by the boolean version of add, 
    *  it is appended to the end of the list. Tested on integers from 11 to 20.
    */
   public void testBoolAddGet(){
      for(int i = 11; i <= 20; i++){
         testList.add(new Integer(i)); // Add the Integer to List12
         assertEquals(new Integer(i), testList.get(i-1)); 
         // Make sure the Integer is added to the end
      }
   }
   
   /**
    * Tests to see if the size increases when adding Integers to the end of the list.
    * Tested for integers from 11 to 20. 
    */
   public void testBoolAddSize(){
      for(int i = 11; i <= 20; i++){
         int initSize = testList.size();
         testList.add(new Integer(i));
         assertEquals(testList.size(),initSize+1);
      }
   }
   
   /** 
    * Tests to see if null elements are able to added to List12. 
    * If not, a NullPointerException should be thrown. 
    * @see NullPointerException
    */
   public void testBoolAddNull(){
      try{
         // If one can add null elements, add(null) should return true. 
         assertTrue(testList.add(null)); 
      }
      catch(NullPointerException e){
         fail(); // Fail if NullPointerException is thrown.
      }
   }
   
   /* Test void add(int Index, E element) */
   
   /**
    * Tests to see that if an element is inserted anywhere into the List12
    * is contained in the List12 itself. Test for integers from 11 to 20, 
    * positions 1-10.  
    */
   public void testVoidAddContains(){
      for(int i = 11; i <= 20; i++){ 
         testList.add((i-10), new Integer(i)); // Add integer at position integer - 10.
         assertTrue(testList.contains(new Integer(i))); // See if list contains integer. 
      }
   }
   
   /**
    * Tests to see that if an element is inserted anywhere into the List12,
    * it is inserted into the coorect index. 
    * Tests for integers from 11 to 20, adds to positions 1-10.  
    */
   public void testVoidAddGet(){
      for(int i = 11; i <= 20; i++){
         Integer initial = testList.get(i-10); // Create integer
         Integer add = new Integer(i);
         testList.add((i-10), add); // Insert integer
         assertEquals(testList.get(i-10), add); 
         // check that integer is in correct spot
         assertNotSame(testList.get(i-10),initial); 
         /* Make sure inserted integer does not have same index as the 
          * integer at the same index before. 
          */  
      }
   }
   
   /**
    *  Tests that the size incrases by 1 when an element is added anywhere to 
    *  the List12. Tests for integers from 11 to 20, adds to positions 1 to 10.  
    */
   public void testVoidAddSize(){
      for(int i = 11; i <= 20; i++){
         int initSize = testList.size(); // Create integer
         testList.add((i-10), new Integer(i)); // Add integer
         assertEquals(testList.size(),initSize+1); // Test for size change
      }
   }
   
   /**
    *  Tests that a null value can be added anywhere, and that it is in the 
    *  correct spot it was added to. Adds null to positions 0 to 9. 
    *  @see NullPointerException
    */
   public void testVoidAddNull(){
      for(int i = 0; i < 9; i++){
         try{
            testList.add(i, null); // Add the null value
         }
         catch(NullPointerException e){
            fail(); // If cannot add null contents, fail the test.
         }
         assertEquals(testList.get(i), null); // Ensure null add to correct spot
      }
   }
   
   /** 
    *  Tests to see that indices that are not in the lower and upper bounds 
    *  of the size indices throw an IndexOutOfBoundsException as expected. Also
    *  tests that objects can be successfully added to indices within the bounds.
    *  Tests for indices betweeh -10 and 20, adds 0 to each of these indices.
    *  @see IndexOutOfBoundsException   
    */
   public void testVoidAddIndexOutOfBoundsException(){
      for(int i = -10; i < 20; i++){
         try{
            testList.add(testList.size()+i, new Integer(0)); // Add 0.
            System.out.println("Test Void Add Index Bounds Exception ran on " + i);
         }
         // Let test continue if proper exception thrown
         catch(IndexOutOfBoundsException e){} 
      }
   }
   
   /**
    *  Tests to see that indices of elements are shifted correctly when 
    *  inserting an element into a position other than the beginning and the
    *  end of the list. Adds integers from 11 to 20 at positions 1 to 10.  
    */
   public void testVoidAddCheckShift(){
      for(int i = 1; i < 10; i++){
         // Make a copy, since two references can point to the same object. 
         List12<Integer> comparison = (List12<Integer>) testList.clone();  
         testList.add(i, new Integer(i + 10));
         for(int j = i; j < comparison.size(); j++){ // Assert correct alignment.
            assertEquals(comparison.get(j), testList.get(j+1));
         }
      }
   }
   
   /* Test contains method */
   
   /**
    *  Tests to see that integers 1-10 are in List12. 
    *  Also tests that integers 11-20 are NOT in list12. 
    */
   public void testContains(){
      for(int i = 1; i <= 10; i++){
         assertTrue(testList.contains(new Integer(i)));
         assertFalse(testList.contains(new Integer(i+10)));
      }
   }
   
   /**
    *  Adds a null value to the List12. 
    *  Tests to see if contains method can detect a null value. 
    *  @see NullPointerException
    */
   public void testContainsNull(){
      testList.add(null); // Add null object to List12
      try{ assertTrue(testList.contains(null)); } // See if null is contained.
      catch(NullPointerException e){ fail(); } // Fail if exception thrown.
   }
 
   /* Test get method */
   
   /** 
    *  Tests to see that if a value is added anywhere into an array,
    *  the value is retrievable. Test on
    */
   public void testGetAdd(){
      for(int i = 1; i <= 10; i++){
         Integer insert = new Integer(i); 
         testList.add(i, insert); 
         // Ensure integer retrieved at inserted index is correct one. 
         assertEquals(testList.get(i), insert); 
      }
   }
   
   /**
    *  Tests that indices that aren't in the range of indices of List12 throw
    *  an IndexOutOfBoundsException. Also tests for accessibility of indices
    *  within the bounds of List12. 
    *  @see IndexOutOfBoundsException 
    */
   public void testGetIndexOutOfBoundsException(){
      for(int i = -10; i <= 20; i++){
         try{ testList.get(i); } // Access at index.
         // Continue test if exception thrown.
         catch(IndexOutOfBoundsException e){} 
      }
   }
   
   // Test E remove (int index) Method
   
   /** 
    *  Tests to see that an element returned by the remove method 
    *  is the same as the element that was previously there before
    *  it was removed. 
    */
   public void testERemoveReturn(){
      int rmIndex1 = (int)(Math.random()*10);
      Integer i = testList.get(rmIndex1);
      assertEquals(i, testList.remove(rmIndex1));
   }
   
   /**
    *  Tests to see that if an element is removed from any index,
    *  that element is no longer in the List12. Tested on every element
    *  in List12, traverse to left instead of right. 
    */
   public void testERemoveContains(){
      for(int i = testList.size()-1; i >= 0; i--){
         Integer a = testList.remove(i); // Store removed integer.
         assertFalse(testList.contains(a)); // Check if integer still contained.
      }
   }
   
   /**
    *  Tests to see that if an element is removed from any index,
    *  the size of List12 is changed.  
    */
   public void testERemoveSize(){
      for(int i = testList.size()-1; i >= 0; i--){
         int initial = testList.size(); // Record initial size. 
         testList.remove(i); // Remove by index.
         assertEquals(initial-1, testList.size()); // Check for size change.  
      }
   }
   
   /**
    *  Tests to see that an IndexOutOfBoundsException is thrown when trying
    *  to remove elements from indices that don't exist in List12. Also 
    *  checks that for indices in List12, the corresponding object
    *  can be removed.  
    *  @see IndexOutOfBoundsException 
    */
   public void testERemoveIndexOutOfBoundsException(){
      for (int i = -1; i >= -10; i--){
         try{testList.remove(i);}
         // Continue test if exception is thrown.
         catch(IndexOutOfBoundsException e){} 
      }
   }
   
   /**
    *  Tests to see that if an object is removed from any index, 
    *  the indices of elements to the right are shifted properly.
    */
   public void testERemoveObjectCheckShift(){
      for(int i = 1; i <= 3; i++){
         // Remove a random object, pick its index. 
         int randIndex = (int)(Math.random()*testList.size());
         // Make copy of List12 for comparison. 
         List12<Integer> comparison = (List12<Integer>)testList.clone(); 
         testList.remove(randIndex); // Remove by index
         // Check indices to right
         for (int j = randIndex; j < testList.size(); j++){
            assertEquals(testList.get(j),comparison.get(j+1)); 
         }
      }
   }
   
   /* Test Iterator<E> iterator*/
   
   /**
    *  Tests that the iterator knows there is more elements in the filled
    *  List12. Creates an empty List12, and tests that the iterator knows
    *  there are no more elements. 
    */
   public void testIteratorHasNextFullEmpty(){
       assertTrue(testList.iterator().hasNext());
       testList = new List12<Integer> ();
       assertFalse(testList.iterator().hasNext()); 
   }
   
   /**
    *  Tests that the iterator can distinguish that more elements are 
    *  available as it traverses the array. 
    */
   public void testIteratorHasNextTraversal(){
      Iterator<Integer> itr = testList.iterator(); // get Iterator. 
      int index = 0; 
      while(itr.hasNext()){ // Loop until end of array
         itr.next(); // move to next element.
         // Assert no more elements available if at end of file.
         if (index == testList.size()- 1)  
            assertFalse(itr.hasNext()); 
         else 
            assertTrue(itr.hasNext()); 
         index++;
      }
   }
   
   /**
    * Tests that the next function in the iterator traverses values of 
    * List12 in the correct order. Test on all elements in List12.
    */
   public void testIteratorNextReturn(){
      Iterator<Integer> itr = testList.iterator(); // get Iterator. 
      int i = 0; // index of values
      // compare traversal with iterator with accessing by increasing index
      while(itr.hasNext()) 
         assertEquals(itr.next(), testList.get(i++)); 
   }
   
   /**
    *  Tests that if there are no more elements, the NoSuchElementException
    *  is thrown by the iterator.
    *  @see NoSuchElementException 
    *  @see Iterator    
    */
   public void testIteratorNextNoSuchElementException(){
      Iterator<Integer> itr = testList.iterator(); // get Iterator.
      try{
         while(true) // Infinite loop
            itr.next(); // Keep Traversing
      }
      catch(NoSuchElementException e){} // End traversal. 
   }
   
   /**
    *  Tests to see that the last element removed from the List12
    *  through the use of the Iterator's method is indeed the last
    *  element removed, and that it is not contained in the 
    *  List12 anymore.  
    */
   public void testIteratorRemove(){
      Iterator<Integer> itr = testList.iterator(); // get Iterator
      while(itr.hasNext()){
         itr.next(); // Traverse iterator
         Integer removed = testList.get(0); // Get item to be removed
         itr.remove(); // Remove item. 
         // Assert item not in List12 anymore
         assertFalse(testList.contains(removed)); 
      }
   }
   
   
   /**
    *  Tests that an IllegalStateException is thrown if next of iterator  
    *  method is not called yet.
    *  @see IllegalStateException
    */
   public void testIteratorRemoveIllegalStateExceptionNotNext(){
      Iterator<Integer> itr = testList.iterator(); 
      try{ // Call remove before next.
         itr.remove(); 
         itr.next(); 
      }
      catch(IllegalStateException e){} // Pass if exception thrown.
   }
   
   /**
    * Tests that an IllegalStateException is thrown if remove is called more
    * than once after each next.
    * @see IllegalStateException
    */
   public void testIteratorRemoveIllegalStateExceptionOverCall(){
      Iterator<Integer> itr = testList.iterator();
      try{
         while(itr.hasNext()){ // Attempt for each element in List12.
            itr.next(); // Call next element
            itr.remove(); // Remove last element
            itr.remove(); // Try to remove last element again.
         }
      }
      catch(IllegalStateException e){} // End test if exception thrown.
   }
   
   /* Test boolean remove(Object o) */
   
   /** 
    *  Tests to see that objects within the List12 can be
    *  successfully removed, and that objects not within
    *  the List12 cannot be removed at all. Integers 1-10 should 
    *  be removable, and 11-20 shouldn't be.  
    */
   public void testBoolRemoveReturn(){
      for(int i = 20; i >= 1; i--){
         Integer rm = new Integer(i);
         if (i > 10) // Indices out of bounds shouldn't be removable
            assertFalse(testList.remove(rm));
         else // Indices in bounds should be removable
            assertTrue(testList.remove(rm));
      }
   }
   
   /**
    *  Tests to see that if a specific object is removed,
    *  it is not contained in List12 anymore.  Tested on integers 
    *  from 1 to 10.  
    */
   public void testBoolRemoveContains(){
      for (int i = 1; i <= 10; i++){
         Integer rm = new Integer(i);
         testList.remove(rm); // Remove by object.
         // Check if object removed is still contained.
         assertFalse(testList.contains(rm));  
      }
   }
   
   /**
    *  Tests to see that the size of List12 changes when 
    *  a specific object is removed. 
    */
   public void testBoolRemoveSize(){
      for (int i = 1; i <= 10; i++){
         int initial = testList.size(); // Record initial size. 
         Integer rm = new Integer(i);
         testList.remove(rm); // Remove by object.
         assertEquals(initial-1, testList.size()); // Check for size change. 
      }
   }
   
   /**
    * Tests to see if null objects can be removed from List12.
    * If not, a NullPointerException is thrown, and the test fails. 
    * @see NullPointerException 
    */
   public void testBoolRemoveNull(){
      int rmIndex1 = (int)(Math.random()*9); // generate random index. 
      testList.add(rmIndex1, null); // add null object @ random index
      try{ assertTrue(testList.remove(null)); } // try to remove it    
      catch(NullPointerException e){
         fail(); // fail test if NullPointerException is thrown. 
      }
   }
   
   /** 
    *  Tests to see that if a specific object is removed, the indices to the 
    *  right shift properly. 
    */
   public void testBoolRemoveObjectCheckShift(){
      for(int i = 1; i <= 3; i++){
         Integer random = (int)(Math.random()*10); // Remove a random integer. 
         if(!testList.contains(random)) // If integer not contained, skip test.
            continue;                          
         int randIndex = 0; 
         for (randIndex = 0; randIndex < testList.size(); randIndex++){
            if (testList.get(randIndex).equals(random))
               break; 
         }
         // Store a copy of List12 for comparison. 
         List12<Integer> comparison = (List12<Integer>)testList.clone(); 
         testList.remove(random); // Remove by object. 
         // Check indices on right.
         for (int k = randIndex; k < testList.size(); k++){  
            assertEquals(testList.get(k),comparison.get(k+1)); 
         }
      }
   }
   
   // Test Set Method:
   
   /**
    *  Test to see if set returns the previous value of an entry
    *  at a given index in List12. 
    */
   public void testSetReturn(){
      for(int i = 0; i < testList.size(); i++){
         Integer oldValue = testList.get(i); // Record previous value
         // Check if previous value is same one. 
         assertEquals(testList.set(i, oldValue), oldValue); 
      }
   }
   
   /**
    *  Test to see if a value at a given index is changed, the new value
    *  is contained in List12. New values are set at 11 higher than the
    *  previous value. 
    */
   public void testSetContains(){
      for (int i = 0; i < testList.size(); i++){
         Integer newValue = new Integer(testList.get(i).intValue() + 11);
         testList.set(i, newValue); 
         assertTrue(testList.contains(newValue)); // Check for containment
      }
   }
   
   /**
    *  Test to see if the new value is set properly, and the new value at the
    *  index is not the same as the old one, and same as the new value the 
    *  set function is trying to set to. 
    */
   public void testSetResult(){
      for (int i = 0; i < testList.size(); i++){
         Integer oldValue = testList.get(i);
         Integer newValue = new Integer(testList.get(i).intValue() + 11);
         testList.set(i, newValue);
         assertFalse(testList.get(i) == oldValue); // Shouldn't equal old value.
         assertEquals(testList.get(i), newValue); // Should equal new value. 
      }
   }
   
   /**
    * Tests to see that when setting Integers at indices outside List12, 
    * an IndexOutOfBoundsException is thrown. 
    * @see IndexOutOfBoundsException
    */
   public void testSetIndexOutOfBoundsException(){
      for(int i = -10; i <= 20; i++){
         try{
            Integer newValue = new Integer(testList.get(i).intValue() + 11);
            testList.set(i, newValue);
         }
         catch(IndexOutOfBoundsException e){}
      }
   }
   
   /* Test Size Method*/
   
   /**
    * Tests to see that when an object is appeneded to the end of the List12,
    * the size is increased by 1.
    */
   public void testSizeBoolAdd(){
      for(int i = 11; i < 20; i++){
         int initial = testList.size();
         testList.add(i);
         assertEquals(testList.size(), initial+1);
      }
   }
   
   /**
    * Tests to see that when an object is appended anywhere within List12,
    * the size should be increased by 1.
    */
   public void testSizeVoidAdd(){
      for(int i = 11; i < 20; i++){
         int initial = testList.size();
         testList.add((i-10), new Integer(i));
         assertEquals(testList.size(), initial+1);
      }
   }
   
   /**
    *  Tests to see if a specific object is removed from List12,
    *  the size should be decreased by 1.
    */
   public void testSizeBoolRemove(){
      for(int i = 1; i <= 10; i++){
         int initial = testList.size();
         testList.remove(new Integer(i)); // Remove integer
         assertEquals(testList.size(), initial -1);
      }
   } 
   
   /**
    *  Tests to see that if an object at a specific index is removed
    *  from List12, the size should be decreased by 1. 
    */
   public void testSizeERemove(){
      while (testList.size() > 0){ 
         int rand = (int)(Math.random()*testList.size()); // Record random index. 
         int initial = testList.size(); // Record initial size
         testList.remove(rand); // Remove element at random index.
         assertEquals(testList.size(), initial -1); // Check proper size change.
      }
   }
   
   /**
    *  Tests to see that if i elements are added to an empty list12,
    *  the list should be i elements in size.
    *  Tested for list12 of sizes 1-10. 
    */
   public void testSizeReturn(){
      for(int i = 1; i <= 10; i++){
         testList = new List12<Integer>(); // Empty list
         for(int j = 1; j <= i; j++) // Add i elements to list
            testList.add(j);
         assertEquals(testList.size(), i); // Check for i elements in List12
      }
   }
   
   /**
    *  Tests that if the list is cleared, the size should be 0.
    */
   public void testSizeEmpty() {
      testList = new List12<Integer> (); 
      assertEquals(testList.size(), 0); 
   }
}
