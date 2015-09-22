package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum PlatformEnum implements DataEnum<String> {

	
	PS3("PS3"),
	PS4("PS4"),
	PSVITA("PSVITA");
	
	private String mData;
	
	private PlatformEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
