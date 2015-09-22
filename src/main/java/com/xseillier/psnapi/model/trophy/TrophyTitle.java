package com.xseillier.psnapi.model.trophy;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophyTitle {

	
	private DefinedTrophies mDefinedTrophies;
	private FromUser mFromUser;
	private boolean mHasTrophyGroups;
    private String mNpCommunicationId;
    private String mTrophyTitleDetail;
    private String mTrophyTitleIconUrl;
    private String mTrophyTitleName;
    private String mTrophyTitlePlatfrom;
    
//=============================================================================
// Accessor
//=============================================================================
  
	public DefinedTrophies getMdefinedTrophies() {
		return mDefinedTrophies;
	}
	
	public FromUser getFromUser() {
		return mFromUser;
	}
	
	public boolean isHasTrophyGroups() {
		return mHasTrophyGroups;
	}
	
	public String getNpCommunicationId() {
		return mNpCommunicationId;
	}
	
	public String getTrophyTitleDetail() {
		return mTrophyTitleDetail;
	}
	
	public String getTrophyTitleIconUrl() {
		return mTrophyTitleIconUrl;
	}
	
	public String getTrophyTitleName() {
		return mTrophyTitleName;
	}
	
	public String getTrophyTitlePlatfrom() {
		return mTrophyTitlePlatfrom;
	}
     
}
