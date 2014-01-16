import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;

/**
 * The BoundedDequeTester tests each of the methods of the Deque12 class:
 * <ul>
 * <li> capacity() </li>
 * <li> size() </li>
 * <li> addFront() </li>
 * <li> addBack() </li>
 * <li> removeFront() </li>
 * <li> removeBack() </li>
 * <li> peekFront() </li>
 * <li> peekBack() </li>
 * </ul>
 * The tests will be run on a Deque12 with the lowercase letters of the 
 * English alphabet, in order from front to back, with a capacity of 52.
 * A separate Deque12 object is instantiated the same way, to check for 
 * changes made. Tests will include calling these methods in succession
 * to one another.
 * @author Andrew Tsun, login: cs12soo
 * @version Programming Assignment 3 
 * @see Deque12, junit.framework.TestCase
 */
public class BoundedDequeTester extends junit.framework.TestCase{

   // The Deque to be tested.
   private Deque12<Character> testDQ = new Deque12<Character>(52); 
   // The Deque to be used for checking for equality. 
   private Deque12<Character> checkDQ = new Deque12<Character>(52); 
   
   /**
    * Creates a new Deque12 with elements ordered from 'a' to 'z'. 
    * Also creates the copy to compare against. 
    * @throws Exception
    */
   @Before
   public void setUp()throws Exception {
      for(char letter = 'a'; letter <= 'z'; letter++){
         testDQ.addBack(new Character(letter));
      }
      for(char letter = 'a'; letter <= 'z'; letter++){
         checkDQ.addBack(new Character(letter));
      }
   }
   
   /**
    * Initializes the test Deque12 to a new Deque12 object, capacity 52,
    * clearing out any modifications tests may have performed.
    * @throws Exception 
    */
   @After
   public void tearDown() throws Exception {
      testDQ = new Deque12<Character>(52); 
      checkDQ = new Deque12<Character> (52); 
   }
   
   /* Test capacity() */
   
   /**
    * Tests to see that when constructing a Deque12 object,
    * the capacity is set correctly. List sizes are from 
    * 1 to 100. 
    */

   public void testCapacityConstructor(){
      for(int i = 1; i < 100; i++){ //100 times
         // Set the capacity to a random number
         int randomCapacity = (int)(Math.random()*1000 + i);
         // Create a new list with the capacity generated.
         testDQ = new Deque12<Character> (randomCapacity);
         // Make sure the capacity is the one generated. 
         assertEquals(randomCapacity, testDQ.capacity()); 
      }
   }
   
   /**
    * Tests to see that when a negative number is used in the constructor
    * an IllegalArgumentException is thrown. 
    */
   public void testCapacityIllegalArgumentException(){
      for(int i = 1; i < 100; i++){
         // Set the capacity to a random negative number
         int randomCapacity = -1* (int)(Math.random()*1000 + i); 
         // Attempt to instantiate a Deque12
         try{
            testDQ = new Deque12<Character>(randomCapacity); 
            fail(); // A Deque12 object should not have been created.
         }
         catch(IllegalArgumentException e){} 
         // This exception should have been thrown
      }
   }
   
   /**
    * Tests that after capacity() is called, it is unchanged. 
    */
   public void testCapacityElementsUnchanged(){
      testDQ.capacity();
      assertEquals(testDQ, checkDQ); 
   }
   
   /* Test size() */
   
   /**
    * Tests that the size of an empty Deque12 is 0, for
    * lists size 1 to 100. 
    */
   public void testSizeEmptyList(){
      for(int i = 1; i < 100; i++){
         int randomCapacity = (int)(Math.random()*1000 + 1);
         testDQ= new Deque12<Character>(randomCapacity); 
         assertEquals(testDQ.size(), 0); 
      }
   }
   
   /**
    * Tests that when size() is called, a Deque object 
    * remains unchanged. 
    */
         
   public void testSizePostCondition(){
      testDQ.size();
      assertTrue(testDQ.equals(checkDQ)); 
   }
   
   /**
    * Tests that when other methods of called, 
    * the size changes accordingly. Add functions should
    * increase the size of the Deque12 object by 1, 
    * remove functions should decrease the size of the Deque12
    * object by 1, and peek functions should not change the 
    * size of the Deque12. 
    */
   public void testSizeMethodChanges(){
      int initial = testDQ.size();
      // AddBack should increase size by 1
      testDQ.addBack(new Character('A'));
      assertEquals(testDQ.size(), initial + 1);
      initial++;
      // AddFront should increase size by 1
      testDQ.addFront(new Character('B'));
      assertEquals(testDQ.size(), initial + 1);
      initial++; 
      // RemoveFront should decrease size by 1
      testDQ.removeFront(); 
      assertEquals(testDQ.size(), initial - 1);
      initial--;
      // RemoveBack should decrease size by 1
      testDQ.removeBack(); 
      assertEquals(testDQ.size(), initial - 1);
      initial--;
      // PeekFront should not change size
      testDQ.peekFront();
      assertEquals(testDQ.size(), initial);
      // PeekBack should not change size
      testDQ.peekBack();
      assertEquals(testDQ.size(), initial);
      // Equals should not change size
      testDQ.equals(new Deque12<Character>(52));
      assertEquals(testDQ.size(), initial); 
   }
   
   /* Test addFront() */
   
   /**
    * Tests that when adding an object to the front of a Deque12, 
    * the object appears as the front item. 
    */
   public void testAddFrontPeekFront(){
      for(char letter = 'Z'; letter >= 'A'; letter--){
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addFront(new Character(letter)); // Add letter
         assertEquals(new Character(letter), testDQ.peekFront()); 
      }
   }
   
   /**
    * Tests that when adding an object to the front of a Deque12,
    * the size is increased by 1. 
    */
   public void testAddFrontSizeChange(){
      for(char letter = 'Z'; letter >= 'A'; letter--){
         int initialSize = testDQ.size(); 
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addFront(new Character(letter)); 
         assertEquals(testDQ.size(), initialSize + 1);
      }
   }
   
   /** 
    * Tests that when adding an object to the front of a Deque12,
    * it will return true if full capacity has not reached, 
    * and it will return false if full capacity has been reached. 
    */
   public void testAddFrontReturn(){
      // Add all uppercase and lowercase letters. 
      for(char letter = 'A'; letter <= 'z'; letter++){
         if(testDQ.size() < testDQ.capacity())
            assertTrue(testDQ.addFront(new Character(letter)));
         else
            assertFalse(testDQ.addFront(new Character(letter)));
      }
   }
   
   /**
    * Tests to see that when adding elements to the front of a Deque12,
    * the rest of the elements remain unchanged.
    */
   public void testAddFrontUnchanged(){
      // Create LinkedList for comparison.  
      LinkedList<Character> ref = new LinkedList<Character>();
      for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
         ref.add(new Character(letter2));
      }
      // Test add operation
      for(char letter = 'A'; letter <= 'Z'; letter++){
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addFront(new Character(letter)); // Perform add operation
         ref.add(0, new Character(letter)); // Update comparison index
         // As removing elements from the Deque12, make sure order correct.
         for(int refIndex = 0; refIndex < ref.size(); refIndex++){
            Character comparison = testDQ.peekFront(); 
            assertEquals(comparison, ref.get(refIndex));
            testDQ.removeFront();
         }
         // Reform the queue
         for(int refIndex2 = ref.size()-1; refIndex2 >= 0; refIndex2--){
            testDQ.addFront(ref.get(refIndex2));
         }
      }
   }
   
   /**
    * Tests to see that when trying to add elements to the front of a Deque12 
    * at full capacity, the Deque12 does not overwrite existing 
    * memebers of the queue. 
    */
   public void testAddFrontDoesNotOverwrite(){
      // Fill up the list to maximum capacity
      for(char letter = 'a'; letter <= 'z'; letter++){
         testDQ.addFront(new Character(letter));
      }
      // Attempt to add new elements to front, but front element stays same.
      for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
         Character front = testDQ.peekFront();
         testDQ.addFront(new Character(letter2));
         assertEquals(front, testDQ.peekFront()); 
      }
   }
   
   /**
    * Tests when adding null to a Deque12 that is not at full capacity, 
    * NullPointerException is thrown.
    * @see NullPointerException
    */
   public void testAddFrontNullPtrExceptionAddNull(){
      try{ 
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addFront(null); // Try to add null object
         fail(); // Should not be able to add null objects
      }catch(NullPointerException e){} // This exception should be thrown. 
   }
   
   /**
    * Tests that when adding null to the front of a full Deque12 won't result
    * in NullPointerException
    * @see NullPointerException
    */
   public void testAddFrontFullCapacityAddNull(){
      // Fill Deque12 to full capacity
      for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
         testDQ.addFront(new Character(letter2));
      }
      try{
         // Capacity should be equal to size
         assertEquals(testDQ.size(), testDQ.capacity());
         // Attempt to add a null object
         testDQ.addFront(null); 
      }
      catch(NullPointerException e){fail();} // Should not throw exception
   }
   
   /* Test addBack() */
   
   /**
    * Tests that when adding an object to the back of a Deque12, 
    * the object appears as the back item. 
    */
   public void testAddBackPeekBack(){
      // Add capital letters A to Z
      for(char letter = 'A'; letter <= 'Z'; letter++){ 
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addBack(new Character(letter)); // Add letter
         assertEquals(new Character(letter), testDQ.peekBack()); 
         // Check position 
      }
   }
   
   /**
    * Tests that when adding an object to the front of a Deque12,
    * the size is increased by 1. 
    */
   public void testAddBackSizeChange(){
      for(char letter = 'A'; letter <= 'Z'; letter++){
         int initialSize = testDQ.size();  
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addBack(new Character(letter));
         assertEquals(testDQ.size(), initialSize + 1);
      }
   }
   
   /** 
    * Tests that when adding an object to the front of a Deque12,
    * it will return true if full capacity has not reached, 
    * and it will return false if full capacity has been reached. 
    */
   public void testAddBackReturn(){
      for(char letter = 'A'; letter >= 'z'; letter++){
         if(testDQ.size() < testDQ.capacity())
            assertTrue( testDQ.addFront(new Character(letter)));
         else
            assertFalse( testDQ.addFront(new Character(letter)));
      }
   }
   
   /**
    * Tests that when adding to the back of a Deque12, the remaining
    * elements remain unchanged. 
    */
   public void testAddBackUnchanged(){
      // Create ArrayList for comparison.  
      ArrayList<Character> ref = new ArrayList<Character>();
      for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
         ref.add(new Character(letter2));
      }
      // Test add operation
      for(char letter = 'A'; letter <= 'Z'; letter++){
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addBack(new Character(letter)); // Perform add operation
         ref.add(new Character(letter)); // Update comparison index
         // As removing elements from the queue, make sure order correct.
         for(int refIndex = ref.size()-1; refIndex >= 0; refIndex--){
            Character comparison = testDQ.peekBack(); 
            assertEquals(comparison, ref.get(refIndex));
            testDQ.removeBack();
         }
         // Reform the queue
         for(int refIndex2 = 0; refIndex2 < ref.size(); refIndex2++){
            testDQ.addBack(ref.get(refIndex2));
         }
      }
   }
   
   /**
    * Tests when adding null to the back of a Deque12 that is not at full  
    * capacity, NullPointerException is thrown.
    * @see NullPointerException
    */
   public void testAddBackNullPtrExceptionAddNull(){
      try{
         assertTrue(testDQ.size() <= testDQ.capacity()); // Precondition
         testDQ.addBack(null);
         fail();
      }catch(NullPointerException e){}
   }
   
   /**
    * Tests that when adding null to the back of a full Deque12 will not result
    * in NullPointerException
    * @see NullPointerException
    */
   public void testAddBackFullCapacityAddNull(){
      for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
         testDQ.addBack(new Character(letter2));
      }
      try{
         assertEquals(testDQ.size(), testDQ.capacity());
         testDQ.addBack(null); 
      }
      catch(NullPointerException e){fail();}
   }
   
   /**
    * Tests to see that when trying to add elements to the back of a Deque12 
    * at full capacity, the Deque12 does not overwrite existing 
    * memebers of the queue. 
    */
   public void testAddBackDoesNotOverwrite(){
      // Fill up the list to maximum capacity
      for(char letter = 'a'; letter <= 'z'; letter++){
         testDQ.addBack(new Character(letter));
      }
      // Attempt to add new elements to front, but front element stays same.
      for(char letter2 = 'a'; letter2 <= 'z'; letter2++){
         Character front = testDQ.peekBack();
         testDQ.addBack(new Character(letter2));
         assertEquals(front, testDQ.peekBack()); 
      }
   }
   
   /* Test peekFront()
    * Note: peekFront tested against addFront, removeFront 
    * under their corresponding test methods. 
    */

   /**
    * Tests that when adding to the back, the front value will not change.
    */
   public void testPeekFrontAddBack(){
      while(testDQ.size() < testDQ.capacity()){
         char addChar = (char)(testDQ.size()- 27 + 'a');
         Character front = testDQ.peekFront(); 
         testDQ.addBack(new Character(addChar));
         assert(front.equals(testDQ.peekFront()));
      }
   }
   
   /**
    * Tests that when removing from the back, the front value will not change.
    */
   public void testPeekFrontRemoveBack(){
      while(testDQ.peekBack()!=null){
         Character front = testDQ.peekFront(); 
         testDQ.removeBack();
         if (front != null)
            assert(front.equals(testDQ.peekFront()));
         else 
            assertTrue(front == null);
      }
   }
   
   /**
    * Tests that when the size is 0, peekFront will return a null value
    */
   public void testPeekFrontReturnNull(){
      for (int i = 0; i < 100; i++){
         int randomCapacity = (int)(Math.random()*1000 + 1);
         testDQ= new Deque12<Character>(randomCapacity); 
         assertEquals(testDQ.size(), 0); // size has to be 0 to return null. 
         assertEquals(testDQ.peekFront(), null); 
      }
   }
   
   /**
    * Tests that when peekFront is called, the Deque12 remains unchanged.
    */
   public void testPeekFrontUnchanged(){
      testDQ.peekFront();
      assertEquals(testDQ, checkDQ);
   }
   
   /* Test peekBack()
    * Note: peekBack tested against addBack, removeBack 
    * under their corresponding test methods. 
    */
  
   /**
    * Tests that when adding to the front, the back value will not change.
    */
   public void testPeekBackAddFront(){
      while(testDQ.size() < testDQ.capacity()){
         char addChar = (char)(testDQ.size()- 27 + 'a');
         Character back = testDQ.peekBack(); 
         testDQ.addFront(new Character(addChar));
         assert(back.equals(testDQ.peekBack()));
      }
   }
   
   /**
    * Tests that when removing from the front, the back value will not change.
    */
   public void testPeekBackRemoveFront(){
      while(testDQ.peekBack()!=null){
         Character back = testDQ.peekBack(); 
         testDQ.removeFront();
         if (back != null)
            assert(back.equals(testDQ.peekBack()));
         else 
            assertTrue(back == null);
      }
   }

   /**
    * Tests that when the size is 0, peekBack will return a null value
    */
   public void testPeekBackReturnNull(){
      for (int i = 0; i < 100; i++){
         int randomCapacity = (int)(Math.random()*1000 + 1);
         testDQ= new Deque12<Character>(randomCapacity); 
         assertEquals(testDQ.size(), 0); // size has to be 0 to return null. 
         assertEquals(testDQ.peekBack(), null); 
      }
   }
   
   /**
    * Tests that when peekBack is called, the Deque12 remains unchanged.
    */
   public void testPeekBackUnchanged(){
      testDQ.peekBack();
      assertEquals(testDQ, checkDQ);
   }
   
   /* Test removeFront() */
   
   /**
    * Tests to see that when removing from the front, only the 
    * front element is removed. 
    */
   public void testRemoveFrontReturn(){
      while(testDQ.peekFront() != null){
         Character front = testDQ.peekFront();
         if(front != null)
            assertEquals(front, testDQ.removeFront());
         else
            assertTrue(null == testDQ.removeFront());
      }
   }
   
   /**
    * Tests to see that when removing elements from the front,
    * the remaining elements remain unchanged.
    */
   public void testRemoveFrontUnchanged(){
      for(char letter = 'a'; letter <= 'z'; letter++){
         assertEquals(new Character(letter), testDQ.removeFront());
      }
   }
   
   /** 
    * Test to see that when removing elements from the front,
    * the element no longer exists in the Deque12. 
    */
   public void testRemoveFrontPeekFront(){
      while(testDQ.peekFront()!=null){
         Character rm = testDQ.removeFront();
         if (rm != null)
            assertFalse(rm.equals(testDQ.peekFront()));
         else 
            assertTrue(rm == null);
      }
   }
   
   /**
    * Tests that null is returned when trying to remove from the back of
    * an empty Deque12. 
    */
   public void testRemoveFrontEmptyListNull(){
      testDQ = new Deque12<Character>(52);
      assertTrue(testDQ.removeFront()== null && testDQ.size() == 0); 
   }
   
   /**
    * Tests that size is decremented by 1 when removing from front.
    */
   public void testRemoveFrontSizeChange(){
      while(testDQ.peekFront() != null){
         int initial = testDQ.size(); 
         testDQ.removeFront();
         assertEquals(testDQ.size(), initial-1);
      }
   }
   
   /* Test removeBack()*/
   
   /**
    * Tests to see that when removing from the back, only the 
    * back element is removed. 
    */
   public void testRemoveBackReturn(){
      while(testDQ.peekBack() != null){
         Character back = testDQ.peekBack();
         if(back != null)
            assertEquals(back, testDQ.removeBack());
         else
            assertTrue(null == testDQ.removeBack());
      }
   }
   
   /** 
    * Test to see that when removing elements from the back,
    * the element no longer exists in the Deque12. 
    */
   public void testRemoveBackPeekBack(){
      while(testDQ.peekBack()!=null){
         Character rm = testDQ.removeBack();
         if (rm != null)
            assertFalse(rm.equals(testDQ.peekBack()));
         else 
            assertTrue(rm == null);
      }
   }
   
   /**
    * Tests that other elements remain unchanged while removing from back.
    */
   public void testRemoveBackUnchanged(){
      for(char letter = 'z'; letter >= 'a'; letter--){
         assertEquals(new Character(letter), testDQ.removeBack());
      }
   }
   
   /**
    * Tests that null is returned when trying to remove from the back of
    * an empty Deque12, and that size is 0 when this happens.  
    */
   public void testRemoveBackEmptyListNull(){
      testDQ = new Deque12<Character>(52);
      assertTrue(testDQ.removeBack()== null && testDQ.size() == 0); 
   }
   
   /**
    * Tests that size is decremented by 1 when removing from back.
    */
   public void testRemoveBackSizeChange(){
      while(testDQ.peekBack() != null){
         int initial = testDQ.size(); 
         testDQ.removeBack();
         assertEquals(testDQ.size(), initial-1);
      }
   }
   
   /*Test equals()*/
   
   /**
    * Tests that two Deques with equal size, same class type, and corresponding 
    * values are equal. Also happens to have equal capacities. 
    */
   public void testEqualsTrue(){
      Deque12<Character> testDQ2= new Deque12<Character>(52); 
      for(char letter = 'a'; letter <= 'z'; letter++){
         testDQ2.addBack(new Character(letter));
      }
      assertTrue(testDQ.equals(testDQ2)); 
      assertTrue(testDQ2.equals(testDQ)); // Equals should be commutative
      assertTrue(testDQ.equals(testDQ2) == testDQ2.equals(testDQ)); 
      // Check that sizes are equal
      assertEquals(testDQ.size(), testDQ2.size());
      // Check that objects in both are of the same class
      Class<?> dqtype = testDQ.peekFront().getClass();
      Class<?> dq2type = testDQ2.peekFront().getClass();
      assertTrue(dqtype.equals(dq2type));
      // CHeck that corresponding objects are equal
      while(testDQ.peekBack() == null && testDQ2.peekBack() == null){
         assertEquals(testDQ.removeBack(), testDQ2.removeBack());
      }
   }
   
   /**
    * Tests that two Deques with equal size, same class type, and corresponding
    * values are equal, and that the capacity's don't have to be equal. 
    */
   public void testEqualsTrueDifferentCapacity(){
      int randomCapacity = (int)(Math.random()*1000 + 1 + 52);
      Deque12<Character> testDQ2= new Deque12<Character>(randomCapacity); 
      for(char letter = 'a'; letter <= 'z'; letter++){
         testDQ2.addBack(new Character(letter));
      }
      assertTrue(testDQ.equals(testDQ2)); 
      assertTrue(testDQ2.equals(testDQ)); // Equals should be commutative
      assertTrue(testDQ.equals(testDQ2) == testDQ2.equals(testDQ)); 
      // Check that sizes are equal
      assertEquals(testDQ.size(), testDQ2.size());
      // Check that objects in both are of the same class
      Class<?> dqtype = testDQ.peekFront().getClass();
      Class<?> dq2type = testDQ2.peekFront().getClass();
      assertTrue(dqtype.equals(dq2type));
      // CHeck that corresponding objects are equal
      while(testDQ.peekBack() == null || testDQ2.peekBack() == null){
         assertEquals(testDQ.peekBack(), testDQ2.peekBack());
         testDQ.removeBack();
         testDQ.removeBack();
      }
   }
   
   /**
    * Tests that two Deque12s will not be equal if they have different types.
    */
   public void testEqualsFalseTypeCondition(){
      Deque12<String> testDQ2 = new Deque12<String>(52);
      for(char i = 'a'; i <= 'z'; i++){
         testDQ2.addBack(new String("" + i));
      }
      // Compare class types, make sure not equal. 
      Class<?> dqtype = testDQ.peekFront().getClass();
      Class<?> dq2type = testDQ2.peekFront().getClass();
      assertFalse(dqtype.equals(dq2type));
      // Assert false, commutative property of equals, and equality of equals
      assertFalse(testDQ.equals(testDQ2));
      assertFalse(testDQ2.equals(testDQ));
      assertTrue(testDQ.equals(testDQ2) == testDQ2.equals(testDQ)); 
   }
   
   /**
    * Tests that two Deque12s will not be equal if they have different sizes.
    */
   public void testEqualsFalseSizeCondition(){
      // Test for comparison to shorter length
      Deque12<Character> testDQ2 = new Deque12<Character>(52);
      for(char ltr = 'a'; ltr <= 'y'; ltr++){
         testDQ2.addBack(new Character(ltr));
      }
      // Check that sizes are not the same. 
      assertFalse(testDQ.size() == testDQ2.size());
      // Assert that the Deque12's aren't equal.
      assertFalse(testDQ.equals(testDQ2));
      assertFalse(testDQ2.equals(testDQ));
      
      // Test for comparison to longer length
      testDQ2 = new Deque12<Character>(52);
      for(char ltr = 'A'; ltr <= 'z'; ltr++){
         testDQ2.addBack(new Character(ltr));
      }
      // Check that sizes are not the same. 
      assertFalse(testDQ.size() == testDQ2.size());
      // Assert that the Deque12's aren't equal.
      assertFalse(testDQ.equals(testDQ2));
      assertFalse(testDQ2.equals(testDQ));
   }
   
   /**
    * Tests that two Deque12s will not be equal if don't have corresponding 
    * values. Also tests that empty Deque12's ARE equal.  
    */
   public void testEqualsFalseCorrespondence(){
      Deque12<Character> testDQ2 = new Deque12<Character>(52);
      // Fill up with the same characters in the test Deque12.
      for(int i = 1; i <= 26; i++){
         char rand = (char)((int)(Math.random()*26) + 'a'); 
         testDQ2.addBack(new Character(rand));
      }
      
      // Compare corresponding values
      while(testDQ.peekBack() != null || testDQ2.peekBack() != null){
         if (testDQ.peekBack().equals(testDQ2.peekBack()))
            assertTrue(testDQ.peekBack().equals(testDQ2.peekBack()));
         else
            assertFalse(testDQ.peekBack().equals(testDQ2.peekBack()));
         testDQ.removeBack();
         testDQ2.removeBack();
         // Test commutative property
         if (testDQ.size()!=0 || testDQ2.size()!= 0){
            assertFalse(testDQ.equals(testDQ2));
            assertFalse(testDQ2.equals(testDQ));
         }
      }
      // Empty lists ARE equal. 
      assertTrue(testDQ.equals(testDQ2));
      assertTrue(testDQ2.equals(testDQ));
   }
   
   /**
    * Tests that a Deque12 can equal itself.
    */
   public void testEqualsReflexive(){
      assertTrue(testDQ.equals(testDQ)); 
   }
}
