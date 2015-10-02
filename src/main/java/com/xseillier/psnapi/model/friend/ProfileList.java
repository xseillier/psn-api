package com.xseillier.psnapi.model.friend;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class ProfileList  {
	
	
	private List<FriendProfile> mProfiles;
	private int           mSize;	
	
	public ProfileList()
	{
		mProfiles = new ArrayList<FriendProfile>();
	}
//=============================================================================
// Accessor
//=============================================================================

	public List<FriendProfile> getProfiles() {
		return mProfiles;
	}

	public void setProfiles(List<FriendProfile> aProfilesList) {
		mProfiles = aProfilesList;
	}

	public int getSize() {
		return mSize;
	}

	public void setSize(int aSize) {
		mSize = aSize;
	}
	
}
