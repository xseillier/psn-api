package com.xseillier.psnapi.model.trophy;

import java.util.Date;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class FromUser {

	
	private EarnedTrophies mMearnedTrophies;
	
	private boolean mHiddenFlag;
    private Date mLastUpdateDate;
    private String mOnlineId;
    private int mProgress;
	private boolean mEarned;
	private Date mEarnedDate;
    
//=============================================================================
// Accessor
//=============================================================================
  
	public EarnedTrophies getMearnedTrophies() {
		return mMearnedTrophies;
	}
	
	public boolean isHiddenFlag() {
		return mHiddenFlag;
	}
	
	public Date getLastUpdateDate() {
		return mLastUpdateDate;
	}
	
	public String getOnlineId() {
		return mOnlineId;
	}
	
	public int getProgress() {
		return mProgress;
	}
    
	public boolean getEarned(){
		return mEarned;
	}

	public Date getEarnedDate() {
		return mEarnedDate;
	}
}
