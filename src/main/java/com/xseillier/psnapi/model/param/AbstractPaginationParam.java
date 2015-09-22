package com.xseillier.psnapi.model.param;
/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
abstract class AbstractPaginationParam {

	private int mOffset;
	private int mLimit;
	
	
	AbstractPaginationParam( int aOffset, int aLimit ){
		setOffset( aOffset );
		setLimit( aLimit );
	}
	
	public AbstractPaginationParam(){
		this(0, 1);
	}
	
	public int getOffset() {
		return mOffset;
	}
	public void setOffset(int aOffset) {
		mOffset = aOffset;
	}
	public int getLimit() {
		return mLimit;
	}
	public void setLimit(int aLimit) {
		mLimit = aLimit;
	}
	
}
