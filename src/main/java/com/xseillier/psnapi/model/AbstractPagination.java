package com.xseillier.psnapi.model;
/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public abstract class AbstractPagination {

	private int mOffset;
	private int mLimit;
	
	
	public AbstractPagination( int aOffset, int aLimit ){
		setOffset( aOffset );
		setLimit( aLimit );
	}
	
	public AbstractPagination(){
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
