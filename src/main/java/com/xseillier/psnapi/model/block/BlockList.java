package com.xseillier.psnapi.model.block;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.param.BlockPaginationParam;
import com.xseillier.psnapi.model.profile.BasicProfile;
/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class BlockList {
	
	
	
	private List<BasicProfile> mBlockList = new ArrayList<BasicProfile>();
	private int mSize;
    private int mStart;
    private int mTotalResults;
	
	private BlockPaginationParam mPaginationParam;


//=============================================================================
// Accessor
//=============================================================================

    public List<BasicProfile> getBlockList() {
		return mBlockList;
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
	
	public void setBlockProfileList(List<BasicProfile> aBlockProfileList) {
		mBlockList = aBlockProfileList;
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
	
	public BlockPaginationParam getNextPagination() {
		return mPaginationParam;
	}

	public void setNextPagination(BlockPaginationParam aPaginationParam) {
		mPaginationParam = aPaginationParam;
	}
}
