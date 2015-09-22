package com.xseillier.psnapi.model.trophy;


/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class EarnedTrophies  {

	
	
	private int mBronze;
	private int mGold;
	private int mPlatinum;
	private int mSilver;
	
//=============================================================================
// Accessor
//=============================================================================
	
	public int getBronze() {
		return mBronze;
	}
	
	public int getGold() {
		return mGold;
	}
	
	public int getPlatinum() {
		return mPlatinum;
	}
	
	public int getSilver() {
		return mSilver;
	}
	
	public void setBronze(int aBronze) {
		mBronze = aBronze;
	}
	
	public void setGold(int aGold) {
		mGold = aGold;
	}
	
	public void setPlatinum(int aPlatinum) {
		mPlatinum = aPlatinum;
	}
	
	public void setSilver(int aSilver) {
		mSilver = aSilver;
	}
	
	

}
