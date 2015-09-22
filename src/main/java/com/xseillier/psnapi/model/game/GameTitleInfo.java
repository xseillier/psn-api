package com.xseillier.psnapi.model.game;


/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class GameTitleInfo  {

	
	private String mNpTitleId;
	private String mTitleName;
	
//=============================================================================
// Accessor
//=============================================================================

	
	public String getNpTitleId() {
		return mNpTitleId;
	}
	
	
	public String getTitleName() {
		return mTitleName;
	}
	
	public void setNpTitleId(String aNpTitleId) {
		mNpTitleId = aNpTitleId;
	}
	
	public void setTitleName(String aTitleName) {
		mTitleName = aTitleName;
	}


	@Override
	public String toString() {
		return "GameTitleInfo [mNpTitleId=" + mNpTitleId + ", mTitleName="
				+ mTitleName + "]";
	}
	
}
