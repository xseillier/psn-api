package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum TrophySummaryOptionEnum implements DataEnum<String>{

	DEFAULT("@default"),
	TROPHY_RARE("trophyRare"),
	TROPHY_EARNED_RATE("trophyEarnedRate");
	
	private String mData;
	
	private TrophySummaryOptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}

}
