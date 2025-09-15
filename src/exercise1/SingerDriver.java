package exercise1;
//main class
public class SingerDriver {
    public static void main(String[] args) {
        // Create the driver class that would create 1 Singer (singer1) object with the help of the no argument constructor.
        // Display the default values of the instance variables of this object singer1.

        System.out.println("Before setting any values:");
        Singers singer1 = new Singers();
        singer1.displaySingerInfo();

        
        System.out.println("After setting all values at once:");
        singer1.setSingerDetails(1, "Taylor Swift", "Pennsylvania, USA", "1989-12-13", 10);
        singer1.displaySingerInfo();
        // Now change the value of each instance variable using setter for each instance variable.
 
        System.out.println("After changing each value individually:");
        singer1.setSingerId(2);
        singer1.setSingerName("The Weeknd");
        singer1.setSingerAddress("Toronto, Ontario, Canada");
        singer1.setDateOfBirth("1990-02-16");
        singer1.setNumberOfAlbumsPublished(5);
          
        // Display current value of each after the changes are done. 
        // Use getters for each to accomplish this.
        System.out.println("Singer ID: " + singer1.getSingerId());
        System.out.println("Name: " + singer1.getSingerName());
        System.out.println("Address: " + singer1.getSingerAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of albums published: " + singer1.getNumberOfAlbumsPublished());
        System.out.println("----------");
        
        System.out.println("Creating extra singer to show 5-argument constructor:");
        Singers singer3 = new Singers(3, "Celine Dion", "Charlemagne, Quebec, Canada", "1968-03-30", 27);
        singer3.displaySingerInfo();
    }
}
