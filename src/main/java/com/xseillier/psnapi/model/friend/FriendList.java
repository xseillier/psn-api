package com.xseillier.psnapi.model.friend;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.param.FriendPaginationParam;
/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class FriendList {
	
	
	private List<FriendProfile> mFriendList = new ArrayList<FriendProfile>();
	private int mSize;
    private int mStart;
    private int mTotalResults;
	
	private FriendPaginationParam mPaginationParam;


//=============================================================================
// Accessor
//=============================================================================

    public List<FriendProfile> getFriendList() {
		return mFriendList;
	}
    
	public int getSize() {
		return mSize;
	}
	
	public int getStart() {
		return mStart;
	}
	
	public int getTotalResults() {
		return mTotalResults;
	}
	
	public void setFriendList(List<FriendProfile> aFriendList) {
		mFriendList = aFriendList;
	}
	
	public void setSize(int aSize) {
		mSize = aSize;
	}
	
	public void setStart(int aStart) {
		mStart = aStart;
	}
	
	public void setTotalResults(int aTotalResults) {
		mTotalResults = aTotalResults;
	}
	
	public FriendPaginationParam getNextPagination() {
		return mPaginationParam;
	}

	public void setNextPagination(FriendPaginationParam aPaginationParam) {
		mPaginationParam = aPaginationParam;
	}
}
