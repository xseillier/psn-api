package com.xseillier.psnapi.model.friend;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.ObjectPaginable;
/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class FriendList extends ObjectPaginable<FriendPagination>{
	
	
	private List<FriendProfile> mFriendList = new ArrayList<FriendProfile>();
	

//=============================================================================
// Accessor
//=============================================================================

    public List<FriendProfile> getFriendList() {
		return mFriendList;
	}
    
	public void setFriendList(List<FriendProfile> aFriendList) {
		mFriendList = aFriendList;
	}
   
}
