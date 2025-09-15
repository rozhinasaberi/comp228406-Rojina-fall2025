package exercise1;
//Create a class named Singers with the following specifications:
//5 instance variables that would store the following singer data (Use recommended variable naming conventions and appropriate data type for each instance variable):  
//Singers id
//Singers name
//Singers address
//Date of birth
//Number of albums published
//Two constructors that would allow you to construct Singer object with no arguments and 5 arguments.

public class Singers {

    private int singerId;
    private String singerName;
    private String singerAddress;
    private String dateOfBirth;
    private int numberOfAlbumsPublished;
 // no arguments
    public Singers() {
        this.singerId = 0;
        this.singerName = "---";
        this.singerAddress = "---";
        this.dateOfBirth = "---";
        this.numberOfAlbumsPublished = 0;
    }

//5 arguments
    public Singers(int singerId, String singerName, String singerAddress, String dateOfBirth, int numberOfAlbumsPublished) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerAddress = singerAddress;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }
     // Create Setters and getters for all the instance variables of class Singer.
     
   
    
    // Make sure to have several setters that would allow you to set the values of individual instance variables of the singer object.
    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setSingerAddress(String singerAddress) {
        this.singerAddress = singerAddress;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNumberOfAlbumsPublished(int numberOfAlbumsPublished) {
        this.numberOfAlbumsPublished = numberOfAlbumsPublished;
    }
     // Also create one setter that would allow you to set all the values of the instance variables at once. 
     
      public void setSingerDetails(int singerId, String singerName, String singerAddress, String dateOfBirth, int numberOfAlbumsPublished) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.singerAddress = singerAddress;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbumsPublished = numberOfAlbumsPublished; }

   //Create several getters that would allow you to get the current individual values of each instance variables of the Singer object.
    public int getSingerId() {
        return singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public String getSingerAddress() {
        return singerAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getNumberOfAlbumsPublished() {
        return numberOfAlbumsPublished;
    }

   // Set the values of each instance variables with the help of the setter that sets all the values. Display the values. 

   // Now change the value of each instance variable using setter for each instance variable. 
   // Display current value of each after the changes are done. Use getters for each to accomplish this.

    public void displaySingerInfo() {
        System.out.println("Singer ID: " + singerId);
        System.out.println("Name: " + singerName);
        System.out.println("Address: " + singerAddress);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Number of albums published " + numberOfAlbumsPublished);
        System.out.println("----------");
    }
}
