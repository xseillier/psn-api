package com.xseillier.psnapi.model;

import java.util.List;

import com.xseillier.psnapi.model.friend.FriendPagination;
import com.xseillier.psnapi.model.friend.FriendProfile;


/**
 *
 * @author xseillier
 * @version 1.0 7 oct. 2015
 */
public class ObjectPaginable<T> {

	
	
	private int mSize;
    private int mStart;
    private int mTotalResults;
	
	private T mPaginationParam;

	
	public int getSize() {
		return mSize;
	}
	
	public int getStart() {
		return mStart;
	}
	
	public int getTotalResults() {
		return mTotalResults;
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
	
	public T getNextPagination() {
		return mPaginationParam;
	}

	public void setNextPagination(T aPaginationParam) {
		mPaginationParam = aPaginationParam;
	}
}
