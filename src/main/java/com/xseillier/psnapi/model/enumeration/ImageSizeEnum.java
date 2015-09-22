package com.xseillier.psnapi.model.enumeration;

import com.google.gson.annotations.SerializedName;
import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum ImageSizeEnum implements DataEnum<String>{

	@SerializedName("s")
	SMALL("s"),
	@SerializedName("m")
	MEDIUM("m"),
	@SerializedName("l")
	LARGE("l"),
	@SerializedName("xl")
	XLARGE("xl");
	
	private String mData;
	
	private ImageSizeEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
