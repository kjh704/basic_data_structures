/**
 * Heap12 emulates a Heap data structure; that is, a FIHPO structure
 * (first in, high priority out). It follows the specifications as outlined
 * by the PQueue interface. This heap is max-heavy; that is larger elements
 * have higher priority, and are removed first. All elements are stored in a 
 * heap structured backing array. As the number of elements reaches the 
 * backing array's capacity, the backing array size is doubled. The capacity
 * is virtually unlimited, and duplicate elements are permitted. 
 * 
 * @author Andrew Tsun, login: cs12soo
 * @version Programming Assignment 4
 * @param <E> The Comparable element to be held in this Heap12. 
 * @see PQueue
 */
public class Heap12<E extends Comparable<? super E>> implements PQueue<E>{
   
   private int size; // The number of elements in the array
   private E[] data; // A place to store elements inserted into the array
   // Initial backing array capacity
   private static final int DEFAULT_CAPACITY = 5; 
   
   /**
    * Creates an empty Heap12 with initial backing array capacity of 5. 
    */
   @SuppressWarnings("unchecked")
   public Heap12(){
      data = (E[]) new Comparable[DEFAULT_CAPACITY]; // Init. backing array
      size = 0; // No elements in array. 
   }
   
   /**
    * Creates a deep copy of a Heap12, as in they have their own backing
    * arrays, but the data elements are still the same (not copied). 
    * @param original The Heap12 to make a copy of. 
    */
   @SuppressWarnings("unchecked")
   public Heap12(Heap12<E> original){
      this.size = original.size; 
      this.data = (E[]) new Comparable[original.data.length];
      for(int i = 0; i < data.length; i++){
         this.data[i] = original.data[i]; 
      }
   }
   
   /**
    *  Places a new element into the leftmost leaf of the highest level. 
    *  @param e The element to add to this Heap12.
    *  @see PQueue#add(Comparable)
    */
   @Override
   public void add(E e) {
      if (e == null) // Do not allow for adding null elements.
         throw new NullPointerException();
      // Double the size of the array if reached max capacity. 
      if (size == data.length) 
         expand();
      // Add new element as a leaf.
      data[size] = e;
      // Place it in the correct spot. 
      bubbleUp(size);
      size++; // Increment size.
   }
   
   /**
    * Removes the highest priority element from the Heap12,
    * i.e. the largest element in this Heap12. 
    * @return the root element of Heap12
    * @see PQueue#remove() 
    */
   @Override
   public E remove() {
      if (isEmpty()) // Don't return anything of use if no elements present.
         return null; 
      E rm = data[0]; // Return top element.
      size--; // Decrement size.
      data[0] = data[size]; // Move most recent leaf to top.
      data[size] = null; // Allocate new space where leaf once was.
      // Place leaf accordingly. 
      trickleDown(0);
      return rm; // Return removed element. 
   }
   
   /**
    * Returns the element of highest priority, but does not remove it.
    * @return the root element.  
    * @see PQueue#peek()
    */
   @Override
   public E peek() {
      return data[0]; // Highest element at top. 
   }
   
   /**
    * Returns the number of elements stored in this Heap12. 
    * @return the number of elements in the backing data array.
    * @see PQueue#size()
    */
   @Override
   public int size() {
      return size;
   }
   
   /**
    * Lets the user know whether the Heap12 is empty 
    * @return true if there are no elements in this Heap12
    * @return false if at least one element exists in this Heap12
    * @see PQueue#isEmpty()
    */
   @Override
   public boolean isEmpty() {
      return size==0;
   }
   
   /**
    * Compares this Heap12 with another object. These objects are equal if <ul>
    * <li> The object for comparison is also a Heap12.
    * <li> Both objects have the same size.
    * <li> Corresponding entries in the data array are equal.
    * </ul>
    * @param o The object to be checked for comparison. 
    * @return true if the objects are equal. 
    * @return false if one of the conditions above is violated.
    * @see PQueue#equals(Object)
    */
   public boolean equals(Object o){
      // Check that the object is a Heap12
      Heap12<?> comparison;
      if (o instanceof Heap12<?>) 
         comparison = (Heap12<?>) o;
      else 
         return false; 
      // Check that the sizes are the same
      if (this.size != comparison.size)
         return false; 
      // Empty Heap12's should be equal to each other.
      if (this.size == 0 && comparison.size == 0)
         return true; 
      // Check that the hashcodes are equal.
      if (this.hashCode() != comparison.hashCode()) 
         return false; 
      // Check to see that the corresponding elements are equal. 
      for (int i = 0; i < this.size; i++){
         if (!this.data[i].equals(comparison.data[i]))
            return false; 
      }
      return true;
   }
   
   /**
    * Creates a hashcode for use in hash tables.
    * If 2 Heap12's are equal, their hashcodes should be too.
    * @return A unique hashcode for this Heap12 object. 
    * @see equals
    * @see PQueue#hashCode()
    */
   public int hashCode(){
      int hash = 0;
      for (int i = 1; i < data.length; i++){
         if (!(data[i]==null)) 
            hash += 25*hash + data[i].hashCode()+i;  
      } 
      hash += size; 
      return hash; 
   }
   
   /**
    * Sorts an array by placing all the elements of the array in a Heap12. 
    * As elements are removed from a Heap12, they are removed in sequential order.
    * They are placed back into the array in sequential order in tandem. 
    * @param a The array to be sorted.
    * @param T The type of array to sort. 
    */
   public static <T extends Comparable<? super T>> void sort(T[] a){
      // Cannot sort a null array. 
      if (a == null)
         throw new NullPointerException();
      // Make a new Heap12 to store array elements in. 
      Heap12<T> arrayHeap = new Heap12<T>();
      // Add everything to a heap
      for (int i = 0; i < a.length; i++){
         arrayHeap.add(a[i]);
      }
      // Remove in sequential order, add to array in sequential order
      for (int i = a.length-1; i >= 0; i--){
         a[i] = arrayHeap.remove();
      }
   }
   
   /**
    * Ensures elements in the recently created left
    * is as high up as possible. 
    * @param index the index of the value to bubble up. 
    */
   private void bubbleUp(int index){
      if (index == 0) // If arrived at root, 
         return;
      int parentIndex = (index-1)/2; // Parent Index
      // If the child is bigger than the parent (not in order)
      if(data[index].compareTo(data[parentIndex])>0){ 
         swap(index, parentIndex); // swap parent and child
         bubbleUp(parentIndex); // Compare parent with its parent. 
      }
   }
   
   /**
    * Ensures the element of the root reaches the lowest position
    * possible. 
    * @param index the index of the value to trickle down.
    */
   private void trickleDown(int index){
      int left = 2*index+1; // Left child index
      int right = 2*index+2;  // Right child index
      boolean trickleLeft;
      if (left >= size && right >= size) // Stop if at leaf
         return;
      else if (right >= size) // If only have left child
         trickleLeft = true;
      else // Have to choose larger of the 2 children
         trickleLeft = data[left].compareTo(data[right]) >= 0;
      // Swap elements with left or right if child greater than parent.
      if(trickleLeft){
         if (data[index].compareTo(data[left]) < 0){
            swap(index,left);
            trickleDown(left);
         }
      }
      else{
         if (data[index].compareTo(data[right]) < 0){
            swap(index,right);
            trickleDown(right);
         }
      }
      // Stop swapping once neither swap can be made. 
   }
   
   /**
    * Swaps elements at 2 indices. 
    * @param index1 First index to swap with.
    * @param index2 Second index to swap with.
    */
   private void swap(int index1, int index2){
      E temp = data[index1];
      data[index1] = data[index2];
      data[index2] = temp; 
   }
   
   /**
    * If the array reaches its length, a new array is created with twice
    * the size, and all the elements are copied to their corresponding
    * locations. 
    */
   @SuppressWarnings("unchecked")
   private void expand(){
      int initial = data.length; // Record initial length
      E[] newData = (E[]) new Comparable[initial*2]; // Make new array 2x long
      for(int i = 0; i < initial; i++) // Copy elements over to corr. indices
         newData[i] = data[i];
      data = newData; // Assign the newly created array to data. 
   }

}
