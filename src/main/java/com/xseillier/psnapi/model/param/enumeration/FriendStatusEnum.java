package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum FriendStatusEnum implements DataEnum<String> {

	
	FRIEND("friend"),
	REQUESTED("requested");
	
	private String mData;
	
	private FriendStatusEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
