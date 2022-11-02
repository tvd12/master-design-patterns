package com.tvd12.designparttern.flyweight;

/**
 * 
 * @author tavandung12
 * @last_modified: Aug 18, 2015
 * 
 * Driver : War Game
 * 
 */
public class SoldierExample {
	public static void main(String[] args) {
		//start war
		
		//draw war terrain
		
		//create 5 soldiers
		SoldierClient warSoldiers[] = {
			new SoldierClient(),	
			new SoldierClient(),
			new SoldierClient(),
			new SoldierClient(),
			new SoldierClient(),
		};
		
		// move each soldier to his location 
		// take user input to move each soldier 
		warSoldiers[0].moveSoldier(17, 2112);
				
		// take user input to move each soldier 
		warSoldiers[1].moveSoldier(137, 112);
				
		// note that there is only one SoldierImp ( flyweight Imp)
		// for all the 5 soldiers 
		// Soldier Client size is small due to the small state it maintains
		// SoliderImp size might be large or might be small 
		// however we saved memory costs of creating 5 Soldier representations
	}
}

interface Soldier {
	/**
	 * Move Soldier from old location to new location Note that solder location
	 * is extrinsic to the SoldierFlyweight implementation
	 * 
	 * @param previousLocationX
	 * @param previousLocationY
	 * @param newLocationX
	 * @param newLocationY
	 */
	public void moveSoldier(
			int previousLocationX, 
			int previousLocationY, 
			int newLocationX, 
			int newLocationY);
}

class SoldierImp implements Soldier {

	/**
	 * Note that this method accepts soldier location Soldier Location is
	 * Extrinsic and no reference to previous location or new location is
	 * maintained inside the flyweight implementation
	 */
	public void moveSoldier(int previousLocationX, 
							int previousLocationY, 
							int newLocationX, 
							int newLocationY) {

		// delete soldier representation from previous location
		// then render soldier representation in new location
	}

	/**
	 * Intrinsic state maintained by flyweight implementaion Soldier Shape
	 * (graphical representation) How to display the soldier is up to the
	 * flyweight implementation
	 */
	@SuppressWarnings("unused")
	private Object mSoldierGraphicalRepresentation;
}

class SoldierFactory {
	
	public static Soldier getSoldier() {
		if(sSoldier == null) {
			sSoldier = new SoldierImp();
		}
		
		return sSoldier;
	}
	
	/**
	 * Pool for one soldier only
	 * If there are more soldier types
	 * This can be an array of list of better a HashMap
	 */
	public static Soldier sSoldier;
}

/**
 * This is the "Heavyweight" soldier object
 * which is the client of the flyweight soldier
 * this object provides all soldier services and is used in the game 
*/
class SoldierClient {
	
	public SoldierClient() {
		mSoldier = SoldierFactory.getSoldier();
		
		mCurrentLocationX = 0;
		mCurrentLocationY = 0;
	}
	
	public void moveSoldier(int newLocationX, int newLocationY) {
		
		//here the actual rendering is handled by the flyweight object
		mSoldier.moveSoldier(
				mCurrentLocationX,
				mCurrentLocationY,
				newLocationX,
				newLocationY);
		
		//this object is responsible for maintaining the state
		//that is extrinsic to the flyweight
		mCurrentLocationX = newLocationX;
		mCurrentLocationY = newLocationY;
	}
	
	/**
	 * This state is maintained by the client
	 */
	private int mCurrentLocationX;
	
	private int mCurrentLocationY;
	
	/**
	 * Reference to the flyweight
	 */
	private Soldier mSoldier;
}