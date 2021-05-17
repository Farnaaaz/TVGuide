//-------------------------------------------------------------------------------------------------------------------------------------------
// Assignment 4 
// PART II
// Written by: Farnaz Zaveh, ID: 40032389
// For COMP 249-S - Winter 2021 
// 
// This program design and implement a TV Guide which will determine if a user can watch a specific show
// based on shows he/she is currently watching. 
// The two input files are TVGuide.txt containing information about various TV shows, 
// and Interests.txt which contains information about the shows a user is interested in. 
// The program will parse these files to extract TV shows information and 
// will produce an outcome for each of the show a user wants to watch.
//----------------------------------------------------------------------------------------------------------------------------------------------
/**
 * Name and ID: Farnaz Zaveh - 40032389
 * Assignment# 4, PART II
 * COMP249
 * Due Date April 24th, 2021
 */
package partII;

import java.util.Scanner;

public class TVShow implements Cloneable, Watchable{
	
	//initializing attributes
	private String showID;
	private String showName;
	private double startTime;
	private double endTime;
	
	 
	/**
	  * parameterized constructor 
	  * @param (String) s_id, (String) s_name, (double) start_time, (double) end_time
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
	
	/**
	 * @Override equals method
	 * return true if shows have the same name, start time and and end time
	 * (Two shows are equal if they have the same attributes, with the exception of the showID)
	*/
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
	 * @Override toString method
	*/
	public String toString() {
		return "ID = "+this.showID+"\nName = "+ this.showName+"\n Start-Time = "+this.startTime+"\nEnd-Time = "+ this.endTime;
	}
	
	/**
	  * accessor method
	  * @return (String) showID
	  */
	public String getShowID() {
		return showID;
	}
	
	/**
	  * mutator method
	  * @param (String) showID
	  */
	public void setShowID(String showID) {
		this.showID = showID;
	}
	
	/**
	  * accessor method
	  * @return (String) showName
	  */
	public String getShowName() {
		return showName;
	}
	
	/**
	  * mutator method
	  * @param (String) startTime
	  */
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	/**
	  * accessor method
	  * @return (double) to date
	  */
	public double getStartTime() {
		return startTime;
	}
	
	/**
	  * mutator method
	  * @param (double) startTime
	  */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	
	/**
	  * accessor method
	  * @return (double) endTime
	  */
	public double getEndTime() {
		return endTime;
	}
	
	/**
	  * mutator method
	  * @param (double) endTime
	  */
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
