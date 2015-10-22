package com.xseillier.psnapi.model.friend;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.ObjectPaginable;
/**
*
* @author xseillier
* @version 1.0 07 oct. 2015
*/
public class FriendSendRequestList extends ObjectPaginable<FriendPagination> {
	
	
	private List<FriendRequest> mSentRequests = new ArrayList<>();


//=============================================================================
// Accessor
//=============================================================================

	public List<FriendRequest> getSentRequests() {
		return mSentRequests;
	}
	
	public void setSentRequests(List<FriendRequest> aSentRequests) {
		mSentRequests = aSentRequests;
	}
	
 
}
