package com.xseillier.psnapi.model.enumeration;

import com.google.gson.annotations.SerializedName;
import com.xseillier.psnapi.enumeration.DataEnum;


/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public enum RelationEnum implements DataEnum<String>{

	@SerializedName("friend")
	FRIEND("friend"),
	@SerializedName("me")
	ME("me"),
	@SerializedName("no relationship")
	NO_RELATIONSHIP("me");
	
	
	private String mData;
	
	private RelationEnum ( String aData ) {
		mData = aData;
	}
	
	public String getData(){
		return mData;
	}
}
