package com.xseillier.psnapi.model.friend;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.ObjectPaginable;
/**
*
* @author xseillier
* @version 1.0 07 oct. 2015
*/
public class FriendReceiveRequestList extends ObjectPaginable<FriendPagination> {
	
	
	private List<FriendRequest> mReceivedRequests = new ArrayList<>();

//=============================================================================
// Accessor
//=============================================================================

	public List<FriendRequest> getReceivedRequests() {
		return mReceivedRequests;
	}
	
	public void setReceivedRequests(List<FriendRequest> aReceivedRequests) {
		mReceivedRequests = aReceivedRequests;
	}
	 
}
