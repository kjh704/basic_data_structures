import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * List12 is an implementation of a Singly Linked List. 
 * @author Andrew Tsun, login: cs12soo
 * @version Programming Assignment 2
 * @param <E> generic type of List12
 */
public class List12<E> implements java.util.List<E>{
   
   /**
    * Node class for List12
    * @author andrew_tsun
    * @param <F> generic type of List12
    */
   class List12Node<F>{
      F data; // 
      List12Node<F> next; 
      
      /**
       * Generates a List12Node with no data value, and no next node.
       */
      List12Node(){
         this(null, null); 
      }
      
      /**
       * Generates a node, fills it with data, does not point it to 
       * any other node. 
       * @param info Data to be stored in List12Node
       */
      List12Node(F info){
         data = info; 
         next = null; 
      }
      
      /** 
       * Generates a node, fills it with data, and points it to the node specified 
       * in the parameters.
       * @param info Data to be stored in List12Node
       * @param after Target node that this node points to. 
       */
      List12Node(F info, List12Node<F> after){
         data = info; 
         next = after; 
      }
      
      /**
       * Allows the data contained within this node to be accessed.
       * @return The data stored in this node
       */
      F getData(){return data;}
      
      /**
       * Allows for this node to store new data.
       * @param newValue The data to set the node to
       */
      void setData(F newValue){data = newValue;}
      
      /**
       * Access the target node this node points to.
       * @return the node this node points to
       */
      List12Node<F> getNext(){return next; }
      
      /**
       * Change the target node this node points to.
       * @param newNext the new node this node needs to point to.
       */
      void setNext(List12Node<F> newNext){next = newNext;}
   }
   
   private List12Node<E> head; // First node in List12
   private int size; // Number of elements in List12
   private int modCount; // Number of times this list has been modified 
   
   /**
    * Constructs an empty List12 with size 0, no head node.
    */
   public List12(){
      head = null; 
      size = 0;
      modCount = 0; 
   }
   
   /**
    * Adds an element to the end of the List12. 
    * @param insert element to add to the end of the List12.
    * @return true as specified by the Collections API.
    */
   public boolean add(E insert) {
      if (size == 0){ // At the beginning of the list.
         head = new List12Node<E>(insert); 
      }
      else{
         List12Node<E> cursor = head; // Track current node.
         while(cursor.getNext() != null){ // Traverse to end node.
            cursor = cursor.getNext();
         }
         // Create new node with new data
         List12Node<E> append = new List12Node<E>(insert); 
         // Set last node to point to the newly created node.
         cursor.setNext(append);
      }
      size++; // Increase node
      modCount++; 
      return true; // Return true for successful add operation.
     
   }
   
   /**
    * Adds an element to a specified index. 
    * @param index to add insert to. 
    * @param insert The element to add to List12.
    * @throws IndexOutOfBoundException if index is below 0, or higher than 
    * number of elements in thhis List12.
    */
   public void add(int index, E insert) {
      // Throw IndexOutOfBoundsException if index is below 0, higher than size
      if (index < 0 || index >= size)
         throw new IndexOutOfBoundsException();
      // Case for adding to empty list. 
      if (index == 0){
         head = new List12Node<E>(insert); 
         return;
      }
      List12Node<E> cursor = head; // Track node as it cycles through.
      for(int i = 0; i < index - 1; i++){ // Cycle to node before insertion point
         cursor = cursor.getNext();
      }
      // Create new node pointing to same node cursor points to.
      List12Node<E> append = new List12Node<E>(insert, cursor.getNext());
      cursor.setNext(append); // Set cursor to point to newly created node
      size++; // Increase size by 1
      modCount++; 
   }

   public boolean addAll(Collection<? extends E> arg0) {
      throw new UnsupportedOperationException(); 
   }

   public boolean addAll(int arg0, Collection<? extends E> arg1) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }
   
   /**
    * Checks to see if a given object is contained in this List12.
    * @param obj Object to look for.
    */
   public boolean contains(Object obj) {
      List12Node<E> cursor = head;
      E element; 
      // Cast object into E for safe comparison.
      try{ 
         element = (E) obj;
      }
      // If object is not even same type, don't bother looking for it.
      catch (ClassCastException e){return false;}
      // Cycle until last node reached. 
      if(size == 1){
         if (element == null)
            return head.getData() == null; 
         else
            return element.equals(head.getData());
      }
      while(cursor.getNext()!= null){
         if (element == null && cursor.getData() == null)
            return true;
         else if (element != null) // Safeguard against NullPtrException
            if (element.equals(cursor.getData()))
               return true; 
         cursor = cursor.getNext(); 
      }
      // Check last node
      if (element == null && cursor.getData() == null)
         return true;
      else if (element != null) // Safeguard against NullPtrException
         if (element.equals(cursor.getData()))
            return true;  
      // If object not reached, return false.
      return false;
   }

   public boolean containsAll(Collection<?> arg0) {
      throw new UnsupportedOperationException();
   }
   
   /** 
    * Allows for access to an object at a given position in this List12.
    * @param index The position to access.
    * @return The object at the specified position.
    * @throws IndexOutOfBoundsException if index is below zero or above 
    * the number of elements in this List12.
    */
   public E get(int index) {
      if (index < 0 || index >= size){
         throw new IndexOutOfBoundsException();
      }
      if (index == 0){
         return head.getData();
      }
      List12Node<E> cursor = head; 
      for(int i = 0; i < index; i++){
         cursor = cursor.getNext();
      }
      return cursor.getData();
   }

   public int indexOf(Object arg0) {
      throw new UnsupportedOperationException();
   }

   public boolean isEmpty() {
      throw new UnsupportedOperationException();
   }
   
   /** 
    * Returns an iterator over this List12, classified as a List12Iterator.
    */
   public Iterator<E> iterator() {
      return new List12Iterator();
   }
   
   /**
    * List12Iterator allows for one way traversal of List12, starting at the 
    * head node, and traversing through the nodes' getNext(). 
    * @author andrew_tsun
    */
   class List12Iterator implements Iterator<E>{
      // Create 2 dummy nodes before head node.  
      List12Node<E> nextNode; // Track current node
      List12Node<E> lastReturned; // Hold node last visited 
      List12Node<E> predecessor; // Hold node before last visited. 
      int expectedModCount; // # of modifications before instantiation
      
      /**
       * Creates List12 iterator starting at a dummy node before the head node, 
       * index at -1 to compensate for dummy node. Calling remove after 
       * construction will lead to IllegalStateException.
       */
      List12Iterator(){
         nextNode = head;
         lastReturned = new List12Node<E>(null, nextNode);
         predecessor = new List12Node<E>(null, lastReturned);
         expectedModCount = modCount;
      }
      
      /**
       * Checks to see if a next node exists.
       */
      public boolean hasNext() {
         return nextNode != null;
      }
      
      /**
       * Traverse to next node, return the data stored in the node. 
       * @return info Object stored in the next node.
       */
      public E next(){
         if(expectedModCount != modCount)
            throw new ConcurrentModificationException();
         if(!hasNext()) // If at end of list, 
            throw new NoSuchElementException(); // Indicate no more elements
         lastReturned = nextNode;
         nextNode = nextNode.getNext();
         predecessor = predecessor.getNext();
         return lastReturned.getData();
      }
      
      /**
       * Removes last element called by next. 
       * @throws IllegalStateException if remove has already been called
       * after next, or if next has not been called yet. 
       */
      public void remove() {
         if(expectedModCount != modCount)
            throw new ConcurrentModificationException();
         if (lastReturned == null) // throw if remove called inappropriately
            throw new IllegalStateException();
         predecessor.setNext(lastReturned.getNext());
         nextNode = nextNode.getNext(); 
         lastReturned = null; 
         size--;
      }
   }

   public int lastIndexOf(Object arg0) {
      throw new UnsupportedOperationException();
   }

   public ListIterator<E> listIterator() {
      throw new UnsupportedOperationException();
   }

   public ListIterator<E> listIterator(int arg0) {
      throw new UnsupportedOperationException();
   }

   /**
    * Removes a specific object in this List12.
    * @return true for successful removal
    * @return false for unsuccessful removal
    */
   public boolean remove(Object rm) {
      E element; // Attempt casting into E
      try{
         element = (E) rm;
      }
      // If not same type, don't bother removing.
      catch (ClassCastException e){return false;}
      // If not contained in List12, don't bother removing.
      if(!contains(element))
         return false;
      // If removing 1st element and it is null. 
      if (element == null && head.getData() == null){
         head = head.getNext();
         size--; 
         modCount++;
         return true;
      }   
      // If removing at the first element
      else if (element != null){ // safeguard against nullptrexception
         if (element.equals(head.getData())){
            head = head.getNext(); 
            size--;
            modCount++;
            return true;
         }
      }
      // Remove at other elements
      List12Node<E> cursor = head.getNext();
      List12Node<E> predecessor = head;
      while(cursor.getNext() != null){
         if (element == null && cursor.getData() == null){
            predecessor.setNext(cursor.getNext());
            break;
         }
         else if (element != null){  // safeguard against nullptrexception    
            if (element.equals(cursor.getData())){
               predecessor.setNext(cursor.getNext());
               break; 
            }
         }
         cursor = cursor.getNext();
         predecessor = predecessor.getNext(); 
      }
      size--;
      modCount++;
      return true; 
   }
   
   /**
    * Removes an object from this List12 at a given position.
    * @return removedData the element removed from this List12
    * @throws IndexOutOfBoundsException if index is below 0 
    * or above the size of this array
    */
   public E remove(int index) {
      if (index < 0 || index >= size) // Throw exception if index not in bounds
         throw new IndexOutOfBoundsException();
      E removedData; 
      if (index == 0){ // Removal from beginning
         removedData = head.getData();
         if (size == 1) // Reassign head if size = 1
            head = null; 
         else
            head = head.getNext(); // Reassign head to next node. 
      }
      else{
         List12Node<E> cursor = head.getNext(); // Track current node.
         List12Node<E> predecessor = head; 
         for(int i = 1; i < index; i++){ // Traverse to node of.
            cursor = cursor.getNext();
            predecessor = predecessor.getNext(); 
         }
         removedData = cursor.getData();
         predecessor.setNext(cursor.getNext()); 
      }
      size--; // Decrement size 
      modCount++; // modification made outside of iterator
      return removedData; // Return data removed. 
   }

   @Override
   public boolean removeAll(Collection<?> arg0) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean retainAll(Collection<?> arg0) {
      throw new UnsupportedOperationException();
   }
   
   /**
    * Allows for modification of an element at a given lcoation
    * @param index The position of the element in List12 to modify.
    * @param newData The data to overwrite the node at position index.
    * @return oldData The previous data in the node at position index. 
    * @throws IndexOutOfBoundsException If index is below 0 or above the
    * number of elements in this List12.  
    */
   public E set(int index, E newData) {
      if (index < 0 || index >= size) // Throw exception if out of bounds
         throw new IndexOutOfBoundsException(); 
      E oldData; 
      if (index == 0){
         oldData = head.getData();
         head.setData(newData);
         return oldData; 
      }
      List12Node<E> cursor = head; // Track current node
      for(int i = 0; i < index; i++){ // Traverse to desired node.
         cursor = cursor.getNext();
      }
      oldData = cursor.getData(); // Record previous data.
      cursor.setData(newData); // Set to new data.
      return oldData; // Return previous data.
   }
   /** Returns the size of List12.
    * @return size The number of elements in this List12
    */
   public int size() {
      return size;
   }

   public List<E> subList(int arg0, int arg1) {
      throw new UnsupportedOperationException();
   }

   public Object[] toArray() {
      throw new UnsupportedOperationException();
   }

   public <T> T[] toArray(T[] arg0) {
      throw new UnsupportedOperationException();
   }
   
   public Object clone(){
      List12<E> copy = new List12<E>();
      List12Node<E> cursor = head;
      while(cursor.getNext() != null){ 
         copy.add(cursor.getData());
         cursor = cursor.getNext();
      }
      copy.add(cursor.getData()); 
      return copy;
   }
}
