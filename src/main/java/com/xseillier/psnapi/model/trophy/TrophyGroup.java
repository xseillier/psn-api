package com.xseillier.psnapi.model.trophy;


/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophyGroup {

	 private DefinedTrophies mDefinedTrophies;
	 private FromUser	     mFromUser;
	 private String          mTrophyGroupDetail;
	 private String          mTrophyGroupIconUrl;
	 private String          mTrophyGroupId;
	 private String          mTrophyGroupName;
	 
//=============================================================================
// Accessor
//=============================================================================
	 	
	public DefinedTrophies getDefinedTrophies() {
		return mDefinedTrophies;
	}
	
	public FromUser getFromUser() {
		return mFromUser;
	}
	
	public String getTrophyGroupDetail() {
		return mTrophyGroupDetail;
	}
	
	public String getTrophyGroupIconUrl() {
		return mTrophyGroupIconUrl;
	}
	
	public String getTrophyGroupId() {
		return mTrophyGroupId;
	}
	
	public String getTrophyGroupName() {
		return mTrophyGroupName;
	}
	 
	 
}
