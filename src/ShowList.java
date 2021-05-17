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

import java.util.NoSuchElementException;

public class ShowList 
{	
	//inner class
	public class ShowNode implements Cloneable
	{	
		//initializing attributes
		private TVShow dataTVShow;     //ShowNode object
		private ShowNode nextShowNode; //pointer to ShowNode object
		
		/**
		  * default constructor 
		  */
		public ShowNode(){
			this.dataTVShow = null;
			this.nextShowNode = null;
		}
		
		/**
		  * parameterized constructor 
		  * @param TVShow and ShowNode objects
		  */
		public ShowNode(TVShow show, ShowNode objShNode){
			this.dataTVShow = show;
			this.nextShowNode = objShNode;
		}
		
		/**
		  * copy constructor 
		  * @param ShowNode object
		  */
		public ShowNode(ShowNode objShNode){
			this.dataTVShow = new TVShow(objShNode.dataTVShow.getShowID(),
					objShNode.dataTVShow.getShowName(),
					objShNode.dataTVShow.getStartTime(),
					objShNode.dataTVShow.getEndTime());

			this.nextShowNode = objShNode.nextShowNode;
		}
		
		/**
		 * @Override clone method
		*/
		protected Object clone() throws CloneNotSupportedException {
			ShowNode cloneShowNode = (ShowNode)super.clone();
			cloneShowNode.dataTVShow = new TVShow(this.dataTVShow.getShowID(),
					this.dataTVShow.getShowName(),
					this.dataTVShow.getStartTime(),
					this.dataTVShow.getEndTime());
			
			return cloneShowNode;
		}

		/*1)This accessor method may result in privacy leak
		  2)It returns a copy of reference to the private composite attribute dataTVShow 
		    which can be used to modify it from outside this class, resulting in 
		    inconsistency*/
		public TVShow getDataTVShow() {
			return dataTVShow;
		}
		
		//mutator method
		public void setDataTVShow(TVShow objTVShow) {
			this.dataTVShow = objTVShow;
		}

		/*1)This accessor method may result in privacy leak
		  2)It returns a copy of reference to the private composite attribute nextShowNode 
		    which can be used to modify it from outside this class, resulting in 
		    inconsistency*/
		public ShowNode getNextShowNode() {
			return nextShowNode;
		}
		
		//mutator method
		public void setNextShowNode(ShowNode objShowNode) {
			this.nextShowNode = objShowNode;
		}
	}//end of inner class
	
	//initializing attributes
	private ShowNode head;
	private int size;
	
	/**
	  * default constructor 
	  */
	public ShowList(){
		this.head = null;
		this.size = 0;
	}
	
	/**
	  * copy constructor
	  * that accepts a ShowList object and creates a copy of it
	  */
	public ShowList(ShowList s_list){
		try 
		{
			this.head = (ShowNode)s_list.head.clone(); //clone the passed list head
			
			ShowNode s_list_node = s_list.head; 
			ShowNode this_list_node = this.head;
			
			for(int i=1; i<s_list.size; i++){
				s_list_node = s_list_node.getNextShowNode();
				
				TVShow show = new TVShow (s_list_node.dataTVShow.getShowID(),
						s_list_node.dataTVShow.getShowName(),
						s_list_node.dataTVShow.getStartTime(),
						s_list_node.dataTVShow.getEndTime());
				ShowNode newNode = new ShowNode(show, s_list_node.getNextShowNode());
				
				this_list_node.setNextShowNode(newNode);
				this_list_node = this_list_node.getNextShowNode();
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		this.size = s_list.size;
	}
	
	/**
	 * This method accepts one parameter(a TVShow object) 
	 * creates a node with that passed object,
	 * then inserts this node at the head of the list
	 * @param TVShow object
	 */
	public void addToStart(TVShow tvShow)
	{
		ShowNode newNode = new ShowNode(); //create a new ShowNode
		newNode.dataTVShow = tvShow;       
		newNode.nextShowNode = null;	   
		
		if(size == 0){                     //if the list is empty
			this.head = newNode;		   // add it as head
		}
		else
		{
			newNode.nextShowNode = this.head;
			this.head = newNode;
		}
		this.size++; 
	}
	
	/**
	 * This method accepts two parameters
	 * @param TVShow object
	 * @param (int) index 
	 * [a valid index must have a value between 0 and size-1]
	 * If the index is valid, the method creates a node with the passed TVShow object 
	 * and inserts this node at the given index.
	 * If the index is not valid the method throw a NoSuchElementException and terminate the program
	 */
	public void insertAtIndex(TVShow tvShow, int index){
		if(index < 0 || index > (this.size-1)){ //if index not valid 
			try
			{
				throw new NoSuchElementException(); //throw the exception
			}
			catch(NoSuchElementException ex)
			{
				System.exit(0); //terminate the program
			}
		}
		else{ //if index is valid creates a node with the passed TVShow object
			ShowNode newNode = new ShowNode(); 
			newNode.dataTVShow = tvShow;
			newNode.nextShowNode = null;
			
			//handling special cases 
			if(index == 0){ 
				newNode.nextShowNode = this.head;
				this.head = newNode;
			}
			else{
				ShowNode correctPrevNode = this.head;
				for(int i=1; i<index; i++){ //move the nodes
					correctPrevNode = correctPrevNode.nextShowNode;
				}
				
				newNode.nextShowNode = correctPrevNode.nextShowNode;
				correctPrevNode.nextShowNode = newNode;
			}
			this.size++; //update the size 
		}
	}
	
	/**
	 * This method accepts one integer parameter representing an index
	 * @param index
	 * If the index is valid, the method will delete the node pointed by that index, from the list
	 * If the index is not valid the method throw a NoSuchElementException and terminate the program
	 */
	public void deleteFromIndex(int index){
		if(index < 0 || index > (this.size-1)){ //if index not valid 
			try
			{
				throw new NoSuchElementException(); //throw the exception
			}
			catch(NoSuchElementException ex){ 
				System.exit(0); //terminate the program
			}
		}else{ //if index is valid delete the node pointed by the index
			if(index == 0){
				this.head = this.head.nextShowNode; //for index 0, 
			}else{
				ShowNode correctPrevNode = this.head;
				for(int i=1; i<index; i++){ //move the nodes
					correctPrevNode = correctPrevNode.nextShowNode;
				}
				correctPrevNode.nextShowNode = correctPrevNode.nextShowNode.nextShowNode;
			}
			this.size--; //update the size 
		}
	}
	/**
	 * This method deletes the first node in the list
	 */
	public void deleteFromStart(){
		this.head = this.head.nextShowNode;
		this.size--;
	}
	
	/**
	 * This method accepts two parameters
	 * @param TVShow object
	 * @param index
	 * If the index is not valid, the method returns
	 * otherwise, the object in the node at the passed index is to be replaced by the passed object
	 */
	public void replaceAtIndex(TVShow tvShowReplacement, int index){
		if(index < 0 && index > (this.size-1)){ //if index not valid return
			return;
		}else{ //if index is valid 
			if(index == 0){ //special case
				this.head.dataTVShow = tvShowReplacement;
			}else{
				ShowNode correctPrevNode = this.head; //replace the passed object
				for(int i=1; i<index; i++){
					correctPrevNode = correctPrevNode.nextShowNode;
				}
				correctPrevNode.nextShowNode.dataTVShow = tvShowReplacement;
			}
		}
	}
	
	/*1)This accessor method may result in privacy leak
	  2)It returns a copy of reference to the private attribute head 
	    which can be used to modify it from outside this class, resulting in 
	    inconsistency*/
	public ShowNode getHead() {
		return head;
	}
	
	//mutator 
	public void setHead(ShowNode head) {
		this.head = head;
	}

	/*1)This accessor method may result in privacy leak
	  2)It returns a copy of reference to the private attribute size 
	    which can be used to modify it from outside this class, resulting in 
	    inconsistency*/
	public int getSize() {
		return size;
	}
	
	//mutator
	public void setSize(int size) {
		this.size = size;
	}
	
	//this method may cause privacy leak because it returns a node.
	//it can be solved by returning a clone of that node.
	/**
	 * @param s_ID
	 * @param showIndex
	 * This method searches the list for a ShowNode with the passed showID(s_ID) 
	 * If such an object is found, then the method returns a pointer to that ShowNode
	 * otherwise, the method returns null. 
	 * The method keeps track of iterations
	 * @return showNode/null
	 */
	public ShowNode find(String s_ID, boolean showIndex) //the boolean parameter defines if we need to print the number of iterations
	{
		ShowNode currentNode = this.head;
		int index=0; //iterator
		for(int i=0; i<this.size ;i++) 
		{
			index = i+1;
			if(currentNode.dataTVShow.getShowID().equals(s_ID))
			{
				if(showIndex)
					System.out.println("No. of iterations required to find TV Show with id "+ s_ID + " is = " + index);
				
				return currentNode; //returning the pointer
			}
			else
				currentNode = currentNode.nextShowNode; //go to the next Node
		}
		System.out.println(s_ID + " was not found after " + index + " terations."); //if not in the list
		return null; 
	}
	
	/**
	 * This method accepts @param s_id (showID)
	 * @return true if a course with that showID is in the list
	 * otherwise, the method returns false
	 * 
	 */
	public boolean contains(String s_id){
		ShowNode currentNode = this.head;
		//int index = 0;
		for(int i=0; i<this.size; i++){
			//index = i+1;
			if(currentNode.dataTVShow.getShowID().equals(s_id))
				return true;
			else
				currentNode = currentNode.nextShowNode;
		}
		return false;
	}
	
	/**
	 * @override
	 * This method accepts one parameter of type ShowList  
	 * @return true if the two lists contain similar shows
	 * otherwise, the method returns false
	 */
	public boolean equals(ShowList s_list){
		if(!(s_list.size == this.size)) //return false if the lists are of different sizes
			return false;
		else{
			boolean isEqual = false;
			ShowNode thisListNode = this.head;
			ShowNode s_listNode = s_list.head;
			
			for(int i=0; i<this.size; i++){ //check the Nodes one by one
				if(thisListNode.getDataTVShow().getShowName().equals(s_listNode.getDataTVShow().getShowName())){	
					isEqual = true;
					thisListNode = thisListNode.nextShowNode;
					s_listNode = s_listNode.nextShowNode;
				}else{
					isEqual = false;
					break;
				}
			}
			return isEqual;
		}
	}
}
