import java.util.NoSuchElementException;

public class ShowList 
{	
	public class ShowNode implements Cloneable
	{	
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
		  */
		public ShowNode(TVShow show, ShowNode objShNode){
			this.dataTVShow = show;
			this.nextShowNode = objShNode;
		}
		
		/**
		  * copy constructor 
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

		public TVShow getDataTVShow() {
			return dataTVShow;
		}
		
		public void setDataTVShow(TVShow objTVShow) {
			this.dataTVShow = objTVShow;
		}

			public ShowNode getNextShowNode() {
			return nextShowNode;
		}
		
		//mutator method
		public void setNextShowNode(ShowNode objShowNode) {
			this.nextShowNode = objShowNode;
		}
	}//end of inner class
	
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
	
	public void deleteFromStart(){
		this.head = this.head.nextShowNode;
		this.size--;
	}
	

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

	public ShowNode getHead() {
		return head;
	}
	
	public void setHead(ShowNode head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
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
