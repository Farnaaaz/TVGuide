import java.util.Scanner;

public class TVShow implements Cloneable, Watchable{
	
	private String showID;
	private String showName;
	private double startTime;
	private double endTime;
	
	 
	/**
	  * parameterized constructor 
	  */
	public TVShow (String s_id, String s_name, double start_time, double end_time) {
		this.showID = s_id;
		this.showName = s_name;
		this.startTime = start_time;
		this.endTime = end_time;
	}
	
	/**
	  * copy constructor 
	  * @param (TVShow) show, (String) s_id
	  */
	public TVShow (TVShow show, String s_id) {
		this.showID = s_id;
		this.showName = show.showName;
		this.startTime = show.startTime;
		this.endTime = show.endTime;
	}
	
	/**
	 * @Override clone method
	 * @return the cloned TVShow (TVShow)
	 * This method prompt the user to enter a new showID, 
	 * then creates and returns a clone of the calling object
	 * with the exception of the showID, which is assigned the value entered by the user
	*/
	protected Object clone() throws CloneNotSupportedException {
		Scanner sc = new Scanner(System.in); //initialize the scanner object
		System.out.println("Enter a new Show ID"); //prompt the user
		String s_id = sc.nextLine(); //read user input
		
		TVShow cloneTVShow = (TVShow) super.clone(); //invoke the clone method of the base class
		cloneTVShow.showID = s_id; //reset the values of the instance variables using 
		                           //"showID, which is assigned the value entered by the user"
		
		sc.close(); //close the scanner
		return cloneTVShow;
	}
	
	
	public boolean equals (Object obj) {
		if(this == obj) // returns true if they have the same reference
			return true;
		
		if(!(obj instanceof TVShow)) // returns false if obj is not an instance of TVshow
			return false;
		
		TVShow show = (TVShow) obj; // cast obj as TVShow class
		
		return (this.showName.equals(show.showName) && //return true if shows have the same name
				(this.startTime == show.startTime) &&  //the same start time and, 
				(this.endTime == show.endTime));       // the same end time
	}

	/**
	 * @Override toString 
	*/
	public String toString() {
		return "ID = "+this.showID+"\nName = "+ this.showName+"\n Start-Time = "+this.startTime+"\nEnd-Time = "+ this.endTime;
	}
	
	
	public String getShowID() {
		return showID;
	}
	
	
	public void setShowID(String showID) {
		this.showID = showID;
	}
	
	
	public String getShowName() {
		return showName;
	}
	
	
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	
	public double getStartTime() {
		return startTime;
	}
	
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	
	public double getEndTime() {
		return endTime;
	}
	
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	/**
	 * @Override isOnSameTim method from the class Watchable
	 * check the time of the show and return either Same time, Some Overlap or Different time
	 * depending on the start and end time of the two shows
	*/
	public String isOnSameTime(TVShow S) {
		if(this.startTime == S.startTime && this.endTime == S.endTime) { //if they start and end at the same time
			return "Same time";
		}
		else if((S.startTime>this.startTime && S.startTime<this.endTime) //check if the shows will have overlap
				|| (S.startTime>this.endTime && S.startTime<this.endTime)
				|| (this.startTime>S.startTime && this.startTime<S.endTime)
				|| (this.endTime>S.startTime && this.endTime<S.endTime))
		{
			return "Some Overlap";
		}else{
			return "Different time"; //otherwise return Different time
		}
	}
	
}
