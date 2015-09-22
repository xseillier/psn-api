package com.xseillier.psnapi.model.trophy;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class Trophy {

	
	
	
	 private String mTrophyDetail; 
     private String mTophyEarnedRate;
     private String mTrophyHidden;
     private String mTrophyIconUrl;
     private int    mTrophyId;
     private String mTrophyName;
     private int 	mTrophyRare;
     private String mTrophyType;
     
//=============================================================================
// Accessor
//=============================================================================
    
	public String getTrophyDetail() {
		return mTrophyDetail;
	}
	
	public String getTophyEarnedRate() {
		return mTophyEarnedRate;
	}
	
	public String getTrophyHidden() {
		return mTrophyHidden;
	}
	
	public String getTrophyIconUrl() {
		return mTrophyIconUrl;
	}
	
	public int getTrophyId() {
		return mTrophyId;
	}
	
	public String getTrophyName() {
		return mTrophyName;
	}
	
	public int getTrophyRare() {
		return mTrophyRare;
	}
	
	public String getTrophyType() {
		return mTrophyType;
	}
   
}
