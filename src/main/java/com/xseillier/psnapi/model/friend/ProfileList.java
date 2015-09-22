package com.xseillier.psnapi.model.friend;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class ProfileList  {
	
	
	private List<Profile> mProfiles;
	private int           mSize;	
	
	public ProfileList()
	{
		mProfiles = new ArrayList<Profile>();
	}
//=============================================================================
// Accessor
//=============================================================================

	public List<Profile> getProfiles() {
		return mProfiles;
	}

	public void setProfiles(List<Profile> aProfilesList) {
		mProfiles = aProfilesList;
	}

	public int getSize() {
		return mSize;
	}

	public void setSize(int aSize) {
		mSize = aSize;
	}
	
}
