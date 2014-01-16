/**
 * Deque12 is a double-ended queue that follows the specifications outlined
 * in the BoundedDeque interface. Items can be accessed only at the ends. 
 * All elements are held in a circular array. There is a capacity that 
 * cannot be exceeded, and the circular array cannot be overwritten, as the
 * Deque12 prevents addition of new objects when the capacity is full.
 * Capacities may not be negative. Duplicate objects are permitted, 
 * null objects are not. Null is used for signaling by the Deque12. Items
 * can be added or removed at both ends.  
 * 
 * @author Andrew Tsun, login: cs12soo
 * @version Programming Assignment 3
 * @see BoundedDeque
 * @param <E> The type of element this Deque12 can hold
 */
public class Deque12<E> implements BoundedDeque<E>{
   
   private int capacity; // Maximum # of elements this deque can hold.
   private int size;  // Number of elements currently in this deque; 
   private Object[] data; // Circular array to hold elements in this Deque12. 
   private int front; // Index of front of array
   private int back; // Index of back of array. 
   
   /**
    * Constructs a Deque12 object, setting pointers front and back in the 
    * middle, setting size to 0, creating a circular array with capacity
    * as specified. 
    * @param max The capacity of this Deque12 object
    * @throws IllegalArgumentException if capacity specified is negative. 
    */
   public Deque12(int max){
      // Cannot have negative capacity. 
      if (max < 0)
         throw new IllegalArgumentException(); 
      // Initialize variables
      capacity = max; // capacity is # of values max in this array.
      size = 0; // no elements in array.
      data = new Object[capacity]; // need place to hold ar
      // Front and back are in the middle
      front = capacity/2;
      back = capacity/2; 
   }
   
   /**
    * Returns the maximum elements this Deque12 can hold.
    * @return the maximum elements this Deque12 can hold.
    * @see BoundedDeque#size()
    */
   @Override
   public int capacity() {
      return capacity;
   }
   
   /**
    * Returns the number of elements this Deque12 is holding.  
    * @return the number of elements in this Deque12. 
    * @see BoundedDeque#size()
    */
   @Override
   public int size() {
      return size; 
   }
   
   /**
    * Adds an element to the back of this Deque12. 
    * Will not add element if capacity has been reached, 
    * or the element is a null element.
    * @param e the element to be added to the back of this list 
    * @return true if the element was successfully added.
    * @return false if the capacity was reached
    * @throws NullPointerException if parameter e is null value. 
    * @see BoundedDeque#addFront(Object)
    */
   @Override
   public boolean addFront(E e) {
      if (size >= capacity) // Do not exceed capacity
         return false;
      if (e == null) // Do not allow null elements
         throw new NullPointerException(); 
      if (size != 0){
         if (front==0) // Continue from end of array if at end. 
            front = capacity-1;
         else // Move front pointer one to left 
            front--; 
      }
      data[front] = e; // Insert object
      size++; // Increase size
      return true; 
   }
   
   /**
    * Adds an element to the back of this Deque12. 
    * Will not add element if capacity has been reached, 
    * or the element is a null element.
    * @param e the element to be added to the back of this list 
    * @return true if the element was successfully added.
    * @return false if the capacity was reached
    * @throws NullPointerException if parameter e is null value. 
    * @see BoundedDeque#addBack(Object)
    */
   @Override
   public boolean addBack(E e) {
      if (size >= capacity) // Do not exceed capacity
         return false;
      if (e == null) // Do not allow null elements
         throw new NullPointerException(); 
      if (size != 0){
         if (back == capacity-1) // Continue from end of array if at end. 
            back = 0;
         else // Move back pointer one to right 
            back++; 
      }
      data[back] = e; // Insert object
      size++; // Increase size
      return true; 
   }

   /**
    * Removes the front element of this Deque12, the one at the front. 
    * @return the element previously at index front of this Deque12.
    * @return null if size was 0. 
    * @see BoundedDeque#removeFront()
    */
   @Override
   @SuppressWarnings("unchecked")
   public E removeFront() {
      // Return null if list is empty
      if(data[front]==null)
         return null;
      // Record item removed
      E rm = (E) data[front];
      // Clear out index @ front
      data[front] = null; 
      // Shift remove index 1 to right
      size--; // Decrement size
      if (size != 0){ // Do not move if size = 0
         if (front == capacity-1) // Loop around
            front = 0;
         else 
            front++;
      }
      return rm; // Return removed object
   }
   
   /**
    * Removes the last element of this Deque12, the one at the back. 
    * @return the element previously at the index back of this Deque12
    * @see BoundedDeque#removeBack()
    */
   @Override
   @SuppressWarnings("unchecked")
   public E removeBack() {
      // Return null if the list is empty
      if(data[back]==null)
         return null;
      // Record item removed. 
      E rm = (E) data[back];
      // Clear out index @ back
      data[back] = null; 
      size--; // Decrement size
      // Shift remove index 1 to left
      if (size != 0){
         if (back == 0) // Loop around
            back = capacity-1;
         else
            back--;
      }
      return rm; // Return removed object
   }

   /**
    * Returns the first element of this Deque12, but does not remove it
    * @return the element at index front of this Deque12
    * @return null if there are no elements in this Deque12
    * @see BoundedDeque#peekFront()
    */
   @SuppressWarnings("unchecked")
   @Override
   public E peekFront() {
      return (E) data[front];
   }

   /**
    * Returns the last element of this Deque12, but does not remove it
    * @return the element at index back of this Deque12
    * @return null if there are no elements in this Deque12
    * @see BoundedDeque#peekBack()
    */
   @SuppressWarnings("unchecked")
   @Override
   public E peekBack() {
      return (E) data[back]; 
   }
   
   /**
    *  Checks to see if 2 Deque12's are equal. Conditions to be equal include: 
    *  <ul>
    *  <li>types held are equal</li>
    *  <li>sizes are equal</li>
    *  <li>corresponding elements are equal</li>
    *  </ul>
    *  @return true if the Deque12's are equal
    *  @return false if the Deque12's are not equal
    *  @see BoundedDeque#equals(Object)
    */
   @SuppressWarnings("unchecked")
   public boolean equals(Object o){
      // Check to see if they hold same type
      Deque12<E> dq;
      try{
         dq = (Deque12<E>) o;  
      }catch(ClassCastException e){return false;}
      // Check to see if the sizes are equal
      if (this.size != dq.size)
         return false; 
      // Check to see if corresponding values are equal
      // Note: fronts are not the same for comparison dq's
      int indexThis = this.front;
      int indexThat = dq.front; 
      for(int i = 1; i <= size; i++){
         if(!this.data[indexThis].equals(dq.data[indexThat]))
            return false;
         // Increment index of this Deque12's circular array
         if (indexThis == this.capacity-1)
            indexThis = 0; 
         else 
            indexThis++;
         // Increment index of other Deque12's circular array
         if (indexThat == dq.capacity-1)
            indexThat = 0; 
         else 
            indexThat++; 
      }
      return true;
   }
   
}
