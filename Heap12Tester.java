/**
 * Heap12Tester tests the following methods of Heap12: <ul>
 * <li>Constructor</li>
 * <li>Copy Constructor</li>
 * <li>add</li>
 * <li>remove</li>
 * <li>peek</li>
 * <li>size</li>
 * <li>isEmpty()</li>
 * <li>equals</li>
 * <li>hashcode</li>
 * <li>sort</li>
 * </ul>
 * on a Heap12 of Characters from 'A' to 'Z'. 
 * @author Andrew Tsun
 * @version Programming Assignment 4
 * @see Heap12
 */

public class Heap12Tester extends junit.framework.TestCase{
   private Heap12<Character> testHeap = new Heap12<Character>(); // Test Heap12
   private static final int DEFAULT_SIZE = 26; // # of letters in the Alphabet.
   
   /**
    * Runs the Heap12Tester Junit Test Case in a SwingUI. 
    */
   /* public static void main(String args[]){
      junit.swingui.TestRunner.main(new String[] {"Heap12Tester"});
   }*/
   
   /**
    * Adds the capital letters of the alphabet to the test Heap12. 
    */
   public void setUp() throws Exception {  
      // Set up so heap has DEFAULT_SIZE letters of the alphabet. 
      for(char letter = 'A'; letter <= 'Z'; letter++){
         Character addChar = new Character(letter); // Make character obj.
         testHeap.add(addChar);
      }
   }

   /**
    * Clears out the Character Heap12 used for this test.
    */
   public void tearDown() throws Exception {
      testHeap = new Heap12<Character>();
   }
   
   /**
    * Tests that when a Heap12 Object is created, it is empty. 
    * @see Heap12#Heap12()
    */
   public void testHeap12Constructor() {
      testHeap = new Heap12<Character>();
      assertTrue(testHeap.isEmpty());
      assertEquals(testHeap.size(), 0);
   }
   
   /**
    * Tests that when a copy of a Heap12 is made, the resulting Heap12s
    * should be equal.
    * @see Heap12#Heap12(Heap12)
    */
   public void testHeap12CopyConstructor(){
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      assertEquals(testHeapCopy, testHeap);
   }
   
   /**
    * Tests that if elements are added to the copy of a Heap12 object,
    * the original does not undergo any structural change, and vice
    * versa. 
    * @see Heap12#Heap12(Heap12)
    * @see Heap12#add(Comparable) 
    * @see Heap12#peek()
    */
   public void testHeap12CopyDifferentBackingArraysAdd() {
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      // In the event that elements are added to the copy, 
      // the same does not happen to the original.
      for(char ltr = 'a'; ltr <= 'z'; ltr++){
         testHeapCopy.add(ltr);
         assertFalse(testHeapCopy.peek().equals(testHeap.peek()));
      }
      testHeapCopy = new Heap12<Character>(testHeap);
      // In the event that elements are added to original, 
      // the same does not happen to copy.
      for(char ltr = 'a'; ltr <= 'z'; ltr++){
         testHeap.add(ltr);
         assertFalse(testHeap.peek().equals(testHeapCopy.peek()));
      }
      
   }
   
   /**
    * Tests that if elements are removed from the copy of a Heap12 object,
    * the original does not undergo any structural change, and vice
    * versa. 
    * @see Heap12#Heap12(Heap12)
    * @see Heap12#remove()
    */
   public void testHeap12CopyDifferentBackingArraysRemove() {
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      // In the event that elements are removed from the original, 
      // the same does not happen to copy.
      testHeap.remove();
      while(testHeap.peek() != null){
         assertFalse(testHeap.peek().equals(testHeapCopy.peek()));
         testHeap.remove();
      }
      // Rebuild the original Heap12.
      for(char letter = 'A'; letter <= 'Z'; letter++){
         Character addChar = new Character(letter); // Make character obj.
         testHeap.add(addChar);
      }
      // Rebuild the copy Heap12. 
      testHeapCopy = new Heap12<Character>(testHeap);
      // In the event that elements are removed from the copy, 
      // the same does not happen to the original.
      testHeapCopy.remove();
      while(testHeapCopy.peek() != null){
         assertFalse(testHeapCopy.peek().equals(testHeap.peek()));
         testHeapCopy.remove();
      }
   }
   
   /**
    * Tests that the elements in the backing arrays of the original and
    * copy Heap12's are the same elements, and not copies.
    * @see Heap12#Heap12(Heap12)
    */
   public void testHeap12CopySameDataElements(){
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      while(testHeap.peek() != null && testHeap.peek() != null){
         Character rm = testHeap.remove(); 
         Character rm2 = testHeapCopy.remove();
         assertSame(rm, rm2); 
      }
   }
   
   /*Test Add*/
   
   /**
    * Tests that when an object is added to Heap12, the size 
    * increases by 1. 
    * @see Heap12#add(Comparable)
    * @see Heap12#size()
    */
   public void testAddSizeChange() {
      for(char letter = 'a'; letter <= 'z'; letter++){
         Character addChar = new Character(letter); // Make character obj.
         int initialSize = testHeap.size(); // Record size.
         testHeap.add(addChar); // Add letter
         // Size should increase by 1.
         assertEquals(initialSize+1, testHeap.size()); 
      }
   }
   
   /**
    * Test that when an object is added to Heap12, it appears
    * in Heap12. 
    * @see Heap12#add(Comparable)
    * @see Heap12#peek()
    */
   public void testAddElementsInArray() {
      for(char letter = 'a'; letter <= 'z'; letter++){
         Character addChar = new Character(letter); // Make character obj.
         testHeap.add(addChar); // Add letter
         // Letter should appear as highest priority. 
         assertEquals(addChar, testHeap.peek()); 
      }
   }
   
   /**
    * Tests that when trying to add a null element to Heap12,
    * a NullPointerException is thrown. 
    * @see Heap12#add(Comparable)
    * @see NullPointerException
    */
   public void testAddNullPtrException(){
      for(char letter = 'a'; letter <= 'z'; letter++){
         try{
            testHeap.add(null); // Add null element
            fail();// Should not be able to add null element.
         } 
         catch(NullPointerException e){} // Should throw this exception. 
      }
   }
   
   /**
    * Tests that other elements are unchanged as elements are added to a Heap12.
    * @see Heap12#add(Comparable)
    * @see Heap12#Heap12(Heap12)
    */
   public void testAddElementsUnchanged(){
      // Record existing instances of Heap12. 
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      // Add the same character to both.  
      for(char letter = 'a'; letter <= 'z'; letter++){
         Character addChar = new Character(letter); // Make character obj.
         // Add same letter to both objects. 
         testHeap.add(addChar); 
         testHeapCopy.add(addChar);
         // Letter should appear as highest priority. 
         assertEquals(testHeap, testHeapCopy); 
      }
   }
   
   /*Test Remove*/

   /**
    * Test that elements are removed by highest priority
    * from the Heap12.
    * @see Heap12#remove()
    * @see Heap12#peek()
    */
   public void testRemoveHighPriority() {
      // Create an array of characters 'z' to 'a', this way it is 
      // ordered from highest to lowest priority
      Character[] backwards = new Character[DEFAULT_SIZE];
      for(int i = 0; i < backwards.length; i++){
         char ltr = (char)('Z'-i); // Determine character
         Character addChar = new Character(ltr); // Make Character object
         backwards[i] = addChar; // Add it to right place in array
      }
      int i = 0; // Keep track of index in letter array. 
      while(testHeap.peek()!= null){ // While elements exist in the Heap12
         Character rm = testHeap.remove(); // Remove an element and store it.
         assertEquals(rm, backwards[i]); // Make sure correct element restored
         i++; 
      }
   }
   
   /**
    * Test that when elements are removed from Heap12, 
    * the size is decreasing.
    * @see Heap12#remove()
    * @see Heap12#size()
    */
   public void testRemoveChangeSize(){
      while(testHeap.peek()!= null){ // While elements exist in the Heap12
         int initial = testHeap.size(); // Record size before removing. 
         testHeap.remove(); // Remove an element and store it.
         assertEquals(testHeap.size(), initial-1); // Size should decrease by 1 
      }
   }
   

   /**
    *  If a Heap12 has items removed from it, the remaining elements should be left unchanged.
    *  @see Heap12#remove()
    *  @see Heap12#Heap12(Heap12)
    */
   public void testRemoveElementsUnchanged(){
      // Make an exact recording of where elements are.  
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      while(testHeap.peek()!= null){
         // Perform same remove operation on both testHeap, and testHeapCopy.
         testHeap.remove();
         testHeapCopy.remove();
         // Same thing should be removed at same place, other elements at same place.
         assertEquals(testHeap, testHeapCopy);
      }
   }

   /*Test Peek*/
   
   /**
    * Ensures that as elements are removed from the Heap12,
    * the peek function constantly returns the highest 
    * priority object. 
    * @see Heap12#remove()
    * @see Heap12#peek()
    */
   public void testPeekReturn() {
      // Create an array of characters 'z' to 'a', this way it is 
      // ordered from highest to lowest priority
      Character[] backwards = new Character[DEFAULT_SIZE];
      for(int i = 0; i < backwards.length; i++){
         char c = (char)('Z'-i); // Determine character
         Character addChar = new Character(c); // Make Character object
         backwards[i] = addChar; // Add it to right place in array
      }
      int index = 0; 
      // Make sure the correct element is peeked at while elements are removed.
      while(testHeap.peek()!= null){ 
         assertTrue(testHeap.peek().equals(new Character(backwards[index])));
         testHeap.remove();
         index++;
      }
   }
   
   /*Test Peek*/
   
   /**
    * Ensures that peeking at an empty Heap returns a null value. 
    * @see Heap12#peek()
    */
   public void testPeekEmptyList(){
      Heap12<Character> testHeap = new Heap12<Character>();
      assertTrue(testHeap.peek() == null);
   }
   
   /**
    *  Tests that peeking at a Heap doesn't change the size, 
    *  size is varied using add and remove methods.
    *  @see Heap12#peek()
    *  @see Heap12#add()
    *  @see Heap12#remove()
    */
   public void testPeekNoSizeChange(){
      Heap12<Character> testHeap = new Heap12<Character>();
      // Test for no size change on calls to peek for subsequent, 
      // decreasing sizes.
      while(testHeap.peek()!= null){ 
         int initial = testHeap.size();
         testHeap.peek();
         assertEquals(initial, testHeap.size());
         testHeap.remove();
      }

      // Test for no size change on calls to peek for subsequent, 
      // increasing sizes.
      for(char ltr = 'a'; ltr <= 'z'; ltr++){
         int initial = testHeap.size();
         testHeap.peek();
         assertEquals(initial, testHeap.size());
         Character addChar = new Character(ltr);
         testHeap.add(addChar);
      }
   }
   
   /**
    * Ensures that when peeking, the rest of the elements remain unchanged.
    * @see Heap12#peek()
    * @see Heap12#Heap12(Heap12)
    */
   public void testPeekElementsUnchanged(){
      // Record initial state
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      testHeap.peek(); // Perform the peeking
      // Compare initial and final states. Should be same. 
      assertEquals(testHeap, testHeapCopy);
   }
   
   /*Test Size*/
   
   /**
    * If the size function is called, the size should not change.
    * @see Heap12#size() 
    */
   public void testSizeSizeUnchanged() {
      // Record initial size (Heap12 has not been modified)
      int initialInt = DEFAULT_SIZE;
      // Record actual size 
      int finalInt = testHeap.size();
      // Make sure both sizes are equal. 
      assertEquals(initialInt, finalInt);
   }
   
   /**
    * If the size function is called, elements inside should be unchanged.
    * @see Heap12#size()
    * @see Heap12#Heap12(Heap12)
    */
   public void testSizeElementsUnchanged(){
      // Record initial state
      Heap12<Character> testHeapCopy = new Heap12<Character>(testHeap);
      // Perform the sizing
      testHeap.size();
      // Compare initial and final states. Should be same. 
      assertEquals(testHeap, testHeapCopy);
   }
   
   /*Test IsEmpty*/
   
   /**
    * If a list is empty, the remove function
    * should return null.
    * @see Heap12#isEmpty()
    * @see Heap12#remove()
    */
   public void testIsEmptyRemoveNull(){
      testHeap = new Heap12<Character>();
      assertTrue(testHeap.isEmpty());
      assertTrue(testHeap.remove() == null);
   }
   
   /**
    * If a list is empty, adding an element
    * will increase its size to 1, and the list
    * will not be empty anymore. 
    * @see Heap12#isEmpty()
    * @see Heap12#add()
    * @see Heap12#size()
    */
   public void testIsEmptyAddOne(){
      testHeap = new Heap12<Character>(); // New Heap
      assertTrue(testHeap.isEmpty()); // Heap should be empty
      // Add random character
      char c = (char)('a'+(int)(Math.random()*DEFAULT_SIZE));
      testHeap.add(new Character(c));
      assertFalse(testHeap.isEmpty()); // Heap shouldn't be empty
      assertEquals(testHeap.size(), 1); // Size should be 1
   }
   
   /** 
    * If a list is empty, its size should be 0. 
    * @see Heap12#isEmpty()
    * @see Heap12#size()
    */
   public void testIsEmptySizeZero(){
      testHeap = new Heap12<Character>();
      assertTrue(testHeap.isEmpty() && testHeap.size() == 0);
   }
   
   /**
    * If a Heap12 is empty, it should be equal to a new empty Heap12. 
    * @see Heap12#isEmpty()
    * @see Heap12#Heap12(Heap12)
    */
   public void testIsEmptyCompareEmptyList(){
      testHeap = new Heap12<Character>();
      Heap12<Character> emptyHeap = new Heap12<Character>();
      assertTrue(emptyHeap.isEmpty());
      assertTrue(testHeap.isEmpty());
      assertTrue(emptyHeap.equals(testHeap)); 
   }
   
   /*Test Equals*/
   
   /**
    * Tests that two Deques with equal size, same class type, and corresponding 
    * values are equal. Also happens to have equal capacities. 
    * @see Heap12#equals(Object)
    */
   public void testEqualsTrue(){
      Heap12<Character> testHeap2= new Heap12<Character>(); 
      for(char letter = 'A'; letter <= 'Z'; letter++){
         testHeap2.add(new Character(letter));
      }
      assertTrue(testHeap.equals(testHeap2)); 
      assertTrue(testHeap2.equals(testHeap)); // Equals should be commutative
      assertTrue(testHeap.equals(testHeap2) == testHeap2.equals(testHeap)); 
      // Check that sizes are equal
      assertEquals(testHeap.size(), testHeap2.size());
      // Check that objects in both Heap12's.
      assertTrue(testHeap instanceof Heap12 && testHeap2 instanceof Heap12);
      // Check that the hashcodes are equal
      assertEquals(testHeap.hashCode(),testHeap2.hashCode());
      // Check that corresponding objects are equal
      while(testHeap.peek() == null && testHeap2.peek() == null){
         assertEquals(testHeap.remove(), testHeap2.remove());
      }
   }

   /**
    * Tests that two Heap12s will be equal if they have different types
    * and are all empty
    * @see Heap12#equals(Object)
    */   
   public void testEqualsTrueDifferentTypesAllEmpty(){
       testHeap = new Heap12<Character>();
       // Different type, same capacity 
       Heap12<Integer> hi = new Heap12<Integer>();
       // Different type, 
       Heap12<String> hs = new Heap12<String>();
       // Assert they are all equal to one another, and equals is commutative
       assertTrue(testHeap.equals(hi));
       assertTrue(hi.equals(testHeap)); 
       assertTrue(testHeap.equals(hs));
       assertTrue(hs.equals(testHeap)); 
       assertTrue(hi.equals(hs));
       assertTrue(hs.equals(hi));
   }
   
   /**
    * Tests that two Heap12s will not be equal if they have different types.
    * @see Heap12#equals(Object)
    */
   public void testEqualsFalseTypeCondition(){
      Object o = new Object();
      String s = new String();
      // Assert false, commutative property of equals, and equality of equals
      assertFalse(testHeap.equals(o));
      assertFalse(testHeap.equals(s));
      assertTrue(testHeap.equals(o) == testHeap.equals(s)); 
   }
   
   /**
    * Tests that two Heap12s will not be equal if they have different sizes.
    * @see Heap12#equals(Object)
    * @see Heap12#size()
    */
   public void testEqualsFalseSizeCondition(){
      // Test for comparison to shorter length
      Heap12<Character> testHeap2 = new Heap12<Character>();
      for(char ltr = 'a'; ltr <= 'y'; ltr++){
         testHeap2.add(new Character(ltr));
      }
      // Check that sizes are not the same. 
      assertFalse(testHeap.size() == testHeap2.size());
      // Assert that the Heap12's aren't equal.
      assertFalse(testHeap.equals(testHeap2));
      assertFalse(testHeap2.equals(testHeap));
      
      // Test for comparison to longer length
      testHeap2 = new Heap12<Character>();
      for(char ltr = 'A'; ltr <= 'z'; ltr++){
         testHeap2.add(new Character(ltr));
      }
      // Check that sizes are not the same. 
      assertFalse(testHeap.size() == testHeap2.size());
      // Assert that the Heap12's aren't equal.
      assertFalse(testHeap.equals(testHeap2));
      assertFalse(testHeap2.equals(testHeap));
   }
   
   /**
    * Tests that two Heap12s will not be equal if don't have the same values. 
    * Also tests that empty Heap12's ARE equal.  
    * @see Heap12#equals(Object)
    * @see Heap12#remove()
    * @see Heap12#peek()
    */
   public void testEqualsFalseDifferentValues(){
      Heap12<Character> testHeap2 = new Heap12<Character>();
      // Fill up with the same characters in the test Heap12.
      for(int i = 1; i <= DEFAULT_SIZE; i++){
         char rand = (char)((int)(Math.random()*DEFAULT_SIZE) + 'a'); 
         testHeap2.add(new Character(rand));
      }
      // Compare corresponding values
      while(testHeap.peek() != null || testHeap2.peek() != null){
         if (testHeap.peek().equals(testHeap2.peek()))
            assertTrue(testHeap.peek().equals(testHeap2.peek()));
         else
            assertFalse(testHeap.peek().equals(testHeap2.peek()));
         testHeap.remove();
         testHeap2.remove();
         // Test commutative property
         if (testHeap.size()!=0 || testHeap2.size()!= 0){
            assertFalse(testHeap.equals(testHeap2));
            assertFalse(testHeap2.equals(testHeap));
         }
      }
      // Empty Heap12's ARE equal. 
      assertTrue(testHeap.equals(testHeap2));
      assertTrue(testHeap2.equals(testHeap));
   }
   
   /**
    * Tests that two Heap12s will not be equal if they have
    * the same values, but the values don't correspond.
    * @see Heap12#equals(Object)
    */
   public void testEqualsSameValuesDifferentOrder(){
      // Add the values 3,4,6,9,24,78,and 100
      Heap12<Integer> testHeapInt1 = new Heap12<Integer>();
      testHeapInt1.add(new Integer(100));
      testHeapInt1.add(new Integer(78));
      testHeapInt1.add(new Integer(24));
      testHeapInt1.add(new Integer(3));
      testHeapInt1.add(new Integer(4));
      testHeapInt1.add(new Integer(6));
      testHeapInt1.add(new Integer(9));
      // Add the values 3,4,6,9,24,78,and 100 in a different order
      // such that a different Heap12 is generated.
      Heap12<Integer> testHeapInt2 = new Heap12<Integer>();
      testHeapInt2.add(new Integer(100));
      testHeapInt2.add(new Integer(24));
      testHeapInt2.add(new Integer(78));
      testHeapInt2.add(new Integer(4));
      testHeapInt2.add(new Integer(3));
      testHeapInt2.add(new Integer(9));
      testHeapInt2.add(new Integer(6));
      // Fill up with the same characters in the test Heap12.
      assertFalse(testHeapInt1.equals(testHeapInt2));
      assertFalse(testHeapInt2.equals(testHeapInt1));
   }
   
   /**
    * Tests that if two Heap12's aren't equal, neither should their hashcodes
    * @see Heap12#equals(Object)
    * @see Heap12#hashCode()
    */
   public void testEqualsFalseHashCode(){
      Heap12<Character> testHeap2 = new Heap12<Character>();
      // Fill up with the same characters in the test Heap12.
      for(int i = 1; i <= DEFAULT_SIZE; i++){
         char rand = (char)((int)(Math.random()*DEFAULT_SIZE)); 
         testHeap2.add(new Character(rand));
      }
      assertFalse(testHeap.equals(testHeap2));
      assertFalse(testHeap.hashCode() == testHeap2.hashCode());
   }
   
   /**
    * Tests that a Heap12 can equal itself.
    * @see Heap12#equals(Object)
    */
   public void testEqualsReflexive(){
      assertTrue(testHeap.equals(testHeap)); 
   }
   
   /*Test Hashcode*/
  
   /**
    * Ensures that every call to hashCode returns a consistent value. 
    * @see Heap12#hashCode()
    */
   public void testHashCodeConsistency() {
      int actual = testHeap.hashCode(); // Record the hashcode. 
      int[] hashCodeArray = new int[100];
      // Store the results of 100 calls to hashcode.
      for (int i = 0; i < hashCodeArray.length; i++){
         hashCodeArray[i] = testHeap.hashCode();
      }
      // Ensure the calls to hashcode are all equal. 
      for (int i = 0; i < hashCodeArray.length; i++){
         if (hashCodeArray[i] != actual)
            fail();
      }
   }
   
   /**
    * Ensures that even if 2 different heaps have the same 
    * values, they will not generate the same
    * hashcode if they are in a different order. 
    * @see Heap12#hashCode()
    */
   public void testHashCodeSameValuesDifferentOrder(){
      // Add the values 3,4,6,9,24,78,and 100
      Heap12<Integer> testHeapInt1 = new Heap12<Integer>();
      testHeapInt1.add(new Integer(100));
      testHeapInt1.add(new Integer(78));
      testHeapInt1.add(new Integer(24));
      testHeapInt1.add(new Integer(3));
      testHeapInt1.add(new Integer(4));
      testHeapInt1.add(new Integer(6));
      testHeapInt1.add(new Integer(9));
      // Add the values 3,4,6,9,24,78,and 100 in a different order
      // such that a different Heap12 is generated.
      Heap12<Integer> testHeapInt2 = new Heap12<Integer>();
      testHeapInt2.add(new Integer(100));
      testHeapInt2.add(new Integer(24));
      testHeapInt2.add(new Integer(78));
      testHeapInt2.add(new Integer(4));
      testHeapInt2.add(new Integer(3));
      testHeapInt2.add(new Integer(9));
      testHeapInt2.add(new Integer(6));
      // Obtain Hashcodes. 
      int hc1 = testHeapInt1.hashCode();
      int hc2 = testHeapInt2.hashCode();
      // Assert that the Hashcodes are not equal.
      assertFalse(hc1 == hc2);
   }
   
   /**
    * Test that if 2 Heap12 objects are created in the same way, 
    * their hashcodes should be equal.
    * @see Heap12#hashCode() 
    */
   public void testHashCodeEquality(){
      Heap12<Character> testHeap2 = new Heap12<Character>();
      for(char letter = 'A'; letter <= 'Z'; letter++){
         Character addChar = new Character(letter); // Make character obj.
         testHeap2.add(addChar);
      }
      int hc1 = testHeap.hashCode();
      int hc2 = testHeap2.hashCode();
      assertEquals(hc1, hc2);
   }
   
   /**
    * Tests that two Heap12s that are not equal will have distinct
    * hashcodes.   
    * @see Heap12#hashCode()
    * @see Heap12#equals(Object)
    */
   public void testHashCodeDistinctValues(){
      Heap12<Character> testHeap2 = new Heap12<Character>();
      // Fill up with random characters
      for(int i = 1; i <= DEFAULT_SIZE; i++){
         char rand = (char)((int)(Math.random()*DEFAULT_SIZE)); 
         testHeap2.add(new Character(rand));
      } 
      // Obtain hashcodes
      int hc1 = testHeap.hashCode();
      int hc2 = testHeap2.hashCode();
      // Make sure there is a difference between the values. 
      assertTrue(hc1-hc2 != 0);
   }
   
   /* Test Sort */
   
   /**
    * Tests that an array can correctly be sorted using a Heap. 
    * Random numbers tested. 
    * @see Heap12#sort(Comparable[])
    */
   
   public void testSort() {
      Integer[] randArray = new Integer[30];
      // Fill a heap with random integers
      for (int i = 1; i <= randArray.length; i++){
         int randInt = (int)(Math.random()*1000) + i;
         randArray[i-1] = randInt; 
      }
      // Sort the heap in nondecreasing order.
      Heap12.<Integer>sort(randArray);
      // If any element is not in order, fail the test.
      for(int i = 1; i < randArray.length; i++){
         // Fail when previous item is greater than current item. 
         if(randArray[i-1].compareTo(randArray[i]) > 0)
            fail();
      }
   }
   
   /**
    * Tests that when passing a null argument into sort, 
    * it throws a NullPointerException
    * @see Heap12#sort(Comparable[])
    * @see NullPointerException
    */
   public void testSortNullPointerException(){
      try{
         Heap12.<Character>sort(null); // Try to sort null element.
         fail(); // Should not be able to sort
      }
      catch(NullPointerException e){} // Should throw nullptrexception
   }

}
