/**
 * Stack12 is an implementation of the Stack data structure. 
 * It is a FIFO structure (First in, first out) structure.
 * It uses the Adapter pattern of software reuse, containing a
 * Deque12 object to perform the necessary method actions. This 
 * stack has a bounded capacity which cannot be exceeded. The 
 * BoundedStack interface guides this Stack12. Objects can only 
 * be accessed at the front, inserted at the front, removed from
 * the front. Capacity may not be negative.
 * 
 * @author Andrew Tsun, login: cs12soo
 * @version CSE12, Programming Assignment #3
 * @param <E> The type of element this Stack12 can hold
 * @see Deque12
 * @see BoundedStack
 */
public class Stack12<E> implements BoundedStack<E> {
   
   // The Deque12 object that will perform the necessary work.
   private Deque12<E> container;  
   
   /**
    * Creates a Stack12 object with a maximum queue size. 
    * @param max the maximum queue size 
    * @throws IllegalArgumentException if the max specified is negative
    * @see Deque12#Deque12(int)
    */
   public Stack12(int max){
      if (max < 0) // Capacity shouldn't be negative
         throw new IllegalArgumentException();
      container = new Deque12<E>(max); // Use Deque12 object.
   }
   
   /**
    * Returns the maximum number of elements it can hold.
    * @return the capacity of this BoundedStack
    * @see Deque12#capacity()
    * @see BoundedStack#capacity()
    */
   public int capacity() {
      return container.capacity(); // Use Deque12 object.
   }
   
   /**
    * Returns the number of elements in this Stack12.
    * @return the number of elements in this Stack12.
    * @see Deque12#size()
    * @see BoundedStack#size()
    */
   @Override
   public int size() {
      return container.size(); // Use Deque12 object.
   }
   
   /**
    * Adds an element to the top of the Stack12.
    * Will not add the element if the Stack12 is full,
    * or if the element to be added is null value. 
    * @param e the element to be added
    * @return true if the element was successfully added
    * @return false if the capacity was full. 
    * @throws NullPointerException if e is a null object
    * @see Deque12#addFront(Object) 
    * @see BoundedStack#push(Object)
    */
   @Override
   public boolean push(E e) {
      return container.addFront(e); // Use Deque12 object.
   }
   
   /**
    * Removes an element from the top of the Stack12. 
    * @return the removed element if elements were in the Stack12 
    * @return null if the size was 0
    * @see Deque12#removeFront()
    * @see BoundedStack#pop()
    */
   @Override
   public E pop() {
      return container.removeFront(); // Use Deque12 object.
   }
   
   /**
    * Returns the element from the top of this Stack12 without removing it.
    * @return the element at the top of this Stack12
    * @return null if there are no elements in this list. 
    * @see Deque12#peekFront()
    * @see BoundedStack#equals(Object)
    */
   @Override
   public E peek() {
      return container.peekFront(); // Use Deque12 object.
   }
   
   /** 
    * Checks to see if this Stack12 object is equal to another Stack12 object by
    * comparing their contained Deque12 objects. 
    * @param o the Stack12 object to be compared against.
    * @return true if this Stack12 object is equal to the other Stack12 object
    * @return false if this Stack12 object is not equal to the other object passed in. 
    * @see Deque12#equals(Object)
    * @see BoundedStack#equals(Object)
    */
   @SuppressWarnings("unchecked")
   public boolean equals(Object o){
      // Attempt cast of object into Stack12<E>
      Stack12<E> compare; 
      try{
         compare = (Stack12<E>) o; 
      }
      // Objects of different types shouldn't be equal. 
      catch(ClassCastException e){return false;} 
      // Compare the Deque12 objects of the Stack12's 
      return this.container.equals(compare.container);
   }

}
