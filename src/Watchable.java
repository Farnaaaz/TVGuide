//-------------------------------------------------------------------------------------------------------------------------------------------
// Assignment 4 
// PART II
// Written by: Farnaz Zaveh, ID: 40032389
// For COMP 249-S - Winter 2021 
// 
// // This program design and implement a TV Guide which will determine if a user can watch a specific show
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

//an interface which has a method string isOnSameTime 
public interface Watchable 
{
	String isOnSameTime (TVShow S);//this method takes an object of type TVShow
}

