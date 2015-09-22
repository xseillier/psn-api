package com.xseillier.psnapi.model.trophy;

import java.util.List;

import com.xseillier.psnapi.model.param.TrophyPaginationParam;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophyTitleList {


	private int mLimit;
    private int mOffset;
    private int mTotalResults;
    private List<TrophyTitle> mTrophyTitles;    
    private TrophyPaginationParam mPagination;
    
//=============================================================================
// Accessor
//=============================================================================
       
	public int getLimit() {
		return mLimit;
	}
	
	public int getOffset() {
		return mOffset;
	}
	
	public int getTotalResults() {
		return mTotalResults;
	}
	
	public List<TrophyTitle> getTrophyTitles() {
		return mTrophyTitles;
	}

	public TrophyPaginationParam getNextPagination() {
		return mPagination;
	}

	public void setNextPagination(TrophyPaginationParam aAPagination) {
		mPagination = aAPagination;
	}
    
}
