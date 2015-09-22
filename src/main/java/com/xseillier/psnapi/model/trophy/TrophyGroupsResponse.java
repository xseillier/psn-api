package com.xseillier.psnapi.model.trophy;


import java.util.List;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophyGroupsResponse {

	
	private DefinedTrophies mDefinedTrophies;
	private List<TrophyGroup> mTrophyGroups;
	
//=============================================================================
// Accessor
//=============================================================================

	public DefinedTrophies getDefinedTrophies() {
		return mDefinedTrophies;
	}
	
	public List<TrophyGroup> getTrophyGroups() {
		return mTrophyGroups;
	}
	
	
}
