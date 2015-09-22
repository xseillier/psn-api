package com.xseillier.psnapi.model.trophy;


/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophySummary {

	
	private EarnedTrophies mEarnedTrophies = new EarnedTrophies();
	private int mLevel;
	private int progress;
	
//=============================================================================
// Accessor
//=============================================================================

	
	public EarnedTrophies getEarnedTrophies() {
		return mEarnedTrophies;
	}
	
	
	public int getLevel() {
		return mLevel;
	}
	
	public int getProgress() {
		return progress;
	}
	
	
	public void setEarnedTrophies(EarnedTrophies aEarnedTrophies) {
		mEarnedTrophies = aEarnedTrophies;
	}
	
	
	public void setLevel(int aLevel) {
		mLevel = aLevel;
	}
	
	public void setProgress(int aProgress) {
		progress = aProgress;
	}
	
	
}
