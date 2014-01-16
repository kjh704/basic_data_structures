import java.util.ArrayList;
import java.util.List;

/**
 * TimeList compares the efficiency of ArrayLists and List12s (Singly Linked 
 * Lists) with regards to adding to the front, or to the back of the list.
 * After typing in java Timelist, two parameters must be given: 
 * <ul>
 * <li> linked/array: Determines whether a List12 
 * or an ArrayList object will be analyzed. </li> 
 * <li> front/back: Determines whether items shall be added to the front
 * or the back of the list specified </li>
 * </ul>  
 * e.g. java TimeList linked front
 * <br> Results will be given in 3 columns 
 * <ul>
 * <li> n, the number of elements to add to the list </li>
 * <li> average time it takes to add n elements to the list</li>
 * <li> standard deviation of time it takes to add n elements to the list </li>
 * </ul> 
 * for each n starting from 2000, ending at 5000, increasing in increments
 * of 1000. The resulting output can be redirected to a text file, and 
 * further analyzed with GNUPlot.  
 * 
 * @author Andrew Tsun, login: cs12soo
 * @version Programming Assignment #2
 */
public class TimeList {
   
   private boolean isLinked; // records List12 or ArrayList 
   private boolean inFront; // records adding to front or back
   
   /**
    * Initializes TimeList's type of list and point of insertion.  
    * @param linked Whether the list analyzed is a List12 list or ArrayList.
    * @param front WHether items will be added to front or back of the list.
    */
   public TimeList(boolean linked, boolean front){
      isLinked = linked; 
      inFront = front; 
   }
   
   /**
    * Analyzes the command arguments and runs the test according to user specs. 
    * @param args Takes in 2 words: <ul>
    * <li>linked/array: determines type of list to analyze </li>
    * <li>front/back: determines where to insert elements </li>
    * <ul>
    */
   public static void main(String[] args){
      boolean linked = false; // true = List12, false = ArrayList
      // true = insert from front, false = insert from back
      boolean front = false; 
      
      /* Analyze command line arguments */
      try{
         // Set type of list to be tested
         if (args[0].equals("linked"))
            linked = true;
         else if (args[0].equals("array"))
            linked = false;
         else 
            throw new Exception();
         // Set position to insert 
         if (args[1].equals("front"))
            front = true;
         else if (args[1].equals("back"))
            front = false;
         else
            throw new Exception(); 
      }
      catch(Exception e){ // If argument invalid,
         printUsage(); // Inform on proper usage
         System.exit(1); // Terminate program
      }
      
      TimeList tl = new TimeList(linked, front); // Initialize the test 
      tl.runTest(); // Run the test
   }

   /**
    * Prints the java command to use when running this program.
    * First word determines the type of list to be analyzed.
    * Second word determines the place to insert new values. 
    * See class description for usage.
    */
   public static void printUsage(){
      System.out.println("Usage: Ex. java TimeList linked front");
      System.out.println("First word is either linked/array: ");
      System.out.println("- linked will run test on a List12 object");
      System.out.println("- array will run test on an ArrayList object");
      System.out.println("Second word is either front/back: ");
      System.out.println("- front specifies adding objects to beginning");
      System.out.println("- back specifies adding objects to end");
   }
   
   /**
    * Runs the test of adding n items to a list, 
    * starting at 2,000 items, and ending at 50,000 items.
    * Increments are per 1,000 items. Results are formatted by 
    * n items, average of run times, standard deviation of run times. 
    * Each value of n has its own line. Output is to standard output.
    */
   public void runTest(){
      for(int n = 2000; n <= 50000; n+= 1000){
         System.out.println(testOutput(n)); 
      }
   }

   /**
    * Creates a string with the number of n values to add to a list, 
    * the average time it takes to add n values to the list,
    * and the standard deviation of adding n values to the list. 
    * @param n The number of items to add to a list.
    * @return A string with number of values added, 
    * average, and standard deviation
    */
   public String testOutput(int n){
      int r = 100000/n; // # of runs
      double[] values = new double[r]; 
      double avg = 0; // Mean
      double stdDev = 0; // Standard Deviation
      for(int i = 0; i < r ; i++){ // Add run values to array
         values[i] = testTime(n); 
      }
      avg = calcAvg(values); // Calculate average
      stdDev = calcStdDev(values, avg); // Calculate std. dev.
      // Formulate output string and return it. 
      String output =  String.format("%5d \t %1.6f \t\t %1.6f", n, avg, stdDev);
      return output; 
   }
   
   /**
    * Adds elements to a list, and calculates how long it takes to do so 
    * List type is determined by isLinked.
    * Adding to front or back is determined by inFront 
    * @param n The number of elements to add to a list
    * @return The number of seconds it takes to add n elements to a list
    */
   public double testTime(int n){
      List<Integer> testList = null; // List to run test on
      // Determine if test is to be run on linked or array list
      if (isLinked)
         testList = new List12<Integer>();
      else
         testList = new ArrayList<Integer>();
      
      // Instantiate time recording variables
      double second = 0; // Test time in seconds 
      long start, end = 0; // Starting and end times in nanoseconds
      
      if(inFront){ // Add to front
         System.gc(); // Run garbage collector
         testList.add(new Integer(5));
         start = System.nanoTime(); // Record start time
         // Add to front n times
         for(int i = 1; i <= n; i++){
            testList.add(0, new Integer(1));
         }
         end = System.nanoTime(); // Record end time
      }
      else{ // Add to back
         System.gc(); // Run garbage collector
         start = System.nanoTime(); // Record start time
         // Add to back n times
         for(int i = 1; i < n; i++){
            testList.add(new Integer(1)); 
         }
         end = System.nanoTime(); // Record end time
      }
      
      second = (end - start) / 1.0e9; // Calculate time in seconds
      return second; // Return value
   }
   
   /**
    * Calculates the arithmetic mean of a set of values
    * @param set Set of values to analyze.
    * @return the arithmetic mean of the set. 
    */
   public double calcAvg(double[] set){
      double sum = 0; // Sum of values
      for(int i = 0; i < set.length; i++){
         sum += set[i]; 
      }
      return sum/(set.length); // Returns average of the sum of values.
   }
   
   /**
    * Calculates the uncorrected standard deviation for a set. 
    * @param set Set of values to analyze
    * @param avg The mean of the set. 
    * @return The standard deviation of the set. 
    */
   public double calcStdDev(double[] set, double avg){
      double sumSqDevs = 0; // sum of the squared deviations
      for(int i = 0; i < set.length; i++){
         double deviation = set[i] - avg; // get deviation
         deviation = Math.pow(deviation, 2); // square deviation
         sumSqDevs += deviation; // add to sum of deviations
      }
      double variance = sumSqDevs/set.length; // take the variance
      return Math.sqrt(variance); // std. dev = square root of variance
   }

}
