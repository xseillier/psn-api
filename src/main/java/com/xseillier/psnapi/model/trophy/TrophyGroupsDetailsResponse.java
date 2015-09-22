package com.xseillier.psnapi.model.trophy;

import java.util.List;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophyGroupsDetailsResponse {

	
	private FromUser mFoFromUser;	
	private List<Trophy> mTrophies;
	
//=============================================================================
// Accessor
//=============================================================================

	public FromUser getFoFromUser() {
		return mFoFromUser;
	}
	
	public List<Trophy> getTrophies() {
		return mTrophies;
	}	
}
